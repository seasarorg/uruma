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
package org.seasar.jface.component.factory.handler;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.UICompositeComponent;
import org.seasar.jface.component.UIElement;
import org.seasar.jface.component.factory.S2JFaceTagHandler;
import org.seasar.jface.exception.NotFoundException;
import org.seasar.jface.exception.ParseException;
import org.seasar.jface.renderer.Renderer;
import org.seasar.jface.renderer.RendererFactrory;
import org.seasar.jface.util.ClassUtil;
import org.xml.sax.Attributes;

public class S2JFaceGenericTagHandler extends S2JFaceTagHandler {
    private static final long serialVersionUID = 4075680211563734762L;

    private Class<? extends UIElement> uiElementClass;

    /**
     * 生成するクラスを指定してインスタンスを構築します。
     * 
     * @param componentClass
     */
    public S2JFaceGenericTagHandler(
            final Class<? extends UIElement> uiElementClass) {
        this.uiElementClass = uiElementClass;
    }

    /*
     * @see org.seasar.framework.xml.TagHandler#start(org.seasar.framework.xml.TagHandlerContext,
     *      org.xml.sax.Attributes)
     */
    @Override
    public void start(TagHandlerContext context, Attributes attributes) {
        UIElement uiElement = createUIElement(uiElementClass);

        setBasePath(uiElement, context);

        setAttributes(uiElement, attributes);

        if (uiElement instanceof UIComponent) {
            setRenderer((UIComponent) uiElement);
        }

        setParent(uiElement, context);

        context.push(uiElement);
    }

    /*
     * @see org.seasar.framework.xml.TagHandler#end(org.seasar.framework.xml.TagHandlerContext,
     *      java.lang.String)
     */
    @Override
    public void end(TagHandlerContext context, String body) {
        context.pop();
    }

    /**
     * <code>UIElement</code> オブジェクトを生成します。<br />
     * 
     * @param uiElementClass
     *            <code>UIElement</code> クラス
     * @return <code>UIElement</code> オブジェクト
     */
    protected UIElement createUIElement(
            final Class<? extends UIElement> uiElementClass) {
        return ClassUtil.<UIElement> newInstance(uiElementClass);
    }

    /**
     * <code>UIElement</code> へXMLのパスを設定します。<br />
     * 
     * @param uiElement
     *            <code>UIElement</code> オブジェクト
     * @param context
     *            コンテクスト情報
     */
    protected void setBasePath(final UIElement uiElement,
            final TagHandlerContext context) {
        uiElement.setBasePath((String) context.getParameter("basePath"));
    }

    /**
     * <code>UIElement</code> へ属性の値をセットします。<br />
     * 
     * @param uiElement
     *            <code>UIElement</code> オブジェクト
     * @param attributes
     *            <code>ComponentAttribute</code> オブジェクト
     */
    protected void setAttributes(final UIElement uiElement,
            final Attributes attributes) {
        int length = attributes.getLength();

        for (int i = 0; i < length; i++) {
            String name = attributes.getQName(i);
            String value = attributes.getValue(i);
            setProperty(uiElement, name, value);
        }
    }

    /**
     * <code>UIElement</code> へプロパティを設定します。<br />
     * <p>
     * <code>name</code>に対応したsetterメソッドが存在すればそれを利用して値を設定します。<br />
     * </p>
     * 
     * @param uiElement
     *            <code>UIElement</code> オブジェクト
     * @param name
     *            プロパティ名
     * @param value
     *            値
     */
    protected void setProperty(final UIElement uiElement, final String name,
            final String value) {
        // TODO UIElementにXMLのライン数を持たせる(解釈に失敗した場合のエラー表示のため)
        BeanDesc desc = BeanDescFactory.getBeanDesc(uiElement.getClass());
        if (desc.hasPropertyDesc(name)) {
            PropertyDesc pd = desc.getPropertyDesc(name);
            if (pd.hasWriteMethod()) {
                pd.setValue(uiElement, value);
            } else {
                throw new ParseException(ParseException.PROPERTY_NOT_FOUND,
                        name, uiElement.getClass().getName());
            }
        } else {
            throw new ParseException(ParseException.PROPERTY_NOT_FOUND, name,
                    uiElement.getClass().getName());
        }
    }

    /**
     * 生成した <code>UIElement</code> を <code>TagHandlerContext</code>
     * 内に存在する親へ設定します。<br />
     * 
     * @param uiElement
     *            <code>UIElement</code> オブジェクト
     * @param context
     *            <code>TagHandlerContext</code> オブジェクト
     */
    protected void setParent(final UIElement uiElement,
            final TagHandlerContext context) {
        if (!context.isEmpty()) {
            Object parent = context.peek();
            if (UICompositeComponent.class.isAssignableFrom(parent.getClass())
                    && UIComponent.class.isAssignableFrom(uiElement.getClass())) {
                UICompositeComponent parentComponent = UICompositeComponent.class
                        .cast(parent);
                UIComponent child = UIComponent.class.cast(uiElement);
                parentComponent.addChild(child);
            }
        }
    }

    /**
     * <code>UIComponent</code> に対応するレンダラをセットします。<br />
     * 
     * @param uiComponent
     *            <code>UIComponent</code> オブジェクト
     */
    protected void setRenderer(final UIComponent uiComponent) {
        Renderer renderer = RendererFactrory
                .getRenderer(uiComponent.getClass());
        if (renderer != null) {
            uiComponent.setRenderer(renderer);
        } else {
            throw new NotFoundException(NotFoundException.RENDERER, uiComponent
                    .getClass().getName());
        }
    }

    @Override
    public String getElementPath() {
        return null;
    }
}
