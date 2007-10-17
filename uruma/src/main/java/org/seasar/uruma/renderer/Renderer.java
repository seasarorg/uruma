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
package org.seasar.uruma.renderer;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * ウィジットをレンダリングするためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface Renderer {
    /**
     * レンダリングを行います。</br>
     * 
     * @param uiComponent
     *            レンダリング対象の情報を持つ {@link UIComponent} オブジェクト
     * @param parent
     *            親となる {@link WidgetHandle} オブジェクト
     * @param context
     *            画面情報を収めた {@link PartContext} オブジェクト
     * @return レンダリングしたウィジットのハンドル
     */
    public WidgetHandle render(UIComponent uiComponent, WidgetHandle parent,
            PartContext context);

    /**
     * 子のレンダリングが終わった後に呼び出されるメソッドです。</br>
     * 
     * @param widget
     *            {@link Renderer#render(UIComponent, WidgetHandle, PartContext) render()}
     *            メソッドでレンダリングされた {@link WidgetHandle} オブジェクト
     * 
     * @param uiComponent
     *            レンダリング対象の情報を持つ {@link UIComponent} オブジェクト
     * @param parent
     *            親となる {@link WidgetHandle} オブジェクト
     * @param context
     *            画面情報を収めた {@link PartContext} オブジェクト
     */
    public void renderAfter(WidgetHandle widget, UIComponent uiComponent,
            WidgetHandle parent, PartContext context);
}
