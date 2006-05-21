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
package org.seasar.jface.container.factory;

import java.lang.reflect.Method;

import org.seasar.framework.container.AutoBindingDef;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.InstanceDef;
import org.seasar.framework.container.factory.TigerAnnotationHandler;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.container.EventListenerDef;
import org.seasar.jface.container.S2JFaceComponentDef;
import org.seasar.jface.container.impl.EventListenerDefImpl;
import org.seasar.jface.container.impl.S2JFaceComponentDefImpl;

/**
 * @author y-komori
 * 
 */
public class S2JFaceAnnotationHandler extends TigerAnnotationHandler {

    @Override
    public ComponentDef createComponentDef(Class componentClass,
            InstanceDef defaultInstanceDef, AutoBindingDef defaultAutoBindingDef) {
        S2JFaceComponentDef componentDef = (S2JFaceComponentDef) super
                .createComponentDef(componentClass, defaultInstanceDef,
                        defaultAutoBindingDef);

        Method[] declaredMethods = componentClass.getDeclaredMethods();
        for (Method method : declaredMethods) {
            EventListener eventListener = method
                    .getAnnotation(EventListener.class);
            if (eventListener != null) {
                EventListenerDef eventListenerDef = new EventListenerDefImpl(
                        method, eventListener);
                componentDef.addEventListenerDef(eventListenerDef);
            }
        }

        return componentDef;
    }

    @Override
    protected ComponentDef createComponentDef(Class componentClass,
            String name, InstanceDef instanceDef, AutoBindingDef autoBindingDef) {
        ComponentDef componentDef = new S2JFaceComponentDefImpl(componentClass);
        if (!StringUtil.isEmpty(name)) {
            componentDef.setComponentName(name);
        }
        if (instanceDef != null) {
            componentDef.setInstanceDef(instanceDef);
        }
        if (autoBindingDef != null) {
            componentDef.setAutoBindingDef(autoBindingDef);
        }
        return componentDef;
    }
}
