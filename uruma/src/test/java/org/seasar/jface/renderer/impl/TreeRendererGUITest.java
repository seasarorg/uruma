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

import java.util.List;

import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Text;
import org.seasar.jface.annotation.EventListener;
import org.seasar.jface.annotation.ExportValue;
import org.seasar.jface.annotation.Form;
import org.seasar.jface.annotation.ImportSelection;

/**
 * @author y-komori
 */
@Form(TreeRendererGUITest.class)
public class TreeRendererGUITest extends AbstractGUITest {
    @ExportValue(id = "tree1")
    TreeNode[] tparent;

    @ImportSelection(id = "tree1")
    List<TreeNode> selected;

    @ImportSelection(id = "tree1")
    TreeNode selected2;

    Text textArea;

    public TreeRendererGUITest() {
        tparent = new TreeNode[2];
        tparent[0] = new TreeNode("親1");
        tparent[1] = new TreeNode("親2");

        TreeNode[] tchild = new TreeNode[2];
        tchild[0] = new TreeNode("子1");
        tchild[1] = new TreeNode("子2");
        tparent[0].setChildren(tchild);

        TreeNode[] tgrandChild = new TreeNode[2];
        tgrandChild[0] = new TreeNode("孫1");
        tgrandChild[1] = new TreeNode("孫2");
        tchild[0].setChildren(tgrandChild);
    }

    @EventListener(id = "tree1")
    public void onTreeSelected() {
        String current = textArea.getText();
        current += "NodeSelected : ";
        for (TreeNode selection : selected) {
            current += selection.getValue() + ", ";
        }
        current += "\n";
        textArea.setText(current);
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
