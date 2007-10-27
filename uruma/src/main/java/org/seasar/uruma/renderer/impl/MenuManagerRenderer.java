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
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.impl.MenuComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
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
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    public WidgetHandle preRender(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {
        setContext(context);

        if (parent == null) {
            MenuManager rootMenuManager = getRootMenuManager();

            WidgetHandle handle = createWidgetHandle(uiComponent,
                    rootMenuManager);

            return handle;
        } else if (parent.getUiComponent() instanceof MenuComponent) {
            MenuComponent menuComponent = (MenuComponent) uiComponent;

            MenuManager menuManager = new MenuManager(menuComponent.getText());
            // renderMenu(uiComponent, menuManager.getMenu());

            MenuManager parentManager = parent.<MenuManager> getCastWidget();
            parentManager.add(menuManager);

            WidgetHandle handle = createWidgetHandle(uiComponent, menuManager);

            return handle;
        }
        return null;
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

    protected void renderMenu(final UIComponent uiComponent, final Menu menu) {
        RendererSupportUtil.setAttributes(uiComponent, menu, SetTiming.RENDER);
    }

    protected MenuManager getRootMenuManager() {
        WidgetHandle handle = getContext().getWidgetHandle(
                PartContext.ROOT_MENU_MANAGER_ID);
        return handle.<MenuManager> getCastWidget();
    }
}
