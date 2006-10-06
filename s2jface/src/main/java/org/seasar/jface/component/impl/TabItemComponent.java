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
package org.seasar.jface.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.ConversionType;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UIContainer;

/**
 * @author bskuroneko
 * 
 */
public class TabItemComponent extends AbstractItemComponent implements
        UIContainer {

    @ComponentAttribute(conversionType = ConversionType.TEXT)
    private String toolTipText;

    private List<UIComponent> children = new ArrayList<UIComponent>();

    public String getToolTipText() {
        return this.toolTipText;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }

    public void addChild(UIComponent child) {
        children.add(child);
    }

    public List<UIComponent> getChildren() {
        return children;
    }

    public UIComponent getChild() {
        if (children.size() > 0) {
            return children.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void render(Widget parent, WindowContext context) {
        UIComponent content = getChild();
        if (content != null) {
            content.render(getParent().getWidget(), context);
        }
        super.render(parent, context);
    }
}
