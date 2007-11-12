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
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import org.eclipse.swt.program.Program;
import org.seasar.framework.util.StringUtil;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.annotation.SelectionListener;
import org.seasar.uruma.ui.dialogs.UrumaMessageDialog;
import org.seasar.uruma.util.MessageUtil;

@Form(FileViewAction.class)
public class FileViewAction {
	@ExportValue(id = "fileDetailTable")
	public List<FileDto> fileList = new ArrayList<FileDto>();

	@ImportSelection(id = "fileDetailTable")
	public List<FileDto> selectedFile;

	@ImportExportValue
	public String statusLineManager;

	@SelectionListener(partId = "folderView")
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
	public void onDoubleClick() {
		if (selectedFile.size() == 1) {
			FileDto dto = selectedFile.get(0);
			File file = new File(dto.absolutePath);

			if (file.isDirectory()) {
			} else {
				openFile();
			}
		}
	}

	@EventListener(id = "fileDetailTable")
	public void onSelected() {
		if (selectedFile.size() > 0) {
			statusLineManager = MessageUtil.getMessage("SELECTED", selectedFile
					.size());
		}
	}

	@EventListener(id = "fileOpen")
	public void onFileOpenMenu() {
		openFile();
	}

	private void openFile() {
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

	@EventListener(id = "fileRename")
	public void renameFile() {
		if (selectedFile.size() == 1) {
			FileDto dto = selectedFile.get(0);
			String newName = UrumaMessageDialog.openInput("RENAME",
					dto.fileName);
			if (newName != null) {
				File oldFile = new File(dto.absolutePath);
				File newFile = new File(oldFile.getParent() + File.separator
						+ newName);

				oldFile.renameTo(newFile);

				dto.absolutePath = newFile.getAbsolutePath();
				dto.fileName = newFile.getName();
			}
		}
	}

	@EventListener(id = "fileDelete")
	public void deleteFile() {
		if (selectedFile.size() > 0) {
			if (UrumaMessageDialog.openConfirm("DELETE")) {
				for (FileDto dto : selectedFile) {
					File file = new File(dto.absolutePath);
					file.delete();
					fileList.remove(dto);
				}
			}

		}
	}
}
