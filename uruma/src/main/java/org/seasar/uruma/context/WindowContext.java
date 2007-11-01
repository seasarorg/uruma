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
package org.seasar.uruma.context;

import java.util.List;

import org.seasar.uruma.binding.enables.EnablesDependingDef;

/**
 * ウィンドウやダイアログに関する情報を保持するクラスのためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface WindowContext extends WidgetHolder {
    /**
     * ウィンドウの名称を取得します。<br />
     * 
     * @return ウィンドウの名称
     */
    public String getName();

    /**
     * {@link WindowContext} が保持する {@link PartContext} のリストを返します。<br />
     * {@link PartContext} を1つも保持しない場合、空のリストを返します。
     * 
     * @return {@link PartContext} のリスト
     */
    public List<PartContext> getPartContextList();

    /**
     * {@link PartContext} を返します。<br />
     * {@link PartContext} が複数登録されている場合、最初に登録された {@link PartContext} を返します。<br />
     * {@link PartContext} が登録されていない場合、<code>null</code> を返します。
     * 
     * @return {@link PartContext} オブジェクト
     */
    public PartContext getPartContext();

    /**
     * <code>partName</code> で指定された名称を持つ {@link PartContext} を取得します。<br />
     * 
     * @param partName
     *            パート名称
     * @return {@link PartContext} オブジェクト。見つからない場合は、<code>null</code>。
     */
    public PartContext getPartContext(String partName);

    /**
     * 親 {@link ApplicationContext} を返します。<br />
     * 
     * @return {@link ApplicationContext} オブジェクト
     */
    public ApplicationContext getApplicationContext();

    /**
     * {@link WindowContext} 本体および配下のすべての {@link PartContext} から、<code>handleId</code>
     * にマッチする {@link WidgetHandle} を検索して返します。<br />
     * 
     * @param handleId
     *            ハンドルID
     * @return 見つかった {@link WidgetHandle} のリスト
     */
    public List<WidgetHandle> findWidgetHandles(String handleId);

    /**
     * {@link EnablesDependingDef} を追加します。<br />
     * 
     * @param enablesDependingDef
     *            {@link EnablesDependingDef} オブジェクト
     */
    public void addEnablesDependingDef(EnablesDependingDef enablesDependingDef);

    /**
     * {@link EnablesDependingDef} のリストを返します。<br />
     * 
     * @return {@link EnablesDependingDef} のリスト
     */
    public List<EnablesDependingDef> getEnablesDependingDefList();
}