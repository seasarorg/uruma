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
package org.seasar.uruma.rcp.ui;

import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.UrumaActivator;

/**
 * Uruma における {@link WorkbenchAdvisor} です。<br />
 * 
 * @author y-komori
 */
public class UrumaWorkbenchAdvisor extends WorkbenchAdvisor {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(UrumaWorkbenchAdvisor.class);

    /*
     * @see org.eclipse.ui.application.WorkbenchAdvisor#initialize(org.eclipse.ui.application.IWorkbenchConfigurer)
     */
    @Override
    public void initialize(final IWorkbenchConfigurer configurer) {
        String imageBundle = UrumaConstants.DEFAULT_IMAGE_BUNDLE_PATH;

        logger.log(UrumaMessageCodes.LOADING_IMAGE_BUNDLE, imageBundle);
        ImageManager.loadImages(imageBundle);
    }

    /*
     * @see org.eclipse.ui.application.WorkbenchAdvisor#preShutdown()
     */
    @Override
    public boolean preShutdown() {
        ImageManager.dispose();
        return true;
    }

    /*
     * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
     */
    @Override
    public String getInitialWindowPerspectiveId() {
        UrumaActivator activator = UrumaActivator.getInstance();

        String perspectiveId = activator.getWorkbenchComponent().initialPerspectiveId;

        if (StringUtil.isNotBlank(perspectiveId)) {
            return activator.createRcpId(perspectiveId);
        } else {
            return activator.createRcpId(UrumaConstants.DEFAULT_PERSPECTIVE_ID);
        }
    }

    /*
     * @see org.eclipse.ui.application.WorkbenchAdvisor#createWorkbenchWindowAdvisor(org.eclipse.ui.application.IWorkbenchWindowConfigurer)
     */
    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
            final IWorkbenchWindowConfigurer configurer) {
        return new UrumaWorkbenchWindowAdvisor(configurer);
    }
}
