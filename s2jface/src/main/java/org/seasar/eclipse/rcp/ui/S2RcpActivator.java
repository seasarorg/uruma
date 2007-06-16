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
import org.seasar.eclipse.common.util.LogUtil;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.jface.S2JFaceTemplateManager;
import org.seasar.jface.component.Template;
import org.seasar.jface.impl.S2JFaceTemplateManagerImpl;

/**
 * S2RCP アプリケーションのための基底アクティベータです。<br />
 * <p>
 * S2RCPを利用するアプリケーションは、本クラスを継承したアクティベータを作成してください。<br />
 * </p>
 * 
 * @author y-komori
 */
public abstract class S2RcpActivator extends AbstractUIPlugin {

    protected S2RcpActivator plugin;

    private S2Container container;

    private S2JFaceTemplateManager templateManager = new S2JFaceTemplateManagerImpl();

    /**
     * {@link S2Container} へ本クラスを登録する際のコンポーネント名です。<br />
     * 値：{@value}
     */
    public static final String PLUGIN = "plugin";

    /**
     * {@link S2RcpActivator} を構築します。<br />
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

        Thread currentThread = Thread.currentThread();
        ClassLoader originalLoader = currentThread.getContextClassLoader();
        currentThread.setContextClassLoader(getClass().getClassLoader());

        try {
            SingletonS2ContainerFactory.init();
            container = SingletonS2ContainerFactory.getContainer();
            container.register(this, PLUGIN);
        } catch (ResourceNotFoundRuntimeException ex) {
            LogUtil.log(this, ex);
            currentThread.setContextClassLoader(originalLoader);
            throw ex;
        }
        currentThread.setContextClassLoader(originalLoader);

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

    /**
     * 指定されたパスの画面定義XMLを読み込み、{@link Template} オブジェクトを生成します。<br />
     * 
     * @param path
     *            画面定義XMLのパス
     * @return {@link Template} オブジェクト
     */
    public Template getTemplate(final String path) {
        Template template = templateManager.getTemplate(path);
        if (template == null) {
            // TODO エラーログ出力
        }
        return template;
    }

    /**
     * {@link S2Container} のインスタンスを取得します。<br />
     * 
     * @return {@link S2Container} のインスタンス
     */
    protected S2Container getContainer() {
        return container;
    }

    /**
     * プラグイン初期化時に呼び出されるメソッドです。<br />
     * <p>
     * プラグイン初期化時の処理は、本メソッドをオーバーライドして記述してください。
     * </p>
     * 
     * @param context
     *            {@link BundleContext} オブジェクト
     * @throws Exception
     */
    protected abstract void s2RcpStart(BundleContext context) throws Exception;

    /**
     * プラグイン終了時に呼び出されるメソッドです。<br />
     * <p>
     * プラグイン終了時の処理は、本メソッドをオーバーライドして記述してください。
     * </p>
     * 
     * @param context
     *            {@link BundleContext} オブジェクト
     * @throws Exception
     */
    protected abstract void s2RcpStop(BundleContext context) throws Exception;
}
