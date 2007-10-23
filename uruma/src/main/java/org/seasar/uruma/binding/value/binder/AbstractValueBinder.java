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
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ValueBinder} のための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractValueBinder implements ValueBinder {
    private Class<? extends Widget> widgetType;

    /**
     * {@link AbstractValueBinder} を構築します。<br />
     * 
     * @param widgetType
     *            ウィジットの型
     */
    public AbstractValueBinder(final Class<? extends Widget> widgetType) {
        AssertionUtil.assertNotNull("widgetType", widgetType);
        this.widgetType = widgetType;
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#importValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void importValue(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        Object widgetValue = getWidgetValue(widget);
        propDesc.setValue(formObj, widgetValue);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#exportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void exportValue(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        Object value = propDesc.getValue(formObj);
        setWidgetValue(widget, value);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#getWidgetClass()
     */
    public Class<?> getWidgetClass() {
        return this.widgetType;
    }

    /**
     * ウィジットから値を取得します。<br />
     * 本メソッドはサブクラスで実装してください。<br />
     * 
     * @param widget
     *            ウィジットオブジェクト
     * @return 取得した値
     */
    protected abstract Object getWidgetValue(Object widget);

    /**
     * ウィジットに対して値を設定します。<br />
     * 本メソッドはサブクラスで実装してください。<br />
     * 
     * @param widget
     *            ウィジットオブジェクト
     * @param value
     *            設定する値
     */
    protected abstract void setWidgetValue(Object widget, Object value);
}
