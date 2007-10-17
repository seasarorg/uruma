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

import java.lang.reflect.Field;

import org.seasar.uruma.annotation.ReturnValue;

/**
 * 戻り値フィールドの解析時にスローされる例外です。<br />
 * 
 * @author bskuroneko
 */
public class ReturnFieldException extends UrumaRuntimeException {

    private static final long serialVersionUID = -7245771988882912561L;

    /**
     * {@link ReturnValue} アノテーションが付加されたフィールドが複数存在することを示すメッセージコードです。<br />
     */

    public static final String DUPLICATE = "EURM0209";

    /**
     * {@link ReturnFieldException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param clazz
     *            対象クラス
     * @param field
     *            対象フィールド
     */
    public ReturnFieldException(final String messageCode, final Class<?> clazz,
            final Field field) {
        super(messageCode, new Object[] { clazz.getName(), field });
    }

}
