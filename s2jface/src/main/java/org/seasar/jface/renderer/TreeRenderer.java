/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.renderer;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.info.ComponentInfo;
import org.seasar.jface.renderer.info.TreeInfo;

/**
 * @author dkameya
 */
public class TreeRenderer extends AbstractControlRenderer<Tree> {

    @Override
    protected int getStyle(final ControlComponent controlComponent) {
        int style = super.getStyle(controlComponent);
        style = (style ==SWT.NONE) ? SWT.BORDER : style;
        return style;
    }

    @Override
    protected void doRender(final Tree tree, final ControlComponent controlComponent) {
        addTree(tree, controlComponent);
    }

    @Override
    protected Class<Tree> getControlType() {
        return Tree.class;
    }

    public String getRendererName() {
        return "tree";
    }
    
    protected void addTree(final Tree tree, final ControlComponent controlComponent) {
        for (Item item : controlComponent.getItemList()) {
            if (item.getValue().length() != 0) {
                TreeItem parent = new TreeItem(tree, SWT.NONE);
                parent.setText(item.getValue());
                if (item.hasChildren()) {
                    display(item.getChildren(), parent);
                }
            }
        }
    }
    
    protected void display(List<Item> items, TreeItem parent) {
        for (Item item : items) {
            TreeItem self = new TreeItem(parent, SWT.NONE);
            self.setText(item.getValue());
            if (item.hasChildren()) {
                display(item.getChildren(), self);
            }
        }
    }

    public Class<? extends ComponentInfo> getComponentInfo() {
        return TreeInfo.class;
    }
    
    
}
