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
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.container.EventListenerDef;
import org.seasar.jface.container.S2JFaceComponentDef;
import org.seasar.jface.events.SWTEventListenerFactory;
import org.seasar.jface.exception.NotFoundException;
import org.seasar.jface.renderer.WindowRenderer;
import org.seasar.jface.util.ImageManager;

/**
 * @author y-komori
 * 
 */
public class TemplateWindow extends ApplicationWindow {
    private TemplateComponent template;

    private WindowComponent windowComponent;

    public TemplateWindow(TemplateComponent template) {
        super(null);
        this.template = template;
        this.windowComponent = (WindowComponent) template.getChild(0);

        int style = ((WindowRenderer) windowComponent.getRenderer())
                .getShellStyle(windowComponent);
        setShellStyle(style);
    }

    @Override
    protected Control createContents(Composite parent) {
        // TODO 初期化位置とdispose位置を再考
        ResourceBundle imageResources = ResourceBundle
                .getBundle("s2JFaceImages");
        ImageManager.loadImages(imageResources, getClass());

        WindowContext context = new WindowContextImpl();
        windowComponent.render(parent, context);
        createListeners(windowComponent.getId(), context);
        return parent;
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
