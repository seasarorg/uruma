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
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerComparator;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.rcp.UrumaActivator;

/**
 * Uruma で利用する定数を保持するインターフェースです。</br>
 * 
 * @author y-komori
 */
public interface UrumaConstants {
    /**
     * 空文字列を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String NULL_STRING = "";

    /**
     * スラッシュ (/) を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SLASH = "/";

    /**
     * アットマーク (@) を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String AT_MARK = "@";

    /**
     * ピリオド (.) を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PERIOD = ".";

    /**
     * コンマ (,) を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String COMMA = ",";

    /**
     * エクスクラメーションマーク (!) を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String EXCLAMATION_MARK = "!";

    /**
     * ハッシュマーク (#) を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String HASH_MARK = "#";

    /**
     * Uruma が内部で使用するコンポーネントを定義した dicon ファイルのパスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_DICON_PATH = "uruma.dicon";

    /**
     * デフォルトのイメージバンドル名称です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_IMAGE_BUNDLE_PATH = "urumaImages";

    /**
     * ユーザアプリケーションが使用するメッセージリソースバンドル名称です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String USER_MESSAGE_BASE = "urumaMessages";

    /**
     * 画面定義XMLのスキーマファイルパスです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SCHEMA_PATH = "org/seasar/uruma/component/factory/uruma.xsd";

    // -------------------------------------------------------------------------
    // プロトコル名称
    // -------------------------------------------------------------------------
    /**
     * jar プロトコルの名称です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PROTCOL_JAR = "jar";

    /**
     * file プロトコルの名称です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PROTCOL_FILE = "file";

    // -------------------------------------------------------------------------
    // 命名規約
    // -------------------------------------------------------------------------
    /**
     * パートアクションクラスのサフィックスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PART_ACTION_SUFFIX = "Action";

    /**
     * フォームクラスのサフィックスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String FORM_SUFFIX = "Form";

    /**
     * {@link ILabelProvider} の S2Container 上でのコンポーネント名称サフィックスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String LABEL_PROVIDER_SUFFIX = "LabelProvider";

    /**
     * {@link IContentProvider} の S2Container 上でのコンポーネント名称サフィックスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CONTENT_PROVIDER_SUFFIX = "ContentProvider";

    /**
     * {@link ViewerComparator} の S2Container 上でのコンポーネント名称サフィックスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SORTER_SUFFIX = "Sorter";

    // -------------------------------------------------------------------------
    // コンテキストへ登録されるID
    // -------------------------------------------------------------------------

    /**
     * 自ウィンドウのShellオブジェクトが登録されるIDです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SHELL_CID = "shell";

    /**
     * メニューバー用の {@link IMenuManager} が登録される ID です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String MENU_BAR_MANAGER_CID = "menuBarManager";

    /**
     * {@link IStatusLineManager} が登録される ID です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String STATUS_LINE_MANAGER_CID = "statusLineManager";

    // -------------------------------------------------------------------------
    // RCP関連機能
    // -------------------------------------------------------------------------
    /**
     * Uruma Core バンドルのシンボリックネームです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_BUNDLE_SYMBOLIC_NAME = "org.seasar.uruma.rcp.core";

    /**
     * Uruma が内部で使用するコンポーネントを定義した dicon ファイルのパスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_RCP_DICON_PATH = "urumaRcp.dicon";

    /**
     * {@link UrumaActivator} を登録時のコンポーネント名称です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_PLUGIN_S2NAME = "urumaPlugin";

    /**
     * ワークベンチに関する画面定義を記述した XML のデフォルトパスです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_WORKBENCH_XML = "workbench.xml";

    /**
     * RCP 環境における画面定義 XML のデフォルト格納先パス({@link #DEFAULT_WORKBENCH_XML}
     * からの相対パス)です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_VIEWS_PATH = "views";

    /**
     * ワークベンチの {@link Template} を {@link ApplicationContext} へ登録する際の名称です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WORKBENCH_TEMPLATE_NAME = "workbenchTemplate";

    /**
     * ワークベンチウィンドウに対応する {@link WindowContext} の名称です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WORKBENCH_WINDOW_CONTEXT_ID = "workbenchWindow";

    /**
     * <code>workbench</code> 要素で <code>initWidth</code> 属性が未指定の場合のデフォルト値です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_WORKBENCH_WIDTH = "30%";

    /**
     * <code>workbench</code> 要素で <code>initHeight</code>
     * 属性が未指定の場合のデフォルト値です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_WORKBENCH_HEIGHT = "30%";

    /**
     * <code>workbench</code> 要素で <code>initialPerspectiveId</code>
     * が未指定のときのデフォルトパースペクティブ ID です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_PERSPECTIVE_ID = "defaultPerspective";
}
