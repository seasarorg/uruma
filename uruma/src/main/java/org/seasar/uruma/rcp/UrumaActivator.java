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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.seasar.uruma.component.UIComponentContainer;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.impl.PerspectiveComponent;
import org.seasar.uruma.component.impl.ViewPartComponent;
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
import org.seasar.uruma.rcp.configuration.ContributionBuilder;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.impl.ViewElement;
import org.seasar.uruma.rcp.ui.GenericViewPart;

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

    private IContributor contributor;

    private String pluginId;

    private List<Extension> extensions = new ArrayList<Extension>();

    private WorkbenchComponent workbenchComponent;

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

        initS2Container();
        registComponentsToS2Container();
        setupContributor();

        Template workbenchTemplate = getTemplate(UrumaConstants.DEFAULT_WORKBENCH_XML);
        UIComponentContainer root = workbenchTemplate.getRootComponent();
        if (root instanceof WorkbenchComponent) {
            this.workbenchComponent = (WorkbenchComponent) root;
        } else {
            throw new NotFoundException(
                    UrumaMessageCodes.WORKBENCH_ELEMENT_NOT_FOUND,
                    workbenchTemplate.getPath());
        }

        applicationContext.setValue(UrumaConstants.WORKBENCH_TEMPLATE_NAME,
                workbenchTemplate);
        this.windowContext = ContextFactory.createWindowContext(
                applicationContext, UrumaConstants.WORKBENCH_WINDOW_CONTEXT_ID);

        setupViews();
        ContributionBuilder.build(contributor, extensions);
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
     * ビューを定義した XML を自動的に検索して読み込み、Extension として 動的に生成します。<br />
     * 
     * @throws IOException
     */
    protected void setupViews() throws IOException {
        templateLoader.loadViewTemplates();

        List<Template> viewTemplates = templateManager
                .getTemplates(ViewPartComponent.class);
        Extension extension = new Extension(ExtensionPoints.VIEWS);

        for (Template template : viewTemplates) {
            ViewPartComponent component = (ViewPartComponent) template
                    .getRootComponent();
            ViewElement element = new ViewElement();
            element.id = pluginId + UrumaConstants.PERIOD + component.getId();
            component.rcpId = element.id;
            element.className = GenericViewPart.class.getName();
            element.name = component.title;
            element.allowMultiple = component.allowMultiple;

            extension.addConfigurationElement(element);
        }
        extensions.add(extension);
    }

    protected void setupPerspectives() {
        for (UIElement child : workbenchComponent.getChildren()) {
            if (child instanceof PerspectiveComponent) {
                PerspectiveComponent perspective = (PerspectiveComponent) child;

                Extension extension = new Extension(ExtensionPoints.PERSPECTIVE);

            }

        }
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
     * 現在のプラグイン ID を返します。<br />
     * 
     * @return プラグイン ID
     */
    public String getPluginId() {
        return this.pluginId;
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

    protected void setupContributor() {
        this.contributor = ContributorFactoryOSGi
                .createContributor(getBundle());
        this.pluginId = contributor.getName();
    }

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
