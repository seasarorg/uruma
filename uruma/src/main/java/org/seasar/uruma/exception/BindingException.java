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

import java.lang.reflect.Field;

/**
 * バインディング処理の失敗時にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class BindingException extends UrumaRuntimeException {

    private static final long serialVersionUID = 9030823268022986419L;

    /**
     * サポートされていない型のウィジットに対してバインディングしようとした場合のメッセージコードです。
     */
    public static final String WIDGET_NOT_SUPPORTED = "EURM0207";

    /**
     * アノテートされたフィールドに対応するウィジットが存在しない場合のメッセージコードです。
     */
    public static final String WIDGET_NOT_FOUND = "EURM0208";

    /**
     * バインド先とバインド元の型が一致しない場合のメッセージコードです。
     */
    public static final String CLASS_NOT_MUTCH = "EURM0214";

    /**
     * {@link BindingException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param id
     *            ID
     * @param clazz
     *            クラス
     * @param field
     *            フィールド
     */
    public BindingException(final String messageCode, final String id,
            final Class<?> clazz, final Field field) {
        super(messageCode,
                new Object[] { id, clazz.getName(), field.getName() });
    }
}
