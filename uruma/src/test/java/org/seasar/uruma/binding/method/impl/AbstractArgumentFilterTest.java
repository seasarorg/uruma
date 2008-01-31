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
import java.util.List;

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

    /**
     * 配列の各要素が同じであるかどうかをチェックします。<br />
     * 
     * @param message
     *            メッセージ
     * @param arg1
     *            引数1
     * @param arg2
     *            引数2
     */
    protected void assertArrayEquals(final String message, final Object[] arg1,
            final Object[] arg2) {
        if (arg1.length != arg2.length) {
            fail(message);
        }

        for (int i = 0; i < arg1.length; i++) {
            Class<?> arg1Class = arg1[i].getClass();
            Class<?> arg2Class = arg2[i].getClass();
            if (arg1Class.isArray() && arg2Class.isArray()) {
                assertArrayEquals(message, (Object[]) arg1[i],
                        (Object[]) arg2[i]);
            } else if (List.class.isAssignableFrom(arg1Class)
                    && List.class.isAssignableFrom(arg2Class)) {
                assertListEquals(message, List.class.cast(arg1[i]), List.class
                        .cast(arg2[i]));
            } else if (!arg1[i].equals(arg2[i])) {
                fail(message);
            }
        }
        assertTrue(message, true);
    }

    protected void assertListEquals(final String message, final List<?> arg1,
            final List<?> arg2) {
        if (arg1.size() != arg2.size()) {
            fail(message);
        }

        for (int i = 0; i < arg1.size(); i++) {
            if (!(arg1.get(i).equals(arg2.get(i)))) {
                fail(message);
            }
        }
    }

    protected abstract Class<T> getFilterType();
}
