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
package org.seasar.uruma.binding.value.binder;

import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.swt.widgets.Combo;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.viewer.ContentsHolder;
import org.seasar.uruma.viewer.GenericLabelProvider;

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
        propDesc.setValue(formObj, combo.getText());
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doExportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    public void doExportValue(final ComboViewer widget, final Object formObj,
            final PropertyDesc propDesc) {
        Class<?> formFieldType = propDesc.getField().getType();
        Object contents = propDesc.getValue(formObj);
        IContentProvider contentProvider = widget.getContentProvider();
        IBaseLabelProvider labelProvider = widget.getLabelProvider();

        if (contents != null) {
            if (contentProvider != null
                    && contentProvider instanceof ContentsHolder) {
                ContentsHolder holder = (ContentsHolder) contentProvider;
                if (formFieldType.isArray()) {
                    holder.setContents((Object[]) contents);
                    setClassToGenericLabelProvider(labelProvider, formFieldType
                            .getComponentType());
                } else if (List.class.isAssignableFrom(formFieldType)) {
                    List<?> listContents = (List<?>) contents;

                    if (listContents.size() > 0) {
                        holder.setContents(listContents);

                        Object content = listContents.get(0);
                        setClassToGenericLabelProvider(labelProvider, content
                                .getClass());
                    }
                } else {
                    holder.setContents(new Object[] { contents });
                    setClassToGenericLabelProvider(labelProvider, contents
                            .getClass());
                }

                widget.setInput(contents);
            }
        }
    }

    @Override
    public void doImportSelection(final ComboViewer widget,
            final Object formObj, final PropertyDesc propDesc) {
        // TODO 自動生成されたメソッド・スタブ
        super.doImportSelection(widget, formObj, propDesc);
    }

    @Override
    public void doExportSelection(final ComboViewer widget,
            final Object formObj, final PropertyDesc propDesc) {
        // TODO 自動生成されたメソッド・スタブ
        super.doExportSelection(widget, formObj, propDesc);
    }

    private void setClassToGenericLabelProvider(
            final IBaseLabelProvider provider, final Class<?> clazz) {
        if ((provider != null) && (provider instanceof GenericLabelProvider)) {
            ((GenericLabelProvider) provider).setTargetClass(clazz);
        }
    }

}
