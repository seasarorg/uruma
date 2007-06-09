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
package org.seasar.jface.impl;

import java.util.HashMap;
import java.util.Map;

import org.seasar.jface.S2JFaceTemplateManager;
import org.seasar.jface.component.Template;
import org.seasar.jface.component.factory.ComponentTreeBuilder;

/**
 * @author y-komori
 * 
 */
public class S2JFaceTemplateManagerImpl implements S2JFaceTemplateManager {
    private Map<String, Template> templateCache = new HashMap<String, Template>();

    private ComponentTreeBuilder builder = new ComponentTreeBuilder();

    /*
     * @see org.seasar.jface.S2JFaceTemplateManager#getTemplate(java.lang.String)
     */
    public Template getTemplate(final String path) {
        Template template = templateCache.get(path);
        if (template == null) {
            template = builder.build(path);
            if (template != null) {
                templateCache.put(template.getBasePath(), template);
            }
        }

        return template;
    }
}
