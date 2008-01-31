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

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Event;
import org.seasar.eclipse.common.util.AbstractShellTest;

/**
 * {@link TypedEventArgumentsFilter} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class TypedEventArgumentsFilterTest extends AbstractShellTest {

    /**
     * {@link TypedEventArgumentsFilterTest} を構築します。<br />
     */
    public TypedEventArgumentsFilterTest() {
        super(true);
    }

    /**
     * {@link TypedEventArgumentsFilter#filter(Object[])} メソッドのテストです。<br />
     */
    public void testFilter() throws Exception {
        TypedEventArgumentsFilter filter = cleateFilter("targetMethod1",
                SelectionEvent.class);
        Event e = new Event();
        e.widget = shell;
        Object[] result = filter.filter(new Object[] { e });

        assertEquals("1", 1, result.length);
        assertEquals("2", SelectionEvent.class, result[0].getClass());

    }

    private TypedEventArgumentsFilter cleateFilter(final String methodName,
            final Class<?>... args) throws NoSuchMethodException {
        Method method = getClass().getMethod(methodName, args);
        return new TypedEventArgumentsFilter(method);
    }

    /**
     * テスト対象メソッドです。
     */
    public void targetMethod1(final SelectionEvent event) {

    }
}
