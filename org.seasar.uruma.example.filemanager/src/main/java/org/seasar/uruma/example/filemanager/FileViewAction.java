package org.seasar.uruma.example.filemanager;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.eclipse.swt.program.Program;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.SelectionListener;

/**
 * @author y-komori
 * 
 */
@Form(FileViewAction.class)
public class FileViewAction {
	@ExportValue(id = "fileDetailTable")
	public List<FileDto> fileList = new ArrayList<FileDto>();

	@ImportSelection(id = "fileDetailTable")
	public List<FileDto> selectedFile;

	@SelectionListener
	public void selectionChanged(final File parentFolder) {
		fileList.clear();

		File[] children = parentFolder.listFiles();

		for (File file : children) {
			FileDto fileDto = new FileDto();

			fileDto.absolutePath = file.getAbsolutePath();
			fileDto.fileName = file.getName();

			if (file.isFile()) {
				Formatter formatter = new Formatter();
				fileDto.fileSize = formatter.format("%,d", file.length()).out()
						.toString();
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

	@EventListener(id = "fileDetailTable", type = EventListenerType.MOUSE_DOUBLE_CLICK)
	public void onSelected() {
		if (selectedFile.size() == 1) {
			FileDto dto = selectedFile.get(0);
			File file = new File(dto.absolutePath);
			if (file.isFile()) {
				Program program = Program.findProgram(StringUtil
						.substringToLast(dto.fileName, "."));
				if (program != null) {
					Program.launch(dto.absolutePath);
				}
			}
		}
	}
}
