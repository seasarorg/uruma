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

/**
 * レンダリング処理中に異常が発生した際にスローされるクラスです。</br>
 * 
 * @author y-komori
 */
public class RenderException extends S2JFaceRuntimeException {
    private static final long serialVersionUID = 2652211902877860349L;

    public static final String IACTION = "EJFC0301";

    public static final String MAPPING_ERORR = "EJFC0302";

    public static final String TYPE_ERROR = "EJFC0303";

    public RenderException(String messageCode, Object... args) {
        super(messageCode, args);
    }

    public RenderException(String messageCode, Throwable cause, Object... args) {
        super(messageCode, cause, args);
    }
}
