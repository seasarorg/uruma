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

/**
 * 検索対象が見つからなかった場合にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class NotFoundException extends UrumaRuntimeException {

    private static final long serialVersionUID = 8319248769348685258L;

    /**
     * {@link NotFoundException} を構築します。<br />
     * 
     * @param code
     *            メッセージコード
     * @param cause
     *            原因となった例外
     * @param name
     *            名称
     */
    public NotFoundException(final String code, final Throwable cause,
            final String... name) {
        super(code, cause, (Object[]) name);
    }

    /**
     * {@link NotFoundException} を構築します。<br />
     * 
     * @param code
     *            メッセージコード
     * @param name
     *            名称
     */
    public NotFoundException(final String code, final String... name) {
        super(code, (Object[]) name);
    }
}
