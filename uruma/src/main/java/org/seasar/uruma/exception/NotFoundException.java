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
package org.seasar.uruma.exception;

import org.seasar.uruma.binding.value.ValueBinder;
import org.seasar.uruma.component.UIComponent;

/**
 * 検索対象が見つからなかった場合にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class NotFoundException extends UrumaRuntimeException {

    private static final long serialVersionUID = 8319248769348685258L;

    /**
     * 検索対象がレイアウトの場合のメッセージコードです。
     */
    public static final String LAYOUT = "EURM0101";

    /**
     * 検索対象がレイアウトデータの場合のメッセージコードです。
     */
    public static final String LAYOUT_DATA = "EURM0102";

    /**
     * 検索対象がレンダラの場合のメッセージコードです。
     */
    public static final String RENDERER = "EURM0103";

    /**
     * 検索対象がイベントリスナの場合のメッセージコードです。
     */
    public static final String SWT_EVENT_LISTENER = "EURM0104";

    /**
     * 検索対象がウィンドウの場合のメッセージコードです。
     */
    public static final String WINDOW = "EURM0300";

    /**
     * 検索対象が継承先コンポーネントの場合のメッセージコードです。
     */
    public static final String EXTEND_TARGET_COMPONENT = "EURM0107";

    /**
     * 検索対象が継承先プロパティの場合のメッセージコードです。
     */
    public static final String EXTEND_TARGET_PROPERTY = "EURM0108";

    /**
     * 検索対象が {@link UIComponent} の場合のメッセージコードです。
     */
    public static final String UICOMPONENT = "EURM0109";

    /**
     * 検索対象が {@link ValueBinder} の場合のメッセージコードです。
     */
    public static final String VALUE_BINDER = "EURM0111";

    /**
     * 検索対象が EnablesDepending ターゲットの場合のメッセージコードです。
     */
    public static final String ENABLES_DEPENDING_TARGET = "EURM0220";

    /**
     * {@link NotFoundException} を構築します。<br />
     * 
     * @param code
     *            メッセージコード
     * @param name
     *            名称
     */
    public NotFoundException(final String code, final String name) {
        super(code, name);
    }
}
