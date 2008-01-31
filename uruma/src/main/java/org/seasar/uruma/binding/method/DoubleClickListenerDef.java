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

import java.lang.reflect.Method;

import org.seasar.uruma.annotation.DoubleClickListener;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link DoubleClickListener} アノテートされたメソッドを表すクラスです。<br />
 * 
 * @author y-komori
 */
public class DoubleClickListenerDef {
    private Method targetMethod;

    private DoubleClickListener listener;

    /**
     * {@link DoubleClickListenerDef} を構築します。<br />
     * 
     * @param target
     *            ターゲットメソッド
     * @param listener
     *            {@link DoubleClickListener} アノテーション
     */
    public DoubleClickListenerDef(final Method target,
            final DoubleClickListener listener) {
        AssertionUtil.assertNotNull("target", target);
        AssertionUtil.assertNotNull("listener", listener);
        this.targetMethod = target;
        this.listener = listener;
    }

    /**
     * ターゲットの {@link Method} オブジェクトを取得します。<br />
     * 
     * @return ターゲットの {@link Method} オブジェクト
     */
    public Method getTargetMethod() {
        return this.targetMethod;
    }

    /**
     * ダブルクリックを検知するコンポーネントの ID を返します。<br />
     * 
     * @return コンポーネントの ID
     */
    public String[] getId() {
        return this.listener.id();
    }
}
