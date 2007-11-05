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
package org.seasar.uruma.rcp;

import java.io.ByteArrayInputStream;

import org.eclipse.core.internal.registry.ExtensionRegistry;
import org.eclipse.core.runtime.ContributorFactoryOSGi;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IContributor;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.impl.WorkbenchComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.core.ViewTemplateLoader;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.log.UrumaLogger;

/**
 * Uruma RCP アプリケーションのためのアクティベータです。<br />
 * 
 * @author y-komori
 */
public class UrumaActivator extends AbstractUIPlugin {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(UrumaActivator.class);

    private static UrumaActivator plugin;

    private S2Container container;

    private TemplateManager templateManager;

    private ViewTemplateLoader templateLoader;

    private ApplicationContext applicationContext;

    private WindowContext windowContext;

    /**
     * {@link UrumaActivator} を構築します。<br />
     */
    public UrumaActivator() {
        plugin = this;
    }

    /*
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
     */
    @Override
    public final void start(final BundleContext context) throws Exception {
        super.start(context);

        // アプリケーションが異なるJarの場合の対処
        // Thread currentThread = Thread.currentThread();
        // ClassLoader originalLoader = currentThread.getContextClassLoader();
        // currentThread.setContextClassLoader(getClass().getClassLoader());

        initS2Container();
        registComponentsToS2Container();

        Template template = getTemplate(UrumaConstants.DEFAULT_WORKBENCH_XML);
        if (!(template.getRootComponent() instanceof WorkbenchComponent)) {
            throw new NotFoundException(
                    UrumaMessageCodes.WORKBENCH_ELEMENT_NOT_FOUND, template
                            .getBasePath());
        }
        applicationContext.setValue(UrumaConstants.WORKBENCH_TEMPLATE_NAME,
                template);
        this.windowContext = ContextFactory.createWindowContext(
                applicationContext, UrumaConstants.WORKBENCH_WINDOW_CONTEXT_ID);

        templateLoader.loadViewTemplates();
        // --------------------------------------------------------------------

        // URL localUrl = RcpResourceUtil
        // .getLocalResourceUrl(UrumaConstants.DEFAULT_WORKBENCH_XML);
        // logger.info("Protcol = " + localUrl.getProtocol());
        // logger.info("Path = " + localUrl.getPath());
        //
        // File localFile = new File(localUrl.getPath());
        // File baseDir = new File(localFile.getParent() + UrumaConstants.SLASH
        // + UrumaConstants.DEFAULT_VIEWS_PATH);
        //
        // if (logger.isInfoEnabled()) {
        // logger.log(UrumaMessageCodes.FINDING_XML_START, baseDir
        // .getAbsolutePath());
        // }
        //
        // List<File> viewFiles = RcpResourceUtil.findFileResources(baseDir,
        // new ExtFileFilter("xml"));
        //
        // if (logger.isInfoEnabled()) {
        // for (File file : viewFiles) {
        // logger.info(file.getAbsolutePath());
        // }
        // }
        //
        // // 現在のプラグインのパスを取得
        // URL entryUrl =
        // UrumaActivator.getInstance().getBundle().getEntry("/");
        // URL nativeUrl = FileLocator.resolve(entryUrl);
        //
        // logger.info("Protcol = " + nativeUrl.getProtocol());
        // logger.info("Path = " + nativeUrl.getPath());
    }

    /**
     * {@link UrumaActivator} のインスタンスを返します。<br />
     * 
     * @return {@link UrumaActivator} のインスタンス
     */
    public static UrumaActivator getInstance() {
        return plugin;
    }

    /**
     * {@link S2Container} のインスタンスを返します。<br />
     * 
     * @return {@link S2Container} のインスタンス
     */
    public S2Container getS2Container() {
        return container;
    }

    /*
     * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
     */
    @Override
    public final void stop(final BundleContext context) throws Exception {
        plugin = null;

        container.destroy();

        super.stop(context);
    }

    /**
     * {@link WorkbenchComponent} を返します。<br />
     * 
     * @return {@link WorkbenchComponent} オブジェクト
     */
    public WorkbenchComponent getWorkbenchComponent() {
        Template template = (Template) applicationContext
                .getValue(UrumaConstants.WORKBENCH_TEMPLATE_NAME);
        return (WorkbenchComponent) template.getRootComponent();
    }

    /**
     * ワークベンチウィンドウに対応する {@link WindowContext} を返します。<br />
     * 
     * @return {@link WindowContext}
     */
    public WindowContext getWorkbenchWindowContext() {
        return this.windowContext;
    }

    /**
     * 指定されたパスの画面定義XMLを読み込み、{@link Template} オブジェクトを生成します。<br />
     * 
     * @param path
     *            画面定義XMLのパス
     * @return {@link Template} オブジェクト
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

    protected void initS2Container() {
        try {
            S2Container urumaContainer = S2ContainerFactory
                    .create(UrumaConstants.URUMA_RCP_DICON_PATH);
            String configPath = SingletonS2ContainerFactory.getConfigPath();
            container = S2ContainerFactory.create(configPath);
            container.include(urumaContainer);

            container.init();
            SingletonS2ContainerFactory.setContainer(container);
        } catch (ResourceNotFoundRuntimeException ex) {
            logger.error(ex.getMessage(), ex);
            throw ex;
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

    private void test() throws Exception {
        IExtensionRegistry registry = Platform.getExtensionRegistry();

        StringBuffer buf = new StringBuffer(2048);
        buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
        buf.append("<?eclipse version=\"3.2\"?>");
        buf.append("<plugin>");
        buf.append("<extension point=\"org.eclipse.ui.views\">");
        buf
                .append("<view class=\"org.seasar.rcp.example.fileman.FolderViewPart\" id=\"org.seasar.rcp.example.fileman.FolderView\" name=\"フォルダ・ツリー\" />");
        buf
                .append("<view class=\"org.seasar.rcp.example.fileman.FileViewPart\" id=\"org.seasar.rcp.example.fileman.FileView\" name=\"ファイル・ビュー\" />");
        buf.append("</extension>");
        buf.append("</plugin>");
        ByteArrayInputStream is = new ByteArrayInputStream(buf.toString()
                .getBytes("UTF-8"));

        Object token = ((ExtensionRegistry) registry).getTemporaryUserToken();

        // System.out.println("ExtensionPointContributor = "
        // + extensionPoint.getContributor());
        //
        // IExtension viewExtension = extensionPoint
        // .getExtension("org.seasar.rcp.example.fileman.views");
        // if (viewExtension != null) {
        // RegistryContributor viewContributor = (RegistryContributor)
        // viewExtension
        // .getContributor();
        // System.out.println("ExtensionContributor = " + viewContributor);
        // } else {
        // System.out.println("ExtensionContributor = NULL");
        // }

        IContributor contributorOsgi = ContributorFactoryOSGi
                .createContributor(getBundle());
        System.out.println("ContributorByOSGI = " + contributorOsgi);

        registry.addContribution(is, contributorOsgi, false, null, null, token);

        // ExtensionRegistry registry = (ExtensionRegistry) RegistryFactory
        // .getRegistry();

        // IExtension viewExtension = extensionPoint
        // .getExtension("org.seasar.rcp.example.fileman.views");
        // System.out.println(viewExtension);
        // RegistryContributor viewContributor = (RegistryContributor)
        // viewExtension
        // .getContributor();
        //
        // RegistryObjectFactory factory = registry.getElementFactory();
        // String[] props = new String[] { "class",
        // "org.seasar.rcp.example.fileman.FileViewPart", "id",
        // "org.seasar.rcp.example.fileman.FileView", "name", "ファイル・ビュー" };

        // ConfigurationElement element = factory
        // .createConfigurationElement(-1, viewContributor.getActualId(),
        // "view", props ,new int[0], 0x80000000, );

        IExtensionPoint extensionPoint = registry
                .getExtensionPoint("org.eclipse.ui.views");
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
                System.out.println("ContributorClass = "
                        + contributor.getClass().getName());
                System.out
                        .println("ContributorName = " + contributor.getName());
                System.out.println("ConfigurationElementClass = "
                        + configurationElement.getClass().getName());

                String[] attrs = configurationElement.getAttributeNames();
                for (String string : attrs) {
                    System.out.println(string + "="
                            + configurationElement.getAttribute(string));
                }
            }
        }
    }
}
