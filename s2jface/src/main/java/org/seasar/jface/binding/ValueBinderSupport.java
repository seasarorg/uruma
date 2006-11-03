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

import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.BindingValue;
import org.seasar.jface.binding.impl.ExportValueBinder;
import org.seasar.jface.binding.impl.ImportValueBinder;
import org.seasar.jface.exception.ValueBindingException;

/**
 * @author bskuroneko
 * 
 */
public class ValueBinderSupport {

    public static void createValueBinders(ActionDesc actionDesc,
            WindowContext context) {

        for (Field field : actionDesc.getImportFields()) {
            createImportValueBinder(field, context);
        }

        for (Field field : actionDesc.getExportFields()) {
            createExportValueBinder(field, context);
        }

        ValueBinder.exportValue(context);
    }

    private static void createImportValueBinder(Field field,
            WindowContext context) {
        BindingValue annotation = field.getAnnotation(BindingValue.class);
        String id = annotation.id();
        Widget widget = getWidget(context, id, field);
        if (widget != null) {
            context.addImportValueBinder(new ImportValueBinder(field, context
                    .getActionComponent(), widget, context));
        } else {
            throw new ValueBindingException(
                    ValueBindingException.IMPORT_SOURCE_NOT_FOUND, id, context
                            .getActionComponent().getClass(), field);
        }
    }

    private static void createExportValueBinder(Field field,
            WindowContext context) {
        BindingValue annotation = field.getAnnotation(BindingValue.class);
        String id = annotation.id();
        Widget widget = getWidget(context, id, field);
        if (widget != null) {
            context.addExportValueBinder(new ExportValueBinder(field, context
                    .getActionComponent(), widget, context));
        } else {
            throw new ValueBindingException(
                    ValueBindingException.IMPORT_SOURCE_NOT_FOUND, id, context
                            .getActionComponent().getClass(), field);
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
