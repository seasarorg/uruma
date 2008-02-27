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
 * 画面定義 XML ファイルのパースに失敗した場合にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class ParseException extends UrumaRuntimeException implements
        UrumaMessageCodes {

    private static final long serialVersionUID = 7575786499116429810L;

    /**
     * {@link ParseException} を構築します。<br />
     * 
     * @param attrName
     *            見つからなかった属性名
     * @param className
     *            対象クラス名
     */
    public ParseException(final String attrName, final String className) {
        super(PROPERY_NOT_FOUND, attrName, className);
    }
}
