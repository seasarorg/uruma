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
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.eclipse.core.runtime.FileLocator;
import org.seasar.framework.container.autoregister.ComponentAutoRegister;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.util.JarFileUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.util.AssertionUtil;

/**
 * Uruma アプリケーションコンポーネントの自動登録を行うクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaAppAutoRegister extends ComponentAutoRegister implements
        UrumaConstants, UrumaMessageCodes {
    private UrumaLogger logger = UrumaLogger
            .getLogger(UrumaAppAutoRegister.class);

    /**
     * {@link UrumaAppAutoRegister} を構築します。<br />
     */
    @SuppressWarnings("unchecked")
    public UrumaAppAutoRegister() {
        super();
        strategies.put("jar", new RcpJarFileStrategy());
    }

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

    /*
     * @see org.seasar.framework.container.autoregister.AbstractComponentAutoRegister#register(java.lang.String)
     */
    @Override
    protected void register(final String className) {
        super.register(className);
        logger.log(COMPONENT_REGISTERED, className);
    }

    /**
     * RCP 環境における jar ファイル用の {@link ComponentAutoRegister.Strategy}です。
     */
    protected class RcpJarFileStrategy implements Strategy {
        @SuppressWarnings("unchecked")
        public void registerAll(final Class referenceClass, final URL url) {
            final JarFile jarFile = JarFileUtil.toJarFile(url);
            traverse(jarFile, getClassPathRoot(url, referenceClass));
        }

        private String getClassPathRoot(final URL url,
                final Class<?> referenceClass) {
            String innerParh = StringUtil.substringToLast(url.getPath(),
                    EXCLAMATION_MARK);
            String classPath = referenceClass.getName().replace('.', '/');
            int pos = innerParh.indexOf(classPath);
            if (pos > -1) {
                return innerParh.substring(1, pos);
            } else {
                return NULL_STRING;
            }
        }

        private void traverse(final JarFile jarFile, final String classPathRoot) {
            final Enumeration<?> enumeration = jarFile.entries();
            while (enumeration.hasMoreElements()) {
                final JarEntry entry = (JarEntry) enumeration.nextElement();
                final String entryName = entry.getName().replace('\\', '/');
                if (entryName.endsWith(CLASS_SUFFIX)) {
                    final String className = entryName.substring(
                            classPathRoot.length(),
                            entryName.length() - CLASS_SUFFIX.length())
                            .replace('/', '.');
                    final int pos = className.lastIndexOf('.');
                    final String packageName = (pos == -1) ? null : className
                            .substring(0, pos);
                    final String shortClassName = (pos == -1) ? className
                            : className.substring(pos + 1);
                    processClass(packageName, shortClassName);
                }
            }
        }
    }
}
