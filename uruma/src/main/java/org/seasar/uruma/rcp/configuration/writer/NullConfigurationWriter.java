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
package org.seasar.uruma.rcp.configuration.writer;

import java.io.IOException;
import java.io.Writer;

import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;

/**
 * 何も処理を行わない {@link ConfigurationWriter} です。<br />
 * 
 * @author y-komori
 */
public class NullConfigurationWriter<ELEMENT_TYPE extends ConfigurationElement>
        extends AbstractConfigurationWriter<ELEMENT_TYPE> {
    private Class<ELEMENT_TYPE> clazz;

    /**
     * {@link NullConfigurationWriter} を構築します。<br />
     * 
     * @param clazz
     *            サポートクラス
     */
    public NullConfigurationWriter(final Class<ELEMENT_TYPE> clazz) {
        super();

        this.clazz = clazz;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.writer.AbstractConfigurationWriter#doGetSupportType()
     */
    @Override
    public Class<ELEMENT_TYPE> doGetSupportType() {
        return clazz;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.writer.AbstractConfigurationWriter#doWriteEndTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer, int)
     */
    @Override
    public void doWriteEndTag(final ELEMENT_TYPE element, final Writer writer,
            final int level) throws IOException {
        // Do nothing.
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.writer.AbstractConfigurationWriter#doWriteStartTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer, int)
     */
    @Override
    public void doWriteStartTag(final ELEMENT_TYPE element,
            final Writer writer, final int level) throws IOException {
        // Do nothing.
    }
}
