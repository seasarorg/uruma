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
package org.seasar.uruma.component;

import java.net.URL;

import org.seasar.uruma.component.factory.UrumaTagHandler;

/**
 * 画面定義テンプレートの要素を表すインターフェースです。<br />
 * 
 * @author y-komori
 * @author $Author$
 * @version $Revision$ $Date$
 */
public interface UIElement extends UIElementVisitorAcceptor {
    /**
     * 画面定義テンプレートファイルの URL を設定します。<br />
     * 
     * @param url
     *        画面定義テンプレートファイルの URL
     */
    public void setURL(URL url);

    /**
     * 画面定義テンプレートファイルの URL を取得します。<br />
     * 
     * @return 画面定義テンプレートファイルの URL
     */
    public URL getURL();

    /**
     * 画面定義テンプレートファイルの親 URL を設定します。<br /> {@code parentUrl} は {@link UrumaTagHandler}
     * によって設定されます。<br />
     * この URL は画面定義テンプレート内で相対パスを使用する際の基点となります。<br />
     * 
     * @param parentUrl
     *        画面定義データファイルの親 URL
     */
    public void setParentURL(URL parentUrl);

    /**
     * 画面定義テンプレートファイルの親 URL を取得します。<br />
     * 
     * @return 画面定義データファイルの親 URL
     */
    public URL getParentURL();

    /**
     * 画面定義テンプレートファイル中の要素の位置を設定します。<br />
     * <code>location</code> は {@link UrumaTagHandler}
     * によって設定され、主にエラーが発生時の参照のために利用されます。<br/>
     * 
     * @param location
     *        画面定義テンプレートファイル中の要素の位置
     */
    public void setLocation(String location);

    /**
     * 画面定義テンプレートファイル中の要素の位置を取得します。<br />
     * 
     * @return 画面定義テンプレートファイル中の要素の位置
     */
    public String getLocation();
}
