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

import java.io.InputStream;
import java.net.URL;

/**
 * リソースを処理するクラスのためのインターフェースです。<br />
 * 
 * @author y-komori
 * @see ResourceTraverser
 */
public interface ResourceHandler {
    /**
     * リソースを処理します。<br />
     * 本メソッドは、 {@link ResourceTraverser#traverse(URL, URL, ResourceHandler)}
     * メソッドを呼び出したとき、発見した各々のリソースに対して呼び出されます。<br />
     * 
     * @param rootPath
     *            クラスパス上のルートを表す絶対パス
     * @param path
     *            クラスパス上のリソースのパス
     * @param is
     *            リソースを読み込むための {@link InputStream}
     */
    public void handle(String rootPath, String path, InputStream is);
}
