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
package org.seasar.uruma.rcp.configuration.impl;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.base.AbstractUIElementContainer;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ConfigurationElement} のための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractConfigurationElement extends
        AbstractUIElementContainer implements ConfigurationElement {
    private List<ConfigurationElement> childElements = new ArrayList<ConfigurationElement>();

    private ConfigurationWriter configurationWriter;

    private String rcpId;

    /*
     * @see org.seasar.uruma.component.jface.AbstractUIElementContainer#addChild(org.seasar.uruma.component.UIElement)
     */
    @Override
    public void addChild(final UIElement child) {
        super.addChild(child);

        if (child instanceof ConfigurationElement) {
            childElements.add((ConfigurationElement) child);
        }
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#getElements()
     */
    public List<ConfigurationElement> getElements() {
        return childElements;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#setConfigurationWriter(org.seasar.uruma.rcp.configuration.ConfigurationWriter)
     */
    public void setConfigurationWriter(final ConfigurationWriter writer) {
        AssertionUtil.assertNotNull("writer", writer);
        this.configurationWriter = writer;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#writeConfiguration(java.io.Writer)
     */
    public void writeConfiguration(final Writer writer) {
        if (configurationWriter != null) {
            configurationWriter.writeStartTag(this, writer);
        }

        for (ConfigurationElement element : childElements) {
            element.writeConfiguration(writer);
        }

        if (configurationWriter != null) {
            configurationWriter.writeEndTag(this, writer);
        }
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#getRcpId()
     */
    public String getRcpId() {
        return rcpId;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#setRcpId(java.lang.String)
     */
    public void setRcpId(final String rcpId) {
        this.rcpId = rcpId;
    }
}