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
package org.seasar.uruma.context.impl;

import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link WidgetHandle} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class WidgetHandleImpl implements WidgetHandle {
    private String id;

    private Object widget;

    private UIComponent uiComponent;

    /**
     * {@link WidgetHandleImpl} を構築します。<br />
     * 本メソッドは <code>protected</code> 属性のため、直接生成できません。<br />
     * 生成するには、{@link ContextFactory#createWidgetHandle(Object, UIComponent)}
     * メソッドを利用してください。<br />
     * 
     * @param widget
     *            ウィジットオブジェクト
     * @param uiComponent
     *            {@link UIComponent} オブジェクト
     */
    protected WidgetHandleImpl(final Object widget,
            final UIComponent uiComponent) {
        AssertionUtil.assertNotNull("widget", widget);
        AssertionUtil.assertNotNull("uiComponent", uiComponent);
        String id = uiComponent.getId();
        AssertionUtil.assertNotEmpty("id", id);

        this.id = id;
        this.widget = widget;
        this.uiComponent = uiComponent;
    }

    /*
     * @see org.seasar.uruma.context.WidgetHandle#getId()
     */
    public String getId() {
        return this.id;
    }

    /*
     * @see org.seasar.uruma.context.WidgetHandle#getWidget()
     */
    public Object getWidget() {
        return this.widget;
    }

    /*
     * @see org.seasar.uruma.context.WidgetHandle#getCastWidget()
     */
    @SuppressWarnings("unchecked")
    public <T> T getCastWidget() {
        return (T) this.getClass().cast(this.getWidget());
    }

    /*
     * @see org.seasar.uruma.context.WidgetHandle#getWidgetClass()
     */
    public Class<?> getWidgetClass() {
        return widget.getClass();
    }

    /*
     * @see org.seasar.uruma.context.WidgetHandle#setId(java.lang.String)
     */
    public void setId(final String id) {
        AssertionUtil.assertNotNull("id", id);
        this.id = id;
    }

    /*
     * @see org.seasar.uruma.context.WidgetHandle#getUiComponent()
     */
    public UIComponent getUiComponent() {
        return this.uiComponent;
    }

    /*
     * @see org.seasar.uruma.context.WidgetHandle#setUiComponent(org.seasar.uruma.component.UIComponent)
     */
    public void setUiComponent(final UIComponent uiComponent) {
        this.uiComponent = uiComponent;
    }

    /*
     * @see org.seasar.uruma.context.WidgetHandle#instanceOf(java.lang.Class)
     */
    public boolean instanceOf(final Class<?> clazz) {
        if (clazz != null) {
            return clazz.isAssignableFrom(widget.getClass());
        } else {
            throw new IllegalArgumentException();
        }
    }
}
