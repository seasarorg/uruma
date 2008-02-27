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
package org.seasar.uruma.desc;

import java.util.concurrent.ConcurrentMap;

import org.seasar.framework.util.Disposable;
import org.seasar.framework.util.DisposableUtil;
import org.seasar.framework.util.tiger.CollectionsUtil;
import org.seasar.uruma.desc.impl.PartActionDescImpl;

/**
 * {@link PartActionDesc} オブジェクトを取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class PartActionDescFactory {
    private static volatile boolean initialized;

    protected static final ConcurrentMap<Class<?>, PartActionDesc> partActionDescs = CollectionsUtil
            .newConcurrentHashMap();

    /**
     * 本ファクトリの初期化を行います。<br />
     */
    public static void initialize() {
        DisposableUtil.add(new Disposable() {
            public void dispose() {
                PartActionDescFactory.dispose();
            }
        });
        initialized = true;
    }

    /**
     * 本ファクトリが保持するキャッシュをクリアします。<br />
     */
    public static synchronized void dispose() {
        partActionDescs.clear();
        initialized = false;
    }

    /**
     * 指定したクラスに対応する {@link PartActionDesc} オブジェクトを取得します。<br />
     * 
     * @param partActionClass
     *            {@link PartActionDesc} を取得するクラス
     * @return {@link PartActionDesc} オブジェクト
     */
    public static PartActionDesc getPartActionDesc(
            final Class<?> partActionClass) {
        if (!initialized) {
            initialize();
        }
        PartActionDesc partActionDesc = partActionDescs.get(partActionClass);
        if (partActionDesc == null) {
            partActionDesc = CollectionsUtil.putIfAbsent(partActionDescs,
                    partActionClass, new PartActionDescImpl(partActionClass));
        }
        return partActionDesc;
    }
}
