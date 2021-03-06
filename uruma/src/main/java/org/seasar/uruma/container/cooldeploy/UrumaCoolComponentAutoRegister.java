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
package org.seasar.uruma.container.cooldeploy;

import org.seasar.framework.container.cooldeploy.CoolComponentAutoRegister;
import org.seasar.framework.convention.NamingConvention;
import org.seasar.framework.util.ClassTraversal.ClassHandler;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;

/**
 * Uruma COOL deploy用の {@link NamingConvention}に一致するコンポーネントを自動登録するクラスです。
 * 
 * @author y.sugigami
 * 
 */
public class UrumaCoolComponentAutoRegister extends CoolComponentAutoRegister
        implements ClassHandler, UrumaMessageCodes {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(UrumaCoolComponentAutoRegister.class);

    // TODO S2Container の更新に伴い、コンパイルが通らなくなったため暫定コメントアウト
    /**
     * 自動登録を行います。
     */
    // @Override
    // public void registerAll() {
    // final String[] rootPackageNames = getNamingConvention()
    // .getRootPackageNames();
    // if (rootPackageNames != null) {
    // for (int i = 0; i < rootPackageNames.length; ++i) {
    // final String rootDir = rootPackageNames[i].replace('.', '/');
    // try {
    // URL localUrl = RcpResourceUtil.getLocalResourceUrl(rootDir);
    // if (localUrl != null) {
    // final Strategy strategy = getStrategy(URLUtil
    // .toCanonicalProtocol(localUrl.getProtocol()));
    // strategy.registerAll(rootDir, localUrl);
    // } else {
    // logger.log(COOLDEPLOY_PACKAGE_NOT_FOUND, rootDir);
    // }
    // } catch (IOException ex) {
    // logger.log(COOLDEPLOY_PACKAGE_NOT_FOUND, rootDir);
    // }
    // }
    // }
    // registerdClasses.clear();
    // }
}
