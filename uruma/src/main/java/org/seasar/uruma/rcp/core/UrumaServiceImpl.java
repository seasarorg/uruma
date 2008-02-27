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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.osgi.framework.Bundle;
import org.seasar.framework.container.ComponentDef;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.component.jface.TemplateImpl;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.ComponentUtil;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.core.ViewTemplateLoader;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.exception.UrumaAppInitException;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.rcp.autoregister.UrumaAppAutoRegisterBuilder;
import org.seasar.uruma.rcp.binding.CommandRegistry;
import org.seasar.uruma.rcp.configuration.ContributionBuilder;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link UrumaService} の実装クラスです。<br />
 * 本クラスは、 {@link UrumaServiceFactory} によって、Uruma アプリケーション毎に固有のインスタンスが生成されます。<br />
 * 
 * @author y-komori
 */
public class UrumaServiceImpl implements UrumaService, UrumaConstants,
        UrumaMessageCodes {

    private static final UrumaLogger logger = UrumaLogger
            .getLogger(UrumaService.class);

    protected Bundle targetBundle;

    protected ClassLoader urumaClassLoader;

    protected ClassLoader appClassLoader;

    protected ClassLoader oldClassLoader;

    protected IContributor contributor;

    protected String pluginId;

    protected S2Container container;

    protected TemplateManager templateManager;

    protected ViewTemplateLoader templateLoader;

    protected ApplicationContext applicationContext;

    protected WindowContext windowContext;

    protected WorkbenchComponent workbenchComponent;

    protected List<Extension> extensions = new ArrayList<Extension>();

    protected String defaultContextId;

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
        this.appClassLoader = getClass().getClassLoader();
        this.pluginId = targetBundle.getSymbolicName();
        this.defaultContextId = pluginId + ".context";

        initialize();
    }

    /**
     * 初期化処理を行います。<br />
     */
    protected void initialize() {
        logger.log(URUMA_SERVICE_INIT_START, targetBundle.getSymbolicName());

        appClassLoader = activateUrumaApplication(targetBundle);

        switchToAppClassLoader();
        try {
            initS2Container();
            prepareS2Components();

            logger.log(URUMA_SERVICE_INIT_END, targetBundle.getSymbolicName());
        } catch (Exception ex) {
            throw new UrumaAppInitException(targetBundle, ex, ex.getMessage());
        } finally {
            restoreClassLoader();
        }
    }

    /**
     * 拡張ポイントの設定を行います。<br />
     */
    protected void registerExtensions() {
        switchToAppClassLoader();
        try {
            setupContributor();

            setupContexts();
            if (!DUMMY_WORKBENCH_PATH.equals(workbenchComponent.getPath())) {
                // ビューテンプレートの読み込み
                templateLoader.loadViewTemplates();
            }

            ContributionBuilder.build(contributor, extensions);
        } catch (Exception ex) {
            throw new UrumaAppInitException(targetBundle, ex, ex.getMessage());
        } finally {
            restoreClassLoader();
        }
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

        String className = findFirstClassName(bundle);
        if (className != null) {
            try {
                Class<?> clazz = bundle.loadClass(className);
                bundleLoader = clazz.getClassLoader();
            } catch (ClassNotFoundException ex) {
                throw new UrumaAppInitException(bundle, ex, ex.getMessage());
            }
        } else {
            throw new UrumaAppInitException(bundle,
                    "Class not found under the "
                            + StringUtil.replace(bundle.getSymbolicName(),
                                    PERIOD, SLASH) + " package.");
        }

        logger.log(URUMA_APP_STARTED, symbolicName);
        return bundleLoader;
    }

    /**
     * {@link Bundle} に含まれるクラスファイルのうち、最初に見つかった一つのクラス名を返します。
     * クラスはバンドルのシンボリックネームが表すパッケージ配下を検索します。
     * 
     * @param bundle
     *            {@link Bundle} オブジェクト
     * @return 見つかったクラス名。見つからなかった場合は <code>null</code>。
     */
    @SuppressWarnings("unchecked")
    protected String findFirstClassName(final Bundle bundle) {
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

    /**
     * {@link S2Container} の初期化を行います。<br />
     */
    protected void initS2Container() throws ClassNotFoundException {
        switchToUrumaClassLoader();
        S2Container urumaContainer = S2ContainerFactory
                .create(UrumaConstants.URUMA_RCP_DICON_PATH);
        switchToAppClassLoader();

        String configPath = SingletonS2ContainerFactory.getConfigPath();

        try {
            ResourceUtil.getResource(configPath);
            container = S2ContainerFactory.create(configPath);
        } catch (ResourceNotFoundRuntimeException ex) {
            // app.dicon が存在しない場合は、空の S2Container を生成する
            container = S2ContainerFactory.create();
        }

        container.include(urumaContainer);
        registAutoRegister(container);

        container.init();
        SingletonS2ContainerFactory.setContainer(container);

        ComponentUtil.setS2Container(container);
    }

    protected void prepareS2Components() {
        this.templateManager = (TemplateManager) container
                .getComponent(TemplateManager.class);
        this.templateLoader = (ViewTemplateLoader) container
                .getComponent(ViewTemplateLoader.class);
        this.applicationContext = (ApplicationContext) container
                .getComponent(ApplicationContext.class);

        container.register(this, UrumaConstants.URUMA_SERVICE_S2NAME);
    }

    protected void registAutoRegister(final S2Container container)
            throws ClassNotFoundException {
        String refClassName = findFirstClassName(targetBundle);
        if (refClassName != null) {
            ComponentDef def = UrumaAppAutoRegisterBuilder.build(refClassName,
                    getPluginId());
            container.register(def);
        }
    }

    protected void switchClassLoader(final ClassLoader loader) {
        Thread currentThread = Thread.currentThread();
        this.oldClassLoader = currentThread.getContextClassLoader();
        logger.log(SWITCH_CONTEXT_CLASS_LOADER, loader);
        currentThread.setContextClassLoader(loader);
    }

    protected void setupContributor() {
        this.contributor = ContributorFactoryOSGi
                .createContributor(targetBundle);
    }

    protected void setupContexts() {
        Template workbenchTemplate = getTemplate(DEFAULT_WORKBENCH_XML);
        if (workbenchTemplate == null) {
            logger.log(WORKBENCH_DEF_FILE_NOT_FOUND, DEFAULT_WORKBENCH_XML);
            // workbench.xml が見つからない場合はダミーを生成する
            workbenchTemplate = createDummyWorkbenchTemplate();
        }

        UIComponentContainer root = workbenchTemplate.getRootComponent();
        if (root instanceof WorkbenchComponent) {
            this.workbenchComponent = (WorkbenchComponent) root;
        } else {
            throw new NotFoundException(WORKBENCH_ELEMENT_NOT_FOUND,
                    workbenchTemplate.getPath());
        }

        this.applicationContext.setValue(WORKBENCH_TEMPLATE_NAME,
                workbenchTemplate);
        this.windowContext = ContextFactory.createWindowContext(
                applicationContext, WORKBENCH_WINDOW_CONTEXT_ID);
    }

    protected Template createDummyWorkbenchTemplate() {
        Template template = new TemplateImpl();
        WorkbenchComponent workbench = new WorkbenchComponent();
        workbench.setPath(DUMMY_WORKBENCH_PATH);
        workbench.title = "Uruma";
        workbench.initWidth = "50%";
        workbench.initHeight = "50%";
        template.setRootComponent(workbench);
        return template;
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getPluginId()
     */
    public String getPluginId() {
        return this.pluginId;
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#createRcpId(java.lang.String)
     */
    public String createRcpId(final String id) {
        return pluginId + PERIOD + id;
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getLocalId(java.lang.String)
     */
    public String getLocalId(final String rcpId) {
        if (rcpId != null) {
            if (rcpId.startsWith(pluginId)) {
                return rcpId.substring(pluginId.length() + 1, rcpId.length());
            } else {
                return rcpId;
            }
        } else {
            return null;
        }
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getTemplate(java.lang.String)
     */
    public Template getTemplate(final String path) {
        try {
            Template template = templateManager.getTemplate(path);
            return template;
        } catch (ResourceNotFoundRuntimeException ex) {
            return null;
        }
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getWorkbench()
     */
    public IWorkbench getWorkbench() {
        return PlatformUI.getWorkbench();
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getWorkbenchComponent()
     */
    public WorkbenchComponent getWorkbenchComponent() {
        Template template = (Template) applicationContext
                .getValue(UrumaConstants.WORKBENCH_TEMPLATE_NAME);
        return (WorkbenchComponent) template.getRootComponent();
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getExtensions()
     */
    public List<Extension> getExtensions() {
        return Collections.unmodifiableList(extensions);
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getExtension(java.lang.String)
     */
    public Extension getExtension(final String point) {
        for (Extension extension : extensions) {
            if (extension.point.equals(point)) {
                return extension;
            }
        }
        return null;
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getWorkbenchWindowContext()
     */
    public WindowContext getWorkbenchWindowContext() {
        return this.windowContext;
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getContainer()
     */
    public S2Container getContainer() {
        return this.container;
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getAppClassLoader()
     */
    public ClassLoader getAppClassLoader() {
        return this.appClassLoader;
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#switchToAppClassLoader()
     */
    public void switchToAppClassLoader() {
        switchClassLoader(appClassLoader);
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#switchToUrumaClassLoader()
     */
    public void switchToUrumaClassLoader() {
        switchClassLoader(urumaClassLoader);
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#restoreClassLoader()
     */
    public void restoreClassLoader() {
        logger.log(SWITCH_CONTEXT_CLASS_LOADER, oldClassLoader);
        Thread.currentThread().setContextClassLoader(oldClassLoader);
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getCommandRegistry()
     */
    public CommandRegistry getCommandRegistry() {
        return (CommandRegistry) container.getComponent(CommandRegistry.class);
    }

    /*
     * @see org.seasar.uruma.rcp.UrumaService#getDefaultContextId()
     */
    public String getDefaultContextId() {
        return this.defaultContextId;
    }

    /**
     * 本サービスを破棄します。<br />
     */
    void destroy() {
        logger.log(URUMA_SERVICE_DESTROY, targetBundle.getSymbolicName());
        container.destroy();
    }
}
