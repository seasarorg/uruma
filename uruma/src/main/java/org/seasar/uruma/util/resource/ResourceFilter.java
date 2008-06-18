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

/**
 * リソースパスのためのフィルタです。<br />
 * 本インターフェースの実装クラスは、{@link ResourceTraverser} クラスの
 * {@link ResourceTraverser#traverse(java.net.URL, java.net.URL, ResourceHandler, ResourceFilter)}
 * メソッドに渡すことができます。<br />
 * 
 * @author y-komori
 */
public interface ResourceFilter {
    /**
     * 指定されたリソースパスがパス名リストに含まれる必要があるかどうかを判定します。<br />
     * 
     * @param path
     *            リソースパス
     * @return リストに含まれる必要がある場合は <code>true</code>、そうでない場合は <code>false</code>。
     */
    public boolean accept(String path);
}
