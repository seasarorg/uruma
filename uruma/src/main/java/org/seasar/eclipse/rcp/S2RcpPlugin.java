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

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.seasar.eclipse.common.util.LogUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.impl.TemplateManagerImpl;

/**
 * S2RCP のプラグインクラスです。<br />
 * 
 * @author y-komori
 */
public class S2RcpPlugin extends Plugin {

    private static S2RcpPlugin plugin;

    private TemplateManager templateManager = new TemplateManagerImpl();

    private S2ContainerManager containerManager = new S2ContainerManager();

    private WindowContextManager contextManager = new WindowContextManager();

    /**
     * {@link S2RcpPlugin} を構築します。<br />
     */
    public S2RcpPlugin() {
        plugin = this;
    }

    /*
     * @see org.eclipse.core.runtime.Plugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public void start(final BundleContext context) throws Exception {
    }

    /*
     * @see org.eclipse.core.runtime.Plugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        super.stop(context);
        plugin = null;
    }

    /**
     * {@link S2RcpPlugin} のインスタンスを取得します。<br />
     * 
     * @return {@link S2RcpPlugin} のインスタンス
     */
    public static S2RcpPlugin getDefault() {
        return plugin;
    }

    /**
     * <code>pluginId</code> で指定されたプラグイン専用の {@link S2Container} インスタンスを取得します。<br />
     * 
     * @param pluginId
     *            プラグインID
     * @return {@link S2Container} のインスタンス
     */
    public S2Container getS2Container(final String pluginId) {
        // TODO 使用しないのであとで削除
        return containerManager.getS2Container(pluginId);
    }

    /**
     * <code>pluginId</code> で指定されたプラグイン専用の {@link WindowContext}
     * インスタンスを取得します。<br />
     * 
     * @param pluginId
     *            プラグインID
     * @return {@link WindowContext} のインスタンス
     */
    public WindowContext getWindowContext(final String pluginId) {
        return contextManager.getWindowContext(pluginId);

    }

    public static void log(final String msg) {
        LogUtil.log(getDefault(), msg);
    }

    public static void log(final Throwable throwable) {
        LogUtil.log(getDefault(), throwable);
    }
}
