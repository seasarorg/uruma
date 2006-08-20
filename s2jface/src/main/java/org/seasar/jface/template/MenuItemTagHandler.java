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

import org.seasar.framework.util.BooleanConversionUtil;
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

    protected static final String ID_ATTR = "id";

    protected static final String TYPE_ATTR = "type";

    protected static final String TEXT_ATTR = "text";

    protected static final String DESCRIPTION_ATTR = "description";

    protected static final String TOOL_TIP_TEXT_ATTR = "toolTipText";

    protected static final String IMAGE_ATTR = "image";

    protected static final String DISABLED_IMAGE_ATTR = "disabledImage";

    protected static final String HOVER_IMAGE_ATTR = "hoverImage";

    protected static final String CHECKED_ATTR = "checked";

    protected static final String ENABLED_ATTR = "enabled";

    @Override
    public void start(TagHandlerContext context, Attributes attributes) {
        Menu parentMenu = (Menu) context.peek();

        MenuItem item = new MenuItemComponent();
        item.setSourceLocation(context.getLocator());
        item.setId(attributes.getValue(ID_ATTR));
        item.setType(MenuItemType.getMenuItemType(attributes
                .getValue(TYPE_ATTR)));
        item.setText(attributes.getValue(TEXT_ATTR));
        item.setDescription(attributes.getValue(DESCRIPTION_ATTR));
        item.setToolTipText(attributes.getValue(TOOL_TIP_TEXT_ATTR));
        item.setImage(attributes.getValue(IMAGE_ATTR));
        item.setDisabledImage(attributes.getValue(DISABLED_IMAGE_ATTR));
        item.setHoverImage(attributes.getValue(HOVER_IMAGE_ATTR));
        item.setEnabled(BooleanConversionUtil.toBoolean(attributes
                .getValue(ENABLED_ATTR)));
        item.setChecked(BooleanConversionUtil.toBoolean(attributes
                .getValue(CHECKED_ATTR)));

        parentMenu.addChild(item);
    }

    @Override
    protected String getElementName() {
        return "menuItem";
    }
}
