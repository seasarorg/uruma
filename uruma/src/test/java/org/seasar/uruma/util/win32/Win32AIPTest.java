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

import junit.framework.TestCase;

/**
 * {@link Win32API} のためのテストクラスです。<br />
 * 
 * @author y-komori
 */
public class Win32AIPTest extends TestCase {
    /**
     * {@link Win32API#getComputerName()} のテストです。<br />
     */
    public void testGetComputerName() {
        System.out.println(Win32API.getComputerName());
    }

    /**
     * {@link Win32API#getLogicalDrives()} のテストです。<br />
     */
    public void testGetLogicalDrives() {
        String[] drives = Win32API.getLogicalDrives();
        for (int i = 0; i < drives.length; i++) {
            System.out.println(drives[i]);
        }
    }

    /**
     * {@link Win32API#getVolumeInformation(String)} のテストです。<br />
     */
    public void testGetVolumeInformation() {
        VolumeInformation info = Win32API.getVolumeInformation("c:\\");
        System.out.println(info);
    }

    /**
     * {@link Win32API#getDriveType(String)} のテストです。<br />
     */
    public void testGetDriveType() {
        String[] drives = Win32API.getLogicalDrives();
        for (int i = 0; i < drives.length; i++) {
            DriveType type = Win32API.getDriveType(drives[i]);
            System.out.println(drives[i] + "..." + type);
        }
    }

    /**
     * {@link Win32API#getFileTypeName(String)} のテストです。<br />
     */
    public void testGetFileTypeName() {
        String[] drives = Win32API.getLogicalDrives();
        for (int i = 0; i < drives.length; i++) {
            String type = Win32API.getFileTypeName(drives[i]);
            System.out.println(drives[i] + "..." + type);
        }
    }
}
