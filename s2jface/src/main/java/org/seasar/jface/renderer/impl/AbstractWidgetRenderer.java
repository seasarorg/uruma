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
package org.seasar.jface.renderer.impl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.renderer.RendererSupportUtil;
import org.seasar.jface.util.ClassUtil;
import org.seasar.jface.util.SWTUtil;

/**
 * <code>Widget</code> のレンダリングを行うための基底クラスです。<br />
 * 
 * @author bskuroneko
 */
public abstract class AbstractWidgetRenderer<COMPONENT_TYPE extends UIComponent, WIDGET_TYPE extends Widget>
        extends AbstractRenderer {

    public Widget render(UIComponent uiComponent, Composite parent,
            WindowContext context) {
        setContext(context);

        inherit((COMPONENT_TYPE) uiComponent);

        Widget widget = createWidget(parent, getStyle(uiComponent));
        RendererSupportUtil.setAttributes(uiComponent, widget);

        // TODO レンダリング中に発生したRuntimeExceptionのハンドリングが必要
        doRender((COMPONENT_TYPE) uiComponent, getWidgetType().cast(widget));

        widget.setData(this);

        return widget;
    }

    protected void inherit(COMPONENT_TYPE uiComponent) {
        // do nothing.
    }

    public void renderAfter(Widget widget, UIComponent uiComponent,
            Composite parent, WindowContext context) {
        // do nothing.
    }

    protected Widget createWidget(Composite parent, int style) {
        Class<? extends Widget> widgetClass = getWidgetType();
        Widget widget = ClassUtil.<Widget> newInstance(widgetClass, parent,
                style);
        return widget;
    }

    protected int getStyle(final UIComponent uiComponent) {
        return SWTUtil.getStyle(uiComponent.getStyle(), getDefaultStyle());
    }

    protected int getDefaultStyle() {
        return SWT.NONE;
    }

    protected abstract Class<WIDGET_TYPE> getWidgetType();

    protected abstract void doRender(COMPONENT_TYPE uiComponent,
            WIDGET_TYPE widget);
}
