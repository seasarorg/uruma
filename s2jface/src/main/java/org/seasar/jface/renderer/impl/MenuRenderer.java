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
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.MenuComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.util.ClassUtil;

/**
 * <code>Menu</code> のレンダリングを行うクラスです。<br />
 * 
 * @author bskuroneko
 */
public class MenuRenderer extends AbstractWidgetRenderer<MenuComponent, Menu> {

    @Override
    public Widget render(UIComponent uiComponent, Widget parent,
            WindowContext context) {
        Widget menuParent = parent;
        if (uiComponent.getParent() instanceof WindowComponent) {
            if ((getStyle(uiComponent) & SWT.BAR) != 0) {
                menuParent = context.getComponent(WindowContext.SHELL_ID);
            }
        }
        return super.render(uiComponent, menuParent, context);
    }

    @Override
    protected int getDefaultStyle() {
        return SWT.BAR;
    }

    @Override
    protected void doRender(MenuComponent uiComponent, Menu widget) {
        setLocation(uiComponent, widget);
        setToParentMenu(uiComponent, widget);
    }

    @Override
    protected void doRenderAfter(Menu widget, MenuComponent uiComponent,
            Widget parent, WindowContext context) {
        setDefaultItem(widget, uiComponent);
    }

    protected void setLocation(final MenuComponent controlComponent,
            final Menu control) {
        String xStr = controlComponent.getX();
        String yStr = controlComponent.getY();
        if ((xStr != null) && (yStr != null)) {
            control.setLocation(Integer.parseInt(xStr), Integer.parseInt(yStr));
        }
    }

    private void setToParentMenu(MenuComponent menu, Menu widget) {
        UIComponent menuHolder = menu.getMenuHolder();
        if (menuHolder != null) {
            if (menuHolder instanceof WindowComponent) {
                // メニューバーの設定
                if ((SWT.BAR & getStyle(menu)) != 0) {
                    Shell shell = (Shell) getContext().getComponent(
                            WindowContext.SHELL_ID);
                    shell.setMenuBar((Menu) widget);
                } else {
                    Control control = (Control) menuHolder.getWidget();
                    control.setMenu(widget);
                }
            } else {
                // コンテクストメニューの設定
                Control control = (Control) menuHolder.getWidget();
                control.setMenu(widget);
            }

        } else if (menu.getParentMenuItem() != null) {
            // サブメニューの設定
            MenuItem item = (MenuItem) menu.getParentMenuItem().getWidget();
            item.setMenu(widget);
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
            return super.createWidget(parent, style);
        } else {
            Class<? extends Widget> widgetClass = getWidgetType();
            return ClassUtil.<Widget> newInstance(widgetClass, parent);
        }
    }
}
