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
package org.seasar.jface.renderer;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.MenuInfo;

/**
 * <code>Menu</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author dkameya
 */
public class MenuRenderer extends AbstractControlRenderer<Composite> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        int style = SWT.NONE;
        return style;
    }

    @Override
    protected void doRender(final Composite composite,
            final ControlComponent controlComponent) {
        Shell shell = composite.getShell();
        Menu menuBar = createMenu(shell);
        shell.setMenuBar(menuBar);
        for (Item item : controlComponent.getItemList()) {
            MenuItem mainMenuItem = createMenuItem(menuBar, SWT.CASCADE);
            mainMenuItem.setText(item.getValue());
            Menu mainMenu = new Menu(mainMenuItem);
            mainMenuItem.setMenu(mainMenu);
            addChildMenu(mainMenu, item.getChildren());
        }
    }
    
    protected Menu createMenu(final Shell parent) {
        return new Menu(parent, SWT.BAR);
    }
    
    protected Menu createMenu(final MenuItem parent) {
        return new Menu(parent);
    }
    
    protected MenuItem createMenuItem(final Menu parent, final int style) {
        return new MenuItem(parent, style);
    }

    @Override
    protected Class<Composite> getControlType() {
        return Composite.class;
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return MenuInfo.class;
    }

    public String getRendererName() {
        return "menu";
    }
    
    protected void addChildMenu(final Menu menu,
            final List<Item> childItems) {
        for (Item item : childItems) {
            MenuItem childMenu = createMenuItem(menu, SWT.PUSH);
            if (childMenu != null && item.getValue() != null) {
                childMenu.setText(item.getValue());
            }
        }
    }
}
