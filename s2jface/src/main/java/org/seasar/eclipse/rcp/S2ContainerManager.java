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
package org.seasar.eclipse.rcp;

import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;

/**
 * プラグイン毎の {@link S2Container} インスタンスを管理するクラスです。<br />
 * 
 * @author y-komori
 */
public class S2ContainerManager {
    private Map<String, S2Container> containerMap = new HashMap<String, S2Container>();

    /**
     * <code>pluginId</code> をキーとして {@link S2Container} のインスタンスを取得します。<br />
     * 
     * @param pluginId
     *            プラグインID
     * @return {@link S2Container} のインスタンス
     */
    public S2Container getS2Container(final String pluginId) {
        S2Container container = containerMap.get(pluginId);
        if (container == null) {
            container = S2ContainerFactory.create("app.dicon");
            containerMap.put(pluginId, container);
        }

        return container;
    }
}
