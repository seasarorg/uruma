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
package org.seasar.uruma.renderer.impl;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Menu;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.impl.MenuComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.renderer.RendererSupportUtil;

/**
 * {@link MenuManager} のレンダリングを行うクラスです。<br />
 * 
 * @author y-komori
 */
public class MenuManagerRenderer extends AbstractRenderer {

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#preRender(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.WindowContext)
     */
    @Override
    public WidgetHandle preRender(final UIComponent uiComponent,
            final WidgetHandle parent, final WindowContext context) {

        if (parent == null) {
            return createWidgetHandle(uiComponent, createMenuManager());
        } else if (parent.getUiComponent() instanceof MenuComponent) {
            MenuComponent menuComponent = (MenuComponent) uiComponent;

            MenuManager menuManager = new MenuManager(menuComponent.getText());
            setVisivle(menuComponent, menuManager);

            MenuManager parentManager = parent.<MenuManager> getCastWidget();
            parentManager.add(menuManager);

            WidgetHandle handle = createWidgetHandle(uiComponent, menuManager);

            return handle;
        }
        return null;
    }

    /**
     * メニューのレンダリングを行います。<br />
     * 
     * @param menuComponent
     *            {@link MenuComponent} オブジェクト
     * @param menu
     *            {@link Menu} オブジェクト
     */
    public void renderMenu(final MenuComponent menuComponent, final Menu menu) {
        String x = menuComponent.getX();
        String y = menuComponent.getY();
        if (!StringUtil.isEmpty(x) && !StringUtil.isEmpty(y)) {
            menu.setLocation(Integer.valueOf(x), Integer.valueOf(y));
        }
    }

    /*
     * @see org.seasar.uruma.renderer.Renderer#render(org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    public WidgetHandle render(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {
        // Do nothing.
        return null;
    }

    /*
     * @see org.seasar.uruma.renderer.Renderer#renderAfter(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    public void renderAfter(final WidgetHandle widget,
            final UIComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        // Do nothing.
    }

    protected void setVisivle(final MenuComponent menuComponent,
            final MenuManager menuManager) {
        String visible = menuComponent.getVisible();
        if (visible != null) {
            menuManager.setVisible(RendererSupportUtil.convertBoolean(visible));
        }
    }

    protected MenuManager createMenuManager() {
        return new MenuManager();
    }
}
