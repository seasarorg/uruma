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

import org.seasar.uruma.core.UrumaMessageCodes;

/**
 * 未サポートのクラスを処理しようとしたときにスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class UnsupportedClassException extends UrumaRuntimeException {

    private static final long serialVersionUID = 3494975724910432898L;

    /**
     * {@link UnsupportedClassException} を構築します。<br />
     * 
     * @param clazz
     *            対象 {@link Class} オブジェクト
     * @see UrumaMessageCodes#UNSUPPORTED_CLASS
     */
    public UnsupportedClassException(final Class<?> clazz) {
        super(UrumaMessageCodes.UNSUPPORTED_CLASS, clazz.getName());
    }

    /**
     * {@link UnsupportedClassException} を構築します。<br />
     * 
     * @param clazz
     *            対象 {@link Class} オブジェクト
     * @param actualClass
     *            本来あるべきクラスの {@link Class} オブジェクト
     * @see UrumaMessageCodes#TYPE_MISS_MATCH
     */
    public UnsupportedClassException(final Class<?> clazz,
            final Class<?> actualClass) {
        super(UrumaMessageCodes.TYPE_MISS_MATCH, clazz.getName(), actualClass
                .getName());
    }
}
