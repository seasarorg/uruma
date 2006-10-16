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
package org.seasar.jface;

import org.eclipse.swt.widgets.Display;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.S2ContainerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.framework.container.factory.TigerAnnotationHandler;
import org.seasar.jface.container.factory.S2JFaceComponentDefBuilder;
import org.seasar.jface.util.ImageManager;

/**
 * @author y-komori
 * 
 */
public class S2JFace {
    protected S2Container container;

    private Display display;

    public static void main(String[] args) {
        if (args.length >= 1) {
            String templatePath = args[0];
            S2JFace s2JFace = new S2JFace();
            s2JFace.openWindow(templatePath);
        } else {
            System.err.println("[Error] 第1引数に初期画面のテンプレートパスを指定してください.");
        }
    }

    public S2JFace() {
        initS2Container();
    }

    public S2JFace(String configPath) {
        SingletonS2ContainerFactory.setConfigPath(configPath);
        initS2Container();
    }

    public void openWindow(final String templatePath) {
        display = Display.getCurrent();
        if (display == null) {
            display = new Display();
        }
        try {
            setupImageManager();

            S2JFaceWindowManager windowManager = (S2JFaceWindowManager) container
                    .getComponent(S2JFaceWindowManager.class);
            windowManager.open(templatePath, true);
        } finally {
            dispose();
        }
    }

    protected void initS2Container() {
        TigerAnnotationHandler
                .addComponentDefBuilder(new S2JFaceComponentDefBuilder());
        
        S2Container s2JFaceContainer = S2ContainerFactory.create(S2JFaceConstants.S2JFACE_DICON_PATH);
        String configPath = SingletonS2ContainerFactory.getConfigPath();
        container = S2ContainerFactory.create(configPath);
        container.include(s2JFaceContainer);
        
        container.init();
        SingletonS2ContainerFactory.setContainer(container);
    }

    protected void setupImageManager() {
        ImageManager.loadImages("s2JFaceImages");
    }

    protected void dispose() {
        ImageManager.dispose();
        display.dispose();
        display = null;
    }
}
