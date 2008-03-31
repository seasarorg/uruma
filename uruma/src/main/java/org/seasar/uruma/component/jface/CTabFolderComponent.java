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
package org.seasar.uruma.component.jface;

import org.eclipse.swt.custom.CTabFolder;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link CTabFolder} に対応するコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
@ComponentElement
public class CTabFolderComponent extends CompositeComponent {

    private GradientInfo selectionBackgroundGradient;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("選択状態")
    public String selection;

    @RenderingPolicy(conversionType = ConversionType.INT, targetType = TargetType.FIELD)
    @ComponentAttribute
    @FieldDescription("縦方向マージン")
    public String marginHeight;

    @RenderingPolicy(conversionType = ConversionType.INT, targetType = TargetType.FIELD)
    @ComponentAttribute
    @FieldDescription("横方向マージン")
    public String marginWidth;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("枠線の表示状態")
    public String borderVisible;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("最大化状態")
    public String maximized;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("最大化ボタンの表示状態")
    public String maximizeVisible;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("最小化状態")
    public String minimized;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("最小化ボタンの表示状態")
    public String minimizeVisible;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("タブに表示される最小表示文字数")
    public String minimumCharacters;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("MRU 表示状態")
    public String mruVisible;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("選択タブの背景色")
    public String selectionBackground;

    @RenderingPolicy(conversionType = ConversionType.IMAGE)
    @ComponentAttribute
    @FieldDescription("選択タブの背景イメージ")
    public String selectionBackgroundImage;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @ComponentAttribute
    @FieldDescription("選択タブの前景色")
    public String selectionForeground;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("シンプル表示状態")
    public String simple;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("単一表示モード")
    public String single;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("タブの高さ")
    public String tabHeight;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @ComponentAttribute
    @FieldDescription("タブ位置")
    public String tabPosition;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("未選択タブのクローズボタン表示状態")
    public String unselectedCloseVisible;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("未選択タブのイメージ表示状態")
    public String unselectedImageVisible;

    /**
     * 選択タブの背景グラデーション色を取得します。<br />
     * 
     * @return 選択タブの背景グラデーション色
     */
    public GradientInfo getSelectionBackgroundGradient() {
        return this.selectionBackgroundGradient;
    }

    /**
     * 選択タブの背景グラデーション色を設定します。<br />
     * 
     * @param selectionBackgroundGradient
     *            選択タブの背景グラデーション色
     */
    public void setSelectionBackgroundGradient(
            final GradientInfo selectionBackgroundGradient) {
        this.selectionBackgroundGradient = selectionBackgroundGradient;
    }
}
