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
package org.seasar.jface.binding;

import java.util.concurrent.ConcurrentMap;

import org.seasar.framework.util.Disposable;
import org.seasar.framework.util.DisposableUtil;
import org.seasar.framework.util.tiger.CollectionsUtil;
import org.seasar.jface.binding.impl.FormDescImpl;

/**
 * {@link FormDesc}を取得するためのファクトリクラスです。<br />
 * 
 * @author y-komori
 */
public class FormDescFactory {
    private static volatile boolean initialized;

    protected static final ConcurrentMap<Class<?>, FormDesc> formDescs = CollectionsUtil
            .newConcurrentHashMap();

    public static void initialize() {
        DisposableUtil.add(new Disposable() {
            public void dispose() {
                FormDescFactory.dispose();
            }
        });
        initialized = true;
    }

    public static synchronized void dispose() {
        formDescs.clear();
        initialized = false;
    }

    public static FormDesc getFormDesc(final Class<?> formClass) {
        if (!initialized) {
            initialize();
        }
        FormDesc formDesc = formDescs.get(formClass);
        if (formDesc == null) {
            formDesc = CollectionsUtil.putIfAbsent(formDescs, formClass,
                    new FormDescImpl(formClass));
        }
        return formDesc;
    }
}
