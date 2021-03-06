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
package org.seasar.uruma.component.factory.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.jface.TreeComponent;
import org.seasar.uruma.component.jface.TreeItemComponent;
import org.xml.sax.Attributes;

/**
 * <code>treeItem</code> 要素に対応するタグハンドラです。<br />
 * 
 * @author y-komori
 */
public class TreeItemTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = -260321293575249423L;

    /**
     * {@link TreeItemTagHandler} を構築します。<br />
     */
    public TreeItemTagHandler() {
        super(TreeItemComponent.class);
    }

    /*
     * @see org.seasar.uruma.component.factory.handler.GenericTagHandler#getElementPath()
     */
    @Override
    public String getElementPath() {
        return "treeItem";
    }

    /*
     * @see org.seasar.uruma.component.factory.handler.GenericTagHandler#start(org.seasar.framework.xml.TagHandlerContext,
     *      org.xml.sax.Attributes)
     */
    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        Object parent = context.peek();
        super.start(context, attributes);
        TreeItemComponent treeItem = (TreeItemComponent) context.peek();

        if (parent instanceof TreeComponent) {
            TreeComponent tree = (TreeComponent) parent;
            tree.addTreeItem(treeItem);
        } else if (parent instanceof TreeItemComponent) {
            TreeItemComponent parentTreeItem = (TreeItemComponent) parent;
            parentTreeItem.addTreeItem(treeItem);
            treeItem.setParentTreeItem(parentTreeItem);
        }
    }
}
