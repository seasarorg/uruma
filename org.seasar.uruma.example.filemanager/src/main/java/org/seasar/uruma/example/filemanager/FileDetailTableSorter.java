package org.seasar.uruma.example.filemanager;

import java.io.File;

import org.seasar.uruma.viewer.GenericTableViewerSorter;

/**
 * @author y-komori
 * 
 */
public class FileDetailTableSorter extends GenericTableViewerSorter {

	@Override
	public int category(final Object element) {
		FileDto dto = (FileDto) element;
		File file = new File(dto.absolutePath);
		if (file.isDirectory()) {
			return 0;
		} else {
			return 1;
		}
	}

}
