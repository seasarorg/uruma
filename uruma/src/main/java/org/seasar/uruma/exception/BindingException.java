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

    /**
     * {@link BindingException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param arg
     *            引数の配列
     */
    public BindingException(final String messageCode, final String... arg) {
        super(messageCode, (Object[]) (arg));
    }
}
