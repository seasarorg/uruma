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

import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationElementContainer;

/**
 * {@link ConfigurationElementContainer} のための基底クラスです。<br />
 * 
 * @author y-komori
 */
public abstract class AbstractConfigurationElementContainer extends
        AbstractConfigurationElement implements ConfigurationElementContainer {

    private List<ConfigurationElement> children = new ArrayList<ConfigurationElement>();

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElementContainer#addElement(org.seasar.uruma.rcp.configuration.ConfigurationElement)
     */
    public void addElement(final ConfigurationElement element) {
        children.add(element);
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationElementContainer#getElements()
     */
    public List<ConfigurationElement> getElements() {
        return Collections.unmodifiableList(children);
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.elements.AbstractConfigurationElement#writeConfiguration(java.io.Writer)
     */
    @Override
    public void writeConfiguration(final Writer writer) {
        if (configurationWriter != null) {
            configurationWriter.writeStartTag(this, writer, level);
        }

        for (ConfigurationElement element : children) {
            element.setLevel(level + 1);
            element.writeConfiguration(writer);
        }

        if (configurationWriter != null) {
            configurationWriter.writeEndTag(this, writer, level);
        }
    }
}
