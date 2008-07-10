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
package org.seasar.uruma.component.factory.desc.impl;

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

import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.container.factory.ClassPathResourceResolver;
import org.seasar.framework.exception.ResourceNotFoundRuntimeException;
import org.seasar.framework.exception.SAXRuntimeException;
import org.seasar.framework.util.InputStreamUtil;
import org.seasar.framework.util.OgnlUtil;
import org.seasar.framework.util.SAXParserFactoryUtil;
import org.seasar.framework.xml.SaxHandler;
import org.seasar.framework.xml.SaxHandlerParser;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.factory.UrumaTagHandler;
import org.seasar.uruma.component.factory.UrumaTagHandlerRule;
import org.seasar.uruma.component.factory.desc.ComponentRegistry;
import org.seasar.uruma.component.factory.desc.UrumaComponentDesc;
import org.seasar.uruma.component.factory.desc.handler.UcdTagHandlerRule;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.log.UrumaLogger;
import org.seasar.uruma.renderer.Renderer;
import org.seasar.uruma.renderer.RendererFactory;
import org.seasar.uruma.util.ClassUtil;
import org.seasar.uruma.util.resource.ResourceHandler;
import org.seasar.uruma.util.resource.ResourceTraverser;
import org.seasar.uruma.util.resource.ResourceTraverserFactory;
import org.seasar.uruma.util.resource.impl.ExtResourceFilter;
import org.xml.sax.SAXException;

/**
 * {@link ComponentRegistry} の実装クラスです。<br />
 * 
 * @author y-komori
 */
public class ComponentRegistryImpl implements ComponentRegistry,
        ResourceHandler, UrumaMessageCodes, UrumaConstants {
    private static final String SEARCH_ROOT_PATH = "components";

    private static final String DESC_EXT = "ucd";

    private static final UrumaLogger logger = UrumaLogger
            .getLogger(ComponentRegistryImpl.class);

    protected ClassPathResourceResolver resolver = new ClassPathResourceResolver();

    private List<UrumaComponentDesc> descs = new ArrayList<UrumaComponentDesc>();

    private UrumaTagHandlerRule rule;

    /*
     * @see
     * org.seasar.uruma.component.factory.desc.ComponentRegistry#registComponents
     * ()
     */
    public void registComponents() {
        registComponents(Thread.currentThread().getContextClassLoader());
    }

    /*
     * @see
     * org.seasar.uruma.component.factory.desc.ComponentRegistry#registComponents
     * (java.lang.ClassLoader)
     */
    public void registComponents(final ClassLoader loader) {
        URL root = loader.getResource(NULL_STRING);
        URL origin = loader.getResource(SEARCH_ROOT_PATH);
        ResourceTraverser traverser = ResourceTraverserFactory
                .getResourceTraverser(origin);
        ExtResourceFilter filter = new ExtResourceFilter(DESC_EXT);
        traverser.traverse(root, origin, this, filter);

        for (UrumaComponentDesc desc : descs) {
            try {
                setupTagHandler(desc, loader);
                setupRenderer(desc, loader);
                logger.log(GUI_COMPONENT_REGISTERED, desc.getTagName());
            } catch (Exception ex) {
                logger.log(GUI_COMPONENT_REGISTER_FAILED, ex, ex.getMessage());
            }
        }
    }

    /*
     * @see
     * org.seasar.uruma.util.resource.ResourceHandler#handle(java.lang.String,
     * java.lang.String, java.io.InputStream)
     */
    public void handle(final String rootPath, final String path,
            final InputStream is) {
        logger.log(GUI_COMPONENT_LOADING, path);
        UrumaComponentDesc desc = load(is, path);
        descs.add(desc);
    }

    /**
     * {@link UrumaTagHandler} を生成して登録します。<br />
     * 
     * @param desc
     *            {@link UrumaComponentDesc}
     * @param loader
     *            読み込み先クラスローダ
     * @throws ClassNotFoundException
     *             クラスが見つからない場合
     */
    protected void setupTagHandler(final UrumaComponentDesc desc,
            final ClassLoader loader) throws ClassNotFoundException {
        String elementName = desc.getTagName();
        String className = desc.getTagHandlerClass();
        Class<?> clazz = Class.forName(className, false, loader);
        ClassUtil.checkSubclass(clazz, UrumaTagHandler.class);
        Object[] args = evaluateArgs(desc.getTagHandlerArgs());
        UrumaTagHandler tagHandler = (UrumaTagHandler) ClassUtil.newInstance(
                clazz, args);
        rule.addTagHandler(elementName, tagHandler);
    }

    /**
     * {@link Renderer} を生成して登録します。<br />
     * 
     * @param desc
     *            {@link UrumaComponentDesc}
     * @param loader
     *            読み込み先クラスローダ
     * @throws ClassNotFoundException
     *             クラスが見つからない場合
     */
    protected void setupRenderer(final UrumaComponentDesc desc,
            final ClassLoader loader) throws ClassNotFoundException {
        String className = desc.getRendererClass();
        Class<?> clazz = Class.forName(className, true, loader);
        ClassUtil.checkSubclass(clazz, Renderer.class);

        String componentClassName = desc.getComponentClass();
        Class<?> componentClass = Class.forName(componentClassName, true,
                loader);
        ClassUtil.checkSubclass(componentClass, UIElement.class);

        Object[] args = evaluateArgs(desc.getRendererArgs());
        Renderer renderer = (Renderer) ClassUtil.newInstance(clazz, args);

        RendererFactory.addRenderer(ClassUtil
                .<Class<? extends UIComponent>> cast(componentClass), renderer);
    }

    protected UrumaComponentDesc load(final InputStream is, final String path) {
        final SaxHandlerParser parser = createSaxHandlerParser();
        try {
            UrumaComponentDesc desc = (UrumaComponentDesc) parser.parse(is,
                    path);
            return desc;
        } finally {
            InputStreamUtil.close(is);
        }
    }

    protected UrumaComponentDesc load(final String path) {
        InputStream is = getInputStream(path);
        return load(is, path);
    }

    protected SaxHandlerParser createSaxHandlerParser() {
        System.setProperty(PROP_SAX_PARSER_FACTORY, SAX_PARSER_FACTORY_CLASS);
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

    /**
     * OGNL 式を評価し、その結果をオブジェクトに変換します。<br />
     * 
     * @param argExprs
     *            OGNL 式のリスト
     * @return 評価結果のオブジェクト配列
     */
    protected Object[] evaluateArgs(final List<String> argExprs) {
        int size = argExprs.size();
        Object[] args = new Object[size];
        for (int i = 0; i < size; i++) {
            Object exp = OgnlUtil.parseExpression(argExprs.get(i));
            args[i] = OgnlUtil.getValue(exp, null);
        }
        return args;
    }

    /**
     * {@link UrumaTagHandlerRule} を設定します。<br />
     * 
     * @param rule
     *            {@link UrumaTagHandlerRule} オブジェクト
     */
    @Binding(bindingType = BindingType.MUST)
    public void setRule(final UrumaTagHandlerRule rule) {
        this.rule = rule;
    }
}
