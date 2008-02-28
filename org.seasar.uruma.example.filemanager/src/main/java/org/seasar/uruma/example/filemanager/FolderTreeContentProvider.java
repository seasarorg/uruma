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
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.seasar.uruma.util.win32.Win32API;

/**
 * フォルダツリーのためのコンテントプロバイダです。<br />
 * 
 * @author y-komori
 */
public class FolderTreeContentProvider implements ITreeContentProvider,
		Constants {

	public Object[] getChildren(final Object parentElement) {
		File parentFolder = (File) parentElement;
		if (parentFolder.getPath().equals(ROOT_PATH)) {
			return new Object[] { new File(MY_COMPUTER_PATH) };
		} else if (parentFolder.getPath().equals(MY_COMPUTER_PATH)) {
			return getLogicalDrives();
		} else {
			return getFolders(parentFolder);
		}
	}

	public Object getParent(final Object element) {
		File file = (File) element;
		return file.getParentFile();
	}

	public boolean hasChildren(final Object element) {
		File file = (File) element;
		if (file.getPath().indexOf(":") > -1) {
			return true;
		} else {
			return file.isDirectory();
		}
	}

	public Object[] getElements(final Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(final Viewer viewer, final Object oldInput,
			final Object newInput) {
	}

	protected File[] getLogicalDrives() {
		String[] paths = Win32API.getLogicalDrives();
		if (paths != null) {
			List<File> drives = new ArrayList<File>();
			for (int i = 0; i < paths.length; i++) {
				if (!paths[i].startsWith("A") && !paths[i].startsWith("B")) {
					drives.add(new File(paths[i]));
				}
			}
			return drives.toArray(new File[drives.size()]);
		} else {
			return null;
		}
	}

	protected File[] getFolders(final File parent) {
		File[] children = parent.listFiles();
		if (children != null) {
			List<File> folders = new ArrayList<File>();
			for (File child : children) {
				if (child.isDirectory()) {
					folders.add(child);
				}
			}
			return folders.toArray(new File[folders.size()]);
		} else {
			return new File[] {};
		}
	}
}
