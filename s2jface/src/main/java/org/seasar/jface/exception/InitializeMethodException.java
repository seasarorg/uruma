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
package org.seasar.jface.exception;

import java.lang.reflect.Method;

/**
 * @author y-komori
 */
public class InitializeMethodException extends S2JFaceRuntimeException {

    private static final long serialVersionUID = 7933888864135281244L;

    public static final String DUPLICATE = "EJFC0204";

    public static final String INVALID = "EJFC0205";

    public static final String INVOKE = "EJFC0206";

    public InitializeMethodException(String messageCode, Class clazz,
            Method method) {
        super(messageCode, new Object[] { clazz.getName(), method });
    }

    public InitializeMethodException(Throwable cause, Class clazz,
            Method method, Object target) {
        super(INVOKE, cause, new Object[] { clazz.getName(), method, target });
    }
}
