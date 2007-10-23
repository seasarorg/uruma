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
package org.seasar.uruma.binding.value;

import java.lang.reflect.Field;
import java.util.List;

import org.seasar.uruma.desc.FormDesc;

/**
 * バインドの実行を行うためのインターフェースです。<br />
 * ウィジットのクラス毎に対する実際のバインド処理は、設定された {@link ValueBinder} に委譲することで実現します。<br />
 * 
 * @author y-komori
 */
public interface BindingCommand {

    /**
     * <code>widget</code> と <code>formObj</code> の間でバインド処理を行います。<br />
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param formField
     *            フォーム側フィールド
     */
    public void doBind(Object widget, Object formObj, Field formField);

    /**
     * <code>field</code> に対応する id を取得します。<br />
     * フィールドにアノテーションが設定されていれば、その id 属性を返し、アノテーションが設定されていなければ、フィールFO名称を id
     * として返します。<br />
     * 
     * @param field
     *            id を取得するフィールド
     * @return id。
     */
    public String getId(Field field);

    /**
     * {@link FormDesc} からバインド対象のフィールドを取得します。<br />
     * 
     * @param desc
     *            {@link FormDesc} オブジェクト
     * @return バインド対象のフィールド
     */
    public List<Field> getTargetFields(FormDesc desc);
}
