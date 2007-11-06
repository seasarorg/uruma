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
package org.seasar.uruma.rcp.configuration.writer;

import java.io.IOException;
import java.io.Writer;

import org.seasar.uruma.rcp.configuration.ConfigurationWriter;
import org.seasar.uruma.rcp.configuration.Extension;

/**
 * <code>extension</code> 要素のための {@link ConfigurationWriter} です。<br />
 * 
 * @author y-komori
 */
public class ExtensionWriter extends AbstractConfigurationWriter<Extension> {

    /*
     * @see org.seasar.uruma.rcp.configuration.writer.AbstractConfigurationWriter#doGetSupportType()
     */
    @Override
    public Class<Extension> doGetSupportType() {
        return Extension.class;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.writer.AbstractConfigurationWriter#doWriteStartTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer)
     */
    @Override
    public void doWriteStartTag(final Extension element, final Writer writer)
            throws IOException {
        writer.write("<extension point=\"" + element.getPoint());
        writer.write("\">\n");
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.writer.AbstractConfigurationWriter#doWriteEndTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer)
     */
    @Override
    public void doWriteEndTag(final Extension element, final Writer writer)
            throws IOException {
        writer.write("</extension>\n");
    }
}
