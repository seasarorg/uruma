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

import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link EventListener} アノテートされたメソッドを表すクラスです。<br />
 * 
 * @author y-komori
 */
public class EventListenerDef {
    private Method targetMethod;

    private EventListener eventListener;

    private boolean isAsync = false;

    /**
     * {@link EventListenerDef} を構築します。<br />
     * 
     * @param target
     *            ターゲットメソッド
     * @param eventListener
     *            {@link EventListener}
     * @param isAsync
     *            <code>true</code> の場合、非同期実行するメソッドを示します
     */
    public EventListenerDef(final Method target,
            final EventListener eventListener, final boolean isAsync) {
        AssertionUtil.assertNotNull("target", target);
        AssertionUtil.assertNotNull("eventListener", eventListener);
        this.targetMethod = target;
        this.eventListener = eventListener;
        this.isAsync = isAsync;
    }

    /**
     * ターゲットメソッドの {@link Method} オブジェクトを取得します。<br />
     * 
     * @return ターゲットメソッドの {@link Method} オブジェクト
     */
    public Method getTargetMethod() {
        return this.targetMethod;
    }

    /**
     * 対応するコンポーネントの ID を取得します。<br />
     * 
     * @return 対応するコンポーネントの ID
     */
    public String[] getId() {
        return this.eventListener.id();
    }

    /**
     * イベントの種類を返します。<br />
     * 
     * @return イベントの種類
     */
    public EventListenerType getType() {
        return this.eventListener.type();
    }

    /**
     * 非同期実行するメソッドであるかどうかを返します。<br />
     * 
     * @return <code>true</code> の場合、非同期実行を行う。<code>false</code> の場合、行わない。
     */
    public boolean isAsync() {
        return this.isAsync;
    }
}
