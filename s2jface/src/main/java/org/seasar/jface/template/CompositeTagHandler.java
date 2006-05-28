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
import org.seasar.jface.component.impl.CompositeComponent;
import org.seasar.jface.renderer.RendererFactrory;
import org.xml.sax.Attributes;

/**
 * @author y-komori
 * 
 */
public class CompositeTagHandler extends AbstractTagHandler {
    private static final long serialVersionUID = 2132200622510506311L;

    protected static final String LAYOUT_ATTR = "layout";

    protected static final String LAYOUT_PARAM_ATTR = "layoutParam";

    @Override
    public void start(TagHandlerContext context, Attributes attributes) {
        final CompositeComponent parent = (CompositeComponent) context.peek();
        final CompositeComponent composite = new CompositeComponent();
        parent.addChild(composite);

        setValue(composite, ID_ATTR, attributes.getValue(ID_ATTR));
        setValue(composite, LAYOUT_ATTR, attributes.getValue(LAYOUT_ATTR));
        setValue(composite, LAYOUT_PARAM_ATTR, attributes
                .getValue(LAYOUT_PARAM_ATTR));

        String rendererType = attributes.getValue(TYPE_ATTR);
        setValue(composite, RENDERER_TYPE_ATTR, rendererType, true);
        composite.setRenderer(RendererFactrory.getRenderer(rendererType));
        setBasePath(composite, context);

        context.push(composite);
    }

    @Override
    public void end(TagHandlerContext context, String body) {
        context.pop();
    }

    @Override
    protected String getElementName() {
        return "composite";
    }

}
