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
package org.seasar.jface.renderer.info;

import java.lang.reflect.Field;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.FieldUtil;
import org.seasar.jface.component.Inheritance;

/**
 * <code>ComponentInfo</code> クラスから情報を取得するためのクラスです。
 * 
 * @author y-komori
 */
public class ComponentInfoAccessor {
    protected static final String INHERITANCE_SUFFIX = "_INHERITANCE";

    protected static final String GET_DEFAULT_INHERITANCE_METHOD = "getDefaultInheritance";

    /**
     * <code>ComponentInfo</code> クラスから各プロパティのデフォルト inheritance 属性を取得します。</br>
     * <p>
     * デフォルト inheritance 属性は、<code>プロパティ名_INHERITANCE</code> という名称の
     * <code>Inheritance</code> 型定数として宣言されている必要があります。
     * </p>
     * <p>
     * 【例】プロパティ名が text の場合
     * 
     * <pre>
     * public static final Inheritance text_INHERITANCE = Inheritance.NONE;
     * </pre>
     * 
     * </p>
     * <p>
     * 定数が見つからなかった場合は、子孫にのみ適用されるプロパティと見なし、
     * <code>Inheritance.DESCENDANT_ONLY</code> を返します。
     * </p>
     * 
     * @param infoClass
     *            ComponentInfoクラスのクラスオブジェクト
     * @param propertyName
     *            プロパティ名称
     * @return デフォルト inheritance 属性。
     */
    public static Inheritance getInheritance(
            final Class<? extends ComponentInfo> infoClass,
            final String propertyName) {
        BeanDesc benaDesc = BeanDescFactory.getBeanDesc(infoClass);
        String inheritanceFieldName = getInheritanceFieldName(propertyName);
        if (benaDesc.hasField(inheritanceFieldName)) {
            Field field = benaDesc.getField(inheritanceFieldName);
            return (Inheritance) FieldUtil.get(field, null);
        } else {
            return Inheritance.DESCENDANT_ONLY;
        }
    }

    protected static String getInheritanceFieldName(String propertyName) {
        return propertyName + INHERITANCE_SUFFIX;
    }
}
