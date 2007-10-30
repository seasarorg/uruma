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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.window.WindowManager;
import org.eclipse.swt.widgets.Shell;
import org.seasar.framework.util.AssertionUtil;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.impl.WindowComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.ContextFactory;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.context.impl.ApplicationContextImpl;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.core.UrumaWindowManager;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.ui.UrumaApplicationWindow;

/**
 * ウィンドウを管理するためのクラスです。</br>
 * 
 * @author y-komori
 * @author bskuroneko
 */
public class UrumaWindowManagerImpl implements UrumaWindowManager {
    private UrumaLogger logger = UrumaLogger
            .getLogger(UrumaWindowManager.class);

    private WindowManager windowManager = new WindowManager();

    private Map<String, WindowComponent> windowMap = new HashMap<String, WindowComponent>();

    private List<UrumaApplicationWindow> windowList = new ArrayList<UrumaApplicationWindow>();

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

    /*
     * @see org.seasar.uruma.core.UrumaWindowManager#close(java.lang.String)
     */
    public void close(final String windowId) {
        UrumaApplicationWindow window = findWindow(windowId);
        if (window != null) {
            logger.log(UrumaMessageCodes.CLOSE_WINDOW, windowId);

            windowList.remove(window);
            windowMap.remove(windowId);
            ((ApplicationContextImpl) applicationContext)
                    .disposeWindowContext(windowId);

            Shell shell = window.getShell();
            if (shell != null && !shell.isDisposed()) {
                shell.close();
            }
        }
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
        Template template = loadTemplate(templatePath);
        UrumaApplicationWindow window = createWindow();
        WindowComponent windowComponent = (WindowComponent) template
                .getRootComponent();
        String windowId = windowComponent.getId();

        WindowContext windowContext = ContextFactory.createWindowContext(
                applicationContext, windowId);

        logger.log(UrumaMessageCodes.INIT_WINDOW, windowId);
        window.init(windowContext, windowComponent, modal);

        window.initActionComponent(argument);

        if (modal) {
            window.setBlockOnOpen(true);
        }
        windowManager.add(window);

        logger.log(UrumaMessageCodes.OPEN_WINDOW, windowId);
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

    protected UrumaApplicationWindow createWindow() {
        UrumaApplicationWindow window = new UrumaApplicationWindow(this);
        windowList.add(window);
        return window;
    }

    protected UrumaApplicationWindow findWindow(final String windowId) {
        AssertionUtil.assertNotNull("windowId", windowId);

        for (UrumaApplicationWindow window : windowList) {
            if (windowId.equals(window.getWindowId())) {
                return window;
            }
        }
        return null;
    }
}
