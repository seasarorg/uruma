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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.seasar.eclipse.common.util.ImageManager;

/**
 * テーブルのラベルを提供するための抽象クラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public abstract class TableLabelProvider<E> implements ILabelProvider, ITableLabelProvider {

    @SuppressWarnings("unchecked")
    private E cast(final Object target) {
        return (E) target;
    }

    /*
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang.Object, int)
     */
    public final Image getColumnImage(final Object element, final int columnIndex) {
        return doGetColumnImage(cast(element), columnIndex);
    }

    /*
     * @see org.eclipse.jface.viewers.ITableLabelProvider#getColumnText(java.lang.Object, int)
     */
    public final String getColumnText(final Object element, final int columnIndex) {
        return doGetColumnText(cast(element), columnIndex);
    }

    /*
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#addListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void addListener(final ILabelProviderListener listener) {
        // do nothing.
    }

    /*
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#dispose()
     */
    public void dispose() {
        // do nothing.
    }

    /*
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#isLabelProperty(java.lang.Object, java.lang.String)
     */
    public boolean isLabelProperty(final Object element, final String property) {
        return false;
    }

    /*
     * @see org.eclipse.jface.viewers.IBaseLabelProvider#removeListener(org.eclipse.jface.viewers.ILabelProviderListener)
     */
    public void removeListener(final ILabelProviderListener listener) {
        // do nothing.
    }

    /*
     * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
     */
    public Image getImage(final Object element) {
        return doGetColumnImage(cast(element), 0);
    }

    /*
     * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
     */
    public final String getText(final Object element) {
        return doGetColumnText(cast(element), 0);
    }

    /**
     * カラムに表示する文字列を返します。<br />
     * 
     * @param element
     *        要素オブジェクト
     * @param columnIndex
     *        カラムインデックス
     * @return 文字列
     */
    protected abstract String doGetColumnText(E element, int columnIndex);

    /**
     * カラムに表示するイメージを返します。<br />
     * デフォルトの実装では、内部で
     * {@link TableLabelProvider#doGetColumnImageKey(Object, int)
     * doGetColumnImageKey()} メソッドを呼び出してイメージのキーまたはパスを取得し、 {@link ImageManager}
     * を使用して {@link Image} オブジェクトを取得するようになっています。 そのため、通常は
     * {@link TableLabelProvider#doGetColumnImageKey(Object, int)
     * doGetColumnImageKey()} メソッドを実装するようにしてください。
     * 
     * @param element
     *        要素オブジェクト
     * @param columnIndex
     *        カラムインデックス
     * @return イメージオブジェクト
     */
    protected Image doGetColumnImage(final E element, final int columnIndex) {
        String key = doGetColumnImageKey(element, columnIndex);
        if (key != null) {
            return ImageManager.getImage(key);
        } else {
            return null;
        }
    }

    /**
     * カラムに表示する画像のキーまたはパスを返します。<br />
     * キーまたはパスは {@link ImageManager} で読み込むためのものを返します。<br /> {@link ImageManager}
     * を使用しない場合は、本メソッドを空実装とし、
     * {@link TableLabelProvider#doGetColumnImage(Object, int)
     * doGetColumnImage()} メソッドをオーバーライドしてください。
     * 
     * @param element
     *        要素オブジェクト
     * @param columnIndex
     *        カラムインデックス
     * @return 画像のキーまたはパス
     */
    protected abstract String doGetColumnImageKey(E element, int columnIndex);
}
