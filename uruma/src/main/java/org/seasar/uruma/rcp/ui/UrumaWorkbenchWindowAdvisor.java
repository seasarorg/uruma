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

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.seasar.eclipse.common.util.GeometryUtil;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.rcp.UrumaActivator;
import org.seasar.uruma.util.PathUtil;

/**
 * ワークベンチウィンドウに関する設定を行うクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {
    private WorkbenchComponent workbench;

    /**
     * {@link UrumaWorkbenchWindowAdvisor} を構築します。<br />
     * 
     * @param configurer
     *            {@link IWorkbenchWindowConfigurer} オブジェクト
     */
    public UrumaWorkbenchWindowAdvisor(
            final IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    /*
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#createActionBarAdvisor(org.eclipse.ui.application.IActionBarConfigurer)
     */
    @Override
    public ActionBarAdvisor createActionBarAdvisor(
            final IActionBarConfigurer configurer) {
        return new UrumaActionBarAdvisor(configurer);
    }

    @Override
    public void preWindowOpen() {
        workbench = UrumaActivator.getInstance().getWorkbenchComponent();

        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();

        configurer.setTitle(workbench.title);

        configurer.setInitialSize(calcInitialSize(workbench.initWidth,
                workbench.initHeight));

        setupStatusLine(workbench, configurer);

        // プリレンダリング処理
        WindowContext windowContext = UrumaActivator.getInstance()
                .getWorkbenchWindowContext();
        workbench.preRender(null, windowContext);

        // TODO ここで XML から情報を読み込んでワークベンチの情報を設定する
        // configurer.setShowMenuBar(true);
        configurer.setShowCoolBar(false);
    }

    protected Point calcInitialSize(final String width, final String height) {
        String widthStr = StringUtil.isNotBlank(width) ? width
                : UrumaConstants.DEFAULT_WORKBENCH_WIDTH;
        String heightStr = StringUtil.isNotBlank(height) ? height
                : UrumaConstants.DEFAULT_WORKBENCH_HEIGHT;

        int xSize = GeometryUtil.calcSize(widthStr, Display.getCurrent()
                .getClientArea().width);
        int ySize = GeometryUtil.calcSize(heightStr, Display.getCurrent()
                .getClientArea().height);
        return new Point(xSize, ySize);
    }

    /*
     * @see org.eclipse.ui.application.WorkbenchWindowAdvisor#postWindowCreate()
     */
    @Override
    public void postWindowCreate() {
        setupImage(workbench);
    }

    protected void setupImage(final WorkbenchComponent workbench) {
        if (StringUtil.isNotBlank(workbench.image)) {
            Image image = ImageManager.getImage(workbench.image);
            if (image == null) {
                String path = PathUtil.createPath(workbench.getBasePath(),
                        workbench.image);
                image = ImageManager.loadImage(path);
            }

            if (image != null) {
                IWorkbenchWindowConfigurer configurator = getWindowConfigurer();
                Shell shell = configurator.getWindow().getShell();
                shell.setImage(image);
            }
        }
    }

    protected void setupStatusLine(final WorkbenchComponent workbench,
            final IWorkbenchWindowConfigurer configurer) {
        configurer
                .setShowStatusLine(Boolean.parseBoolean(workbench.statusLine));
    }
}
