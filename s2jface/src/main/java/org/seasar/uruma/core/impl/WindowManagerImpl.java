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

import org.seasar.jface.impl.S2JFaceApplicationWindow;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.impl.WindowComponent;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.WindowManager;

/**
 * ウィンドウを管理するためのクラスです。</br>
 * 
 * @author y-komori
 * @author bskuroneko
 */
public class WindowManagerImpl implements WindowManager {

    private Map<String, WindowComponent> windowMap = new HashMap<String, WindowComponent>();

    private TemplateManager templateManager = new TemplateManagerImpl();

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
        S2JFaceApplicationWindow window = openWindow(templatePath, true,
                argument);
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
        S2JFaceApplicationWindow window = openWindow(templatePath, false,
                argument);
        return window.getActionComponent();
    }

    /**
     * @param templatePath
     * @param modal
     * @param argument
     * @return
     */
    public S2JFaceApplicationWindow openWindow(final String templatePath,
            final boolean modal, final Object argument) {
        Template template = loadTemplate(templatePath);
        S2JFaceApplicationWindow window = new S2JFaceApplicationWindow();
        // window.setMenuManagerBuilder((MenuManagerBuilder) S2ContainerUtil
        // .getComponent(MenuManagerBuilder.class));
        window.init(template, modal);

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

}
