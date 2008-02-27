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

import java.util.List;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;

/**
 * パースペクティブを自動的に生成するための {@link IPerspectiveFactory} です。<br />
 * 
 * @author y-komori
 */
public class AutoPerspectiveFactory implements IPerspectiveFactory {
    protected UrumaService service;

    protected TemplateManager templateManager;

    /**
     * {@link AutoPerspectiveFactory} を構築します。<br />
     */
    public AutoPerspectiveFactory() {
        this.service = UrumaServiceUtil.getService();
        this.templateManager = (TemplateManager) service.getContainer()
                .getComponent(TemplateManager.class);
    }

    /*
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
    public void createInitialLayout(final IPageLayout layout) {

        layout.setEditorAreaVisible(false);

        List<Template> templates = templateManager
                .getTemplates(ViewPartComponent.class);

        int size = templates.size();
        for (int i = 0; i < size; i++) {
            Template template = templates.get(i);
            ViewPartComponent viewPart = (ViewPartComponent) template
                    .getRootComponent();

            // 左から均等に配置されるように割合を計算
            float ratio = (1 / (float) (size - i));
            int position = IPageLayout.LEFT;
            if (i == (templates.size() - 1)) {
                position = IPageLayout.RIGHT;
            }

            layout.addStandaloneView(service.createRcpId(viewPart.getId()),
                    true, position, ratio, layout.getEditorArea());
        }
    }
}
