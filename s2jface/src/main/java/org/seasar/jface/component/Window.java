/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.component;

/**
 * ウィンドウに関する情報を保持するオブジェクトのためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface Window {

    /**
     * メニューバーとして使用する <code>Menu</code> オブジェクトを登録します。<br />
     * <p>
     * メニューバーとして使用する <code>Menu</code> オブジェクトは1つのみです。 <code>Menu</code>
     * 本メソッドで登録された <code>Menu</code> オブジェクトは、<code>addMenu()</code>
     * による登録も自動的に行われます。
     * </p>
     * 
     * @param menu
     *            <code>Menu</code> オブジェクト
     */
    public void setMenuAsMenuBar(Menu menu);

    /**
     * メニューバーとして使用する <code>Menu</code> オブジェクトを返します。<br />
     * 
     * @return メニューバーとして使用する <code>Menu</code> オブジェクト。登録されていない場合は
     *         <code>null</code>。
     */
    public Menu getMenuBar();

    /**
     * メニューバー以外で使用する <code>Menu</code> オブジェクトを登録します。<br />
     * <p>
     * ポップアップメニュー等で使用するメニューは本メソッドを利用して登録してください。<br />
     * なお、<code>Menu</code> オブジェクトの <code>setId()</code> メソッドによって ID
     * が登録されていない場合は <code>findMenu()</code> メソッドによる検索ができません。
     * </p>
     * 
     * @param menu
     *            <code>Menu</code> オブジェクト。
     */
    public void addMenu(Menu menu);

    /**
     * ID をキーとして登録されたメニューを検索して返します。<br />
     * 
     * @param id
     *            メニューのID
     * @return 見つかった <code>Menu</code> オブジェクト。見つからなかった場合は <code>null</code>。
     */
    public Menu findMenu(String id);
}
