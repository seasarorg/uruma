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
package org.seasar.jface.component.impl;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.Menu;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UIContainer;
import org.seasar.jface.renderer.Renderer;
import org.seasar.jface.util.AssertionUtil;
import org.seasar.jface.viewer.ViewerAdapter;

/**
 * {@link UIComponent} を表す基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractUIComponent extends AbstractUIElement implements
        UIComponent {
    private UIContainer parent;

    private String id;

    private String replace;

    private String style;

    private Renderer renderer;

    private Widget widget;

    private Viewer viewer;

    private Menu menu;

    /**
     * レンダラ呼び出し中に独自のレンダリング処理を追加するためのメソッドです。<br />
     * <p>
     * 本メソッドは {@link AbstractUIComponent#renderer} メソッドの中で、{@link Renderer レンダラ}
     * の {@link Renderer#render(UIComponent, Widget, WindowContext) render()}
     * メソッドと
     * {@link Renderer#renderAfter(Widget, UIComponent, Widget, WindowContext) renderAfter()}
     * メソッドを呼び出す間に呼び出されます。<br />
     * </p>
     * <p>
     * このタイミングでサブクラスで独自のレンダリング処理を行う場合、本メソッドをオーバーライドしてください。<br />
     * </p>
     * 
     * @param parent
     *            親 {@link Widget} オブジェクト
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    protected void doRender(final Widget parent, final WindowContext context) {
    }

    /*
     * @see org.seasar.jface.component.UIComponent#getId()
     */
    public String getId() {
        return this.id;
    }

    public Menu getMenu() {
        return menu;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#getParent()
     */
    public UIContainer getParent() {
        return parent;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#getRenderer()
     */
    public Renderer getRenderer() {
        return this.renderer;
    }

    // TODO 見直しが必要
    public String getReplace() {
        return this.replace;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#getStyle()
     */
    public String getStyle() {
        return this.style;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#getViewer()
     */
    public Viewer getViewer() {
        return this.viewer;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#getWidget()
     */
    public Widget getWidget() {
        return this.widget;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#render(org.eclipse.swt.widgets.Widget,
     *      org.seasar.jface.WindowContext)
     */
    public void render(final Widget parent, final WindowContext context) {
        Widget widget = getRenderer().render(this, parent, context);
        setWidget(widget);

        Viewer viewer = null;
        ViewerAdapter<?> viewerAdapter = context.getViewerAdapter(widget);
        if (viewerAdapter != null) {
            viewer = (Viewer) viewerAdapter.getViewer();
        }

        // レンダラが Viewer を生成していれば優先的に WindowContext へセットする
        String id = getId();
        if (id != null) {
            if (viewer != null) {
                context.putViewerComponent(id, viewer);
            } else if (widget != null) {
                context.putComponent(id, widget);
            }
        }

        renderMenu(parent, context);
        doRender(parent, context);

        getRenderer().renderAfter(widget, this, parent, context);
    }

    protected void renderMenu(final Widget parent, final WindowContext context) {
        if (menu != null) {
            if (parent instanceof Decorations) {
                menu.render(parent, context);
            } else {
                menu.render(widget, context);
            }
        }
    }

    /*
     * @see org.seasar.jface.component.UIComponent#setId(java.lang.String)
     */
    public void setId(String id) {
        this.id = id;
    }

    public void setMenu(final Menu menu) {
        this.menu = menu;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#setParent(org.seasar.jface.component.UIContainer)
     */
    public void setParent(final UIContainer parent) {
        this.parent = parent;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#setRenderer(org.seasar.jface.renderer.Renderer)
     */
    public void setRenderer(final Renderer renderer) {
        AssertionUtil.assertNotNull("renderer", renderer);
        this.renderer = renderer;
    }

    // TODO 見直しが必要
    public void setReplace(final String replace) {
        this.replace = replace;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#setStyle(java.lang.String)
     */
    public void setStyle(final String style) {
        this.style = style;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#setViewer(org.eclipse.jface.viewers.Viewer)
     */
    public void setViewer(final Viewer viewer) {
        AssertionUtil.assertNotNull("viewer", viewer);
        this.viewer = viewer;
    }

    /*
     * @see org.seasar.jface.component.UIComponent#setWidget(org.eclipse.swt.widgets.Widget)
     */
    public void setWidget(final Widget widget) {
        AssertionUtil.assertNotNull("widget", widget);
        this.widget = widget;
    }
}
