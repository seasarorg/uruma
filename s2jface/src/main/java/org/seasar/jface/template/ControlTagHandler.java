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
import org.seasar.jface.component.impl.AbstractCompositeComponent;
import org.seasar.jface.component.impl.ControlComponent;
import org.seasar.jface.renderer.RendererFactrory;
import org.xml.sax.Attributes;

/**
 * @author y-komori
 *
 */
public class ControlTagHandler extends AbstractTagHandler {
    private static final long serialVersionUID = 1627288649056320123L;

    protected static final String STYLE_ATTR = "style";
    protected static final String ACTION_ATTR = "action";

    @Override
    public void start(TagHandlerContext context, Attributes attributes) {
        AbstractCompositeComponent parent = (AbstractCompositeComponent) context
                .peek();
        ControlComponent control = new ControlComponent();
        parent.addChild(control);

        setValue(control, ID_ATTR, attributes.getValue(ID_ATTR));
        setValue(control, STYLE_ATTR, attributes.getValue(STYLE_ATTR));
        setValue(control, ACTION_ATTR, attributes.getValue(ACTION_ATTR));

        String rendererType = attributes.getValue(TYPE_ATTR);
        setValue(control, RENDERER_TYPE_ATTR, rendererType, true);
        control.setRenderer(RendererFactrory.getRenderer(rendererType));
        setBasePath(control, context);
        
        context.push(control);
    }

    @Override
    public void end(TagHandlerContext context, String body) {
        context.pop();
    }

    @Override
    protected String getElementName() {
        return "control";
    }
}
