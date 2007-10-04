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
package org.seasar.uruma.component.impl;

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.annotation.FieldDescription;
import org.seasar.uruma.annotation.RenderingPolicy;
import org.seasar.uruma.annotation.RenderingPolicy.TargetType;
import org.seasar.uruma.component.Menu;
import org.seasar.uruma.component.MenuItem;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * メニュー情報を保持するためのコンポーネントです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
public class MenuComponent extends MenuItemComponent implements Menu {

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("デフォルトアイテムID")
    private String defaultItemId;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("イネーブル状態")
    private String enabled;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("X 座標")
    private String x;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("Y 座標")
    private String y;

    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    private UIComponent menuHolder;

    /**
     * デフォルトアイテムIDを取得します。<br />
     * 
     * @return デフォルトアイテムID
     */
    public String getDefaultItemId() {
        return this.defaultItemId;
    }

    /**
     * デフォルトアイテムIDを設定します。<br />
     * 
     * @param defaultItemId デフォルトアイテムID
     */
    public void setDefaultItemId(final String defaultItemId) {
        this.defaultItemId = defaultItemId;
    }

    @Override
    /**
     * イネーブル状態を取得します。<br />
     * 
     * @return イネーブル状態
     */
    public String getEnabled() {
        return this.enabled;
    }

    @Override
    /**
     * イネーブル状態を設定します。<br />
     * 
     * @param enabled イネーブル状態
     */
    public void setEnabled(final String enabled) {
        this.enabled = enabled;
    }

    /**
     * X 座標を取得します。<br />
     * 
     * @return X 座標
     */
    public String getX() {
        return this.x;
    }

    /**
     * X 座標を設定します。<br />
     * 
     * @param x X 座標
     */
    public void setX(final String x) {
        this.x = x;
    }

    /**
     * Y 座標を取得します。<br />
     * 
     * @return Y 座標
     */
    public String getY() {
        return this.y;
    }

    /**
     * Y 座標を設定します。<br />
     * 
     * @param y Y 座標
     */
    public void setY(final String y) {
        this.y = y;
    }

    public void addMenuItem(final MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public List<MenuItem> getMenuItemList() {
        return menuItems;
    }

    public UIComponent getMenuHolder() {
        return this.menuHolder;
    }

    public void setMenuHolder(final UIComponent menuHolder) {
        this.menuHolder = menuHolder;
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractUIComponent#doRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRender(final WidgetHandle parent, final PartContext context) {
        for (MenuItem menuItem : menuItems) {
            menuItem.render(getWidgetHandle(), context);
        }
    }
}
