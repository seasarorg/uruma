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

import org.eclipse.swt.widgets.Control;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.EnablesDependable;
import org.seasar.uruma.component.LayoutDataInfo;
import org.seasar.uruma.component.UIControlComponent;

/**
 * {@link Control} を表す基底コンポーネントクラスです。<br />
 * 
 * @author y-komori
 */
public abstract class ControlComponent extends AbstractUIComponent implements
        UIControlComponent, EnablesDependable {
    private LayoutDataInfo layoutDataInfo;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @FieldDescription("背景色")
    private String background;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("背景イメージパス")
    private String backgroundImage;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("イネーブル状態")
    private String enabled;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("フォント高さ")
    private String fontHeight;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("フォント名称")
    private String fontName;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("フォントスタイル")
    private String fontStyle;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @FieldDescription("前景色")
    private String foreground;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("高さ")
    private String height;

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @FieldDescription("ツールチップテキスト")
    private String toolTipText;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("可視状態")
    private String visible;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("幅")
    private String width;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("X座標")
    private String x;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("Y座標")
    private String y;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("メニューのID")
    private String menu;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("依存先コンポーネントのID")
    private String enablesDependingId;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("依存方法")
    private String enablesForType;

    /**
     * 背景色を取得します。<br />
     * 
     * @return 背景色
     */
    public String getBackground() {
        return this.background;
    }

    /**
     * 背景イメージパスを取得します。<br />
     * 
     * @return 背景イメージパス
     */
    public String getBackgroundImage() {
        return this.backgroundImage;
    }

    /**
     * イネーブル状態を取得します。<br />
     * 
     * @return イネーブル状態
     */
    public String getEnabled() {
        return this.enabled;
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
     * フォント名称を取得します。<br />
     * 
     * @return フォント名称
     */
    public String getFontName() {
        return this.fontName;
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
     * 前景色を取得します。<br />
     * 
     * @return 前景色
     */
    public String getForeground() {
        return this.foreground;
    }

    /**
     * 高さを取得します。<br />
     * 
     * @return 高さ
     */
    public String getHeight() {
        return this.height;
    }

    /**
     * ツールチップテキストを取得します。<br />
     * 
     * @return ツールチップテキスト
     */
    public String getToolTipText() {
        return this.toolTipText;
    }

    /**
     * 可視状態を取得します。<br />
     * 
     * @return 可視状態
     */
    public String getVisible() {
        return this.visible;
    }

    /**
     * 幅を取得します。<br />
     * 
     * @return 幅
     */
    public String getWidth() {
        return this.width;
    }

    /**
     * X 座標を取得します。<br />
     * 
     * @return X 座標
     */
    public String getX() {
        return this.x;
    }

    /**
     * Y 座標を取得します。<br />
     * 
     * @return Y 座標
     */
    public String getY() {
        return this.y;
    }

    /**
     * 背景色を設定します。<br />
     * 
     * @param background
     *            背景色
     */
    public void setBackground(final String background) {
        this.background = background;
    }

    /**
     * 背景イメージパスを設定します。<br />
     * 
     * @param backgroundImage
     *            背景イメージパス
     */
    public void setBackgroundImage(final String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    /**
     * イネーブル状態を設定します。<br />
     * 
     * @param enabled
     *            イネーブル状態
     */
    public void setEnabled(final String enabled) {
        this.enabled = enabled;
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
     * フォント名称を設定します。<br />
     * 
     * @param fontName
     *            フォント名称
     */
    public void setFontName(final String fontName) {
        this.fontName = fontName;
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

    /**
     * 前景色を設定します。<br />
     * 
     * @param foreground
     *            前景色
     */
    public void setForeground(final String foreground) {
        this.foreground = foreground;
    }

    /**
     * 高さを設定します。<br />
     * 
     * @param height
     *            高さ
     */
    public void setHeight(final String height) {
        this.height = height;
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
     * 可視状態を設定します。<br />
     * 
     * @param visible
     *            可視状態
     */
    public void setVisible(final String visible) {
        this.visible = visible;
    }

    /**
     * 幅を設定します。<br />
     * 
     * @param width
     *            幅
     */
    public void setWidth(final String width) {
        this.width = width;
    }

    /**
     * X 座標を設定します。<br />
     * 
     * @param x
     *            X 座標
     */
    public void setX(final String x) {
        this.x = x;
    }

    /**
     * Y 座標を設定します。<br />
     * 
     * @param y
     *            Y座標
     */
    public void setY(final String y) {
        this.y = y;
    }

    /**
     * メニューのIDを取得します。<br />
     * 
     * @return メニューのID
     */
    public String getMenu() {
        return this.menu;
    }

    /**
     * メニューのIDを設定します。
     * 
     * @param menu
     *            メニューのID
     */
    public void setMenu(final String menu) {
        this.menu = menu;
    }

    /*
     * @see org.seasar.uruma.component.EnablesDependable#getEnablesDependingId()
     */
    public String getEnablesDependingId() {
        return this.enablesDependingId;
    }

    /**
     * 依存先コンポーネントの ID を設定します。<br />
     * 
     * @param enablesDependingId
     *            依存先コンポーネントの ID
     */
    public void setEnablesDependingId(final String enablesDependingId) {
        this.enablesDependingId = enablesDependingId;
    }

    /*
     * @see org.seasar.uruma.component.EnablesDependable#getEnablesForType()
     */
    public String getEnablesForType() {
        return this.enablesForType;
    }

    /**
     * 依存方法を設定します。<br />
     * 
     * @param enablesForType
     *            依存方法
     */
    public void setEnablesForType(final String enablesForType) {
        this.enablesForType = enablesForType;
    }

    /*
     * @see org.seasar.uruma.component.UIControlComponent#getLayoutDataInfo()
     */
    public LayoutDataInfo getLayoutDataInfo() {
        return this.layoutDataInfo;
    }

    /*
     * @see org.seasar.uruma.component.UIControlComponent#setLayoutDataInfo(org.seasar.uruma.component.LayoutDataInfo)
     */
    public void setLayoutDataInfo(final LayoutDataInfo layoutDataInfo) {
        this.layoutDataInfo = layoutDataInfo;
    }
}
