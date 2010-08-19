/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
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
package org.seasar.uruma.userviewer;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

/**
 * ツリー構造の内容を提供するための抽象クラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public abstract class TreeContentProvider<E> implements ITreeContentProvider {

    @SuppressWarnings("unchecked")
    private E cast(final Object target) {
        return (E) target;
    }

    /*
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
     */
    public final Object[] getChildren(final Object parentElement) {
        List<E> children = doGetChildren(cast(parentElement));
        Object[] result = new Object[] {};
        result = children.<Object> toArray(result);
        return result;
    }

    /*
     * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
     */
    public final Object getParent(final Object element) {
        return doGetParent(cast(element));
    }

    /*
     * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
     */
    public final boolean hasChildren(final Object element) {
        return doHasChildren(cast(element));
    }

    /*
     * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
     */
    public final Object[] getElements(final Object inputElement) {
        return getChildren(inputElement);
    }

    /*
     * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer, java.lang.Object, java.lang.Object)
     */
    public final void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
        doInputChanged(viewer, cast(oldInput), cast(newInput));
    }

    /**
     * 与えられた要素の子を返します。<br />
     * 
     * @param parentElement
     *        子を取得する要素
     * @return 子要素のリスト
     */
    protected abstract List<E> doGetChildren(E parentElement);

    /**
     * 与えられた要素の親を帰します。<br />
     * 
     * @param element
     *        親を調べる要素
     * 
     * @return 親。存在しない場合は {@code null}
     */
    protected abstract E doGetParent(final E element);

    /**
     * 子要素を持つかどうかを返します。<br />
     * 
     * @param element
     *        調査対象要素
     * @return 子要素を持つ場合は {@code true}
     */
    protected abstract boolean doHasChildren(E element);

    /**
     * モデルを破棄します。<br />
     */
    public void dispose() {
        // do nothing.
    }

    /**
     * 入力が変更されたことを通知します。<br />
     * 
     * @param viewer
     *        入力が変更された {@link Viewer} のインスタンス
     * @param oldInput
     *        変更前の入力
     * @param newInput
     *        変更後の入力
     */
    protected void doInputChanged(final Viewer viewer, final E oldInput, final E newInput) {
        // do nothing.
    }
}
