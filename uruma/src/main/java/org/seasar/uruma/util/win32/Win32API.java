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
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import nlink.Holder;
import nlink.win32.NLink;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.internal.win32.OS;
import org.eclipse.swt.internal.win32.SHFILEINFO;
import org.eclipse.swt.internal.win32.SHFILEINFOA;
import org.eclipse.swt.internal.win32.SHFILEINFOW;
import org.eclipse.swt.internal.win32.TCHAR;
import org.seasar.uruma.exception.Win32ApiException;
import org.seasar.uruma.util.AssertionUtil;

/**
 * Java から Win32 API を呼び出すためのユーティリティクラスです。<br />
 * 
 * @author y-komori
 */
public class Win32API {
    protected static final int MAX_COMPUTERNAME_LENGTH = 15;

    private static Kernel32 kernel32 = NLink.create(Kernel32.class);

    private Win32API() {

    }

    private static final int SHGFI_TYPENAME = 1024;

    private static final int SHGFI_DISPLAYNAME = 512;

    /**
     * ローカルコンピュータの NetBIOS 名を取得します。<br />
     * 
     * @return ローカルコンピュータのNetBIOS名
     */
    public static String getComputerName() {
        ByteBuffer buffer = ByteBuffer
                .allocateDirect(MAX_COMPUTERNAME_LENGTH + 1);
        Holder<Integer> holder = new Holder<Integer>(buffer.capacity());

        try {
            kernel32.GetComputerName(buffer, holder);
            return convertToJavaString(buffer);
        } catch (Exception ex) {
            throw new Win32ApiException(ex.getLocalizedMessage());
        }
    }

    /**
     * 現在利用可能なディスクドライブを取得します。<br />
     * 
     * @return 利用可能なディスクドライブ名の配列
     */
    public static String[] getLogicalDrives() {
        try {
            int result = kernel32.GetLogicalDrives();

            int mask = 0x01;
            int cnt = 0;
            for (int i = 0; i < 26; i++, mask = mask << 1) {
                if ((result & mask) != 0) {
                    cnt++;
                }
            }
            String[] drives = new String[cnt];
            char drive = 'A';
            mask = 0x01;
            cnt = 0;
            for (int i = 0; i < 26; i++, drive++, mask = mask << 1) {
                if ((result & mask) != 0) {
                    drives[cnt++] = drive + ":\\";
                }
            }
            return drives;

        } catch (Exception ex) {
            throw new Win32ApiException(ex.getLocalizedMessage());
        }
    }

    /**
     * ボリューム情報を取得します。<br />
     * 
     * @param rootPathName
     *            ボリュームのルートパス
     * @return ボリューム情報。取得できなかった場合は <code>null</code>
     */
    public static VolumeInformation getVolumeInformation(
            final String rootPathName) {
        ByteBuffer volumeNameBuf = ByteBuffer.allocateDirect(128);
        Holder<Integer> volumeSerialNum = new Holder<Integer>(new Integer(0));
        Holder<Integer> maxComponentLength = new Holder<Integer>(new Integer(0));
        Holder<Integer> fileSystemFlags = new Holder<Integer>(new Integer(0));
        ByteBuffer fileSystemNameBuf = ByteBuffer.allocateDirect(128);

        try {
            kernel32.SetErrorMode(Kernel32.SEM_FAILCRITICALERRORS);
            int result = kernel32.GetVolumeInformation(rootPathName,
                    volumeNameBuf, volumeNameBuf.capacity(), volumeSerialNum,
                    maxComponentLength, fileSystemFlags, fileSystemNameBuf,
                    fileSystemNameBuf.capacity());
            if (result != 0) {
                VolumeInformation info = new VolumeInformation();
                info.setRootPath(rootPathName);
                info.setVolumeLabel(convertToJavaString(volumeNameBuf));
                info.setSerialNumber(volumeSerialNum.value);
                info.setFileSystemName(convertToJavaString(fileSystemNameBuf));
                info.setMaxComponentLength(maxComponentLength.value);
                info.setFileSystemFlags(fileSystemFlags.value);
                return info;
            } else {
                return null;
            }
        } catch (Exception ex) {
            throw new Win32ApiException(ex.getLocalizedMessage());
        }
    }

    /**
     * 指定したルートパスのドライブの種類を調べます。<br />
     * 
     * @param rootPathName
     *            種類を調べるドライブのルートパス
     * @return ドライブの種類
     * @see DriveType
     */
    public static DriveType getDriveType(final String rootPathName) {
        try {
            int result = kernel32.GetDriveType(rootPathName);

            switch (result) {
            case Kernel32.DRIVE_UNKNOWN:
                return DriveType.DRIVE_UNKNOWN;

            case Kernel32.DRIVE_NO_ROOT_DIR:
                return DriveType.DRIVE_NO_ROOT_DIR;

            case Kernel32.DRIVE_REMOVABLE:
                return DriveType.DRIVE_REMOVABLE;

            case Kernel32.DRIVE_FIXED:
                return DriveType.DRIVE_FIXED;

            case Kernel32.DRIVE_REMOTE:
                return DriveType.DRIVE_REMOTE;

            case Kernel32.DRIVE_CDROM:
                return DriveType.DRIVE_CDROM;

            case Kernel32.DRIVE_RAMDISK:
                return DriveType.DRIVE_RAMDISK;

            default:
                return DriveType.DRIVE_UNKNOWN;
            }
        } catch (Exception ex) {
            throw new Win32ApiException(ex.getLocalizedMessage());
        }
    }

    /**
     * 指定されたファイルの種類を取得します。<br />
     * 
     * @param path
     *            種類を調べるファイルのパス
     * @return ファイルの種類
     */
    public static String getFileTypeName(final String path) {
        int flags = SHGFI_TYPENAME | OS.SHGFI_USEFILEATTRIBUTES;
        SHFILEINFO info = getFileInfo(path, flags);
        if (info instanceof SHFILEINFOW) {
            return convertToJavaString(((SHFILEINFOW) info).szTypeName);
        } else {
            return convertToJavaString(((SHFILEINFOA) info).szTypeName);
        }
    }

    /**
     * 指定されたファイルの表示名称を取得します。<br />
     * 
     * @param path
     *            ファイルのパス
     * @return 表示名称
     */
    public static String getFileDisplayName(final String path) {
        int flags = SHGFI_DISPLAYNAME | OS.SHGFI_USEFILEATTRIBUTES;
        SHFILEINFO info = getFileInfo(path, flags);
        if (info instanceof SHFILEINFOW) {
            return convertToJavaString(((SHFILEINFOW) info).szDisplayName);
        } else {
            return convertToJavaString(((SHFILEINFOA) info).szDisplayName);
        }
    }

    /**
     * 指定したパスのアイコンをを取得します。<br />
     * 
     * @param path
     *            パス
     * @return アイコンの {@link ImageData} オブジェクト
     */
    public static ImageData getFileIcon(final String path) {
        int flags = OS.SHGFI_ICON | OS.SHGFI_SMALLICON
                | OS.SHGFI_USEFILEATTRIBUTES;
        SHFILEINFO info = getFileInfo(path, flags);
        if (info.hIcon != 0) {
            Image image = Image.win32_new(null, SWT.ICON, info.hIcon);
            ImageData imageData = image.getImageData();
            image.dispose();
            return imageData;
        } else {
            return null;
        }
    }

    private static SHFILEINFO getFileInfo(final String path, final int flags) {
        AssertionUtil.assertNotNull("path", path);
        SHFILEINFO info = OS.IsUnicode ? (SHFILEINFO) new SHFILEINFOW()
                : new SHFILEINFOA();
        TCHAR pszPath = new TCHAR(0, path, true);
        int retCode = OS.SHGetFileInfo(pszPath, OS.FILE_ATTRIBUTE_NORMAL, info,
                SHFILEINFO.sizeof, flags);
        if (retCode != 0) {
            return info;
        } else {
            throw new Win32ApiException("SHGetFileInfo", retCode);
        }
    }

    /**
     * ファイルからインデックスで指定したアイコンを取得します。<br />
     * 
     * @param fileName
     *            ファイル名
     * @param index
     *            アイコンのインデックス番号
     * @return アイコンの {@link ImageData} オブジェクト
     */
    public static ImageData extractIcon(final String fileName, final int index) {
        TCHAR lpszFile = new TCHAR(0, fileName, true);
        int[] phiconSmall = new int[1];
        int[] phiconLarge = null;
        OS.ExtractIconEx(lpszFile, index, phiconLarge, phiconSmall, 1);
        if (phiconSmall[0] == 0) {
            return null;
        }
        Image image = Image.win32_new(null, SWT.ICON, phiconSmall[0]);
        ImageData imageData = image.getImageData();
        image.dispose();
        return imageData;
    }

    /**
     * 指定した文字列に含まれる環境変数を展開します。<br />
     * 
     * @param str
     *            対象文字列
     * @return 環境変数展開後の文字列
     */
    public static String expandEnvironmentStrings(final String str) {
        TCHAR lpSrc = new TCHAR(OS.CP_INSTALLED, str, true);
        String result = str;
        int length = OS.ExpandEnvironmentStrings(lpSrc, null, 0);
        if (length != 0) {
            TCHAR lpDst = new TCHAR(0, length);
            OS.ExpandEnvironmentStrings(lpSrc, lpDst, length);
            result = lpDst.toString(0, Math.max(0, length - 1));
        }
        return result;
    }

    static String convertToJavaString(final ByteBuffer buffer) {
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        StringWriter writer = new StringWriter();
        for (int i = 0; buffer.remaining() > 0; i++) {
            short chr = buffer.getShort();
            if (chr == 0x00) {
                break;
            } else {
                writer.write(chr);
            }
        }
        writer.flush();
        return writer.toString();
    }

    static String convertToJavaString(final char[] str) {
        StringWriter writer = new StringWriter();
        for (int i = 0; i < str.length; i++) {
            if (str[i] == 0x00) {
                break;
            } else {
                writer.write(str[i]);
            }
        }
        writer.flush();
        return writer.toString();
    }

    static String convertToJavaString(final byte[] bytes) {
        byte[] result = null;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == 0x00) {
                result = new byte[i];
                System.arraycopy(bytes, 0, result, 0, result.length);
                return new String(result);
            }
        }
        return "";
    }
}
