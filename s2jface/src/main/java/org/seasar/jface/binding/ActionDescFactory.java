/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.binding;

import java.util.concurrent.ConcurrentMap;

import org.seasar.framework.util.Disposable;
import org.seasar.framework.util.DisposableUtil;
import org.seasar.framework.util.tiger.CollectionsUtil;
import org.seasar.jface.binding.impl.ActionDescImpl;

/**
 * {@link ActionDesc}を取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class ActionDescFactory {
    private static volatile boolean initialized;

    protected static final ConcurrentMap<Class<?>, ActionDesc> actionDescs = CollectionsUtil
            .newConcurrentHashMap();

    public static void initialize() {
        DisposableUtil.add(new Disposable() {
            public void dispose() {
                ActionDescFactory.dispose();
            }
        });
        initialized = true;
    }

    public static synchronized void dispose() {
        actionDescs.clear();
        initialized = false;
    }

    public static ActionDesc getActionDesc(final Class<?> actionClass) {
        if (!initialized) {
            initialize();
        }
        ActionDesc actionDesc = actionDescs.get(actionClass);
        if (actionDesc == null) {
            actionDesc = CollectionsUtil.putIfAbsent(actionDescs, actionClass,
                    new ActionDescImpl(actionClass));
        }
        return actionDesc;
    }
}
