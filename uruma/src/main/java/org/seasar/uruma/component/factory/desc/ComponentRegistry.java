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
package org.seasar.uruma.component.factory.desc;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.seasar.framework.container.factory.ClassPathResourceResolver;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.exception.SAXRuntimeException;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.OgnlUtil;
import org.seasar.framework.util.SAXParserFactoryUtil;
import org.seasar.framework.xml.SaxHandler;
import org.seasar.framework.xml.SaxHandlerParser;
import org.seasar.uruma.component.factory.desc.handler.UcdTagHandlerRule;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.util.AssertionUtil;
import org.seasar.uruma.util.resource.ResourceHandler;
import org.seasar.uruma.util.resource.ResourceTraverser;
import org.seasar.uruma.util.resource.ResourceTraverserFactory;
import org.seasar.uruma.util.resource.impl.ExtResourceFilter;
import org.xml.sax.SAXException;

/**
 * @author y-komori
 */
public class ComponentRegistry implements ResourceHandler {
    protected ClassPathResourceResolver resolver = new ClassPathResourceResolver();

    private List<UrumaComponentDesc> descs = new ArrayList<UrumaComponentDesc>();

    /**
     * 
     */
    public void registComponents() {
        registComponents(Thread.currentThread().getContextClassLoader());
    }

    /**
     * @param loader
     */
    public void registComponents(final ClassLoader loader) {
        URL root = loader.getResource("");
        URL origin = loader.getResource("components");
        ResourceTraverser traverser = ResourceTraverserFactory
                .getResourceTraverser(origin);
        ExtResourceFilter filter = new ExtResourceFilter("ucd");
        traverser.traverse(root, origin, this, filter);
    }

    /*
     * @see
     * org.seasar.uruma.util.resource.ResourceHandler#handle(java.lang.String,
     * java.lang.String, java.io.InputStream)
     */
    public void handle(final String rootPath, final String path,
            final InputStream is) {
        System.err.println(path + " から読み込みます");
        UrumaComponentDesc desc = load(path);
        registComponent(desc);
        System.err.println(desc);
    }

    /**
     * コンポーネント・ディスクリプタを登録します。<br />
     * 
     * @param desc
     *            {@link UrumaComponentDesc} オブジェクト
     */
    public void registComponent(final UrumaComponentDesc desc) {
        AssertionUtil.assertNotNull("desc", desc);
        descs.add(desc);
    }

    /**
     * 指定されたパスから Uruma コンポーネントディスクリプタを読み込みます。<br />
     * 
     * @param path
     *            コンポーネントディスクリプタファイルのパス
     * @return {@link UrumaComponentDesc} オブジェクト
     */
    public UrumaComponentDesc load(final String path) {
        final SaxHandlerParser parser = createSaxHandlerParser(path);
        final InputStream is = getInputStream(path);
        try {
            UrumaComponentDesc desc = (UrumaComponentDesc) parser.parse(is,
                    path);
            return desc;
        } finally {
            InputStreamUtil.close(is);
        }
    }

    protected SaxHandlerParser createSaxHandlerParser(final String path) {
        System.setProperty("javax.xml.parsers.SAXParserFactory",
                "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
        final SAXParserFactory factory = SAXParserFactoryUtil.newInstance();
        factory.setNamespaceAware(true);

        final SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        InputStream is = getInputStream(UrumaConstants.COMPONENT_DESC_SCHEMA_PATH);
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

        return new SaxHandlerParser(handler, saxParser);
    }

    protected InputStream getInputStream(final String path) {
        final InputStream is = resolver.getInputStream(path);

        if (is == null) {
            throw new ResourceNotFoundRuntimeException(path);
        }
        return is;
    }

    protected SaxHandler createSaxHandler() {
        SaxHandler handler = new SaxHandler(new UcdTagHandlerRule());
        return handler;
    }

    protected void setupComponents() {

    }

    protected Object[] evaluateArgs(final List<String> argExprs) {
        int size = argExprs.size();
        Object[] args = new Object[size];
        for (int i = 0; i < size; i++) {
            Object exp = OgnlUtil.parseExpression(argExprs.get(i));
            args[i] = OgnlUtil.getValue(exp, null);
        }
        return args;
    }
}
