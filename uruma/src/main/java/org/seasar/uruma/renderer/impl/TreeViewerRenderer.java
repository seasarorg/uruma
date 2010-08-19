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
package org.seasar.uruma.renderer.impl;

import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UICompositeComponent;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.TreeComponent;
import org.seasar.uruma.component.jface.TreeItemComponent;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;
import org.seasar.uruma.viewer.GenericLabelProvider;
import org.seasar.uruma.viewer.UrumaTreeViewer;

/**
 * {@link TreeViewer} のレンダリングを行うクラスです。<br />
 * 
 * @author y-komori
 */
public class TreeViewerRenderer extends
        AbstractViewerRenderer<TreeComponent, UrumaTreeViewer, Tree> {

    @Override
    protected Class<Tree> getWidgetType() {
        return Tree.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#canCreateViewer(org.seasar.uruma.component.UICompositeComponent)
     */
    @Override
    protected boolean canCreateViewer(final UICompositeComponent component) {
        // TreeItemComponent が存在する場合、Viewer を生成しない
        List<UIElement> children = component.getChildren();
        for (UIElement child : children) {
            if (child instanceof TreeItemComponent) {
                return false;
            }
        }
        return true;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#renderAfter(org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.component.UIComponent,
     *      org.seasar.uruma.context.WidgetHandle,
     *      org.seasar.uruma.context.PartContext)
     */
    @Override
    public void renderAfter(final WidgetHandle handle,
            final UIComponent uiComponent, final WidgetHandle parent,
            final PartContext context) {
        if (handle.instanceOf(Tree.class)) {
            TreeComponent component = (TreeComponent) uiComponent;
            Tree tree = handle.<Tree> getCastWidget();
            int autoExpandLevel = Integer.parseInt(component.autoExpandLevel);
            if (autoExpandLevel >= 1) {
                expandTree(tree.getItems(), 1, autoExpandLevel);
            }

            int columnCount = tree.getColumnCount();
            for (int i = 0; i < columnCount; i++) {
                TreeColumn column = tree.getColumn(i);
                column.pack();
            }
        }
        super.renderAfter(handle, uiComponent, parent, context);
    }

    protected void expandTree(final TreeItem[] items, final int currentLevel,
            final int maxLevel) {
        if (currentLevel >= maxLevel) {
            return;
        }

        for (TreeItem treeItem : items) {
            treeItem.setExpanded(true);
        }

        for (TreeItem treeItem : items) {
            expandTree(treeItem.getItems(), currentLevel + 1, maxLevel);
        }
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#doRenderViewer(org.seasar.uruma.component.jface.CompositeComponent,
     *      org.eclipse.jface.viewers.Viewer)
     */
    @Override
    protected void doRenderViewer(final TreeComponent uiComponent,
            final UrumaTreeViewer viewer) {
        int autoExpandLevel = Integer.parseInt(uiComponent.autoExpandLevel);
        viewer.setAutoExpandLevel(autoExpandLevel);
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractRenderer#getDefaultStyle()
     */
    @Override
    protected int getDefaultStyle() {
        return SWT.BORDER;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getDefaultLabelProvider()
     */
    @Override
    protected IBaseLabelProvider getDefaultLabelProvider() {
        return new GenericLabelProvider();
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getViewerType()
     */
    @Override
    protected Class<UrumaTreeViewer> getViewerType() {
        return UrumaTreeViewer.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getDefaultContentProvider()
     */
    @Override
    protected IContentProvider getDefaultContentProvider() {
        return new TreeNodeContentProvider();
    }
}
