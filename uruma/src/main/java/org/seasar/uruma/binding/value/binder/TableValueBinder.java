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
package org.seasar.uruma.binding.value.binder;

import org.eclipse.swt.widgets.Table;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.component.UIComponent;

/**
 * {@link Table} に対する {@link ValueBinder} です。<br />
 * {@link Table} に対してはバリュー・バインディングを行いません。<br />
 * tableItem を設定した table に対してバインディングを行おうとした場合の対処用です。<br />
 * 
 * @author y-komori
 */
public class TableValueBinder implements ValueBinder {

    public void exportSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {
        // Do nothing.
    }

    public void exportValue(final Object widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {
        // Do nothing.
    }

    public Class<?> getWidgetType() {
        return Table.class;
    }

    public void importSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc, final UIComponent uiComp) {
        // Do nothing.
    }

    public void importValue(final Object widget, final Object formObj,
            final PropertyDesc propDes, final UIComponent uiCompc) {
        // Do nothing.
    }
}
