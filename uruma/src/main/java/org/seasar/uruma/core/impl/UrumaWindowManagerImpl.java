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
package org.seasar.uruma.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.WindowManager;
import org.seasar.framework.log.Logger;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.impl.WindowComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaWindowManager;
import org.seasar.uruma.ui.UrumaApplicationWindow;

/**
 * ウィンドウを管理するためのクラスです。</br>
 * 
 * @author y-komori
 * @author bskuroneko
 */
public class UrumaWindowManagerImpl implements UrumaWindowManager {
    private Logger logger = Logger.getLogger(UrumaWindowManager.class);

    private WindowManager windowManager = new WindowManager();

    private Map<String, WindowComponent> windowMap = new HashMap<String, WindowComponent>();

    private TemplateManager templateManager = new TemplateManagerImpl();

    private ApplicationContext applicationContext;

    /*
     * @see org.seasar.uruma.core.WindowManager#openModal(java.lang.String)
     */
    public Object openModal(final String templatePath) {
        return openModal(templatePath, null);
    }

    /*
     * @see org.seasar.uruma.core.WindowManager#openModal(java.lang.String,
     *      java.lang.Object)
     */
    public Object openModal(final String templatePath, final Object argument) {
        UrumaApplicationWindow window = openWindow(templatePath, true, argument);
        return window.getReturnValue();
    }

    /*
     * @see org.seasar.uruma.core.WindowManager#openModeless(java.lang.String)
     */
    public Object openModeless(final String templatePath) {
        return openModeless(templatePath, null);
    }

    /*
     * @see org.seasar.uruma.core.WindowManager#openModeless(java.lang.String,
     *      java.lang.Object)
     */
    public Object openModeless(final String templatePath, final Object argument) {
        UrumaApplicationWindow window = openWindow(templatePath, false,
                argument);
        return window.getPartActionComponent();
    }

    /**
     * 新しいウィンドウを開きます。<br />
     * 
     * @param templatePath
     *            テンプレートパス
     * @param modal
     *            モーダル属性
     * @param argument
     *            引数オブジェクト
     * @return 生成したウィンドウ
     */
    public UrumaApplicationWindow openWindow(final String templatePath,
            final boolean modal, final Object argument) {
        if (logger.isInfoEnabled()) {
            logger.log("IURM9903", new Object[] { templatePath });
        }

        Template template = loadTemplate(templatePath);
        UrumaApplicationWindow window = new UrumaApplicationWindow();
        window.init(applicationContext, (WindowComponent) template
                .getRootComponent(), modal);

        window.initActionComponent(argument);

        if (modal) {
            window.setBlockOnOpen(true);
        }
        windowManager.add(window);
        window.open();
        return window;
    }

    protected Template loadTemplate(final String path) {
        Template template = templateManager.getTemplate(path);
        WindowComponent window = (WindowComponent) template.getRootComponent();
        windowMap.put(window.getId(), window);
        return template;
    }

    /**
     * {@link ApplicationContext} を設定します。<br />
     * S2Container から設定される前提です。<br />
     * 
     * @param applicationContext
     *            {@link ApplicationContext} オブジェクト
     */
    public void setApplicationContext(
            final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
