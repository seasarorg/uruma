package org.seasar.jface.template;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.seasar.framework.container.factory.ClassPathResourceResolver;
import org.seasar.framework.container.factory.ResourceResolver;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.ResourceNotFoundRuntimeException;
import org.seasar.framework.util.SAXParserFactoryUtil;
import org.seasar.framework.xml.SaxHandler;
import org.seasar.framework.xml.SaxHandlerParser;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.impl.TemplateComponent;

/**
 * @author y-komori
 * 
 */
public class TemplateBuilder {
    public static final String PUBLIC_ID_01 = "-//SEASAR//DTD S2JFace 0.1//EN";

    public static final String DTD_PATH_01 = "org/seasar/jface/template/s2jface01.dtd";

    protected ResourceResolver resourceResolver = new ClassPathResourceResolver();

    protected TemplateTagHandlerRule rule = new TemplateTagHandlerRule();

    public TemplateComponent build(final String path) {
        final SaxHandlerParser parser = createSaxHandlerParser(path);
        final InputStream is = getInputStream(path);
        try {
            return (TemplateComponent) parser.parse(is, path);
        } finally {
            InputStreamUtil.close(is);
        }

    }

    protected InputStream getInputStream(final String path) {
        final InputStream is = resourceResolver.getInputStream(path);
        if (is == null) {
            throw new ResourceNotFoundRuntimeException(path);
        }
        return is;
    }

    protected SaxHandlerParser createSaxHandlerParser(final String path) {
        final SAXParserFactory factory = SAXParserFactoryUtil.newInstance();
        factory.setValidating(true);

        final SAXParser saxParser = SAXParserFactoryUtil.newSAXParser(factory);

        final SaxHandler handler = new SaxHandler(rule);
        handler.registerDtdPath(PUBLIC_ID_01, DTD_PATH_01);

        final TagHandlerContext ctx = handler.getTagHandlerContext();
        ctx.addParameter("path", path);
        ctx.addParameter("basePath", (new File(path)).getParent());

        return new SaxHandlerParser(handler, saxParser);
    }
}
