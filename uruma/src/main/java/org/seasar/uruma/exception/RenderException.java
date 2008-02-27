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
 * レンダリング処理中に異常が発生した際にスローされるクラスです。</br>
 * 
 * @author y-komori
 */
public class RenderException extends UrumaRuntimeException {
    private static final long serialVersionUID = 2652211902877860349L;

    /**
     * {@link RenderException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param args
     *            メッセージ引数
     */
    public RenderException(final String messageCode, final Object... args) {
        super(messageCode, args);
    }

    /**
     * {@link RenderException} を構築します。<br />
     * 
     * @param messageCode
     *            メッセージコード
     * @param cause
     *            原因となった例外オブジェクト
     * @param args
     *            メッセージ引数
     */
    public RenderException(final String messageCode, final Throwable cause,
            final Object... args) {
        super(messageCode, cause, args);
    }
}
