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
import org.seasar.uruma.component.jface.TreeColumnComponent;
import org.seasar.uruma.component.jface.TreeComponent;
import org.xml.sax.Attributes;

/**
 * {@code treeColumn} タグに対するタグハンドラです。<br />
 * 
 * @author y-komori
 * @version $Revision$ $Date$
 */
public class TreeColumnTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = 4275363508707407832L;

    /**
     * {@link TreeColumnTagHandler} を構築します。<br />
     */
    public TreeColumnTagHandler() {
        super(TreeColumnComponent.class);
    }

    /*
     * @see org.seasar.uruma.component.factory.handler.GenericTagHandler#start(org.seasar.framework.xml.TagHandlerContext,
     *      org.xml.sax.Attributes)
     */
    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        super.start(context, attributes);

        TreeColumnComponent column = (TreeColumnComponent) context.peek();
        TreeComponent tree = (TreeComponent) context.peek(1);

        int columnNo = tree.columnCount;
        column.columnNo = columnNo;
        tree.columnCount = ++columnNo;
    }
}
