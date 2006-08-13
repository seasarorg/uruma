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
import java.util.Iterator;
import java.util.List;

import org.seasar.jface.component.Menu;
import org.seasar.jface.component.MenuItem;
import org.seasar.jface.component.MenuItemType;

/**
 * @author y-komori
 * 
 */
public class MenuComponent implements Menu {
    protected String id;

    protected String label;

    protected String image;

    protected List<MenuItem> children = new ArrayList<MenuItem>();

    public void setId(final String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public void addChildMenu(Menu menu) {
        this.children.add(menu);
    }

    public MenuItem findMenuItem(String id) {
        for (MenuItem menu : children) {
            String childId = menu.getId();
            if ((childId != null) && (childId.equals(id))) {
                return menu;
            }
        }
        return null;
    }

    public Iterator<MenuItem> iterator() {
        return children.iterator();
    }

    public void addChild(MenuItem menuItem) {
        this.children.add(menuItem);
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public MenuItemType getType() {
        return MenuItemType.MENU;
    }

    public void setType(MenuItemType type) {
        throw new UnsupportedOperationException();
    }

    public String getActionComponent() {
        throw new UnsupportedOperationException();
    }

    public void setActionComponent(String actionComponent) {
        throw new UnsupportedOperationException();
    }
}
