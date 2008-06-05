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

import org.seasar.framework.exception.SIOException;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.context.ApplicationContext;
import org.seasar.uruma.rcp.UrumaApplication;
import org.seasar.uruma.rcp.UrumaService;
import org.seasar.uruma.rcp.configuration.ConfigurationWriter;

/**
 * Uruma で利用するメッセージコードを定義するインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface UrumaMessageCodes {
    // -------------------------------------------------------------------------
    // 共通メッセージコード (00xx)
    // -------------------------------------------------------------------------
    /**
     * 例外が発生した場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String EXCEPTION_OCCURED = "EURM0000";

    /**
     * 例外が発生した場合のメッセージコード(理由つき)です。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String EXCEPTION_OCCURED_WITH_REASON = "EURM0001";

    /**
     * パラメータが <code>null</code> であってはいけない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CANT_BE_NULL = "EURM0002";

    /**
     * パラメータが空文字列であってはいけない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CANT_BE_EMPTY_STRING = "EURM0003";

    /**
     * 未サポートのクラスが指定された場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String UNSUPPORTED_CLASS = "EURM0004";

    /**
     * 型が想定している型に一致しない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String TYPE_MISS_MATCH = "EURM0005";

    /**
     * {@link SIOException} が発生した場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String IO_EXCEPTION_OCCURED = "EURM0006";

    // -------------------------------------------------------------------------
    // XMLパース関連メッセージコード (01xx)
    // -------------------------------------------------------------------------
    /**
     * レイアウトが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String LAYOUT_NOTO_FOUND = "EURM0100";

    /**
     * レイアウトデータが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String LAYOUT_DATA_NOT_FOUND = "EURM0101";

    /**
     * レンダラが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String RENDERER_NOT_FOUND = "EURM0102";

    /**
     * イベントリスナが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SWT_EVENT_LISTENER_NOT_FOUND = "EURM0103";

    /**
     * アノテーションで指定された id が画面定義 XML 上に存在しないことを示すメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WIDGET_NOT_FOUND_ON_XML = "EURM0104";

    /**
     * コンポーネント ID が重複して定義されている際のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DUPLICATE_COMPONENT_ID = "EURM0105";

    /**
     * 継承先コンポーネントが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String EXTEND_TARGET_COMPONENT_NOT_FOUND = "EURM0106";

    /**
     * 継承先プロパティが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String EXTEND_TARGET_PROPERTY_NOT_FOUND = "EURM0107";

    /**
     * {@link UIComponent} が見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String UICOMPONENT_NOT_FOUND = "EURM0108";

    /**
     * 画面定義テンプレートをファイルから読み込む際のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String LOAD_TEMPLATE_FROM_FILE = "IURM0110";

    /**
     * 画面定義テンプレートをキャッシュから読み込む際のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String LOAD_TEMPLATE_FROM_CACHE = "IURM0111";

    /**
     * 画面定義テンプレート登録時のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String TEMPLATE_REGISTERED = "IURM0112";

    /**
     * 重複した ID を持つ画面定義テンプレートが見つかった際のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DUPLICATE_ID_TEMPLATE = "EURM0113";

    /**
     * 指定された ID を持つ画面定義テンプレートが見つからなかった際のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String TEMPLATE_NOT_FOUND = "EURM0114";

    /**
     * XML 要素をマッピングする Java クラス側に対応する属性が見つからなかった際のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PROPERY_NOT_FOUND = "EURM0115";

    /**
     * 指定された ID を持つすべての画面定義テンプレートをキャッシュから削除する際のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DELETE_ALL_TEMPLATE_FROM_CACHE = "DURM0116";

    /**
     * 指定された ID を持つ画面定義テンプレートをキャッシュから削除する際のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DELETE_TEMPLATE_FROM_CACHE = "DURM0117";

    // -------------------------------------------------------------------------
    // バインディング関連メッセージコード (02xx)
    // -------------------------------------------------------------------------
    /**
     * アノテートされたメソッドが複数存在する場合のエラーコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DUPLICATE_ANNOTATED_METHOD = "EURM0200";

    /**
     * メソッドが引数・戻り値なしのメソッドでない場合のエラーコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String ILLEGAL_METHOD_SIGNATURE = "EURM0201";

    /**
     * イニシャライズメソッド実行中に例外が発生した場合のエラーコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String EXCEPTION_ON_INVOKING_INITIALIZE_METHOD = "EURM0202";

    /**
     * サポートされていない型のウィジットに対してバインディングしようとした場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WIDGET_NOT_SUPPORTED = "EURM0203";

    /**
     * アノテートされたフィールドに対応するウィジットが存在しない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WIDGET_NOT_FOUND = "EURM0204";

    /**
     * EnablesDepending でターゲットウィジットがサポートされていない際のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEPENDING_WIDGET_NOT_SUPPORTED = "EURM0205";

    /**
     * EnablesDepending でターゲットに対する選択条件がサポートされていない際のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DEPENDING_TYPE_NOT_SUPPORTED = "EURM0206";

    /**
     * バインド先とバインド元の型が一致しない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CLASS_NOT_MUTCH = "EURM0207";

    /**
     * バインド処理を実行する際のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DO_BINDING = "DURM0208";

    /**
     * {@link ApplicationContext} からオブジェクトへ値をバインドする際のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String IMPORT_APPLICATION_CONTEXT = "DURM0209";

    /**
     * オブジェクトから {@link ApplicationContext} へ値をバインドする際のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String EXPORT_APPLICATION_CONTEXT = "DURM0210";

    /**
     * メソッドバインディング開始時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String START_METHOD_CALL = "IURM0211";

    /**
     * メソッドバインディング終了時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String END_METHOD_CALL = "IURM0212";

    /**
     * EnablesDepending ターゲットが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String ENABLES_DEPENDING_TARGET_NOT_FOUND = "EURM0213";

    /**
     * パートアクションクラス設定時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PART_ACTION_CLASS_FOUND = "DURM0214";

    /**
     * フォームクラス設定時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String FORM_CLASS_FOUND = "DURM0215";

    /**
     * メソッドバインディング生成時のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CREATE_METHOD_BINDING = "DURM0216";

    /**
     * ISelectionListener 登録時のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String ISELECTION_LISTENER_REGISTERED = "DURM0217";

    /**
     * リスナからのメソッド実行中に例外が発生した場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String EXCEPTION_OCCURED_INVOKING_METHOD = "EURM0218";

    /**
     * メソッドバインディング対象メソッドの引数の数が不正である場合のメッセージコードです。<br />
     * 
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String ILLEGAL_ARG_NUMBERS = "EURM0219";

    /**
     * ワークベンチアクションクラス設定時のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WORKBENCH_ACTION_CLASS_FOUND = "DURM0220";

    /**
     * パートアクションクラスが見つからなかった場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PART_ACTION_CLASS_NOT_FOUND = "WURM0221";

    /**
     * ワークベンチアクションクラスが見つからなかった場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WORKBENCH_ACTION_CLASS_NOT_FOUND = "WURM0222";

    /**
     * フォームクラスが見つからなかった場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String FORM_CLASS_NOT_FOUND = "WURM0223";

    /**
     * フォームクラスが見つからなかった場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String VALUE_BINDER_NOT_FOUND = "WURM0224";

    // -------------------------------------------------------------------------
    // レンダリング関連メッセージコード (03xx)
    // -------------------------------------------------------------------------
    /**
     * ウィンドウが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WINDOW_NOT_FOUND = "EURM0300";

    /**
     * レンダリング時の値の設定に失敗した場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String RENDER_MAPPING_FAILED = "EURM0301";

    /**
     * レンダリング時に実際の型が想定していた型と異なっていた場合のエラーコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String UNSUPPORTED_TYPE_ERROR = "EURM0302";

    /**
     * ViewPart のレンダリング時にルートコンポーネントが {@link ViewPartComponent} でなかった場合のエラーコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String REQUIRED_VIEWPART_ERROR = "EURM0303";

    /**
     * {@link UIComponent#preRender(org.seasar.uruma.context.WidgetHandle,
     * org.seasar.uruma.context.WindowContext)} メソッド開始時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PRE_RENDER_START = "TURM0304";

    /**
     * {@link UIComponent#preRender(org.seasar.uruma.context.WidgetHandle,
     * org.seasar.uruma.context.WindowContext)} メソッド終了時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PRE_RENDER_END = "TURM0305";

    /**
     * {@link UIComponent#render(org.seasar.uruma.context.WidgetHandle,
     * org.seasar.uruma.context.PartContext)} メソッド開始時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String RENDER_START = "TURM0306";

    /**
     * {@link UIComponent#render(org.seasar.uruma.context.WidgetHandle,
     * org.seasar.uruma.context.PartContext)} メソッド終了時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String RENDER_END = "TURM0307";

    /**
     * ウィジット生成時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WIDGET_CREATED = "TURM0308";

    /**
     * コンテントプロバイダ設定時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CONTENT_PROVIDER_FOUND = "DURM0309";

    /**
     * ラベルプロバイダ設定時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String LABEL_PROVIDER_FOUND = "DURM0310";

    /**
     * コンパレータ設定時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String COMPARATOR_FOUND = "DURM0311";

    /**
     * コンポーネントのプロパティが String 型ではないため、読み出しに失敗した際のメッセージコードです。<br />
     */
    public static final String COMPONENT_PROPERTY_IS_NOT_STRING = "EURM0312";

    /**
     * ウィジットにプロパティが見つからず、値を設定しなかった際のメッセージコードです。<br />
     */
    public static final String WIDGET_PROPERTY_NOT_FOUND = "EURM0313";

    /**
     * プロパティの書き込みに失敗した際のメッセージコードです。<br />
     */
    public static final String PROPERTY_IS_NOT_WRITABLE = "EURM0314";

    /**
     * ウィジェット 登録時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WIDGET_REGISTERED = "DURM0315";

    /**
     * ウィジェット ID が重複して定義されている際のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DUPLICATE_WIDGET_ID = "EURM0316";

    // -------------------------------------------------------------------------
    // ウィンドウ管理関連メッセージコード (04xx)
    // -------------------------------------------------------------------------
    /**
     * ウィンドウオープン時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String OPEN_WINDOW = "IURM0400";

    /**
     * ウィンドウ初期化時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String INIT_WINDOW = "IURM0401";

    /**
     * ウィンドウクローズ時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CLOSE_WINDOW = "IURM0402";

    // -------------------------------------------------------------------------
    // RCP関連メッセージコード (05xx)
    // -------------------------------------------------------------------------

    /**
     * Uruma バンドル開始時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_BUNDLE_START = "IURM0500";

    /**
     * Uruma バンドル終了時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_BUNDLE_STOP = "IURM0501";

    /**
     * 画面定義 XML に <code>workbench</code> 要素が定義されていなかった場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WORKBENCH_ELEMENT_NOT_FOUND = "EURM0502";

    /**
     * 画面定義 XML 検索開始時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String FINDING_XML_START = "IURM0503";

    /**
     * バンドルへのコントリビューション生成時のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CREATE_CONTRIBUTION = "TURM0504";

    /**
     * {@link ConfigurationWriter} が見つからない場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String CONFIGURATION_WRITER_NOT_FOUND = "EURM0505";

    /**
     * <code>perspective</code> 要素内で参照されているパートの ID が見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PART_IN_PERSPECTIVE_NOT_FOUND = "EURM0506";

    /**
     * パースペクティブが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String PERSPECTIVE_NOT_FOUND = "EURM0507";

    /**
     * Urumaアプリケーションを発見した場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APPLICATION_FOUND = "IURM0508";

    /**
     * バンドル調査時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String ANALYZING_BUNDLE = "DURM0509";

    /**
     * {@link UrumaService} 初期化開始時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_SERVICE_INIT_START = "IURM0510";

    /**
     * {@link UrumaService} 初期化完了時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_SERVICE_INIT_END = "IURM0511";

    /**
     * Urumaアプリケーションのアクティベーション開始時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APP_STARTING = "IURM0512";

    /**
     * Urumaアプリケーションのアクティベーション完了時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APP_STARTED = "IURM0513";

    /**
     * Urumaアプリケーションのアクティベーション失敗時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APP_STARTING_FAILED = "EURM0514";

    /**
     * {@link UrumaApplication} 開始時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APPLICATION_STARTING = "IURM0515";

    /**
     * {@link UrumaApplication} 終了時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APPLICATION_STOPPING = "IURM0516";

    /**
     * {@link UrumaService} 破棄時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_SERVICE_DESTROY = "IURM0517";

    /**
     * コンテクストクラスローダ切り替え時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String SWITCH_CONTEXT_CLASS_LOADER = "DURM0518";

    /**
     * S2コンポーネント登録時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String COMPONENT_REGISTERED = "DURM0519";

    /**
     * Uruma アプリケーションが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String URUMA_APP_NOT_FOUND = "EURM0520";

    /**
     * バンドルを開始する場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String BUNDLE_START = "DURM0521";

    /**
     * バンドルが開始済みの場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String BUNDLE_STARTED = "DURM0522";

    /**
     * バンドルを停止する場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String BUNDLE_STOP = "DURM0523";

    /**
     * バンドルを更新する場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String BUNDLE_UPDATE = "DURM0524";

    // -------------------------------------------------------------------------
    // リソース関連メッセージコード (06xx)
    // -------------------------------------------------------------------------

    /**
     * メッセージリソースが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String MESSAGE_RESOURCE_NOT_FOUND = "EURM0600";

    /**
     * メッセージリソースからキーが見つからない場合のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String MESSAGE_KEY_NOT_FOUND = "WURM0601";

    /**
     * Dicon ファイルが見つからない場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String DICON_FILE_NOT_FOUND = "WURM0602";

    /**
     * urumaImages.properties が見つからない場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String IMAGE_DEF_BUNDLE_NOT_FOUND = "WURM0603";

    /**
     * workbench.xml が見つからない場合のメッセージコードです。<br />
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WORKBENCH_DEF_FILE_NOT_FOUND = "WURM0604";

    // -------------------------------------------------------------------------
    // Win32API関連メッセージコード (98xx)
    // -------------------------------------------------------------------------
    /**
     * API の呼び出しに失敗した場合のメッセージコードです。<br />
     * 
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String WIN32_API_CALL_FAILED = "WURM9800";

    // -------------------------------------------------------------------------
    // システムデバッグログ関連メッセージコード (99xx)
    // -------------------------------------------------------------------------
    /**
     * {@link StandAloneUrumaStarter} 初期化時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String STAND_ALONE_URUMA_STARTER_INIT = "DURM9900";

    /**
     * イメージバンドルファイル読み込み時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String LOADING_IMAGE_BUNDLE = "DURM9901";

    /**
     * {@link StandAloneUrumaStarter} 終了時のメッセージコードです。
     * <dl>
     * <dt><b>値 :</b></dt>
     * <dd>{@value}</dd>
     * </dl>
     */
    public static final String STAND_ALONE_URUMA_STARTER_STOP = "DURM9902";
}
