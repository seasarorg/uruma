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
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.exception.ParseException;
import org.xml.sax.Attributes;

/**
 * layoutData 要素に対するタグハンドラです。
 * 
 * @author y-komori
 * 
 */
public class LayoutDataTagHandler extends AbstractTagHandler {
    private static final long serialVersionUID = -4353026434864374392L;

    protected static final String NAME_ATTR = "name";

    protected static final String VALUE_ATTR = "value";

    @Override
    public void start(TagHandlerContext context, Attributes attributes) {
        ControlComponent parent = (ControlComponent) context.peek();

        String name = attributes.getValue(NAME_ATTR);
        if (name == null) {
            throw new ParseException("EJFC0100", getElementName(), NAME_ATTR);
        }

        String value = attributes.getValue(VALUE_ATTR);
        if (value == null) {
            throw new ParseException("EJFC0100", getElementName(), VALUE_ATTR);
        }

        parent.addLayoutData(name, value);
    }

    @Override
    protected String getElementName() {
        return "layoutData";
    }
}
