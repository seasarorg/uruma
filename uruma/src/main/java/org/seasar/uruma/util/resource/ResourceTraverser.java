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
package org.seasar.uruma.util.resource;

import java.net.URL;

/**
 * リソースツリーをトラバースするクラスのためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ResourceTraverser {
    /**
     * リソースツリーをたどり、各リソースに対して {@link ResourceHandler} を呼び出します。<br />
     * <code>root</code> で指定された URL を起点として、リソースツリーを再帰的にたどります。<br />
     * 発見した各リソースに対して、
     * {@link ResourceHandler#handle(String, String, java.io.InputStream)}
     * メソッドを呼び出します。<br />
     * 
     * @param root
     *            クラスパス上のルート URL (<code>null</code> でもよい)
     * @param origin
     *            リソースをたどる際の起点 URL
     * @param handler
     *            {@link ResourceHandler} オブジェクト
     */
    public void traverse(URL root, URL origin, ResourceHandler handler);

    /**
     * リソースツリーをたどり、条件に一致するリソースに対して {@link ResourceHandler} を呼び出します。<br />
     * <code>root</code> で指定された URL を起点として、リソースツリーを再帰的にたどります。<br />
     * 発見した各リソースに対して、<code>filter</code> を適用し、マッチしたリソースに対して
     * {@link ResourceHandler#handle(String, String, java.io.InputStream)}
     * メソッドを呼び出します。<br />
     * 
     * @param root
     *            クラスパス上のルート URL (<code>null</code> でもよい)
     * @param origin
     *            リソースをたどる際の起点 URL
     * @param handler
     *            {@link ResourceHandler} オブジェクト
     * @param filter
     *            条件を指定するフィルタ
     * @see ResourceFilter
     */
    public void traverse(URL root, URL origin, ResourceHandler handler,
            ResourceFilter filter);

    /**
     * 対応するプロトコルを返します。<br />
     * 
     * @return 対応するプロトコル
     */
    public String getProtocol();
}
