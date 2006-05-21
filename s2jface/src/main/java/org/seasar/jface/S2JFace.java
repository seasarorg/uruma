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

import org.seasar.framework.container.factory.AnnotationHandlerFactory;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.container.factory.S2JFaceAnnotationHandler;
import org.seasar.jface.impl.TemplateWindow;
import org.seasar.jface.template.TemplateBuilder;

/**
 * @author y-komori
 * 
 */
public class S2JFace {
    public void openWindow(String path) {
        AnnotationHandlerFactory
                .setAnnotationHandler(new S2JFaceAnnotationHandler());
        SingletonS2ContainerFactory.init();

        TemplateBuilder builder = new TemplateBuilder();

        TemplateComponent template = (TemplateComponent) builder.build(path);
        TemplateWindow templateWindow = new TemplateWindow(template);
        templateWindow.setBlockOnOpen(true);
        templateWindow.open();
    }
}
