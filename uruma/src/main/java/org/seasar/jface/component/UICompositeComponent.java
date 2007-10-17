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
package org.seasar.jface.component;

/**
 * 子を持ち、レイアウト指定できる画面要素を表すインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface UICompositeComponent extends UIContainer {
    /**
     * レイアウトデータオブジェクトを取得します。<br />
     * 
     * @return レイアウトデータオブジェクト
     */
    public LayoutInfo getLayoutInfo();

    /**
     * レイアウトデータオブジェクトを設定します。<br />
     * 
     * @param layoutInfo
     *            レイアウトデータオブジェクト
     */
    public void setLayoutInfo(LayoutInfo layoutInfo);

    /**
     * 一括設定属性オブジェクトを設定します。<br />
     * 
     * @param commonAttributes
     *            一括設定属性オブジェクト
     */
    public void setCommonAttributes(CommonAttributes commonAttributes);

    /**
     * 一括設定属性オブジェクトを取得します。<br />
     * 
     * @return 一括設定属性オブジェクト
     */
    public CommonAttributes getCommonAttributes();
}
