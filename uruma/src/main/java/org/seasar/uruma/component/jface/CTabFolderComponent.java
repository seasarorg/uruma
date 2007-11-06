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
package org.seasar.uruma.component.jface;

import org.eclipse.swt.custom.CTabFolder;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link CTabFolder} に対応するコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class CTabFolderComponent extends CompositeComponent {

    private GradientInfo selectionBackgroundGradient;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("選択状態")
    private String selection;

    @RenderingPolicy(conversionType = ConversionType.INT, targetType = TargetType.FIELD)
    @FieldDescription("縦方向マージン")
    private String marginHeight;

    @RenderingPolicy(conversionType = ConversionType.INT, targetType = TargetType.FIELD)
    @FieldDescription("横方向マージン")
    private String marginWidth;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("枠線の表示状態")
    private String borderVisible;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("最大化状態")
    private String maximized;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("最大化ボタンの表示状態")
    private String maximizeVisible;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("最小化状態")
    private String minimized;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("最小化ボタンの表示状態")
    private String minimizeVisible;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("タブに表示される最小表示文字数")
    private String minimumCharacters;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("MRU 表示状態")
    private String mruVisible;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("選択タブの背景色")
    private String selectionBackground;

    @RenderingPolicy(conversionType = ConversionType.IMAGE)
    @FieldDescription("選択タブの背景イメージ")
    private String selectionBackgroundImage;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @FieldDescription("選択タブの前景色")
    private String selectionForeground;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("シンプル表示状態")
    private String simple;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("単一表示モード")
    private String single;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("タブの高さ")
    private String tabHeight;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("タブ位置")
    private String tabPosition;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("未選択タブのクローズボタン表示状態")
    private String unselectedCloseVisible;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("未選択タブのイメージ表示状態")
    private String unselectedImageVisible;

    /**
     * 選択状態を取得します。<br />
     * 
     * @return 選択状態
     */
    public String getSelection() {
        return this.selection;
    }

    /**
     * 選択状態を設定します。<br />
     * 
     * @param selection
     *            選択状態
     */
    public void setSelection(final String selection) {
        this.selection = selection;
    }

    /**
     * 枠線の表示状態を取得します。<br />
     * 
     * @return 枠線の表示状態
     */
    public String getBorderVisible() {
        return this.borderVisible;
    }

    /**
     * 枠線の表示状態を設定します。<br />
     * 
     * @param borderVisible
     *            枠線の表示状態
     */
    public void setBorderVisible(final String borderVisible) {
        this.borderVisible = borderVisible;
    }

    /**
     * 縦方向マージンを取得します。<br />
     * 
     * @return 縦方向マージン
     */
    public String getMarginHeight() {
        return this.marginHeight;
    }

    /**
     * 縦方向マージンを設定します。<br />
     * 
     * @param marginHeight
     *            縦方向マージン
     */
    public void setMarginHeight(final String marginHeight) {
        this.marginHeight = marginHeight;
    }

    /**
     * 横方向マージンを取得します。<br />
     * 
     * @return 横方向マージン
     */
    public String getMarginWidth() {
        return this.marginWidth;
    }

    /**
     * 横方向マージンを設定します。<br />
     * 
     * @param marginWidth
     *            横方向マージン
     */
    public void setMarginWidth(final String marginWidth) {
        this.marginWidth = marginWidth;
    }

    /**
     * 最大化状態を取得します。<br />
     * 
     * @return 最大化状態
     */
    public String getMaximized() {
        return this.maximized;
    }

    /**
     * 最大化状態を設定します。<br />
     * 
     * @param maximized
     *            最大化状態
     */
    public void setMaximized(final String maximized) {
        this.maximized = maximized;
    }

    /**
     * 最大化ボタンの表示状態を取得します。<br />
     * 
     * @return 最大化ボタンの表示状態
     */
    public String getMaximizeVisible() {
        return this.maximizeVisible;
    }

    /**
     * 最大化ボタンの表示状態を設定します。<br />
     * 
     * @param maximizeVisible
     *            最大化ボタンの表示状態
     */
    public void setMaximizeVisible(final String maximizeVisible) {
        this.maximizeVisible = maximizeVisible;
    }

    /**
     * 最小化状態を取得します。<br />
     * 
     * @return 最小化状態
     */
    public String getMinimized() {
        return this.minimized;
    }

    /**
     * 最小化状態を設定します。<br />
     * 
     * @param minimized
     *            最小化状態
     */
    public void setMinimized(final String minimized) {
        this.minimized = minimized;
    }

    /**
     * 最小化ボタンの表示状態を取得します。<br />
     * 
     * @return 最小化ボタンの表示状態
     */
    public String getMinimizeVisible() {
        return this.minimizeVisible;
    }

    /**
     * 最小化ボタンの表示状態を設定します。<br />
     * 
     * @param minimizeVisible
     *            最小化ボタンの表示状態
     */
    public void setMinimizeVisible(final String minimizeVisible) {
        this.minimizeVisible = minimizeVisible;
    }

    /**
     * タブに表示される最小表示文字数を取得します。<br />
     * 
     * @return タブに表示される最小表示文字数
     */
    public String getMinimumCharacters() {
        return this.minimumCharacters;
    }

    /**
     * タブに表示される最小表示文字数を設定します。<br />
     * 
     * @param minimumCharacters
     *            タブに表示される最小表示文字数
     */
    public void setMinimumCharacters(final String minimumCharacters) {
        this.minimumCharacters = minimumCharacters;
    }

    /**
     * MRU 表示状態を取得します。<br />
     * 
     * @return MRU 表示状態
     */
    public String getMruVisible() {
        return this.mruVisible;
    }

    /**
     * MRU 表示状態を設定します。<br />
     * 
     * @param mruVisible
     *            MRU 表示状態
     */
    public void setMruVisible(final String mruVisible) {
        this.mruVisible = mruVisible;
    }

    /**
     * 選択タブの背景色を取得します。<br />
     * 
     * @return 選択タブの背景色
     */
    public String getSelectionBackground() {
        return this.selectionBackground;
    }

    /**
     * 選択タブの背景色を設定します。<br />
     * 
     * @param selectionBackground
     *            選択タブの背景色
     */
    public void setSelectionBackground(final String selectionBackground) {
        this.selectionBackground = selectionBackground;
    }

    /**
     * 選択タブの背景イメージを取得します。<br />
     * 
     * @return 選択タブの背景イメージ
     */
    public String getSelectionBackgroundImage() {
        return this.selectionBackgroundImage;
    }

    /**
     * 選択タブの背景イメージを設定します。<br />
     * 
     * @param selectionBackgroundImage
     *            選択タブの背景イメージ
     */
    public void setSelectionBackgroundImage(
            final String selectionBackgroundImage) {
        this.selectionBackgroundImage = selectionBackgroundImage;
    }

    /**
     * 選択タブの前景色を取得します。<br />
     * 
     * @return 選択タブの前景色
     */
    public String getSelectionForeground() {
        return this.selectionForeground;
    }

    /**
     * 選択タブの前景色を設定します。<br />
     * 
     * @param selectionForeground
     *            選択タブの前景色
     */
    public void setSelectionForeground(final String selectionForeground) {
        this.selectionForeground = selectionForeground;
    }

    /**
     * シンプル表示状態を取得します。<br />
     * 
     * @return シンプル表示状態
     */
    public String getSimple() {
        return this.simple;
    }

    /**
     * シンプル表示状態を設定します。<br />
     * 
     * @param simple
     *            シンプル表示状態
     */
    public void setSimple(final String simple) {
        this.simple = simple;
    }

    /**
     * 単一表示モードを取得します。<br />
     * 
     * @return 単一表示モード
     */
    public String getSingle() {
        return this.single;
    }

    /**
     * 単一表示モードを設定します。<br />
     * 
     * @param single
     *            単一表示モード
     */
    public void setSingle(final String single) {
        this.single = single;
    }

    /**
     * タブの高さを取得します。<br />
     * 
     * @return タブの高さ
     */
    public String getTabHeight() {
        return this.tabHeight;
    }

    /**
     * タブの高さを設定します。<br />
     * 
     * @param tabHeight
     *            タブの高さ
     */
    public void setTabHeight(final String tabHeight) {
        this.tabHeight = tabHeight;
    }

    /**
     * タブ位置を取得します。<br />
     * 
     * @return タブ位置
     */
    public String getTabPosition() {
        return this.tabPosition;
    }

    /**
     * タブ位置を設定します。<br />
     * 
     * @param tabPosition
     *            タブ位置
     */
    public void setTabPosition(final String tabPosition) {
        this.tabPosition = tabPosition;
    }

    /**
     * 未選択タブのクローズボタン表示状態を取得します。<br />
     * 
     * @return 未選択タブのクローズボタン表示状態
     */
    public String getUnselectedCloseVisible() {
        return this.unselectedCloseVisible;
    }

    /**
     * 未選択タブのクローズボタン表示状態を設定します。<br />
     * 
     * @param unselectedCloseVisible
     *            未選択タブのクローズボタン表示状態
     */
    public void setUnselectedCloseVisible(final String unselectedCloseVisible) {
        this.unselectedCloseVisible = unselectedCloseVisible;
    }

    /**
     * 未選択タブのイメージ表示状態を取得します。<br />
     * 
     * @return 未選択タブのイメージ表示状態
     */
    public String getUnselectedImageVisible() {
        return this.unselectedImageVisible;
    }

    /**
     * 未選択タブのイメージ表示状態を設定します。<br />
     * 
     * @param unselectedImageVisible
     *            未選択タブのイメージ表示状態
     */
    public void setUnselectedImageVisible(final String unselectedImageVisible) {
        this.unselectedImageVisible = unselectedImageVisible;
    }

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
