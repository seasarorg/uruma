/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.component.factory.impl;

import static org.seasar.uruma.core.UrumaConstants.*;

import java.io.InputStream;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.container.factory.ClassPathResourceResolver;
import org.seasar.framework.exception.IORuntimeException;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.exception.SAXRuntimeException;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.ResourceUtil;
import org.seasar.framework.util.SAXParserFactoryUtil;
import org.seasar.framework.util.URLUtil;
import org.seasar.framework.xml.SaxHandler;
import org.seasar.framework.xml.SaxHandlerParser;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.factory.ComponentTreeBuilder;
import org.seasar.uruma.component.factory.UrumaTagHandler;
import org.seasar.uruma.component.factory.UrumaTagHandlerRule;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.util.PathUtil;
import org.xml.sax.SAXException;

/**
 * {@link ComponentTreeBuilder} の実装クラスです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public class ComponentTreeBuilderImpl implements ComponentTreeBuilder {

    protected ClassPathResourceResolver resolver = new ClassPathResourceResolver();

    private UrumaTagHandlerRule rule;

    /*
     * @see org.seasar.uruma.component.factory.ComponentTreeBuilder#build(java.lang.String)
     */
    public Template build(final String path) {
        URL url = ResourceUtil.getResourceNoException(path);
        if (url != null) {
            return build(url);
        } else {
            throw new ResourceNotFoundRuntimeException(path);
        }
    }

    /*
     * @see org.seasar.uruma.component.factory.ComponentTreeBuilder#build(java.net.URL)
     */
    public Template build(final URL url) {
        final SaxHandlerParser parser = createSaxHandlerParser(url);
        final InputStream is = getInputStream(url);
        try {
            Template template = (Template) parser.parse(is, url.getPath());
            return template;
        } finally {
            InputStreamUtil.close(is);
        }
    }

    protected InputStream getInputStream(final URL url) {
        try {
            return URLUtil.openStream(url);
        } catch (IORuntimeException ex) {
            throw new ResourceNotFoundRuntimeException(url.getPath());
        }
    }

    protected InputStream getInputStream(final String path) {
        final InputStream is = resolver.getInputStream(path);

        if (is == null) {
            throw new ResourceNotFoundRuntimeException(path);
        }
        return is;
    }

    protected SaxHandlerParser createSaxHandlerParser(final URL url) {
        System.setProperty(PROP_SAX_PARSER_FACTORY, SAX_PARSER_FACTORY_CLASS);
        final SAXParserFactory factory = SAXParserFactoryUtil.newInstance();
        factory.setNamespaceAware(true);

        final SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        InputStream is = getInputStream(UrumaConstants.SCHEMA_PATH);
        try {
            final Schema schema = schemaFactory.newSchema(new StreamSource(is));
            factory.setSchema(schema);
        } catch (SAXException ex) {
            throw new SAXRuntimeException(ex);
        } finally {
            InputStreamUtil.close(is);
        }

        final SAXParser saxParser = SAXParserFactoryUtil.newSAXParser(factory);
        final SaxHandler handler = createSaxHandler();

        createContext(handler, url);

        return new SaxHandlerParser(handler, saxParser);
    }

    protected SaxHandler createSaxHandler() {
        SaxHandler handler = new SaxHandler(rule.getTagHandlerRule());
        return handler;
    }

    protected void createContext(final SaxHandler handler, final URL url) {
        final TagHandlerContext context = handler.getTagHandlerContext();
        URL parentUrl = URLUtil.create(PathUtil.getParent(url.toString()));
        context.addParameter(UrumaTagHandler.PARAM_URL, url);
        context.addParameter(UrumaTagHandler.PARAM_PARENT_URL, parentUrl);
    }

    /**
     * {@link UrumaTagHandlerRule} を設定します。<br />
     * 
     * @param rule
     *        {@link UrumaTagHandlerRule} オブジェクト
     */
    @Binding(bindingType = BindingType.MUST)
    public void setRule(final UrumaTagHandlerRule rule) {
        this.rule = rule;
    }
}
