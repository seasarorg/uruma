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

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory.DefaultProvider;

/**
 * Uruma COOL deploy用の
 * {@link org.seasar.framework.container.factory.S2ContainerFactory.Provider}
 * です。<br />
 * 
 * @author y.sugigami
 */
public class UrumaS2ContainerFactoryCoolProvider extends DefaultProvider {

    /**
     * COOL deploy用のdiconファイルのパスです。
     */
    protected static final String DICON_PATH = "org/seasar/uruma/dicon/uruma-cooldeploy-autoregister.dicon";

    @Override
    public S2Container create(final String path) {
        final S2Container container = super.create(path);
        include(container, DICON_PATH);
        return container;
    }

}
