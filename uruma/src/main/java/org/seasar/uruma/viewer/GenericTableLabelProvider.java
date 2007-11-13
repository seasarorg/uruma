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
package org.seasar.uruma.viewer;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.annotation.BindingLabel;

/**
 * 汎用的な {@link ITableLabelProvider} の実装クラスです。<br />
 * <p>
 * {@link GenericTableLabelProvider#setTargetClass(Class) setTargetClass()}
 * メソッドで設定されたクラスの中から {@link BindingLabel} アノテーションが付加されたフィールドを、テーブルのカラムとして表示します。
 * </p>
 * 
 * @author y-komori
 */
public class GenericTableLabelProvider implements ITableLabelProvider,
        TargetClassHoldingProvider {
    protected Map<String, Integer> columnNoMap = new HashMap<String, Integer>();

    protected Map<Integer, PropertyDesc> columnMap = new HashMap<Integer, PropertyDesc>();

    /*
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object,
     *      int)
     */
    public Image getColumnImage(final Object element, final int columnIndex) {
        return null;
    }

    /*
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    public String getColumnText(final Object element, final int columnIndex) {
        PropertyDesc pd = columnMap.get(columnIndex);
        if (pd != null && pd.isReadable()) {
            Object value = pd.getValue(element);

            return (value != null) ? value.toString() : "";
        } else {
            return "";
        }
    }

    public void addListener(final ILabelProviderListener listener) {
        // Do nothing.
    }

    public void dispose() {
        // Do nothing.
    }

    public boolean isLabelProperty(final Object element, final String property) {
        return false;
        // Do nothing.
    }

    public void removeListener(final ILabelProviderListener listener) {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.viewer.TargetClassHoldingProvider#setTargetClass(java.lang.Class)
     */
    public void setTargetClass(final Class<?> clazz) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(clazz);
        for (String columnName : columnNoMap.keySet()) {
            if (desc.hasPropertyDesc(columnName)) {
                PropertyDesc pd = desc.getPropertyDesc(columnName);
                columnMap.put(columnNoMap.get(columnName), pd);
            }
        }
    }

    /**
     * カラム名とカラム番号の対応を設定します。<br />
     * 
     * @param columnNo
     *            カラム番号
     * @param columnName
     *            カラム名
     */
    public void addColumnMap(final int columnNo, final String columnName) {
        columnNoMap.put(columnName, columnNo);
    }
}
