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
package org.seasar.uruma.binding.method;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

import org.seasar.framework.util.MethodUtil;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.UIllegalArgumentException;

/**
 * 引数の型を一種類に限定した {@link MethodBinding} クラスです。<br />
 * 
 * @author y-komori
 */
public class SingleParamTypeMethodBinding extends MethodBinding implements
        UrumaMessageCodes {
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
        super(target, method, null);
        setup();
    }

    /**
     * {@link SingleParamTypeMethodBinding} を構築します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     * @param method
     *            ターゲットメソッド
     * @param callback
     *            コールバックオブジェクト
     * @throws IllegalArgumentException
     *             ターゲットメソッドの引数が2個以上存在する場合
     */
    public SingleParamTypeMethodBinding(final Object target,
            final Method method, final MethodCallback callback) {
        super(target, method, callback);
        setup();
    }

    private void setup() {
        Class<?>[] paramTypes = method.getParameterTypes();
        if (paramTypes.length == 0) {
            paramType = null;
        } else if (paramTypes.length == 1) {
            paramType = paramTypes[0];
        } else {
            throw new UIllegalArgumentException(ILLEGAL_ARG_NUMBERS, method
                    .getDeclaringClass().getName(), method.getName());
        }
    }

    /*
     * @see org.seasar.uruma.binding.method.MethodBinding#invoke(java.lang.Object[])
     */
    @Override
    public Object invoke(final Object[] args) {
        Object[] trueArgs = null;
        if (paramType != null && args != null) {
            if (paramType.isArray()) {
                Class<?> componentType = paramType.getComponentType();
                Object[] params = (Object[]) Array.newInstance(componentType,
                        args.length);
                System.arraycopy(args, 0, params, 0, args.length);
                trueArgs = new Object[] { params };
            } else {
                trueArgs = new Object[] { paramType.cast(args[0]) };
            }
        }

        Object result = MethodUtil.invoke(method, target, trueArgs);
        return callback(trueArgs, result);
    }
}
