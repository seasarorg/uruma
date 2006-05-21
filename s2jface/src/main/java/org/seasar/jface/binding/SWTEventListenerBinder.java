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
package org.seasar.jface.binding;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.internal.SWTEventListener;
import org.eclipse.swt.widgets.Widget;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.MethodNotFoundRuntimeException;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.MethodUtil;
import org.seasar.jface.events.S2JFaceSelectionListener;

/**
 * @author y-komori
 * 
 */
public class SWTEventListenerBinder {
    public static void bindListener(SWTEventListener listener, Widget widget) {
        if (listener instanceof S2JFaceSelectionListener) {
            bindListener((S2JFaceSelectionListener) listener, widget);
        }
    }

    protected static void bindListener(S2JFaceSelectionListener listener,
            Widget widget) {
        Method addListenerMethod = findMethod(widget.getClass(),
                "addSelectionListener", SelectionListener.class);
        MethodUtil.invoke(addListenerMethod, widget, new Object[] { listener });
    }

    protected static Method findMethod(Class clazz, String methodName,
            Class paramType) {
        return findMethod(clazz, methodName, new Class[] { paramType });
    }

    protected static Method findMethod(Class clazz, String methodName,
            Class[] paramTypes) {
        // TODO BeanDescImplへ移動
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(clazz);
        Method[] methods = beanDesc.getMethods(methodName);
        for (Method method : methods) {
            Class[] types = method.getParameterTypes();
            if (Arrays.equals(types, paramTypes)
                    && (method.getReturnType() == Void.TYPE)) {
                return method;
            }
        }
        throw new MethodNotFoundRuntimeException(clazz, methodName, paramTypes);
    }
}
