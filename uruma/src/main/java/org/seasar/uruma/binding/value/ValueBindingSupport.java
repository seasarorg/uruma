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
public class ValueBindingSupport {
    private static final ImportValueCommand IMPORT_VALUE_COMMAND = new ImportValueCommand();

    private static final ExportValueCommand EXPORT_VALUE_COMMAND = new ExportValueCommand();

    private static final ImportSelectionCommand IMPORT_SELECTION_COMMAND = new ImportSelectionCommand();

    private static final ExportSelectionCommand EXPORT_SELECTION_COMMAND = new ExportSelectionCommand();

    /**
     * ウィジットから {@link PartContext} の保持するアクションコンポーネントに対して、バリュー・バインディングを行います。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void importValue(final PartContext context) {
        dealFields(context, IMPORT_VALUE_COMMAND);
    }

    /**
     * {@link PartContext} の保持するアクションコンポーネントから、ウィジットへバリュー・バインディングを行います。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void exportValue(final PartContext context) {
        dealFields(context, EXPORT_VALUE_COMMAND);
    }

    /**
     * ウィジットから {@link PartContext} の保持するアクションコンポーネントに対して、セレクション・バインディングを行います。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void importSelection(final PartContext context) {
        dealFields(context, IMPORT_SELECTION_COMMAND);
    }

    /**
     * {@link PartContext} の保持するアクションコンポーネントから、ウィジットへセレクション・バインディングを行います。<br />
     * 
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void exportSelection(final PartContext context) {
        dealFields(context, EXPORT_SELECTION_COMMAND);
    }

    private static void dealFields(final PartContext context,
            final BindingCommand command) {
        Object form = context.getFormObject();
        if (form == null) {
            return;
        }

        FormDesc desc = FormDescFactory.getFormDesc(form.getClass());
        List<Field> targetFields = command.getTargetFields(desc);
        for (Field field : targetFields) {
            String id = command.getId(field);

            WidgetHandle handle = context.getWidgetHandle(id);
            if (handle != null) {
                Object widget = handle.getWidget();

                try {
                    command.doBind(widget, form, field);
                } catch (BindingException ex) {
                    throw new BindingException(
                            BindingException.WIDGET_NOT_SUPPORTED, id, form
                                    .getClass(), field);
                }
            } else {
                throw new BindingException(BindingException.WIDGET_NOT_FOUND,
                        id, form.getClass(), field);
            }
        }
    }
}
