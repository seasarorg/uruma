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
import org.seasar.jface.component.Property;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.impl.TemplateComponent;
import org.seasar.jface.component.impl.WindowComponent;
import org.seasar.jface.component.impl.TemplateComponent.ExtendPoint;
import org.seasar.jface.exception.NotFoundException;
import org.seasar.jface.util.PathUtil;

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
            TemplateComponent template = (TemplateComponent) parser.parse(is,
                    path);
            template.setSourcePath(path);
            return extendTemplate(template);
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

    protected TemplateComponent extendTemplate(final TemplateComponent template) {
        String extendsPath = template.getExtends();
        if (extendsPath != null) {
            extendsPath = PathUtil.createPath(template.getBasePath(),
                    extendsPath);
            TemplateComponent parentComponent = build(extendsPath);
            if (parentComponent != null) {
                return doExtend(parentComponent, template);
            }
        }

        return template;
    }

    protected TemplateComponent doExtend(final TemplateComponent parent,
            final TemplateComponent template) {
        WindowComponent window = parent.getWindowComponent();
        window.setId(template.getWindowComponent().getId());

        for (ExtendPoint extendPoint : template.getExtendPoints()) {
            if (extendPoint.getProperty() != null) {
                extendProperty(parent, extendPoint);
            } else {
                extendComponent(parent, template, extendPoint);
            }
        }

        return parent;
    }

    /**
     * <code>extendPoint</code> で与えられた内容にしたがって、コンポーネントの上書きを行います。</br>
     * <p>
     * <code>parent</code> の中から <code>extendPoint</code> の示す <code>id</code>
     * を持つコンポーネントを探し、<code>child</code> が保持する <code>UIComponent</code>
     * のうち同じ <code>id</code> を持つもので置き換えます。</br>
     * <p>
     * <p>
     * このとき、<code>extendPoint#getReplace()</code> の結果が <code>true</code>
     * ならば <code>UIComponent</code> をすべて置き換えます。</br> <code>false</code>
     * ならば、子コンポーネントのみ置き換えます。
     * </p>
     * 
     * @param parent
     *            上書き対象の <code>TemplateComponent</code>。
     * @param child
     *            上書きを行う <code>TemplateComponent</code>。
     * @param extendPoint
     *            プロパティの上書き方法を指定するオブジェクト。
     * @throws NotFoundException
     *             上書き対象のコンポーネントまたはプロパティが見つからなかった場合。
     */
    protected void extendComponent(final TemplateComponent parent,
            final TemplateComponent child, final ExtendPoint extendPoint) {
        UIComponent override = child.find(extendPoint.getId());
        if (override == null) {
            throwNotFoundExceptionByComponent(child, extendPoint);
        }

        UIComponent overrideTarget = parent.find(extendPoint.getId());
        if (overrideTarget == null) {
            throwNotFoundExceptionByExtendComponent(parent, extendPoint);
        }

        if (extendPoint.getReplace()) {
            UIComponent overrideParent = overrideTarget.getParent();
            if (overrideParent != null) {
                overrideParent.replaceChild(override);
            }
        } else {
            overrideTarget.replaceChildren(override);
        }
    }

    /**
     * <code>extendPoint</code> で与えられた内容にしたがって、プロパティの上書きを行います。</br>
     * <p>
     * <code>target</code> の中から <code>extendPoint</code> の
     * <code>getId()</code> メソッド、 <code>getValue()</code>
     * メソッドが表すプロパティを検索し、その値を extendPoint#getValue() の結果に置き換えます。</br>
     * </p>
     * 
     * @param target
     *            上書き対象の <code>TemplateComponent</code>。
     * @param extendPoint
     *            プロパティの上書き方法を指定するオブジェクト。
     * @throws NotFoundException
     *             上書き対象のコンポーネントまたはプロパティが見つからなかった場合。
     */
    protected void extendProperty(final TemplateComponent target,
            final ExtendPoint extendPoint) {
        UIComponent extendTarget = target.find(extendPoint.getId());
        if (extendTarget != null) {
            Property prop = extendTarget.getProperty(extendPoint.getProperty());
            if (prop != null) {
                prop.setValue(extendPoint.getValue());
            } else {
                throwNotFoundExceptionByExtendProperty(target, extendPoint);
            }
        } else {
            throwNotFoundExceptionByExtendComponent(target, extendPoint);
        }
    }

    protected void throwNotFoundExceptionByExtendProperty(
            final TemplateComponent target, final ExtendPoint extendPoint)
            throws NotFoundException {
        throw new NotFoundException(NotFoundException.EXTEND_TARGET_PROPERTY,
                target.getSourcePath() + ":" + extendPoint.getId() + ":"
                        + extendPoint.getProperty());
    }

    protected void throwNotFoundExceptionByExtendComponent(
            final TemplateComponent target, final ExtendPoint extendPoint)
            throws NotFoundException {
        throw new NotFoundException(NotFoundException.EXTEND_TARGET_COMPONENT,
                target.getSourcePath() + ":" + extendPoint.getId());
    }

    protected void throwNotFoundExceptionByComponent(
            final TemplateComponent target, final ExtendPoint extendPoint)
            throws NotFoundException {
        throw new NotFoundException(NotFoundException.UICOMPONENT, target
                .getSourcePath()
                + ":" + extendPoint.getId());
    }
}
