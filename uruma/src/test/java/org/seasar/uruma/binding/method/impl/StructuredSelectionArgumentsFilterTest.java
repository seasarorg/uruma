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
import java.util.Arrays;
import java.util.List;

import junitx.framework.ArrayAssert;

import org.eclipse.jface.viewers.StructuredSelection;
import org.seasar.uruma.exception.UIllegalArgumentException;

/**
 * {@link StructuredSelectionArgumentsFilter} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class StructuredSelectionArgumentsFilterTest extends
        AbstractArgumentFilterTest<StructuredSelectionArgumentsFilter> {

    /**
     * {@link StructuredSelectionArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter1() {
        StructuredSelectionArgumentsFilter filter = createFilter("targetMethod1");

        assertNull("1", filter.filter(null));

        StructuredSelection selection = new StructuredSelection("arg");
        assertNull("2", filter.filter(new Object[] { selection }));
    }

    /**
     * {@link StructuredSelectionArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter2() {
        StructuredSelectionArgumentsFilter filter = createFilter(
                "targetMethod2", String.class);

        StructuredSelection selection = new StructuredSelection("arg");

        Object[] expected = new Object[] { "arg" };
        Object[] actual = filter.filter(new Object[] { selection });
        ArrayAssert.assertEquals(expected, actual);
    }

    /**
     * {@link StructuredSelectionArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter3() {
        StructuredSelectionArgumentsFilter filter = createFilter(
                "targetMethod3", String[].class);

        String[] args = new String[] { "arg1", "arg2", "arg3" };
        StructuredSelection selection = new StructuredSelection(args);

        Object[] expected = new Object[] { args };
        Object[] actual = filter.filter(new Object[] { selection });
        assertEquals(1, actual.length);
        ArrayAssert.assertEquals((Object[]) expected[0], (Object[]) actual[0]);
    }

    /**
     * {@link StructuredSelectionArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter4() {
        StructuredSelectionArgumentsFilter filter = createFilter(
                "targetMethod4", List.class);

        String[] args = new String[] { "arg1", "arg2", "arg3" };
        StructuredSelection selection = new StructuredSelection(args);

        Object[] expected = new Object[] { Arrays.<String> asList(args) };
        Object[] actual = filter.filter(new Object[] { selection });
        ArrayAssert.assertEquals(expected, actual);
    }

    /**
     * {@link StructuredSelectionArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter5() {
        try {
            Method method = desc.getMethod("targetMethod5", new Class[] {
                    String.class, String.class });
            new StructuredSelectionArgumentsFilter(method);
            fail();
        } catch (UIllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod1() {

    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod2(final String arg) {

    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod3(final String[] arg) {

    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod4(final List<String> arg) {

    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod5(final String ng1, final String ng2) {

    }

    /*
     * @see org.seasar.uruma.binding.method.impl.AbstractArgumentFilterTest#getFilterType()
     */
    @Override
    protected Class<StructuredSelectionArgumentsFilter> getFilterType() {
        return StructuredSelectionArgumentsFilter.class;
    }
}
