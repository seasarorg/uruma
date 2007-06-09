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
package org.seasar.jface.component.factory.handler;

import org.seasar.jface.component.UIElement;
import org.seasar.jface.component.impl.MenuItemComponent;
import org.xml.sax.Attributes;

/**
 * <code>separator</code> タグに対するタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class SeparatorTagHandler extends MenuItemTagHandler {

    private static final long serialVersionUID = 7033038411653634637L;

    /**
     * {@link SeparatorTagHandler} を構築します。<br />
     */
    public SeparatorTagHandler() {
        super();
    }

    @Override
    public String getElementPath() {
        return "separator";
    }

    @Override
    protected void setAttributes(UIElement uiElement, Attributes attributes) {
        super.setAttributes(uiElement, attributes);
        MenuItemComponent menuItemComponent = (MenuItemComponent) uiElement;
        menuItemComponent.setStyle("SEPARATOR");
    }
}
