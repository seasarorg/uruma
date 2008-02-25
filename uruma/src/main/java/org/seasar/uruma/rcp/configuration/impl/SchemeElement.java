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

/**
 * <code>scheme</code> 要素を表す {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori <a
 *         href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_bindings.html#e.scheme">scheme</a>
 */
public class SchemeElement extends AbstractConfigurationElement {
    /**
     * 要素名です。<br />
     */
    public static final String ELEMENT_NAME = "scheme";

    /**
     * スキーム ID です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String id;

    /**
     * スキーム名称です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String name;

    /**
     * スキームの説明です。
     */
    @ConfigurationAttribute
    public String description;

    /**
     * 親スキームの ID です。<br />
     */
    @ConfigurationAttribute
    public String parentId;

    /**
     * {@link SchemeElement} を構築します。<br />
     * 
     * @param id
     *            スキームID
     * @param name
     *            スキーム名称
     */
    public SchemeElement(final String id, final String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
