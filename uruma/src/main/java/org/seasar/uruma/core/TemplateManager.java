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

import java.util.List;

import org.seasar.uruma.component.Template;
import org.seasar.uruma.component.UIComponentContainer;

/**
 * 画面定義テンプレートを管理するためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface TemplateManager {
    /**
     * 指定されたパスの画面定義 XML を読み込み、{@link Template} オブジェクトを返します。<br />
     * 
     * @param path
     *      画面定義 XML のパス
     * @return {@link Template} オブジェクト
     */
    public Template getTemplate(final String path);

    /**
     * 指定された id を持つ {@link Template} オブジェクトを返します。<br /> 事前に {@link
     * #loadTemplates(List)} メソッドでロードしたテンプレートが対象です。<br />
     * 
     * @param id
     *      テンプレート ID
     * @return {@link Template} オブジェクト
     */
    public Template getTemplateById(String id);

    /**
     * 指定されたパスの画面定義 XML を一括読み込みします。<br />
     * 
     * @param pathList
     *      画面定義 XML のパスリスト
     */
    public void loadTemplates(List<String> pathList);

    /**
     * 指定されたタイプのルートコンポーネントを持つ {@link Template} のリストを返します。<br />
     * 
     * @param componentClass
     *      ルートコンポーネントクラス
     * @return {@link Template} のリスト
     */
    public List<Template> getTemplates(
            Class<? extends UIComponentContainer> componentClass);

    /**
     * キャッシュしている内容をクリアします。
     */
    public void clear();

    /**
     * 指定された idのテンプレートキャッシュの内容をクリアします。<br />
     * 
     * @param id
     *      テンプレート ID
     */
    public void remove(final String id);
}
