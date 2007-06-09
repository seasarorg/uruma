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
package org.seasar.eclipse.rcp.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * S2RCP アプリケーションのための基底アクティベータです。<br />
 * <p>
 * S2RCPを利用するアプリケーションは、本クラスを継承したアクティベータを作成してください。<br />
 * </p>
 * 
 * @author y-komori
 */
public abstract class S2RcpActivator extends AbstractUIPlugin {

    protected static S2RcpActivator plugin;

    private S2Container container;

    /**
     * The constructor
     */
    public S2RcpActivator() {
        plugin = this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    public final void start(BundleContext context) throws Exception {
        super.start(context);

        SingletonS2ContainerFactory.init();
        container = SingletonS2ContainerFactory.getContainer();
        System.out.println("Activatorのコンテナ" + container);

        s2RcpStart(context);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    public final void stop(BundleContext context) throws Exception {
        plugin = null;
        s2RcpStop(context);

        container.destroy();

        super.stop(context);
    }

    protected S2Container getContainer() {
        return container;
    }

    protected abstract void s2RcpStart(BundleContext context) throws Exception;

    protected abstract void s2RcpStop(BundleContext context) throws Exception;
}
