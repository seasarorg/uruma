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
	public List<File> fileList = new ArrayList<File>();

	@ImportSelection(id = "fileDetailTable")
	public List<File> selectedFile;

	@ImportExportValue
	public String statusLineManager;

	@SelectionListener(partId = "folderView")
	public void selectionChanged(final File parentFolder) {
		fileList.clear();

		File[] children = parentFolder.listFiles();
		for (File file : children) {
			fileList.add(file);
		}
	}

	@EventListener(id = "fileDetailTable", type = EventListenerType.MOUSE_DOUBLE_CLICK)
	public void onDoubleClick() {
		if (selectedFile.size() == 1) {
			File file = selectedFile.get(0);

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
		File file = selectedFile.get(0);
		if (file.isFile()) {
			Program program = Program.findProgram(StringUtil.substringToLast(
					file.getName(), "."));
			if (program != null) {
				Program.launch(file.getAbsolutePath());
			}
		}
	}

	@EventListener(id = "fileRename")
	public void renameFile() {
		File oldFile = selectedFile.get(0);
		String newName = UrumaMessageDialog.openInput("RENAME", oldFile
				.getName());
		if (newName != null) {
			File newFile = new File(oldFile.getParent() + File.separator
					+ newName);

			oldFile.renameTo(newFile);
		}
	}

	@EventListener(id = "fileDelete")
	public void deleteFile() {
		if (UrumaMessageDialog.openConfirm("DELETE")) {
			for (File file : selectedFile) {
				file.delete();
				fileList.remove(file);
			}
		}
	}
}
