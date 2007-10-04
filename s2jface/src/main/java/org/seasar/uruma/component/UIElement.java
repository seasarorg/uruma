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
package org.seasar.uruma.component;

/**
 * 画面定義データの要素を表すインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface UIElement {
    // TODO 参照修正
    /**
     * 画面定義データファイルのパスを設定します。<br />
     * <code>basePath</code> は
     * {@link org.seasar.jface.component.factory.S2JFaceTagHandler} によって設定されます。
     * 
     * @param basePath
     *            画面定義データファイルのパス
     */
    public void setBasePath(String basePath);

    /**
     * 画面定義データファイルのパスを取得します。<br />
     * 
     * @return 画面定義データファイルのパス
     */
    public String getBasePath();

    // TODO 参照修正
    /**
     * 画面定義データファイル中の要素の位置を設定します。<br />
     * <code>location</code> は
     * {@link org.seasar.jface.component.factory.S2JFaceTagHandler}
     * によって設定され、主にエラーが発生時の参照のために利用されます。<br/>
     * 
     * @param location
     *            画面定義データファイル中の要素の位置
     */
    public void setLocation(String location);

    /**
     * 画面定義データファイル中の要素の位置を取得します。<br />
     * 
     * @return 画面定義データファイル中の要素の位置
     */
    public String getLocation();
}
