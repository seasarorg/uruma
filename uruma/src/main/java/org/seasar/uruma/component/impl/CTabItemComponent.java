/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.uruma.component.impl;

import org.eclipse.swt.custom.CTabItem;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link CTabItem} に対応するコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class CTabItemComponent extends AbstractUIContainerItemComponent {

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @FieldDescription("ツールチップテキスト")
    private String toolTipText;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("フォント高さ")
    private String fontHeight;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("フォント名称")
    private String fontName;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("フォントスタイル")
    private String fontStyle;

    /**
     * ツールチップテキストを取得します。<br />
     * 
     * @return ツールチップテキスト
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * ツールチップテキストを設定します。<br />
     * 
     * @param toolTipText
     *            ツールチップテキスト
     */
    public void setToolTipText(final String toolTipText) {
        this.toolTipText = toolTipText;
    }

    /**
     * フォント高さを取得します。<br />
     * 
     * @return フォント高さ
     */
    public String getFontHeight() {
        return this.fontHeight;
    }

    /**
     * フォント高さを設定します。<br />
     * 
     * @param fontHeight
     *            フォント高さ
     */
    public void setFontHeight(final String fontHeight) {
        this.fontHeight = fontHeight;
    }

    /**
     * フォント名称を取得します。<br />
     * 
     * @return フォント名称
     */
    public String getFontName() {
        return this.fontName;
    }

    /**
     * フォント名称を設定します。<br />
     * 
     * @param fontName
     *            フォント名称
     */
    public void setFontName(final String fontName) {
        this.fontName = fontName;
    }

    /**
     * フォントスタイルを取得します。<br />
     * 
     * @return フォントスタイル
     */
    public String getFontStyle() {
        return this.fontStyle;
    }

    /**
     * フォントスタイルを設定します。<br />
     * 
     * @param fontStyle
     *            フォントスタイル
     */
    public void setFontStyle(final String fontStyle) {
        this.fontStyle = fontStyle;
    }
}
