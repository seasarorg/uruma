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
package org.seasar.uruma.util.resource.impl;

import java.io.InputStream;
import java.net.URL;

import junit.framework.TestCase;

import org.seasar.uruma.util.resource.ResourceHandler;

/**
 * {@link FileResourceTraverser} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class FileResourceTraverserTest extends TestCase {
    /**
     * {@link FileResourceTraverser#traverse(URL, URL, ResourceHandler)}
     * メソッドのテストです。<br />
     */
    public void testTraverse() {
        FileResourceTraverser traverser = new FileResourceTraverser();
        URL rootUrl = getClass().getResource("/");
        URL originUrl = getClass().getResource("/org/seasar/uruma/util");

        try {
            traverser.traverse(rootUrl, originUrl, new TestResourceHandler());
            assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail(ex.getMessage());
        }

        try {
            traverser.traverse(null, originUrl, new TestResourceHandler());
            assertTrue(true);
        } catch (Exception ex) {
            ex.printStackTrace();
            fail(ex.getMessage());
        }
    }

    private class TestResourceHandler implements ResourceHandler {
        public void handle(final String rootPath, final String path,
                final InputStream is) {
            if (rootPath == null || path == null || is == null) {
                fail();
            }
        }
    }
}
