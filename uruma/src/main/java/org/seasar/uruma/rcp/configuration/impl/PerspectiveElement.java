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

import org.eclipse.ui.IPerspectiveFactory;
import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;

/**
 * <code>perspective</code> 要素のための {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori
 * @see <a
 *      href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_ui_perspectives.html#e.perspective">perspective</a>
 */
public class PerspectiveElement extends AbstractConfigurationElement {

    /**
     * {@link PerspectiveElement} を構築します。<br />
     */
    public PerspectiveElement() {
        super();
    }

    /**
     * {@link PerspectiveComponent} を元にして {@link PerspectiveElement} を構築します。<br />
     */
    public PerspectiveElement(final PerspectiveComponent component) {
        super();
        this.id = createRcpId(component.id);
        this.name = component.name;
        this.icon = component.icon;
        this.clazz = component.clazz;
        this.fixed = Boolean.getBoolean(component.fixed);
    }

    /**
     * ID です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String id;

    /**
     * パースペクティブの名称です。<br />
     */
    @ConfigurationAttribute(required = true)
    public String name;

    /**
     * {@link IPerspectiveFactory} を実装したクラスのフルネームです。<br />
     */
    @ConfigurationAttribute(name = "class", required = true)
    public String clazz;

    /**
     * アイコンのパスです。<br />
     */
    @ConfigurationAttribute
    public String icon;

    /**
     * <code>true</code> の場合、パースペクティブのレイアウトを固定します。<br />
     */
    @ConfigurationAttribute
    public boolean fixed;

}
