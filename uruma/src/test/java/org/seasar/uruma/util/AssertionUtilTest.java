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
package org.seasar.uruma.util;

import java.util.ArrayList;
import java.util.List;

import org.seasar.framework.exception.SIllegalArgumentException;

import junit.framework.TestCase;

/**
 * {@link AssertionUtil} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class AssertionUtilTest extends TestCase {

    /**
     * {@link AssertionUtil#assertNotNull(String, Object)} メソッドのテストです。<br />
     */
    public void testAssertNotNull() {
        try {
            AssertionUtil.assertNotNull("test1", new Object());
            assertTrue(true);
        } catch (SIllegalArgumentException e) {
            fail("test1");
        }

        try {
            AssertionUtil.assertNotNull("test2", null);
            fail("test2");
        } catch (SIllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * {@link AssertionUtil#assertNotEmpty(String, String)} メソッドのテストです。<br />
     */
    public void testAssertNotEmpty() {
        try {
            AssertionUtil.assertNotEmpty("test3", "sample");
            assertTrue(true);
        } catch (SIllegalArgumentException e) {
            fail("test3");
        }

        try {
            AssertionUtil.assertNotEmpty("test4", "");
            fail("test4");
        } catch (SIllegalArgumentException e) {
            assertTrue(true);
        }

        try {
            AssertionUtil.assertNotEmpty("test5", null);
            fail("test5");
        } catch (SIllegalArgumentException e) {
            assertTrue(true);
        }
    }

    /**
     * {@link AssertionUtil#assertInstanceOf(String, Class, Object)} メソッドのテストです。<br />
     */
    public void testAssertInstanceOf() {
        try {
            AssertionUtil.assertInstanceOf("test6", List.class,
                    new ArrayList<Object>());
            assertTrue(true);
        } catch (SIllegalArgumentException e) {
            fail("test6");
        }

        try {
            AssertionUtil.assertInstanceOf("test7", List.class, new Object());
            fail("test7");
        } catch (SIllegalArgumentException e) {
            assertTrue(true);
        }
    }
}
