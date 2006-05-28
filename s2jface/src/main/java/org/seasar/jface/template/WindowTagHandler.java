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
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.renderer.RendererFactrory;
import org.xml.sax.Attributes;

/**
 * @author y-komori
 * 
 */
public class WindowTagHandler extends AbstractTagHandler {
    private static final long serialVersionUID = -8514801832940499258L;

    private static final String TITLE_ATTR = "title";

    private static final String IMAGE_ATTR = "image";

    private static final String WIDTH_ATTR = "width";

    private static final String HEIGHT_ATTR = "height";

    private static final String X_ATTR = "x";

    private static final String Y_ATTR = "y";

    private static final String MIN_BUTTON_ATTR = "minButton";

    private static final String MAX_BUTTON_ATTR = "maxButton";

    private static final String CLOSE_BUTTON_ATTR = "closeButton";

    private static final String RESIZABLE_ATTR = "resizable";

    private static final String SAVE_STATE_ATTR = "saveState";

    protected static final String LAYOUT_ATTR = "layout";

    protected static final String LAYOUT_PARAM_ATTR = "layoutParam";

    protected static final String BASE_PATH_ATTR = "basePath";

    protected static final String DEFAULT_RENDERER = "window";

    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        final WindowComponent window = new WindowComponent();

        final TemplateComponent template = (TemplateComponent) context.peek();
        template.addChild(window);

        setValue(window, ID_ATTR, attributes.getValue(ID_ATTR), true);
        setValue(window, TITLE_ATTR, attributes.getValue(TITLE_ATTR));

        String rendererType = attributes.getValue(TYPE_ATTR);
        if (rendererType == null) {
            rendererType = DEFAULT_RENDERER;
        }
        setValue(window, RENDERER_TYPE_ATTR, rendererType);
        window.setRenderer(RendererFactrory.getRenderer(rendererType));

        setValue(window, IMAGE_ATTR, attributes.getValue(IMAGE_ATTR));
        setValue(window, WIDTH_ATTR, attributes.getValue(WIDTH_ATTR));
        setValue(window, HEIGHT_ATTR, attributes.getValue(HEIGHT_ATTR));
        setValue(window, X_ATTR, attributes.getValue(X_ATTR));
        setValue(window, Y_ATTR, attributes.getValue(Y_ATTR));
        setValue(window, MIN_BUTTON_ATTR, attributes.getValue(MIN_BUTTON_ATTR));
        setValue(window, MAX_BUTTON_ATTR, attributes.getValue(MAX_BUTTON_ATTR));
        setValue(window, CLOSE_BUTTON_ATTR, attributes
                .getValue(CLOSE_BUTTON_ATTR));
        setValue(window, RESIZABLE_ATTR, attributes.getValue(RESIZABLE_ATTR));
        setValue(window, SAVE_STATE_ATTR, attributes.getValue(SAVE_STATE_ATTR));

        CompositeComponent rootComposite = new CompositeComponent();
        setValue(rootComposite, ID_ATTR, "rootComposite");
        setValue(rootComposite, LAYOUT_ATTR, attributes.getValue(LAYOUT_ATTR));
        setValue(rootComposite, LAYOUT_PARAM_ATTR, attributes
                .getValue(LAYOUT_PARAM_ATTR));
        setValue(rootComposite, RENDERER_TYPE_ATTR, "box");
        rootComposite.setRenderer(RendererFactrory
                .getRenderer(rootComposite.getRendererType()));
        window.addChild(rootComposite);
        setBasePath(window, context);

        context.push(window);
        context.push(rootComposite);
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
