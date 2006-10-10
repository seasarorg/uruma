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

import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.CoolItemComponent;

/**
 * <code>CoolItem</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class CoolItemRenderer extends
        AbstractWidgetRenderer<CoolItemComponent, CoolItem> {

    @Override
    protected void doRender(CoolItemComponent coolItemComponent, CoolItem control) {
    }
    
    @Override
    protected void doRenderAfter(CoolItem widget, CoolItemComponent uiComponent, Widget parent, WindowContext context) {
        setControl(widget, uiComponent);
    }
    
    private void setControl(CoolItem coolItem, CoolItemComponent coolItemComponent) {
        List<UIComponent> children = coolItemComponent.getChildren();
        if (children.size() > 0) {
            assert children.size() == 1;
            coolItem.setControl((Control) children.get(0).getWidget());
        }
    }

    @Override
    protected Class<CoolItem> getWidgetType() {
        return CoolItem.class;
    }
}
