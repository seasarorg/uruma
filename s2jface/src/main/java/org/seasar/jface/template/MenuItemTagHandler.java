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
import org.seasar.jface.component.MenuItem;
import org.seasar.jface.component.MenuItemType;
import org.seasar.jface.component.impl.MenuItemComponent;
import org.xml.sax.Attributes;

/**
 * @author y-komori
 * 
 */
public class MenuItemTagHandler extends AbstractTagHandler {

    private static final long serialVersionUID = 6310926726574728898L;

    protected static final String ITEM_ID_ATTR = "itemId";

    protected static final String TYPE_ATTR = "type";

    protected static final String LABEL_ATTR = "label";

    protected static final String IMAGE_ATTR = "image";

    protected static final String ACTION_COMPONENT_ATTR = "actionComponent";

    @Override
    public void start(TagHandlerContext context, Attributes attributes) {
        Menu parentMenu = (Menu) context.peek();

        MenuItem item = new MenuItemComponent();
        item.setId(attributes.getValue(ITEM_ID_ATTR));
        item.setType(MenuItemType.getMenuItemType(attributes
                .getValue(TYPE_ATTR)));
        item.setLabel(attributes.getValue(LABEL_ATTR));
        item.setImage(attributes.getValue(IMAGE_ATTR));
        item.setActionComponent(attributes.getValue(ACTION_COMPONENT_ATTR));

        parentMenu.addChild(item);
    }

    @Override
    protected String getElementName() {
        return "menuItem";
    }
}
