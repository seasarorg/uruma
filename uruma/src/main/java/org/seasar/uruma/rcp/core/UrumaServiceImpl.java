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
package org.seasar.uruma.rcp.core;

import java.net.URL;
import java.util.Enumeration;

import org.osgi.framework.Bundle;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.ComponentUtil;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.core.ViewTemplateLoader;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link UrumaService} の実装クラスです。
 * 
 * @author y-komori
 */
public class UrumaServiceImpl implements UrumaService, UrumaConstants,
        UrumaMessageCodes {

    private static final UrumaLogger logger = UrumaLogger
            .getLogger(UrumaService.class);

    private Bundle targetBundle;

    private ClassLoader urumaClassLoader;

    private ClassLoader appClassLoader;

    private S2Container container;

    private TemplateManager templateManager;

    private ViewTemplateLoader templateLoader;

    private ApplicationContext applicationContext;

    private WindowContext windowContext;

    private WorkbenchComponent workbenchComponent;

    /**
     * {@link UrumaServiceImpl} を構築します。<br />
     * 
     * @param targetBundle
     *            ターゲットバンドル
     */
    public UrumaServiceImpl(final Bundle targetBundle) {
        AssertionUtil.assertNotNull("targetBundle", targetBundle);
        this.targetBundle = targetBundle;
        this.urumaClassLoader = getClass().getClassLoader();

        initialize();
    }

    protected void initialize() {
        logger.log(URUMA_SERVICE_INIT_START, targetBundle.getSymbolicName());

        appClassLoader = activateUrumaApplication(targetBundle);

        initS2Container();

        logger.log(URUMA_SERVICE_INIT_END, targetBundle.getSymbolicName());
    }

    /**
     * 指定したバンドルをアクティベートします。
     * 
     * @param bundle
     *            Urumaアプリケーションを含むバンドル
     * 
     * @return バンドルのクラスローダ
     */
    protected ClassLoader activateUrumaApplication(final Bundle bundle) {
        String symbolicName = bundle.getSymbolicName();
        logger.log(URUMA_APP_STARTING, symbolicName);

        ClassLoader bundleLoader = null;

        String className = getFirstClassName(bundle);
        if (className != null) {
            try {
                Class<?> clazz = bundle.loadClass(className);
                bundleLoader = clazz.getClassLoader();
            } catch (ClassNotFoundException ex) {
                // TODO 自動生成された catch ブロック
                ex.printStackTrace();
            }
        }

        logger.log(URUMA_APP_STARTED, symbolicName);
        return bundleLoader;
    }

    /**
     * {@link Bundle} に含まれるクラスファイルのうち、最初に見つかった一つのクラス名を返します。
     * 
     * @param bundle
     *            {@link Bundle} オブジェクト
     * @return 見つかったクラス名。見つからなかった場合は <code>null</code>。
     */
    @SuppressWarnings("unchecked")
    protected String getFirstClassName(final Bundle bundle) {
        String prefix = StringUtil.replace(bundle.getSymbolicName(), PERIOD,
                SLASH);
        Enumeration entries = bundle.findEntries("", "*.class", true);
        while (entries.hasMoreElements()) {
            URL url = (URL) entries.nextElement();
            String path = url.getPath();

            int pos = path.indexOf(prefix);
            if (pos > 0) {
                String className = StringUtil.replace(path.substring(pos, path
                        .length() - 6), SLASH, PERIOD);
                return className;
            }
        }
        return null;
    }

    protected void initS2Container() {
        try {

            S2Container urumaContainer = S2ContainerFactory
                    .create(UrumaConstants.URUMA_RCP_DICON_PATH);

            switchToAppClassLoader();

            // TODO app.dicon が読み込めない場合のハンドリング
            String configPath = SingletonS2ContainerFactory.getConfigPath();
            container = S2ContainerFactory.create(configPath);
            container.include(urumaContainer);

            container.init();
            SingletonS2ContainerFactory.setContainer(container);

            ComponentUtil.setS2Container(container);

        } catch (ResourceNotFoundRuntimeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
        } finally {
            switchToUrumaClassLoader();
        }
    }

    protected void registComponentsToS2Container() {
        this.templateManager = (TemplateManager) container
                .getComponent(TemplateManager.class);
        this.templateLoader = (ViewTemplateLoader) container
                .getComponent(ViewTemplateLoader.class);
        this.applicationContext = (ApplicationContext) container
                .getComponent(ApplicationContext.class);

        container.register(this, UrumaConstants.URUMA_PLUGIN_S2NAME);
    }

    protected void switchToAppClassLoader() {
        Thread currentThread = Thread.currentThread();
        this.urumaClassLoader = currentThread.getContextClassLoader();
        currentThread.setContextClassLoader(appClassLoader);
    }

    protected void switchToUrumaClassLoader() {
        Thread.currentThread().setContextClassLoader(this.urumaClassLoader);
    }
}
