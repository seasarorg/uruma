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
package org.seasar.jface.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute;
import org.seasar.jface.annotation.component.ComponentAttribute.TargetType;
import org.seasar.jface.component.EnabledDependable;
import org.seasar.jface.component.Menu;
import org.seasar.jface.component.MenuItem;
import org.seasar.jface.component.UIComponent;

/**
 * メニュー情報を保持するためのコンポーネントです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
public class MenuComponent extends MenuItemComponent implements Menu,
        EnabledDependable {

    @ComponentAttribute(targetType = TargetType.NONE)
    private String defaultItemId;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String enabled;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String x;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String y;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String enabledDependId;

    @ComponentAttribute(targetType = TargetType.NONE)
    private String enabledDependType;

    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    private UIComponent menuHolder;

    public String getDefaultItemId() {
        return this.defaultItemId;
    }

    public void setDefaultItemId(String defaultItemId) {
        this.defaultItemId = defaultItemId;
    }

    public String getEnabled() {
        return this.enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getX() {
        return this.x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return this.y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItemList() {
        return menuItems;
    }

    public UIComponent getMenuHolder() {
        return this.menuHolder;
    }

    public void setMenuHolder(UIComponent menuHolder) {
        this.menuHolder = menuHolder;
    }

    public String getEnabledDependId() {
        return this.enabledDependId;
    }

    public void setEnabledDependId(String enabledDependId) {
        this.enabledDependId = enabledDependId;
    }

    public String getEnabledDependType() {
        return this.enabledDependType;
    }

    public void setEnabledDependType(String enabledDependType) {
        this.enabledDependType = enabledDependType;
    }

    @Override
    protected void doRender(Widget parent, WindowContext context) {
        for (MenuItem menuItem : menuItems) {
            menuItem.render(getWidget(), context);
        }
    }
}
