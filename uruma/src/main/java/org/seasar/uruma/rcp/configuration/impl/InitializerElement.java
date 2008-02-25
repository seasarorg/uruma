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
package org.seasar.uruma.rcp.configuration.impl;

import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.util.AssertionUtil;

/**
 * <code>initializer</code> 要素を表す {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori <a
 *         href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_core_runtime_preferences.html#e.initializer">initializer</a>
 */
public class InitializerElement extends AbstractConfigurationElement {
    /**
     * 要素名です。<br />
     */
    public static final String ELEMENT_NAME = "initializer";

    /**
     * プリファレンスイニシャライザのクラス名です。<br />
     */
    @ConfigurationAttribute(name = "class", required = true)
    public String clazz;

    /**
     * {@link InitializerElement} を構築します。<br />
     * 
     * @param clazz
     *            プリファレンスイニシャライザのクラス名
     */
    public InitializerElement(final String clazz) {
        super();
        AssertionUtil.assertNotEmpty("clazz", clazz);
        this.clazz = clazz;
    }
}
