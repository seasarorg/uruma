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
package org.seasar.jface.template;

import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.exception.NotFoundException;

/**
 * @author y-komori
 * 
 */
public class TemplateBuilderTest extends S2FrameworkTestCase {
    protected TemplateBuilder builder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        builder = new TemplateBuilder();
    }

    /**
     * 画面定義XMLのビルドに関するテスト。</br>
     */
    public void testBuild() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath("TemplateBuilderTest.xml"));

        assertNotNull(template);

        assertNotNull(template.getWindowComponent());
    }

    /**
     * 継承した画面定義XMLからのプロパティ上書きテスト。</br>
     */
    public void testExtendProperty() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath("TemplateBuilderTest2.xml"));

        assertNotNull(template);

        assertEquals("1", "templateBuilderTest2", template.getWindowComponent()
                .getId());

        UIComponent testButton = template.find("testButton");
        assertEquals("2", "テストボタン2", testButton.getPropertyValue("text"));

        UIComponent testButton3 = template.find("testButton3");
        assertEquals("3", "false", testButton3.getPropertyValue("enabled"));
    }

    /**
     * ２階層に渡る継承に対するテスト。</br>
     */
    public void testTwiceExtendProperty() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath("TemplateBuilderTest3.xml"));

        assertNotNull(template);

        assertEquals("1", "templateBuilderTest3", template.getWindowComponent()
                .getId());

        UIComponent testButton = template.find("testButton");
        assertEquals("2", "テストボタン3", testButton.getPropertyValue("text"));

        UIComponent testButton3 = template.find("testButton3");
        assertEquals("3", "false", testButton3.getPropertyValue("visible"));
    }

    /**
     * 継承元コンポーネントが存在しない場合のテスト。</br>
     */
    public void testExtendPropertyIdNotFound() {
        try {
            TemplateComponent template = (TemplateComponent) builder
                    .build(convertPath("TemplateBuilderTest4.xml"));

            fail();
        } catch (NotFoundException ex) {
            System.err.println(ex);
            assertEquals(NotFoundException.EXTEND_TARGET_COMPONENT, ex
                    .getMessageCode());
        }
    }

    /**
     * 継承元コンポーネントのプロパティが存在しない場合のテスト。</br>
     */
    public void testExtendPropertyPropertyNotFound() {
        try {
            TemplateComponent template = (TemplateComponent) builder
                    .build(convertPath("TemplateBuilderTest5.xml"));

            fail();
        } catch (NotFoundException ex) {
            System.err.println(ex);
            assertEquals(NotFoundException.EXTEND_TARGET_PROPERTY, ex
                    .getMessageCode());
        }
    }

    /**
     * コンポーネントの継承(要素置き換えあり)のテスト。</br>
     */
    public void testExtendComponentReplace() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath("TemplateBuilderTest6.xml"));

        UIComponent testButton = template.find("testButton");
        assertEquals("1", "30", testButton.getPropertyValue("x"));
        assertEquals("2", "40", testButton.getPropertyValue("y"));
        assertEquals("3", "100", testButton.getPropertyValue("width"));
        assertEquals("4", "200", testButton.getPropertyValue("height"));
        assertEquals("5", "override", testButton.getPropertyValue("text"));
    }

    /**
     * コンポーネントの継承(要素置き換えなし)のテスト。</br>
     */
    public void testExtendComponentNonReplace() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath("TemplateBuilderTest7.xml"));

        UIComponent testButton = template.find("testButton");
        assertEquals("1", "30", testButton.getPropertyValue("x"));
        assertEquals("2", "40", testButton.getPropertyValue("y"));
        assertEquals("3", "100", testButton.getPropertyValue("width"));
        assertEquals("4", "200", testButton.getPropertyValue("height"));
        assertEquals("5", "override", testButton.getPropertyValue("text"));

        WindowComponent window = template.getWindowComponent();
        assertNotNull("6", window);
        assertEquals("7", "testApp", window.getId());
        assertEquals("8", "テストアプリケーション", window.getPropertyValue("title"));
    }
}
