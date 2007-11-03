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

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.seasar.eclipse.common.util.GeometryUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIContainer;
import org.seasar.uruma.component.impl.WorkbenchComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.rcp.UrumaActivator;

/**
 * ワークベンチウィンドウに関する設定を行うクラスです。<br />
 * 
 * @author y-komori
 */
public class UrumaWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

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
        ApplicationContext context = (ApplicationContext) UrumaActivator
                .getInstance().getS2Container().getComponent(
                        ApplicationContext.class);
        Template template = (Template) context
                .getValue(UrumaConstants.WORKBENCH_TEMPLATE_NAME);
        UIContainer component = template.getRootComponent();
        if (component instanceof WorkbenchComponent) {
            WorkbenchComponent workbench = (WorkbenchComponent) component;

            IWorkbenchWindowConfigurer configurer = getWindowConfigurer();

            // タイトルの設定
            configurer.setTitle(workbench.title);

            // 初期サイズの設定
            configurer.setInitialSize(calcInitialSize(workbench.initWidth,
                    workbench.initHeight));
        } else {
            throw new NotFoundException(
                    UrumaMessageCodes.WORKBENCH_TAG_NOT_FOUND, template
                            .getBasePath());
        }

        // TODO ここで XML から情報を読み込んでワークベンチの情報を設定する
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setShowMenuBar(true);
        configurer.setShowCoolBar(false);
        configurer.setShowStatusLine(true);
    }

    protected Point calcInitialSize(final String width, final String height) {
        String widthStr = StringUtil.isNotBlank(width) ? width : "30%";
        String heightStr = StringUtil.isNotBlank(height) ? height : "30%";

        int xSize = GeometryUtil.calcSize(widthStr, Display.getCurrent()
                .getClientArea().width);
        int ySize = GeometryUtil.calcSize(heightStr, Display.getCurrent()
                .getClientArea().height);
        return new Point(xSize, ySize);
    }

}
