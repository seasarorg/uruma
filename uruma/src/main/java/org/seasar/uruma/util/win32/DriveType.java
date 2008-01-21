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

/**
 * ドライブの種類を表す列挙型です。<br />
 * 
 * @author y-komori
 */
public enum DriveType {
    /**
     * ドライブの種類を判別できませんでした。
     */
    DRIVE_UNKNOWN,

    /**
     * 指定のルートディレクトリが存在しません。<br />
     * たとえば、パスにボリュームがマウントされていません。<br />
     * （未フォーマットや、メディアが挿入されていないなど）。
     */
    DRIVE_NO_ROOT_DIR,

    /**
     * このディスクは、ドライブから取り出せます。
     */
    DRIVE_REMOVABLE,

    /**
     * このディスクは、ドライブから取り出せません。
     */
    DRIVE_FIXED,

    /**
     * このドライブは、リモート（ネットワーク）ドライブです。
     */
    DRIVE_REMOTE,

    /**
     * このドライブは、CD-ROM ドライブです。
     */
    DRIVE_CDROM,

    /**
     * このドライブは、RAM ディスクです。
     */
    DRIVE_RAMDISK
}
