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
package org.seasar.jface.component.impl;

import junit.framework.TestCase;

import org.seasar.jface.component.Inheritance;

/**
 * @author y-komori
 * 
 */
public class InheritanceFactoryTest extends TestCase {
    public void testCreateInheritance() {
        assertEquals("1", Inheritance.NULL, InheritanceFactory
                .createInheritance("null"));
        assertEquals("2", Inheritance.NULL, InheritanceFactory
                .createInheritance("NULL"));

        assertEquals("3", Inheritance.NONE, InheritanceFactory
                .createInheritance("none"));
        assertEquals("4", Inheritance.NONE, InheritanceFactory
                .createInheritance("NONE"));

        assertEquals("5", Inheritance.CHILD, InheritanceFactory
                .createInheritance("child"));
        assertEquals("6", Inheritance.CHILD, InheritanceFactory
                .createInheritance("CHILD"));

        assertEquals("7", Inheritance.CHILD_ONLY, InheritanceFactory
                .createInheritance("childOnly"));
        assertEquals("8", Inheritance.CHILD_ONLY, InheritanceFactory
                .createInheritance("CHILDONLY"));

        assertEquals("9", Inheritance.DESCENDANT, InheritanceFactory
                .createInheritance("descendant"));
        assertEquals("10", Inheritance.DESCENDANT, InheritanceFactory
                .createInheritance("DESCENDANT"));

        assertEquals("11", Inheritance.DESCENDANT_ONLY, InheritanceFactory
                .createInheritance("descendantOnly"));
        assertEquals("12", Inheritance.DESCENDANT_ONLY, InheritanceFactory
                .createInheritance("DESCENDANTONLY"));

        assertEquals("13", Inheritance.NULL, InheritanceFactory
                .createInheritance(null));
        assertEquals("14", Inheritance.NULL, InheritanceFactory
                .createInheritance("dummy"));
    }
}
