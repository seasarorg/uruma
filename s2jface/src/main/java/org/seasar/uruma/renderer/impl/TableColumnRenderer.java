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
package org.seasar.uruma.renderer.impl;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.seasar.jface.viewer.GenericTableLabelProvider;
import org.seasar.uruma.component.impl.TableColumnComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link TableColumn} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TableColumnRenderer extends
        AbstractWidgetRenderer<TableColumnComponent, TableColumn> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRender(org.seasar.uruma.component.UIComponent,
     *      org.eclipse.swt.widgets.Widget)
     */
    @Override
    protected void doRender(final TableColumnComponent uiComponent,
            final TableColumn widget) {
        setupColumnMap(uiComponent, widget);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<TableColumn> getWidgetType() {
        return TableColumn.class;
    }

    private void setupColumnMap(final TableColumnComponent uiComponent,
            final TableColumn widget) {
        int columnNo = uiComponent.getColumnNo();
        String id = uiComponent.getId();
        String parentId = uiComponent.getParent().getId();

        PartContext context = getContext();
        WidgetHandle parentHandle = context.getWidgetHandle(parentId);
        if (parentHandle != null && parentHandle.instanceOf(TableViewer.class)) {
            TableViewer viewer = parentHandle.<TableViewer> getCastWidget();
            IBaseLabelProvider baseLabelProvider = viewer.getLabelProvider();

            if (baseLabelProvider instanceof GenericTableLabelProvider) {
                GenericTableLabelProvider provider = (GenericTableLabelProvider) baseLabelProvider;
                provider.addColumnMap(columnNo, id);
            }
        }
    }
}
