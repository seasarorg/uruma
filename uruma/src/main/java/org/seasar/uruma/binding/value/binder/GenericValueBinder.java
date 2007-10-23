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
package org.seasar.uruma.binding.value.binder;

import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.util.AssertionUtil;

/**
 * 汎用的な {@link ValueBinder} です。
 * 
 * @author y-komori
 */
public class GenericValueBinder extends AbstractValueBinder {
    private PropertyDesc propertyDesc;

    /**
     * {@link GenericValueBinder} を構築します。<br />
     * 
     * @param targetClass
     *            ターゲットのクラスオブジェクト
     * @param propertyName
     *            ターゲットのプロパティ名称
     */
    public GenericValueBinder(final Class<? extends Widget> targetClass,
            final String propertyName) {
        super(targetClass);
        AssertionUtil.assertNotEmpty("propertyName", propertyName);

        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(targetClass);
        this.propertyDesc = beanDesc.getPropertyDesc(propertyName);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#getWidgetValue(java.lang.Object)
     */
    @Override
    protected Object getWidgetValue(final Object widget) {
        return propertyDesc.getValue(widget);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#setWidgetValue(java.lang.Object,
     *      java.lang.Object)
     */
    @Override
    protected void setWidgetValue(final Object widget, final Object value) {
        propertyDesc.setValue(widget, value);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#exportSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void exportSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#importSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void importSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        // Do nothing.
    }
}
