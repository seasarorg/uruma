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
package org.seasar.uruma.renderer.layout;

import org.eclipse.swt.widgets.Layout;
import org.seasar.uruma.component.LayoutDataInfo;
import org.seasar.uruma.component.LayoutInfo;
import org.seasar.uruma.component.UIComponent;

/**
 * SWTにおけるレイアウトやレイアウトデータオブジェクトの生成をサポートするクラスのためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface LayoutSupport {
    /**
     * レイアウトオブジェクトを生成します。<br />
     * 
     * @return レイアウトオブジェクト
     */
    public Layout createLayout();

    /**
     * {@link LayoutInfo} オブジェクトを元にして、レイアウトオブジェクトを生成します。<br />
     * 
     * @param layoutInfo
     *            {@link LayoutInfo} オブジェクト
     * @return レイアウトオブジェクト
     */
    public Layout createLayout(LayoutInfo<?> layoutInfo);

    /**
     * レイアウトデータオブジェクトを生成します。<br />
     * レイアウトデータを利用しないレイアウトの場合は、常に <code>null</code> を返します。
     * 
     * @return レイアウトデータオブジェクト
     */
    public Object createLayoutData();

    /**
     * {@link LayoutDataInfo} オブジェクトを元にして、レイアウトデータオブジェクトを生成します。</br>
     * レイアウトデータを利用しないレイアウトの場合は、常に <code>null</code> を返します。</br>
     * 
     * @param uiComponent
     *            {@link UIComponent} オブジェクト
     * @param layoutDataInfo
     *            {@link LayoutDataInfo} オブジェクト
     * @return レイアウトデータオブジェクト
     */
    public Object createLayoutData(UIComponent uiComponent,
            LayoutDataInfo layoutDataInfo);
}
