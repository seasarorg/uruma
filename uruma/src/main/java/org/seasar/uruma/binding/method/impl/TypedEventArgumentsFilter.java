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
package org.seasar.uruma.binding.method.impl;

import java.lang.reflect.Method;

import org.eclipse.swt.events.TypedEvent;
import org.eclipse.swt.widgets.Event;
import org.seasar.uruma.binding.method.ArgumentsFilter;
import org.seasar.uruma.util.ClassUtil;

/**
 * 引数を {@link TypedEvent} オブジェクトへ変換する {@link ArgumentsFilter} です。<br />
 * 
 * ターゲットメソッドの引数型が {@link TypedEvent} のサブクラスであった場合、引数オブジェクトをその型にあわせてラップします。<br />
 * 与えられる引数は {@link Event} のサブクラスである必要があります。<br />
 * ターゲットメソッドの引数型が {@link TypedEvent} のサブクラスでない場合や、与えられた引数が {@link Event}
 * のサブクラスではない場合、変換は行いません。<br />
 * 
 * @author bskuroneko
 * @author y-komori
 */
public class TypedEventArgumentsFilter implements ArgumentsFilter {

    private Class<?>[] targetParamTypes;

    /**
     * {@link TypedEventArgumentsFilter} を構築します。<br />
     * 
     * @param targetMethod
     *            対象メソッド
     */
    public TypedEventArgumentsFilter(final Method targetMethod) {
        this.targetParamTypes = targetMethod.getParameterTypes();
    }

    /*
     * @see org.seasar.uruma.binding.method.ArgumentsFilter#filter(java.lang.Object[])
     */
    public Object[] filter(final Object[] args) {
        if (args == null) {
            return null;
        }

        if (targetParamTypes.length != args.length) {
            return args;
        }

        Object[] filteredArgs = new Object[args.length];
        for (int i = 0; i < args.length; i++) {
            Class<?> paramType = targetParamTypes[i];
            if (TypedEvent.class.isAssignableFrom(paramType)
                    && args[i] instanceof Event) {
                // TODO 別タイプのイベントでもインスタンス化できてしまうが、制限するかを検討
                filteredArgs[i] = ClassUtil.newInstance(paramType, args[i]);
            } else {
                filteredArgs[i] = args[i];
            }
        }
        return filteredArgs;
    }
}
