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

import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.seasar.uruma.annotation.RenderingPolicy.ConversionType;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UICompositeComponent;
import org.seasar.uruma.component.impl.TableComponent;
import org.seasar.uruma.component.impl.TableItemComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.renderer.RendererSupportUtil;
import org.seasar.uruma.viewer.GenericTableLabelProvider;
import org.seasar.uruma.viewer.GenericTableViewerSorter;

/**
 * {@link TableViewer} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TableViewerRenderer extends
        AbstractViewerRenderer<TableComponent, TableViewer, Table> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#render(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    public WidgetHandle render(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {

        return super.render(uiComponent, parent, context);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#canCreateViewer(org.seasar.uruma.component.UICompositeComponent)
     */
    @Override
    protected boolean canCreateViewer(final UICompositeComponent component) {
        // TableItemComponent が存在する場合、Viewer を生成しない
        List<UIComponent> children = component.getChildren();
        for (UIComponent child : children) {
            if (child instanceof TableItemComponent) {
                return false;
            }
        }
        return true;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#doRenderAfter(org.eclipse.jface.viewers.Viewer,
     *      org.seasar.uruma.component.impl.CompositeComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRenderAfter(final TableViewer viewer,
            final TableComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        // TODO ISelection を用いた設定に変更する
        String selection = uiComponent.getSelection();
        if (selection != null) {
            Table table = viewer.getTable();
            table.setSelection((int[]) RendererSupportUtil.convertValue(
                    uiComponent, selection, ConversionType.INT_ARRAY));
        }
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<Table> getWidgetType() {
        return Table.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#getDefaultStyle()
     */
    @Override
    protected int getDefaultStyle() {
        return SWT.BORDER;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getViewerType()
     */
    @Override
    protected Class<TableViewer> getViewerType() {
        return TableViewer.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getDefaultLabelProvider()
     */
    @Override
    protected IBaseLabelProvider getDefaultLabelProvider() {
        return new GenericTableLabelProvider();
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getLabelProviderClass()
     */
    @Override
    protected Class<? extends IBaseLabelProvider> getLabelProviderClass() {
        return ITableLabelProvider.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getDefaultComparator(org.eclipse.jface.viewers.Viewer)
     */
    @Override
    protected ViewerSorter getDefaultComparator(final TableViewer viewer) {
        return new GenericTableViewerSorter(viewer);
    }
}
