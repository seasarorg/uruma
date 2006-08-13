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

import org.seasar.jface.component.MenuItem;
import org.seasar.jface.component.MenuItemType;

/**
 * @author y-komori
 */
public class MenuItemComponent implements MenuItem {
    protected String id;

    protected MenuItemType type;

    protected String label;

    protected String image;

    protected String actionComponent;

    public String getActionComponent() {
        return this.actionComponent;
    }

    public void setActionComponent(final String actionComponent) {
        this.actionComponent = actionComponent;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(final String image) {
        this.image = image;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(final String label) {
        this.label = label;
    }

    public MenuItemType getType() {
        return this.type;
    }

    public void setType(final MenuItemType type) {
        this.type = type;
    }
}
