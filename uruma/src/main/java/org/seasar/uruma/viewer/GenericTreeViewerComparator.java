/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

/**
 * {@link TreeViewer} 用の汎用ソートクラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class GenericTreeViewerComparator extends AbstractGenericColumnViewerComparator<TreeViewer, Tree, TreeColumn> {
    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#addSelectionListener(org.eclipse.swt.widgets.Item, org.eclipse.swt.events.SelectionListener)
     */
    @Override
    protected void addSelectionListener(final TreeColumn column, final SelectionListener listener) {
        column.addSelectionListener(listener);
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#getColumn(org.eclipse.swt.events.SelectionEvent)
     */
    @Override
    protected TreeColumn getColumn(final SelectionEvent e) {
        return (TreeColumn) e.widget;
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#getColumnsFromWidget(org.eclipse.swt.widgets.Widget)
     */
    @Override
    protected TreeColumn[] getColumnsFromWidget(final Tree widget) {
        return widget.getColumns();
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#castViewer(org.eclipse.jface.viewers.ColumnViewer)
     */
    @Override
    protected TreeViewer castViewer(final ColumnViewer viewer) {
        return (TreeViewer) viewer;
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#getWidgetFromViewer(org.eclipse.jface.viewers.ColumnViewer)
     */
    @Override
    protected Tree getWidgetFromViewer(final TreeViewer viewer) {
        return viewer.getTree();
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#refresh(org.eclipse.jface.viewers.ColumnViewer)
     */
    @Override
    protected void refresh(final TreeViewer viewer) {
        viewer.refresh(true);
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#setSortColumn(org.eclipse.swt.widgets.Widget, org.eclipse.swt.widgets.Item)
     */
    @Override
    protected void setSortColumn(final Tree widget, final TreeColumn column) {
        widget.setSortColumn(column);
    }

    /*
     * @see org.seasar.uruma.viewer.AbstractGenericColumnViewerComparator#setSortDirection(org.eclipse.swt.widgets.Widget, int)
     */
    @Override
    protected void setSortDirection(final Tree widget, final int direction) {
        widget.setSortDirection(direction);
    }
}
