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

import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.BindingValue;
import org.seasar.jface.binding.impl.ExportValueCommand;
import org.seasar.jface.binding.impl.ImportValueCommand;
import org.seasar.jface.exception.ValueBindingException;

/**
 * ValueBinding を実現するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ValueBinder {
    /**
     * {@link WindowContext} の保持するアクションコンポーネントに対して、ValueBinding を行います。<br />
     * 
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    public static void importValue(WindowContext context) {
        dealFields(context, new ImportValueCommand());
    }

    /**
     * {@link WindowContext} の保持するアクションコンポーネントから、ValueBinding を行います。<br />
     * 
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    public static void exportValue(WindowContext context) {
        dealFields(context, new ExportValueCommand());
    }

    private static void dealFields(WindowContext context,
            WidgetValueBinderCommand command) {
        Object action = context.getActionComponent();
        if (action == null) {
            return;
        }

        ActionDesc desc = ActionDescFactory.getActionDesc(action.getClass());
        List<Field> targetFields = command.getTargetFields(desc);
        for (Field field : targetFields) {
            BindingValue annotation = field.getAnnotation(BindingValue.class);
            String id = annotation.id();
            Widget widget = getWidget(context, id, field);
            if (widget != null) {
                WidgetValueBinder binder = WidgetValueBinderFactory
                        .getBinder(widget.getClass());
                if (binder != null) {
                    command.setWidgetValueBinder(binder);
                    command.doBind(widget, action, field, context);
                } else {
                    throw new ValueBindingException(
                            ValueBindingException.WIDGET_NOT_SUPPORTED, widget
                                    .getClass().getName(), action.getClass(),
                            field);
                }
            } else {
                throw new ValueBindingException(
                        ValueBindingException.WIDGET_NOT_FOUND, id, context
                                .getActionComponent().getClass(), field);
            }
        }

    }

    private static Widget getWidget(WindowContext context, String id,
            Field field) {
        if (StringUtil.isEmpty(id)) {
            id = field.getName();
        }
        return context.getComponent(id);
    }
}
