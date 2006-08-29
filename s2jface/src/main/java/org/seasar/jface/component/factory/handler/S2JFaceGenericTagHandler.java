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
import org.seasar.jface.annotation.xml.Attribute;
import org.seasar.jface.annotation.xml.ComponentMapping;
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
        // コンポーネント情報を保持するアノテーションを取得
        Class<? extends ComponentInfo> componentInfo = getComponentInfoClass();
        ComponentMapping mappingInfo = componentInfo
                .getAnnotation(ComponentMapping.class);

        if (mappingInfo != null) {
            // 要素に対応するコンポーネントを生成
            UIComponent uiComponent = createUIComponent(mappingInfo);
            setBasePath(uiComponent, context);

            // コンポーネント情報クラスから属性名を取得
            Attribute[] attributeInfoList = mappingInfo.attributes();
            for (Attribute attribute : attributeInfoList) {
                String attrName = attribute.value();
                String attrValue = attributes.getValue(attrName);
                if (attrValue != null) {
                    setProperty(uiComponent, attrName, attrValue);
                }
            }

            context.push(uiComponent);
        } else {
            // TODO アノテーションが存在しない場合の例外処理
        }
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
     * <code>ComponentMapping</code> アノテーションにしたがって <code>UIComponent</code>
     * オブジェクトを生成します。<br />
     * 
     * @param componentMapping
     *            <code>ComponentMapping</code> アノテーション
     * @return <code>UIComponent</code> オブジェクト
     */
    protected UIComponent createUIComponent(
            final ComponentMapping componentMapping) {
        Class<? extends UIComponent> componentClass = componentMapping
                .componentClass();
        return ClassUtil.<UIComponent> newInstance(componentClass);
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

    /**
     * <code>UIComponent</code> へプロパティを設定します。<br />
     * <p>
     * <code>name</code>に対応したsetterメソッドが存在すればそれを利用して値を設定します。<br />
     * setterが存在しない場合、<code>addProperty()</code> メソッドによって設定します。
     * </p>
     * 
     * @param uiComponent
     *            <code>UIComponent</code> オブジェクト
     * @param name
     *            プロパティ名
     * @param value
     *            値
     */
    protected void setProperty(final UIComponent uiComponent,
            final String name, final String value) {
        BeanDesc desc = BeanDescFactory.getBeanDesc(uiComponent.getClass());
        PropertyDesc pd = desc.getPropertyDesc(name);
        if ((pd != null) && pd.hasWriteMethod()) {
            pd.setValue(uiComponent, value);
        } else {
            // TODO プロパティにXMLのライン数を持たせる(解釈に失敗した場合のエラー表示のため)
            Property property = new PropertyComponent(name, value);
            uiComponent.addProperty(property);
        }
    }

    @Override
    public String getElementName() {
        Class<? extends ComponentInfo> componentClass = getComponentInfoClass();
        ComponentMapping mapping = componentClass
                .getAnnotation(ComponentMapping.class);
        if (mapping != null) {
            return mapping.element();
        } else {
            return null;
        }
    }
}
