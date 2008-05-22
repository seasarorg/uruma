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
package org.seasar.uruma.component.spec;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * GUI コンポーネントの属性に関する仕様を指定するためのアノテーションです。<br />
 * 
 * @author y-komori
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.FIELD, ElementType.METHOD })
public @interface SpecValue {
    /**
     * @return 省略時のデフォルト値
     */
    String defaultValue();

    /**
     * @return 値の下限値
     */
    String lowerLimit();

    /**
     * @return 値の上限値
     */
    String higherLimit();

    /**
     * @return <code>true</code> の場合、必須属性。
     */
    boolean mandatory();

    /**
     * @return CHOICE 型属性の場合の選択肢。
     */
    String[] choices();
}
