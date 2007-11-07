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
package org.seasar.uruma.context;

import java.util.Collection;
import java.util.List;

/**
 * {@link WidgetHandle} を保持するクラスのためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface WidgetHolder {
    /**
     * すべての {@link WidgetHandle} のコレクションを返します。
     * 
     * @return {@link WidgetHandle} のコレクション
     */
    public Collection<WidgetHandle> getWidgetHandles();

    /**
     * <code>handleId</code> で指定された ID を持つ {@link WidgetHandle} を返します。<br />
     * 
     * @param handleId
     *            ハンドルID
     * @return {@link WidgetHandle} オブジェクト。見つからなかった場合は、<code>null</code>。
     */
    public WidgetHandle getWidgetHandle(String handleId);

    /**
     * <code>handleId</code> で指定された ID を持つ {@link WidgetHandle}
     * が存在するかどうかを調べます。
     * 
     * @param handleId
     *            ハンドルID
     * @return 存在すれば <code>true</code>。存在しなければ <code>false</code>
     */
    public boolean hasWidgetHandle(String handleId);

    /**
     * {@link WidgetHandle} を登録します。<br />
     * 
     * @param handle
     *            {@link WidgetHandle} オブジェクト
     */
    public void putWidgetHandle(WidgetHandle handle);

    /**
     * 指定したクラスのオブジェクトを持つ {@link WidgetHandle} のリストを返します。<br />
     * 
     * @param clazz
     *            クラス
     * @return {@link WidgetHandle} のリスト
     */
    public List<WidgetHandle> getWidgetHandles(Class<?> clazz);
}
