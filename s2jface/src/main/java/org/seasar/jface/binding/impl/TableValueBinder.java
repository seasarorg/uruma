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
package org.seasar.jface.binding.impl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.annotation.BindingValue;
import org.seasar.jface.component.impl.TableColumnComponent;
import org.seasar.jface.util.PropertyUtil;

/**
 * {@link Table} ウィジットに対する {@link org.seasar.jface.binding.ValueBinder} です。
 * 
 * @author bskuroneko
 */
public class TableValueBinder extends AbstractIterableWidgetValueBinder {

    public TableValueBinder() {
        super(Table.class);
    }

    @Override
    protected Object getWidgetValue(Widget widget) {
        // TODO 実装
        throw new UnsupportedOperationException(
                "TableValueBinder#getWidgetValueは未実装です");
    }

    @Override
    protected void clearWidgetValue(Widget widget) {
        Table table = (Table) widget;
        table.removeAll();
    }

    @Override
    protected void addWidgetValue(Widget widget, Object value,
            BindingValue annotation) {
        Table table = (Table) widget;
        TableItem item = new TableItem(table, SWT.NONE);

        if (annotation.label().length > 0) {
            Object[] labelValues = getLabelValues(value, annotation);
            for (int i = 0; i < labelValues.length; i++) {
                String text = (String) convertValue(labelValues[i],
                        String.class);
                if (text == null) {
                    text = "";
                }
                item.setText(i, text);
            }
        } else {
            for (int i = 0; i < table.getColumns().length; i++) {
                TableColumnComponent component = (TableColumnComponent) table
                        .getColumns()[i].getData();
                String columnId = component.getId();
                Object columnValue = PropertyUtil.getProperty(value, columnId);
                String text = (String) convertValue(columnValue, String.class);
                if (text == null) {
                    text = "";
                }
                item.setText(i, text);
            }
        }
    }

}
