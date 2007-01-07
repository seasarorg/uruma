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
package org.seasar.jface.viewer;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.annotation.BindingLabel;

/**
 * 汎用的な {@link org.eclipse.jface.viewers.ITableLabelProvider} の実装クラスです。<br />
 * <p>
 * <code>setTargetClass()</code> メソッドで設定されたクラスの中から
 * {@link org.seasar.jface.annotation.BindingLabel}
 * アノテーションが付加されたフィールドをテーブルのカラムとして表示します。
 * </p>
 * <p>
 * カラムの順番は、{@link org.seasar.jface.annotation.BindingLabel} アノテーションの
 * <code>column</code> 属性によって指定します。
 * </p>
 * 
 * @author y-komori
 */
public class GenericTableLabelProvider implements ITableLabelProvider {
    protected Map<Integer, Field> columnMap = new HashMap<Integer, Field>();

    public Image getColumnImage(Object element, int columnIndex) {
        return null;
    }

    public String getColumnText(Object element, int columnIndex) {
        Field field = columnMap.get(columnIndex);
        if (field != null) {
            Object value = FieldUtil.get(field, element);

            return (value != null) ? value.toString() : "";
        } else {
            return "";
        }
    }

    public void addListener(ILabelProviderListener listener) {
    }

    public void dispose() {
    }

    public boolean isLabelProperty(Object element, String property) {
        return false;
    }

    public void removeListener(ILabelProviderListener listener) {
    }

    /**
     * レコードに対応するクラスを設定します。<br />
     * 
     * @param clazz
     *            レコードに対応するクラス
     */
    public void setTargetClass(final Class clazz) {
        for (Class<?> target = clazz; target != Object.class; target = target
                .getSuperclass()) {
            BeanDesc desc = BeanDescFactory.getBeanDesc(clazz);
            int fieldSize = desc.getFieldSize();
            for (int i = 0; i < fieldSize; i++) {
                Field field = desc.getField(i);
                BindingLabel annotation = field
                        .getAnnotation(BindingLabel.class);
                if (annotation != null) {
                    int column = annotation.column();
                    columnMap.put(column, field);
                }
            }
        }
    }
}
