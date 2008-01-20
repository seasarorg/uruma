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

import java.nio.Buffer;

import nlink.Holder;
import nlink.MarshalAs;
import nlink.NativeType;
import nlink.win32.CheckLastError;
import nlink.win32.DllClass;
import nlink.win32.DllMethod;

/**
 * kernel32.dll の提供する API をラップするためのインターフェースです。<br />
 * 
 * @author y-komori
 */
@DllClass
public interface Kernel32 {
    // SetErrorMode 用の定数
    /**
     * 致命的なエラーに関するメッセージボックスを表示しません。
     */
    static final int SEM_FAILCRITICALERRORS = 0x0001;

    /**
     * メモリ整列の違反を自動的に修復します。x86 プロセッサでは効果がありません。
     */
    static final int SEM_NOALIGNMENTFAULTEXCEPT = 0x0002;

    /**
     * 一般保護違反メッセージボックスを表示しません。
     */
    static final int SEM_NOGPFAULTERRORBOX = 0x0004;

    /**
     * ファイルが見つからなかった場合にメッセージボックスを表示しません。
     */
    static final int SEM_NOOPENFILEERRORBOX = 0x8000;

    /**
     * @see <a
     *      href="http://msdn.microsoft.com/library/ja/jpsysinf/html/_win32_getcomputername.asp">GetComputerName</a>
     */
    @DllMethod
    @CheckLastError
    int GetComputerName(@MarshalAs(NativeType.PVOID)
    Buffer buffer, @MarshalAs(NativeType.Int32_ByRef)
    Holder<Integer> size);

    /**
     * @see <a
     *      href="http://msdn.microsoft.com/library/ja/jpfileio/html/_win32_getlogicaldrives.asp">GetLogicalDrives</a>
     */
    @DllMethod
    @CheckLastError
    int GetLogicalDrives();

    /**
     * @see <a
     *      href="http://msdn.microsoft.com/library/ja/jpfileio/html/_win32_getvolumeinformation.asp">GetVolumeInformation</a>
     */
    @DllMethod
    int GetVolumeInformation(String rootPathName, @MarshalAs(NativeType.PVOID)
    Buffer volumeNameBuffer, int volumeNameSize,
            @MarshalAs(NativeType.Int32_ByRef)
            Holder<Integer> volumeSerialNumber,
            @MarshalAs(NativeType.Int32_ByRef)
            Holder<Integer> maximumComponentLength,
            @MarshalAs(NativeType.Int32_ByRef)
            Holder<Integer> fileSystemFlags, @MarshalAs(NativeType.PVOID)
            Buffer fileSystemNameBuffer, int fileSystemNameSize);

    /**
     * @see <a
     *      href="http://msdn.microsoft.com/library/ja/jpdebug/html/_win32_seterrormode.asp">SetErrorMode</a>
     */
    @DllMethod
    @CheckLastError
    int SetErrorMode(int mode);

    /**
     * @see <a
     *      href="http://msdn.microsoft.com/library/ja/jpdebug/html/_win32_getlasterror.asp">GetLastError</a>
     */
    @DllMethod
    @CheckLastError
    int GetLastError();
}
