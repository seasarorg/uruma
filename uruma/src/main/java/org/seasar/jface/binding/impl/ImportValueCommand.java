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
package org.seasar.jface.binding.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.FormDesc;

/**
 * {@link org.seasar.jface.binding.WidgetValueBinder}の
 * <code>importValue()</code> メソッドを実行するためのコマンドです。<br />
 * 
 * @author y-komori
 */
public class ImportValueCommand extends AbstractWidgetValueBinderCommand {

    public void doBind(Widget widget, Object target, Field field,
            WindowContext context) {
        binder.importValue(widget, target, field, context);
    }

    public List<Field> getTargetFields(FormDesc desc) {
        return desc.getImportValueFields();
    }
}
