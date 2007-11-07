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

import java.util.List;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.rcp.PartComponent;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.rcp.UrumaActivator;

/**
 * パースペクティブを自動的に生成するための {@link IPerspectiveFactory} です。<br />
 * 
 * @author y-komori
 */
public class AutoPerspectiveFactory implements IPerspectiveFactory {
    private static final UrumaLogger logger = UrumaLogger
            .getLogger(AutoPerspectiveFactory.class);

    private static final String PART_LEFT = "LEFT";

    private static final String PART_RIGHT = "RIGHT";

    private static final String PART_TOP = "TOP";

    private static final String PART_BOTTOM = "BOTTOM";

    private TemplateManager templateManager;

    /**
     * {@link AutoPerspectiveFactory} を構築します。<br />
     */
    public AutoPerspectiveFactory() {
        this.templateManager = (TemplateManager) UrumaActivator.getInstance()
                .getS2Container().getComponent(TemplateManager.class);
    }

    /*
     * @see org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui.IPageLayout)
     */
    public void createInitialLayout(final IPageLayout layout) {
        UrumaActivator uruma = UrumaActivator.getInstance();

        layout.setEditorAreaVisible(false);

        String perspectiveId = layout.getDescriptor().getId();
        if (uruma.createRcpId(UrumaConstants.DEFAULT_PERSPECTIVE_ID).equals(
                perspectiveId)) {
            createLayoutAuto(layout);
        } else {
            createLayoutFromXML(layout);
        }
    }

    protected void createLayoutAuto(final IPageLayout layout) {
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

            layout.addStandaloneView(viewPart.getRcpId(), true, position,
                    ratio, layout.getEditorArea());
        }
    }

    protected void createLayoutFromXML(final IPageLayout layout) {
        UrumaActivator uruma = UrumaActivator.getInstance();

        WorkbenchComponent workbench = uruma.getWorkbenchComponent();
        String perspectiveId = layout.getDescriptor().getId();
        PerspectiveComponent pespective = findPerspective(workbench
                .getChildren(), perspectiveId);
        if (pespective != null) {
            List<UIElement> parts = pespective.getChildren();
            for (UIElement part : parts) {
                if (part instanceof PartComponent) {
                    if (!setupLayout(layout, (PartComponent) part)) {
                        throw new NotFoundException(
                                UrumaMessageCodes.PART_IN_PERSPECTIVE_NOT_FOUND,
                                perspectiveId, ((PartComponent) part).ref);
                    }
                }
            }
        } else {
            logger.error("パースペクティブ " + perspectiveId + " が見つかりません.");

        }
    }

    protected PerspectiveComponent findPerspective(
            final List<UIElement> elements, final String perspectiveId) {
        for (UIElement element : elements) {
            if (element instanceof PerspectiveComponent) {
                PerspectiveComponent perspective = (PerspectiveComponent) element;
                if (perspectiveId.equals(perspective.getRcpId())) {
                    return perspective;
                }
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
        String refViewId = UrumaActivator.getInstance().createRcpId(part.ref);

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

        for (Template template : templates) {
            ViewPartComponent viewPart = (ViewPartComponent) template
                    .getRootComponent();
            if (viewId.equals(viewPart.getRcpId())) {
                return true;
            }
        }

        return false;
    }
}
