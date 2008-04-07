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

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.jface.TableColumnComponent;
import org.seasar.uruma.component.jface.TableComponent;

/**
 * {@link TableViewer} のための {@link ValueBinder} です。<br />
 * 
 * @author y-komori
 */
public class TableViewerValueBinder extends AbstractValueBinder<TableViewer> {

    /**
     * {@link TableViewerValueBinder} を構築します。<br />
     * 
     */
    public TableViewerValueBinder() {
        super(TableViewer.class);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doExportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    protected void doExportValue(final TableViewer widget,
            final Object formObj, final PropertyDesc propDesc,
            final UIComponent uiComp) {
        super.doExportValue(widget, formObj, propDesc, uiComp);

        Table table = widget.getTable();
        table.setRedraw(false);
        TableColumn[] columns = table.getColumns();
        TableComponent tableComp = (TableComponent) uiComp;
        for (int i = 0; i < columns.length; i++) {
            if (((TableColumnComponent) tableComp.getChildren().get(i)).width == null) {
                columns[i].pack();
            }
        }
        table.setRedraw(true);
    }
}
