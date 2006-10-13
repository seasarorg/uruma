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
package org.seasar.jface;

import java.lang.reflect.Method;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Widget;

/**
 * ウィンドウに関する各種情報を保持するためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface WindowContext {

    /**
     * 自ウィンドウのShellオブジェクトが登録されるIDです。
     */
    public static final String SHELL_ID = "shell";

    /**
     * 本ウィンドウに対応するアクションクラスのオブジェクトを設定します。<br />
     * 
     * @param actionObject
     *            アクションクラスのオブジェクト
     */
    public void setActionObject(Object actionObject);

    /**
     * 本ウィンドウに対応するアクションクラスのオブジェクトを取得します。<br />
     * 
     * @return アクションクラスのオブジェクト
     */
    public Object getActionObject();

    /**
     * アクションクラスのイニシャライズメソッドを設定します。<br />
     * <p>
     * イニシャライズメソッドは {@link org.seasar.jface.annotation.InitializeMethod}
     * アノテーションが付加されたメソッドです。<br />
     * イニシャライズメソッドは1つのアクションクラスにつき1つのみ存在が許されます。<br />
     * イニシャライズメソッドは、引数および戻り値を持たないメソッドです。<br />
     * </p>
     * 
     * @param method
     *            イニシャライズメソッド
     */
    public void setInitializeMethod(Method method);

    /**
     * アクションクラスのイニシャライズメソッドを取得します。<br />
     * 
     * @return イニシャライズメソッド
     */
    public Method getInitializeMethod();

    /**
     * 画面コンポーネント(SWTウィジット)を登録します。</br>
     * 
     * @param id
     *            コンポーネントのID
     * @param component
     *            コンポーネント
     * @throws org.seasar.jface.exception.DuplicateComponentIdException
     *             コンポーネントIDが既に登録されている場合にスローされます。
     * @see Widget
     */
    public void putComponent(String id, Widget component);

    /**
     * 画面コンポーネント(SWTウィジット)を取得します。</br>
     * 
     * @param id
     *            コンポーネントのID
     * @return コンポーネント
     */
    public Widget getComponent(String id);

    /**
     * ウィンドウへメニューバーとして表示する <code>MenuManager</code> を登録します。</br>
     * <p>
     * ウィンドウへメニューバーを表示する際は、レンダラ内で<code>MenuManager</code>
     * を生成して本メソッドを利用して登録してください。
     * </p>
     * 
     * @param menuManager
     *            <code>MenuManager</code>オブジェクト
     * @see MenuManager
     */
    @Deprecated
    public void setMenuBar(MenuManager menuManager);

    /**
     * メニューバーとして登録した <code>MenuManager</code> を取得します。</br>
     * 
     * @return <code>MenuManager</code> のインスタンス
     * @see MenuManager
     */
    @Deprecated
    public MenuManager getMenuBar();
}
