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
package org.seasar.jface.component.factory;

import org.seasar.framework.unit.S2FrameworkTestCase;
import org.seasar.jface.component.Template;

public class ComponentTreeBuilderTest extends S2FrameworkTestCase {
    private ComponentTreeBuilder builder;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        builder = new ComponentTreeBuilder();
    }

    /**
     * 画面定義XMLのビルドに関するテスト。</br>
     */
    public void testBuild() {
        Template template = (Template) builder
                .build(convertPath("org/seasar/jface/component/factory/DIdeJanken.xml"));

        assertNotNull(template);

        assertNotNull(template.getWindowComponent());
    }
}
