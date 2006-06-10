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

import static org.seasar.jface.renderer.info.TableInfo.HEADER_PROP;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.TableInfo;

/**
 * <code>Table</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author dkameya
 */
public class TableRenderer extends AbstractControlRenderer<Table> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        int style = super.getStyle(controlComponent);
        style = (style == SWT.NONE) ? SWT.BORDER : style;
        return style;
    }

    @Override
    protected void doRender(final Table table,
            final ControlComponent controlComponent) {
        addHeader(table, controlComponent);
        addLine(table, controlComponent);
        for (TableColumn column : table.getColumns()) {
            column.pack();
        }
    }

    @Override
    protected Class<Table> getControlType() {
        return Table.class;
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return TableInfo.class;
    }

    public String getRendererName() {
        return "table";
    }

    protected void addHeader(final Table table,
            final ControlComponent controlComponent) {
        table.setHeaderVisible(true);
        String prop = controlComponent.getPropertyValue(HEADER_PROP);
        if (prop != null) {
            StringTokenizer st = new StringTokenizer(prop, ",");
            TableColumn header;
            while (st.hasMoreTokens()) {
                String s;
                System.out.println(s = st.nextToken().trim());
                header = new TableColumn(table, SWT.NONE);
                header.setText(s);
            }
        }
    }

    protected void addLine(final Table table,
            final ControlComponent controlComponent) {
        List<TableItem> itemList = new ArrayList<TableItem>();
        for (Item item : controlComponent.getItemList()) {
            StringTokenizer st = new StringTokenizer(item.getValue(), ",");
            String[] items = new String[st.countTokens()];
            int i = 0;
            TableItem child = new TableItem(table, SWT.NONE);
            while (st.hasMoreTokens()) {
                items[i++] = st.nextToken().trim();
            }
            child.setText(items);
            itemList.add(child);
        }
        table.setSelection(itemList.toArray(new TableItem[0]));
    }
}
