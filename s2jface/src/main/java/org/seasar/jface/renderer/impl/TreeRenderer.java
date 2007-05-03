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
 * <p>
 * <code>id + ContentProvider</code> という名称で {@link ITreeContentProvider}
 * の実装クラスがS2Containerへ登録されている場合、 {@link TreeViewer} を利用したレンダリングを行います。<br />
 * このとき、<code>tree</code> 要素の子として記述されている <code>treeItem</code>
 * 要素はすべて無視されます。<br />
 * また、<code>id + LabelProvider</code> という名称で {@link ILabelProvider} の実装クラスが
 * S2Container に登録されていれば、自動的にラベルプロバイダとして設定します。<br />
 * </p>
 * <p>
 * コンテントプロバイダが S2Container へ登録されていない場合には、{@link TreeViewer} によるレンダリングを行いません。<br />
 * </p>
 * 
 * @author y-komori
 */
public class TreeRenderer extends
        AbstractCompositeRenderer<TreeComponent, Tree> {
    private static final String CONTENT_PROVIDER = "ContentProvider";

    private static final String LABEL_PROVIDER = "LabelProvider";

    @Override
    protected void doRenderComposite(TreeComponent treeComponent, Tree tree) {
        String id = treeComponent.getId();

        ITreeContentProvider contentProvider = getContentProvider(id);
        if (contentProvider != null) {
            TreeViewer viewer = new TreeViewer(tree);

            viewer.setContentProvider(contentProvider);
            setupLabelProvider(viewer, id);

            TreeViewerAdapter viewerAdapter = new TreeViewerAdapter(viewer);
            getContext().putViewerAdapter(tree, viewerAdapter);
        }
    }

    private ITreeContentProvider getContentProvider(String id) {
        if (id != null) {
            Object provider = S2ContainerUtil.getComponentNoException(id
                    + CONTENT_PROVIDER);
            if (provider != null) {
                if (provider instanceof ITreeContentProvider) {
                    return (ITreeContentProvider) provider;
                } else {
                    throw new RenderException(RenderException.TYPE_ERROR,
                            provider, ITreeContentProvider.class.getName());
                }
            }
        }
        return null;
    }

    private void setupLabelProvider(TreeViewer viewer, String id) {
        // TODO TableRenderer の同メソッドと共通化
        IBaseLabelProvider provider = null;
        if (id != null) {
            Object component = S2ContainerUtil.getComponentNoException(id
                    + LABEL_PROVIDER);
            if (component != null) {
                if (component instanceof ITableLabelProvider
                        || component instanceof ILabelProvider) {
                    provider = (IBaseLabelProvider) component;
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
