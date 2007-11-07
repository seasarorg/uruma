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
package org.seasar.uruma.component.rcp;

import org.eclipse.ui.IPerspectiveFactory;
import org.seasar.uruma.rcp.configuration.impl.AbstractConfigurationElement;

/**
 * <code>perspective</code> 要素を表すコンポーネントです。<br />
 * 
 * @author y-komori
 */
public class PerspectiveComponent extends AbstractConfigurationElement {
    /**
     * パースペクティブのIDです。
     */
    public String id;

    /**
     * パースペクティブの名称です。
     */
    public String name;

    /**
     * アイコンのパスです。
     */
    public String icon;

    /**
     * パートの配置を固定するかどうかのフラグです。
     */
    public String fixed;

    /**
     * {@link IPerspectiveFactory} のクラス名です。
     */
    public String perspectiveClass;
}
