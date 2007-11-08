package org.seasar.uruma.example.filemanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
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
	@ExportValue(id = "fileDetailTable")
	public List<FileDto> fileList = new ArrayList<FileDto>();

	@SelectionListener
	public void selectionChanged(final File parentFolder) {
		fileList.clear();

		File[] children = parentFolder.listFiles();

		for (File file : children) {
			FileDto fileDto = new FileDto();

			fileDto.absolutePath = file.getAbsolutePath();
			fileDto.fileName = file.getName();

			if (file.isFile()) {
				fileDto.fileSize = Long.toString(file.length());
			} else {
				fileDto.fileSize = "";
			}

			Formatter formatter = new Formatter();
			formatter.format("%tY/%<tm/%<td(%<ta) %<tk:%<tM:%<tS", new Date(
					file.lastModified()));
			fileDto.fileUpdateTime = formatter.out().toString();

			fileList.add(fileDto);
		}
	}
}
