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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * {@link TableViewer} 用の汎用ソートクラスです。<br />
 * 
 * @author y-komori
 */
public class GenericTableViewerComparator extends ViewerComparator {
    private Table table;

    private Map<TableColumn, Integer> columnMap = new HashMap<TableColumn, Integer>();

    private TableColumn sortKey;

    private SortingState sortingState = SortingState.NONE;

    /**
     * {@link GenericTableViewerComparator} を構築します。<br />
     */
    public GenericTableViewerComparator() {
        super(new StringAndNumberComparator());
    }

    protected void setupColumnMap(final TableViewer viewer) {
        this.table = viewer.getTable();
        TableColumn[] columns = table.getColumns();
        if (columns.length == 0) {
            return;
        }

        for (int i = 0; i < columns.length; i++) {
            columnMap.put(columns[i], i);

            columns[i].addSelectionListener(new SelectionListener() {
                public void widgetDefaultSelected(final SelectionEvent e) {
                }

                public void widgetSelected(final SelectionEvent e) {
                    setSortKey((TableColumn) e.widget);
                    viewer.setInput(viewer.getInput());
                }
            });
        }

        sortKey = columns[0];
    }

    /*
     * @see org.eclipse.jface.viewers.ViewerComparator#compare(org.eclipse.jface.viewers.Viewer,
     *      java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(final Viewer viewer, final Object e1, final Object e2) {
        TableViewer tableViewer = (TableViewer) viewer;

        if (this.table == null) {
            setupColumnMap(tableViewer);
        }

        int cat1 = category(e1);
        int cat2 = category(e2);

        if (cat1 != cat2) {
            return cat1 - cat2;
        }

        Integer sortColumn = columnMap.get(sortKey);
        if (sortColumn != null) {
            int result = doCompare(tableViewer, e1, e2, sortColumn.intValue());

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
     *            {@link TableViewer} オブジェクト
     * @param e1
     *            比較対象1
     * @param e2
     *            比較対象2
     * @param sortColumn
     *            ソート対象のカラム番号
     * @return 比較結果
     */
    protected int doCompare(final TableViewer viewer, final Object e1,
            final Object e2, final int sortColumn) {
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

    /**
     * ソートキーとなるカラムを設定します。<br />
     * ソート順は呼び出すたびに反転します。
     * 
     * @param tableColumn
     *            ソートキーとなる {@link TableColumn} オブジェクト
     */
    public void setSortKey(final TableColumn tableColumn) {
        if ((tableColumn != null) && !tableColumn.isDisposed()) {
            sortKey = tableColumn;

            if (sortingState == SortingState.DESCENDING) {
                sortingState = SortingState.ASCENDING;
                table.setSortColumn(sortKey);
                table.setSortDirection(SWT.UP);
            } else if (sortingState == SortingState.ASCENDING) {
                sortingState = SortingState.NONE;
                table.setSortColumn(null);
                table.setSortDirection(SWT.NONE);
            } else if (sortingState == SortingState.NONE) {
                sortingState = SortingState.DESCENDING;
                table.setSortColumn(sortKey);
                table.setSortDirection(SWT.DOWN);
            }
        }
    }
}
