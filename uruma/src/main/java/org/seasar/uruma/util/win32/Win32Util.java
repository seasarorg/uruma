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
package org.seasar.uruma.util.win32;

import java.io.StringWriter;

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.SHFILEINFO;
import org.eclipse.swt.internal.win32.TCHAR;
import org.seasar.uruma.exception.Win32ApiException;
import org.seasar.uruma.util.AssertionUtil;

/**
 * Win32API コールに関するユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class Win32Util {
    private Win32Util() {

    }

    /** get icon */
    public static final int SHGFI_ICON = 0x000000100;

    /** get display name */
    public static final int SHGFI_DISPLAYNAME = 0x000000200;

    /** get type name */
    public static final int SHGFI_TYPENAME = 0x000000400;

    /** get attributes */
    public static final int SHGFI_ATTRIBUTES = 0x000000800;

    /** get icon location */
    public static final int SHGFI_ICONLOCATION = 0x000001000;

    /** return exe type */
    public static final int SHGFI_EXETYPE = 0x000002000;

    /** get system icon index */
    public static final int SHGFI_SYSICONINDEX = 0x000004000;

    /** put a link overlay on icon */
    public static final int SHGFI_LINKOVERLAY = 0x000008000;

    /** show icon in selected state */
    public static final int SHGFI_SELECTED = 0x000010000;

    /** get only specified attributes */
    public static final int SHGFI_ATTR_SPECIFIED = 0x000020000;

    /** get large icon */
    public static final int SHGFI_LARGEICON = 0x000000000;

    /** get small icon */
    public static final int SHGFI_SMALLICON = 0x000000001;

    /** get open icon */
    public static final int SHGFI_OPENICON = 0x000000002;

    /** get shell size icon */
    public static final int SHGFI_SHELLICONSIZE = 0x000000004;

    /** pszPath is a pidl */
    public static final int SHGFI_PIDL = 0x000000008;

    /** use passed dwFileAttribute */
    public static final int SHGFI_USEFILEATTRIBUTES = 0x000000010;

    /** apply the appropriate overlays */
    public static final int SHGFI_ADDOVERLAYS = 0x000000020;

    /** Get the index of the overlay */
    public static final int SHGFI_OVERLAYINDEX = 0x000000040;

    public static String shGetFileInfo(final String path) {
        AssertionUtil.assertNotNull("path", path);

        TCHAR tPath = new TCHAR(OS.CP_INSTALLED, path, true);
        SHFILEINFO shFileInfo = new SHFILEINFO();
        OS.SHGetFileInfo(tPath, OS.FILE_ATTRIBUTE_NORMAL, shFileInfo,
                SHFILEINFO.sizeof, SHGFI_TYPENAME);

        return null;
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
