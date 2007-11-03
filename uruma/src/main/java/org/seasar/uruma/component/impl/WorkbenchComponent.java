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
package org.seasar.uruma.component.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIContainer;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.renderer.Renderer;
import org.seasar.uruma.util.AssertionUtil;

/**
 * ワークベンチのためのコンポーネントです。<br />
 * 
 * @author y-komori
 */
public class WorkbenchComponent extends AbstractUIElement implements
        UIContainer {
    private UIContainer parent;

    private List<UIComponent> children = new ArrayList<UIComponent>();

    public String title;

    public String image;

    public String style;

    public String initWidth;

    public String initHeight;

    public String statusLine;

    /*
     * @see org.seasar.uruma.component.UIContainer#addChild(org.seasar.uruma.component.UIComponent)
     */
    public void addChild(final UIComponent child) {
        AssertionUtil.assertNotNull("child", child);
        children.add(child);
    }

    /*
     * @see org.seasar.uruma.component.UIContainer#getChildren()
     */
    public List<UIComponent> getChildren() {
        return Collections.unmodifiableList(children);
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#getId()
     */
    public String getId() {
        return null;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#getParent()
     */
    public UIContainer getParent() {
        return this.parent;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#getRenderer()
     */
    public Renderer getRenderer() {
        return null;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#getStyle()
     */
    public String getStyle() {
        return this.style;
    }

    public WidgetHandle getWidgetHandle() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    public void preRender(final WidgetHandle parent, final PartContext context) {
        // TODO 自動生成されたメソッド・スタブ

    }

    public void render(final WidgetHandle parent, final PartContext context) {
        // TODO 自動生成されたメソッド・スタブ

    }

    /*
     * @see org.seasar.uruma.component.UIComponent#setId(java.lang.String)
     */
    public void setId(final String id) {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#setParent(org.seasar.uruma.component.UIContainer)
     */
    public void setParent(final UIContainer parent) {
        this.parent = parent;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#setRenderer(org.seasar.uruma.renderer.Renderer)
     */
    public void setRenderer(final Renderer renderer) {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#setStyle(java.lang.String)
     */
    public void setStyle(final String style) {
        this.style = style;
    }

    public void setWidgetHandle(final WidgetHandle handle) {
        // TODO 自動生成されたメソッド・スタブ

    }
}
