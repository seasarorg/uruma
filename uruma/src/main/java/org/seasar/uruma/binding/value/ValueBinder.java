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
package org.seasar.uruma.binding.value;

import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.ImportValue;
import org.seasar.uruma.binding.value.command.ExportSelectionCommand;
import org.seasar.uruma.binding.value.command.ExportValueCommand;
import org.seasar.uruma.binding.value.command.ImportSelectionCommand;
import org.seasar.uruma.binding.value.command.ImportValueCommand;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.desc.FormDesc;
import org.seasar.uruma.desc.FormDescFactory;
import org.seasar.uruma.exception.BindingException;

/**
 * ValueBinding を実現するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ValueBinder {
    private static final ImportValueCommand IMPORT_VALUE_COMMAND = new ImportValueCommand();

    private static final ExportValueCommand EXPORT_VALUE_COMMAND = new ExportValueCommand();

    private static final ImportSelectionCommand IMPORT_SELECTION_COMMAND = new ImportSelectionCommand();

    private static final ExportSelectionCommand EXPORT_SELECTION_COMMAND = new ExportSelectionCommand();

    /**
     * ウィジットから {@link PartContext} の保持するアクションコンポーネントに対して、ValueBinding を行います。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void importValue(final PartContext context) {
        dealFields(context, IMPORT_VALUE_COMMAND);
    }

    /**
     * {@link PartContext} の保持するアクションコンポーネントから、ウィジットへ ValueBinding を行います。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void exportValue(final PartContext context) {
        dealFields(context, EXPORT_VALUE_COMMAND);
    }

    /**
     * ウィジットから {@link PartContext} の保持するアクションコンポーネントに対して、SelectionBinding を行います。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void importSelection(final PartContext context) {
        dealFields(context, IMPORT_SELECTION_COMMAND);
    }

    /**
     * {@link PartContext} の保持するアクションコンポーネントから、ウィジットへ SelectionBinding を行います。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void exportSelection(final PartContext context) {
        dealFields(context, EXPORT_SELECTION_COMMAND);
    }

    private static void dealFields(final PartContext context,
            final WidgetValueBinderCommand command) {
        Object form = context.getFormObject();
        if (form == null) {
            return;
        }

        FormDesc desc = FormDescFactory.getFormDesc(form.getClass());
        List<Field> targetFields = command.getTargetFields(desc);
        for (Field field : targetFields) {
            String id = getId(field);
            Widget widget = getWidget(context, id, field);
            if (widget == null) {
                throw new BindingException(BindingException.WIDGET_NOT_FOUND,
                        id, context.getPartActionObject().getClass(), field);
            }

            if (!widget.isDisposed()) {
                WidgetValueBinder binder = WidgetValueBinderFactory
                        .getBinder(widget.getClass());
                if (binder != null) {
                    command.setWidgetValueBinder(binder);
                    command.doBind(widget, form, field, context);
                } else {
                    throw new BindingException(
                            BindingException.WIDGET_NOT_SUPPORTED, widget
                                    .getClass().getName(), form.getClass(),
                            field);
                }
            }
        }

    }

    private static String getId(final Field field) {
        ExportValue exportValue = field.getAnnotation(ExportValue.class);
        if (exportValue != null) {
            String id = exportValue.id();
            return StringUtil.isEmpty(id) ? field.getName() : id;
        }

        ImportValue importValue = field.getAnnotation(ImportValue.class);
        if (importValue != null) {
            String id = importValue.id();
            return StringUtil.isEmpty(id) ? field.getName() : id;
        }

        ImportExportValue importExportValue = field
                .getAnnotation(ImportExportValue.class);
        if (importExportValue != null) {
            String id = importExportValue.id();
            return StringUtil.isEmpty(id) ? field.getName() : id;
        }

        ExportSelection exportSelection = field
                .getAnnotation(ExportSelection.class);
        if (exportSelection != null) {
            String id = exportSelection.id();
            return StringUtil.isEmpty(id) ? field.getName() : id;
        }

        ImportSelection importSelection = field
                .getAnnotation(ImportSelection.class);
        if (importSelection != null) {
            String id = importSelection.id();
            return StringUtil.isEmpty(id) ? field.getName() : id;
        }
        return field.getName();
    }

    private static Widget getWidget(final PartContext context, String id,
            final Field field) {
        if (StringUtil.isEmpty(id)) {
            id = field.getName();
        }
        WidgetHandle handle = context.getWidgetHandle(id);
        if (handle != null) {
            return handle.<Widget> getCastWidget();
        } else {
            return null;
        }
    }
}
