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

public class FolderTreeContentProvider implements ITreeContentProvider {

	public Object[] getChildren(final Object parentElement) {
		File parentFolder = (File) parentElement;
		File[] children = parentFolder.listFiles();
		List<File> temp = new ArrayList<File>();

		for (File child : children) {
			if (child.isDirectory()) {
				temp.add(child);
			}
		}

		return temp.toArray(new File[temp.size()]);
	}

	public Object getParent(final Object element) {
		File file = (File) element;
		return file.getParentFile();
	}

	public boolean hasChildren(final Object element) {
		File file = (File) element;
		return file.isDirectory();
	}

	public Object[] getElements(final Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(final Viewer viewer, final Object oldInput,
			final Object newInput) {
	}
}
