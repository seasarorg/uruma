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
package org.seasar.uruma.example.filemanager.provider;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.seasar.uruma.example.filemanager.Constants;
import org.seasar.uruma.example.filemanager.IconManager;
import org.seasar.uruma.util.win32.RegistryUtil;
import org.seasar.uruma.util.win32.Win32API;

/**
 * フォルダツリーのためのラベルプロバイダです。<br />
 * 
 * @author y-komori
 */
public class FolderTreeLabelProvider extends LabelProvider implements Constants {
	@Override
	public Image getImage(final Object element) {
		File folder = (File) element;
		String path = folder.getPath();
		if (path.equals(MY_COMPUTER_PATH)) {
			return IconManager.getMyComputerIcon();
		} else if (path.endsWith(DRIVE_SUFFIX)) {
			return IconManager.getDriveIcon(path);
		} else {
			return IconManager.getFolderIcon();
		}
	}

	@Override
	public String getText(final Object element) {
		File folder = (File) element;
		if (folder.getPath().equals(MY_COMPUTER_PATH)) {
			return getMyComputerName();
		} else if (folder.getAbsolutePath().endsWith(DRIVE_SUFFIX)) {
			return Win32API.getFileDisplayName(folder.getAbsolutePath());
		} else {
			return folder.getName();
		}
	}

	protected String getMyComputerName() {
		return RegistryUtil.getRegistryValue(RegistryUtil.HKEY_CURRENT_USER,
				"Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\"
						+ "CLSID\\{20D04FE0-3AEA-1069-A2D8-08002B30309D}");
	}
}
