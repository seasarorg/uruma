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
package org.seasar.uruma.component.base;

import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.component.jface.CompositeComponent;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;

/**
 * 子要素を持たない {@link ConfigurationElement} の基底クラスです。<br />
 * 主に <code>viewPart</code> や <code>editorPart</code> を定義するためのクラスです。<br />
 * 
 * @author y-komori
 */
public abstract class PartConfigurationElement extends CompositeComponent
        implements ConfigurationElement {
    private static final List<ConfigurationElement> nullList = new ArrayList<ConfigurationElement>(
            0);

    protected ConfigurationWriter configurationWriter;

    @ConfigurationAttribute(name = "id")
    private String rcpId;

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#getElements()
     */
    public List<ConfigurationElement> getElements() {
        return nullList;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#setConfigurationWriter(org.seasar.uruma.rcp.configuration.ConfigurationWriter)
     */
    public void setConfigurationWriter(final ConfigurationWriter writer) {
        this.configurationWriter = writer;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#writeConfiguration(java.io.Writer)
     */
    public void writeConfiguration(final Writer writer) {
        if (configurationWriter != null) {
            configurationWriter.writeStartTag(this, writer);
            configurationWriter.writeEndTag(this, writer);
        }
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#getRcpId()
     */
    public String getRcpId() {
        return this.rcpId;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElement#setRcpId(java.lang.String)
     */
    public void setRcpId(final String rcpId) {
        this.rcpId = rcpId;
    }

}
