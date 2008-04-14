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
package org.seasar.uruma.example.filemanager;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.uruma.util.PathUtil;
import org.seasar.uruma.util.win32.RegistryUtil;
import org.seasar.uruma.util.win32.Win32API;

/**
 * アイコンを管理するためのクラスです。<br />
 * 
 * @author y-komori
 */
public class IconManager {
	private IconManager() {

	}

	public static Image getExtIcon(final String path) {
		String ext = PathUtil.getExt(path);
		Image icon = ImageManager.getImage(ext);
		if (icon != null) {
			return icon;
		} else {
			ImageData imageData = Win32API.getFileIcon(path);
			return ImageManager.putImage(ext, imageData);
		}
	}

	public static Image getDriveIcon(final String rootPath) {
		Image icon = ImageManager.getImage(rootPath);
		if (icon != null) {
			return icon;
		} else {
			ImageData imageData = Win32API.getFileIcon(rootPath);
			return ImageManager.putImage(rootPath, imageData);
		}
	}

	public static Image getMyComputerIcon() {
		return getIconImage("myComputer", RegistryUtil.HKEY_CLASSES_ROOT,
				"CLSID\\{20D04FE0-3AEA-1069-A2D8-08002B30309D}\\DefaultIcon",
				"");
	}

	public static Image getFolderIcon() {
		Image img = null;
		try {
			img = getIconImage(
					"winFolder",
					RegistryUtil.HKEY_LOCAL_MACHINE,
					"SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Icons",
					"3");
		} catch (Exception e) {
		}
		return img;
	}

	private static Image getIconImage(final String iconKey, final int hKey,
			final String entry, final String key) {
		Image icon = ImageManager.getImage(iconKey);
		if (icon != null) {
			return icon;
		} else {
			ImageData imageData = getIconFromRegistry(hKey, entry, key);
			return ImageManager.putImage(iconKey, imageData);
		}
	}

	private static ImageData getIconFromRegistry(final int hKey,
			final String entry, final String key) {
		String iconPath = RegistryUtil.getRegistryValue(hKey, entry, key);
		int pos = iconPath.indexOf(",");
		int index = -1;
		if (pos > -1) {
			index = Integer.parseInt(iconPath.substring(pos + 1));
			iconPath = iconPath.substring(0, pos);
		}
		iconPath = Win32API.expandEnvironmentStrings(iconPath);
		return Win32API.extractIcon(iconPath, index);
	}

}
