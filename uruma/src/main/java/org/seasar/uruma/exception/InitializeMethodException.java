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

import java.lang.reflect.Method;

import org.seasar.uruma.core.UrumaMessageCodes;

/**
 * パートアクションクラスにおけるイニシャライズメソッド解析時にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class InitializeMethodException extends UrumaRuntimeException {

    private static final long serialVersionUID = 1843113708082422291L;

    /**
     * {@link InitializeMethodException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param clazz
     *            対象クラス
     * @param method
     *            対象メソッド
     */
    public InitializeMethodException(final String messageCode,
            final Class<?> clazz, final Method method) {
        super(messageCode, new Object[] { clazz.getName(), method });
    }

    /**
     * {@link InitializeMethodException} を構築します。<br />
     * 
     * @param cause
     *            ネストされた例外オブジェクト
     * @param clazz
     *            対象クラス
     * @param method
     *            対象メソッド
     * @param target
     *            ターゲットオブジェクト
     */
    public InitializeMethodException(final Throwable cause,
            final Class<?> clazz, final Method method, final Object target) {
        super(UrumaMessageCodes.EXCEPTION_ON_INVOKING_INITIALIZE_METHOD, cause,
                new Object[] { clazz.getName(), method, target });
    }
}
