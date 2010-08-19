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

import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.renderer.impl.testmodel.TestTreeNode;
import org.seasar.uruma.renderer.impl.testmodel.TestTreeNodeFactory;
import org.seasar.uruma.userviewer.TableLabelProvider;
import org.seasar.uruma.userviewer.TreeContentProvider;

/**
 * {@link TreeViewerRenderer} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class TreeViewerRendererGUITest extends AbstractGUITest {

    @ExportValue(id = "tree1")
    public TestTreeNode root = TestTreeNodeFactory.generate(3);

    /**
     * テスト用コンテントプロバイダです。<br />
     * 
     * @author y-komori
     */
    public static class Tree1ContentProvider extends TreeContentProvider<TestTreeNode> {

        /*
         * @see org.seasar.uruma.userviewer.TreeContentProvider#doGetChildren(java.lang.Object)
         */
        @Override
        protected List<TestTreeNode> doGetChildren(final TestTreeNode parentElement) {
            return parentElement.getChildren();
        }

        /*
         * @see org.seasar.uruma.userviewer.TreeContentProvider#doGetParent(java.lang.Object)
         */
        @Override
        protected TestTreeNode doGetParent(final TestTreeNode element) {
            return element.getParent();
        }

        /*
         * @see org.seasar.uruma.userviewer.TreeContentProvider#doHasChildren(java.lang.Object)
         */
        @Override
        protected boolean doHasChildren(final TestTreeNode element) {
            return (element.getChildren().size() > 0);
        }

    }

    /**
     * テスト用ラベルプロバイダです。<br />
     */
    public static class Tree1LabelProvider extends TableLabelProvider<TestTreeNode> {

        /*
         * @see org.seasar.uruma.userviewer.TableLabelProvider#doGetColumnImageKey(java.lang.Object, int)
         */
        @Override
        protected String doGetColumnImageKey(final TestTreeNode element, final int columnIndex) {
            return null;
        }

        /*
         * @see org.seasar.uruma.userviewer.TableLabelProvider#doGetColumnText(java.lang.Object, int)
         */
        @Override
        protected String doGetColumnText(final TestTreeNode element, final int columnIndex) {
            return element.getLabel(columnIndex);
        }
    }
}
