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
package org.seasar.uruma.rcp.ui;

import static org.seasar.uruma.core.UrumaConstants.*;
import static org.seasar.uruma.core.UrumaMessageCodes.*;

import java.util.ResourceBundle;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;
import org.seasar.uruma.resource.ResourceRegistry;
import org.seasar.uruma.resource.internal.DefaultResourceRegistry;
import org.seasar.uruma.ui.dialogs.UrumaErrorDialog;
import org.seasar.uruma.util.MessageUtil;

/**
 * Uruma における {@link WorkbenchAdvisor} です。<br />
 * 
 * @author y-komori
 */
public class UrumaWorkbenchAdvisor extends WorkbenchAdvisor {
    private static final UrumaLogger logger = UrumaLogger.getLogger(UrumaWorkbenchAdvisor.class);

    /*
     * @see org.eclipse.ui.application.WorkbenchAdvisor#initialize(org.eclipse.ui.application.IWorkbenchConfigurer)
     */
    @Override
    public void initialize(final IWorkbenchConfigurer configurer) {
        UrumaService service = UrumaServiceUtil.getService();
        S2Container container = service.getContainer();

        container.register(configurer);

        // ウィンドウの位置・サイズの保存可否を指定します。
        WorkbenchComponent workbench = service.getWorkbenchComponent();
        boolean flg = Boolean.parseBoolean(workbench.saveAndRestore);
        configurer.setSaveAndRestore(flg);

        // リソースレジストリにイメージリソースを読み込みます。
        DefaultResourceRegistry registry = (DefaultResourceRegistry) container
                .getComponent(ResourceRegistry.class);
        ResourceBundle imageBundle = service.getImageBundle();
        if (imageBundle != null) {
            logger.log(LOADING_IMAGE_BUNDLE, imageBundle);
            registry.loadImages(imageBundle);
        }
    }

    /*
     * @see org.eclipse.ui.application.WorkbenchAdvisor#preShutdown()
     */
    @Override
    public boolean preShutdown() {
        DefaultResourceRegistry registry = (DefaultResourceRegistry) UrumaServiceUtil.getService()
                .getContainer().getComponent(ResourceRegistry.class);
        registry.dispose();
        return true;
    }

    /*
     * @see org.eclipse.ui.application.WorkbenchAdvisor#getInitialWindowPerspectiveId()
     */
    @Override
    public String getInitialWindowPerspectiveId() {
        UrumaService service = UrumaServiceUtil.getService();

        String perspectiveId = service.getWorkbenchComponent().initialPerspectiveId;

        if (StringUtil.isNotBlank(perspectiveId)) {
            WorkbenchComponent workbench = UrumaServiceUtil.getService().getWorkbenchComponent();
            if (workbench.findPerspective(perspectiveId) != null) {
                return service.createRcpId(perspectiveId);
            } else {
                throw new NotFoundException(PERSPECTIVE_NOT_FOUND, perspectiveId);
            }
        } else {
            return service.createRcpId(DEFAULT_PERSPECTIVE_ID);
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

    /*
     * @see org.eclipse.ui.application.WorkbenchAdvisor#eventLoopException(java.lang.Throwable)
     */
    @Override
    public void eventLoopException(final Throwable throwable) {
        logger.log(EXCEPTION_OCCURED_WITH_REASON, throwable, throwable.getMessage());

        Display display = getWorkbenchConfigurer().getWorkbench().getDisplay();
        boolean displayCreated = false;
        if (display == null) {
            display = Display.getCurrent();

            if (display == null) {
                display = new Display();
                displayCreated = true;
            }
        }
        Shell shell = new Shell(display);
        String msg = MessageUtil.getMessageWithBundleName(URUMA_MESSAGE_BASE,
                "RCP_EXCEPTION_OCCURED");
        UrumaErrorDialog dialog = new UrumaErrorDialog(shell, "Uruma", msg, throwable);
        dialog.open();
        shell.dispose();

        if (displayCreated) {
            display.dispose();
        }
    }
}
