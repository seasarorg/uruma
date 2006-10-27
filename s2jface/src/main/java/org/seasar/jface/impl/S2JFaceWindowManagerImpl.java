/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.impl;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.WindowManager;
import org.seasar.jface.S2JFaceWindowManager;
import org.seasar.jface.component.Template;
import org.seasar.jface.component.factory.ComponentTreeBuilder;
import org.seasar.jface.component.impl.TemplateImpl;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.util.AssertionUtil;

/**
 * ウィンドウを管理するためのクラスです。</br>
 * 
 * @author y-komori
 * @author bskuroneko
 */
public class S2JFaceWindowManagerImpl implements S2JFaceWindowManager {
    // 現在開いているウィンドウを管理するためのクラス
    private WindowManager windowManager = new WindowManager();

    private Map<String, WindowComponent> windowMap = new HashMap<String, WindowComponent>();

    private Map<String, TemplateImpl> templateCache = new HashMap<String, TemplateImpl>();

    private ComponentTreeBuilder builder = new ComponentTreeBuilder();

    /*
     * @see org.seasar.jface.S2JFaceWindowManager#openModal(String)
     */
    public Object openModal(String templatePath) {
        return openModal(templatePath, null);
    }

    /*
     * @see org.seasar.jface.S2JFaceWindowManager#openModal(String, Object)
     */
    public Object openModal(String templatePath, Object argument) {
        S2JFaceApplicationWindow window = openWindow(templatePath, true,
                argument);
        return window.getReturnValue();
    }

    /*
     * @see org.seasar.jface.S2JFaceWindowManager#openModeless(String)
     */
    public Object openModeless(String templatePath) {
        return openModeless(templatePath, null);
    }

    /*
     * @see org.seasar.jface.S2JFaceWindowManager#openModeless(String, Object)
     */
    public Object openModeless(String templatePath, Object argument) {
        S2JFaceApplicationWindow window = openWindow(templatePath, false,
                argument);
        return window.getActionComponent();
    }

    public S2JFaceApplicationWindow openWindow(String templatePath,
            boolean modal, Object argument) {
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
        TemplateImpl template = getTemplate(path);
        if (template == null) {
            template = (TemplateImpl) builder.build(path);
            if (template != null) {
                registTemplate(template);
                return template;
            }
        }
        return template;
    }

    protected TemplateImpl getTemplate(final String path) {
        return templateCache.get(path);
    }

    protected void registTemplate(final TemplateImpl template) {
        AssertionUtil.assertNotNull("template", template);
        templateCache.put(template.getBasePath(), template);
        WindowComponent window = template.getWindowComponent();
        windowMap.put(window.getId(), window);
    }

}
