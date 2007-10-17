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

import java.util.List;

import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.CTabItemComponent;
import org.seasar.jface.renderer.RendererSupportUtil;

/**
 * <code>CTabItem</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class CTabItemRenderer extends
        AbstractWidgetRenderer<CTabItemComponent, CTabItem> {

    @Override
    protected void doRender(CTabItemComponent controlComponent, CTabItem control) {
        setFont(controlComponent, control);
    }

    private void setFont(CTabItemComponent controlComponent, CTabItem control) {
        if (controlComponent.getFontName() == null
                && controlComponent.getFontStyle() == null
                && controlComponent.getFontHeight() == null) {
            return;
        }
        Font font = RendererSupportUtil.getFont(control.getFont(),
                controlComponent.getFontName(),
                controlComponent.getFontStyle(), controlComponent
                        .getFontHeight());
        control.setFont(font);
    }

    @Override
    protected void doRenderAfter(CTabItem widget,
            CTabItemComponent uiComponent, Widget parent, WindowContext context) {
        setControl(widget, uiComponent);
    }

    private void setControl(CTabItem tabItem, CTabItemComponent tabItemComponent) {
        List<UIComponent> children = tabItemComponent.getChildren();
        if (children.size() > 0) {
            assert children.size() == 1;
            tabItem.setControl((Control) children.get(0).getWidget());
        }
    }

    @Override
    protected Class<CTabItem> getWidgetType() {
        return CTabItem.class;
    }
}
