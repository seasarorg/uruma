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
import java.lang.reflect.Field;
import java.util.List;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.ConfigurationAttribute;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.rcp.configuration.ConfigurationElement;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;
import org.seasar.uruma.util.AnnotationUtil;

/**
 * 汎用的な {@link ConfigurationWriter} です。<br />
 * 
 * @author y-komori
 */
public class GenericConfigurationWriter implements ConfigurationWriter,
        UrumaConstants {

    private Class<? extends ConfigurationElement> supportType;

    private String elementName;

    private boolean startTagOnly;

    /**
     * {@link GenericConfigurationWriter} を構築します。<br />
     * 
     * @param supportType
     *            対応する {@link ConfigurationElement} の型
     * @param elementName
     *            要素名
     */
    public GenericConfigurationWriter(
            final Class<? extends ConfigurationElement> supportType,
            final String elementName) {
        this(supportType, elementName, false);
    }

    /**
     * {@link GenericConfigurationWriter} を構築します。<br />
     * 
     * @param supportType
     *            対応する {@link ConfigurationElement} の型
     * @param elementName
     *            要素名
     * @param startTagOnly
     *            <code>true</code> の場合、開始タグのみを出力する
     */
    public GenericConfigurationWriter(
            final Class<? extends ConfigurationElement> supportType,
            final String elementName, final boolean startTagOnly) {
        this.supportType = supportType;
        this.elementName = elementName;
        this.startTagOnly = startTagOnly;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationWriter#getSupportType()
     */
    public Class<? extends ConfigurationElement> getSupportType() {
        return this.supportType;
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationWriter#writeStartTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer)
     */
    public void writeStartTag(final ConfigurationElement element,
            final Writer writer) {
        writeStartTag(element, writer, 0);
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationWriter#writeStartTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer, int)
     */
    public void writeStartTag(final ConfigurationElement element,
            final Writer writer, final int level) {
        try {
            writer.write(createIndent(level));
            writer.write("<");
            writer.write(elementName);
            writer.write(WHITE_SPACE);

            List<PropertyDesc> pdLists = AnnotationUtil
                    .getAnnotatedPropertyDescs(supportType,
                            ConfigurationAttribute.class);
            for (PropertyDesc pd : pdLists) {
                Field field = pd.getField();
                ConfigurationAttribute anno = field
                        .getAnnotation(ConfigurationAttribute.class);
                String name = anno.name();
                if (StringUtil.isEmpty(name)) {
                    name = field.getName();
                }

                Object value = pd.getValue(element);

                if (value != null) {
                    writer.write(name);
                    writer.write("=\"");
                    escapeChars(value.toString(), writer);
                    writer.write("\" ");
                } else if (anno.required()) {
                    writer.write(name);
                    writer.write("=\"\" ");
                }
            }

            if (startTagOnly) {
                writer.write("/>\n");
            } else {
                writer.write(">\n");
            }
        } catch (IOException ex) {
            throw new IORuntimeException(ex);
        }
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationWriter#writeEndTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer)
     */
    public void writeEndTag(final ConfigurationElement element,
            final Writer writer) {
        writeEndTag(element, writer, 0);
    }

    /*
     * @see org.seasar.uruma.rcp.configuration.ConfigurationWriter#writeEndTag(org.seasar.uruma.rcp.configuration.ConfigurationElement,
     *      java.io.Writer, int)
     */
    public void writeEndTag(final ConfigurationElement element,
            final Writer writer, final int level) {
        if (!startTagOnly) {
            try {
                writer.write(createIndent(level));
                writer.write("</" + elementName + ">\n");
            } catch (IOException ex) {
                throw new IORuntimeException(ex);
            }
        }
    }

    protected void escapeChars(final String str, final Writer writer)
            throws IOException {
        for (int i = 0; i < str.length(); i++) {
            int chr = str.charAt(i);

            switch (chr) {
            case '&':
                writer.write("&amp;");
                break;

            case '<':
                writer.write("&lt;");
                break;

            case '>':
                writer.write("&gt;");
                break;

            case '"':
                writer.write("&quot;");
                break;

            default:
                writer.write(chr);
            }
        }
    }

    protected String createIndent(final int level) {
        if (level > 0) {
            StringBuilder builder = new StringBuilder(level);
            for (int i = 0; i < level; i++) {
                builder.append(WHITE_SPACE);
            }
            return builder.toString();
        } else {
            return NULL_STRING;
        }
    }
}
