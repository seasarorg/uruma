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
package org.seasar.uruma.binding.value.binder;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.util.AssertionUtil;

/**
 * 汎用的な {@link ValueBinder} です。
 * 
 * @param <WIDGET_TYPE>
 *            対応するウィジットの型
 * @author y-komori
 */
public class GenericValueBinder<WIDGET_TYPE> extends
        AbstractValueBinder<WIDGET_TYPE> {
    private PropertyDesc propertyDesc;

    /**
     * {@link GenericValueBinder} を構築します。<br />
     * 
     * @param targetClass
     *            ターゲットのクラスオブジェクト
     * @param propertyName
     *            ターゲットのプロパティ名称
     */
    public GenericValueBinder(final Class<WIDGET_TYPE> targetClass,
            final String propertyName) {
        super(targetClass);
        AssertionUtil.assertNotEmpty("propertyName", propertyName);

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(targetClass);
        this.propertyDesc = beanDesc.getPropertyDesc(propertyName);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doImportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    public void doImportValue(final WIDGET_TYPE widget, final Object formObj,
            final PropertyDesc propDesc) {
        Object value = propertyDesc.getValue(widget);

        // 空文字列の入力は null として扱う
        if (value instanceof String && ((String) value).length() == 0) {
            value = null;
        }

        logBinding(IMPORT_VALUE, widget, propertyDesc, formObj, propDesc, value);

        propDesc.setValue(formObj, value);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doExportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    public void doExportValue(final WIDGET_TYPE widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {
        Object value = propDesc.getValue(formObj);

        if (value == null) {
            value = "";
        }

        logBinding(EXPORT_VALUE, formObj, propDesc, widget, propertyDesc, value);

        propertyDesc.setValue(widget, value);
    }
}
