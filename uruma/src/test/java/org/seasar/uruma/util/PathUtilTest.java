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
package org.seasar.uruma.util;

import junit.framework.TestCase;

/**
 * {@link PathUtil} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class PathUtilTest extends TestCase {
    public void testCreatePath() {
        assertEquals("1", "org/seasar/jface/util/PathUtil.java", PathUtil
                .createPath("org/seasar/jface/util", "PathUtil.java"));

        assertEquals("2", "org/seasar/jface/util/PathUtil.java", PathUtil
                .createPath("org/seasar/jface/util/", "PathUtil.java"));

        assertEquals("3", "org/seasar/jface/util/PathUtil.java", PathUtil
                .createPath("", "org/seasar/jface/util/PathUtil.java"));

        assertEquals("4", "org/seasar/jface/util/PathUtil.java", PathUtil
                .createPath("org/seasar/jface/util",
                        "org/seasar/jface/util/PathUtil.java"));

        assertEquals("5", "/org/seasar/jface/abc/PathUtil.java", PathUtil
                .createPath("org/seasar/jface/util",
                        "/org/seasar/jface/abc/PathUtil.java"));

        assertEquals("6", "org/seasar/jface/util/../template/PathUtil.java",
                PathUtil.createPath("org/seasar/jface/util",
                        "../template/PathUtil.java"));
    }
}
