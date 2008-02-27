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

import org.seasar.uruma.core.UrumaMessageCodes;

/**
 * ユーザアプリケーションのメソッド実行中に例外が発生したときにスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class MethodInvocationException extends UrumaRuntimeException {

    private static final long serialVersionUID = -3759589925878230700L;

    /**
     * {@link MethodInvocationException} を構築します。<br />
     * 
     * @param ex
     *            原因となった {@link Throwable} オブジェクト
     */
    public MethodInvocationException(final Throwable ex) {
        super(UrumaMessageCodes.EXCEPTION_OCCURED_WITH_REASON, ex.getCause(),
                ex.getCause().getMessage());
    }
}
