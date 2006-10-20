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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.seasar.framework.exception.EmptyRuntimeException;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.ExportValue;
import org.seasar.jface.annotation.ImportValue;
import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.annotation.ReturnValue;
import org.seasar.jface.binding.ActionDesc;
import org.seasar.jface.binding.EventListenerDef;
import org.seasar.jface.exception.InitializeMethodException;
import org.seasar.jface.exception.ReturnFieldException;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 */
public class ActionDescImpl implements ActionDesc {
    private Class actionClass;

    private Method initializeMethod;

    private Map<String, List<Method>> methodsCache = new HashMap<String, List<Method>>();

    private Map<String, Field> fieldsCache = new HashMap<String, Field>();

    private List<Field> importFields = new ArrayList<Field>();

    private List<Field> exportFields = new ArrayList<Field>();

    private Field returnField = null;

    private List<EventListenerDef> eventListenerDefs = new ArrayList<EventListenerDef>();

    public ActionDescImpl(Class actionClass) throws EmptyRuntimeException {
        if (actionClass == null) {
            throw new EmptyRuntimeException("actionClass");
        }

        this.actionClass = actionClass;

        setupMethods();
        setupFields();
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
            setupEventListenerMethod(methods[i]);
        }

        for (String methodName : methodListMap.keySet()) {
            List<Method> methodList = methodListMap.get(methodName);
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

    protected void setupEventListenerMethod(Method method) {
        EventListener eventListener = method.getAnnotation(EventListener.class);
        if (eventListener != null) {
            EventListenerDef eventListenerDef = new EventListenerDefImpl(
                    method, eventListener);
            eventListenerDefs.add(eventListenerDef);
        }
    }

    protected void setupFields() {
        setupFieldsByClass(actionClass);
        Class superClass = actionClass.getSuperclass();
        if (superClass != Object.class && superClass != null) {
            setupFieldsByClass(superClass);
        }
    }

    protected void setupFieldsByClass(final Class targetClass) {
        Field[] fields = targetClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            String fieldName = field.getName();
            if (!fieldsCache.containsKey(fieldName)) {
                field.setAccessible(true);
                fieldsCache.put(fieldName, field);

                setupExportField(field);
                setupImportField(field);
                setupReturnField(field);
            }
        }
    }

    protected void setupExportField(final Field field) {
        if (field.isAnnotationPresent(ExportValue.class)) {
            exportFields.add(field);
        }
    }

    protected void setupImportField(final Field field) {
        if (field.isAnnotationPresent(ImportValue.class)) {
            importFields.add(field);
        }
    }

    protected void setupReturnField(final Field field) {
        if (field.isAnnotationPresent(ReturnValue.class)) {
            if (returnField != null) {
                throw new ReturnFieldException (
                        ReturnFieldException.DUPLICATE, actionClass,
                        field);
            }
            returnField = field;
        }
    }

    /*
     * @see org.seasar.jface.binding.ActionDesc#getInitializeMethod()
     */
    public Method getInitializeMethod() {
        return initializeMethod;
    }

    /*
     * @see org.seasar.jface.binding.ActionDesc#invokeInitializeMethod(java.lang.Object)
     */
    public void invokeInitializeMethod(final Object target) {
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

    /*
     * @see org.seasar.jface.binding.ActionDesc#getExportFields()
     */
    public List<Field> getExportFields() {
        return exportFields;
    }

    /*
     * @see org.seasar.jface.binding.ActionDesc#getImportFields()
     */
    public List<Field> getImportFields() {
        return importFields;
    }

    /*
     * @see org.seasar.jface.binding.ActionDesc#getReturnField()
     */
    public Field getReturnField() {
        return returnField;
    }

    /*
     * @see org.seasar.jface.binding.ActionDesc#getReturnField()
     */
    public Object getReturnValue(Object target) {
        Object result = null;
        if (returnField != null) {
            result = FieldUtil.get(returnField, target);
        }
        return result;
    }

    public Iterator<EventListenerDef> eventListenerDefIterator() {
        return eventListenerDefs.iterator();
    }

}
