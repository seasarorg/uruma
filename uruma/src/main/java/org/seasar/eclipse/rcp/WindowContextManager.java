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

import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.WindowContext;

/**
 * プラグイン毎の {@link WindowContext} を管理するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class WindowContextManager {
    private Map<String, WindowContext> contextMap = new HashMap<String, WindowContext>();

    /**
     * <code>pluginId</code> をキーとして {@link WindowContext} のインスタンスを取得します。<br />
     * 
     * @param pluginId
     *            プラグインID
     * @return {@link WindowContext} のインスタンス
     */
    public WindowContext getWindowContext(final String pluginId) {
        WindowContext context = contextMap.get(pluginId);
        if (context == null) {
            // TODO 要修正
            context = ContextFactory.createWindowContext(null, "");
            contextMap.put(pluginId, context);
        }
        return context;
    }
}
