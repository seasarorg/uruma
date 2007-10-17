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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.TableItem;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link TableItem} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class TableItemComponent extends AbstractItemComponent {

    private List<TableCellComponent> tableCells = new ArrayList<TableCellComponent>();

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @FieldDescription("背景色")
    private String background;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("チェック状態")
    private String checked;

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

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("グレーアウト状態")
    private String grayed;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("イメージパス")
    private String image;

    @RenderingPolicy(targetType = TargetType.NONE)
    private String text;

    /**
     * 背景色を取得します。<br />
     * 
     * @return 背景色
     */
    public String getBackground() {
        return this.background;
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
     * チェック状態を取得します。<br />
     * 
     * @return チェック状態
     */
    public String getChecked() {
        return this.checked;
    }

    /**
     * チェック状態を設定します。<br />
     * 
     * @param checked
     *            チェック状態
     */
    public void setChecked(final String checked) {
        this.checked = checked;
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

    /**
     * 前景色を取得します。<br />
     * 
     * @return 前景色
     */
    public String getForeground() {
        return this.foreground;
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
     * グレーアウト状態を取得します。<br />
     * 
     * @return グレーアウト状態
     */
    public String getGrayed() {
        return this.grayed;
    }

    /**
     * グレーアウト状態を設定します。<br />
     * 
     * @param grayed
     *            グレーアウト状態
     */
    public void setGrayed(final String grayed) {
        this.grayed = grayed;
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractItemComponent#getImage()
     */
    @Override
    public String getImage() {
        return this.image;
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractItemComponent#setImage(java.lang.String)
     */
    @Override
    public void setImage(final String image) {
        this.image = image;
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractItemComponent#getText()
     */
    @Override
    public String getText() {
        return this.text;
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractItemComponent#setText(java.lang.String)
     */
    @Override
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * テーブルセルを追加します。<br />
     * 
     * @param cell
     *            {@link TableCellComponent} オブジェクト
     */
    public void addTableCell(final TableCellComponent cell) {
        this.tableCells.add(cell);
    }

    /**
     * テーブルセルのリストを取得します。<br />
     * 
     * @return テーブルセルのリスト
     */
    public List<TableCellComponent> getTableCells() {
        return Collections.unmodifiableList(tableCells);
    }
}
