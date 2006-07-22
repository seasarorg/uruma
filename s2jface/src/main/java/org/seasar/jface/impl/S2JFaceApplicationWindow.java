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
package org.seasar.jface.impl;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.MethodNotFoundRuntimeException;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.WindowContext;
import org.seasar.jface.annotation.EventListenerType;
import org.seasar.jface.binding.MethodBinding;
import org.seasar.jface.binding.SWTEventListenerBinder;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.container.EventListenerDef;
import org.seasar.jface.container.S2JFaceComponentDef;
import org.seasar.jface.events.SWTEventListenerFactory;
import org.seasar.jface.exception.NotFoundException;
import org.seasar.jface.renderer.MenuManagerRenderer;
import org.seasar.jface.renderer.WindowRenderer;
import org.seasar.jface.util.ImageManager;

/**
 * @author y-komori
 * 
 */
public class S2JFaceApplicationWindow extends ApplicationWindow {
    private TemplateComponent template;

    private WindowContext context;

    public S2JFaceApplicationWindow(TemplateComponent template) {
        super(null);
        this.template = template;
        this.context = new WindowContextImpl();

        setupMenuBar();
        setupShellStyle(template.getWindowComponent());
    }

    protected void setupShellStyle(final WindowComponent component) {
        int style = ((WindowRenderer) component.getRenderer())
                .getShellStyle(component);
        setShellStyle(style);
    }

    protected void setupMenuBar() {
        // addMenuBar() は shell の生成前に呼び出さなければならないため、
        // MenuManagerRenderer のみ別扱いでレンダリングを行っている
        WindowComponent windowComponent = template.getWindowComponent();
        String rendererName = (new MenuManagerRenderer()).getRendererName();
        for (UIComponent component : windowComponent.getChildren()) {
            if (rendererName.equals(component.getRendererType())) {
                component.render(null, context);
                if (context.getMenuBar() != null) {
                    addMenuBar();
                }
            }
        }
    }

    protected void setupImageManager() {
        ResourceBundle imageResources = ResourceBundle
                .getBundle("s2JFaceImages");
        ImageManager.loadImages(imageResources);
    }

    @Override
    protected Control createContents(Composite parent) {
        setupImageManager();

        WindowComponent windowComponent = template.getWindowComponent();
        windowComponent.render(parent, context);

        createListeners(windowComponent.getId(), context);

        return parent;
    }

    @Override
    protected MenuManager createMenuManager() {
        return context.getMenuBar();
    }

    @Override
    public boolean close() {
        boolean result = super.close();
        if (result) {
            ImageManager.dispose();
        }
        return result;
    }

    protected void createListeners(String windowName, WindowContext context) {
        S2JFaceComponentDef componentDef = getActionComponentDef(windowName);
        if (componentDef == null) {
            return;
        }
        Object action = componentDef.getComponent();

        Iterator<EventListenerDef> iter = componentDef
                .eventListenerDefIterator();
        while (iter.hasNext()) {
            EventListenerDef eventListenerDef = iter.next();
            String id = eventListenerDef.getEventListener().id();
            Widget widget = context.getComponent(id);
            if (widget != null) {
                Method targetMethod = eventListenerDef.getTargetMethod();
                MethodBinding methodBinding = new MethodBinding(action,
                        targetMethod);

                EventListenerType listenerType = eventListenerDef
                        .getEventListener().eventListenerType();

                try {
                    SWTEventListener listener = SWTEventListenerFactory
                            .getListener(listenerType, context, methodBinding);
                    SWTEventListenerBinder.bindListener(listener, widget);

                } catch (MethodNotFoundRuntimeException ex) {
                    // TODO 例外処理
                }
            } else {
                throw new NotFoundException(NotFoundException.WIDGET, id);
            }
        }
    }

    protected S2JFaceComponentDef getActionComponentDef(String windowName) {
        String actionComponentName = getActionComponentName(windowName);
        S2Container container = SingletonS2ContainerFactory.getContainer();
        if (container.hasComponentDef(actionComponentName)) {
            return (S2JFaceComponentDef) container
                    .getComponentDef(actionComponentName);
        } else {
            return null;
        }
    }

    protected String getActionComponentName(String windowName) {
        return StringUtil.decapitalize(windowName) + "Action";
    }
}
