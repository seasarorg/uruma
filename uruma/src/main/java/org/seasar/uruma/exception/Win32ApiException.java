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

import org.seasar.uruma.core.UrumaConstants;
import org.seasar.uruma.core.UrumaMessageCodes;
import org.seasar.uruma.util.win32.Win32Util;

/**
 * Win32API 呼び出しに関する問題発生時にスローされる例外です。<br />
 * 
 * @author y-komori
 */
public class Win32ApiException extends UrumaRuntimeException implements
        UrumaConstants {

    private static final long serialVersionUID = 214038110299619208L;

    /**
     * {@link Win32ApiException} を構築します。<br />
     * 
     * @param message
     *            メッセージ
     */
    public Win32ApiException(final String message) {
        super(UrumaMessageCodes.WIN32_API_CALL_FAILED, NULL_STRING, message);
    }

    /**
     * {@link Win32ApiException} を構築します。<br />
     * 
     * @param funcName
     *            関数名
     * @param message
     *            メッセージ
     */
    public Win32ApiException(final String funcName, final String message) {
        super(UrumaMessageCodes.WIN32_API_CALL_FAILED, funcName, message);
    }

    /**
     * {@link Win32ApiException} を構築します。<br />
     * 
     * @param funcName
     *            関数名
     * @param retCode
     *            リターンコード
     */
    public Win32ApiException(final String funcName, final int retCode) {
        super(UrumaMessageCodes.WIN32_API_CALL_FAILED, funcName, Win32Util
                .formatMessage(retCode));
    }
}
