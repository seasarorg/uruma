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
package org.seasar.uruma.binding.value.binder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.renderer.impl.AbstractGUITest;

/**
 * {@link TableViewerValueBinder} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class TableViewerValueBinderGUITest extends AbstractGUITest {
    @ExportValue(id = "table")
    public List<Contents> contents;

    public TableViewer table;

    @ImportSelection(id = "table")
    public Contents selected;

    @InitializeMethod
    public void initialize() {
        contents = new ArrayList<Contents>();
        Contents row1 = new Contents("aaa", "bbb", "ccc", "ddd");
        Contents row2 = new Contents("111", "222", "333", "444");
        contents.add(row1);
        contents.add(row2);
    }

    @EventListener(id = "select")
    public void onSelect() {
        System.out.println("選択された : " + table);

        Table widget = table.getTable();
        TableColumn[] columns = widget.getColumns();
        for (int i = 0; i < columns.length; i++) {
            System.out.println(columns[i].getText());
            System.out.println(columns[i].getWidth());
            columns[i].pack();
        }

        TableItem[] items = widget.getItems();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i].getBounds());
        }
        table.refresh(true);
    }

    @EventListener(id = "table")
    public void onTableSelect() {
        System.out.println(selected);

    }

    /**
     * @author y-komori
     * 
     */
    private static class Contents {
        public String column1;

        public String column2;

        public String column3;

        public String column4;

        /**
         * {@link Contents} を構築します。<br />
         * 
         * @param column1
         * @param column2
         * @param column3
         * @param column4
         */
        public Contents(final String column1, final String column2, final String column3,
                final String column4) {
            this.column1 = column1;
            this.column2 = column2;
            this.column3 = column3;
            this.column4 = column4;
        }
    }
}
