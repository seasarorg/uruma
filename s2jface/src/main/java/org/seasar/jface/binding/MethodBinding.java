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
import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.util.MethodUtil;
import org.seasar.jface.util.AssertionUtil;

/**
 * @author y-komori
 * 
 */
public class MethodBinding {
    private Object target;

    private Method method;

    private List<ArgumentsFilter> argumentsFilterList = new ArrayList<ArgumentsFilter>();

    public MethodBinding(Object target, Method method) {
        AssertionUtil.assertNotNull("target", target);
        AssertionUtil.assertNotNull("method", method);

        this.target = target;
        this.method = method;
    }

    public Object invoke() {
        return invoke(null);
    }

    public Object invoke(Object[] args) {
        Object[] filtedArgs = args;
        for (ArgumentsFilter filter : argumentsFilterList) {
            filtedArgs = filter.filter(filtedArgs);
        }
        return MethodUtil.invoke(method, target, filtedArgs);
    }

    public void addArgumentsFilter(ArgumentsFilter argumentsFilter) {
        this.argumentsFilterList.add(argumentsFilter);
    }

    public Method getMethod() {
        return this.method;
    }

    public Object getTarget() {
        return this.target;
    }
}
