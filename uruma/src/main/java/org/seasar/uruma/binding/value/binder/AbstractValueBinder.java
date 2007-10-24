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
package org.seasar.uruma.binding.value.binder;

import org.seasar.framework.beans.PropertyDesc;
import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.util.AssertionUtil;

/**
 * {@link ValueBinder} のための基底クラスです。<br />
 * 
 * @param <WIDGET_TYPE>
 *            対応するウィジットの型
 * @author y-komori
 */
public abstract class AbstractValueBinder<WIDGET_TYPE> implements ValueBinder {
    private Class<WIDGET_TYPE> widgetType;

    /**
     * {@link AbstractValueBinder} を構築します。<br />
     * 
     * @param widgetType
     *            ウィジットの型
     */
    public AbstractValueBinder(final Class<WIDGET_TYPE> widgetType) {
        AssertionUtil.assertNotNull("widgetType", widgetType);
        this.widgetType = widgetType;
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#importValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void importValue(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doImportValue(getWidgetType().cast(widget), formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#exportValue(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void exportValue(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doExportValue(getWidgetType().cast(widget), formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#importSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void importSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doImportSelection(getWidgetType().cast(widget), formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#exportSelection(java.lang.Object,
     *      java.lang.Object, org.seasar.framework.beans.PropertyDesc)
     */
    public void exportSelection(final Object widget, final Object formObj,
            final PropertyDesc propDesc) {
        doExportSelection(getWidgetType().cast(widget), formObj, propDesc);
    }

    /*
     * @see org.seasar.uruma.binding.value.ValueBinder#getWidgetType()
     */
    public Class<WIDGET_TYPE> getWidgetType() {
        return this.widgetType;
    }

    /**
     * ウィジットの値をフォームへ設定します。<br />
     * 本メソッドをサブクラスでオーバーライドしてください。<br />
     * デフォルトでは何も行いません。
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     */
    public void doImportValue(final WIDGET_TYPE widget, final Object formObj,
            final PropertyDesc propDesc) {

    }

    /**
     * フォームの値をウィジットへ設定します。<br />
     * 本メソッドをサブクラスでオーバーライドしてください。<br />
     * デフォルトでは何も行いません。
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     */
    public void doExportValue(final WIDGET_TYPE widget, final Object formObj,
            final PropertyDesc propDesc) {

    }

    /**
     * ウィジットで選択されているオブジェクトをフォームへ設定します。<br />
     * 本メソッドをサブクラスでオーバーライドしてください。<br />
     * デフォルトでは何も行いません。
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     */
    public void doImportSelection(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc) {
    }

    /**
     * フォームの持つオブジェクトをウィジットの選択状態として設定します。<br />
     * 本メソッドをサブクラスでオーバーライドしてください。<br />
     * デフォルトでは何も行いません。
     * 
     * @param widget
     *            ウィジット側オブジェクト
     * @param formObj
     *            フォーム側オブジェクト
     * @param propDesc
     *            フォーム側のプロパティを表す {@link PropertyDesc} オブジェクト
     */
    public void doExportSelection(final WIDGET_TYPE widget,
            final Object formObj, final PropertyDesc propDesc) {

    }
}
