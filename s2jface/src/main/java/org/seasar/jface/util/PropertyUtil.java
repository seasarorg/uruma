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
package org.seasar.jface.util;

import java.lang.reflect.Field;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.BooleanConversionUtil;
import org.seasar.framework.util.FieldUtil;
import org.seasar.framework.util.IntegerConversionUtil;

/**
 * @author y-komori
 * 
 */
public class PropertyUtil {
    /**
     * 指定されたオブジェクトのプロパティに値をセットします。<br>
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param propertyName
     *            プロパティ名
     * @param value
     *            セットする値
     * @return 成功した場合、<code>true</code>。失敗した場合、<code>false</code>。
     */
    public static boolean setProperty(final Object target,
            final String propertyName, final Object value) {
        PropertyDesc pd = getPropertyDesc(target, propertyName);
        if (pd != null) {
            pd.setValue(target, value);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 指定されたオブジェクトのフィールドに値をセットします。<br>
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param fieldName
     *            フィールド名
     * @param value
     *            セットする値
     * @return 成功した場合、<code>true</code>。失敗した場合、<code>false</code>。
     */
    public static boolean setField(final Object target, final String fieldName,
            final Object value) {
        Field field = getField(target, fieldName);
        if (field != null) {
            Class fieldType = field.getType();

            Object setValue = value;
            // TODO BooleanConversionUtil 修正?
            if (fieldType == Boolean.TYPE || fieldType == Boolean.class) {
                setValue = BooleanConversionUtil.toBoolean(value);
            } else if (fieldType == Integer.TYPE || fieldType == Integer.class) {
                setValue = IntegerConversionUtil.toInteger(value);
            }
            FieldUtil.set(field, target, setValue);

            return true;
        } else {
            return false;
        }
    }

    protected static PropertyDesc getPropertyDesc(final Object target,
            final String property) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());
        if (beanDesc.hasPropertyDesc(property)) {
            return beanDesc.getPropertyDesc(property);
        } else {
            return null;
        }
    }

    protected static Field getField(final Object target, final String field) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());
        if (beanDesc.hasField(field)) {
            return beanDesc.getField(field);
        } else {
            return null;
        }
    }

    /**
     * 指定されたオブジェクトのプロパティから値を取得します。<br>
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param propertyName
     *            プロパティ名
     * @return プロパティの値
     * @throws org.seasar.framework.beans.PropertyNotFoundRuntimeException
     *             指定されたプロパティが見つからなかった場合
     */
    public static Object getProperty(Object target, String propertyName) {
        BeanDesc beanDesc = BeanDescFactory.getBeanDesc(target.getClass());
        PropertyDesc propDesc = beanDesc.getPropertyDesc(propertyName);
        return propDesc.getValue(target);
    }

}
