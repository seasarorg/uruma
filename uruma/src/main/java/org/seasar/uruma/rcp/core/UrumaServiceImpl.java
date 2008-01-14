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
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
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
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
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
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;
import org.seasar.uruma.rcp.configuration.ConfigurationWriterFactory;
import org.seasar.uruma.rcp.configuration.ContributionBuilder;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.ui.AutoPerspectiveFactory;
import org.seasar.uruma.rcp.ui.GenericPerspectiveFactory;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link UrumaService} の実装クラスです。<br />
 * 本クラスは、 {@link UrumaServiceFactory} によって、Uruma アプリケーション毎に固有のインスタンスが生成されます。
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

    private ClassLoader oldClassLoader;

    private IContributor contributor;

    private String pluginId;

    private S2Container container;

    private TemplateManager templateManager;

    private ViewTemplateLoader templateLoader;

    private ApplicationContext applicationContext;

    private WindowContext windowContext;

    private WorkbenchComponent workbenchComponent;

    private List<Extension> extensions = new ArrayList<Extension>();

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
        this.pluginId = targetBundle.getSymbolicName();

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

    void registerExtensions() {
        switchToAppClassLoader();
        try {
            setupContributor();

            setupContexts();

            // ビューテンプレートの読み込み
            templateLoader.loadViewTemplates();

            // ビュー、パースペクティブの登録
            setupViewExtensions();
            setupPerspectives();
            ContributionBuilder.build(contributor, extensions);
        } catch (Exception ex) {
            // TODO 例外が不適切
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

    protected void setupViewExtensions() {
        List<Template> viewTemplates = templateManager
                .getTemplates(ViewPartComponent.class);

        Extension extension = ExtensionFactory
                .createExtension(ExtensionPoints.VIEWS);
        for (Template template : viewTemplates) {
            ViewPartComponent component = (ViewPartComponent) template
                    .getRootComponent();
            component.setRcpId(createRcpId(component.getId()));
            extension.addConfigurationElement(component);
        }
        extensions.add(extension);
    }

    protected void setupContexts() {
        // TODO workbench.xml が読み込めなかった場合のハンドリング
        Template workbenchTemplate = getTemplate(UrumaConstants.DEFAULT_WORKBENCH_XML);
        UIComponentContainer root = workbenchTemplate.getRootComponent();
        if (root instanceof WorkbenchComponent) {
            this.workbenchComponent = (WorkbenchComponent) root;
        } else {
            throw new NotFoundException(
                    UrumaMessageCodes.WORKBENCH_ELEMENT_NOT_FOUND,
                    workbenchTemplate.getPath());
        }

        this.applicationContext.setValue(
                UrumaConstants.WORKBENCH_TEMPLATE_NAME, workbenchTemplate);
        this.windowContext = ContextFactory.createWindowContext(
                applicationContext, UrumaConstants.WORKBENCH_WINDOW_CONTEXT_ID);
    }

    protected void setupPerspectives() {
        Extension extension = ExtensionFactory
                .createExtension(ExtensionPoints.PERSPECTIVES);

        boolean defaultIdUsed = false;

        for (PerspectiveComponent perspective : workbenchComponent
                .getPerspectives()) {
            perspective.perspectiveClass = GenericPerspectiveFactory.class
                    .getName();

            // ID のついていない最初のパースペクティブにはデフォルトIDをつける
            if (StringUtil.isBlank(perspective.id) && !defaultIdUsed) {
                perspective.id = UrumaConstants.DEFAULT_PERSPECTIVE_ID;
                perspective.setRcpId(createRcpId(perspective.id));
                defaultIdUsed = true;
            }

            extension.addConfigurationElement(perspective);
        }

        if (extension.getElements().size() == 0) {
            // perspective 要素が定義されていないときにデフォルト設定を行う
            PerspectiveComponent perspective = new PerspectiveComponent();
            ConfigurationWriter writer = ConfigurationWriterFactory
                    .getConfigurationWriter(perspective);
            perspective.setConfigurationWriter(writer);

            perspective.perspectiveClass = AutoPerspectiveFactory.class
                    .getName();
            perspective.id = UrumaConstants.DEFAULT_PERSPECTIVE_ID;
            perspective.setRcpId(createRcpId(perspective.id));
            perspective.name = getPluginId();

            extension.addConfigurationElement(perspective);

            workbenchComponent.addChild(perspective);
            workbenchComponent.initialPerspectiveId = UrumaConstants.DEFAULT_PERSPECTIVE_ID;
        } else if (StringUtil.isBlank(workbenchComponent.initialPerspectiveId)) {
            // initialPerspectiveId が定義されていない場合は
            // 最初に記述されている perspective を表示する
            PerspectiveComponent perspective = workbenchComponent
                    .getPerspectives().get(0);
            workbenchComponent.initialPerspectiveId = perspective.id;
        }

        extensions.add(extension);
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
            logger.error(ex.getMessage(), ex);
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

    /**
     * 本サービスを破棄します。<br />
     */
    void destroy() {
        logger.log(URUMA_SERVICE_DESTROY, targetBundle.getSymbolicName());
        container.destroy();
    }

    // TODO テスト用メソッド。後で削除
    private void test1() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        // IExtensionPoint extensionPoint = registry
        // .getExtensionPoint("org.eclipse.ui.views");
        IExtensionPoint extensionPoint = registry
                .getExtensionPoint("org.eclipse.ui.actionSets");

        IExtension[] extensions = extensionPoint.getExtensions();
        for (IExtension extension : extensions) {
            System.out.println("\nNamespaceIdentifier="
                    + extension.getNamespaceIdentifier());
            System.out.println("ExtensionPointUniqueIdentifier="
                    + extension.getExtensionPointUniqueIdentifier());
            System.out.println("Label=" + extension.getLabel());
            System.out.println("SimpleIdentifier="
                    + extension.getSimpleIdentifier());
            System.out.println("UniqueIdentifier="
                    + extension.getUniqueIdentifier());

            IConfigurationElement[] configurationElements = extension
                    .getConfigurationElements();
            for (IConfigurationElement configurationElement : configurationElements) {
                IContributor contributor = configurationElement
                        .getContributor();
                System.out.println("  ContributorClass = "
                        + contributor.getClass().getName());
                System.out.println("  ContributorName = "
                        + contributor.getName());
                System.out.println("  ConfigurationElementClass = "
                        + configurationElement.getClass().getName());

                String[] attrs = configurationElement.getAttributeNames();
                for (String string : attrs) {
                    System.out.println("    " + string + "="
                            + configurationElement.getAttribute(string));
                }
            }
        }
    }
}
