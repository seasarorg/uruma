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
    /**
     * {@link PathUtil#createPath(String, String)} メソッドのテストです。<br />
     */
    public void testCreatePath() {
        assertEquals("1", "org/seasar/uruma/util/PathUtil.java", PathUtil
                .createPath("org/seasar/uruma/util", "PathUtil.java"));

        assertEquals("2", "org/seasar/uruma/util/PathUtil.java", PathUtil
                .createPath("org/seasar/uruma/util/", "PathUtil.java"));

        assertEquals("3", "org/seasar/uruma/util/PathUtil.java", PathUtil
                .createPath("", "org/seasar/uruma/util/PathUtil.java"));

        assertEquals("4", "org/seasar/uruma/util/PathUtil.java", PathUtil
                .createPath("org/seasar/uruma/util",
                        "org/seasar/uruma/util/PathUtil.java"));

        assertEquals("5", "/org/seasar/uruma/abc/PathUtil.java", PathUtil
                .createPath("org/seasar/uruma/util",
                        "/org/seasar/uruma/abc/PathUtil.java"));

        assertEquals("6", "org/seasar/uruma/util/../template/PathUtil.java",
                PathUtil.createPath("org/seasar/uruma/util",
                        "../template/PathUtil.java"));
    }

    /**
     * {@link PathUtil#replaceSeparator(String)} メソッドのテストです。<br />
     */
    public void testReplaceSeparator() {
        assertEquals("1", "abc/def/ghi", PathUtil
                .replaceSeparator("abc\\def\\ghi"));

        assertEquals("2", "", PathUtil.replaceSeparator(null));
    }

    /**
     * {@link PathUtil#getRelativePath(String, String)} メソッドのテストです。<br />
     */
    public void testGetRelativePath() {
        assertEquals("1", "seasar", PathUtil.getRelativePath("c:/org/",
                "c:/org/seasar"));

        assertEquals("2", "seasar/uruma", PathUtil.getRelativePath("c:/org/",
                "c:/org/seasar/uruma"));

        assertEquals("3", "c:/org/seasar/uruma", PathUtil.getRelativePath(
                "c:/org/seasar/framework", "c:/org/seasar/uruma"));

        try {
            PathUtil.getRelativePath(null, "abc");
            fail("4");
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }

        try {
            PathUtil.getRelativePath("abc", null);
            fail("5");
        } catch (IllegalArgumentException ex) {
            assertTrue(true);
        }
    }

    /**
     * {@link PathUtil#getParent(String)} メソッドのテストです。<br />
     */
    public void testGetParent() {
        assertEquals("1", null, PathUtil.getParent(null));

        assertEquals("2", "c:/org/seasar/uruma", PathUtil
                .getParent("c:/org/seasar/uruma/test.java"));

        assertEquals("3", "c:/org/seasar/uruma", PathUtil
                .getParent("c:/org/seasar/uruma/"));

        assertEquals("4", "c:\\org\\seasar\\uruma", PathUtil
                .getParent("c:\\org\\seasar\\uruma\\test.java"));

        assertEquals("5", "c:\\org\\seasar\\uruma", PathUtil
                .getParent("c:\\org\\seasar\\uruma\\"));

        assertEquals("6", "c:/org\\seasar/uruma", PathUtil
                .getParent("c:/org\\seasar/uruma\\test.java"));

        assertEquals("7", "c:\\org/seasar\\uruma", PathUtil
                .getParent("c:\\org/seasar\\uruma/test.java"));

        assertEquals("8", "test", PathUtil.getParent("test"));
    }

    /**
     * {@link PathUtil#getFileName(String)} メソッドのテストです。<br />
     */
    public void testGetFileName() {
        assertEquals("1", null, PathUtil.getFileName(null));

        assertEquals("2", "test.java", PathUtil
                .getFileName("c:/org/seasar/uruma/test.java"));

        assertEquals("3", "", PathUtil.getFileName("c:/org/seasar/uruma/"));

        assertEquals("4", "test.java", PathUtil
                .getFileName("c:\\org\\seasar\\uruma\\test.java"));

        assertEquals("5", "", PathUtil.getFileName("c:\\org\\seasar\\uruma\\"));

        assertEquals("6", "test.java", PathUtil
                .getFileName("c:/org\\seasar/uruma\\test.java"));

        assertEquals("7", "test.java", PathUtil
                .getFileName("c:\\org/seasar\\uruma/test.java"));

        assertEquals("8", "test", PathUtil.getFileName("test"));
    }

    /**
     * {@link PathUtil#getBaseName(String)} メソッドのテストです。<br />
     */
    public void testGetBaseName() {
        assertEquals("1", "test", PathUtil.getBaseName("test.txt"));

        assertEquals("2", "test", PathUtil.getBaseName("test."));

        assertEquals("3", "test", PathUtil.getBaseName("test"));

        assertEquals("4", null, PathUtil.getBaseName(null));

        assertEquals("5", "test.abc", PathUtil.getBaseName("test.abc.txt"));
    }

    /**
     * {@link PathUtil#getExt(String)} メソッドのテストです。<br />
     */
    public void testGetExt() {
        assertNull("1", PathUtil.getExt(null));
        assertEquals("2", "txt", PathUtil.getExt("test.txt"));
        assertEquals("3", "txt", PathUtil.getExt("test.abc.txt"));
        assertEquals("4", "", PathUtil.getExt("test"));
        assertEquals("5", "", PathUtil.getExt(""));
    }
}
