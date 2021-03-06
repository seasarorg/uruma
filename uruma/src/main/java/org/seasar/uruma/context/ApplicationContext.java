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
package org.seasar.uruma.context;

import java.util.Collection;

import org.seasar.uruma.resource.ResourceRegistry;

/**
 * アプリケーション全体に共通な情報を保持するクラスのためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ApplicationContext {
    /**
     * {@link ApplicationContext} が保持する {@link WindowContext} のコレクションを返します。<br />
     * {@link WindowContext} を1つも保持しない場合、空のコレクションを返します。
     * 
     * @return {@link WindowContext} のコレクション
     */
    public Collection<WindowContext> getWindowContexts();

    /**
     * {@code windowName} で指定された名称を持つ {@link WindowContext} を返します。<br />
     * 
     * @param windowName
     *        ウィンドウ名称
     * @return {@link WindowContext} オブジェクト。見つからなかった場合は {@code null}
     */
    public WindowContext getWindowContext(String windowName);

    /**
     * {@link ResourceRegistry} を取得します。<br />
     * 
     * @return {@link ResourceRegistry} のインスタンス
     */
    public ResourceRegistry getResourceRegistry();

    /**
     * {@link ApplicationContext} へ値を設定します。<br />
     * 同じ名称の値が既に設定されている場合は、上書きされます。<br />
     * 
     * @param name
     *        名称
     * @param value
     *        値
     */
    public void setValue(String name, Object value);

    /**
     * {@link ApplicationContext} から値を取得します。<br />
     * 
     * @param name
     *        名称
     * @return 値。見つからない場合は <code>null</code>
     */
    public Object getValue(String name);
}
