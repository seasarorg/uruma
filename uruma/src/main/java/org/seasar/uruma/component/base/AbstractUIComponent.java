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
package org.seasar.uruma.component.base;

import org.seasar.uruma.annotation.ComponentAttribute;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.renderer.Renderer;
import org.seasar.uruma.renderer.RendererExtender;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link UIComponent} を表す基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractUIComponent extends AbstractUIElement implements UIComponent {
    private UrumaLogger logger = UrumaLogger.getLogger(getClass());

    private UIComponentContainer parent;

    @ComponentAttribute
    private String id;

    @ComponentAttribute
    private String style;

    private Renderer renderer;

    /**
     * レンダラ呼び出し中に独自のレンダリング処理を追加するためのメソッドです。<br />
     * <p>
     * 本メソッドは {@link AbstractUIComponent#preRender(WidgetHandle, WindowContext)}
     * メソッドの中で、{@link Renderer レンダラ} の
     * {@link Renderer#preRender(UIComponent, WidgetHandle, WindowContext)}
     * メソッドを呼び出した後に呼び出されます。<br />
     * </p>
     * <p>
     * このタイミングでサブクラスで独自のレンダリング処理を行う場合、本メソッドをオーバーライドしてください。<br />
     * </p>
     * 
     * @param parent
     *        親 {@link WidgetHandle} オブジェクト
     * @param context
     *        {@link WindowContext} オブジェクト
     */
    protected void doPreRender(final WidgetHandle parent, final WindowContext context) {
    }

    /**
     * レンダラ呼び出し中に独自のレンダリング処理を追加するためのメソッドです。<br />
     * <p>
     * 本メソッドは {@link AbstractUIComponent#renderer} メソッドの中で、{@link Renderer レンダラ}
     * の {@link Renderer#render(UIComponent, WidgetHandle, PartContext)
     * render()} メソッドと
     * {@link Renderer#renderAfter(WidgetHandle, UIComponent, WidgetHandle, PartContext)
     * renderAfter()} メソッドを呼び出す間に呼び出されます。<br />
     * </p>
     * <p>
     * このタイミングでサブクラスで独自のレンダリング処理を行う場合、本メソッドをオーバーライドしてください。<br />
     * </p>
     * 
     * @param parent
     *        親 {@link WidgetHandle} オブジェクト
     * @param context
     *        {@link PartContext} オブジェクト
     */
    protected void doRender(final WidgetHandle parent, final PartContext context) {
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#preRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.WindowContext)
     */
    public void preRender(final WidgetHandle parent, final WindowContext context) {
        if (logger.isDebugEnabled()) {
            logger.log(UrumaMessageCodes.PRE_RENDER_START, this);
        }

        setupId();

        WidgetHandle handle = getRenderer().preRender(this, parent, context);
        if (handle != null) {
            context.putWidgetHandle(handle);
        }

        doPreRender(parent, context);

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

        setupId();

        WidgetHandle handle = getRenderer().render(this, parent, context);
        if (handle != null) {
            context.putWidgetHandle(handle);
        }

        doRender(parent, context);

        RendererExtender.doExtRender(this, handle, context);

        getRenderer().renderAfter(handle, this, parent, context);

        if (logger.isDebugEnabled()) {
            logger.log(UrumaMessageCodes.RENDER_END, this);
        }
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
    public UIComponentContainer getParent() {
        return parent;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#getRenderer()
     */
    public Renderer getRenderer() {
        return this.renderer;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#getStyle()
     */
    public String getStyle() {
        return this.style;
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
    public void setParent(final UIComponentContainer parent) {
        this.parent = parent;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#setRenderer(org.seasar.uruma.renderer.Renderer)
     */
    public void setRenderer(final Renderer renderer) {
        AssertionUtil.assertNotNull("renderer", renderer);
        this.renderer = renderer;
    }

    /*
     * @see org.seasar.uruma.component.UIComponent#setStyle(java.lang.String)
     */
    public void setStyle(final String style) {
        this.style = style;
    }

    private void setupId() {
        if (this.id == null) {
            setId(getClass().getName() + UrumaConstants.AT_MARK + Integer.toHexString(hashCode()));
        }
    }

    /*
     * @see org.seasar.uruma.component.base.AbstractUIElement#toString()
     */
    @Override
    public String toString() {
        return getURL().toString() + " id:" + getId();
    }
}
