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
package org.seasar.jface.component.factory;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.seasar.framework.container.factory.ClassPathResourceResolver;
import org.seasar.framework.container.factory.ResourceResolver;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.SAXParserFactoryUtil;
import org.seasar.framework.xml.SaxHandler;
import org.seasar.framework.xml.SaxHandlerParser;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.impl.Template;

/**
 * @author y-komori
 * 
 */
public class ComponentTreeBuilder {
    public static final String PUBLIC_ID_02 = "-//SEASAR//DTD S2JFace 0.2//EN";

    public static final String DTD_PATH_02 = "org/seasar/jface/component/factory/s2jface02.dtd";

    protected ResourceResolver resourceResolver = new ClassPathResourceResolver();

    public Template build(final String path) {
        final SaxHandlerParser parser = createSaxHandlerParser(path);
        final InputStream is = getInputStream(path);
        try {
            Template template = (Template) parser.parse(is, path);
            return template;
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

        final SaxHandler handler = createSaxHandler();

        final TagHandlerContext ctx = handler.getTagHandlerContext();
        ctx.addParameter("path", path);
        ctx.addParameter("basePath", (new File(path)).getParent());

        return new SaxHandlerParser(handler, saxParser);
    }

    protected SaxHandler createSaxHandler() {
        SaxHandler handler = new SaxHandler(new S2JFaceTagHandlerRule());
        handler.registerDtdPath(PUBLIC_ID_02, DTD_PATH_02);
        return handler;
    }
}
