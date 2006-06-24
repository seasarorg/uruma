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
import org.seasar.jface.component.impl.TemplateComponent;
import org.xml.sax.Attributes;

/**
 * template 要素に対するタグハンドラです。
 * 
 * @author y-komori
 * 
 */
public class TemplateTagHandler extends AbstractTagHandler {
    private static final long serialVersionUID = 3205297833758351919L;

    private static final String NAME_ATTR = "name";

    private static final String EXTENDS_ATTR = "extends";

    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        final TemplateComponent template = new TemplateComponent();

        setValue(template, NAME_ATTR, attributes.getValue(NAME_ATTR));
        setValue(template, EXTENDS_ATTR, attributes.getValue(EXTENDS_ATTR));
        setBasePath(template, context);

        context.push(template);
    }

    @Override
    protected String getElementName() {
        return "template";
    }
}
