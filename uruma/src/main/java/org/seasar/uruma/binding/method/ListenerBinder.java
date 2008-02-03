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

import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.context.PartContext;
import org.seasar.uruma.context.WidgetHandle;

/**
 * {@link WidgetHandle} の保持するウィジットに対してリスナを設定するためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ListenerBinder {
    /**
     * {@link WidgetHandle} の保持するウィジットに対してリスナを設定します。<br />
     * 
     * @param handle
     *            {@link WidgetHandle} オブジェクト
     * @param context
     *            {@link PartContext} オブジェクト
     * @param binding
     *            {@link MethodBinding} オブジェクト
     * @param def
     *            {@link EventListenerDef} オブジェクト
     * @return バインドしたリスナのクラス
     */
    public Class<?> bindListener(WidgetHandle handle, PartContext context,
            MethodBinding binding, EventListenerDef def);

    /**
     * 本 {@link ListenerBinder} が対象とするクラスを返します。<br />
     * 
     * @return 対象クラス
     */
    public Class<?> getTargetCLass();

    /**
     * 本 {@link ListenerBinder} が対象とする {@link EventListenerType} を返します。<br />
     * すべての種類のイベントを対象とする場合、<code>null</code> を返します。<br />
     * 
     * @return {@link EventListenerType} の配列または <code>null</code>
     */
    public EventListenerType[] getEventTypes();
}
