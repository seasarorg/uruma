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
import org.eclipse.swt.widgets.Table;

/**
 * S2JFace で {@link TableViewer} を使用するためのアダプタクラスです。<br />
 * 
 * @author y-komori
 */
public class S2JFaceTableViewer extends TableViewer {

    private Object[] contents;

    public S2JFaceTableViewer(Table table) {
        super(table);
        setContentProvider(new TableContentProvider());
    }

    /**
     * テーブルへ表示するオブジェクトを配列形式でセットします。<br />
     * 
     * @param contents
     *            テーブルへ表示するオブジェクトの配列
     */
    public void setContents(Object[] contents) {
        this.contents = contents;
    }

    /**
     * テーブルへ表示するオブジェクトを {@link List} 形式でセットします。<br />
     * 
     * @param contents
     *            テーブルへ表示するオブジェクトのリスト
     */
    public void setContents(List<?> contents) {
        this.contents = (Object[]) contents
                .toArray(new Object[contents.size()]);
    }

    /**
     * 現在選択されているオブジェクトを取得します。<br />
     * 
     * @return 選択中のオブジェクト
     */
    public Object getSelectedElement() {
        ISelection selection = getSelection();
        return ((IStructuredSelection) selection).getFirstElement();
    }

    /**
     * 現在選択されている複数のオブジェクトを取得します。<br />
     * 
     * @return 選択中のオブジェクト
     */
    public Object[] getSelectedElements() {
        ISelection selection = getSelection();
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
