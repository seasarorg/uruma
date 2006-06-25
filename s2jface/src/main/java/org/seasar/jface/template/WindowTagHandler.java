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
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.renderer.RendererFactrory;
import org.xml.sax.Attributes;

/**
 * window 要素に対するタグハンドラです。
 * 
 * @author y-komori
 * 
 */
public class WindowTagHandler extends AbstractTagHandler {
    private static final long serialVersionUID = -8514801832940499258L;

    protected static final String BASE_PATH_ATTR = "basePath";

    protected static final String DEFAULT_RENDERER = "window";

    protected static final String STYLE_ATTR = "style";

    protected static final String LAYOUT_ATTR = "layout";

    protected static final String LAYOUT_PARAM_ATTR = "layoutParam";

    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        final WindowComponent window = new WindowComponent();

        final TemplateComponent template = (TemplateComponent) context.peek();
        template.addChild(window);

        setValue(window, ID_ATTR, attributes.getValue(ID_ATTR), true);
        setValue(window, STYLE_ATTR, attributes.getValue(STYLE_ATTR));

        String rendererType = attributes.getValue(TYPE_ATTR);
        if (rendererType == null) {
            rendererType = DEFAULT_RENDERER;
        }
        setValue(window, RENDERER_TYPE_ATTR, rendererType);
        window.setRenderer(RendererFactrory.getRenderer(rendererType));

        setValue(window, LAYOUT_ATTR, attributes.getValue(LAYOUT_ATTR), true);
        setValue(window, LAYOUT_PARAM_ATTR, attributes
                .getValue(LAYOUT_PARAM_ATTR));

        setBasePath(window, context);

        context.push(window);
    }

    @Override
    public void end(TagHandlerContext context, String body) {
        context.pop();
        context.pop();
    }

    @Override
    protected String getElementName() {
        return "window";
    }
}
