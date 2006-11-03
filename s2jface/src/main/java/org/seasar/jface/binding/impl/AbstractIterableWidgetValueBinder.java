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
package org.seasar.jface.binding.impl;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.annotation.BindingValue;

/**
 * 複数項目を保持するウィジットに対する {@link org.seasar.jface.binding.ValueBinder} の基底クラスです。
 * 
 * @author bskuroneko
 */
public abstract class AbstractIterableWidgetValueBinder extends
        AbstractWidgetValueBinder {

    public AbstractIterableWidgetValueBinder(Class<? extends Widget> widgetType) {
        super(widgetType);
    }

    @Override
    protected void putWidgetValue(Widget widget, Object value,
            BindingValue annotation) {
        clearWidgetValue(widget);

        if (value == null) {
            return;
        }

        Class valueType = value.getClass();
        if (valueType.isArray()) {
            Object[] array = (Object[]) value;
            for (Object object : array) {
                addWidgetValue(widget, object, annotation);
            }
        } else if (Iterable.class.isAssignableFrom(valueType)) {
            Iterable iterable = (Iterable) value;
            for (Object object : iterable) {
                addWidgetValue(widget, object, annotation);
            }
        } else {
            addWidgetValue(widget, value, annotation);
        }
    }

    protected abstract void clearWidgetValue(Widget widget);

    protected abstract void addWidgetValue(Widget widget, Object value,
            BindingValue annotation);

}
