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
package org.seasar.uruma.binding.method.impl;

import java.lang.reflect.Method;

import org.seasar.eclipse.common.util.AbstractShellTest;
import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.binding.method.ArgumentsFilter;
import org.seasar.uruma.util.ClassUtil;

/**
 * {@link ArgumentsFilter} のテストを行うための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractArgumentFilterTest<T> extends AbstractShellTest {
    protected BeanDesc desc = BeanDescFactory.getBeanDesc(getClass());

    protected T createFilter(final String methodName) {
        Method method = desc.getMethod(methodName);
        return ClassUtil.<T> newInstance(getFilterType(), method);
    }

    protected T createFilter(final String methodName, final Class<?>... types) {
        Method method = desc.getMethod(methodName, types);
        return ClassUtil.<T> newInstance(getFilterType(), method);
    }

    protected abstract Class<T> getFilterType();
}
