/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.rcp.configuration.extension;

import java.util.List;

import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.rcp.configuration.Extension;
import org.seasar.uruma.rcp.configuration.ExtensionBuilder;
import org.seasar.uruma.rcp.configuration.ExtensionFactory;
import org.seasar.uruma.rcp.configuration.ExtensionPoints;
import org.seasar.uruma.rcp.configuration.elements.ViewElement;

/**
 * <code>views</code> 拡張ポイントを生成するための {@link ExtensionBuilder} です。<br />
 * 
 * @author y-komori
 */
public class ViewsBuilder extends AbstractExtensionBuilder {

    /*
     * @see org.seasar.uruma.rcp.configuration.ExtensionBuilder#buildExtension()
     */
    public Extension[] buildExtension() {
        TemplateManager templateManager = (TemplateManager) service
                .getContainer().getComponent(TemplateManager.class);
        List<Template> viewTemplates = templateManager
                .getTemplates(ViewPartComponent.class);

        Extension extension = ExtensionFactory
                .createExtension(ExtensionPoints.VIEWS);
        for (Template template : viewTemplates) {
            ViewPartComponent component = (ViewPartComponent) template
                    .getRootComponent();
            ViewElement element = new ViewElement(component);
            extension.addElement(element);
        }

        return new Extension[] { extension };
    }
}
