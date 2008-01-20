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
 * ボリュームに関する情報を保持するクラスです。<br />
 * 
 * @author y-komori
 */
public class VolumeInformation {
    static final int FS_CASE_IS_PRESERVED = 2;

    static final int FS_CASE_SENSITIVE = 1;

    static final int FS_UNICODE_STORED_ON_DISK = 4;

    static final int FS_PERSISTENT_ACLS = 8;

    static final int FS_FILE_COMPRESSION = 16;

    static final int FS_VOL_IS_COMPRESSED = 32768;

    static final int FILE_NAMED_STREAMS = 0x00040000;

    static final int FILE_SUPPORTS_ENCRYPTION = 0x00020000;

    static final int FILE_SUPPORTS_OBJECT_IDS = 0x00010000;

    static final int FILE_SUPPORTS_REPARSE_POINTS = 0x00000080;

    static final int FILE_SUPPORTS_SPARSE_FILES = 0x00000040;

    static final int FILE_VOLUME_QUOTAS = 0x00000020;

    private String rootPath;

    private String volumeLabel;

    private int serialNumber;

    private String fileSystemName;

    private int maxComponentLength;

    private int fileSystemFlags;

    VolumeInformation() {
    }

    /**
     * ボリュームのルートパスを返します。<br />
     * 
     * @return ルートパス
     */
    public String getRootPath() {
        return this.rootPath;
    }

    void setRootPath(final String rootPath) {
        this.rootPath = rootPath;
    }

    /**
     * ボリュームラベルを返します。<br />
     * 
     * @return ボリュームラベル
     */
    public String getVolumeLabel() {
        return this.volumeLabel;
    }

    void setVolumeLabel(final String volumeLabel) {
        this.volumeLabel = volumeLabel;
    }

    /**
     * ボリュームシリアルナンバーを返します。<br />
     * 
     * @return ボリュームシリアルナンバー
     */
    public int getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * ボリュームシリアルナンバーを文字列で返します。<br />
     * 
     * @return ボリュームシリアルナンバー
     */
    public String getSerialNumberAsString() {
        int high = (this.serialNumber >> 16) & 0x0000ffff;
        int low = this.serialNumber & 0x0000ffff;
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("%04X", high));
        builder.append("-");
        builder.append(String.format("%04X", low));
        return builder.toString();
    }

    void setSerialNumber(final int serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * ファイルシステム名を返します。<br />
     * 
     * @return ファイルシステム名
     */
    public String getFileSystemName() {
        return this.fileSystemName;
    }

    void setFileSystemName(final String fileSystemName) {
        this.fileSystemName = fileSystemName;
    }

    /**
     * ファイルシステムがサポートするファイル名の最大長を返します。<br />
     * 
     * @return ファイル名の最大長
     */
    public int getMaxComponentLength() {
        return this.maxComponentLength;
    }

    void setMaxComponentLength(final int maxComponentLength) {
        this.maxComponentLength = maxComponentLength;
    }

    void setFileSystemFlags(final int fileSystemFlags) {
        this.fileSystemFlags = fileSystemFlags;
    }

    /**
     * ファイルシステムがディスクにファイル名を保存する際、大文字と小文字を区別するかどうかを返します。<br />
     * 
     * @return 区別する場合は <code>true</code>、区別しない場合は <code>false</code>
     */
    public boolean isCaseIsPreserved() {
        return (fileSystemFlags & FS_CASE_IS_PRESERVED) != 0 ? true : false;
    }

    /**
     * ファイルシステムがファイル名の大文字と小文字を区別するかどうかを返します。<br />
     * 
     * @return 区別する場合は <code>true</code>、区別しない場合は <code>false</code>
     */
    public boolean isCaseSensitive() {
        return (fileSystemFlags & FS_CASE_SENSITIVE) != 0 ? true : false;

    }

    /**
     * ファイルシステムが Unicode のファイル名をサポートしていて、ディスク上でも正しく表示されるかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isUnicodeStoredOnDisk() {
        return (fileSystemFlags & FS_UNICODE_STORED_ON_DISK) != 0 ? true
                : false;
    }

    /**
     * ファイルシステムは、ACL（アクセス制御リスト）の保存と適用を行うかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isPersistentACLs() {
        return (fileSystemFlags & FS_PERSISTENT_ACLS) != 0 ? true : false;
    }

    /**
     * ファイルシステムが、ファイルベースの圧縮をサポートしているかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isFileCompression() {
        return (fileSystemFlags & FS_FILE_COMPRESSION) != 0 ? true : false;
    }

    /**
     * ボリュームが圧縮されているかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isVolumeComplessed() {
        return (fileSystemFlags & FS_VOL_IS_COMPRESSED) != 0 ? true : false;
    }

    /**
     * ファイルシステムが名前付きストリームをサポートしているかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isNamedStreams() {
        return (fileSystemFlags & FILE_NAMED_STREAMS) != 0 ? true : false;
    }

    /**
     * ファイルシステムが、暗号化ファイルシステムをサポートしているかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isSupportsEncryption() {
        return (fileSystemFlags & FILE_SUPPORTS_ENCRYPTION) != 0 ? true : false;
    }

    /**
     * ファイルシステムがオブジェクト識別子をサポートしているかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isSupportsObjectIDs() {
        return (fileSystemFlags & FILE_SUPPORTS_OBJECT_IDS) != 0 ? true : false;
    }

    /**
     * ファイルシステムが再解析ポイントをサポートしているかどうかを返します。 <br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isSupportsReparsePoints() {
        return (fileSystemFlags & FILE_SUPPORTS_REPARSE_POINTS) != 0 ? true
                : false;
    }

    /**
     * ファイルシステムがスパースファイルをサポートしているかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isSupportsSparseFiles() {
        return (fileSystemFlags & FILE_SUPPORTS_SPARSE_FILES) != 0 ? true
                : false;
    }

    /**
     * ファイルシステムがディスククォータをサポートしているかどうかを返します。<br />
     * 
     * @return サポートしている場合は <code>true</code>、そうでない場合は <code>false</code>
     */
    public boolean isVolumeQuotas() {
        return (fileSystemFlags & FILE_VOLUME_QUOTAS) != 0 ? true : false;
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder
                .append("RootPath                     : " + getRootPath()
                        + "\n");
        builder.append("VolumeLabel                  : " + getVolumeLabel()
                + "\n");
        builder.append("SerialNumber                 : "
                + getSerialNumberAsString() + "\n");
        builder.append("FileSystemName               : " + getFileSystemName()
                + "\n");
        builder.append("MaxComponentLength           : "
                + getMaxComponentLength() + "\n");
        builder.append("FS_CASE_IS_PRESERVED         : " + isCaseIsPreserved()
                + "\n");
        builder.append("FS_CASE_SENSITIVE            : " + isCaseSensitive()
                + "\n");
        builder.append("FS_UNICODE_STORED_ON_DISK    : "
                + isUnicodeStoredOnDisk() + "\n");
        builder.append("FS_PERSISTENT_ACLS           : " + isPersistentACLs()
                + "\n");
        builder.append("FS_FILE_COMPRESSION          : " + isFileCompression()
                + "\n");
        builder.append("FS_VOL_IS_COMPRESSED         : " + isVolumeComplessed()
                + "\n");
        builder.append("FILE_NAMED_STREAMS           : " + isNamedStreams()
                + "\n");
        builder.append("FILE_SUPPORTS_ENCRYPTION     : "
                + isSupportsEncryption() + "\n");
        builder.append("FILE_SUPPORTS_OBJECT_IDS     : "
                + isSupportsObjectIDs() + "\n");
        builder.append("FILE_SUPPORTS_REPARSE_POINTS : "
                + isSupportsReparsePoints() + "\n");
        builder.append("FILE_SUPPORTS_SPARSE_FILES   : "
                + isSupportsSparseFiles() + "\n");
        builder.append("FILE_VOLUME_QUOTAS           : " + isVolumeQuotas()
                + "\n");
        return builder.toString();
    }
}
