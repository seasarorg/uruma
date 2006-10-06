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
package org.seasar.jface.component;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.renderer.Renderer;

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
     * 親となる {@link UICompositeComponent} を設定します。
     * 
     * @param parent
     *            親コンポーネント
     */
    public void setParent(UIContainer parent);

    /**
     * 親となる {@link UICompositeComponent} を取得します。
     * 
     * @return 親コンポーネント
     */
    public UIContainer getParent();

    /**
     * 本コンポーネントに対応する SWT の {@link Widget} を取得します。
     * 
     * @return {@link Widget} オブジェクト
     */
    public Widget getWidget();

    /**
     * 本コンポーネントに対応する SWT の {@link Widget} を設定します。
     * 
     * @param widget
     *            {@link Widget} オブジェクト
     */
    public void setWidget(Widget widget);

    /**
     * メニューを取得します。<br />
     * 
     * @return メニュー
     */
    public Menu getMenu();

    /**
     * メニューを設定します。<br />
     * 
     * @param menu
     *            メニュー
     */
    public void setMenu(Menu menu);

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
     * 設定されたレンダラを利用して、レンダリングを行います。</br>
     * 
     * @param parent
     *            親となる <code>Widget</code> オブジェクト
     * @param context
     *            <code>WindowContext</code> オブジェクト
     * @see WindowContext
     */
    public void render(Widget parent, WindowContext context);
}
