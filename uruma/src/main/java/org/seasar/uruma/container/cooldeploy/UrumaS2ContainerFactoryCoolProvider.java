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

import java.net.URL;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory.DefaultProvider;

/**
 * Uruma COOL deploy用の
 * {@link org.seasar.framework.container.factory.S2ContainerFactory.Provider}です。<br />
 * クラスパス上に customizer.dicon、creator.dicon が存在する場合はそれらを優先してインクルードし、存在しない場合、
 * org/seasar/uruma/dicon/ 配下の uruma-customizer.dicon、uruma-creator.dicon
 * をそれぞれインクルードします。
 * 
 * @author y.sugigami
 * @author y-komori
 */
public class UrumaS2ContainerFactoryCoolProvider extends DefaultProvider {
    protected static final String URUMA_DICON_PATH = "org/seasar/uruma/dicon/";

    /** COOL deploy用のdiconファイルのパスです。 */
    protected static final String COOLDEPLOY_AUTOREGISTER = URUMA_DICON_PATH
            + "uruma-cooldeploy-autoregister.dicon";

    /** Uruma 標準のカスタマイザ dicon ファイルのパスです。 */
    protected static final String URUMA_CUSTOMIZER = URUMA_DICON_PATH
            + "uruma-customizer.dicon";

    /** Uruma 標準のクリエータ dicon ファイルのパスです。 */
    protected static final String URUMA_CREATOR = URUMA_DICON_PATH
            + "uruma-creator.dicon";

    /** S2Container 標準のカスタマイザ dicon ファイルのパスです。 */
    protected static final String CUSTOMIZER = "customizer.dicon";

    /** S2Container 標準のクリエータ dicon ファイルのパスです。 */
    protected static final String CREATOR = "creator.dicon";

    @Override
    public S2Container create(final String path) {
        final S2Container container = super.create(path);
        final S2Container child = include(container, COOLDEPLOY_AUTOREGISTER);

        includeS2Container(child, CUSTOMIZER, URUMA_CUSTOMIZER);
        includeS2Container(child, CREATOR, URUMA_CREATOR);
        return container;
    }

    protected S2Container includeS2Container(final S2Container parent,
            final String appDicon, final String urumaDicon) {
        // RCP モードでは、コンテクストクラスローダがApp側の前提
        String path = appDicon;
        ClassLoader contextLoader = Thread.currentThread()
                .getContextClassLoader();
        URL resource = contextLoader.getResource(path);
        if (resource == null) {
            path = urumaDicon;
        }
        return include(parent, path);
    }
}
