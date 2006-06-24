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

import java.util.Collection;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;
import org.seasar.jface.WindowContext;
import org.seasar.jface.renderer.Renderer;

/**
 * 画面上のコンポーネントに関する情報を保持するオブジェクトためのインターフェースです。
 * 
 * @author y-komori
 * 
 */
public interface UIComponent extends Iterable<UIComponent> {
    /**
     * コンポーネントID を設定します。</br>
     * 
     * @param id
     *            コンポーネントID
     */
    public void setId(String id);

    /**
     * コンポーネントID を取得します。</br>
     * 
     * @return コンポーネントID
     */
    public String getId();

    /**
     * 親コンポーネントをセットします。</br>
     * 
     * @param parent
     *            親コンポーネント
     */
    public void setParent(UIComponent parent);

    /**
     * 親コンポーネントを取得します。</br>
     * 
     * @return 親コンポーネント
     */
    public UIComponent getParent();

    /**
     * 子コンポーネントを追加します。</br>
     * 
     * @param child
     *            子コンポーネント
     */
    public void addChild(UIComponent child);

    /**
     * 子コンポーネントのコレクションを取得します。</br>
     * 
     * @return 子コンポーネントのコレクション
     */
    public Collection<UIComponent> getChildren();

    /**
     * 指定された id を持つ<code>UIComponent</code>を探して返します。</br>
     * 検索は子コンポーネントに対して再帰的に行います。
     * 
     * @param id
     *            コンポーネントのID
     * @return 見つかった<code>UIComponent</code>。見つからなかった場合は <code>null</code>。
     */
    public UIComponent find(String id);

    /**
     * レンダラをセットします。</br>
     * 
     * @param renderer
     *            レンダラオブジェクト
     * @see Renderer
     */
    public void setRenderer(Renderer renderer);

    /**
     * レンダラオブジェクトを取得します。</br>
     * 
     * @return レンダラオブジェクト
     */
    public Renderer getRenderer();

    /**
     * レンダラ名称を設定します。</br>
     * 
     * @param type
     *            レンダラ名称
     */
    public void setRendererType(String type);

    /**
     * レンダラ名称を取得します。</br>
     * 
     * @return レンダラ名称
     */
    public String getRendererType();

    /**
     * ウィジットを取得します。</br>
     * 
     * @return ウィジット
     */
    public Widget getWidget();

    /**
     * 基準パスを設定します。</br>
     * <p>
     * 基準パスとは、レンダラが画像等リソースを取得する際に基準として使用するファイルシステム上のパスです。</br>
     * 通常は、画面定義XMLの存在するパスを基準パスとして使用します。</br>
     * </p>
     * 
     * 
     * @param basePath
     *            基準パス
     */
    public void setBasePath(String basePath);

    /**
     * 基準パスを取得します。</br>
     * 
     * @return 基準パス
     */
    public String getBasePath();

    /**
     * 設定されたレンダラを利用して、レンダリングを行います。</br>
     * 
     * @param parent
     *            親コンポジット
     * @param context
     *            <code>WindowContext</code> オブジェクト
     * @see WindowContext
     */
    public void render(Composite parent, WindowContext context);

    /**
     * プロパティを追加します。</br>
     * <p>
     * 既に同名のプロパティが存在する場合、追加したプロパティに置き換えられます。
     * </p>
     * 
     * @param property
     *            プロパティ
     */
    public void addProperty(Property property);

    /**
     * プロパティを取得します。</br>
     * <p>
     * この際、inheritance 属性が <code>NONE</code>、<code>CHILD</code>、<code>DECENDANT</code>
     * のものだけが取得できます。
     * </p>
     * 
     * @param name
     *            プロパティ名
     * @return プロパティ
     * @see Inheritance
     */
    public Property getProperty(String name);

    /**
     * プロパティの持つ値を文字列として取得します。</br>
     * <p>
     * この際、inheritance 属性が <code>NONE</code>、<code>CHILD</code>、<code>DECENDANT</code>
     * のものだけが取得できます。
     * </p>
     * 
     * @param name
     *            プロパティ名
     * @return プロパティの値
     * @see Inheritance
     */
    public String getPropertyValue(String name);

    /**
     * すべてのプロパティをコレクションとして取得します。</br>
     * <p>
     * 本メソッドでは、inheritance 属性に関係なくすべてのプロパティを取得することができます。
     * </p>
     * 
     * @return <code>Property</code> オブジェクトの <code>Collection</code>
     */
    public Collection<Property> getProperties();

}
