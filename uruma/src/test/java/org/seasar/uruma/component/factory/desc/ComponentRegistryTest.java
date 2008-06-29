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
package org.seasar.uruma.component.factory.desc;

import junit.framework.TestCase;

import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.exception.SAXRuntimeException;
import org.seasar.framework.util.ResourceUtil;

/**
 * {@link ComponentRegistry} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class ComponentRegistryTest extends TestCase {
    /**
     * 
     */
    public void testRegistComponents() {
        ComponentRegistry registry = new ComponentRegistry();
        // registry.registComponents();
    }

    /**
     * {@link ComponentRegistry#load(String)} メソッドのテストです。<br />
     */
    public void testLoad1() {
        String path = createPath("1");
        ComponentRegistry registry = new ComponentRegistry();
        UrumaComponentDesc desc = registry.load(path);
        assertNotNull("1", desc);
        assertEquals("2", "tagName:testTag "
                + "tagHandler:org.seasar.uruma.dummy.TagHandler(arg1, arg2) "
                + "renderer:org.seasar.uruma.dummy.Renderer(arg1, arg2, arg3)",
                desc.toString());
    }

    /**
     * {@link ComponentRegistry#load(String)} メソッドのテストです。<br />
     */
    public void testLoad2() {
        String path = createPath("2");
        ComponentRegistry registry = new ComponentRegistry();
        try {
            registry.load(path);
            fail();
        } catch (SAXRuntimeException ex) {
            assertTrue(true);
        }
    }

    /**
     * {@link ComponentRegistry#load(String)} メソッドのテストです。<br />
     */
    public void testLoad3() {
        String path = createPath("NG");
        ComponentRegistry registry = new ComponentRegistry();
        try {
            registry.load(path);
            fail();
        } catch (ResourceNotFoundRuntimeException ex) {
            assertTrue(true);
        }
    }

    private String createPath(final String suffix) {
        return ResourceUtil.convertPath(getClass().getSimpleName() + suffix
                + ".ucd", getClass());
    }
}
