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

import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.component.UIComponent;
import org.seasar.uruma.component.impl.ViewPartComponent;
import org.seasar.uruma.context.ApplicationContext;

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
     */
    public static final String EXCEPTION_OCCURED = "EURM0000";

    /**
     * 例外が発生した場合のメッセージコード(理由つき)です。
     */
    public static final String EXCEPTION_OCCURED_WITH_REASON = "EURM0001";

    /**
     * パラメータが <code>null</code> であってはいけない場合のメッセージコードです。
     */
    public static final String CANT_BE_NULL = "EURM0002";

    /**
     * パラメータが空文字列であってはいけない場合のメッセージコードです。
     */
    public static final String CANT_BE_EMPTY_STRING = "EURM0003";

    /**
     * 未サポートのクラスが指定された場合のメッセージコードです。
     */
    public static final String UNSUPPORTED_CLASS = "EURM0005";

    /**
     * 型が想定している型に一致しない場合のメッセージコードです。
     */
    public static final String TYPE_MISS_MATCH = "EURM0006";

    // -------------------------------------------------------------------------
    // XMLパース関連メッセージコード (01xx)
    // -------------------------------------------------------------------------
    /**
     * レイアウトが見つからない場合のメッセージコードです。
     */
    public static final String LAYOUT_NOTO_FOUND = "EURM0101";

    /**
     * レイアウトデータが見つからない場合のメッセージコードです。
     */
    public static final String LAYOUT_DATA_NOT_FOUND = "EURM0102";

    /**
     * レンダラが見つからない場合のメッセージコードです。
     */
    public static final String RENDERER_NOT_FOUND = "EURM0103";

    /**
     * イベントリスナが見つからない場合のメッセージコードです。
     */
    public static final String SWT_EVENT_LISTENER_NOT_FOUND = "EURM0104";

    /**
     * アノテーションで指定された id が画面定義 XML 上に存在しないことを示すメッセージコードです。
     */
    public static final String WIDGET_NOT_FOUND_ON_XML = "EURM0105";

    /**
     * コンポーネント ID が重複して定義されている際のメッセージコードです。
     */
    public static final String DUPLICATE_COMPONENT_ID = "EURM0106";

    /**
     * 継承先コンポーネントが見つからない場合のメッセージコードです。
     */
    public static final String EXTEND_TARGET_COMPONENT_NOT_FOUND = "EURM0107";

    /**
     * 継承先プロパティが見つからない場合のメッセージコードです。
     */
    public static final String EXTEND_TARGET_PROPERTY_NOT_FOUND = "EURM0108";

    /**
     * {@link UIComponent} が見つからない場合のメッセージコードです。
     */
    public static final String UICOMPONENT_NOT_FOUND = "EURM0109";

    /**
     * {@link ValueBinder} が見つからない場合のメッセージコードです。
     */
    public static final String VALUE_BINDER_NOT_FOUND = "EURM0111";

    /**
     * 画面定義テンプレートをファイルから読み込む際のメッセージコードです。
     */
    public static final String LOAD_TEMPLATE_FROM_FILE = "IURM0112";

    /**
     * 画面定義テンプレートをキャッシュから読み込む際のメッセージコードです。
     */
    public static final String LOAD_TEMPLATE_FROM_CACHE = "IURM0113";

    // -------------------------------------------------------------------------
    // バインディング関連メッセージコード (02xx)
    // -------------------------------------------------------------------------
    /**
     * {@link InitializeMethod} アノテートされたメソッドが複数存在する場合のエラーコードです。
     */
    public static final String DUPLICATE_INITIALIZE_METHOD = "EURM0204";

    /**
     * イニシャライズメソッドが引数・戻り値なしのメソッドでない場合のエラーコードです。
     */
    public static final String INVALID_INITIALIZE_METHOD = "EURM0205";

    /**
     * イニシャライズメソッド実行中に例外が発生した場合のエラーコードです。
     */
    public static final String EXCEPTION_ON_INVOKING_INITIALIZE_METHOD = "EURM0206";

    /**
     * サポートされていない型のウィジットに対してバインディングしようとした場合のメッセージコードです。
     */
    public static final String WIDGET_NOT_SUPPORTED = "EURM0207";

    /**
     * アノテートされたフィールドに対応するウィジットが存在しない場合のメッセージコードです。
     */
    public static final String WIDGET_NOT_FOUND = "EURM0208";

    /**
     * EnablesDepending でターゲットウィジットがサポートされていない際のメッセージコードです。<br />
     */
    public static final String DEPENDING_WIDGET_NOT_SUPPORTED = "EJFC0211";

    /**
     * EnablesDepending でターゲットに対する選択条件がサポートされていない際のメッセージコードです。<br />
     */
    public static final String DEPENDING_TYPE_NOT_SUPPORTED = "EJFC0212";

    /**
     * バインド先とバインド元の型が一致しない場合のメッセージコードです。
     */
    public static final String CLASS_NOT_MUTCH = "EURM0214";

    /**
     * バインド処理を実行する際のメッセージコードです。<br />
     */
    public static final String DO_BINDING = "DURM0215";

    /**
     * {@link ApplicationContext} からオブジェクトへ値をバインドする際のメッセージコードです。
     */
    public static final String IMPORT_APPLICATION_CONTEXT = "DURM0216";

    /**
     * オブジェクトから {@link ApplicationContext} へ値をバインドする際のメッセージコードです。
     */
    public static final String EXPORT_APPLICATION_CONTEXT = "DURM0217";

    /**
     * メソッドバインディング開始時のメッセージコードです。
     */
    public static final String START_METHOD_CALL = "IURM0218";

    /**
     * メソッドバインディング終了時のメッセージコードです。
     */
    public static final String END_METHOD_CALL = "IURM0219";

    /**
     * EnablesDepending ターゲットが見つからない場合のメッセージコードです。
     */
    public static final String ENABLES_DEPENDING_TARGET_NOT_FOUND = "EURM0220";

    // -------------------------------------------------------------------------
    // レンダリング関連メッセージコード (03xx)
    // -------------------------------------------------------------------------
    /**
     * ウィンドウが見つからない場合のメッセージコードです。
     */
    public static final String WINDOW_NOT_FOUND = "EURM0300";

    /**
     * レンダリング時の値の設定に失敗した場合のメッセージコードです。
     */
    public static final String RENDER_MAPPING_FAILED = "EURM0302";

    /**
     * レンダリング時に実際の型が想定していた型と異なっていた場合のエラーコードです。
     */
    public static final String UNSUPPORTED_TYPE_ERROR = "EURM0303";

    /**
     * ViewPart のレンダリング時にルートコンポーネントが {@link ViewPartComponent} でなかった場合のエラーコードです。
     */
    public static final String REQUIRED_VIEWPART_ERROR = "EJFC0304";

    // -------------------------------------------------------------------------
    // ウィンドウ管理関連メッセージコード (04xx)
    // -------------------------------------------------------------------------
    /**
     * ウィンドウオープン時のメッセージコードです。
     */
    public static final String OPEN_WINDOW = "IURM0401";

    /**
     * ウィンドウ初期化時のメッセージコードです。
     */
    public static final String INIT_WINDOW = "IURM0402";

    /**
     * ウィンドウクローズ時のメッセージコードです。
     */
    public static final String CLOSE_WINDOW = "IURM0403";

    // -------------------------------------------------------------------------
    // システムデバッグログ関連メッセージコード (99xx)
    // -------------------------------------------------------------------------
    /**
     * {@link StandAloneUrumaStarter} 初期化時のメッセージコードです。<br />
     */
    public static final String STAND_ALONE_URUMA_STARTER_INIT = "DURM9900";

    /**
     * イメージバンドルファイル読み込み時のメッセージコードです。<br />
     */
    public static final String LOADING_IMAGE_BUNDLE = "DURM9901";

    /**
     * {@link StandAloneUrumaStarter} 終了時のメッセージコードです。<br />
     */
    public static final String STAND_ALONE_URUMA_STARTER_STOP = "DURM9902";
}
