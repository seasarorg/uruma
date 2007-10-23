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

import java.util.List;

import org.seasar.framework.beans.PropertyDesc;
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
     * {@link ImportValue} アノテーションが付加されたプロパティのリストを取得します。<br />
     * 
     * @return {@link PropertyDesc} のリスト
     */
    public List<PropertyDesc> getImportValueProperties();

    /**
     * {@link ExportValue} アノテーションが付加されたプロパティのリストを取得します。<br />
     * 
     * @return {@link PropertyDesc} のリスト
     */
    public List<PropertyDesc> getExportValueProperties();

    /**
     * {@link ImportSelection} アノテーションが付加されたプロパティのリストを取得します。<br />
     * 
     * @return {@link PropertyDesc} のリスト
     */
    public List<PropertyDesc> getImportSelectionProperties();

    /**
     * {@link ExportSelection} アノテーションが付加されたプロパティのリストを取得します。<br />
     * 
     * @return {@link PropertyDesc} のリスト
     */
    public List<PropertyDesc> getExportSelectionProperties();
}
