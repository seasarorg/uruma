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
package org.seasar.jface.binding.impl;

import junit.framework.TestCase;

import org.seasar.jface.annotation.InitializeMethod;
import org.seasar.jface.binding.ActionDesc;
import org.seasar.jface.exception.InitializeMethodException;

/**
 * @author y-komori
 */
public class ActionDescImplTest extends TestCase {
    public void testSetupInitializeMethod() throws Exception {
        ActionDesc target01 = new ActionDescImpl(Target01.class);
        assertEquals("1", Target01.class
                .getMethod("initialize", (Class[]) null), target01
                .getInitializeMethod());

        try {
            ActionDesc target02 = new ActionDescImpl(Target02.class);
            fail("2");
        } catch (InitializeMethodException ex) {
            assertTrue(true);
        }

        try {
            ActionDesc target03 = new ActionDescImpl(Target03.class);
            fail("3");
        } catch (InitializeMethodException ex) {
            assertTrue(true);
        }
    }

    public void testInvokeInitializeMethod() {
        Target01 target01 = new Target01();
        ActionDesc target01Desc = new ActionDescImpl(target01.getClass());
        target01Desc.invokeInitializeMethod(target01);
        assertTrue(target01.invoked);
    }

    public void testInvokeInitializeMethodFail() {
        Target04 target04 = new Target04();
        ActionDesc target04Desc = new ActionDescImpl(target04.getClass());
        try {
            target04Desc.invokeInitializeMethod(target04);
            fail();
        } catch (InitializeMethodException ex) {
            assertTrue(true);
        }
    }

    class Target01 {
        private boolean invoked;

        @InitializeMethod
        public void initialize() {
            invoked = true;
        }
    }

    class Target02 {
        @InitializeMethod
        public void initialize1() {
        }

        @InitializeMethod
        public void initialize2() {
        }
    }

    class Target03 {
        @InitializeMethod
        public int initialize(int value) {
            return value;
        }
    }

    class Target04 {
        @InitializeMethod
        public void initialize() {
            throw new IllegalArgumentException();
        }
    }
}
