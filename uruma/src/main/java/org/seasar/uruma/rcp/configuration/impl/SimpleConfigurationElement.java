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

import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;
import org.seasar.uruma.rcp.configuration.ConfigurationWriterFactory;
import org.seasar.uruma.util.AssertionUtil;

/**
 * シンプルな {@link ConfigurationElement} の基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class SimpleConfigurationElement implements
        ConfigurationElement {
    private List<ConfigurationElement> elements = new ArrayList<ConfigurationElement>();

    @ConfigurationAttribute(name = "id")
    private String rcpId;

    private ConfigurationWriter configurationWriter;

    /**
     * {@link SimpleConfigurationElement} を構築します。<br />
     */
    public SimpleConfigurationElement() {
        ConfigurationWriter writer = ConfigurationWriterFactory
                .getConfigurationWriter(getClass());
        setConfigurationWriter(writer);
    }

    /**
     * {@link ConfigurationElement} を追加します。<br />
     * 
     * @param element
     *            {@link ConfigurationElement} オブジェクト
     */
    public void addElement(final ConfigurationElement element) {
        AssertionUtil.assertNotNull("element", element);
        elements.add(element);
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#getElements()
     */
    public List<ConfigurationElement> getElements() {
        return this.elements;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#getRcpId()
     */
    public String getRcpId() {
        return this.rcpId;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#setConfigurationWriter(org.seasar.uruma.rcp.configuration.ConfigurationWriter)
     */
    public void setConfigurationWriter(final ConfigurationWriter writer) {
        this.configurationWriter = writer;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#setRcpId(java.lang.String)
     */
    public void setRcpId(final String rcpId) {
        this.rcpId = rcpId;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#writeConfiguration(java.io.Writer)
     */
    public void writeConfiguration(final Writer writer) {
        if (configurationWriter != null) {
            configurationWriter.writeStartTag(this, writer);
        }

        for (ConfigurationElement element : elements) {
            element.writeConfiguration(writer);
        }

        if (configurationWriter != null) {
            configurationWriter.writeEndTag(this, writer);
        }
    }

}
