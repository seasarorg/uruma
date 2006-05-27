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
import org.seasar.framework.container.annotation.tiger.AutoBindingType;
import org.seasar.framework.container.annotation.tiger.InstanceType;
import org.seasar.framework.container.assembler.AutoBindingDefFactory;
import org.seasar.framework.container.deployer.InstanceDefFactory;
import org.seasar.framework.container.factory.ComponentDefFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.S2JFaceAction;
import org.seasar.jface.container.EventListenerDef;
import org.seasar.jface.container.S2JFaceComponentDef;
import org.seasar.jface.container.impl.EventListenerDefImpl;
import org.seasar.jface.container.impl.S2JFaceComponentDefImpl;

/**
 * @author y-komori
 * 
 */
public class S2JFaceComponentDefFactory implements ComponentDefFactory {

    public ComponentDef createComponentDef(final Class<?> componentClass,
            final InstanceDef defaultInstanceDef,
            final AutoBindingDef defaultAutoBindingDef) {
        final S2JFaceAction s2JFaceAction = componentClass
                .getAnnotation(S2JFaceAction.class);
        if (s2JFaceAction == null) {
            return null;
        }

        final S2JFaceComponentDef componentDef = new S2JFaceComponentDefImpl(
                componentClass);

        setupName(componentDef, s2JFaceAction.name());

        setupInstanceType(componentDef, s2JFaceAction.instance(),
                defaultInstanceDef);

        setupAutoBindingType(componentDef, s2JFaceAction.autoBinding(),
                defaultAutoBindingDef);

        setupMethods(componentDef);

        return componentDef;

    }

    protected void setupName(final S2JFaceComponentDef componentDef,
            final String name) {
        if (!StringUtil.isEmpty(name)) {
            componentDef.setComponentName(name);
        } else {
            componentDef.setComponentName(StringUtil.decapitalize(componentDef
                    .getComponentClass().getSimpleName()));
        }

    }

    protected void setupInstanceType(final S2JFaceComponentDef componentDef,
            final InstanceType instanceType,
            final InstanceDef defaultInstanceDef) {
        if (instanceType != null && !StringUtil.isEmpty(instanceType.getName())) {
            componentDef.setInstanceDef(InstanceDefFactory
                    .getInstanceDef(instanceType.getName()));
        } else {
            componentDef.setInstanceDef(defaultInstanceDef);
        }
    }

    protected void setupAutoBindingType(final S2JFaceComponentDef componentDef,
            final AutoBindingType autoBindingType,
            final AutoBindingDef defaultAutoBindingDef) {
        if (autoBindingType != null
                && !StringUtil.isEmpty(autoBindingType.getName())) {
            componentDef.setAutoBindingDef(AutoBindingDefFactory
                    .getAutoBindingDef(autoBindingType.getName()));
        } else {
            componentDef.setAutoBindingDef(defaultAutoBindingDef);
        }
    }

    protected void setupMethods(final S2JFaceComponentDef componentDef) {
        Method[] declaredMethods = componentDef.getComponentClass()
                .getDeclaredMethods();
        for (Method method : declaredMethods) {
            EventListener eventListener = method
                    .getAnnotation(EventListener.class);
            if (eventListener != null) {
                EventListenerDef eventListenerDef = new EventListenerDefImpl(
                        method, eventListener);
                componentDef.addEventListenerDef(eventListenerDef);
            }
        }
    }
}
