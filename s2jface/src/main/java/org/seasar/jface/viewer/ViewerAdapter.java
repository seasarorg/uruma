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

import org.eclipse.jface.viewers.Viewer;

/**
 * Viewer の利用をサポートするためのインターフェースです。<br />
 * <p>
 * {@link ViewerAdapter} の実装クラスは JFace の
 * {@link org.eclipse.jface.viewers.Viewer} インスタンスと対になって生成され、{@link org.seasar.jface.WindowContext}
 * に登録されます。<br />
 * これらのクラスは、{@link org.seasar.jface.binding.WidgetValueBinder} から
 * {@link org.eclipse.jface.viewers.Viewer} に対して値を設定するのに利用されます。
 * </p>
 * 
 * @author y-komori
 */
public interface ViewerAdapter<VIEWER_TYPE extends Viewer> {
    /**
     * 本クラスのサポートする {@link org.eclipse.jface.viewers.Viewer} を返します。<br />
     * 
     * @return {@link org.eclipse.jface.viewers.Viewer} の実装オブジェクト。
     */
    public VIEWER_TYPE getViewer();

    /**
     * {@link org.eclipse.jface.viewers.Viewer} へ表示するオブジェクトを配列形式でセットします。<br />
     * 
     * @param contents
     *            テーブルへ表示するオブジェクトの配列
     */
    public void setContents(Object[] contents);

    /**
     * {@link org.eclipse.jface.viewers.Viewer} へ表示するオブジェクトを {@link List}
     * 形式でセットします。<br />
     * 
     * @param contents
     *            テーブルへ表示するオブジェクトのリスト
     */
    public void setContents(List<?> contents);

    /**
     * 現在選択されているオブジェクトを取得します。<br />
     * 
     * @return 選択中のオブジェクト
     */
    public Object getSelectedElement();

    /**
     * 現在選択されている複数のオブジェクトを取得します。<br />
     * 
     * @return 選択中のオブジェクト
     */
    public Object[] getSelectedElements();
}
