/*
 * Copyright 2004-2006 the Seasar Foundation and the Others.
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
package org.seasar.jface.binding;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;


/**
 * アクションクラスに関する詳細情報を保持するインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ActionDesc {
    /**
     * {@link org.seasar.jface.annotation.InitializeMethod}
     * アノテーションが付加されたメソッドを取得します。<br />
     * 
     * @return {@link org.seasar.jface.annotation.InitializeMethod}
     *         アノテーションが付加されたメソッド
     */
    public Method getInitializeMethod();

    /**
     * {@link org.seasar.jface.annotation.InitializeMethod}
     * アノテーションが付加されたメソッドを実行します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     */
    public void invokeInitializeMethod(Object target);

    /**
     * {@link org.seasar.jface.annotation.ImportValue}
     * アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getImportFields();

    /**
     * {@link org.seasar.jface.annotation.ExportValue}
     * アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getExportFields();
    
    /**
     * {@link org.seasar.jface.annotation.ReturnValue}
     * アノテーションが付加されたフィールドを取得します。<br />
     * 
     * @return {@link Field}
     */
    public Field getReturnField();
    
    /**
     * {@link org.seasar.jface.annotation.ReturnValue}
     * アノテーションが付加されたフィールドの値を取得します。<br />
     * 
     * @param target
     *            ターゲットオブジェクト
     * @return フィールドの値
     */
    public Object getReturnValue(Object target);
    
    public Iterator<EventListenerDef> eventListenerDefIterator();
}
