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

import org.seasar.framework.util.StringUtil;
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

    private static final String LABEL_ATTR = "label";

    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        Item item = new ItemComponent();
        String label = attributes.getValue(LABEL_ATTR);
        if (label != null) {
            item.setLabel(label);
        }

        Object parent = context.peek();
        if (parent instanceof ControlComponent) {
            ((ControlComponent) parent).addItem(item);
        } else if (parent instanceof Item) {
            ((Item) parent).addChild(item);
        }
        context.push(item);
    }

    @Override
    public void end(final TagHandlerContext context, final String body) {
        Item item = (Item) context.pop();
        if (!StringUtil.isEmpty(body)) {
            item.setValue(body);
        }
    }

    @Override
    protected String getElementName() {
        return "item";
    }
}
