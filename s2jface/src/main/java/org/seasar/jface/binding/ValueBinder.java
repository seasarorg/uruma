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
import org.seasar.jface.annotation.ImportValue;
import org.seasar.jface.exception.ValueBindingException;

/**
 * @author y-komori
 * 
 */
public class ValueBinder {
    /**
     * {@link WindowContext} の保持するアクションコンポーネントに対して、バリューバインディングを行います。<br />
     * 
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    public static void importValue(WindowContext context) {
        Object target = context.getActionComponent();
        ActionDesc desc = ActionDescFactory.getActionDesc(target.getClass());

        List<Field> importFields = desc.getImportFields();
        for (Field field : importFields) {
            ImportValue annotation = field.getAnnotation(ImportValue.class);
            String id = annotation.id();
            if (StringUtil.isEmpty(id)) {
                id = field.getName();
            }

            Widget widget = context.getComponent(id);
            if (widget != null) {
                WidgetValueBinder binder = WidgetValueBinderFactory
                        .getBinder(widget.getClass());
                if (binder != null) {
                    binder.importValue(widget, field);
                } else {
                    throw new ValueBindingException(
                            ValueBindingException.WIDGET_NOT_SUPPORTED, widget
                                    .getClass().getName(), target.getClass(),
                            field);
                }
            } else {
                throw new ValueBindingException(
                        ValueBindingException.IMPORT_SOURCE_NOT_FOUND, id,
                        target.getClass(), field);
            }
        }
    }
}
