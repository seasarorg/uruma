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

import java.lang.reflect.Field;

/**
 * @author bskuroneko
 */
public class ArgumentFieldException extends S2JFaceRuntimeException {

    private static final long serialVersionUID = -150742105184209863L;
    
    public static final String DUPLICATE = "EJFC0213";

    public ArgumentFieldException(String messageCode, Class clazz, Field field) {
        super(messageCode, new Object[] { clazz.getName(), field });
    }

}
