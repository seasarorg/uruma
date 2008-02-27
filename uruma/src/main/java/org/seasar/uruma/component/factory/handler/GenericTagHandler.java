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
package org.seasar.uruma.component.factory.handler;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.StringUtil;
import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.UIElement;
import org.seasar.uruma.component.UIElementContainer;
import org.seasar.uruma.component.factory.UrumaTagHandler;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.NotFoundException;
import org.seasar.uruma.exception.ParseException;
import org.seasar.uruma.renderer.Renderer;
import org.seasar.uruma.renderer.RendererFactrory;
import org.seasar.uruma.util.ClassUtil;
import org.seasar.uruma.util.PathUtil;
import org.xml.sax.Attributes;

/**
 * 汎用のタグハンドラクラスです。<br />
 * 
 * @author y-komori
 */
public class GenericTagHandler extends UrumaTagHandler {
    private static final long serialVersionUID = 4075680211563734762L;

    private Class<? extends UIElement> uiElementClass;

    /**
     * 生成するクラスを指定してインスタンスを構築します。
     * 
     * @param uiElementClass
     *            生成するクラス
     */
    public GenericTagHandler(final Class<? extends UIElement> uiElementClass) {
        this.uiElementClass = uiElementClass;
    }

    /*
     * @see org.seasar.framework.xml.TagHandler#start(org.seasar.framework.xml.TagHandlerContext,
     *      org.xml.sax.Attributes)
     */
    @Override
    public void start(final TagHandlerContext context,
            final Attributes attributes) {
        UIElement uiElement = createUIElement(uiElementClass, context);

        setPath(uiElement, context);

        setBasePath(uiElement, context);

        setLocation(uiElement, context);

        setAttributes(uiElement, attributes);

        if (uiElement instanceof UIComponent) {
            setRenderer((UIComponent) uiElement);
        }

        setParent(uiElement, context);

        if (enableAutoId()) {
            setupAutoId(uiElement);
        }

        if (enableAutoTitle()) {
            setupAutoTitle(uiElement);
        }

        context.push(uiElement);
    }

    /*
     * @see org.seasar.framework.xml.TagHandler#end(org.seasar.framework.xml.TagHandlerContext,
     *      java.lang.String)
     */
    @Override
    public void end(final TagHandlerContext context, final String body) {
        context.pop();
    }

    /**
     * {@link UIElement} オブジェクトを生成します。<br />
     * 
     * @param uiElementClass
     *            {@link UIElement} クラス
     * @param context
     *            {@link TagHandlerContext} オブジェクト。<br />
     *            コンテクストに応じて生成する {@link UIElement} を変更するために使用します。<br />
     *            本クラスでは利用しませんが、サブクラスで必要に応じて利用することができます。<br />
     * @return {@link UIElement} オブジェクト
     */
    protected UIElement createUIElement(
            final Class<? extends UIElement> uiElementClass,
            final TagHandlerContext context) {
        return ClassUtil.<UIElement> newInstance(uiElementClass);
    }

    /**
     * {@link UIElement} へXMLのパスを設定します。<br />
     * 
     * @param uiElement
     *            {@link UIElement} オブジェクト
     * @param context
     *            コンテクスト情報
     */
    protected void setPath(final UIElement uiElement,
            final TagHandlerContext context) {
        uiElement.setPath((String) context
                .getParameter(UrumaTagHandler.PARAM_PATH));
    }

    /**
     * {@link UIElement} へXMLのベースパスを設定します。<br />
     * 
     * @param uiElement
     *            {@link UIElement} オブジェクト
     * @param context
     *            コンテクスト情報
     */
    protected void setBasePath(final UIElement uiElement,
            final TagHandlerContext context) {
        uiElement.setBasePath((String) context
                .getParameter(UrumaTagHandler.PARAM_BASE_PATH));
    }

    /**
     * {@link UIElement} へXML上のロケーション情報を設定します。<br />
     * 
     * @param uiElement
     *            {@link UIElement} オブジェクト
     * @param context
     *            コンテクスト情報
     */
    protected void setLocation(final UIElement uiElement,
            final TagHandlerContext context) {
        uiElement.setLocation(context.getDetailPath());
    }

    /**
     * {@link UIElement} へ属性の値をセットします。<br />
     * 
     * @param uiElement
     *            {@link UIElement} オブジェクト
     * @param attributes
     *            {@link Attributes} オブジェクト
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
     * {@link UIElement} へプロパティを設定します。<br />
     * <p>
     * <code>name</code>に対応したsetterメソッドが存在すればそれを利用して値を設定します。<br />
     * </p>
     * 
     * @param uiElement
     *            {@link UIElement} オブジェクト
     * @param name
     *            プロパティ名
     * @param value
     *            値
     */
    protected void setProperty(final UIElement uiElement, final String name,
            final String value) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(uiElement.getClass());
        if (desc.hasPropertyDesc(name)) {
            PropertyDesc pd = desc.getPropertyDesc(name);
            pd.setValue(uiElement, value);
        } else {
            throw new ParseException(name, uiElement.getClass().getName());
        }
    }

    /**
     * 生成した {@link UIElement} を {@link TagHandlerContext} 内に存在する親へ設定します。<br />
     * 
     * @param uiElement
     *            {@link UIElement} オブジェクト
     * @param context
     *            {@link TagHandlerContext} オブジェクト
     */
    protected void setParent(final UIElement uiElement,
            final TagHandlerContext context) {
        if (!context.isEmpty()) {
            Object parent = context.peek();
            if (UIElementContainer.class.isAssignableFrom(parent.getClass())) {
                UIElementContainer parentComponent = (UIElementContainer) parent;
                parentComponent.addChild(uiElement);
            }
        }
    }

    /**
     * {@link UIComponent} に対応するレンダラをセットします。<br />
     * 
     * @param uiComponent
     *            {@link UIComponent} オブジェクト
     */
    protected void setRenderer(final UIComponent uiComponent) {
        Renderer renderer = RendererFactrory
                .getRenderer(uiComponent.getClass());
        if (renderer != null) {
            uiComponent.setRenderer(renderer);
        } else {
            throw new NotFoundException(UrumaMessageCodes.RENDERER_NOT_FOUND,
                    uiComponent.getClass().getName());
        }
    }

    /*
     * @see org.seasar.uruma.component.factory.UrumaTagHandler#getElementPath()
     */
    @Override
    public String getElementPath() {
        return null;
    }

    protected void setupAutoId(final UIElement element) {
        if (element instanceof UIComponent) {
            UIComponent uiComponent = (UIComponent) element;
            if (StringUtil.isEmpty(uiComponent.getId())) {
                String fileName = PathUtil.getFileName(uiComponent.getPath());
                String id = StringUtil.decapitalize(PathUtil
                        .getBaseName(fileName));
                uiComponent.setId(id);
            }
        }
    }

    /**
     * ID の自動設定を有効にするかどうかを返します。<br />
     * デフォルトでは <code>false</code> を返します。<br />
     * 本機能を有効にする場合、サブクラスでオーバーライドして <code>true</code> を返してください。<br />
     * 
     * @return ID の自動設定を有効にするかどうか
     */
    protected boolean enableAutoId() {
        return false;
    }

    protected void setupAutoTitle(final UIElement element) {
        if (element instanceof UIComponent) {
            UIComponent uiComponent = (UIComponent) element;
            BeanDesc desc = BeanDescFactory.getBeanDesc(uiComponent.getClass());
            if (desc.hasPropertyDesc("title")) {
                PropertyDesc pd = desc.getPropertyDesc("title");

                if (StringUtil.isEmpty((String) pd.getValue(uiComponent))) {
                    String fileName = PathUtil.getFileName(uiComponent
                            .getPath());
                    String title = PathUtil.getBaseName(fileName);
                    pd.setValue(uiComponent, title);
                }
            }
        }
    }

    /**
     * タイトルの自動設定を有効にするかどうかを返します。<br />
     * デフォルトでは <code>false</code> を返します。<br />
     * 本機能を有効にする場合、サブクラスでオーバーライドして <code>true</code> を返してください。<br />
     * 
     * @return タイトルの自動設定を有効にするかどうか
     */
    protected boolean enableAutoTitle() {
        return false;
    }
}
