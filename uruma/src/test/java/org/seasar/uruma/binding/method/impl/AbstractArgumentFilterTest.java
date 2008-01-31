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

import junit.framework.TestCase;

import org.seasar.uruma.binding.method.ArgumentsFilter;

/**
 * {@link ArgumentsFilter} のテストを行うための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractArgumentFilterTest extends TestCase {

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
    protected void assertEqualArray(final String message, final Object[] arg1,
            final Object[] arg2) {
        if (arg1.length != arg2.length) {
            fail(message);
        }

        for (int i = 0; i < arg1.length; i++) {
            if (!arg1[i].equals(arg2[i])) {
                fail(message);
            }
        }
        assertTrue(message, true);
    }

}