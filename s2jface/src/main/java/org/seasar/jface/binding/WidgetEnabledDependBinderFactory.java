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
package org.seasar.jface.binding;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.binding.impl.TableEnabledDependBinder;
import org.seasar.jface.exception.EnabledDependException;
import org.seasar.jface.util.ClassUtil;

/**
 * @author bskuroneko
 * 
 */
public class WidgetEnabledDependBinderFactory {

    private static final Map<Class<? extends Widget>, Class<? extends WidgetEnabledDependBinder>> binderMap = new HashMap<Class<? extends Widget>, Class<? extends WidgetEnabledDependBinder>>();

    static {
        addBinder(Table.class, TableEnabledDependBinder.class);
    }

    public static WidgetEnabledDependBinder getBinder(Widget widget,
            Widget target, EnabledDependType type) {
        Class<? extends WidgetEnabledDependBinder> binderType = binderMap
                .get(target.getClass());
        
        if (binderType == null) {
            throw new EnabledDependException(
                    EnabledDependException.DEPEND_WIDGET_NOT_SUPPORTED,
                    target.getClass());
        }
        
        return ClassUtil.newInstance(binderType, new Object[] { widget, target,
                type });
    }

    public static void addBinder(Class<? extends Widget> widgetType,
            Class<? extends WidgetEnabledDependBinder> binderType) {
        binderMap.put(widgetType, binderType);
    }
}
