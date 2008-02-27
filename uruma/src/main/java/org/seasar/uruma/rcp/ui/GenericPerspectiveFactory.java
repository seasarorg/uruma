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
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.rcp.PartComponent;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;

/**
 * <code>workbench.xml</code> に記述された <code>perspective</code>
 * 要素からパースペクティブを生成するクラスです。<br />
 * 
 * @author y-komori
 */
public class GenericPerspectiveFactory implements IPerspectiveFactory,
        UrumaMessageCodes {

    private static final String PART_LEFT = "LEFT";

    private static final String PART_RIGHT = "RIGHT";

    private static final String PART_TOP = "TOP";

    private static final String PART_BOTTOM = "BOTTOM";

    protected UrumaService service;

    protected TemplateManager templateManager;

    /**
     * {@link GenericPerspectiveFactory} を構築します。<br />
     */
    public GenericPerspectiveFactory() {
        this.service = UrumaServiceUtil.getService();
        this.templateManager = (TemplateManager) service.getContainer()
                .getComponent(TemplateManager.class);
    }

    /*
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
    public void createInitialLayout(final IPageLayout layout) {
        UrumaService service = UrumaServiceUtil.getService();

        layout.setEditorAreaVisible(false);

        String perspectiveId = layout.getDescriptor().getId();

        List<PerspectiveComponent> perspectives = service
                .getWorkbenchComponent().getPerspectives();

        PerspectiveComponent perspective = findPerspective(perspectives,
                perspectiveId);

        if (perspective != null) {
            List<UIElement> parts = perspective.getChildren();
            for (UIElement part : parts) {
                if (part instanceof PartComponent) {
                    if (!setupLayout(layout, (PartComponent) part)) {
                        throw new NotFoundException(
                                PART_IN_PERSPECTIVE_NOT_FOUND, service
                                        .getLocalId(perspectiveId),
                                ((PartComponent) part).ref);
                    }
                }
            }
        }
    }

    protected PerspectiveComponent findPerspective(
            final List<PerspectiveComponent> perspectives,
            final String perspectiveId) {
        String localId = service.getLocalId(perspectiveId);
        for (PerspectiveComponent perspective : perspectives) {
            if (localId.equals(perspective.id)) {
                return perspective;
            }
        }
        return null;
    }

    protected boolean setupLayout(final IPageLayout layout,
            final PartComponent part) {
        int pos = IPageLayout.LEFT;
        if (PART_LEFT.equals(part.position)) {
            pos = IPageLayout.LEFT;
        } else if (PART_RIGHT.equals(part.position)) {
            pos = IPageLayout.RIGHT;
        } else if (PART_TOP.equals(part.position)) {
            pos = IPageLayout.TOP;
        } else if (PART_BOTTOM.equals(part.position)) {
            pos = IPageLayout.BOTTOM;
        }

        float ratio = Integer.parseInt(part.ratio) / (float) 100;
        String refViewId = UrumaServiceUtil.getService().createRcpId(part.ref);

        if (findViewPart(refViewId)) {
            layout.addStandaloneView(refViewId, true, pos, ratio, layout
                    .getEditorArea());
            return true;
        } else {
            return false;
        }

    }

    protected boolean findViewPart(final String viewId) {
        List<Template> templates = templateManager
                .getTemplates(ViewPartComponent.class);
        String localViewId = service.getLocalId(viewId);

        for (Template template : templates) {
            ViewPartComponent viewPart = (ViewPartComponent) template
                    .getRootComponent();
            if (localViewId.equals(viewPart.getId())) {
                return true;
            }
        }

        return false;
    }
}
