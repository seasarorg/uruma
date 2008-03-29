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
package org.seasar.uruma.binding.value;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.seasar.uruma.binding.value.binder.BrowserValueBinder;
import org.seasar.uruma.binding.value.binder.ComboViewerValueBinder;
import org.seasar.uruma.binding.value.binder.DateTimeValueBinder;
import org.seasar.uruma.binding.value.binder.GenericValueBinder;
import org.seasar.uruma.binding.value.binder.StatusLineManagerValueBinder;
import org.seasar.uruma.binding.value.binder.TableValueBinder;
import org.seasar.uruma.binding.value.binder.TableViewerValueBinder;
import org.seasar.uruma.binding.value.binder.TreeViewerValueBinder;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ValueBinder} を取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ValueBinderFactory implements UrumaMessageCodes {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(ValueBinderFactory.class);

    private static final Map<Class<?>, ValueBinder> binderMap = new HashMap<Class<?>, ValueBinder>();

    static {
        addValueBinder(new GenericValueBinder<Label>(Label.class, "text"));
        addValueBinder(new GenericValueBinder<Text>(Text.class, "text"));
        addValueBinder(new GenericValueBinder<Spinner>(Spinner.class,
                "selection"));
        addValueBinder(new ComboViewerValueBinder());
        addValueBinder(new TableViewerValueBinder());
        addValueBinder(new TableValueBinder());
        addValueBinder(new TreeViewerValueBinder());
        addValueBinder(new StatusLineManagerValueBinder());
        addValueBinder(new BrowserValueBinder());
        addValueBinder(new DateTimeValueBinder());
    }

    /**
     * <code>widgetClass</code> に対応する {@link ValueBinder} を取得します。<br />
     * 
     * @param widgetClass
     *            対応する {@link ValueBinder} を取得するための {@link Class} オブジェクト
     * @return <code>widgetClass</code> に対応する {@link ValueBinder}。見つからなかった場合は
     *         <code>null</code>
     */
    public static ValueBinder getValueBinder(final Class<?> widgetClass) {
        ValueBinder binder = binderMap.get(widgetClass);
        if (binder == null) {
            logger.log(VALUE_BINDER_NOT_FOUND, widgetClass.getName());
        }
        return binder;
    }

    /**
     * {@link ValueBinder} を登録します。<br />
     * 
     * @param valueBinder
     *            {@link ValueBinder} オブジェクト
     */
    public static void addValueBinder(final ValueBinder valueBinder) {
        AssertionUtil.assertNotNull("valueBinder", valueBinder);
        Class<?> widgetClass = valueBinder.getWidgetType();
        AssertionUtil.assertNotNull("widgetClass", widgetClass);

        binderMap.put(widgetClass, valueBinder);
    }
}
