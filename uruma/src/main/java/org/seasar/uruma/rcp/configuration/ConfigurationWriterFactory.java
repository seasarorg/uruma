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
package org.seasar.uruma.rcp.configuration;

import java.util.HashMap;
import java.util.Map;

import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.rcp.configuration.elements.ActionElement;
import org.seasar.uruma.rcp.configuration.elements.ActionSetElement;
import org.seasar.uruma.rcp.configuration.elements.ApplicationElement;
import org.seasar.uruma.rcp.configuration.elements.CategoryElement;
import org.seasar.uruma.rcp.configuration.elements.ClassElement;
import org.seasar.uruma.rcp.configuration.elements.CommandElement;
import org.seasar.uruma.rcp.configuration.elements.ContextElement;
import org.seasar.uruma.rcp.configuration.elements.GroupMarkerElement;
import org.seasar.uruma.rcp.configuration.elements.HandlerElement;
import org.seasar.uruma.rcp.configuration.elements.InitializerElement;
import org.seasar.uruma.rcp.configuration.elements.KeyElement;
import org.seasar.uruma.rcp.configuration.elements.MenuCommandElement;
import org.seasar.uruma.rcp.configuration.elements.MenuContributionElement;
import org.seasar.uruma.rcp.configuration.elements.MenuElement;
import org.seasar.uruma.rcp.configuration.elements.ParameterElement;
import org.seasar.uruma.rcp.configuration.elements.PerspectiveElement;
import org.seasar.uruma.rcp.configuration.elements.RunElement;
import org.seasar.uruma.rcp.configuration.elements.SchemeElement;
import org.seasar.uruma.rcp.configuration.elements.ViewElement;
import org.seasar.uruma.rcp.configuration.writer.GenericConfigurationWriter;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ConfigurationWriter} のためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ConfigurationWriterFactory {
    private static final Map<Class<? extends ConfigurationElement>, ConfigurationWriter> writerMap = new HashMap<Class<? extends ConfigurationElement>, ConfigurationWriter>();

    static {
        addWriter(new GenericConfigurationWriter(Extension.class));

        addWriter(new GenericConfigurationWriter(ActionElement.class));
        addWriter(new GenericConfigurationWriter(ActionSetElement.class));
        addWriter(new GenericConfigurationWriter(ApplicationElement.class));
        addWriter(new GenericConfigurationWriter(CategoryElement.class));
        addWriter(new GenericConfigurationWriter(ClassElement.class));
        addWriter(new GenericConfigurationWriter(CommandElement.class));
        addWriter(new GenericConfigurationWriter(ContextElement.class));
        addWriter(new GenericConfigurationWriter(GroupMarkerElement.class));
        addWriter(new GenericConfigurationWriter(HandlerElement.class));
        addWriter(new GenericConfigurationWriter(InitializerElement.class));
        addWriter(new GenericConfigurationWriter(KeyElement.class));
        addWriter(new GenericConfigurationWriter(MenuCommandElement.class));
        addWriter(new GenericConfigurationWriter(MenuContributionElement.class));
        addWriter(new GenericConfigurationWriter(MenuElement.class));
        addWriter(new GenericConfigurationWriter(ParameterElement.class));
        addWriter(new GenericConfigurationWriter(PerspectiveElement.class));
        addWriter(new GenericConfigurationWriter(RunElement.class));
        addWriter(new GenericConfigurationWriter(SchemeElement.class));
        addWriter(new GenericConfigurationWriter(ViewElement.class));
    }

    /**
     * {@link ConfigurationWriter} を追加します。<br />
     * 
     * @param writer
     *            {@link ConfigurationWriter} オブジェクト
     */
    public static final void addWriter(final ConfigurationWriter writer) {
        AssertionUtil.assertNotNull("writer", writer);

        Class<? extends ConfigurationElement> clazz = writer.getSupportType();
        writerMap.put(clazz, writer);
    }

    /**
     * クラスを指定して {@link ConfigurationWriter} を取得します。<br />
     * 
     * @param clazz
     *            {@link ConfigurationElement} のクラスオブジェクト
     * @return {@link ConfigurationWriter} オブジェクト
     * @throws NotFoundException
     *             {@link ConfigurationWriter} が見つからない場合
     */
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

    /**
     * {@link ConfigurationElement} オブジェクトを指定して {@link ConfigurationWriter}
     * を取得します。<br />
     * 
     * @param element
     *            {@link ConfigurationElement} オブジェクト
     * @return {@link ConfigurationWriter} オブジェクト
     * @throws NotFoundException
     *             {@link ConfigurationWriter} が見つからない場合
     */
    public static final ConfigurationWriter getConfigurationWriter(
            final ConfigurationElement element) {
        AssertionUtil.assertNotNull("element", element);
        return getConfigurationWriter(element.getClass());
    }
}
