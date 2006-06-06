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

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.window.WindowManager;
import org.eclipse.swt.widgets.Display;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.factory.TigerAnnotationHandler;
import org.seasar.jface.S2JFace;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.container.factory.S2JFaceComponentDefFactory;
import org.seasar.jface.exception.NotFoundException;
import org.seasar.jface.template.TemplateBuilder;

/**
 * @author y-komori
 * 
 */
public class S2JFaceImpl implements S2JFace {
    private WindowManager windowManager;

    private Map<String, WindowComponent> windowMap = new HashMap<String, WindowComponent>();

    private TemplateBuilder builder = new TemplateBuilder();

    private Display display;

    public S2JFaceImpl() {
        TigerAnnotationHandler
                .addComponentDefFactory(new S2JFaceComponentDefFactory());
        SingletonS2ContainerFactory.init();

        this.windowManager = new WindowManager();
        this.display = new Display();
    }

    public String loadTemplate(String path) {
        TemplateComponent template = (TemplateComponent) builder.build(path);
        if (template != null) {
            Collection children = template.getChildren();
            if (children.iterator().hasNext()) {
                WindowComponent windowComponent = (WindowComponent) children
                        .iterator().next();
                String windowName = windowComponent.getId();
                windowMap.put(windowName, windowComponent);
                return windowName;
            }
        }
        return null;
    }

    public String[] loadTemplates(String directoryName) {
        // TODO 自動生成されたメソッド・スタブ
        return null;

    }

    public void openWindow(String windowName) {
        WindowComponent windowComponent = windowMap.get(windowName);
        if (windowComponent != null) {
            TemplateWindow templateWindow = new TemplateWindow(
                    (TemplateComponent) windowComponent.getParent());
            templateWindow.setBlockOnOpen(true);
            windowManager.add(templateWindow);
            templateWindow.open();
        } else {
            throw new NotFoundException(NotFoundException.WINDOW, windowName);
        }
    }
}
