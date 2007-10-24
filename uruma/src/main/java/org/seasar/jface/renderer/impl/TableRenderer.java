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
package org.seasar.jface.renderer.impl;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.component.impl.TableComponent;
import org.seasar.jface.exception.RenderException;
import org.seasar.jface.renderer.RendererSupportUtil;
import org.seasar.jface.util.S2ContainerUtil;
import org.seasar.jface.viewer.GenericTableLabelProvider;
import org.seasar.jface.viewer.GenericTableViewerSorter;
import org.seasar.jface.viewer.TableViewerAdapter;

/**
 * <code>Table</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TableRenderer extends
        AbstractCompositeRenderer<TableComponent, Table> {
    private static final String LABEL_PROVIDER = "LabelProvider";

    private static final String SORTER = "Sorter";

    @Override
    protected void doRenderComposite(TableComponent tableComponent, Table table) {
        TableViewer viewer = new TableViewer(table);
        TableViewerAdapter viewerAdapter = new TableViewerAdapter(viewer);
        getContext().putViewerAdapter(table, viewerAdapter);

        String id = tableComponent.getId();
        setupLabelProvider(viewer, id);
    }

    private void setupLabelProvider(TableViewer viewer, String id) {
        IBaseLabelProvider provider = null;
        if (id != null) {
            Object defined = S2ContainerUtil.getComponentNoException(id
                    + LABEL_PROVIDER);
            if (defined != null) {
                if (defined instanceof ITableLabelProvider
                        || defined instanceof ILabelProvider) {
                    provider = (IBaseLabelProvider) defined;
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            provider, ITableLabelProvider.class.getName());
                }
            }
        }

        if (provider == null) {
            // ユーザー定義のLabelProviderが存在しない場合、
            // デフォルトのLabelProviderを設定する
            provider = new GenericTableLabelProvider();
        }
        viewer.setLabelProvider(provider);
    }

    private void setupSorter(Table table, TableViewer viewer, String id) {
        Object sorter = S2ContainerUtil.getComponentNoException(id + SORTER);
        if (sorter != null) {
            if (sorter instanceof ViewerSorter) {
                viewer.setSorter(ViewerSorter.class.cast(sorter));
            } else {
                throw new RenderException(RenderException.TYPE_ERROR, sorter,
                        ViewerSorter.class.getName());
            }
        } else {
            // ユーザ定義のViewerSorterが存在しない場合、
            // デフォルトのViewerSorterを設定する
            viewer.setSorter(new GenericTableViewerSorter(viewer));
        }
    }

    @Override
    protected void doRenderAfter(Table widget, TableComponent uiComponent,
            Widget parent, WindowContext context) {
        String selection = uiComponent.getSelection();
        if (selection != null) {
            widget.setSelection((int[]) RendererSupportUtil.convertValue(
                    uiComponent, selection, ConversionType.INT_ARRAY));
        }

        String id = uiComponent.getId();
        setupSorter(widget, (TableViewer) context.getViewerAdapter(widget)
                .getViewer(), id);
    }

    @Override
    protected Class<Table> getWidgetType() {
        return Table.class;
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.BORDER;
    }
}