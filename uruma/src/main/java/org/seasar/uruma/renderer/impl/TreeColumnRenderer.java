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
package org.seasar.uruma.renderer.impl;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.TreeColumn;
import org.seasar.uruma.component.jface.TreeColumnComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.viewer.GenericTableLabelProvider;

/**
 * {@link TreeColumn} のレンダリングを行うクラスです。<br />
 * 
 * @author y-komori
 * @version $Revision$ $Date$
 */
public class TreeColumnRenderer extends
        AbstractWidgetRenderer<TreeColumnComponent, TreeColumn> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRender(org.seasar.uruma.component.UIComponent,
     *      org.eclipse.swt.widgets.Widget)
     */
    @Override
    protected void doRender(final TreeColumnComponent uiComponent,
            final TreeColumn widget) {
        // widget.pack();

        setupColumnMap(uiComponent, widget);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<TreeColumn> getWidgetType() {
        return TreeColumn.class;
    }

    private void setupColumnMap(final TreeColumnComponent uiComponent,
            final TreeColumn widget) {
        int columnNo = uiComponent.columnNo;
        String id = uiComponent.getId();
        String parentId = uiComponent.getParent().getId();

        PartContext context = getContext();
        WidgetHandle parentHandle = context.getWidgetHandle(parentId);
        if (parentHandle != null && parentHandle.instanceOf(TreeViewer.class)) {
            TreeViewer viewer = parentHandle.<TreeViewer> getCastWidget();
            IBaseLabelProvider baseLabelProvider = viewer.getLabelProvider();

            if (baseLabelProvider instanceof GenericTableLabelProvider) {
                GenericTableLabelProvider provider = (GenericTableLabelProvider) baseLabelProvider;
                provider.addColumnMap(columnNo, id);
            }
        }
    }
}
