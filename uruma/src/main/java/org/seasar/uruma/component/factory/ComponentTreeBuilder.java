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
package org.seasar.uruma.component.factory;

import java.net.URL;

import org.seasar.uruma.component.Template;

/**
 * 画面定義 XML ファイルを読み込み、コンポーネントツリーを生成するためのインタフェースです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public interface ComponentTreeBuilder {
    /**
     * 指定されたパスの画面定義XMLを読み込み、コンポーネントツリーを生成します。<br />
     * 
     * @param path
     *        画面定義 XML のパス
     * @return {@link Template} オブジェクト
     */
    public Template build(String path);

    /**
     * 指定された URL の画面定義XMLを読み込み、コンポーネントツリーを生成します。<br />
     * 
     * @param url
     *        画面定義 XML の URL
     * @return {@link Template} オブジェクト
     */
    public Template build(URL url);
}
