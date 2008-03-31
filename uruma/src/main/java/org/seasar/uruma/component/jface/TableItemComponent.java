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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.TableItem;
import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.annotation.ComponentElement;
import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.base.AbstractItemComponent;

/**
 * {@link TableItem} を表すコンポーネントです。<br />
 * 
 * @author bskuroneko
 */
@ComponentElement
public class TableItemComponent extends AbstractItemComponent {

    private List<TableCellComponent> tableCells = new ArrayList<TableCellComponent>();

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @ComponentAttribute
    @FieldDescription("背景色")
    public String background;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("チェック状態")
    public String checked;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("フォント高さ")
    public String fontHeight;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("フォント名称")
    public String fontName;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("フォントスタイル")
    public String fontStyle;

    @RenderingPolicy(conversionType = ConversionType.COLOR)
    @ComponentAttribute
    @FieldDescription("前景色")
    public String foreground;

    @RenderingPolicy(conversionType = ConversionType.BOOLEAN)
    @ComponentAttribute
    @FieldDescription("グレーアウト状態")
    public String grayed;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("イメージパス")
    public String image;

    @RenderingPolicy(targetType = TargetType.NONE)
    @ComponentAttribute
    @FieldDescription("テキスト")
    public String text;

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
