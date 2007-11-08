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
package org.seasar.uruma.viewer;

import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;

/**
 * 機能拡張した {@link TreeViewer} です。<br />
 * 
 * @author y-komori
 */
public class UrumaTreeViewer extends TreeViewer implements IDoubleClickListener {

    /**
     * {@link UrumaTreeViewer} を構築します。<br />
     * 
     * @param parent
     *            親 {@link Composite}
     */
    public UrumaTreeViewer(final Composite parent) {
        super(parent);
        init();
    }

    /**
     * {@link UrumaTreeViewer} を構築します。<br />
     * 
     * @param parent
     *            親 {@link Composite}
     * @param style
     *            スタイル値
     */
    public UrumaTreeViewer(final Composite parent, final int style) {
        super(parent, style);
        init();
    }

    /**
     * {@link UrumaTreeViewer} を構築します。<br />
     * 
     * @param tree
     *            {@link Tree} オブジェクト
     */
    public UrumaTreeViewer(final Tree tree) {
        super(tree);
        init();
    }

    protected void init() {
        addDoubleClickListener(this);
    }

    /*
     * @see org.eclipse.jface.viewers.IDoubleClickListener#doubleClick(org.eclipse.jface.viewers.DoubleClickEvent)
     */
    public void doubleClick(final DoubleClickEvent event) {
        IStructuredSelection selection = (IStructuredSelection) event
                .getSelection();
        Object selected = selection.getFirstElement();

        // ノードの展開状態を反転させる
        boolean expandedState = getExpandedState(selected);
        setExpandedState(selected, !expandedState);
    }

}
