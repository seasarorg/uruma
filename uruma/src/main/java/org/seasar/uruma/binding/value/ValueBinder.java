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
package org.seasar.uruma.binding.value;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.component.UIComponent;

/**
 * ウィジットクラス毎のバインディングを行うためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface ValueBinder {
    /**
     * ウィジットの値をフォームへ設定します。<br />
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     * @param uiComp
     *            コンポーネント
     */
    public void importValue(Object widget, Object formObj,
            PropertyDesc propDesc, UIComponent uiComp);

    /**
     * フォームの値をウィジットへ設定します。<br />
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     * @param uiComp
     *            コンポーネント
     */
    public void exportValue(Object widget, Object formObj,
            PropertyDesc propDesc, UIComponent uiComp);

    /**
     * ウィジットで選択されているオブジェクトをフォームへ設定します。<br />
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     * @param uiComp
     *            コンポーネント
     */
    public void importSelection(Object widget, Object formObj,
            PropertyDesc propDesc, UIComponent uiComp);

    /**
     * フォームの持つオブジェクトをウィジットの選択状態として設定します。<br />
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     * @param uiComp
     *            コンポーネント
     */
    public void exportSelection(Object widget, Object formObj,
            PropertyDesc propDesc, UIComponent uiComp);

    /**
     * 対応するウィジットの {@link Class} オブジェクトを返します。<br />
     * 
     * @return 対応するウィジットの {@link Class} オブジェクト
     */
    public Class<?> getWidgetType();
}
