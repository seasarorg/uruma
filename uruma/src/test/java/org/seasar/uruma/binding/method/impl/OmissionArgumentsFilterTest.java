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

/**
 * {@link OmissionArgumentsFilter} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class OmissionArgumentsFilterTest extends AbstractArgumentFilterTest {
    /**
     * {@link OmissionArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter1() throws Exception {
        OmissionArgumentsFilter filter = cleateFilter("targetMethod1");
        // 引数が同数の場合
        assertNull("1", filter.filter(null));

        // 引数が多い場合
        assertEqualArray("2", new Object[] {}, filter
                .filter(new Object[] { "test" }));
    }

    /**
     * {@link OmissionArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter2() throws Exception {
        OmissionArgumentsFilter filter = cleateFilter("targetMethod2",
                Object.class);

        // 引数が同数の場合
        Object[] args = new Object[] { "test1" };
        assertEqualArray("1", args, filter.filter(args));

        // 引数が多いの場合
        assertEqualArray("1", args, filter.filter(new Object[] { "test1",
                "test2" }));
    }

    /**
     * {@link OmissionArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter3() throws Exception {
        OmissionArgumentsFilter filter = cleateFilter("targetMethod3",
                Object.class, Object.class);

        // 引数が同数の場合
        Object[] args = new Object[] { "test1", "test2" };
        assertEqualArray("1", args, filter.filter(args));

        // 引数が多いの場合
        assertEqualArray("2", args, filter.filter(new Object[] { "test1",
                "test2", "test3" }));
    }

    private OmissionArgumentsFilter cleateFilter(final String methodName,
            final Class<?>... args) throws NoSuchMethodException {
        Method method = getClass().getMethod(methodName, args);
        return new OmissionArgumentsFilter(method);
    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod1() {

    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod2(final Object arg1) {

    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod3(final Object arg1, final Object arg2) {

    }
}
