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
package org.seasar.uruma.rcp.configuration.writer;

import java.io.StringWriter;

import junit.framework.TestCase;

import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.rcp.configuration.ConfigurationWriterFactory;
import org.seasar.uruma.rcp.configuration.impl.SimpleConfigurationElement;

/**
 * {@link GenericConfigurationWriter} に対するテストクラスです。<br />
 * 
 * @author y-komori
 */
public class GenericConfigurationWriterTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        ConfigurationWriterFactory.addWriter(new GenericConfigurationWriter(
                TestElement1.class, "element"));
        ConfigurationWriterFactory.addWriter(new GenericConfigurationWriter(
                TestElement2.class, "element", true));
        ConfigurationWriterFactory.addWriter(new GenericConfigurationWriter(
                TestElement3.class, "element", true));
        ConfigurationWriterFactory.addWriter(new GenericConfigurationWriter(
                TestElement4.class, "element"));
        ConfigurationWriterFactory.addWriter(new GenericConfigurationWriter(
                TestElement5.class, "child", true));
    }

    /**
     * 開始/終了タグがある場合のテストです。<br />
     */
    public void testWrite1() {
        TestElement1 element = new TestElement1();

        StringWriter sw = new StringWriter();
        element.writeConfiguration(sw);

        assertEquals(
                "<element attr1=\"abc\" attribute2=\"def\" attr3=\"123\" >\n</element>\n",
                sw.getBuffer().toString());
    }

    /**
     * 開始タグのみの場合のテストです。<br />
     */
    public void testWrite2() {
        TestElement2 element = new TestElement2();

        StringWriter sw = new StringWriter();
        element.writeConfiguration(sw);

        assertEquals(
                "<element attr1=\"abc\" attribute2=\"def\" attr3=\"123\" />\n",
                sw.getBuffer().toString());
    }

    /**
     * 継承されたクラスに対するテストです。<br />
     */
    public void testWrite3() {
        TestElement3 element = new TestElement3();

        StringWriter sw = new StringWriter();
        element.writeConfiguration(sw);

        assertEquals("<element attr4=\"ghi\" attribute5=\"jkl\" attr6=\"456\""
                + " attr1=\"abc\" attribute2=\"def\" attr3=\"123\" />\n", sw
                .getBuffer().toString());
    }

    /**
     * 親子関係を持つ場合のテストです。<br />
     */
    public void testWrite4() {
        TestElement4 element = new TestElement4();

        TestElement5 child1 = new TestElement5();
        child1.attr = "child1";
        element.addElement(child1);

        TestElement5 child2 = new TestElement5();
        child2.attr = "child2";
        element.addElement(child2);

        StringWriter sw = new StringWriter();
        element.writeConfiguration(sw);

        assertEquals("<element >\n<child attr=\"child1\" />\n"
                + "<child attr=\"child2\" />\n</element>\n", sw.getBuffer()
                .toString());
    }

    private static class TestElement1 extends SimpleConfigurationElement {
        @ConfigurationAttribute
        public String attr1 = "abc";

        @ConfigurationAttribute(name = "attribute2")
        public String attr2 = "def";

        @ConfigurationAttribute
        public int attr3 = 123;
    }

    private static class TestElement2 extends TestElement1 {
    }

    private static class TestElement3 extends TestElement1 {
        @ConfigurationAttribute
        public String attr4 = "ghi";

        @ConfigurationAttribute(name = "attribute5")
        public String attr5 = "jkl";

        @ConfigurationAttribute
        public int attr6 = 456;
    }

    private static class TestElement4 extends SimpleConfigurationElement {

    }

    private static class TestElement5 extends SimpleConfigurationElement {
        @ConfigurationAttribute
        public String attr;
    }
}
