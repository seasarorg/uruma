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
package org.seasar.uruma.binding.value.command;

import java.lang.reflect.Field;
import java.util.List;

import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.binding.value.BindingCommand;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.desc.FormDesc;

/**
 * {@link ExportSelection} アノテーションに対応した処理を行うための、{@link BindingCommand} です。<br />
 * 
 * @author y-komori
 */
public class ExportSelectionCommand extends
        AbstractBindingCommand<ExportSelection> {

    /*
     * @see org.seasar.uruma.binding.value.WidgetValueBinderCommand#getTargetFields(org.seasar.uruma.desc.FormDesc)
     */
    public List<Field> getTargetFields(final FormDesc desc) {
        return desc.getExportSelectionFields();
    }

    /*
     * @see org.seasar.uruma.binding.value.command.AbstractBindingCommand#doBind(ValueBinder,
     *      Object, Object, Field)
     */
    @Override
    protected void doBind(final ValueBinder binder, final Object widget,
            final Object formObj, final Field formField) {
        binder.exportSelection(widget, formObj, formField);
    }

    /*
     * @see org.seasar.uruma.binding.value.command.AbstractBindingCommand#getAnnotation(Field)
     */
    @Override
    protected ExportSelection getAnnotation(final Field field) {
        return field.getAnnotation(ExportSelection.class);
    }

    /*
     * @see org.seasar.uruma.binding.value.command.AbstractBindingCommand#getId(ANNOTATION_CLASS)
     */
    @Override
    protected String getId(final ExportSelection annotation) {
        return annotation.id();
    }
}
