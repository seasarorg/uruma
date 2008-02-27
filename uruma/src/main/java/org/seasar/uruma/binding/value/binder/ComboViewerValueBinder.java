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

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Combo;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.binding.value.ValueBinder;

/**
 * {@link ComboViewer} のための {@link ValueBinder} です。<br />
 * 
 * @author y-komori
 */
public class ComboViewerValueBinder extends AbstractValueBinder<ComboViewer> {

    /**
     * {@link ComboViewerValueBinder} を構築します。<br />
     * 
     */
    public ComboViewerValueBinder() {
        super(ComboViewer.class);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doImportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    public void doImportValue(final ComboViewer widget, final Object formObj,
            final PropertyDesc propDesc) {
        Combo combo = widget.getCombo();
        String text = combo.getText();
        text = StringUtil.isEmpty(text) ? null : text;

        logBinding(IMPORT_VALUE, widget, null, formObj, propDesc, text);

        propDesc.setValue(formObj, text);
    }
}
