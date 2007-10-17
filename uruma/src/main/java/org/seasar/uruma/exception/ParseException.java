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

/**
 * 画面定義 XML ファイルのパースに失敗した場合にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class ParseException extends UrumaRuntimeException {

    private static final long serialVersionUID = 7575786499116429810L;

    /**
     * 属性が見つからなかった場合のメッセージコードです。
     */
    public static final String PROPERTY_NOT_FOUND = "EJFC0110";

    /**
     * {@link ParseException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param args
     *            原因オブジェクト
     */
    public ParseException(final String messageCode, final Object... args) {
        super(messageCode, args);
    }

    /**
     * {@link ParseException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param cause
     *            原因となった例外
     * @param args
     *            原因オブジェクト
     */
    public ParseException(final String messageCode, final Throwable cause,
            final Object... args) {
        super(messageCode, cause, args);
    }

    /**
     * {@link ParseException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     */
    public ParseException(final String messageCode) {
        super(messageCode);
    }
}
