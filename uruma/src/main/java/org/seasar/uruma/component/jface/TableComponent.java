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

import org.eclipse.swt.widgets.Table;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;

/**
 * {@link Table} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
public class TableComponent extends CompositeComponent {

    @RenderingPolicy(conversionType = ConversionType.INT_ARRAY, setTiming = SetTiming.RENDER_AFTER)
    @FieldDescription("カラム順序")
    private String columnOrder;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("ヘッダ表示状態")
    private String headerVisible = "true";

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @FieldDescription("罫線表示状態")
    private String linesVisible = "true";

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("選択状態")
    private String selection;

    @RenderingPolicy(conversionType = ConversionType.INT, setTiming = SetTiming.RENDER_AFTER)
    @FieldDescription("最上位表示項目")
    private String topIndex;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("カラム数")
    private int columnCount;

    /**
     * カラム順序を取得します。<br />
     * 
     * @return カラム順序
     */
    public String getColumnOrder() {
        return this.columnOrder;
    }

    /**
     * カラム順序を設定します。<br />
     * 
     * @param columnOrder
     *            カラム順序
     */
    public void setColumnOrder(final String columnOrder) {
        this.columnOrder = columnOrder;
    }

    /**
     * ヘッダ表示状態を取得します。<br />
     * 
     * @return ヘッダ表示状態
     */
    public String getHeaderVisible() {
        return this.headerVisible;
    }

    /**
     * ヘッダ表示状態を設定します。<br />
     * 
     * @param headerVisible
     *            ヘッダ表示状態
     */
    public void setHeaderVisible(final String headerVisible) {
        this.headerVisible = headerVisible;
    }

    /**
     * 罫線表示状態を取得します。<br />
     * 
     * @return 罫線表示状態
     */
    public String getLinesVisible() {
        return this.linesVisible;
    }

    /**
     * 罫線表示状態を設定します。<br />
     * 
     * @param linesVisible
     *            罫線表示状態
     */
    public void setLinesVisible(final String linesVisible) {
        this.linesVisible = linesVisible;
    }

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
     * 最上位表示項目を取得します。<br />
     * 
     * @return 最上位表示項目
     */
    public String getTopIndex() {
        return this.topIndex;
    }

    /**
     * 最上位表示項目を設定します。<br />
     * 
     * @param topIndex
     *            最上位表示項目
     */
    public void setTopIndex(final String topIndex) {
        this.topIndex = topIndex;
    }

    /**
     * カラム数を取得します。<br />
     * 
     * @return カラム数
     */
    public int getColumnCount() {
        return this.columnCount;
    }

    /**
     * カラム数を設定します。<br />
     * 
     * @param columnCount
     *            カラム数
     */
    public void setColumnCount(final int columnCount) {
        this.columnCount = columnCount;
    }
}
