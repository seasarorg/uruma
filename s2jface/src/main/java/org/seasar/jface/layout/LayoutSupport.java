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
package org.seasar.jface.layout;

import org.eclipse.swt.widgets.Layout;
import org.seasar.jface.component.impl.ControlComponent;

/**
 * SWTにおけるレイアウトやレイアウトデータオブジェクトの生成をサポートするクラスのためのインターフェースです。</br>
 * 
 * @author y-komori
 * 
 * @param <LAYOUT_TYPE>
 *            レイアウトのクラス
 * @param <LAYOUT_DATA_TYPE>
 *            レイアウトデータのクラス
 * @see org.eclipse.swt.widgets.Layout
 * @see org.eclipse.swt.widgets.Control#setLayoutData(java.lang.Object)
 */
public interface LayoutSupport<LAYOUT_TYPE extends Layout, LAYOUT_DATA_TYPE> {
    /**
     * レイアウトオブジェクトを生成します。</br>
     * 
     * @return レイアウトオブジェクト
     */
    public LAYOUT_TYPE createLayout();

    /**
     * サポート対象のレイアウトクラスを返します。</br>
     * 
     * @return レイアウトクラスの<code>Class</code>オブジェクト
     */
    public Class<LAYOUT_TYPE> getLayoutType();

    /**
     * レイアウトデータオブジェクトを生成します。</br> レイアウトデータを利用しないレイアウトの場合は、常に<code>null</code>を返します。
     * 
     * @return レイアウトデータオブジェクト
     */
    public LAYOUT_DATA_TYPE createLayoutData();

    /**
     * <code>ControlComponent</code>を指定して、レイアウトデータオブジェクトを生成します。</br>
     * レイアウトデータを利用しないレイアウトの場合は、常に<code>null</code>を返します。</br>
     * レイアウトデータオブジェクト生成時、<code>ControlComponent</code>が持つ<code>LayoutData</code>属性をレイアウトデータに設定します。
     * 
     * @param controlComponent
     *            <code>LayoutData</code>属性を使用する<code>ControlComponent</code>オブジェクト
     * @return レイアウトデータオブジェクト
     */
    public LAYOUT_DATA_TYPE createLayoutData(ControlComponent controlComponent);

    /**
     * レイアウト名称を返します。</br>
     * 
     * @return レイアウト名称
     */
    public String getLayoutName();
}
