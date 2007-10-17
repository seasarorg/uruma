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
 * 画面定義テンプレートを表すインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface Template extends UIElement {

    /**
     * ルートコンポーネントを取得します。
     * 
     * @return ルートコンポーネント
     */
    public UICompositeComponent getRootComponent();

    /**
     * ルートコンポーネントを設定します。<br />
     * 
     * @param rootComponent
     */
    public void setRootComponent(UICompositeComponent rootComponent);

    /**
     * 継承元のパスを取得します。<br />
     * 
     * @return 継承元パス
     */
    public String getExtends();

    /**
     * 継承元パスを設定します。<br />
     * 
     * @param extendsPath
     *            継承元パス
     */
    public void setExtends(String extendsPath);
}
