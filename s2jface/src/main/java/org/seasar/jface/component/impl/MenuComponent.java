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
import org.seasar.jface.util.LocatorUtil;
import org.xml.sax.Locator;

/**
 * @author y-komori
 * 
 */
public class MenuComponent implements Menu {
    protected String sourceLocation;

    protected String id;

    protected String text;

    protected Boolean enabled;

    protected Boolean visible;

    protected List<MenuItem> children = new ArrayList<MenuItem>();

    public String getSourceLocation() {
        return this.sourceLocation;
    }

    public void setSourceLocation(Locator locator) {
        this.sourceLocation = LocatorUtil.toString(locator);
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public Boolean isVisible() {
        return this.visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;

    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public MenuItemType getType() {
        return MenuItemType.MENU;
    }

    public void setType(MenuItemType type) {
        throw new UnsupportedOperationException();
    }

    public String getDescription() {
        throw new UnsupportedOperationException();
    }

    public void setDescription(String description) {
        throw new UnsupportedOperationException();
    }

    public String getToolTipText() {
        throw new UnsupportedOperationException();
    }

    public void setToolTipText(String toolTipText) {
        throw new UnsupportedOperationException();
    }

    public String getImage() {
        throw new UnsupportedOperationException();
    }

    public void setImage(String image) {
        throw new UnsupportedOperationException();
    }

    public String getDisabledImage() {
        throw new UnsupportedOperationException();
    }

    public void setDisabledImage(String image) {
        throw new UnsupportedOperationException();
    }

    public String getHoverImage() {
        throw new UnsupportedOperationException();
    }

    public void setHoverImage(String image) {
        throw new UnsupportedOperationException();
    }

    public Boolean isChecked() {
        throw new UnsupportedOperationException();
    }

    public void setChecked(Boolean checked) {
        throw new UnsupportedOperationException();
    }
}
