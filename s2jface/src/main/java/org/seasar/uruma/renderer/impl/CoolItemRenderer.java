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

import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolItem;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.impl.CoolItemComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link CoolItem} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class CoolItemRenderer extends
        AbstractWidgetRenderer<CoolItemComponent, CoolItem> {

    @Override
    protected void doRender(final CoolItemComponent coolItemComponent,
            final CoolItem control) {
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRenderAfter(org.eclipse.swt.widgets.Widget,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRenderAfter(final CoolItem widget,
            final CoolItemComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        setControl(widget, uiComponent);
    }

    private void setControl(final CoolItem coolItem,
            final CoolItemComponent coolItemComponent) {
        List<UIComponent> children = coolItemComponent.getChildren();
        if (children.size() > 0) {
            WidgetHandle handle = children.get(0).getWidgetHandle();
            coolItem.setControl((Control) handle.getWidget());
        }
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#getWidgetType()
     */
    @Override
    protected Class<CoolItem> getWidgetType() {
        return CoolItem.class;
    }
}
