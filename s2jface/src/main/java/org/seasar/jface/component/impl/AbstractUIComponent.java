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
package org.seasar.jface.component.impl;

import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.Menu;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UIContainer;
import org.seasar.jface.renderer.Renderer;
import org.seasar.jface.util.AssertionUtil;

/**
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

    private Menu menu;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // TODO 見直しが必要
    public String getReplace() {
        return this.replace;
    }

    // TODO 見直しが必要
    public void setReplace(String replace) {
        this.replace = replace;
    }

    public String getStyle() {
        return this.style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Renderer getRenderer() {
        return this.renderer;
    }

    public void setRenderer(Renderer renderer) {
        AssertionUtil.assertNotNull("renderer", renderer);
        this.renderer = renderer;
    }

    public Widget getWidget() {
        return this.widget;
    }

    public void setWidget(Widget widget) {
        AssertionUtil.assertNotNull("widget", widget);
        this.widget = widget;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void render(final Widget parent, final WindowContext context) {
        Widget widget = getRenderer().render(this, parent, context);
        setWidget(widget);

        if ((getId() != null) && (widget != null)) {
            context.putComponent(getId(), widget);
        }

        renderMenu(parent, context);
        doRender(parent, context);

        getRenderer().renderAfter(widget, this, parent, context);
    }

    public UIContainer getParent() {
        return parent;
    }

    public void setParent(UIContainer parent) {
        this.parent = parent;
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

    /**
     * レンダラ呼び出し中に独自のレンダリング処理を追加するためのメソッドです。<br />
     * <p>
     * 本メソッドは render メソッドの中で、レンダラの render() メソッドと renderAfter()
     * メソッドを呼び出す間に呼び出されます。
     * </p>
     * <p>
     * このタイミングでサブクラスで独自のレンダリング処理を行う場合、本メソッドをオーバーライドしてください。
     * </p>
     * 
     * @param parent
     *            親 {@link Widget} オブジェクト
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    protected void doRender(final Widget parent, final WindowContext context) {
    }
}
