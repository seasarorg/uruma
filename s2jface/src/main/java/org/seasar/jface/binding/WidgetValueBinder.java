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

import org.eclipse.swt.widgets.Widget;

/**
 * {@link org.eclipse.swt.widgets.Widget} 毎の ValueBinding を実現するためのインターフェースです。<br />
 * <p>
 * 本インターフェースの実装クラスは、{@link ValueBinder} によって利用されます。
 * </p>
 * 
 * @author y-komori
 */
public interface WidgetValueBinder {
    /**
     * ウィジットからフィールドへ値を設定します。<br />
     * 
     * @param src
     *            設定元 {@link Widget}
     * @param destObject
     *            設定先オブジェクト
     * @param destField
     *            設定先 {@link Field}
     */
    public void importValue(Widget src, Object destObject, Field destField);

    /**
     * フィールドからウィジットへ値を設定します。<br />
     * 
     * @param srcObject
     *            設定元オブジェクト
     * @param srcField
     *            設定元 {@link Field}
     * @param dest
     *            設定先 {@link Widget}
     */
    public void exportValue(Object srcObject, Field srcField, Widget dest);

    /**
     * 対応するウィジットの型を返します。<br />
     * 
     * @return ウィジットの型
     */
    public Class<? extends Widget> getWidgetType();
}
