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
package org.seasar.uruma.desc.impl;

import junit.framework.TestCase;

import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.desc.PartActionDesc;
import org.seasar.uruma.exception.IllegalMethodException;
import org.seasar.uruma.exception.InitializeMethodException;

/**
 * {@link PartActionDescImpl} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class PartActionDescImplTest extends TestCase {
    public void testSetupInitializeMethod() throws Exception {
        PartActionDesc target01 = new PartActionDescImpl(Target01.class);
        assertEquals("1", Target01.class
                .getMethod("initialize", (Class[]) null), target01
                .getInitializeMethod());

        try {
            new PartActionDescImpl(Target02.class);
            fail("2");
        } catch (IllegalMethodException ex) {
            assertTrue(true);
        }

        try {
            new PartActionDescImpl(Target03.class);
            fail("3");
        } catch (IllegalMethodException ex) {
            assertTrue(true);
        }
    }

    public void testInvokeInitializeMethod() {
        Target01 target01 = new Target01();
        PartActionDesc target01Desc = new PartActionDescImpl(target01
                .getClass());
        target01Desc.invokeInitializeMethod(target01);
        assertTrue(target01.invoked);
    }

    public void testInvokeInitializeMethodFail() {
        Target04 target04 = new Target04();
        PartActionDesc target04Desc = new PartActionDescImpl(target04
                .getClass());
        try {
            target04Desc.invokeInitializeMethod(target04);
            fail();
        } catch (InitializeMethodException ex) {
            assertTrue(true);
        }
    }

    static class Target01 {
        private boolean invoked;

        @InitializeMethod
        public void initialize() {
            invoked = true;
        }
    }

    static class Target02 {
        @InitializeMethod
        public void initialize1() {
        }

        @InitializeMethod
        public void initialize2() {
        }
    }

    static class Target03 {
        @InitializeMethod
        public int initialize(final int value) {
            return value;
        }
    }

    static class Target04 {
        @InitializeMethod
        public void initialize() {
            throw new IllegalArgumentException();
        }
    }
}
