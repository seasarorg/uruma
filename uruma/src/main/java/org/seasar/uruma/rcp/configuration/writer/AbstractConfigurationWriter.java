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

import org.seasar.framework.exception.IORuntimeException;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;

/**
 * {@link ConfigurationWriter} のための基底クラスです。<br />
 * 
 * @author y-komori
 * 
 * @param <ELEMENT_TYPE>
 *            対応する {@link ConfigurationElement} の型
 */
public abstract class AbstractConfigurationWriter<ELEMENT_TYPE extends ConfigurationElement>
        implements ConfigurationWriter {

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationWriter#writeStartTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer)
     */
    public final Class<? extends ConfigurationElement> getSupportType() {
        return doGetSupportType();
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationWriter#writeStartTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer)
     */
    public final void writeStartTag(final ConfigurationElement element,
            final Writer writer) {
        try {
            doWriteStartTag(doGetSupportType().cast(element), writer);
        } catch (IOException ex) {
            throw new IORuntimeException(ex);
        }
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationWriter#writeEndTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer)
     */
    public final void writeEndTag(final ConfigurationElement element,
            final Writer writer) {
        try {
            doWriteEndTag(doGetSupportType().cast(element), writer);
        } catch (IOException ex) {
            throw new IORuntimeException(ex);
        }
    }

    public abstract Class<ELEMENT_TYPE> doGetSupportType();

    public abstract void doWriteStartTag(final ELEMENT_TYPE element,
            final Writer writer) throws IOException;

    public abstract void doWriteEndTag(ELEMENT_TYPE element, Writer writer)
            throws IOException;
}
