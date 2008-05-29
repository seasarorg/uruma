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
package org.seasar.uruma.rcp;

import java.util.List;
import java.util.ResourceBundle;

import org.eclipse.ui.IWorkbench;
import org.osgi.framework.Bundle;
import org.seasar.framework.container.S2Container;
import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.rcp.ViewPartComponent;
import org.seasar.uruma.component.rcp.WorkbenchComponent;
import org.seasar.uruma.context.WindowContext;
import org.seasar.uruma.rcp.binding.CommandRegistry;
import org.seasar.uruma.rcp.configuration.Extension;

/**
 * RCP 環境で Uruma が提供するサービスです。<br />
 * 
 * @author y-komori
 */
public interface UrumaService {
    /**
     * Uruma アプリケーションのバンドルを返します。<br />
     * 
     * @return {@link Bundle} オブジェクト
     */
    public Bundle getBundle();

    /**
     * 現在のプラグイン ID を返します。<br />
     * 
     * @return プラグイン ID
     */
    public String getPluginId();

    /**
     * 画面コンポーネントのIDをRCP上のIDに変換します。<br />
     * RCP上のIDは、画面コンポーネントのIDにサフィックスとしてプラグインIDを追加したものになります。<br />
     * <p>
     * 【例】プラグインID: <code>org.seasar.uruma.example</code>、コンポーネントID:
     * <code>button</code> の場合、本メソッドの戻り値は、
     * <code>org.seasar.uruma.example.button</code> となります。
     * </p>
     * 
     * @param id
     *      画面コンポーネントのID
     * @return RCP上のID
     */
    public String createRcpId(String id);

    /**
     * RCP上のIDから画面コンポーネントのIDを取得します。<br />
     * 
     * @param rcpId
     *      RCP上のID
     * @return 画面コンポーネントのID
     * @see #createRcpId(String)
     */
    public String getLocalId(String rcpId);

    /**
     * 指定されたパスの画面定義XMLを読み込み、{@link Template} オブジェクトを生成します。<br />
     * 
     * @param path
     *      画面定義XMLのパス
     * @return {@link Template} オブジェクト
     */
    public Template getTemplate(String path);

    /**
     * {@link IWorkbench} のインスタンスを返します。<br />
     * 
     * @return {@link IWorkbench} のインスタンス
     */
    public IWorkbench getWorkbench();

    /**
     * {@link WorkbenchComponent} を返します。<br />
     * 
     * @return {@link WorkbenchComponent} オブジェクト
     */
    public WorkbenchComponent getWorkbenchComponent();

    /**
     * {@link ViewPartComponent} のリストを返します。<br />
     * 
     * @return {@link ViewPartComponent} オブジェクト
     */
    public List<ViewPartComponent> getViewPartComponent();

    /**
     * 現在登録されている拡張ポイントのリストを返します。<br />
     * 
     * @return 拡張ポイントのリスト
     */
    public List<Extension> getExtensions();

    /**
     * 指定した名前の拡張ポイントを返します。<br />
     * 
     * @param point
     *      拡張ポイントの名称
     * @return 拡張ポイント。見つからなかった場合は <code>null</code>。
     */
    public Extension getExtension(String point);

    /**
     * ワークベンチウィンドウに対応する {@link WindowContext} を返します。<br />
     * 
     * @return {@link WindowContext}
     */
    public WindowContext getWorkbenchWindowContext();

    /**
     * {@link S2Container} のインスタンスを返します。<br />
     * 
     * @return {@link S2Container} のインスタンス
     */
    public S2Container getContainer();

    /**
     * Urumaアプリケーションバンドルのクラスローダを返します。<br />
     * 
     * @return Urumaアプリケーションバンドルのクラスローダ
     */
    public ClassLoader getAppClassLoader();

    /**
     * Urumaバンドルのクラスローダを返します。<br />
     * 
     * @return Urumaバンドルのクラスローダ
     */
    public ClassLoader getUrumaClassLoader();

    /**
     * コンテクストクラスローダを Uruma アプリケーションのクラスローダに切り替えます。<br />
     */
    public void switchToAppClassLoader();

    /**
     * コンテクストクラスローダを Uruma バンドルのクラスローダに切り替えます。<br />
     */
    public void switchToUrumaClassLoader();

    /**
     * イメージ定義を保持する {@link ResourceBundle} を返します。<br /> デフォルトは
     * <code>urumaImages.properties</code> の内容となります。
     * 
     * @return {@link ResourceBundle} オブジェクト
     */
    public ResourceBundle getImageBundle();

    /**
     * 直前に使用していたクラスローダに切り替えます。<br /> 直前に実行された {@link #switchToUrumaClassLoader()
     * } メソッドまたは、 {@link #switchToAppClassLoader()} メソッド実行前のクラスローダに切り替えます。
     */
    public void restoreClassLoader();

    /**
     * {@link CommandRegistry} のインスタンスを返します。<br />
     */
    public CommandRegistry getCommandRegistry();

    /**
     * Uruma アプリケーションのためのデフォルトコンテクスト ID を返します。<br /> デフォルト値は
     * <code>&lt;プラグインID&gt;.context</code> となります。<br />
     * 
     * @return デフォルトコンテクスト ID
     */
    public String getDefaultContextId();
}
