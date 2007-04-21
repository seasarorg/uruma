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
package org.seasar.jface.renderer.impl;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.impl.TableColumnComponent;
import org.seasar.jface.viewer.GenericTableLabelProvider;
import org.seasar.jface.viewer.ViewerAdapter;

/**
 * <code>TableColumn</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TableColumnRenderer extends
        AbstractWidgetRenderer<TableColumnComponent, TableColumn> {

    @Override
    protected void doRender(TableColumnComponent uiComponent, TableColumn widget) {
        setupColumnMap(uiComponent, widget);
    }

    @Override
    protected Class<TableColumn> getWidgetType() {
        return TableColumn.class;
    }

    private void setupColumnMap(TableColumnComponent uiComponent,
            TableColumn widget) {
        int columnNo = uiComponent.getColumnNo();
        String id = uiComponent.getId();

        WindowContext context = getContext();
        ViewerAdapter adapter = context.getViewerAdapter(widget.getParent());
        TableViewer viewer = (TableViewer) adapter.getViewer();
        IBaseLabelProvider baseLabelProvider = viewer.getLabelProvider();

        if (baseLabelProvider instanceof GenericTableLabelProvider) {
            GenericTableLabelProvider provider = (GenericTableLabelProvider) baseLabelProvider;
            provider.addColumnMap(columnNo, id);
        }
    }
}
