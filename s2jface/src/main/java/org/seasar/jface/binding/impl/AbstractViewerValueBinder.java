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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.WidgetValueBinder;
import org.seasar.jface.exception.BindingException;
import org.seasar.jface.viewer.ViewerAdapter;

/**
 * {@link org.eclipse.jface.viewers.Viewer} に対する ValueBinding 機能を提供する基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractViewerValueBinder implements WidgetValueBinder {

    /*
     * @see org.seasar.jface.binding.WidgetValueBinder#importSelection(org.eclipse.swt.widgets.Widget,
     *      java.lang.Object, java.lang.reflect.Field,
     *      org.seasar.jface.WindowContext)
     */
    public void importSelection(Widget src, Object destObject, Field destField,
            WindowContext context) {
        ViewerAdapter<?> viewerAdapter = context.getViewerAdapter(src);
        if (viewerAdapter == null) {
            return;
        }
        Viewer viewer = viewerAdapter.getViewer();

        IStructuredSelection selection = (IStructuredSelection) viewer
                .getSelection();
        int size = selection.size();
        if (size > 0) {
            Class<?> destFieldClass = destField.getType();
            Object firstElement = selection.getFirstElement();
            if (destFieldClass.isArray()) {
                Object[] selectedArray = selection.toArray();
                if (destFieldClass.isAssignableFrom(selectedArray.getClass())) {
                    FieldUtil.set(destField, destObject, selectedArray);
                } else {
                    throw new BindingException(
                            BindingException.CLASS_NOT_MUTCH, null, destObject
                                    .getClass(), destField);
                }
            } else if (destFieldClass.isAssignableFrom(List.class)) {
                List<Object> selectedObjects = new ArrayList<Object>();
                Iterator<?> iterator = selection.iterator();
                while (iterator.hasNext()) {
                    selectedObjects.add(iterator.next());
                }
                FieldUtil.set(destField, destObject, selectedObjects);
            } else if (destFieldClass.isAssignableFrom(firstElement.getClass())) {
                FieldUtil.set(destField, destObject, firstElement);
            } else {
                throw new BindingException(BindingException.CLASS_NOT_MUTCH,
                        null, destObject.getClass(), destField);
            }
        }
    }

    /*
     * @see org.seasar.jface.binding.WidgetValueBinder#exportSelection(java.lang.Object,
     *      java.lang.reflect.Field, org.eclipse.swt.widgets.Widget,
     *      org.seasar.jface.WindowContext)
     */
    public void exportSelection(Object srcObject, Field srcField, Widget dest,
            WindowContext context) {
        ViewerAdapter<?> viewerAdapter = context.getViewerAdapter(dest);
        Viewer viewer = viewerAdapter.getViewer();

        Object selection = FieldUtil.get(srcField, srcObject);
        if (selection != null) {
            viewer.setSelection(new StructuredSelection(selection), true);
        }
    }
}
