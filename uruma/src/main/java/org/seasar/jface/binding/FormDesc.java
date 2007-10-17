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
package org.seasar.jface.binding;

import java.lang.reflect.Field;
import java.util.List;

/**
 * フォームクラスに関する詳細情報を保持するインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface FormDesc {
    /**
     * {@link org.seasar.jface.annotation.ImportValue}
     * アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getImportValueFields();

    /**
     * {@link org.seasar.jface.annotation.ExportValue}
     * アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getExportValueFields();

    /**
     * {@link org.seasar.jface.annotation.ImportSelection}
     * アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getImportSelectionFields();

    /**
     * {@link org.seasar.jface.annotation.ExportSelection}
     * アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getExportSelectionFields();
}
