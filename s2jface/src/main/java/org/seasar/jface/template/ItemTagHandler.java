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
package org.seasar.jface.template;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.Item;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.component.impl.ItemComponent;
import org.xml.sax.Attributes;

/**
 * item 要素に対するタグハンドラです。
 * 
 * @author dkameya
 */
public class ItemTagHandler extends AbstractTagHandler {
    private static final long serialVersionUID = -7973949046037222249L;

    private Item item = null;

    private Item parentItem = null;

    @Override
    public void end(final TagHandlerContext context, final String body) {
        ControlComponent parent = (ControlComponent) context.peek();
        if (item == null) {
            if (parentItem == null) {
                item = new ItemComponent();
                item.setValue(body);
                parent.addItem(item);
            } else {
                parentItem = null;
            }
        }
        item = null;
    }

    @Override
    protected String getElementName() {
        return "item";
    }

    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        String label = attributes.getValue("label");

        if (item != null) {
            parentItem = item;
        }

        if (label != null) {
            item = new ItemComponent();
            item.setValue(label);
        }
        if (parentItem != null) {
            parentItem.addChild(item);
        }
        if (item != null && parentItem == null) {
            ((ControlComponent) context.peek()).addItem(item);
        }
    }
}
