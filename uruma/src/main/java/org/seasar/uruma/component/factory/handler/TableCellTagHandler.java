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
package org.seasar.uruma.component.factory.handler;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.jface.TableCellComponent;
import org.seasar.uruma.component.jface.TableItemComponent;

/**
 * <code>tableCell</code> タグに対するタグハンドラです。<br />
 * 
 * @author bskuroneko
 */
public class TableCellTagHandler extends GenericTagHandler {

    private static final long serialVersionUID = 3939806738083124492L;

    /**
     * {@link TableCellTagHandler} を構築します。<br />
     */
    public TableCellTagHandler() {
        super(TableCellComponent.class);
    }

    @Override
    public String getElementPath() {
        return "tableCell";
    }

    @Override
    protected void setParent(final UIElement uiElement,
            final TagHandlerContext context) {
        final TableItemComponent parent = (TableItemComponent) context.peek();
        parent.addTableCell((TableCellComponent) uiElement);
    }
}
