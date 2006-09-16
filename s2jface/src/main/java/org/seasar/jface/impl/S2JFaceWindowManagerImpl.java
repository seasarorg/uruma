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
import org.seasar.jface.component.factory.ComponentTreeBuilder;
import org.seasar.jface.component2.impl.Template;
import org.seasar.jface.component2.impl.WindowComponent;
import org.seasar.jface.exception.WindowManagerException;
import org.seasar.jface.util.AssertionUtil;

/**
 * ウィンドウを管理するためのクラスです。</br>
 * 
 * @author y-komori
 */
public class S2JFaceWindowManagerImpl implements S2JFaceWindowManager {
    private boolean blocking;

    // 現在開いているウィンドウを管理するためのクラス
    private WindowManager windowManager = new WindowManager();

    private Map<String, WindowComponent> windowMap = new HashMap<String, WindowComponent>();

    private Map<String, Template> templateCache = new HashMap<String, Template>();

    private ComponentTreeBuilder builder = new ComponentTreeBuilder();

    /*
     * @see org.seasar.jface.S2JFaceWindowManager#open(java.lang.String,
     *      boolean)
     */
    public void open(String templatePath, boolean blockOnOpen) {
        if (blockOnOpen) {
            startBlocking();
        }

        Template template = loadTemplate(templatePath);
        S2JFaceApplicationWindow window = new S2JFaceApplicationWindow();
        // window.setMenuManagerBuilder((MenuManagerBuilder) S2ContainerUtil
        // .getComponent(MenuManagerBuilder.class));
        window.init(template);

        window.setBlockOnOpen(blockOnOpen);
        windowManager.add(window);
        window.open();

        if (blockOnOpen) {
            endBlocking();
        }
    }

    /*
     * @see org.seasar.jface.S2JFaceWindowManager#open(java.lang.String)
     */
    public void open(String templatePath) {
        open(templatePath, false);
    }

    protected Template loadTemplate(final String path) {
        Template template = getTemplate(path);
        if (template == null) {
            template = (Template) builder.build(path);
            if (template != null) {
                registTemplate(template);
                return template;
            }
        }
        return template;
    }

    protected Template getTemplate(final String path) {
        return templateCache.get(path);
    }

    protected void registTemplate(final Template template) {
        AssertionUtil.assertNotNull("template", template);
        templateCache.put(template.getBasePath(), template);
        WindowComponent window = template.getWindowComponent();
        windowMap.put(window.getId(), window);
    }

    protected synchronized void startBlocking() {
        if (!blocking) {
            blocking = true;
        } else {
            throw new WindowManagerException(
                    WindowManagerException.ALREADY_BLOCKING);
        }
    }

    protected synchronized void endBlocking() {
        blocking = false;
    }

}
