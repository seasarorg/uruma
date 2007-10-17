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

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.uruma.annotation.RenderingPolicy.SetTiming;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.impl.MenuComponent;
import org.seasar.uruma.component.impl.WindowComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.renderer.RendererSupportUtil;

/**
 * {@link Menu} のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class MenuRenderer extends AbstractWidgetRenderer<MenuComponent, Menu> {
    // TODO MenuManager を利用して見直す

    @Override
    public WidgetHandle render(final UIComponent uiComponent,
            final WidgetHandle parent, final PartContext context) {
        Widget menuParent = parent.<Widget> getCastWidget();
        if (uiComponent.getParent() instanceof WindowComponent) {
            if ((getStyle(uiComponent) & SWT.BAR) != 0) {
                WidgetHandle shellHandle = context
                        .getWidgetHandle(PartContext.SHELL_ID);
                menuParent = shellHandle.<Widget> getCastWidget();
            }
        }

        // サブメニューの場合は MenuItem を親として用意する
        MenuItem subMenuItem = null;
        if (parent instanceof Menu) {
            subMenuItem = new MenuItem((Menu) parent, SWT.CASCADE);
            RendererSupportUtil.setAttributes(uiComponent, subMenuItem,
                    SetTiming.RENDER);
        }

        setContext(context);

        Menu menu = createWidget(menuParent, getStyle(uiComponent));
        // サブメニューの場合、MenuItem へ Menu をセットする
        if (subMenuItem != null) {
            subMenuItem.setMenu(menu);
            setEnabled((MenuComponent) uiComponent, subMenuItem);
        } else {
            setEnabled((MenuComponent) uiComponent, menu);
        }

        doRender((MenuComponent) uiComponent, menu);

        menu.setData(uiComponent);

        return createWidgetHandle(uiComponent, menu);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#getDefaultStyle()
     */
    @Override
    protected int getDefaultStyle() {
        return SWT.BAR;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRender(org.seasar.uruma.component.UIComponent,
     *      org.eclipse.swt.widgets.Widget)
     */
    @Override
    protected void doRender(final MenuComponent menuComponent, final Menu menu) {
        setLocation(menuComponent, menu);
        setToParentMenu(menuComponent, menu);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractWidgetRenderer#doRenderAfter(org.eclipse.swt.widgets.Widget,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    protected void doRenderAfter(final Menu widget,
            final MenuComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        setDefaultItem(widget, uiComponent);
    }

    protected void setEnabled(final MenuComponent menuComponent, final Menu menu) {
        String enabledStr = menuComponent.getEnabled();
        if (enabledStr != null) {
            menu.setEnabled(BooleanConversionUtil
                    .toPrimitiveBoolean(enabledStr));
        }
    }

    protected void setEnabled(final MenuComponent menuComponent,
            final MenuItem menuItem) {
        String enabledStr = menuComponent.getEnabled();
        if (enabledStr != null) {
            menuItem.setEnabled(BooleanConversionUtil
                    .toPrimitiveBoolean(enabledStr));
        }
    }

    protected void setLocation(final MenuComponent menuComponent,
            final Menu menu) {
        String xStr = menuComponent.getX();
        String yStr = menuComponent.getY();
        if ((xStr != null) && (yStr != null)) {
            menu.setLocation(Integer.parseInt(xStr), Integer.parseInt(yStr));
        }
    }

    private void setToParentMenu(final MenuComponent menuComponent,
            final Menu menu) {
        // UIComponent menuHolder = menuComponent.getMenuHolder();
        // if (menuHolder != null) {
        // if (menuHolder instanceof WindowComponent) {
        // // メニューバーの設定
        // if ((SWT.BAR & getStyle(menuComponent)) != 0) {
        // Shell shell = (Shell) getContext().getComponent(
        // WindowContext.SHELL_ID);
        // shell.setMenuBar(menu);
        // } else {
        // Control control = (Control) menuHolder.getWidget();
        // control.setMenu(menu);
        // }
        // } else {
        // // コンテクストメニューの設定
        // Control control = (Control) menuHolder.getWidget();
        // control.setMenu(menu);
        // }
        // }
    }

    private void setDefaultItem(final Menu widget,
            final MenuComponent uiComponent) {
        // if (uiComponent.getDefaultItemId() != null) {
        // Widget defaultItem = getContext().getComponent(
        // uiComponent.getDefaultItemId());
        // widget.setDefaultItem((MenuItem) defaultItem);
        // }
    }

    @Override
    protected Class<Menu> getWidgetType() {
        return Menu.class;
    }

    @Override
    protected Menu createWidget(final Widget parent, final int style) {
        // if (parent instanceof Decorations) {
        // // メニューバーの場合
        // return super.createWidget(parent, style);
        // } else {
        // Class<Menu> widgetClass = getWidgetType();
        // return ClassUtil.<Menu> newInstance(widgetClass, parent);
        // }
        return null;
    }
}
