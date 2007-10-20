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
package org.seasar.uruma.binding.value.impl;

import java.lang.reflect.Field;

import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.impl.PropertyDescImpl;
import org.seasar.framework.util.FieldUtil;
import org.seasar.uruma.binding.value.WidgetValueBinder;
import org.seasar.uruma.context.PartContext;

/**
 * {@link WidgetValueBinder} のための基底クラスです。<br />
 * 
 * @author bskuroneko
 */
public abstract class AbstractWidgetValueBinder implements WidgetValueBinder {

    private Class<? extends Widget> widgetType;

    /**
     * {@link AbstractWidgetValueBinder} を構築します。<br />
     * 
     * @param widgetType
     *            対応するウィジットの型
     */
    public AbstractWidgetValueBinder(final Class<? extends Widget> widgetType) {
        this.widgetType = widgetType;
    }

    /*
     * @see org.seasar.uruma.binding.value.WidgetValueBinder#importValue(org.eclipse.swt.widgets.Widget,
     *      java.lang.Object, java.lang.reflect.Field,
     *      org.seasar.uruma.context.PartContext)
     */
    public void importValue(final Widget src, final Object destObject,
            final Field destField, final PartContext context) {
        Object widgetValue = getWidgetValue(src);
        Object convertedValue = convertValue(widgetValue, destField.getType());
        FieldUtil.set(destField, destObject, convertedValue);
    }

    protected Object convertValue(final Object srcValue, final Class<?> destType) {
        // TODO PropertyDescImplに依存しないようにする？
        PropertyDesc propDesc = new PropertyDescImpl("dummy", destType, null,
                null, null);
        Object result = propDesc.convertIfNeed(srcValue);
        if (result != null && destType.equals(String.class)) {
            result = result.toString();
        }
        return result;
    }

    /*
     * @see org.seasar.uruma.binding.value.WidgetValueBinder#exportValue(java.lang.Object,
     *      java.lang.reflect.Field, org.eclipse.swt.widgets.Widget,
     *      org.seasar.uruma.context.PartContext)
     */
    public void exportValue(final Object srcObject, final Field srcField,
            final Widget dest, final PartContext context) {
        Object fieldValue = FieldUtil.get(srcField, srcObject);
        putWidgetValue(dest, fieldValue);
    }

    /*
     * @see org.seasar.uruma.binding.value.WidgetValueBinder#getWidgetType()
     */
    public Class<? extends Widget> getWidgetType() {
        return widgetType;
    }

    protected abstract Object getWidgetValue(Widget widget);

    protected abstract void putWidgetValue(Widget widget, Object value);

}
