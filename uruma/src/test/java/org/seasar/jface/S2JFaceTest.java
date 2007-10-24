/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.jface;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

public class S2JFaceTest extends TestCase {
    private static final String PATH = "org/seasar/jface/S2JFaceTest.dicon";

    public void testInit() {
        S2JFace.destroy();
        S2JFace.setConfigPath(PATH);
        S2JFace.getInstance();
        List list = (List) SingletonS2ContainerFactory.getContainer()
                .getComponent(List.class);
        assertNotNull(list);
        assertEquals(ArrayList.class, list.getClass());
    }
}