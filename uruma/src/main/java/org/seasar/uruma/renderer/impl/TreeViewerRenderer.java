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
package org.seasar.uruma.renderer.impl;

import java.util.List;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.seasar.uruma.component.UICompositeComponent;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.TreeComponent;
import org.seasar.uruma.component.jface.TreeItemComponent;
import org.seasar.uruma.viewer.GenericLabelProvider;

/**
 * {@link TreeViewer} のレンダリングを行うクラスです。<br />
 * 
 * @author y-komori
 */
public class TreeViewerRenderer extends
        AbstractViewerRenderer<TreeComponent, TreeViewer, Tree> {

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
    protected Class<TreeViewer> getViewerType() {
        return TreeViewer.class;
    }

    /*
     * @see org.seasar.uruma.renderer.impl.AbstractViewerRenderer#getDefaultContentProvider()
     */
    @Override
    protected IContentProvider getDefaultContentProvider() {
        return new TreeNodeContentProvider();
    }
}
