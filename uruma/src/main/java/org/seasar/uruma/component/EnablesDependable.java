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

/**
 * 他コンポーネントの選択状態によってイネーブル状態が変化するコンポーネントのためのインターフェースです。<br />
 * 
 * @author bskuroneko
 */
public interface EnablesDependable {
    /**
     * 依存先コンポーネントの ID を返します。<br />
     * 
     * @return 依存先コンポーネントの ID
     */
    public String getEnablesDependingId();

    /**
     * 依存方法を返します。<br />
     * 
     * @return 依存方法
     */
    public String getEnablesFor();
}
