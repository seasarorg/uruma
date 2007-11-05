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

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.core.ViewTemplateLoader;
import org.seasar.uruma.core.io.ExtFileFilter;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.util.RcpResourceUtil;

/**
 * {@link ViewTemplateLoader} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class ViewTemplateLoaderImpl implements ViewTemplateLoader {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(TemplateManager.class);

    private TemplateManager templateManager;

    /*
     * @see org.seasar.uruma.core.ViewTemplateLoader#loadViewTemplates()
     */
    public void loadViewTemplates() throws IOException {
        URL localUrl = RcpResourceUtil
                .getLocalResourceUrl(UrumaConstants.DEFAULT_WORKBENCH_XML);
        logger.info("Protcol = " + localUrl.getProtocol());
        logger.info("Path = " + localUrl.getPath());

        if (UrumaConstants.PROTCOL_FILE.equals(localUrl.getProtocol())) {
            File localFile = new File(localUrl.getPath());
            File baseDir = new File(localFile.getParent()
                    + UrumaConstants.SLASH + UrumaConstants.DEFAULT_VIEWS_PATH);

            if (logger.isInfoEnabled()) {
                logger.log(UrumaMessageCodes.FINDING_XML_START, baseDir
                        .getAbsolutePath());
            }

            List<File> viewFiles = RcpResourceUtil.findFileResources(baseDir,
                    new ExtFileFilter("xml"));

            templateManager.loadTemplates(viewFiles);
        }

    }

    /**
     * {@link TemplateManager} を設定します。<br />
     * 
     * @param templateManager
     *            {@link TemplateManager}
     */
    @Binding(bindingType = BindingType.MUST)
    public void setTemplateManager(final TemplateManager templateManager) {
        this.templateManager = templateManager;
    }
}
