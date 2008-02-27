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
package org.seasar.uruma.util.win32;

import java.io.StringWriter;

import org.eclipse.swt.internal.win32.OS;
import org.seasar.uruma.exception.Win32ApiException;

/**
 * Win32API コールに関するユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class Win32Util {
    private Win32Util() {

    }

    /**
     * OSから与えられた戻り値をもとにメッセージを取得します。<br />
     * <p>
     * 出典 : http://homepage2.nifty.com/igat/igapyon/diary/2005/ig051228.html
     * </p>
     * 
     * @param rc
     *            Win32APIから返却されるDWORD値
     * @return OSから得られたメッセージ
     */
    public static String formatMessage(final int rc) {
        final int[] lpMsgBuf = new int[2048];
        final int retCode = OS.FormatMessage(OS.FORMAT_MESSAGE_FROM_SYSTEM, 0,
                rc, 0, lpMsgBuf, lpMsgBuf.length, 0);
        if (retCode == 0) {
            throw new Win32ApiException("FormatMessage", Integer.valueOf(
                    OS.GetLastError()).toString());
        }

        return lpmsgbuf2String(lpMsgBuf) + "(" + rc + ")";
    }

    /**
     * <code>LPMSGBUF</code> を {@link String} オブジェクトに変換します。<br />
     * <p>
     * 出典 : http://homepage2.nifty.com/igat/igapyon/diary/2005/ig051228.html
     * </p>
     * 
     * @param lpMsgBuf
     *            C言語上としての文字列
     * @return {@link String} 化された文字列
     */
    public static String lpmsgbuf2String(final int[] lpMsgBuf) {
        final StringWriter result = new StringWriter();
        for (int index = 0; index < lpMsgBuf.length; index++) {
            if (lpMsgBuf[index] == 0) {
                // NULLが現れたら中断。
                break;
            }
            result.write(lpMsgBuf[index]);

            if (lpMsgBuf[index] / 0x10000 == 0) {
                // NULLが現れたら中断。
                break;
            }
            result.write(lpMsgBuf[index] / 0x10000);
        }
        result.flush();
        return result.toString();
    }
}
