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

import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;
import org.seasar.uruma.rcp.configuration.ConfigurationWriterFactory;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ConfigurationElement} のための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractConfigurationElement implements
        ConfigurationElement {
    protected ConfigurationWriter configurationWriter;

    /**
     * {@link AbstractConfigurationElement} を構築します。<br />
     */
    public AbstractConfigurationElement() {
        ConfigurationWriter writer = ConfigurationWriterFactory
                .getConfigurationWriter(getClass());
        setConfigurationWriter(writer);
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
            configurationWriter.writeEndTag(this, writer);
        }
    }
}
