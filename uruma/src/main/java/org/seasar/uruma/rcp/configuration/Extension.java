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
package org.seasar.uruma.rcp.configuration;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IExtension;
import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.util.AssertionUtil;

/**
 * <code>extension</code> 要素を表すクラスです。<br />
 * 
 * @see IExtension
 * @author y-komori
 */
public class Extension implements ConfigurationElement {
    // TODO あとでインターフェース化する
    @ConfigurationAttribute(name = "id")
    private String rcpId;

    @ConfigurationAttribute(required = true)
    private String point;

    private ConfigurationWriter configurationWriter;

    private List<ConfigurationElement> elements = new ArrayList<ConfigurationElement>();

    /**
     * {@link Extension} を構築します。<br />
     * 
     * @param point
     *            <code>point</code> 属性
     */
    Extension(final String point) {
        AssertionUtil.assertNotEmpty("point", point);
        this.point = point;
    }

    /**
     * <code>point</code> 属性を返します。<br />
     * 
     * @return <code>point</code> 属性
     */
    public String getPoint() {
        return this.point;
    }

    /**
     * {@link ConfigurationElement} を追加します。<br />
     * 
     * @param configurationElement
     *            {@link ConfigurationElement}
     */
    public void addConfigurationElement(
            final ConfigurationElement configurationElement) {
        AssertionUtil.assertNotNull("configurationElement",
                configurationElement);
        this.elements.add(configurationElement);
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

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return this.point;
    }
}
