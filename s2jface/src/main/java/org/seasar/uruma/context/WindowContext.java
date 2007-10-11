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

/**
 * ウィンドウやダイアログに関する情報を保持するクラスのためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface WindowContext {
    /**
     * ウィンドウの名称を取得します。<br />
     * 
     * @return ウィンドウの名称
     */
    public String getWindowName();

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

}
