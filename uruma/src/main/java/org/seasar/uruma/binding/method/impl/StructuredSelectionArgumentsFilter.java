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
import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.seasar.uruma.binding.method.ArgumentsFilter;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.exception.UIllegalArgumentException;

/**
 * {@link IStructuredSelection} に含まれるオブジェクトを引数に展開するための {@link ArgumentsFilter}
 * です。<br />
 * 対象メソッドの引数は0または1個でなくてはなりません。<br />
 * そうでない場合はコンストラクタで {@link UIllegalArgumentException} がスローされます。 対象メソッドの引数がない場合、
 * <code>null</code> を返します。<br />
 * <dl>
 * <dt>対象メソッドの引数が配列の場合</dt>
 * <dd>{@link IStructuredSelection} が保持するオブジェクトの型と配列の型が一致すれば、
 * {@link IStructuredSelection} が保持するオブジェクトを配列に変換します。</dd>
 * </dl>
 * <dl>
 * <dt>対象メソッドの引数が {@link List} インターフェースの場合</dt>
 * <dd>{@link IStructuredSelection} が保持するオブジェクトの型と配列の型が一致すれば、
 * {@link IStructuredSelection} が保持するオブジェクトを配列に変換します。</dd>
 * </dl>
 * 
 * @author y-komori
 */
public class StructuredSelectionArgumentsFilter implements ArgumentsFilter,
        UrumaMessageCodes {
    private Class<?> paramType;

    private Method targetMethod;

    /**
     * {@link StructuredSelectionArgumentsFilter} を構築します。<br />
     * 
     * @param targetMethod
     *            対象メソッド
     * @throws UIllegalArgumentException
     *             対象メソッドの引数が0または1個でない場合
     */
    public StructuredSelectionArgumentsFilter(final Method targetMethod) {
        this.targetMethod = targetMethod;
        setup(targetMethod);
    }

    protected void setup(final Method method) {
        Class<?>[] paramTypes = method.getParameterTypes();
        if (paramTypes.length == 0) {
            this.paramType = null;
        } else if (paramTypes.length == 1) {
            this.paramType = paramTypes[0];
        } else {
            throw new UIllegalArgumentException(ILLEGAL_ARG_NUMBERS, method
                    .getDeclaringClass().getName(), method.getName());
        }
    }

    /*
     * @see org.seasar.uruma.binding.method.ArgumentsFilter#filter(java.lang.Object[])
     */
    public Object[] filter(final Object[] args) {
        // ターゲットが引数なしの場合は引数を無視する
        if (paramType == null) {
            return null;
        }

        // 引数が null の場合は null を渡す
        if (args == null) {
            return new Object[] { null };
        }

        if ((args.length >= 1) && (args[0] != null)) {
            if (args[0] instanceof IStructuredSelection) {
                IStructuredSelection selection = (IStructuredSelection) args[0];

                if (selection.size() == 0) {
                    return new Object[] { null };
                }
                Object firstElement = selection.getFirstElement();
                Class<?> argClazz = firstElement.getClass();

                if (paramType.isArray()) {
                    Class<?> componentType = paramType.getComponentType();
                    if (componentType.isAssignableFrom(argClazz)) {
                        return new Object[] { selection.toArray() };
                    }
                } else if (List.class.isAssignableFrom(paramType)) {

                    return new Object[] { selection.toList() };
                }
                if (paramType.isAssignableFrom(argClazz)) {
                    return new Object[] { firstElement };
                }
            }
        }

        return args;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return targetMethod.toString();
    }
}
