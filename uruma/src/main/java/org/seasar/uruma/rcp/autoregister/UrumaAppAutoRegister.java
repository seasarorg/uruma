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
package org.seasar.uruma.rcp.autoregister;

import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.seasar.framework.container.autoregister.ComponentAutoRegister;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.uruma.util.AssertionUtil;

/**
 * Uruma アプリケーションコンポーネントの自動登録を行うクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaAppAutoRegister extends ComponentAutoRegister {
    /*
     * @see org.seasar.framework.container.autoregister.ComponentAutoRegister#registerAll()
     */
    @Override
    public void registerAll() {
        for (int i = 0; i < referenceClasses.size(); ++i) {
            final Class<?> referenceClass = (Class<?>) referenceClasses.get(i);
            final String baseClassPath = ResourceUtil
                    .getResourcePath(referenceClass);
            final URL url = ResourceUtil.getResource(baseClassPath);

            try {
                URL resolvedUrl = FileLocator.resolve(url);
                final Strategy strategy = getStrategy(resolvedUrl.getProtocol());
                strategy.registerAll(referenceClass, resolvedUrl);
            } catch (IOException ex) {
                throw new IORuntimeException(ex);
            }
        }
    }

    /**
     * リファレンスクラス名を設定します。<br />
     * 引数で指定した名前のクラスはコンテクストクラスローダから読み込み、 {@link #addReferenceClass(Class)}
     * メソッドによって設定されます。
     * 
     * @param referenceClassName
     *            リファレンスクラス名
     */
    public void addReferenceClassName(final String referenceClassName)
            throws ClassNotFoundException {
        AssertionUtil.assertNotNull("referenceClassName", referenceClassName);
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        Class<?> refClass = loader.loadClass(referenceClassName);
        addReferenceClass(refClass);
    }
}
