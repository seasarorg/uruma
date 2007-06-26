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
package org.seasar.jface.exception;

import java.lang.reflect.Method;

/**
 * メソッドの動的呼び出しに失敗したときにスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class MethodInvocationException extends S2JFaceRuntimeException {
    private static final long serialVersionUID = 1788438759154758912L;

    private static final String MESSAGE_CODE = "WJFC0214";

    /**
     * {@link MethodInvocationException} を構築します。<br />
     * 
     * @param targetMethod
     *            呼び出しに失敗した {@link Method} オブジェクト
     * @param cause
     *            原因となった例外オブジェクト
     */
    public MethodInvocationException(final Method targetMethod,
            final Throwable cause) {
        super(MESSAGE_CODE, cause, targetMethod.getDeclaringClass().getName(),
                targetMethod.getName());
    }
}
