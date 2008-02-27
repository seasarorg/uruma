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

/**
 * {@link Extension} オブジェクトを生成するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ExtensionFactory {
    private ExtensionFactory() {

    }

    /**
     * {@link Extension} オブジェクトを生成します。<br />
     * 
     * @param point
     *            エクステンションポイント ID
     * @return {@link Extension} オブジェクト
     */
    public static final Extension createExtension(final String point) {
        Extension extension = new Extension(point);
        ConfigurationWriter writer = ConfigurationWriterFactory
                .getConfigurationWriter(Extension.class);
        extension.setConfigurationWriter(writer);

        return extension;
    }
}
