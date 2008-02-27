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

/**
 * <code>application</code> 要素のための {@link ConfigurationElement} です。<br />
 * 
 * @author y-komori
 * @see <a
 *      href="http://help.eclipse.org/help33/topic/org.eclipse.platform.doc.isv/reference/extension-points/org_eclipse_core_runtime_applications.html">application</a>
 */
public class ApplicationElement extends AbstractConfigurationElementContainer {
    /**
     * 要素名です。<br />
     */
    public static final String ELEMENT_NAME = "application";

    /**
     * アプリケーションの ID です。<br />
     */
    @ConfigurationAttribute
    public String id;
}
