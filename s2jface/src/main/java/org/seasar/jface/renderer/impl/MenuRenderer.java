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
package org.seasar.jface.renderer.impl;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.component.ComponentAttribute.SetTiming;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.MenuComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.renderer.RendererSupportUtil;
import org.seasar.jface.util.ClassUtil;

/**
 * <code>Menu</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class MenuRenderer extends AbstractWidgetRenderer<MenuComponent, Menu> {

    @Override
    public Widget render(final UIComponent uiComponent, final Widget parent,
            final WindowContext context) {
        Widget menuParent = parent;
        if (uiComponent.getParent() instanceof WindowComponent) {
            if ((getStyle(uiComponent) & SWT.BAR) != 0) {
                menuParent = context.getComponent(WindowContext.SHELL_ID);
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

        Menu menu = (Menu) createWidget(menuParent, getStyle(uiComponent));
        // サブメニューの場合、MenuItem へ Menu をセットする
        if (subMenuItem != null) {
            subMenuItem.setMenu(menu);
            setEnabled((MenuComponent) uiComponent, subMenuItem);
            addEnabledDepend(subMenuItem, (MenuComponent) uiComponent);
        } else {
            setEnabled((MenuComponent) uiComponent, menu);
            addEnabledDepend(menu, (MenuComponent) uiComponent);
        }

        doRender((MenuComponent) uiComponent, menu);

        menu.setData(uiComponent);

        return menu;
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.BAR;
    }

    @Override
    protected void doRender(MenuComponent menuComponent, Menu menu) {
        setLocation(menuComponent, menu);
        setToParentMenu(menuComponent, menu);
    }

    @Override
    protected void doRenderAfter(Menu widget, MenuComponent uiComponent,
            Widget parent, WindowContext context) {
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

    private void setToParentMenu(MenuComponent menuComponent, Menu menu) {
        UIComponent menuHolder = menuComponent.getMenuHolder();
        if (menuHolder != null) {
            if (menuHolder instanceof WindowComponent) {
                // メニューバーの設定
                if ((SWT.BAR & getStyle(menuComponent)) != 0) {
                    Shell shell = (Shell) getContext().getComponent(
                            WindowContext.SHELL_ID);
                    shell.setMenuBar(menu);
                } else {
                    Control control = (Control) menuHolder.getWidget();
                    control.setMenu(menu);
                }
            } else {
                // コンテクストメニューの設定
                Control control = (Control) menuHolder.getWidget();
                control.setMenu(menu);
            }
        }
    }

    private void setDefaultItem(Menu widget, MenuComponent uiComponent) {
        if (uiComponent.getDefaultItemId() != null) {
            Widget defaultItem = getContext().getComponent(
                    uiComponent.getDefaultItemId());
            widget.setDefaultItem((MenuItem) defaultItem);
        }
    }

    @Override
    protected Class<Menu> getWidgetType() {
        return Menu.class;
    }

    @Override
    protected Widget createWidget(Widget parent, int style) {
        if (parent instanceof Decorations) {
            // メニューバーの場合
            return super.createWidget(parent, style);
        } else {
            Class<Menu> widgetClass = getWidgetType();
            return ClassUtil.<Menu> newInstance(widgetClass, parent);
        }
    }
}
