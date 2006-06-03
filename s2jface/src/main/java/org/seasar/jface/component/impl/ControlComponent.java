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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.Item;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 * 
 */
public class ControlComponent extends UIComponentBase {
    public static final String X_PROP = "x";

    public static final String Y_PROP = "y";

    public static final String WIDTH_PROP = "width";

    public static final String HEIGHT_PROP = "height";

    public static final String ORDER_PROP = "order";

    public static final String FOREGROUND_COLOR_PROP = "foregroundColor";

    public static final String BACKGROUND_COLOR_PROP = "backgroundColor";

    public static final String ENABLED_PROP = "enabled";

    public static final String VISIBLE_PROP = "visible";

    public static final String FONT_PROP = "font";

    public static final String FONT_SIZE_PROP = "fontSize";

    public static final String FONT_STYLE_PROP = "fontStyle";

    public static final String TOOL_TIP_PROP = "toolTip";

    public static final String ACCESS_KEY_PROP = "accessKey";

    public static final String FOCUS_ORDER_PROP = "focusOrder";

    private String style;

    private Map<String, String> layoutData = new HashMap<String, String>();

    private List<Item> itemList = new ArrayList<Item>();

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void addLayoutData(final String name, final String value) {
        layoutData.put(name, value);
    }

    public String getLayoutData(final String name) {
        return layoutData.get(name);
    }

    public int getLayoutDataNum() {
        return layoutData.size();
    }

    public Collection<String> getLayoutDataNames() {
        return layoutData.keySet();
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void addItem(final Item item) {
        itemList.add(item);
    }

    public void render(final Composite parent, final WindowContext context) {
        AssertionUtil.assertNotNull("renderer", renderer);
        Widget widget = renderer.render(this, parent, context);

        if (getId() != null) {
            context.putComponent(getId(), widget);
        }
    }
}
