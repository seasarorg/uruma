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

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.binding.WidgetValueBinder;
import org.seasar.jface.viewer.GenericTableLabelProvider;
import org.seasar.jface.viewer.TableViewerAdapter;

/**
 * {@link org.eclipse.jface.viewers.TableViewer} のための ValueBinder です。<br />
 * 
 * @author y-komori
 */
public class TableViewerValueBinder implements WidgetValueBinder {

    public void exportValue(Object srcObject, Field srcField, Widget dest,
            WindowContext context) {
        TableViewerAdapter viewerAdapter = (TableViewerAdapter) context
                .getViewerAdapter(dest);
        TableViewer viewer = viewerAdapter.getViewer();
        IBaseLabelProvider provider = viewer.getLabelProvider();

        Class type = srcField.getType();
        Object contents = FieldUtil.get(srcField, srcObject);
        if (contents != null) {
            if (type.isArray()) {
                viewerAdapter.setContents((Object[]) contents);
                setClassToGenericTableLabelProvider(provider, type
                        .getComponentType());
            } else if (List.class.isAssignableFrom(type)) {
                List listContents = (List) contents;
                viewerAdapter.setContents(listContents);

                Object content = listContents.get(0);
                setClassToGenericTableLabelProvider(provider, content
                        .getClass());
            } else {
                viewerAdapter.setContents(new Object[] { contents });
                setClassToGenericTableLabelProvider(provider, contents
                        .getClass());
            }
            viewer.setInput(contents);
        }
    }

    private void setClassToGenericTableLabelProvider(
            IBaseLabelProvider provider, Class clazz) {
        if (provider instanceof GenericTableLabelProvider) {
            ((GenericTableLabelProvider) provider).setTargetClass(clazz);
        }
    }

    public void importValue(Widget src, Object destObject, Field destField,
            WindowContext context) {
        TableViewerAdapter viewerAdapter = (TableViewerAdapter) context
                .getViewerAdapter(src);

        // TODO 要作成

    }

    public Class<? extends Widget> getWidgetType() {
        return Table.class;
    }

    public void exportSelection(Object srcObject, Field srcField, Widget dest,
            WindowContext context) {
        // TODO exportSelection の実装

    }

    public void importSelection(Widget src, Object destObject, Field destField,
            WindowContext context) {
        // TODO importSelection の実装

    }
}
