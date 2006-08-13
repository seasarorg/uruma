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
package org.seasar.jface.template;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.Menu;
import org.seasar.jface.component.Window;
import org.seasar.jface.component.impl.MenuComponent;
import org.xml.sax.Attributes;

/**
 * @author y-komori
 * 
 */
public class MenuTagHandler extends AbstractTagHandler {

    private static final long serialVersionUID = -3982261589249592378L;

    protected static final String ID_ATTR = "id";

    protected static final String LABEL_ATTR = "label";

    @Override
    public void start(TagHandlerContext context, Attributes attributes) {
        Menu menu = new MenuComponent();
        menu.setId(attributes.getValue(ID_ATTR));
        menu.setLabel(attributes.getValue(LABEL_ATTR));
        context.push(menu);

        Object parent = context.peek();
        if (parent instanceof Window) {
            Window window = (Window) parent;
            if (window.getMenuBar() == null) {
                window.setMenuAsMenuBar(menu);
            } else {
                window.addMenu(menu);
            }
        } else if (parent instanceof Menu) {
            Menu parentMenu = (Menu) parent;
            parentMenu.addChild(menu);
        }
    }

    @Override
    public void end(TagHandlerContext context, String body) {
        context.pop();
    }

    @Override
    protected String getElementName() {
        return "menu";
    }
}
