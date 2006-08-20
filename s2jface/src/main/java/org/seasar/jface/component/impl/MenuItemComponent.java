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
import org.seasar.jface.util.LocatorUtil;
import org.xml.sax.Locator;

/**
 * @author y-komori
 */
public class MenuItemComponent implements MenuItem {
    protected String sourceLocation;

    protected String id;

    protected MenuItemType type;

    protected String text;

    protected String description;

    protected String toolTipText;

    protected String image;

    protected String disabledImage;

    protected String hoverImage;

    protected Boolean checked;

    protected Boolean enabled;

    public String getSourceLocation() {
        return sourceLocation;
    }

    public void setSourceLocation(Locator locator) {
        this.sourceLocation = LocatorUtil.toString(locator);
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

    public String getText() {
        return this.text;
    }

    public void setText(final String label) {
        this.text = label;
    }

    public MenuItemType getType() {
        return this.type;
    }

    public void setType(final MenuItemType type) {
        this.type = type;
    }

    public Boolean isChecked() {
        return this.checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisabledImage() {
        return this.disabledImage;
    }

    public void setDisabledImage(String disabledImage) {
        this.disabledImage = disabledImage;
    }

    public String getHoverImage() {
        return this.hoverImage;
    }

    public void setHoverImage(String hoverImage) {
        this.hoverImage = hoverImage;
    }

    public String getToolTipText() {
        return this.toolTipText;
    }

    public void setToolTipText(String toolTipText) {
        this.toolTipText = toolTipText;
    }
}
