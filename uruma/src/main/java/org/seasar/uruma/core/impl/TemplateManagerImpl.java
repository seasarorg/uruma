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

import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIContainer;
import org.seasar.uruma.component.factory.ComponentTreeBuilder;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;

/**
 * {@link TemplateManager} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class TemplateManagerImpl implements TemplateManager {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(TemplateManager.class);

    private Map<String, Template> templateCache = new HashMap<String, Template>();

    private Map<String, String> idToPathMap = new HashMap<String, String>();

    private ComponentTreeBuilder builder = new ComponentTreeBuilder();

    /*
     * @see org.seasar.uruma.core.TemplateManager#getTemplate(java.lang.String)
     */
    public Template getTemplate(final String path) {
        // TODO キャッシュ機構に問題があるため、いったん無効。Template のコピーを返す必要がある。
        Template template = templateCache.get(path);
        // Template template = null;
        if (template == null) {
            logger.log(UrumaMessageCodes.LOAD_TEMPLATE_FROM_FILE, path);

            template = builder.build(path);
            if (template != null) {
                templateCache.put(path, template);
                String id = template.getRootComponent().getId();
                if (StringUtil.isNotBlank(id)) {
                    if (!idToPathMap.containsKey(id)) {
                        idToPathMap.put(id, path);

                        String type = template.getRootComponent().getClass()
                                .getSimpleName();
                        logger.log(UrumaMessageCodes.TEMPLATE_REGISTERED, id,
                                type, path);
                    } else {
                        // TODO テンプレートIDが重複しているので例外スロー
                    }
                }
            }
        } else {
            logger.log(UrumaMessageCodes.LOAD_TEMPLATE_FROM_CACHE, path);
        }

        return template;
    }

    /*
     * @see org.seasar.uruma.core.TemplateManager#getTemplates(java.lang.Class)
     */
    public List<Template> getTemplates(
            final Class<? extends UIContainer> componentClass) {
        List<Template> templates = new ArrayList<Template>();

        for (Template template : templateCache.values()) {
            UIContainer root = template.getRootComponent();
            if ((root != null) && componentClass.equals(root.getClass())) {
                templates.add(template);
            }
        }
        return templates;
    }

    /*
     * @see org.seasar.uruma.core.TemplateManager#loadTemplates(java.util.List)
     */
    public void loadTemplates(final List<String> pathList) {
        for (String path : pathList) {
            getTemplate(path);
        }
    }
}
