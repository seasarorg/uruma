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

import java.net.URL;

import junit.framework.TestCase;

import org.seasar.framework.util.ResourceUtil;

/**
 * {@link PathUtil} のためのテストクラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$
 */
public class PathUtilTest extends TestCase {
    /**
     * {@link PathUtil#createPath(String, String)} メソッドのテストです。<br />
     */
    public void testCreatePath() {
        assertEquals("1", "org/seasar/uruma/util/PathUtil.java", PathUtil.createPath(
                "org/seasar/uruma/util", "PathUtil.java"));

        assertEquals("2", "org/seasar/uruma/util/PathUtil.java", PathUtil.createPath(
                "org/seasar/uruma/util/", "PathUtil.java"));

        assertEquals("3", "org/seasar/uruma/util/PathUtil.java", PathUtil.createPath("",
                "org/seasar/uruma/util/PathUtil.java"));

        assertEquals("4", "org/seasar/uruma/util/PathUtil.java", PathUtil.createPath(
                "org/seasar/uruma/util", "org/seasar/uruma/util/PathUtil.java"));

        assertEquals("5", "/org/seasar/uruma/abc/PathUtil.java", PathUtil.createPath(
                "org/seasar/uruma/util", "/org/seasar/uruma/abc/PathUtil.java"));

        assertEquals("6", "org/seasar/uruma/util/../template/PathUtil.java", PathUtil.createPath(
                "org/seasar/uruma/util", "../template/PathUtil.java"));
    }

    /**
     * {@link PathUtil#createURL(URL, String)} メソッドのテストです。<br />
     */
    public void testCreateURL_URL_String() {
        URL url = ResourceUtil.getResource(getClass().getName().replace('.', '/') + ".class");
        URL parentUrl = PathUtil.getParentURL(url);
        URL actualUrl = PathUtil.createURL(parentUrl, "Test.txt");

        assertEquals("1", parentUrl.toExternalForm() + "/Test.txt", actualUrl.toExternalForm());
    }

    /**
     * {@link PathUtil#createURL(URL, String, boolean)} メソッドのテストです。<br />
     */
    public void testCreateURL_URL_String_boolean() {
        URL url = ResourceUtil.getResource(getClass().getName().replace('.', '/') + ".class");
        URL parentUrl = PathUtil.getParentURL(url);

        String expectedPath = PathUtil.getParent(PathUtil.getParent(parentUrl.toExternalForm()))
                + "/Test.txt";
        assertEquals("1", expectedPath, PathUtil.createURL(parentUrl, "../../Test.txt", true)
                .toExternalForm());
    }

    /**
     * {@link PathUtil#replaceSeparator(String)} メソッドのテストです。<br />
     */
    public void testReplaceSeparator() {
        assertEquals("1", "abc/def/ghi", PathUtil.replaceSeparator("abc\\def\\ghi"));

        assertEquals("2", "", PathUtil.replaceSeparator(null));
    }

    /**
     * {@link PathUtil#getRelativePath(String, String)} メソッドのテストです。<br />
     */
    public void testGetRelativePath() {
        assertEquals("1", "seasar", PathUtil.getRelativePath("c:/org/", "c:/org/seasar"));

        assertEquals("2", "seasar/uruma", PathUtil
                .getRelativePath("c:/org/", "c:/org/seasar/uruma"));

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

        assertEquals("3", "c:/org/seasar/uruma", PathUtil.getParent("c:/org/seasar/uruma/"));

        assertEquals("4", "c:\\org\\seasar\\uruma", PathUtil
                .getParent("c:\\org\\seasar\\uruma\\test.java"));

        assertEquals("5", "c:\\org\\seasar\\uruma", PathUtil.getParent("c:\\org\\seasar\\uruma\\"));

        assertEquals("6", "c:/org\\seasar/uruma", PathUtil
                .getParent("c:/org\\seasar/uruma\\test.java"));

        assertEquals("7", "c:\\org/seasar\\uruma", PathUtil
                .getParent("c:\\org/seasar\\uruma/test.java"));

        assertEquals("8", "test", PathUtil.getParent("test"));
    }

    /**
     * {@link PathUtil#getParentURL(URL)} メソッドのテストです。<br />
     */
    public void testGetParentURL_URL() {
        URL url = ResourceUtil.getResource(getClass().getName().replace('.', '/') + ".class");
        URL parentURL = PathUtil.getParentURL(url);
        assertEquals("1", PathUtil.getParent(url.toExternalForm()), parentURL.toExternalForm());

        assertNull("2", PathUtil.getParentURL((URL) null));
    }

    /**
     * {@link PathUtil#getParentURL(Class)} メソッドのテストです。<br />
     */
    public void testGetParentURL_Class() {
        URL url = ResourceUtil.getResource(getClass().getName().replace('.', '/') + ".class");
        URL parentURL = PathUtil.getParentURL(getClass());
        assertEquals("1", PathUtil.getParent(url.toExternalForm()), parentURL.toExternalForm());

        assertNull("2", PathUtil.getParentURL((Class<?>) null));
    }

    /**
     * {@link PathUtil#getFileName(String)} メソッドのテストです。<br />
     */
    public void testGetFileName() {
        assertEquals("1", null, PathUtil.getFileName(null));

        assertEquals("2", "test.java", PathUtil.getFileName("c:/org/seasar/uruma/test.java"));

        assertEquals("3", "", PathUtil.getFileName("c:/org/seasar/uruma/"));

        assertEquals("4", "test.java", PathUtil.getFileName("c:\\org\\seasar\\uruma\\test.java"));

        assertEquals("5", "", PathUtil.getFileName("c:\\org\\seasar\\uruma\\"));

        assertEquals("6", "test.java", PathUtil.getFileName("c:/org\\seasar/uruma\\test.java"));

        assertEquals("7", "test.java", PathUtil.getFileName("c:\\org/seasar\\uruma/test.java"));

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

    /**
     * {@link PathUtil#getPackagePath(Class)} メソッドのテストです。<br />
     */
    public void testGetPackagePath() {
        assertEquals("1", "", PathUtil.getPackagePath(null));
        assertEquals("2", "org/seasar/uruma/util", PathUtil.getPackagePath(getClass()));
    }

    /**
     * {@link PathUtil#getPath(Class, String)} メソッドのテストです。<br />
     */
    public void testGetPath() {
        assertEquals("1", "org/seasar/uruma/util/filename.txt", PathUtil.getPath(getClass(),
                "filename.txt"));
        assertEquals("2", "filename.txt", PathUtil.getPath(null, "filename.txt"));
        assertEquals("3", "org/seasar/uruma/util/", PathUtil.getPath(getClass(), null));
        assertEquals("4", "", PathUtil.getPath(null, null));
    }

    /**
     * {@link PathUtil#normalizeRelativePath(String)} メソッドのテストです。<br />
     */
    public void testNormalizeRelativePath() {
        assertEquals("1", "", PathUtil.normalizeRelativePath(""));
        assertEquals("2", "abc/def/ghi/jkl", PathUtil.normalizeRelativePath("abc/def/ghi/jkl"));
        assertEquals("3", "/abc/def/ghi/jkl", PathUtil.normalizeRelativePath("/abc/def/ghi/jkl"));
        assertEquals("4", "abc/def/ghi/jkl/", PathUtil.normalizeRelativePath("abc/def/ghi/jkl/"));
        assertEquals("5", "/abc/def/ghi", PathUtil.normalizeRelativePath("/abc/def/ghi/jkl/.."));
        assertEquals("6", "/abc/def/ghi/", PathUtil.normalizeRelativePath("/abc/def/ghi/jkl/../"));
        assertEquals("7", "/abc/def/jkl", PathUtil.normalizeRelativePath("/abc/def/ghi/../jkl"));
        assertEquals("8", "/jkl", PathUtil.normalizeRelativePath("/abc/../ghi/../jkl"));
        assertEquals("9", "/ghi", PathUtil.normalizeRelativePath("/abc/../ghi/jkl/.."));
        assertEquals("10", "/abc/ghi/jkl", PathUtil.normalizeRelativePath("/abc/def/../ghi/jkl"));
        assertEquals("11", "/ghi/jkl", PathUtil.normalizeRelativePath("/abc/def/../../ghi/jkl"));
        assertEquals("12", "/ghi/jkl/", PathUtil.normalizeRelativePath("/abc/def/../../ghi/jkl/"));
        assertEquals("13", "ghi/jkl", PathUtil.normalizeRelativePath("/abc/def/../../../ghi/jkl"));
        assertEquals("14", "../ghi/jkl", PathUtil.normalizeRelativePath("abc/def/../../../ghi/jkl"));
        assertEquals("15", "../abc/def/ghi", PathUtil.normalizeRelativePath("../abc/def/ghi"));
        assertEquals("16", "abc/def/ghi", PathUtil.normalizeRelativePath("/../abc/def/ghi"));
        assertEquals("17", "abc/ghi/jkl", PathUtil.normalizeRelativePath("abc/./ghi/jkl"));
        assertEquals("18", "def/ghi/jkl", PathUtil.normalizeRelativePath("./def/ghi/jkl"));
        assertEquals("19", "abc/def/ghi", PathUtil.normalizeRelativePath("abc/def/ghi/."));
        assertEquals("20", "file:///C:/abc/ghi.txt", PathUtil
                .normalizeRelativePath("file:///C:/abc/def/../ghi.txt"));
        assertEquals("21", "abc/def", PathUtil.normalizeRelativePath("abc/./def"));
    }

    /**
     * {@link PathUtil#getClassFilePath(Class)} メソッドのテストです。<br />
     */
    public void testGetClassFilePath() {
        assertEquals("1", "org/seasar/uruma/util/PathUtilTest.class", PathUtil
                .getClassFilePath(getClass()));
        assertEquals("2", "", PathUtil.getClassFilePath(null));
    }

    /**
     * {@link PathUtil#concat(String, String)} メソッドのテストです。<br />
     */
    public void testConcat() {
        assertNull("1", PathUtil.concat(null, null));
        assertEquals("2", "abc", PathUtil.concat("abc", null));
        assertEquals("3", "abc", PathUtil.concat(null, "abc"));
        assertEquals("4", "/abc/", PathUtil.concat("/abc/", null));
        assertEquals("5", "/abc/", PathUtil.concat("/abc/", ""));
        assertEquals("6", "/def/", PathUtil.concat(null, "/def/"));
        assertEquals("7", "/def/", PathUtil.concat("", "/def/"));

        assertEquals("8", "/abc/def/", PathUtil.concat("/abc", "def/"));
        assertEquals("9", "/abc/def/", PathUtil.concat("/abc/", "def/"));
        assertEquals("10", "/abc/def/", PathUtil.concat("/abc", "/def/"));
        assertEquals("11", "/abc/def/", PathUtil.concat("/abc/", "/def/"));
    }
}
