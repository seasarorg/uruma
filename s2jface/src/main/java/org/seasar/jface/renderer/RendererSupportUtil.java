/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.renderer;

import java.lang.reflect.Field;
import java.util.StringTokenizer;

import org.eclipse.jface.action.LegacyActionTools;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.annotation.component.ComponentAttribute.SetTiming;
import org.seasar.jface.component.UIElement;
import org.seasar.jface.exception.RenderException;
import org.seasar.jface.util.FontManager;
import org.seasar.jface.util.ImageManager;
import org.seasar.jface.util.PathUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * レンダリングのサポートを行うユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class RendererSupportUtil {
    /**
     * <code>src</code> でアノテートされたフィールドを <code>dest</code> へコピーします。<br />
     * <p>
     * src オブジェクトの持つフィールドのうち、{@link ComponentAttribute}
     * アノテーションが指定されたフィールドで、現在のタイミングと同じタイミングが指定されたフィールドを、 アノテーションの示す方法で変換して
     * <code>dest</code> の同名フィールドへコピーします。<br />
     * コピー方法の詳細は、{@link ComponentAttribute} のドキュメントを参照してください。<br />
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
        int fieldSize = beanDesc.getFieldSize();
        for (int i = 0; i < fieldSize; i++) {
            Field field = beanDesc.getField(i);
            ComponentAttribute attr = field
                    .getAnnotation(ComponentAttribute.class);
            if (attr != null && attr.setTiming() == nowTiming) {
                String value = getSrcValue(src, beanDesc, field);
                if (value != null) {
                    setValue(src, dest, field, attr, value);
                }
            }
        }
    }

    private static String getSrcValue(final UIElement src,
            final BeanDesc beanDesc, final Field field) {
        if (field.getType() == String.class) {
            String fieldName = field.getName();
            if (beanDesc.hasPropertyDesc(field.getName())) {
                PropertyDesc pd = beanDesc.getPropertyDesc(fieldName);
                if (pd.hasReadMethod()) {
                    return (String) pd.getValue(src);
                }
            }
        }
        return null;
    }

    private static void setValue(final UIElement src, final Object dest,
            final Field field, final ComponentAttribute attr, final String value) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(dest.getClass());

        try {
            if (attr.targetType() == ComponentAttribute.TargetType.PROPERTY) {
                PropertyDesc pd = desc.getPropertyDesc(field.getName());
                if (pd.hasWriteMethod()) {
                    pd.setValue(dest, convertValue(src, value, attr
                            .conversionType()));
                }
            } else if (attr.targetType() == ComponentAttribute.TargetType.FIELD) {
                Field destField = desc.getField(field.getName());
                FieldUtil.set(destField, dest, convertValue(src, value, attr
                        .conversionType()));
            }
        } catch (Exception ex) {
            // TODO 後で削除
            ex.printStackTrace();
            throw new RenderException(RenderException.MAPPING_ERORR, ex, field
                    .getName(), dest.getClass().getName(), value);
        }
    }

    public static Object convertValue(final UIElement src, final String value,
            final ConversionType conversionType) {
        if (conversionType == ConversionType.STRING) {
            return value;
        } else if (conversionType == ConversionType.TEXT) {
            String text = value.replace("\\n", "\n");
            text = text.replace("\\t", "\t");
            if (text.startsWith("\"") && text.endsWith("\"")) {
                text = text.substring(1, text.length() - 1);
            }
            return text;
        } else if (conversionType == ConversionType.BOOLEAN) {
            return Boolean.valueOf(value);
        } else if (conversionType == ConversionType.INT) {
            return new Integer(value);
        } else if (conversionType == ConversionType.INT_ARRAY) {
            StringTokenizer tokenizer = new StringTokenizer(value, ",");
            int[] result = new int[tokenizer.countTokens()];
            for (int i = 0; tokenizer.hasMoreTokens(); i++) {
                String token = tokenizer.nextToken();
                result[i] = Integer.parseInt(token.trim());
            }
            return result;
        } else if (conversionType == ConversionType.CHAR) {
            assert value.length() == 1;
            return new Character(value.charAt(0));
        } else if (conversionType == ConversionType.SWT_CONST) {
            return SWTUtil.getStyle(value);
        } else if (conversionType == ConversionType.COLOR) {
            return SWTUtil.getColor(value);
        } else if (conversionType == ConversionType.IMAGE) {
            Image image = ImageManager.getImage(value);
            if (image == null) {
                String path = PathUtil.createPath(src.getBasePath(), value);
                image = ImageManager.loadImage(path);
            }
            return image;
        } else if (conversionType == ConversionType.ACCELERATOR) {
            return LegacyActionTools.convertAccelerator(value);
        }
        return null;
    }

    public static Font getFont(Font defaultFont, String fontName,
            String fontStyle, String fontHeight) {
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
