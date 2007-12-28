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

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.ComponentUtil;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.ViewTemplateLoader;
import org.seasar.uruma.log.UrumaLogger;

/**
 * @author y-komori
 * 
 */
public class UrumaService {
    private static UrumaService instance;

    private static final UrumaLogger logger = UrumaLogger
            .getLogger(UrumaService.class);

    private S2Container container;

    private TemplateManager templateManager;

    private ViewTemplateLoader templateLoader;

    private ApplicationContext applicationContext;

    private WindowContext windowContext;

    private WorkbenchComponent workbenchComponent;

    private UrumaService() {
    }

    public static UrumaService getInstance() {
        if (instance != null) {
            return instance;
        } else {
            // TODO 例外クラスを作る
            throw new RuntimeException();
        }
    }

    static void init() {
        instance = new UrumaService();
    }

    protected void initS2Container() {
        try {
            S2Container urumaContainer = S2ContainerFactory
                    .create(UrumaConstants.URUMA_RCP_DICON_PATH);

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

}
