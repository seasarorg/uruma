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
import java.util.Date;

import org.eclipse.jface.viewers.TableViewer;
import org.seasar.uruma.util.win32.Win32API;
import org.seasar.uruma.viewer.GenericTableViewerComparator;

/**
 * ファイルビューのソートを行うためのクラスです。<br />
 * 
 * @author y-komori
 */
public class FileDetailTableSorterProvider extends GenericTableViewerComparator {

	@Override
	public int category(final Object element) {
		if (((File) element).isDirectory()) {
			return 0;
		} else {
			return 1;
		}
	}

	@Override
	protected int doCompare(final TableViewer viewer, final Object e1,
			final Object e2, final int sortColumn) {
		File f1 = (File) e1;
		File f2 = (File) e2;

		switch (sortColumn) {
		case 0:
			return f1.getName().compareToIgnoreCase(f2.getName());

		case 1:
			return (int) (f1.length() - f2.length());

		case 2:
			String type1 = Win32API.getFileTypeName(f1.getAbsolutePath());
			String type2 = Win32API.getFileTypeName(f2.getAbsolutePath());
			if ((type1 != null) && (type2 != null)) {
				return type1.compareTo(type2);
			} else {
				return 0;
			}

		case 3:
			Date d1 = new Date(f1.lastModified());
			Date d2 = new Date(f2.lastModified());
			return d1.compareTo(d2);

		default:
			return 0;
		}
	}
}
