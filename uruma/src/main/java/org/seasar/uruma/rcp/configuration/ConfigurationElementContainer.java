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

import java.util.List;

/**
 * 子を持つことができる {@link ConfigurationElement} のためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ConfigurationElementContainer extends ConfigurationElement {
    /**
     * 子の {@link ConfigurationElement} を返します。<br />
     * 
     * @return {@link ConfigurationElement} のリスト
     */
    public List<ConfigurationElement> getElements();

    /**
     * 子の {@link ConfigurationElement} を追加します。<br />
     * 
     * @param element
     *            追加する {@link ConfigurationElement}
     */
    public void addElement(ConfigurationElement element);
}
