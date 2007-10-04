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

import java.lang.reflect.Field;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.graphics.Image;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;
import org.seasar.uruma.annotation.BindingLabel;

/**
 * 汎用的な {@link ILabelProvider} の実装クラスです。<br />
 * <p>
 * {@link GenericLabelProvider#setTargetClass(Class) setTargetClass()}
 * メソッドで設定されたクラスの中から {@link BindingLabel} アノテーションが付加されたフィールドをテーブルのカラムとして表示します。
 * </p>
 * <p>
 * {@link BindingLabel} アノテーションが付加されたフィールドが存在しない場合、<code>toString()</code>
 * メソッドの戻り値をラベルに設定します。
 * </p>
 * <p>
 * {@link BindingLabel} アノテーションが付加されたフィールドが複数存在する場合、一番最初のフィールドが有効となります。
 * </p>
 * 
 * @author y-komori
 */
public class GenericLabelProvider implements ILabelProvider {
    protected Field labelField;

    /*
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(final Object element) {
        return null;
    }

    /*
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public String getText(final Object element) {
        if (labelField != null) {
            Object value = FieldUtil.get(labelField, element);
            return (value != null) ? value.toString() : "";
        } else {
            return element.toString();
        }
    }

    /*
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(final ILabelProviderListener listener) {
        // Do nothing.
    }

    /*
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
        // Do nothing.
    }

    /*
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object,
     *      java.lang.String)
     */
    public boolean isLabelProperty(final Object element, final String property) {
        // Do nothing.
        return false;
    }

    /*
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(final ILabelProviderListener listener) {
        // Do nothing.
    }

    /**
     * レコードに対応するクラスを設定します。<br />
     * 
     * @param clazz
     *            レコードに対応するクラス
     */
    public void setTargetClass(final Class<?> clazz) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(clazz);
        int fieldSize = desc.getFieldSize();
        for (int i = 0; i < fieldSize; i++) {
            Field field = desc.getField(i);
            BindingLabel annotation = field.getAnnotation(BindingLabel.class);
            if (annotation != null) {
                labelField = field;
                break;
            }
        }
    }
}
