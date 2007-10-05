/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
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
package org.seasar.uruma.component.factory;

import java.io.File;
import java.io.InputStream;

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
import org.seasar.framework.util.SAXParserFactoryUtil;
import org.seasar.framework.xml.SaxHandler;
import org.seasar.framework.xml.SaxHandlerParser;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.Template;
import org.xml.sax.SAXException;

/**
 * 画面定義 XML ファイルを読み込み、コンポーネントツリーを生成するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class ComponentTreeBuilder {
    /**
     * 画面定義XMLのスキーマファイルパス
     */
    public static final String SCHEMA_PATH = "org/seasar/uruma/component/factory/uruma.xsd";

    protected ClassPathResourceResolver resolver = new ClassPathResourceResolver();

    /**
     * 指定されたパスの画面定義XMLを読み込み、コンポーネントツリーを生成します。<br />
     * 
     * @param path
     *            画面定義XMLのパス
     * @return {@link Template} オブジェクト
     */
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
        final InputStream is = resolver.getInputStream(path);

        if (is == null) {
            throw new ResourceNotFoundRuntimeException(path);
        }
        return is;
    }

    protected SaxHandlerParser createSaxHandlerParser(final String path) {
        final SAXParserFactory factory = SAXParserFactoryUtil.newInstance();
        factory.setNamespaceAware(true);

        final SchemaFactory schemaFactory = SchemaFactory
                .newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        InputStream is = getInputStream(SCHEMA_PATH);
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

        final TagHandlerContext ctx = handler.getTagHandlerContext();
        ctx.addParameter("path", path);
        ctx.addParameter("basePath", (new File(path)).getParent());

        return new SaxHandlerParser(handler, saxParser);
    }

    protected SaxHandler createSaxHandler() {
        SaxHandler handler = new SaxHandler(new UrumaTagHandlerRule());
        return handler;
    }
}
