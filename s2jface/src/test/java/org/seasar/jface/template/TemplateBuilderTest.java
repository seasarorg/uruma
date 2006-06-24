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

    public void testBuild() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath("TemplateBuilderTest.xml"));

        assertNotNull(template);

        WindowComponent window = (WindowComponent) template.getChildren()
                .iterator().next();
        assertNotNull(window);
    }

    public void testExtendProperty() {
        TemplateComponent template = (TemplateComponent) builder
                .build(convertPath("TemplateBuilderTest2.xml"));

        assertNotNull(template);

        UIComponent testButton = template.find("testButton");
        assertEquals("テストボタン2", testButton.getPropertyValue("text"));

        UIComponent testButton3 = template.find("testButton3");
        assertEquals("false", testButton3.getPropertyValue("enabled"));
    }
}
