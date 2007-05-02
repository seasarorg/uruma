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

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;
import org.seasar.jface.annotation.ExportValue;
import org.seasar.jface.annotation.Form;

/**
 * @author y-komori
 */
@Form(TreeRendererGUITest.class)
public class TreeRendererGUITest extends AbstractGUITest {
    @ExportValue(id = "tree")
    TreeNode[] tparent;

    public TreeRendererGUITest() {
        tparent = new TreeNode[2];
        tparent[0] = new TreeNode("大企業");
        tparent[1] = new TreeNode("中企業");
    }

    public static class TreeNodeLabelProvider implements ILabelProvider {

        public Image getImage(Object element) {
            return null;
        }

        public String getText(Object element) {
            if (element instanceof TreeNode) {
                TreeNode treeNode = (TreeNode) element;
                return treeNode.getValue().toString();
            }
            return null;
        }

        public void addListener(ILabelProviderListener listener) {
        }

        public void dispose() {
        }

        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        public void removeListener(ILabelProviderListener listener) {
        }
    }
}
