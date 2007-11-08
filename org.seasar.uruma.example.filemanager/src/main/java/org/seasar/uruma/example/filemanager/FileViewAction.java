package org.seasar.uruma.example.filemanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.SelectionListener;

/**
 * @author y-komori
 * 
 */
@Form(FileViewAction.class)
public class FileViewAction {
	// private TableViewer fileDetailTable;

	@ExportValue(id = "fileDetailTable")
	private List<FileDto> fileList = new ArrayList<FileDto>();

	@SelectionListener
	public void selectionChanged(final File parentFolder) {
		fileList.clear();

		File[] children = parentFolder.listFiles();

		for (File file : children) {
			if (file.isFile()) {
				FileDto fileDto = new FileDto();
				fileDto.setFileName(file.getName());
				fileList.add(fileDto);
			}
		}
	}
}
