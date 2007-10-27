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

import org.seasar.uruma.component.impl.MenuComponent;

/**
 * <code>menu</code> 要素に対するタグハンドラです。<br/>
 * 
 * @author y-komori
 */
public class MenuTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = 5143956606600001623L;

    /**
     * {@link MenuTagHandler} を構築します。
     */
    public MenuTagHandler() {
        super(MenuComponent.class);
    }

    @Override
    public String getElementPath() {
        return "menu";
    }

    // @Override
    // protected void setParent(final UIElement uiElement,
    // final TagHandlerContext context) {
    // Menu menu = (Menu) uiElement;
    // Object parent = context.peek();
    // if (parent instanceof Menu) {
    // Menu parentMenu = (Menu) parent;
    // parentMenu.addMenuItem(menu);
    // menu.setParentMenu(parentMenu);
    // } else if (parent instanceof UIComponent) {
    // UIComponent parentComponent = (UIComponent) parent;
    // parentComponent.setMenu(menu);
    // menu.setMenuHolder(parentComponent);
    // }
    // }
}
