/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.desc.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.ApplicationContext;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.annotation.PostOpenMethod;
import org.seasar.uruma.binding.context.ApplicationContextDef;
import org.seasar.uruma.binding.method.EventListenerDef;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.exception.IllegalMethodException;
import org.seasar.uruma.exception.InitializeMethodException;
import org.seasar.uruma.exception.MethodInvocationException;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link PartActionDesc} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class PartActionDescImpl implements PartActionDesc, UrumaMessageCodes {

    private Class<?> partActionClass;

    private BeanDesc beanDesc;

    private Method initializeMethod;

    private Method postOpenMethod;

    private Map<String, List<Method>> methodsCache = new HashMap<String, List<Method>>();

    private Map<String, Field> fieldsCache = new HashMap<String, Field>();

    private List<EventListenerDef> eventListenerDefs = new ArrayList<EventListenerDef>();

    private List<ApplicationContextDef> appContextDefs = new ArrayList<ApplicationContextDef>();

    /**
     * {@link PartActionDescImpl} を構築します。<br />
     * 
     * @param partActionClass
     *            対応するクラスオブジェクト
     */
    public PartActionDescImpl(final Class<?> partActionClass) {
        if (partActionClass == null) {
            throw new EmptyRuntimeException("partActionClass");
        }

        this.partActionClass = partActionClass;
        this.beanDesc = BeanDescFactory.getBeanDesc(partActionClass);

        setupMethods();
        setupFields();
    }

    /*
     * @see org.seasar.uruma.desc.PartActionDesc#getInitializeMethod()
     */
    public Method getInitializeMethod() {
        return this.initializeMethod;
    }

    /*
     * @see org.seasar.uruma.desc.PartActionDesc#invokeInitializeMethod(java.lang.Object)
     */
    public void invokeInitializeMethod(final Object target) {
        if (initializeMethod != null) {
            AssertionUtil.assertNotNull("target", target);
            try {
                initializeMethod.invoke(target, (Object[]) null);
            } catch (Throwable ex) {
                throw new InitializeMethodException(ex.getCause(),
                        partActionClass, initializeMethod, target);
            }
        }
    }

    /*
     * @see org.seasar.uruma.desc.PartActionDesc#getPostOpenMethod()
     */
    public Method getPostOpenMethod() {
        return this.postOpenMethod;
    }

    /*
     * @see org.seasar.uruma.desc.PartActionDesc#invokePostOpenMethod(java.lang.Object)
     */
    public void invokePostOpenMethod(final Object target) {
        if (postOpenMethod != null) {
            AssertionUtil.assertNotNull("target", target);
            try {
                postOpenMethod.invoke(target, (Object[]) null);
            } catch (Throwable ex) {
                throw new MethodInvocationException(ex);
            }
        }
    }

    /*
     * @see org.seasar.uruma.desc.PartActionDesc#getEventListenerDefList()
     */
    public List<EventListenerDef> getEventListenerDefList() {
        return Collections.unmodifiableList(eventListenerDefs);
    }

    /*
     * @see org.seasar.uruma.desc.PartActionDesc#getApplicationContextDefList()
     */
    public List<ApplicationContextDef> getApplicationContextDefList() {
        return Collections.unmodifiableList(appContextDefs);
    }

    protected void setupMethods() {
        Map<String, List<Method>> methodListMap = new HashMap<String, List<Method>>();
        Method[] methods = partActionClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            List<Method> methodList = methodListMap.get(methods[i].getName());
            if (methodList == null) {
                methodList = new ArrayList<Method>();
                methodListMap.put(methods[i].getName(), methodList);
            }
            methodList.add(methods[i]);

            setupInitializeMethod(methods[i]);
            setupPostOpenMethod(methods[i]);
            setupEventListenerMethod(methods[i]);
        }

        for (Entry<String, List<Method>> entry : methodListMap.entrySet()) {
            String methodName = entry.getKey();
            List<Method> methodList = entry.getValue();
            methodsCache.put(methodName, methodList);
        }
    }

    protected void setupInitializeMethod(final Method method) {
        if (method.isAnnotationPresent(InitializeMethod.class)) {
            if ((method.getReturnType() == Void.TYPE)
                    && (method.getParameterTypes().length == 0)) {
                if (initializeMethod == null) {
                    initializeMethod = method;
                } else {
                    throw new IllegalMethodException(
                            DUPLICATE_ANNOTATED_METHOD, partActionClass, method);
                }
            } else {
                throw new IllegalMethodException(ILLEGAL_METHOD_SIGNATURE,
                        partActionClass, method);
            }
        }
    }

    protected void setupPostOpenMethod(final Method method) {
        if (method.isAnnotationPresent(PostOpenMethod.class)) {
            if ((method.getReturnType() == Void.TYPE)
                    && (method.getParameterTypes().length == 0)) {
                if (postOpenMethod == null) {
                    postOpenMethod = method;
                } else {
                    throw new InitializeMethodException(
                            DUPLICATE_ANNOTATED_METHOD, partActionClass, method);
                }
            } else {
                throw new InitializeMethodException(ILLEGAL_METHOD_SIGNATURE,
                        partActionClass, method);
            }
        }
    }

    protected void setupEventListenerMethod(final Method method) {
        EventListener anno = method.getAnnotation(EventListener.class);
        if (anno != null) {
            EventListenerDef def = new EventListenerDef(method, anno);
            eventListenerDefs.add(def);
        }
    }

    protected void setupApplicationContext(final Field field) {
        ApplicationContext anno = field.getAnnotation(ApplicationContext.class);
        if (anno != null) {
            String name = anno.name();
            if (StringUtil.isEmpty(name)) {
                name = field.getName();
            }

            PropertyDesc pd = beanDesc.getPropertyDesc(field.getName());
            ApplicationContextDef def = new ApplicationContextDef(pd, name);
            appContextDefs.add(def);
        }
    }

    protected void setupFields() {
        setupFieldsByClass(partActionClass);
        Class<?> superClass = partActionClass.getSuperclass();
        while (superClass != Object.class && superClass != null) {
            setupFieldsByClass(superClass);
            superClass = superClass.getSuperclass();
        }
    }

    protected void setupFieldsByClass(final Class<?> targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (!fieldsCache.containsKey(fieldName)) {
                field.setAccessible(true);
                fieldsCache.put(fieldName, field);

                setupApplicationContext(field);
            }
        }
    }
}
