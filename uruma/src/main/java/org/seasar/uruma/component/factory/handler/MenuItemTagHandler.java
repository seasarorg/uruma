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
package org.seasar.uruma.component.factory.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.Menu;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.impl.MenuItemComponent;

/**
 * <code>menuItem</code> 要素に対するタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class MenuItemTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = -5380844837424058319L;

    /**
     * {@link MenuItemTagHandler} を構築します。
     */
    public MenuItemTagHandler() {
        super(MenuItemComponent.class);
    }

    @Override
    public String getElementPath() {
        return "menuItem";
    }

    @Override
    protected void setParent(final UIElement uiElement,
            final TagHandlerContext context) {
        MenuItemComponent menuItem = (MenuItemComponent) uiElement;
        Menu parent = (Menu) context.peek();
        parent.addMenuItem(menuItem);
        menuItem.setParentMenu(parent);
    }
}
