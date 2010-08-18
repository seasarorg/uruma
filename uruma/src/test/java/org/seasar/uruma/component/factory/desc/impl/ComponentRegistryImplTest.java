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
package org.seasar.uruma.component.factory.desc.impl;

import static junit.framework.Assert.*;

import javax.annotation.Resource;

import org.junit.runner.RunWith;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.exception.SAXRuntimeException;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;
import org.seasar.uruma.component.factory.desc.ComponentRegistry;
import org.seasar.uruma.component.factory.desc.UrumaComponentDesc;

/**
 * {@link ComponentRegistryImpl} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
@RunWith(Seasar2.class)
public class ComponentRegistryImplTest {
    @Resource
    private TestContext ctx;

    @Resource
    private ComponentRegistry registry;

    // /*
    // * @see junit.framework.TestCase#setUp()
    // */
    // @Override
    // protected void setUp() throws Exception {
    // String path = convertPath(getClass().getSimpleName() + ".dicon");
    // include(path);
    // }

    /**
     * {@link ComponentRegistryImpl#registComponents()} メソッドのテストです。<br />
     */
    public void testRegistComponents() {
        registry.registComponents();
    }

    /**
     * {@link ComponentRegistryImpl#load(String)} メソッドのテストです。<br />
     */
    public void testLoad1() {
        String path = createPath("1");
        UrumaComponentDesc desc = ((ComponentRegistryImpl) registry).load(path);
        assertNotNull("1", desc);
        assertEquals("2", "tagName:testTag "
                + "tagHandler:org.seasar.uruma.dummy.TagHandler(arg1, arg2) "
                + "renderer:org.seasar.uruma.dummy.Renderer(arg1, arg2, arg3)",
                desc.toString());
    }

    /**
     * {@link ComponentRegistryImpl#load(String)} メソッドのテストです。<br />
     */
    public void testLoad2() {
        String path = createPath("2");
        try {
            ((ComponentRegistryImpl) registry).load(path);
            fail();
        } catch (SAXRuntimeException ex) {
            assertTrue(true);
        }
    }

    /**
     * {@link ComponentRegistryImpl#load(String)} メソッドのテストです。<br />
     */
    public void testLoad3() {
        String path = createPath("NG");
        try {
            ((ComponentRegistryImpl) registry).load(path);
            fail();
        } catch (ResourceNotFoundRuntimeException ex) {
            assertTrue(true);
        }
    }

    private String createPath(final String suffix) {
        return ctx.getTestClassPackagePath() + "/"
                + ctx.getTestClassShortName() + suffix + "."
                + ComponentRegistry.DESC_EXT;
    }
}
