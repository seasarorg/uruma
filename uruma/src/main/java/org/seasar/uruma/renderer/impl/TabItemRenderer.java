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
package org.seasar.uruma.renderer.impl;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabItem;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.impl.TabItemComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link TabItem} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TabItemRenderer extends
        AbstractWidgetRenderer<TabItemComponent, TabItem> {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRender(org.seasar.uruma.component.UIComponent,
     *      org.eclipse.swt.widgets.Widget)
     */
    @Override
    protected void doRender(final TabItemComponent controlComponent,
            final TabItem control) {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRenderAfter(org.eclipse.swt.widgets.Widget,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRenderAfter(final TabItem widget,
            final TabItemComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        setControl(widget, uiComponent);
    }

    private void setControl(final TabItem tabItem,
            final TabItemComponent tabItemComponent) {
        UIElement content = tabItemComponent.getChild();
        if ((content != null) && (content instanceof UIComponent)) {
            String id = ((UIComponent) content).getId();
            WidgetHandle handle = getContext().getWidgetHandle(id);
            if (handle.instanceOf(Control.class)) {
                Control control = handle.<Control> getCastWidget();
                tabItem.setControl(control);
            }
        }
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<TabItem> getWidgetType() {
        return TabItem.class;
    }
}
