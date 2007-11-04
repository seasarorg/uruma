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
package org.seasar.uruma.component;

import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.renderer.Renderer;
import org.seasar.uruma.ui.UrumaApplicationWindow;

/**
 * レンダリング可能な画面要素を表すインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface UIComponent extends UIElement {
    /**
     * ID を取得します。<br />
     * 
     * @return ID
     */
    public String getId();

    /**
     * ID を設定します。<br />
     * 
     * @param id
     *            ID
     */
    public void setId(String id);

    /**
     * スタイルを表す文字列を取得します。<br />
     * 
     * @return スタイル
     */
    public String getStyle();

    /**
     * スタイルを表す文字列を設定します。<br />
     * 
     * @param style
     *            スタイル
     */
    public void setStyle(String style);

    /**
     * 親となる {@link UICompositeComponent} を設定します。<br />
     * 
     * @param parent
     *            親コンポーネント
     */
    public void setParent(UIContainer parent);

    /**
     * 親となる {@link UICompositeComponent} を取得します。<br />
     * 
     * @return 親コンポーネント
     */
    public UIContainer getParent();

    /**
     * 本コンポーネントに対応する {@link WidgetHandle} を取得します。<br />
     * <p>
     * {@link WidgetHandle} が設定されていない場合は、<code>null</code> を返します。<br />
     * </p>
     * 
     * @return {@link WidgetHandle} オブジェクト
     */
    public WidgetHandle getWidgetHandle();

    /**
     * 本コンポーネントに対応する {@link WidgetHandle} を設定します。<br />
     * 
     * @param handle
     *            {@link WidgetHandle} オブジェクト
     */
    public void setWidgetHandle(WidgetHandle handle);

    /**
     * レンダラを取得します。
     * 
     * @return レンダラオブジェクト
     */
    public Renderer getRenderer();

    /**
     * レンダラを設定します。<br />
     * 
     * @param renderer
     *            レンダラオブジェクト
     */
    public void setRenderer(Renderer renderer);

    /**
     * 設定されたレンダラを利用して、レンダリングを行います。<br />
     * 本メソッドは、シェルが生成される前のタイミングで呼び出されます。<br />
     * <p>
     * 具体的には以下のタイミングです。
     * <dl>
     * <dt> {@link UrumaApplicationWindow} の場合
     * <dd> {@link UrumaApplicationWindow}<code>#init()</code> メソッド内 (<code>createContent()</code>
     * メソッドよりも前のタイミング
     * </dl>
     * </p>
     * 
     * @param parent
     *            親となる {@link WidgetHandle} オブジェクト
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    public void preRender(WidgetHandle parent, WindowContext context);

    /**
     * 設定されたレンダラを利用して、レンダリングを行います。</br> 本メソッドは、 {@link UrumaApplicationWindow}<code>#createContents()</code>
     * メソッドの中で呼び出されます。<br />
     * 
     * @param parent
     *            親となる {@link WidgetHandle} オブジェクト
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public void render(WidgetHandle parent, PartContext context);
}
