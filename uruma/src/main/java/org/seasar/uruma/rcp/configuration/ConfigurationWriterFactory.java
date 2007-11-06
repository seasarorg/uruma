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

import java.util.HashMap;
import java.util.Map;

import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.rcp.configuration.writer.ExtensionWriter;
import org.seasar.uruma.rcp.configuration.writer.PerspectiveWriter;
import org.seasar.uruma.rcp.configuration.writer.ViewWriter;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ConfigurationWriter} のためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ConfigurationWriterFactory {
    private static final Map<Class<? extends ConfigurationElement>, ConfigurationWriter> writerMap = new HashMap<Class<? extends ConfigurationElement>, ConfigurationWriter>();

    static {
        addWriter(new ExtensionWriter());
        addWriter(new ViewWriter());
        addWriter(new PerspectiveWriter());
    }

    public static final void addWriter(final ConfigurationWriter writer) {
        AssertionUtil.assertNotNull("writer", writer);

        Class<? extends ConfigurationElement> clazz = writer.getSupportType();
        writerMap.put(clazz, writer);
    }

    public static final ConfigurationWriter getConfigurationWriter(
            final Class<? extends ConfigurationElement> clazz) {
        ConfigurationWriter writer = writerMap.get(clazz);
        if (writer != null) {
            return writer;
        } else {
            throw new NotFoundException(
                    UrumaMessageCodes.CONFIGURATION_WRITER_NOT_FOUND, clazz
                            .getName());
        }
    }

    public static final ConfigurationWriter getConfigurationWriter(
            final ConfigurationElement element) {
        AssertionUtil.assertNotNull("element", element);
        return getConfigurationWriter(element.getClass());
    }
}
