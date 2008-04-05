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
package org.seasar.uruma.desc;

import java.lang.reflect.Method;
import java.util.List;

import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.annotation.PostOpenMethod;
import org.seasar.uruma.binding.context.ApplicationContextDef;
import org.seasar.uruma.binding.method.EventListenerDef;

/**
 * パートアクションクラスのメタデータを扱うためのインターフェースです。<br />
 * パートアクションクラスは、ウィンドウパートで発生したイベントを処理するためのクラスで、POJO として実現されます。<br />
 * 
 * @author y-komori
 */
public interface PartActionDesc {
    /**
     * {@link InitializeMethod} アノテーションが付加されたメソッドを取得します。<br />
     * 
     * @return {@link InitializeMethod} アノテーションが付加されたメソッド
     */
    public Method getInitializeMethod();

    /**
     * {@link InitializeMethod} アノテーションが付加されたメソッドを実行します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     */
    public void invokeInitializeMethod(Object target);

    /**
     * {@link PostOpenMethod} アノテーションが付加されたメソッドを取得します。<br />
     * 
     * @return {@link PostOpenMethod} アノテーションが付加されたメソッド
     */
    public Method getPostOpenMethod();

    /**
     * {@link PostOpenMethod} アノテーションが付加されたメソッドを実行します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     */
    public void invokePostOpenMethod(Object target);

    /**
     * {@link EventListenerDef} のリストを取得します。<br />
     * 
     * @return {@link EventListenerDef} のリスト
     */
    public List<EventListenerDef> getEventListenerDefList();

    /**
     * {@link ApplicationContextDef} のリストを取得します<br />
     * 
     * @return {@link ApplicationContextDef} のリスト
     */
    public List<ApplicationContextDef> getApplicationContextDefList();
}
