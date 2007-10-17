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

import org.eclipse.swt.widgets.TableColumn;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link TableColumn} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class TableColumnComponent extends AbstractItemComponent {

    @RenderingPolicy(conversionType = ConversionType.SWT_CONST)
    @FieldDescription("アライメント")
    private String alignment;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("移動可不可状態")
    private String moveable;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("サイズ変更可不可状態")
    private String resizable;

    @RenderingPolicy(conversionType = ConversionType.TEXT)
    @FieldDescription("ツールチップテキスト")
    private String toolTipText;

    @RenderingPolicy(conversionType = ConversionType.INT)
    @FieldDescription("幅")
    private String width;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("カラム位置")
    private int columnNo;

    /**
     * アライメントを取得します。<br />
     * 
     * @return アライメント
     */
    public String getAlignment() {
        return this.alignment;
    }

    /**
     * アライメントを設定します。<br />
     * 
     * @param alignment
     *            アライメント
     */
    public void setAlignment(final String alignment) {
        this.alignment = alignment;
    }

    /**
     * 移動可不可状態を取得します。<br />
     * 
     * @return 移動可不可状態
     */
    public String getMoveable() {
        return this.moveable;
    }

    /**
     * 移動可不可状態を設定します。<br />
     * 
     * @param moveable
     *            移動可不可状態
     */
    public void setMoveable(final String moveable) {
        this.moveable = moveable;
    }

    /**
     * サイズ変更可不可状態を取得します。<br />
     * 
     * @return サイズ変更可不可状態
     */
    public String getResizable() {
        return this.resizable;
    }

    /**
     * サイズ変更可不可状態を設定します。<br />
     * 
     * @param resizable
     *            サイズ変更可不可状態
     */
    public void setResizable(final String resizable) {
        this.resizable = resizable;
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
     * ツールチップテキストを設定します。<br />
     * 
     * @param toolTipText
     *            ツールチップテキスト
     */
    public void setToolTipText(final String toolTipText) {
        this.toolTipText = toolTipText;
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
     * 幅を設定します。<br />
     * 
     * @param width
     *            幅
     */
    public void setWidth(final String width) {
        this.width = width;
    }

    /**
     * カラム位置を取得します。<br />
     * 
     * @return カラム位置
     */
    public int getColumnNo() {
        return this.columnNo;
    }

    /**
     * カラム位置を設定します。<br />
     * 
     * @param columnNo
     *            カラム位置
     */
    public void setColumnNo(final int columnNo) {
        this.columnNo = columnNo;
    }
}
