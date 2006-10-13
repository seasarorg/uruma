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
package org.seasar.jface.binding.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.binding.ActionDesc;
import org.seasar.jface.exception.InitializeMethodException;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 */
public class ActionDescImpl implements ActionDesc {
    private Class actionClass;

    private Method initializeMethod;

    private Map<String, List<Method>> methodsCache = new HashMap<String, List<Method>>();

    public ActionDescImpl(Class actionClass) throws EmptyRuntimeException {
        if (actionClass == null) {
            throw new EmptyRuntimeException("actionClass");
        }

        this.actionClass = actionClass;

        setupMethods();
    }

    protected void setupMethods() {
        Map<String, List<Method>> methodListMap = new HashMap<String, List<Method>>();
        Method[] methods = actionClass.getMethods();
        for (int i = 0; i < methods.length; i++) {
            List<Method> methodList = methodListMap.get(methods[i].getName());
            if (methodList == null) {
                methodList = new ArrayList<Method>();
                methodListMap.put(methods[i].getName(), methodList);
            }
            methodList.add(methods[i]);

            setupInitializeMethod(methods[i]);
        }

        for (String methodName : methodListMap.keySet()) {
            List<Method> methodList = methodListMap.get(methodName);
            methodsCache.put(methodName, methodList);
        }
    }

    protected void setupInitializeMethod(Method method) {
        if (method.isAnnotationPresent(InitializeMethod.class)) {
            if ((method.getReturnType() == Void.TYPE)
                    && (method.getParameterTypes().length == 0)) {
                if (initializeMethod == null) {
                    initializeMethod = method;
                } else {
                    throw new InitializeMethodException(
                            InitializeMethodException.DUPLICATE, actionClass,
                            method);
                }
            } else {
                throw new InitializeMethodException(
                        InitializeMethodException.INVALID, actionClass, method);
            }
        }
    }

    public Method getInitializeMethod() {
        return initializeMethod;
    }

    public void invokeInitializeMethod(Object target) {
        if (initializeMethod != null) {
            AssertionUtil.assertNotNull("target", target);
            try {
                initializeMethod.invoke(target, (Object[]) null);
            } catch (Throwable ex) {
                throw new InitializeMethodException(ex, actionClass,
                        initializeMethod, target);
            }
        }
    }
}
