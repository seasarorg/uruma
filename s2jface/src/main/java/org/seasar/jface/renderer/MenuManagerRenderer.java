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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.component.Inheritance;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.exception.RenderException;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.MenuManagerInfo;

/**
 * <code>MenuManager</code> のレンダリングを行うクラスです。<br/>
 * 
 * @author y-komori
 */
public class MenuManagerRenderer extends AbstractRenderer {
    protected S2Container container = SingletonS2ContainerFactory
            .getContainer();

    public Widget render(final UIComponent uiComponent, final Composite parent,
            final WindowContext context) {
        MenuManager rootMenuManager = new MenuManager("");
        context.setMenuBar(rootMenuManager);

        renderItems(((ControlComponent) uiComponent).getItemList(),
                rootMenuManager);

        return null;
    }

    protected void renderItems(final List<Item> items, final MenuManager parent) {
        for (Item item : items) {
            String label = item.getLabel();
            String componentName = item.getValue();

            if (componentName != null) {
                // IAction オブジェクトの登録
                IAction action = getActionComponent(componentName);
                if (label != null) {
                    action.setText(label);
                }
                parent.add(action);
            } else if (label != null) {
                if (StringUtil.isBlank(label)) {
                    // セパレータの登録
                    parent.add(new Separator());
                } else {
                    // メニューの登録
                    MenuManager menu = new MenuManager(label);
                    parent.add(menu);

                    List<Item> children = item.getChildren();
                    if (children.size() > 0) {
                        renderItems(children, menu);
                    }
                }
            }
        }
    }

    protected IAction getActionComponent(final String name) {
        Object component = container.getComponent(name);
        if (component instanceof IAction) {
            return (IAction) component;
        } else {
            throw new RenderException(RenderException.IACTION, name);
        }
    }

    public void renderAfter(Widget widget, UIComponent uiComponent,
            Composite parent, WindowContext context) {
    }

    public Inheritance getDefaultInheritance(String propertyName) {
        return Inheritance.NONE;
    }

    public String getRendererName() {
        return "menuManager";
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return MenuManagerInfo.class;
    }
}
