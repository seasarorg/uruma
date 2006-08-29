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

import org.seasar.framework.xml.TagHandlerContext;
import org.seasar.jface.annotation.xml.Attribute;
import org.seasar.jface.annotation.xml.ComponentClass;
import org.seasar.jface.annotation.xml.ComponentProperties;
import org.seasar.jface.annotation.xml.ElementName;
import org.seasar.jface.component.Property;
import org.seasar.jface.component.UIComponent;
import org.seasar.jface.component.factory.S2JFaceTagHandler;
import org.seasar.jface.component.impl.PropertyComponent;
import org.seasar.jface.component.info.ComponentInfo;
import org.seasar.jface.util.ClassUtil;
import org.xml.sax.Attributes;

public class S2JFaceGenericTagHandler extends S2JFaceTagHandler {
    private static final long serialVersionUID = 4075680211563734762L;

    private Class<? extends ComponentInfo> componentInfo;

    /**
     * <code>ComponentInfo</code> クラスを指定してインスタンスを構築します。
     * 
     * @param componentInfo
     *            <code>ComponentInfo</code> クラスのオブジェクト。
     */
    public S2JFaceGenericTagHandler(Class<? extends ComponentInfo> componentInfo) {
        this.componentInfo = componentInfo;
    }

    /*
     * @see org.seasar.framework.xml.TagHandler#start(org.seasar.framework.xml.TagHandlerContext,
     *      org.xml.sax.Attributes)
     */
    @Override
    public void start(TagHandlerContext context, Attributes attributes) {
        // 要素に対応するコンポーネント情報クラスを取得
        Class<? extends ComponentInfo> componentInfo = getComponentInfoClass();

        // 要素に対応するコンポーネントを生成
        UIComponent uiComponent = createUIComponent(componentInfo);
        setBasePath(uiComponent, context);

        // コンポーネント情報クラスから属性名を取得
        ComponentProperties properties = componentInfo
                .getAnnotation(ComponentProperties.class);
        if (properties != null) {
            Attribute[] attributeInfoList = properties.value();
            if (attributeInfoList != null) {
                for (Attribute attribute : attributeInfoList) {
                    String attrName = attribute.value();
                    String attrValue = attributes.getValue(attrName);
                    if (attrValue != null) {
                        // TODO プロパティにXMLのライン数を持たせる(解釈に失敗した場合のエラー表示のため)
                        Property property = new PropertyComponent(attrName,
                                attrValue);
                        uiComponent.addProperty(property);
                    }
                }
            }
        }

        context.push(uiComponent);
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
     * <code>ComponentInfo</code> の保持する <code>ComponentClass</code>
     * アノテーションにしたがって <code>UIComponent</code> オブジェクトを生成します。<br />
     * 
     * @param componentInfo
     *            <code>ComponentInfo</code> のクラスオブジェクト
     * @return <code>UIComponent</code> オブジェクト
     */
    protected UIComponent createUIComponent(
            final Class<? extends ComponentInfo> componentInfo) {
        ComponentClass componentClassInfo = componentInfo
                .getAnnotation(ComponentClass.class);
        if (componentClassInfo != null) {
            Class<? extends UIComponent> componentClass = componentClassInfo
                    .value();
            if (componentClass != null) {
                return ClassUtil.<UIComponent> newInstance(componentClass);
            } else {
                // TODO 例外処理
                return null;
            }
        } else {
            // TODO 例外処理
            return null;
        }
    }

    /**
     * <code>UIComponent</code> へXMLのパスを設定します。<br />
     * 
     * @param component
     *            <code>UIComponent</code> オブジェクト
     * @param context
     *            コンテクスト情報
     */
    protected void setBasePath(final UIComponent component,
            final TagHandlerContext context) {
        component.setBasePath((String) context.getParameter("basePath"));
    }

    /**
     * コンポーネント情報クラスを取得するメソッドです。<br />
     * <p>
     * 本クラスをサブクラス化する際、コンポーネント情報クラスを固定化したい場合にオーバーライドしてください。<br />
     * </p>
     * 
     * @return コンポーネント情報クラスの <code>Class</code> オブジェクト
     */
    protected Class<? extends ComponentInfo> getComponentInfoClass() {
        return this.componentInfo;
    }

    @Override
    public String getElementName() {
        Class<? extends ComponentInfo> componentClass = getComponentInfoClass();
        ElementName elementName = componentClass
                .getAnnotation(ElementName.class);
        if (elementName != null) {
            return elementName.value();
        } else {
            return null;
        }
    }
}
