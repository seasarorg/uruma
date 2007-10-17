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
package org.seasar.uruma.context;

import org.seasar.uruma.component.UIComponent;

/**
 * ウィジットを保持するためのインターフェースです。<br />
 * 
 * @author y-komori
 */
public interface WidgetHandle {
    /**
     * ウィジットの ID を取得します。<br />
     * 
     * @return ID
     */
    public String getId();

    /**
     * {@link WidgetHandle} が保持するウィジットを取得します。<br />
     * 
     * @return ウィジットのインスタンス
     */
    public Object getWidget();

    /**
     * {@link WidgetHandle} が保持するウィジットを <code>T</code> にキャスト取得します。<br />
     * 
     * @param <T>
     *            キャストする型
     * @return ウィジットのインスタンス
     */
    public <T> T getCastWidget();

    /**
     * {@link WidgetHandle} が保持するウィジットの型を取得します。<br />
     * 
     * @return ウィジットの型
     */
    public Class<?> getWidgetClass();

    /**
     * {@link UIComponent} を取得します。<br />
     * 
     * @return {@link UIComponent} オブジェクト
     */
    public UIComponent getUiComponent();

    /**
     * 保持するウィジットの型が <code>clazz</code> のサブクラスであるかどうかをチェックします。<br />
     * 
     * @param clazz
     *            チェックする型の {@link Class} オブジェクト
     * @return サブクラスであれば <code>true</code>。そうでなければ <code>false</code>
     */
    public boolean instanceOf(Class<?> clazz);

    /**
     * ウィジットの ID を設定します。<br />
     */
    public void setId(String id);

    /**
     * ウィジットに対応する {@link UIComponent} を設定します。<br />
     */
    public void setUiComponent(UIComponent uiComponent);
}
