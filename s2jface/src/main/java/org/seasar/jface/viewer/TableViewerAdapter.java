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
package org.seasar.jface.viewer;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * S2JFace で {@link TableViewer} を使用するためのアダプタクラスです。<br />
 * 
 * @author y-komori
 */
public class TableViewerAdapter implements ViewerAdapter<TableViewer> {

    private Object[] contents;

    private TableViewer viewer;

    public TableViewerAdapter(TableViewer viewer) {
        this.viewer = viewer;
        viewer.setContentProvider(new TableContentProvider());
    }

    /*
     * @see org.seasar.jface.viewer.ViewerAdapter#getViewer()
     */
    public TableViewer getViewer() {
        return viewer;
    }

    /*
     * @see org.seasar.jface.viewer.ViewerAdapter#setContents(java.lang.Object[])
     */
    public void setContents(Object[] contents) {
        this.contents = contents;
    }

    /*
     * @see org.seasar.jface.viewer.ViewerAdapter#setContents(java.util.List)
     */
    public void setContents(List<?> contents) {
        this.contents = (Object[]) contents
                .toArray(new Object[contents.size()]);
    }

    /*
     * @see org.seasar.jface.viewer.ViewerAdapter#getSelectedElement()
     */
    public Object getSelectedElement() {
        ISelection selection = viewer.getSelection();
        return ((IStructuredSelection) selection).getFirstElement();
    }

    /*
     * @see org.seasar.jface.viewer.ViewerAdapter#getSelectedElements()
     */
    public Object[] getSelectedElements() {
        ISelection selection = viewer.getSelection();
        return ((IStructuredSelection) selection).toArray();
    }

    private class TableContentProvider implements IStructuredContentProvider {

        public Object[] getElements(Object inputElement) {
            return contents;
        }

        public void dispose() {
        }

        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }
}
