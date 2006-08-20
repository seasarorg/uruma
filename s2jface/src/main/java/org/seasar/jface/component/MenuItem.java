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
package org.seasar.jface.component;

import org.xml.sax.Locator;

/**
 * メニューアイテムに関する情報を保持するオブジェクトのためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface MenuItem {
    public String getSourceLocation();

    public void setSourceLocation(Locator locator);

    public String getId();

    public void setId(String id);

    public MenuItemType getType();

    public void setType(MenuItemType type);

    public String getText();

    public void setText(String text);

    public String getDescription();

    public void setDescription(String description);

    public String getToolTipText();

    public void setToolTipText(String toolTipText);

    public String getImage();

    public void setImage(String image);

    public String getDisabledImage();

    public void setDisabledImage(String image);

    public String getHoverImage();

    public void setHoverImage(String image);

    public Boolean isChecked();

    public void setChecked(Boolean checked);

    public Boolean isEnabled();

    public void setEnabled(Boolean enabled);
}
