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
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIContainer;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.util.AssertionUtil;

/**
 * メニュー情報を保持するためのコンポーネントです。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
public class MenuComponent extends MenuItemComponent implements UIContainer {

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("デフォルトアイテムID")
    private String defaultItemId;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("可視状態")
    private String visible;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("X 座標")
    private String x;

    @RenderingPolicy(targetType = TargetType.NONE)
    @FieldDescription("Y 座標")
    private String y;

    private List<UIComponent> children = new ArrayList<UIComponent>();

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
     * @param defaultItemId
     *            デフォルトアイテムID
     */
    public void setDefaultItemId(final String defaultItemId) {
        this.defaultItemId = defaultItemId;
    }

    /**
     * 可視状態を取得します。<br />
     * 
     * @return 可視状態
     */
    public String getVisible() {
        return this.visible;
    }

    /**
     * 可視状態を設定します。<br />
     * 
     * @param visible
     *            可視状態
     */
    public void setVisible(final String visible) {
        this.visible = visible;
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
     * @param x
     *            X 座標
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
     * @param y
     *            Y 座標
     */
    public void setY(final String y) {
        this.y = y;
    }

    public void addChild(final UIComponent child) {
        AssertionUtil.assertNotNull("child", child);
        AssertionUtil.assertInstanceOf("child", MenuItemComponent.class, child);

        this.children.add(child);
    }

    public List<UIComponent> getChildren() {
        return this.children;
    }

    /*
     * @see org.seasar.uruma.component.impl.AbstractUIComponent#doPreRender(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doPreRender(final WidgetHandle parent,
            final PartContext context) {
        for (UIComponent uiComponent : children) {
            uiComponent.preRender(getWidgetHandle(), context);
        }
    }
}
