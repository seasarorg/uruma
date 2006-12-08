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
package org.seasar.jface.binding.impl;

import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.WidgetValueBinder;
import org.seasar.jface.exception.BindingException;
import org.seasar.jface.viewer.ComboViewerAdapter;
import org.seasar.jface.viewer.GenericLabelProvider;

/**
 * {@link org.eclipse.jface.viewers.ComboViewer} のための ValueBinder です。<br />
 * 
 * @author y-komori
 */
public class ComboViewerValueBinder implements WidgetValueBinder {

    public void exportValue(Object srcObject, Field srcField, Widget dest,
            WindowContext context) {
        ComboViewerAdapter viewerAdapter = (ComboViewerAdapter) context
                .getViewerAdapter(dest);
        ComboViewer viewer = viewerAdapter.getViewer();
        IBaseLabelProvider provider = viewer.getLabelProvider();

        Class type = srcField.getType();
        Object contents = FieldUtil.get(srcField, srcObject);
        if (contents != null) {
            if (type.isArray()) {
                viewerAdapter.setContents((Object[]) contents);
                setClassToGenericLabelProvider(provider, type
                        .getComponentType());
            } else if (List.class.isAssignableFrom(type)) {
                List listContents = (List) contents;
                viewerAdapter.setContents(listContents);

                Object content = listContents.get(0);
                setClassToGenericLabelProvider(provider, content.getClass());
            } else {
                viewerAdapter.setContents(new Object[] { contents });
                setClassToGenericLabelProvider(provider, contents.getClass());
            }
            viewer.setInput(contents);
        }

    }

    public void importValue(Widget src, Object destObject, Field destField,
            WindowContext context) {
        Combo combo = (Combo) src;
        String value = combo.getText();
        if (destField.getType() == String.class) {
            FieldUtil.set(destField, destObject, combo.getText());
        } else {
            throw new BindingException(BindingException.CLASS_NOT_MUTCH, null,
                    destObject.getClass(), destField);
        }
    }

    public void exportSelection(Object srcObject, Field srcField, Widget dest,
            WindowContext context) {
        // TODO 自動生成されたメソッド・スタブ

    }

    public void importSelection(Widget src, Object destObject, Field destField,
            WindowContext context) {
        ComboViewerAdapter viewerAdapter = (ComboViewerAdapter) context
                .getViewerAdapter(src);
        ComboViewer viewer = viewerAdapter.getViewer();

        ISelection selection = viewer.getSelection();
        if (selection != null) {
            Object selectedObject = ((StructuredSelection) selection)
                    .getFirstElement();
            Class<?> destFieldClass = destField.getType();
            if (destFieldClass.isAssignableFrom(selectedObject.getClass())) {
                FieldUtil.set(destField, destObject, selectedObject);
            } else {
                throw new BindingException(BindingException.CLASS_NOT_MUTCH,
                        null, destObject.getClass(), destField);
            }
        }
    }

    public Class<? extends Widget> getWidgetType() {
        return Combo.class;
    }

    private void setClassToGenericLabelProvider(IBaseLabelProvider provider,
            Class clazz) {
        if (provider instanceof GenericLabelProvider) {
            ((GenericLabelProvider) provider).setTargetClass(clazz);
        }
    }

}
