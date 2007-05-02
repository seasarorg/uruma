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
package org.seasar.jface.renderer.impl;

import org.eclipse.jface.viewers.IBaseLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Tree;
import org.seasar.jface.component.impl.TreeComponent;
import org.seasar.jface.exception.RenderException;
import org.seasar.jface.util.S2ContainerUtil;
import org.seasar.jface.viewer.GenericLabelProvider;
import org.seasar.jface.viewer.TreeViewerAdapter;

/**
 * <code>Tree</code> のレンダリングを行うクラスです。<br />
 * 
 * @author y-komori
 */
public class TreeRenderer extends
        AbstractCompositeRenderer<TreeComponent, Tree> {
    private static final String CONTENT_PROVIDER = "ContentProvider";

    private static final String LABEL_PROVIDER = "LabelProvider";

    @Override
    protected void doRenderComposite(TreeComponent treeComponent, Tree tree) {
        TreeViewer viewer = new TreeViewer(tree);
        TreeViewerAdapter viewerAdapter = new TreeViewerAdapter(viewer);
        getContext().putViewerAdapter(tree, viewerAdapter);

        String id = treeComponent.getId();
        setupContentProvider(viewer, id);
        setupLabelProvider(viewer, id);
    }

    private void setupContentProvider(TreeViewer viewer, String id) {
        ITreeContentProvider provider = null;
        if (id != null) {
            Object defined = S2ContainerUtil.getComponentNoException(id
                    + CONTENT_PROVIDER);
            if (defined != null) {
                if (defined instanceof ITreeContentProvider) {
                    provider = (ITreeContentProvider) defined;
                    viewer.setContentProvider(provider);
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            provider, ITreeContentProvider.class.getName());
                }
            } else {
                // TODO ContentProviderが登録されていなかった場合、警告を出力の上、デフォルト実装を組み込む
            }
        }
    }

    private void setupLabelProvider(TreeViewer viewer, String id) {
        // TODO TableRenderer の同メソッドと共通化
        IBaseLabelProvider provider = null;
        if (id != null) {
            Object defined = S2ContainerUtil.getComponentNoException(id
                    + LABEL_PROVIDER);
            if (defined != null) {
                if (defined instanceof ITableLabelProvider
                        || defined instanceof ILabelProvider) {
                    provider = (IBaseLabelProvider) defined;
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            provider, ITableLabelProvider.class.getName());
                }
            }
        }

        if (provider == null) {
            provider = new GenericLabelProvider();
        }

        viewer.setLabelProvider(provider);
    }

    @Override
    protected Class<Tree> getWidgetType() {
        return Tree.class;
    }
}
