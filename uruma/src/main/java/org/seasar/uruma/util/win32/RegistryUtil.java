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

import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.TCHAR;
import org.seasar.uruma.exception.Win32ApiException;
import org.seasar.uruma.util.AssertionUtil;

/**
 * Windows レジストリアクセスに関するユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class RegistryUtil {
    private RegistryUtil() {

    }

    /**
     * <code>HKEY_LOCAL_MACHINE</code> を表すハンドルです。<br />
     */
    public static int HKEY_LOCAL_MACHINE = OS.HKEY_LOCAL_MACHINE;

    /**
     * <code>HKEY_CLASSES_ROOT</code> を表すハンドルです。<br />
     */
    public static int HKEY_CLASSES_ROOT = OS.HKEY_CLASSES_ROOT;

    /**
     * <code>HKEY_CURRENT_USER</code> を表すハンドルです。<br />
     */
    public static int HKEY_CURRENT_USER = OS.HKEY_CURRENT_USER;

    private static int KEY_ALL_ACCESS = 0xF003F;

    /**
     * レジストリをオープンします。<br />
     * 
     * @param hKey
     *            ハンドル({@link #HKEY_LOCAL_MACHINE}, {@link #HKEY_CLASSES_ROOT},
     *            {@link #HKEY_CURRENT_USER} のいずれかを指定)
     * @param entry
     *            レジストリエントリ
     * @return レジストリハンドル
     */
    public static RegistryHandle regOpenKey(final int hKey, final String entry) {
        AssertionUtil.assertNotNull("entry", entry);
        RegistryHandle handle = new RegistryHandle();
        int retCode = OS.RegOpenKeyEx(hKey, new TCHAR(OS.CP_INSTALLED, entry,
                true), 0, OS.KEY_READ, handle.getPhkResult());
        if (retCode == 0) {
            return handle;
        } else {
            throw new Win32ApiException("RegOpenKeyEX", retCode);
        }
    }

    /**
     * レジストリをクローズします。<br />
     * 
     * @param handle
     *            オープンされたレジストリハンドル
     */
    public static void regCloseKey(final RegistryHandle handle) {
        AssertionUtil.assertNotNull("handle", handle);
        if (handle.getPointer() != 0) {
            OS.RegCloseKey(handle.getPointer());
        }
    }

    /**
     * レジストリから値を読み込みます。<br />
     * 
     * @param handle
     *            オープンされたレジストリハンドル
     * @param valueName
     *            読み込む値の名称
     * @return 読み込んだ結果
     */
    public static String regQueryValue(final RegistryHandle handle,
            final String valueName) {
        AssertionUtil.assertNotNull("handle", handle);
        AssertionUtil.assertNotNull("valueName", valueName);

        TCHAR buf = new TCHAR(OS.CP_INSTALLED, 256);
        final int[] len = new int[] { 256 };
        int retCode = OS.RegQueryValueEx(handle.getPointer(), new TCHAR(
                OS.CP_INSTALLED, valueName, true), 0, null, buf, len);
        if (retCode == 0) {
            return buf.toString(0, buf.strlen());
        } else {
            regCloseKey(handle);
            throw new Win32ApiException("RegQueryValueEx", retCode);
        }
    }
}
