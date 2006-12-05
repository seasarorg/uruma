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
import java.util.List;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.binding.EnabledDepend;
import org.seasar.jface.binding.WidgetEnabledDependBinder;
import org.seasar.jface.viewer.ViewerAdapter;

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
     * 本ウィンドウに対応するアクションコンポーネントを設定します。<br />
     * 
     * @param actionComponent
     *            アクションコンポーネント
     */
    public void setActionComponent(Object actionComponent);

    /**
     * 本ウィンドウに対応するアクションコンポーネントを取得します。<br />
     * 
     * @return アクションコンポーネント
     */
    public Object getActionComponent();

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

    /**
     * {@link EnabledDepend} を登録します。<br />
     * 
     * @param enabledDepend
     *            {@link EnabledDepend} オブジェクト。
     */
    public void addEnabledDepend(EnabledDepend enabledDepend);

    /**
     * {@link EnabledDepend} のリストを取得します。<br />
     * 
     * @return {@link EnabledDepend} のリスト。
     */
    public List<EnabledDepend> getEnabledDepends();

    /**
     * {@link WidgetEnabledDependBinder} を登録します。<br />
     * 
     * @param binder
     *            {@link WidgetEnabledDependBinder} オブジェクト。
     */
    public void addWidgetEnabledDependBinder(WidgetEnabledDependBinder binder);

    /**
     * {@link WidgetEnabledDependBinder} のリストを取得します。<br />
     * 
     * @return {@link WidgetEnabledDependBinder} のリスト。
     */
    public List<WidgetEnabledDependBinder> getWidgetEnabledDependBinders();

    /**
     * {@link Widget} をキーとして {@link ViewerAdapter} を登録します。
     * 
     * @param id
     *            ID
     * @param adapter
     *            {@link ViewerAdapter} オブジェクト
     */
    public void putViewerAdapter(Widget widget, ViewerAdapter adapter);

    /**
     * {@link Widget} をキーとして関連づけられた {@link ViewerAdapter} を取得します。
     * 
     * @param id
     *            ID
     * @return {@link ViewerAdapter} オブジェクト
     */
    public ViewerAdapter getViewerAdapter(Widget widget);
}
