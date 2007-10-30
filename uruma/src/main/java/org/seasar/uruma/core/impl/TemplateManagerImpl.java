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

import org.seasar.uruma.component.Template;
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
    private UrumaLogger logger = UrumaLogger.getLogger(TemplateManager.class);

    private Map<String, Template> templateCache = new HashMap<String, Template>();

    private ComponentTreeBuilder builder = new ComponentTreeBuilder();

    /*
     * @see org.seasar.uruma.core.TemplateManager#getTemplate(java.lang.String)
     */
    public Template getTemplate(final String path) {
        // TODO キャッシュ機構に問題があるため、いったん無効。Template のコピーを返す必要がある。
        // Template template = templateCache.get(path);
        Template template = null;
        if (template == null) {
            logger.log(UrumaMessageCodes.LOAD_TEMPLATE_FROM_FILE, path);

            template = builder.build(path);
            if (template != null) {
                templateCache.put(path, template);
            }
        } else {
            logger.log(UrumaMessageCodes.LOAD_TEMPLATE_FROM_CACHE, path);
        }

        return template;
    }
}
