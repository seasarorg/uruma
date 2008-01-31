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
package org.seasar.uruma.binding.method.impl;

import java.lang.reflect.Method;

import org.seasar.uruma.binding.method.ArgumentsFilter;

/**
 * ターゲットメソッドの引数よりも数が多い場合に切り捨てを行う {@link ArgumentsFilter} です。<br />
 * 
 * @author bskuroneko
 */
public class OmissionArgumentsFilter implements ArgumentsFilter {

    private int targetParameterLength;

    /**
     * {@link OmissionArgumentsFilter} を構築します。<br />
     * 
     * @param targetMethod
     *            ターゲットメソッド
     */
    public OmissionArgumentsFilter(final Method targetMethod) {
        this.targetParameterLength = targetMethod.getParameterTypes().length;
    }

    /*
     * @see org.seasar.uruma.binding.method.ArgumentsFilter#filter(java.lang.Object[])
     */
    public Object[] filter(final Object[] args) {
        if (args == null) {
            return null;
        }

        if (targetParameterLength >= args.length) {
            return args;
        }

        Object[] adjustArgs = new Object[targetParameterLength];
        System.arraycopy(args, 0, adjustArgs, 0, adjustArgs.length);
        return adjustArgs;
    }
}
