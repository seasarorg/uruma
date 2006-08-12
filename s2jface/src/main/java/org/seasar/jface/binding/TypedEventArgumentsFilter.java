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

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Event;
import org.seasar.jface.util.ClassUtil;

/**
 * @author bskuroneko
 * 
 */
public class TypedEventArgumentsFilter implements ArgumentsFilter {

    private Class<?>[] targetParameterTypes;

    public TypedEventArgumentsFilter(Method targetMethod) {
        this.targetParameterTypes = targetMethod.getParameterTypes();
    }

    public Object[] filter(Object[] args) {
        if (args == null) {
            return null;
        }

        if (targetParameterTypes.length != args.length) {
            return args;
        }

        Object[] filtedArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            Class<?> paramType = targetParameterTypes[i];
            Object arg = args[i];
            if (TypedEvent.class.isAssignableFrom(paramType)
                    && arg instanceof Event) {
                // TODO 別タイプのイベントでもインスタンス化できてしまうが、制限するかを検討
                filtedArgs[i] = ClassUtil.newInstance(paramType,
                        new Object[] { arg });
            } else {
                filtedArgs[i] = arg;
            }
        }
        return filtedArgs;
    }
}
