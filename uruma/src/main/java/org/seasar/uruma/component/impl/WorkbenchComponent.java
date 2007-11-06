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
import java.util.List;

import org.eclipse.swt.SWT;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIContainer;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.renderer.Renderer;
import org.seasar.uruma.util.AssertionUtil;

/**
 * ワークベンチのためのコンポーネントです。<br />
 * 
 * @author y-komori
 */
public class WorkbenchComponent extends AbstractUIElement implements
        UIContainer {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(WorkbenchComponent.class);

    private UIContainer parent;

    private WidgetHandle widgetHandle;

    private String id;

    private List<UIComponent> children = new ArrayList<UIComponent>();

    /**
     * ワークベンチウィンドウのタイトルです。
     */
    public String title;

    /**
     * ワークベンチウィンドウに表示するアイコンのイメージパスです。
     */
    public String image;

    /**
     * ワークベンチウィンドウのスタイルです。
     * 
     * @see SWT
     */
    public String style;

    /**
     * ワークベンチウィンドウの初期ウィンドウ幅です。
     */
    public String initWidth;

    /**
     * ワークベンチウィンドウの初期ウィンドウ高さです。
     */
    public String initHeight;

    /**
     * ワークベンチのメニューバーに表示する <code>menu</code> 要素の ID です。
     */
    public String menu;

    /**
     * ステータスラインを表示/非表示を指定します。
     */
    public String statusLine;

    /**
     * 最初に表示するパースペクティブの ID です。
     */
    public String initialPerspectiveId;

    /*
     * @see org.seasar.uruma.component.UIContainer#addChild(org.seasar.uruma.component.UIComponent)
     */
    public void addChild(final UIComponent child) {
        AssertionUtil.assertNotNull("child", child);
        children.add(child);
        child.setParent(this);
    }

    /*
     * @see org.seasar.uruma.component.UIContainer#getChildren()
     */
    public List<UIComponent> getChildren() {
        return children;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#getId()
     */
    public String getId() {
        return this.id;
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
        return this.widgetHandle;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#preRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.WindowContext)
     */
    public void preRender(final WidgetHandle parent, final WindowContext context) {
        if (logger.isDebugEnabled()) {
            logger.log(UrumaMessageCodes.PRE_RENDER_START, this);
        }

        preRenderChild(parent, context);

        if (logger.isDebugEnabled()) {
            logger.log(UrumaMessageCodes.PRE_RENDER_END, this);
        }
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#render(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    public void render(final WidgetHandle parent, final PartContext context) {
        if (logger.isDebugEnabled()) {
            logger.log(UrumaMessageCodes.RENDER_START, this);
        }

        renderChild(parent, context);

        if (logger.isDebugEnabled()) {
            logger.log(UrumaMessageCodes.RENDER_END, this);
        }
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#setId(java.lang.String)
     */
    public void setId(final String id) {
        this.id = id;
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
        this.widgetHandle = handle;
    }

    /**
     * 子コンポーネントのレンダリングを行います。<br />
     * 
     * @param parent
     *            親 {@link WidgetHandle}
     * @param context
     *            {@link PartContext} オブジェクト
     */
    protected void renderChild(final WidgetHandle parent,
            final PartContext context) {
        for (UIComponent child : children) {
            child.render(parent, context);
        }
    }

    /**
     * 子コンポーネントのプリレンダリングを行います。<br />
     * 
     * @param parent
     *            親 {@link WidgetHandle}
     * @param context
     *            {@link WindowContext} オブジェクト
     */
    protected void preRenderChild(final WidgetHandle parent,
            final WindowContext context) {
        for (UIComponent child : children) {
            child.preRender(parent, context);
        }
    }
}
