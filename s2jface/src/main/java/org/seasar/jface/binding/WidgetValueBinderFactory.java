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
package org.seasar.jface.binding;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.binding.impl.ComboValueBinder;
import org.seasar.jface.binding.impl.TableValueBinder;
import org.seasar.jface.binding.impl.WidgetPropertyValueBinder;

/**
 * {@link WidgetValueBinder} を取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class WidgetValueBinderFactory {
    private static final Map<Class<? extends Widget>, WidgetValueBinder> binderMap = new HashMap<Class<? extends Widget>, WidgetValueBinder>();

    static {
        addBinder(new WidgetPropertyValueBinder(Label.class, "text"));
        addBinder(new WidgetPropertyValueBinder(Text.class, "text"));
        addBinder(new ComboValueBinder());
        addBinder(new TableValueBinder());
    }

    public static WidgetValueBinder getBinder(
            Class<? extends Widget> widgetClass) {
        return binderMap.get(widgetClass);
    }

    public static void addBinder(WidgetValueBinder binder) {
        binderMap.put(binder.getWidgetType(), binder);
    }
}
