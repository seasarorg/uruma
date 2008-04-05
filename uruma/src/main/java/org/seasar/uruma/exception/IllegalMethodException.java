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

import java.lang.reflect.Method;

/**
 * パートアクションクラスにおけるメソッドバインディング解析時にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class IllegalMethodException extends UrumaRuntimeException {

    private static final long serialVersionUID = 746915588403227938L;

    /**
     * {@link IllegalMethodException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param clazz
     *            対象クラス
     * @param method
     *            対象メソッド
     */
    public IllegalMethodException(final String messageCode,
            final Class<?> clazz, final Method method) {
        super(messageCode, new Object[] { clazz.getName(), method });
    }
}
