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
package org.seasar.uruma.desc;

import java.lang.reflect.Field;
import java.util.List;

import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.ImportValue;

/**
 * フォームクラスのメタデータを扱うためのインターフェースです。<br />
 * フォームクラスは、ウィンドウパートに対する入出力情報を保持するためのクラスで、POJO として実現されます。<br />
 * 
 * @author y-komori
 */
public interface FormDesc {
    /**
     * {@link ImportValue} アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getImportValueFields();

    /**
     * {@link ExportValue} アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getExportValueFields();

    /**
     * {@link ImportSelection} アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getImportSelectionFields();

    /**
     * {@link ExportSelection} アノテーションが付加されたフィールドのリストを取得します。<br />
     * 
     * @return {@link Field}のリスト
     */
    public List<Field> getExportSelectionFields();

}
