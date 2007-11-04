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
package org.seasar.uruma.core;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.rcp.UrumaActivator;

/**
 * Uruma で利用する定数を保持するクラスです。</br>
 * 
 * @author y-komori
 */
public class UrumaConstants {
    /**
     * 空文字列を表す定数です。<br />
     */
    public static final String NULL_STRING = "";

    /**
     * Uruma が内部で使用するコンポーネントを定義した dicon ファイルのパスです。<br />
     */
    public static final String URUMA_DICON_PATH = "uruma.dicon";

    /**
     * デフォルトのイメージバンドル名称です。<br />
     */
    public static final String DEFAULT_IMAGE_BUNDLE_PATH = "urumaImages";

    // -------------------------------------------------------------------------
    // コンテキストへ登録されるID
    // -------------------------------------------------------------------------
    /**
     * 自ウィンドウのShellオブジェクトが登録されるIDです。
     */
    public static final String SHELL_CID = "shell";

    /**
     * メニューバー用の {@link IMenuManager} が登録される ID です。
     */
    public static final String MENU_BAR_MANAGER_CID = "menuBarManager";

    /**
     * ID の設定されていない <code>menu</code> 要素にデフォルトで割り当てられる ID です。
     */
    public static final String DEFAULT_MENU_CID = "defaultMenu";

    /**
     * {@link IStatusLineManager} が登録される ID です。
     */
    public static final String STATUS_LINE_MANAGER_CID = "statusLineManager";

    // -------------------------------------------------------------------------
    // RCP関連機能
    // -------------------------------------------------------------------------
    /**
     * Uruma が内部で使用するコンポーネントを定義した dicon ファイルのパスです。<br />
     */
    public static final String URUMA_RCP_DICON_PATH = "urumaRcp.dicon";

    /**
     * {@link UrumaActivator} を登録時のコンポーネント名称です。
     */
    public static final String URUMA_PLUGIN_S2NAME = "urumaPlugin";

    /**
     * ワークベンチの {@link Template} を {@link ApplicationContext} へ登録する際の名称です。
     */
    public static final String WORKBENCH_TEMPLATE_NAME = "workbenchTemplate";

    /**
     * <code>workbench</code> 要素で <code>initWidth</code> 属性が未指定の場合のデフォルト値です。
     */
    public static final String DEFAULT_WORKBENCH_WIDTH = "30%";

    /**
     * <code>workbench</code> 要素で <code>initHeight</code>
     * 属性が未指定の場合のデフォルト値です。
     */
    public static final String DEFAULT_WORKBENCH_HEIGHT = "30%";
}
