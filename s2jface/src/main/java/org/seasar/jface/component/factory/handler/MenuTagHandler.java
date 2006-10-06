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
package org.seasar.jface.component.factory.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.Menu;
import org.seasar.jface.component.MenuItem;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UIElement;
import org.seasar.jface.component.impl.MenuComponent;

/**
 * @author y-komori
 * 
 */
public class MenuTagHandler extends S2JFaceGenericTagHandler {

    private static final long serialVersionUID = 5143956606600001623L;

    public MenuTagHandler() {
        super(MenuComponent.class);
    }

    @Override
    public String getElementPath() {
        return "menu";
    }

    @Override
    protected void setParent(UIElement uiElement, TagHandlerContext context) {
        Menu menu = (Menu) uiElement;
        Object parent = context.peek();
        if (parent instanceof MenuItem) {
            MenuItem parentMenuItem = (MenuItem) parent;
            parentMenuItem.setChildMenu(menu);
            menu.setParentMenuItem(parentMenuItem);
        } else if (parent instanceof UIComponent) {
            UIComponent parentComponent = (UIComponent) parent;
            parentComponent.setMenu(menu);
            menu.setMenuHolder(parentComponent);
        }
    }
}
