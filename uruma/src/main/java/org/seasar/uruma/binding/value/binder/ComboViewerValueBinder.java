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
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Combo;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.exception.BindingException;
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

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doImportSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    public void doImportSelection(final ComboViewer widget,
            final Object formObj, final PropertyDesc propDesc) {
        ISelection selection = widget.getSelection();
        Object selectedObject = ((StructuredSelection) selection)
                .getFirstElement();

        if (selectedObject != null) {
            Class<?> destFieldClass = propDesc.getPropertyType();
            if (destFieldClass.isAssignableFrom(selectedObject.getClass())) {
                propDesc.setValue(formObj, selectedObject);
            } else {
                throw new BindingException(BindingException.CLASS_NOT_MUTCH,
                        null, formObj.getClass(), propDesc.getField());
            }
        }
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doExportSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    public void doExportSelection(final ComboViewer widget,
            final Object formObj, final PropertyDesc propDesc) {
        Object selection = propDesc.getValue(formObj);
        if (selection != null) {
            widget.setSelection(new StructuredSelection(selection), true);
        }
    }

    private void setClassToGenericLabelProvider(
            final IBaseLabelProvider provider, final Class<?> clazz) {
        if ((provider != null) && (provider instanceof GenericLabelProvider)) {
            ((GenericLabelProvider) provider).setTargetClass(clazz);
        }
    }

}
