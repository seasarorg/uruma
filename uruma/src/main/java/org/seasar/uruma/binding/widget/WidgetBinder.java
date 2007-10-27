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
package org.seasar.uruma.binding.widget;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * 任意のオブジェクトに対してウィジットバインディングを実行するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class WidgetBinder {
    /**
     * 指定されたオブジェクトに対して、ウィジットバインディングを行います。<br />
     * <p>
     * <code>target</code> で指定されたオブジェクトに対して、<code>context</code> で指定された
     * {@link PartContext} に登録されているオブジェクトをバインドします。<br />
     * 具体的には、<code>target</code> で定義されるフィールドに対し、そのフィールド名と名前が一致する
     * {@link WidgetHandle} を {@link PartContext} から取得します。取得できた場合、
     * {@link WidgetHandle} が内包するオブジェクトの型がフィールドに代入可能であれば そのフィールドにセットします。<br />
     * </p>
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param context
     *            {@link PartContext} オブジェクト
     */
    public static void bindWidgets(final Object target,
            final PartContext context) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());

        int pdSize = beanDesc.getPropertyDescSize();
        for (int i = 0; i < pdSize; i++) {
            PropertyDesc pd = beanDesc.getPropertyDesc(i);
            Class<?> propertyType = pd.getPropertyType();
            String propertyName = pd.getPropertyName();
            WidgetHandle handle = context.getWidgetHandle(propertyName);
            if (handle != null) {
                if (propertyType.isAssignableFrom(handle.getWidgetClass())) {
                    pd.setValue(target, handle.getWidget());
                }
            }
        }
    }
}
