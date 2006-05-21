package org.seasar.jface.template;

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.impl.TemplateComponent;
import org.xml.sax.Attributes;

/**
 * @author y-komori
 * 
 */
public class TemplateTagHandler extends AbstractTagHandler {
    private static final long serialVersionUID = 3205297833758351919L;

    private static final String NAME_ATTR = "name";

    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        final TemplateComponent template = new TemplateComponent();

        setValue(template, NAME_ATTR, attributes.getValue(NAME_ATTR));
        setBasePath(template, context);

        context.push(template);
    }

    @Override
    protected String getElementName() {
        return "template";
    }
}
