/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.uruma.renderer;

import java.lang.reflect.Field;
import java.util.StringTokenizer;

import org.eclipse.jface.action.LegacyActionTools;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.seasar.eclipse.common.util.FontManager;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.eclipse.common.util.SWTUtil;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.RenderException;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.PathUtil;

/**
 * レンダリングのサポートを行うユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class RendererSupportUtil {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(RendererSupportUtil.class);

    /**
     * <code>src</code> でアノテートされたフィールドを <code>dest</code> へコピーします。<br />
     * <p>
     * src オブジェクトの持つフィールドのうち、 {@link RenderingPolicy}
     * アノテーションが指定されたフィールドで、現在のタイミングと同じタイミングが指定されたフィールドを、 アノテーションの示す方法で変換して
     * <code>dest</code> の同名フィールドへコピーします。<br />
     * コピー方法の詳細は、 {@link RenderingPolicy} のドキュメントを参照してください。<br />
     * </p>
     * 
     * @param src
     *            転送元オブジェクト
     * @param dest
     *            転送先オブジェクト
     * @param nowTiming
     *            現在のタイミング
     */
    public static void setAttributes(final UIElement src, final Object dest,
            final SetTiming nowTiming) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(src.getClass());
        int pdSize = beanDesc.getPropertyDescSize();

        for (int i = 0; i < pdSize; i++) {
            PropertyDesc pd = beanDesc.getPropertyDesc(i);
            Field field = pd.getField();

            if (field != null) {
                RenderingPolicy policy = field
                        .getAnnotation(RenderingPolicy.class);
                if ((policy != null) && (policy.setTiming() == nowTiming)
                        && (policy.targetType() != TargetType.NONE)) {
                    String value = getSrcValue(src, pd);
                    if (value != null) {
                        setValue(src, dest, pd, policy, value);
                    }
                }
            }
        }
    }

    private static String getSrcValue(final UIElement src, final PropertyDesc pd) {
        if (String.class.isAssignableFrom(pd.getPropertyType())) {
            return (String) pd.getValue(src);
        } else {
            logger.log(UrumaMessageCodes.COMPONENT_PROPERTY_IS_NOT_STRING, pd
                    .getBeanDesc().getBeanClass().getName(), pd
                    .getPropertyName());
            return null;
        }
    }

    private static void setValue(final UIElement src, final Object dest,
            final PropertyDesc srcPd, final RenderingPolicy policy,
            final String value) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(dest.getClass());
        String propertyName = srcPd.getPropertyName();
        try {
            if (desc.hasPropertyDesc(propertyName)) {
                PropertyDesc destPd = desc.getPropertyDesc(propertyName);
                if (destPd.isWritable()) {
                    destPd.setValue(dest, convertValue(src, value, policy
                            .conversionType()));
                } else {
                    logger.log(UrumaMessageCodes.PROPERTY_IS_NOT_WRITABLE, dest
                            .getClass().getName(), destPd.getPropertyName());
                }
            } else {
                logger.log(UrumaMessageCodes.WIDGET_PROPERTY_NOT_FOUND, dest
                        .getClass().getName(), propertyName);
            }
        } catch (Exception ex) {
            throw new RenderException(UrumaMessageCodes.RENDER_MAPPING_FAILED,
                    ex, propertyName, dest.getClass().getName(), value);
        }
    }

    /**
     * {@link ConversionType} にしたがって値を変換します。<br />
     * 
     * @param src
     *            変換元の値を保持する {@link UIElement} オブジェクト
     * @param value
     *            変換元の値
     * @param conversionType
     *            変換方式を指定する {@link ConversionType} オブジェクト
     * @return 変換結果オブジェクト
     */
    public static Object convertValue(final UIElement src, final String value,
            final ConversionType conversionType) {

        if (conversionType == ConversionType.STRING) {
            return value;
        } else if (conversionType == ConversionType.TEXT) {
            return convertText(value);
        } else if (conversionType == ConversionType.BOOLEAN) {
            return convertBoolean(value);
        } else if (conversionType == ConversionType.INT) {
            return convertInt(value);
        } else if (conversionType == ConversionType.INT_ARRAY) {
            return convertIntArray(value);
        } else if (conversionType == ConversionType.CHAR) {
            return convertCharacter(value);
        } else if (conversionType == ConversionType.SWT_CONST) {
            return convertSwtConst(value);
        } else if (conversionType == ConversionType.COLOR) {
            return convertColor(value);
        } else if (conversionType == ConversionType.IMAGE) {
            return convertImage(value, src.getBasePath());
        } else if (conversionType == ConversionType.ACCELERATOR) {
            return convertAccelerator(value);
        }
        return null;
    }

    /**
     * テキストの変換を行います。<br />
     * 
     * @param value
     *            変換対象
     * @return 変換結果
     */
    public static String convertText(final String value) {
        String text = value.replace("\\n", "\n");
        text = text.replace("\\t", "\t");
        if (text.startsWith("\"") && text.endsWith("\"")) {
            text = text.substring(1, text.length() - 1);
        }
        return text;
    }

    /**
     * <code>boolean</code> 型への変換を行います。<br />
     * 
     * @param value
     *            変換対象
     * @return 変換結果
     */
    public static boolean convertBoolean(final String value) {
        return Boolean.valueOf(value).booleanValue();
    }

    /**
     * <code>int</code> 型への変換を行います。<br />
     * 
     * @param value
     *            変換対象
     * @return 変換結果
     */
    public static int convertInt(final String value) {
        return Integer.valueOf(value).intValue();
    }

    /**
     * カンマ区切りの数値を <code>int</code> 型配列へ変換します。<br />
     * 
     * @param value
     *            変換対象
     * @return 変換結果
     */
    public static int[] convertIntArray(final String value) {
        StringTokenizer tokenizer = new StringTokenizer(value, ",");
        int[] result = new int[tokenizer.countTokens()];
        for (int i = 0; tokenizer.hasMoreTokens(); i++) {
            String token = tokenizer.nextToken();
            result[i] = convertInt(token.trim());
        }
        return result;
    }

    /**
     * {@link Character} 型への変換を行います。<br />
     * 
     * @param value
     *            変換対象
     * @return 変換結果
     */
    public static Character convertCharacter(final String value) {
        if (value.length() > 0) {
            return Character.valueOf(value.charAt(0));
        } else {
            return null;
        }
    }

    /**
     * {@link SWT} 定数への変換を行います。<br />
     * 
     * @param value
     *            変換対象
     * @return 変換結果
     * @see SWTUtil#getStyle(String)
     */
    public static int convertSwtConst(final String value) {
        return SWTUtil.getStyle(value);
    }

    /**
     * {@link Color} オブジェクトへの変換を行います。<br />
     * 
     * @param value
     *            変換対象
     * @return 変換結果
     * @see SWTUtil#getColor(String)
     */
    public static Color convertColor(final String value) {
        return SWTUtil.getColor(value);
    }

    /**
     * <code>value</code> の示すパスからイメージを読み込みます。<br />
     * 
     * @param value
     *            パス
     * @param basePath
     *            ベースパス
     * @return 変換結果
     * @see ImageManager
     */
    public static Image convertImage(final String value, final String basePath) {
        Image image = ImageManager.getImage(value);
        if (image == null) {
            String path = PathUtil.createPath(basePath, value);
            image = ImageManager.loadImage(path);
        }
        return image;
    }

    /**
     * <code>value</code> の示すパスからイメージを読み込みます。<br />
     * 
     * @param value
     *            パス
     * @return 変換結果
     * @see ImageManager
     */
    public static Image convertImage(final String value) {
        return convertImage(value, "");
    }

    /**
     * <code>value</code> のパスの指すイメージを表す {@link ImageDescriptor} を返します。<br />
     * 
     * @param value
     *            パス
     * @param basePath
     *            ベースパス
     * @return 変換結果
     * @see ImageManager
     */
    public static ImageDescriptor convertImageDescriptor(final String value,
            final String basePath) {
        ImageDescriptor image = ImageManager.getImageDescriptor(value);
        if (image == null) {
            String path = PathUtil.createPath(basePath, value);
            image = ImageManager.loadImageDescriptor(path);
        }
        return image;
    }

    /**
     * <code>value</code> のパスの指すイメージを表す {@link ImageDescriptor} を返します。<br />
     * 
     * @param value
     *            パス
     * @return 変換結果
     * @see ImageManager
     */
    public static ImageDescriptor convertImageDescriptor(final String value) {
        return convertImageDescriptor(value, "");
    }

    /**
     * キーアクセラレータの <code>int</code> 値への変換を行います<br />。
     * 
     * @param value
     *            変換対象
     * @return 変換結果
     */
    public static int convertAccelerator(final String value) {
        return LegacyActionTools.convertAccelerator(value);
    }

    /**
     * {@link Font} オブジェクトを取得します。<br />
     * 
     * @param defaultFont
     *            見つからなかった場合のデフォルト {@link Font} オブジェクト
     * @param fontName
     *            フォント名称
     * @param fontStyle
     *            フォントスタイル
     * @param fontHeight
     *            フォント高さ
     * @return {@link Font} オブジェクト
     */
    public static Font getFont(final Font defaultFont, String fontName,
            final String fontStyle, final String fontHeight) {
        FontData fontData = defaultFont.getFontData()[0];

        if (fontName == null) {
            fontName = fontData.getName();
        }

        int style;
        if (fontStyle != null) {
            style = SWTUtil.getStyle(fontStyle);
            style = (style == SWT.NONE) ? SWT.NORMAL : style;
        } else {
            style = fontData.getStyle();
        }

        int height;
        if (fontHeight != null) {
            height = Integer.parseInt(fontHeight);
        } else {
            height = fontData.getHeight();
        }

        return FontManager.get(fontName, height, style);
    }
}
