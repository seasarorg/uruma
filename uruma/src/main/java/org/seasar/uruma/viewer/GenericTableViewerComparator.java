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
package org.seasar.uruma.viewer;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * {@link TableViewer} 用の汎用ソートクラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class GenericTableViewerComparator extends
        AbstractGenericColumnViewerComparator<TableViewer, Table, TableColumn> {

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#addSelectionListener(org.eclipse.swt.widgets.Item, org.eclipse.swt.events.SelectionListener)
     */
    @Override
    protected void addSelectionListener(final TableColumn column, final SelectionListener listener) {
        column.addSelectionListener(listener);
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#castViewer(org.eclipse.jface.viewers.ColumnViewer)
     */
    @Override
    protected TableViewer castViewer(final ColumnViewer viewer) {
        return (TableViewer) viewer;
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#getColumn(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    protected TableColumn getColumn(final SelectionEvent e) {
        return (TableColumn) e.widget;
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#getColumnsFromWidget(org.eclipse.swt.widgets.Widget)
     */
    @Override
    protected TableColumn[] getColumnsFromWidget(final Table widget) {
        return widget.getColumns();
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#getWidgetFromViewer(org.eclipse.jface.viewers.ColumnViewer)
     */
    @Override
    protected Table getWidgetFromViewer(final TableViewer viewer) {
        return viewer.getTable();
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#refresh(org.eclipse.jface.viewers.ColumnViewer)
     */
    @Override
    protected void refresh(final TableViewer viewer) {
        viewer.refresh(true, true);
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#setSortColumn(org.eclipse.swt.widgets.Widget, org.eclipse.swt.widgets.Item)
     */
    @Override
    protected void setSortColumn(final Table widget, final TableColumn column) {
        widget.setSortColumn(column);
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#setSortDirection(org.eclipse.swt.widgets.Widget, int)
     */
    @Override
    protected void setSortDirection(final Table widget, final int direction) {
        widget.setSortDirection(direction);
    }
}
