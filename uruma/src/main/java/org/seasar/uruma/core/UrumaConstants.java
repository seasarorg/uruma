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
package org.seasar.uruma.core;

import javax.xml.parsers.SAXParserFactory;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.rcp.UrumaService;

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
     * 半角スペースを表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WHITE_SPACE = " ";

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
     * コロン (:) を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String COLON = ":";

    /**
     * アンパサンド (&) を表す定数です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String AMPERSAND = "&";

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
     * Uruma が使用するメッセージリソースバンドル名称です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_MESSAGE_BASE = "urumaCoreMessages";

    // -------------------------------------------------------------------------
    // XML 関連
    // -------------------------------------------------------------------------
    /**
     * {@link SAXParserFactory} を指定するためのプロパティ名です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PROP_SAX_PARSER_FACTORY = "javax.xml.parsers.SAXParserFactory";

    /**
     * {@link SAXParserFactory} の実装クラス名です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SAX_PARSER_FACTORY_CLASS = "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl";

    /**
     * 画面定義XMLのスキーマファイルパスです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SCHEMA_PATH = "org/seasar/uruma/component/factory/uruma.xsd";

    /**
     * Uruma コンポーネントディスクリプタのスキーマファイルパスです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String COMPONENT_DESC_SCHEMA_PATH = "org/seasar/uruma/component/factory/urumaComponent.xsd";

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
     * 自ウィンドウの {@link Shell} オブジェクトが登録されるIDです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SHELL_CID = "shell";

    /**
     * 自ウィンドウの {@link Window} オブジェクトが登録されるIDです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WINDOW_CID = "window";

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
     * Uruma Core バンドルのデバッグオプションを表すプロパティです。<br />
     * 本プロパティを <code>true</code> に設定すると、OSGi Framework Extension
     * として動作する部分のデバッグメッセージを表示します。
     * 
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_EXTENSION_DEBUG = "org.seasar.uruma.extension.debug";

    /**
     * Uruma アプリケーションのデフォルトログ設定ファイル名(properties形式)です。<br />
     * 
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_LOG_PROPERTIES = "log4j.properties";

    /**
     * Uruma アプリケーションのデフォルトログ設定ファイル名(xml形式)です。<br />
     * 
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_LOG_XML = "log4j.xml";

    /**
     * Uruma Core バンドルのシンボリックネームです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_BUNDLE_SYMBOLIC_NAME = "org.seasar.uruma";

    /**
     * Uruma が内部で使用するコンポーネントを定義した dicon ファイルのパスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_RCP_DICON_PATH = "urumaRcp.dicon";

    /**
     * {@link UrumaService} 登録時のコンポーネント名称です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_SERVICE_S2NAME = "urumaService";

    /**
     * ワークベンチに関する画面定義を記述した XML のデフォルトパスです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEFAULT_WORKBENCH_XML = "workbench.xml";

    /**
     * RCP 環境における画面定義 XML のデフォルト格納先パス({@link #DEFAULT_WORKBENCH_XML} からの相対パス)です。
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
     * <code>workbench</code> 要素で <code>initHeight</code> 属性が未指定の場合のデフォルト値です。
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

    /**
     * <code>menu</code> 要素で id が未指定のときに自動的に設定される id のプレフィックスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String AUTO_MENU_ID_PREFIX = "autoDefinedMenu_";

    /**
     * <code>menuItem</code> 要素で id が未指定のときに自動的に設定される id のプレフィックスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String AUTO_ACTION_ID_PREFIX = "autoDefinedAction_";

    /**
     * Uruma アプリケーションが使用するキーコンフィグレーション(スキーム)の ID です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APP_SCHEME_ID = "org.seasar.uruma.keyConfiguration";

    /**
     * Uruma アプリケーションが使用するキーコンフィグレーション(スキーム)の名称です。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APP_SCHEME_NAME = "UrumaApplication";

    /**
     * workbench.xml が存在しないときに使用されるダミーのパスです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DUMMY_WORKBENCH_PATH = "dummyWorkbench.xml";

    // -------------------------------------------------------------------------
    // UrumaService に関する定数
    // -------------------------------------------------------------------------

    /**
     * UrumaアプリケーションであるバンドルのSymbolicNameを保持するプロパティ名称です。
     * 
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_SERVICE_PROP_APPS = "UrumaApplications";
}
