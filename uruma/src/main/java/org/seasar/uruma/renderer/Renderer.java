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
package org.seasar.uruma.renderer;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;

/**
 * ウィジットをレンダリングするためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface Renderer {
    /**
     * レンダリングを行います。</br>
     * <p>
     * 本メソッドは、
     * {@link UIComponent#preRender(WidgetHandle, org.seasar.uruma.context.WindowContext)}
     * の内部から呼び出されます。<br />
     * </p>
     * 
     * @param uiComponent
     *        レンダリング対象の情報を持つ {@link UIComponent} オブジェクト
     * @param parent
     *        親となる {@link WidgetHandle} オブジェクト
     * @param context
     *        画面情報を収めた {@link WindowContext} オブジェクト
     * @return レンダリングしたウィジットのハンドル
     */
    public WidgetHandle preRender(UIComponent uiComponent, WidgetHandle parent,
            WindowContext context);

    /**
     * レンダリングを行います。</br>
     * 
     * @param uiComponent
     *        レンダリング対象の情報を持つ {@link UIComponent} オブジェクト
     * @param parent
     *        親となる {@link WidgetHandle} オブジェクト
     * @param context
     *        画面情報を収めた {@link PartContext} オブジェクト
     * @return レンダリングしたウィジットのハンドル
     */
    public WidgetHandle render(UIComponent uiComponent, WidgetHandle parent, PartContext context);

    /**
     * 子のレンダリングが終わった後に呼び出されるメソッドです。</br>
     * 
     * @param widget
     *        {@link Renderer#render(UIComponent, WidgetHandle, PartContext)
     *        render()} メソッドでレンダリングされた {@link WidgetHandle} オブジェクト
     * 
     * @param uiComponent
     *        レンダリング対象の情報を持つ {@link UIComponent} オブジェクト
     * @param parent
     *        親となる {@link WidgetHandle} オブジェクト
     * @param context
     *        画面情報を収めた {@link PartContext} オブジェクト
     */
    public void renderAfter(WidgetHandle widget, UIComponent uiComponent, WidgetHandle parent,
            PartContext context);

    /**
     * 一度レンダリングしたウィジットに対する再レンダリングを行います。<br />
     * 再レンダリングとは、 {@link UIComponent} が保持する情報をウィジットに対して再度反映させる処理のことです。<br />
     * 再レンダリングを行うには、変更したい属性に対応する {@link UIComponent} の属性を変更し、変更対象のウィジットと
     * {@link UIComponent} を保持する {@link WidgetHandle} を本メソッドの引数として渡してください。
     * 
     * @param widget
     *        再レンダリング対象のウィジットと {@link UIComponent} を保持する {@link WidgetHandle}
     *        オブジェクト
     * @param context
     *        画面情報を収めた {@link PartContext} オブジェクト
     */
    public void reRender(WidgetHandle widget, PartContext context);
}
