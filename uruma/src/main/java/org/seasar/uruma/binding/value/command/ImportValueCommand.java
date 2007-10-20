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

import org.eclipse.swt.widgets.Widget;
import org.seasar.uruma.binding.value.WidgetValueBinder;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.desc.FormDesc;

/**
 * {@link WidgetValueBinder#importValue(Widget, Object, Field, org.seasar.uruma.context.PartContext)}
 * メソッドを実行するためのコマンドです。<br />
 * 
 * @author y-komori
 */
public class ImportValueCommand extends AbstractWidgetValueBinderCommand {

    /*
     * @see org.seasar.uruma.binding.value.WidgetValueBinderCommand#doBind(org.eclipse.swt.widgets.Widget,
     *      java.lang.Object, java.lang.reflect.Field,
     *      org.seasar.uruma.context.PartContext)
     */
    public void doBind(final Widget widget, final Object target,
            final Field field, final PartContext context) {
        binder.importValue(widget, target, field, context);
    }

    /*
     * @see org.seasar.uruma.binding.value.WidgetValueBinderCommand#getTargetFields(org.seasar.uruma.desc.FormDesc)
     */
    public List<Field> getTargetFields(final FormDesc desc) {
        return desc.getImportValueFields();
    }
}
