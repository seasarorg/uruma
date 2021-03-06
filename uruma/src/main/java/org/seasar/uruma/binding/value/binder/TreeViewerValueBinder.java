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

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.jface.TreeColumnComponent;
import org.seasar.uruma.component.jface.TreeComponent;
import org.seasar.uruma.viewer.UrumaTreeViewer;

/**
 * {@link TreeViewer} のための {@link ValueBinder} です。<br />
 * 
 * @author y-komori
 */
public class TreeViewerValueBinder extends AbstractValueBinder<UrumaTreeViewer> {

    /**
     * {@link TreeViewerValueBinder} を構築します。<br />
     */
    public TreeViewerValueBinder() {
        super(UrumaTreeViewer.class);
    }

    /*
     * @see org.seasar.uruma.binding.value.binder.AbstractValueBinder#doExportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    @Override
    public void doExportValue(final UrumaTreeViewer widget,
            final Object formObj, final PropertyDesc propDesc,
            final UIComponent uiComp) {
        if (widget.getContentProvider() != null) {
            Object value = propDesc.getValue(formObj);
            if (value != null) {
                Object oldValue = widget.getInput();
                if (oldValue != value) {
                    logBinding(EXPORT_VALUE, formObj, propDesc, widget, null,
                            value);
                    widget.setInput(value);
                }
            }
        }

        Tree tree = widget.getTree();
        tree.setRedraw(false);
        TreeColumn[] columns = tree.getColumns();
        TreeComponent treeComponent = (TreeComponent) uiComp;
        for (int i = 0; i < columns.length; i++) {
            if (((TreeColumnComponent) treeComponent.getChildren().get(i)).width == null) {
                columns[i].pack();
            }
        }
        tree.setRedraw(true);
    }
}
