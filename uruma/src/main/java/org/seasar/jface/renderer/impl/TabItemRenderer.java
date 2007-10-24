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
package org.seasar.jface.renderer.impl;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.TabItemComponent;

/**
 * <code>TabItem</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class TabItemRenderer extends
        AbstractWidgetRenderer<TabItemComponent, TabItem> {

    @Override
    protected void doRender(TabItemComponent controlComponent, TabItem control) {
    }

    @Override
    protected void doRenderAfter(TabItem widget, TabItemComponent uiComponent,
            Widget parent, WindowContext context) {
        setControl(widget, uiComponent);
    }

    private void setControl(TabItem tabItem, TabItemComponent tabItemComponent) {
        UIComponent content = tabItemComponent.getChild();
        if (content != null) {
            tabItem.setControl((Control) content.getWidget());
        }
    }

    @Override
    protected Class<TabItem> getWidgetType() {
        return TabItem.class;
    }
}