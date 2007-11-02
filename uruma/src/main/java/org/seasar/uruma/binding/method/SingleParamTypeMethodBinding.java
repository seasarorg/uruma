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
package org.seasar.uruma.binding.method;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.seasar.framework.util.MethodUtil;

/**
 * 引数の型を一種類に限定した {@link MethodBinding} クラスです。<br />
 * 
 * @author y-komori
 */
public class SingleParamTypeMethodBinding extends MethodBinding {
    private Class<?> paramType;

    /**
     * {@link SingleParamTypeMethodBinding} を構築します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param method
     *            ターゲットメソッド
     * @throws IllegalArgumentException
     *             ターゲットメソッドの引数が2個以上存在する場合
     */
    public SingleParamTypeMethodBinding(final Object target, final Method method) {
        super(target, method);
        setup();
    }

    private void setup() {
        Class<?>[] paramTypes = method.getParameterTypes();
        if (paramTypes.length == 0) {
            paramType = null;
        } else if (paramTypes.length == 1) {
            paramType = paramTypes[0];
        } else {
            // TODO メッセージをリソース化
            throw new IllegalArgumentException(
                    "targetMethod の引数は 0 個または 1個でなくてはなりません.");
        }
    }

    @Override
    public Object invoke(final Object[] args) {
        if (paramType == null || args == null) {
            return MethodUtil.invoke(method, target, null);
        } else {
            if (paramType.isArray()) {
                Class<?> componentType = paramType.getComponentType();
                Object[] params = (Object[]) Array.newInstance(componentType,
                        args.length);
                System.arraycopy(args, 0, params, 0, args.length);
                return MethodUtil.invoke(method, target,
                        new Object[] { params });
            } else {
                return MethodUtil.invoke(method, target,
                        new Object[] { paramType.cast(args[0]) });
            }
        }
    }
}
