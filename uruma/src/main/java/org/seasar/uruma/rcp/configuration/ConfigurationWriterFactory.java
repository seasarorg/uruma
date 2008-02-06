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

import org.seasar.uruma.component.rcp.PartComponent;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.rcp.configuration.impl.ActionElement;
import org.seasar.uruma.rcp.configuration.impl.ActionSetElement;
import org.seasar.uruma.rcp.configuration.impl.ApplicationElement;
import org.seasar.uruma.rcp.configuration.impl.GroupMarkerElement;
import org.seasar.uruma.rcp.configuration.impl.MenuElement;
import org.seasar.uruma.rcp.configuration.impl.RunElement;
import org.seasar.uruma.rcp.configuration.writer.GenericConfigurationWriter;
import org.seasar.uruma.rcp.configuration.writer.NullConfigurationWriter;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ConfigurationWriter} のためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ConfigurationWriterFactory {
    private static final Map<Class<? extends ConfigurationElement>, ConfigurationWriter> writerMap = new HashMap<Class<? extends ConfigurationElement>, ConfigurationWriter>();

    static {
        addWriter(new GenericConfigurationWriter(Extension.class, "extension"));
        addWriter(new GenericConfigurationWriter(ViewPartComponent.class,
                "view", true));
        addWriter(new GenericConfigurationWriter(PerspectiveComponent.class,
                "perspective"));
        addWriter(new NullConfigurationWriter<PartComponent>(
                PartComponent.class));
        addWriter(new GenericConfigurationWriter(ActionSetElement.class,
                "actionSet"));
        addWriter(new GenericConfigurationWriter(MenuElement.class, "menu"));
        addWriter(new GenericConfigurationWriter(ActionElement.class, "action",
                true));
        addWriter(new GenericConfigurationWriter(GroupMarkerElement.class,
                "groupMarker"));

        addWriter(new GenericConfigurationWriter(ApplicationElement.class,
                "application"));
        addWriter(new GenericConfigurationWriter(RunElement.class, "run", true));
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
