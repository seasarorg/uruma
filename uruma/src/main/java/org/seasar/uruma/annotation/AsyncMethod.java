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
package org.seasar.uruma.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * UI スレッドとは非同期に実行するメソッドを指定するためのアノテーションです。<br />
 * メソッドバインディング対象のメソッドに本アノテーションを指定すると、そのメソッドを Uruma
 * が呼び出す際、UIスレッドとは異なるスレッドで非同期に呼び出します。
 * 主に時間のかかる処理を実行するメソッドに対して指定すると、メソッドを実行している間にも画面を操作することができます。<br />
 * 
 * @author y-komori
 */
@Retention(RetentionPolicy.RUNTIME)
@Target( { ElementType.METHOD })
public @interface AsyncMethod {
    /**
     * タスク名称を表すプロパティ名を指定します。<br />
     * 
     * @return プロパティ名
     */
    String nameProperty() default "";

    /**
     * キャンセル可能なタスクかどうかを指定します。<br />
     * 
     * @return <code>true</code> の場合、キャンセル可能。そうでない場合不可能。
     */
    boolean cancelable() default true;
}
