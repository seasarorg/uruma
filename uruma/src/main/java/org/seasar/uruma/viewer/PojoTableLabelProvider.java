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
package org.seasar.uruma.viewer;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.seasar.framework.beans.BeanDesc;
import org.seasar.framework.beans.PropertyDesc;
import org.seasar.framework.beans.factory.BeanDescFactory;
import org.seasar.framework.util.MethodUtil;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.util.AssertionUtil;

/**
 * POJO をテーブルのラベルプロバイダとして利用するためのアダプタクラスです。<br />
 * <p>
 * <dl>
 * <dt>ラベルの取得</dt>
 * <dd> 以下のようなメソッドをPOJO側に必要なカラムの分だけ用意します。<br />
 * <code>public String get<i>&lt;キャピタライズされたカラムID&gt;</i>Text(Object
 * model);</code>
 * <br />
 * 引数が {@link Object} 型以外の場合、呼び出し時にその方へのキャストを試みます。 </dd>
 * 
 * <br />
 * 
 * <dt>カラムイメージの取得</dt>
 * <dd> 以下のようなメソッドをPOJO側に必要なカラムの分だけ用意します。<br />
 * <code>public String get<i>&lt;キャピタライズされたカラムID&gt;</i>Image(Object
 * model);</code><br />
 * 引数が {@link Object} 型以外の場合、呼び出し時にその方へのキャストを試みます。 </dd>
 * </dl>
 * 
 * @author y-komori
 */
public class PojoTableLabelProvider extends GenericTableLabelProvider implements
        PojoLabelProvider {
    private Object pojo;

    private static final String GET_PREFIX = "get";

    private static final String TEXT_SUFFIX = "Text";

    private static final String IMAGE_SUFFIX = "Image";

    private Map<Integer, Method> textMethodCache = new HashMap<Integer, Method>();

    private Map<Integer, Method> imageMethodCache = new HashMap<Integer, Method>();

    /*
     * @see org.seasar.uruma.viewer.GenericTableLabelProvider#getColumnText(java.lang.Object,
     *      int)
     */
    @Override
    public String getColumnText(final Object element, final int columnIndex) {
        Method method = getTextMethod(columnIndex);
        if (method != null) {
            Class<?> paramType = method.getParameterTypes()[0];
            if (paramType.isAssignableFrom(element.getClass())) {
                String text = (String) MethodUtil.invoke(method, pojo,
                        new Object[] { paramType.cast(element) });
                if (text == null) {
                    text = UrumaConstants.NULL_STRING;
                }

                return text;
            }
        }
        return super.getColumnText(element, columnIndex);
    }

    protected Method getTextMethod(final int columnIndex) {
        Method textMethod = textMethodCache.get(columnIndex);
        if (textMethod == null) {
            PropertyDesc pd = columnMap.get(columnIndex);
            if (pd != null) {
                String columnName = pd.getPropertyName();
                String methodName = GET_PREFIX
                        + StringUtil.capitalize(columnName) + TEXT_SUFFIX;
                BeanDesc desc = BeanDescFactory.getBeanDesc(pojo.getClass());
                if (desc.hasMethod(methodName)) {
                    Method[] methods = desc.getMethods(methodName);
                    for (int i = 0; i < methods.length; i++) {
                        Method method = methods[i];
                        if ((method.getParameterTypes().length == 1)
                                && (method.getReturnType() == String.class)) {
                            textMethod = method;
                            textMethodCache.put(columnIndex, textMethod);
                        }
                    }
                }
            }
        }

        return textMethod;
    }

    /*
     * @see org.seasar.uruma.viewer.PojoLabelProvider#setPojo(java.lang.Object)
     */
    public void setPojo(final Object pojo) {
        AssertionUtil.assertNotNull("pojo", pojo);
        this.pojo = pojo;
    }
}
