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
package org.seasar.uruma.rcp.configuration.elements;

import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.util.AssertionUtil;

/**
 * <code>category</code> 要素を表す {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori
 * @see <a
 *      href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_commands.html#e.category">category</a>
 */
public class CategoryElement extends AbstractConfigurationElement {
    /**
     * 要素名です。<br />
     */
    public static final String ELEMENT_NAME = "category";

    /**
     * {@link CategoryElement} を構築します。<br />
     * 
     * @param id
     *            カテゴリ ID
     * @param name
     *            カテゴリ名称
     */
    public CategoryElement(final String id, final String name) {
        super();

        AssertionUtil.assertNotNull("id", id);
        AssertionUtil.assertNotNull("name", name);
        this.id = id;
        this.name = name;
    }

    /**
     * カテゴリの ID です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String id;

    /**
     * カテゴリの名称です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String name;

    /**
     * カテゴリの詳細説明です。<br />
     */
    @ConfigurationAttribute
    public String description;
}
