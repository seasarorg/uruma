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

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Item;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;

/**
 * {@link ColumnViewer} 用の汎用ソートクラスです。<br />
 * 本クラスのインスタンスを {@link ColumnViewer#setComparator(ViewerComparator)}
 * メソッドで設定して使用してください。<br />
 * 本コンパレータを設定する時点で {@link ColumnViewer} の内包するウィジット({@link Table} や {@link Tree}
 * など)のカラムが生成されている必要があります。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public abstract class AbstractGenericColumnViewerComparator<V extends ColumnViewer, W extends Widget, C extends Item>
        extends ViewerComparator {
    private W widget;

    private Map<C, Integer> columnMap = new HashMap<C, Integer>();

    private C sortKey;

    private SortingState sortingState = SortingState.NONE;

    /**
     * {@link AbstractGenericColumnViewerComparator} を構築します。<br />
     */
    public AbstractGenericColumnViewerComparator() {
        super(new StringAndNumberComparator());
    }

    protected void setupColumnMap(final ColumnViewer viewer) {
        this.widget = getWidgetFromViewer(castViewer(viewer));
        C[] columns = getColumnsFromWidget(widget);
        if (columns.length == 0) {
            return;
        }

        for (int i = 0; i < columns.length; i++) {
            columnMap.put(columns[i], i);

            addSelectionListener(columns[i], new SelectionListener() {
                public void widgetDefaultSelected(final SelectionEvent e) {
                }

                public void widgetSelected(final SelectionEvent e) {
                    setSortKey(getColumn(e));
                    viewer.setInput(viewer.getInput());
                    refresh(castViewer(viewer));
                }
            });
        }

        sortKey = columns[0];
    }

    protected abstract W getWidgetFromViewer(V viewer);

    protected abstract C[] getColumnsFromWidget(W widget);

    protected abstract void addSelectionListener(C column, SelectionListener listener);

    protected abstract void refresh(V viewer);

    protected abstract void setSortColumn(W widget, C column);

    protected abstract void setSortDirection(W widget, int direction);

    protected abstract C getColumn(SelectionEvent e);

    protected abstract V castViewer(ColumnViewer viewer);

    /*
     * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final Viewer viewer, final Object e1, final Object e2) {
        ColumnViewer columnViewer = (ColumnViewer) viewer;

        if (this.widget == null) {
            setupColumnMap(columnViewer);
        }

        int cat1 = category(e1);
        int cat2 = category(e2);

        if (cat1 != cat2) {
            return cat1 - cat2;
        }

        Integer sortColumn = columnMap.get(sortKey);
        if (sortColumn != null) {
            int result = doCompare(columnViewer, e1, e2, sortColumn.intValue());

            if (sortingState == SortingState.ASCENDING) {
                return result;
            } else if (sortingState == SortingState.DESCENDING) {
                return result * (-1);
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }

    /**
     * モデルオブジェクトの比較を行います。<br />
     * 比較方法をカスタマイズする場合、サブクラスで本メソッドをオーバーライドしてください。<br />
     * 
     * @param viewer
     *        {@link ColumnViewer} オブジェクト
     * @param e1
     *        比較対象1
     * @param e2
     *        比較対象2
     * @param sortColumn
     *        ソート対象のカラム番号
     * @return 比較結果
     */
    protected int doCompare(final ColumnViewer viewer, final Object e1, final Object e2, final int sortColumn) {
        IBaseLabelProvider baseLabelProvider = viewer.getLabelProvider();

        String value1 = "";
        String value2 = "";
        if (baseLabelProvider instanceof ITableLabelProvider) {
            ITableLabelProvider prov = (ITableLabelProvider) baseLabelProvider;
            value1 = prov.getColumnText(e1, sortColumn);
            value2 = prov.getColumnText(e2, sortColumn);
        } else {
            value1 = e1.toString();
            value2 = e2.toString();
        }

        if (value1 == null) {
            value1 = "";
        }
        if (value2 == null) {
            value2 = "";
        }

        return getComparator().compare(value1, value2);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected final Comparator<String> getComparator() {
        return super.getComparator();
    }

    /**
     * ソートキーとなるカラムを設定します。<br />
     * ソート順は呼び出すたびに反転します。
     * 
     * @param column
     *        ソートキーとなるカラムオブジェクト
     */
    public void setSortKey(final C column) {
        if ((column != null) && !column.isDisposed()) {
            sortKey = column;

            if (sortingState == SortingState.DESCENDING) {
                sortingState = SortingState.ASCENDING;
                setSortColumn(widget, sortKey);
                setSortDirection(widget, SWT.UP);
            } else if (sortingState == SortingState.ASCENDING) {
                sortingState = SortingState.NONE;
                setSortColumn(widget, null);
                setSortDirection(widget, SWT.NONE);
            } else if (sortingState == SortingState.NONE) {
                sortingState = SortingState.DESCENDING;
                setSortColumn(widget, sortKey);
                setSortDirection(widget, SWT.DOWN);
            }
        }
    }
}
