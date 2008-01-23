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
package org.seasar.uruma.example.filemanager;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.seasar.eclipse.common.util.ImageManager;
import org.seasar.uruma.util.win32.RegistryUtil;
import org.seasar.uruma.util.win32.VolumeInformation;
import org.seasar.uruma.util.win32.Win32API;

/**
 * @author y-komori
 */
public class FolderTreeLabelProvider extends LabelProvider {
	@Override
	public Image getImage(final Object element) {
		File folder = (File) element;
		if (folder.getPath().equals("::")) {
			return getMyComputerImage();
		} else {
			Image folderImage = ImageManager.loadImage("folder");
			return folderImage;
		}
	}

	@Override
	public String getText(final Object element) {
		File folder = (File) element;
		if (folder.getPath().equals("::")) {
			return getMyComputerName();
		} else if (folder.getAbsolutePath().endsWith(":\\")) {
			String rootPath = folder.getAbsolutePath();
			String label = Win32API.getFileTypeName(folder.getAbsolutePath());
			String drive = "(" + rootPath.substring(0, rootPath.length() - 1)
					+ ")";
			VolumeInformation info = Win32API.getVolumeInformation(rootPath);
			if (info != null) {
				label = info.getVolumeLabel();
			}
			return label + drive;
		} else {
			return folder.getName();
		}
	}

	protected String getMyComputerName() {
		return RegistryUtil.getRegistryValue(RegistryUtil.HKEY_CURRENT_USER,
				"Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\"
						+ "CLSID\\{20D04FE0-3AEA-1069-A2D8-08002B30309D}");
	}

	protected Image getMyComputerImage() {
		String iconPath = RegistryUtil.getRegistryValue(
				RegistryUtil.HKEY_CLASSES_ROOT,
				"CLSID\\{20D04FE0-3AEA-1069-A2D8-08002B30309D}\\DefaultIcon");
		int pos = iconPath.indexOf(",");
		int index = -1;
		if (pos > -1) {
			index = Integer.parseInt(iconPath.substring(pos + 1));
			iconPath = iconPath.substring(0, pos);
		}
		iconPath = Win32API.expandEnvironmentStrings(iconPath);
		ImageData imageData = Win32API.extractIcon(iconPath, index);
		return new Image(Display.getCurrent(), imageData);
	}
}
