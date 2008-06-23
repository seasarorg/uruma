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

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.UIElementContainer;
import org.seasar.uruma.component.rcp.PartComponent;
import org.seasar.uruma.component.rcp.PartFolderComponent;
import org.seasar.uruma.component.rcp.PerspectiveComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.core.TemplateManager;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.rcp.util.UrumaServiceUtil;
import org.seasar.uruma.rcp.util.ViewPartUtil;

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

    protected int folderNum = 1;

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

        if (perspective == null) {
            return;
        }

        setupChildren(perspective, layout, perspectiveId);
    }

    protected void setupChildren(final UIElementContainer container,
            final Object layout, final String perspectiveId) {
        List<UIElement> children = container.getChildren();
        for (UIElement child : children) {
            if (child instanceof PartComponent) {
                PartComponent part = (PartComponent) child;
                if (!setupLayout(layout, part, container)) {
                    throw new NotFoundException(PART_IN_PERSPECTIVE_NOT_FOUND,
                            service.getLocalId(perspectiveId), (part).ref);
                }
            } else if (child instanceof PartFolderComponent) {
                PartFolderComponent component = (PartFolderComponent) child;
                IFolderLayout folder = createFolder((IPageLayout) layout,
                        component);
                setupChildren(component, folder, perspectiveId);
            }
        }

    }

    protected IFolderLayout createFolder(final IPageLayout layout,
            final PartFolderComponent folder) {
        int pos = getPosition(folder.position);
        float ratio = getRatio(folder.ratio);
        String id = getFolderId(folder);
        return layout.createFolder(id, pos, ratio, layout.getEditorArea());
    }

    protected boolean setupLayout(final Object layout,
            final PartComponent part, final UIElementContainer parent) {
        int pos = getPosition(part.position);
        float ratio = getRatio(part.ratio);
        String refViewId = UrumaServiceUtil.getService().createRcpId(part.ref);

        if (findViewPart(refViewId)) {
            addView(layout, refViewId, pos, ratio);
            return true;
        } else {
            // あらかじめ定義されたビューがあれば、表示する
            // TODO ビューが見つからない場合はエラーとする
            addView(layout, part.ref, pos, ratio);
            return true;
        }
    }

    protected void addView(final Object parent, final String viewId,
            final int pos, final float ratio) {
        if (parent instanceof IPageLayout) {
            IPageLayout layout = (IPageLayout) parent;
            layout.addStandaloneView(viewId, true, pos, ratio, layout
                    .getEditorArea());
        } else if (parent instanceof IFolderLayout) {
            IFolderLayout layout = (IFolderLayout) parent;
            layout.addView(viewId);
        }
    }

    protected int getPosition(final String str) {
        int pos = IPageLayout.LEFT;
        if (PART_LEFT.equals(str)) {
            pos = IPageLayout.LEFT;
        } else if (PART_RIGHT.equals(str)) {
            pos = IPageLayout.RIGHT;
        } else if (PART_TOP.equals(str)) {
            pos = IPageLayout.TOP;
        } else if (PART_BOTTOM.equals(str)) {
            pos = IPageLayout.BOTTOM;
        }
        return pos;
    }

    protected float getRatio(final String str) {
        if (!StringUtil.isEmpty(str)) {
            return Integer.parseInt(str) / (float) 100;
        } else {
            return 0.95f;
        }
    }

    protected String getFolderId(final PartFolderComponent folder) {
        if (!StringUtil.isEmpty(folder.id)) {
            return folder.id;
        } else {
            return "folder" + folderNum++;
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

    protected boolean findViewPart(final String viewId) {
        List<Template> templates = templateManager
                .getTemplates(ViewPartComponent.class);
        String localViewId = ViewPartUtil.getPrimaryId(service
                .getLocalId(viewId));

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
