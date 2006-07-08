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
import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.factory.AnnotationHandler;
import org.seasar.framework.container.factory.component.PojoComponentDefBuilder;
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
public class S2JFaceComponentDefBuilder extends PojoComponentDefBuilder {
    public S2JFaceComponentDefBuilder() {
    }

    public ComponentDef createComponentDef(
            final AnnotationHandler annotationHandler,
            final Class<?> componentClass,
            final InstanceDef defaultInstanceDef,
            final AutoBindingDef defaultAutoBindingDef,
            final boolean defaultExternalBinding) {
        final Component component = componentClass
                .getAnnotation(Component.class);

        final S2JFaceComponentDef componentDef = new S2JFaceComponentDefImpl(
                componentClass);

        setupMethods(componentDef);

        if (component != null) {
            if (!StringUtil.isEmpty(component.name())) {
                componentDef.setComponentName(component.name());
            }
            componentDef.setInstanceDef(getInstanceDef(component,
                    defaultInstanceDef));
            componentDef.setAutoBindingDef(getAutoBindingDef(component,
                    defaultAutoBindingDef));
            componentDef.setExternalBinding(component.externalBinding());
        }
        return componentDef;
    }

    protected void setupMethods(final S2JFaceComponentDef componentDef) {
        for (Class<?> type = componentDef.getComponentClass(); type != Object.class; type = type
                .getSuperclass()) {
            Method[] declaredMethods = type.getDeclaredMethods();
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
}
