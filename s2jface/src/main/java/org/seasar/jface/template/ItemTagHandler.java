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

    @Override
    public void end(final TagHandlerContext context, final String body) {
        if (item == null) {
            if (context.peek() instanceof ControlComponent) {
                createItem(body);
                ((ControlComponent) context.peek()).addItem(item);
            } else {
                if (body != null && !body.equals("")) {
                    createItem(body);
                    ((Item) context.peek()).addChild(item);
                } else {
                    context.pop();
                }
            }
        }
        item = null;
    }

    /**
     * <code>item</code> に引数で指定した文字列をセットした <code>Item<code> をセットする。 
     * @param value テキスト文字列
     */
    protected void createItem(String value) {
        item = new ItemComponent();
        item.setValue(value);
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
            context.push(item);
            item = null;
        }

        if (label != null) {
            createItem(label);
        }

        if (context.peek() instanceof Item && item != null) {
            ((Item) context.peek()).addChild(item);
        }
        
        if (item != null && context.peek() instanceof ControlComponent) {
            ((ControlComponent) context.peek()).addItem(item);
        }
    }
}
