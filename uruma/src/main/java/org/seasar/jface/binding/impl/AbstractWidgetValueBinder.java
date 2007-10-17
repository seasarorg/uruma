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
package org.seasar.jface.binding.impl;

import java.lang.reflect.Field;

import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.impl.PropertyDescImpl;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.WidgetValueBinder;

/**
 * {@link org.seasar.jface.binding.ValueBinder} のための基底クラスです。<br />
 * 
 * @author bskuroneko
 */
public abstract class AbstractWidgetValueBinder implements WidgetValueBinder {

    private Class<? extends Widget> widgetType;

    public AbstractWidgetValueBinder(Class<? extends Widget> widgetType) {
        this.widgetType = widgetType;
    }

    public void importValue(Widget src, Object destObject, Field destField,
            WindowContext context) {
        Object widgetValue = getWidgetValue(src);
        Object convertedValue = convertValue(widgetValue, destField.getType());
        FieldUtil.set(destField, destObject, convertedValue);
    }

    protected Object convertValue(Object srcValue, Class<?> destType) {
        // TODO PropertyDescImplに依存しないようにする？
        PropertyDesc propDesc = new PropertyDescImpl("dummy", destType, null,
                null, null);
        Object result = propDesc.convertIfNeed(srcValue);
        if (result != null && destType.equals(String.class)) {
            result = result.toString();
        }
        return result;
    }

    public void exportValue(Object srcObject, Field srcField, Widget dest,
            WindowContext context) {
        Object fieldValue = FieldUtil.get(srcField, srcObject);
        putWidgetValue(dest, fieldValue);
    }

    public Class<? extends Widget> getWidgetType() {
        return widgetType;
    }

    protected abstract Object getWidgetValue(Widget widget);

    protected abstract void putWidgetValue(Widget widget, Object value);

}
