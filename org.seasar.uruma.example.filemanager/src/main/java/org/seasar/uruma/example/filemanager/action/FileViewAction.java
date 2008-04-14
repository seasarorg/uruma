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
package org.seasar.uruma.example.filemanager.action;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.program.Program;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.ImportSelection;
import org.seasar.uruma.example.filemanager.Constants;
import org.seasar.uruma.ui.dialogs.UrumaMessageDialog;
import org.seasar.uruma.util.MessageUtil;
import org.seasar.uruma.util.PathUtil;

/**
 * ファイルビューのためのアクションクラスです。<br />
 * 
 * @author y-komori
 */
@Form(FileViewAction.class)
public class FileViewAction implements Constants {
	@ExportValue(id = "fileDetailTable")
	public List<File> fileList = new ArrayList<File>();

	@ImportSelection(id = "fileDetailTable")
	public List<File> selectedFile;

	private File selectedFolder;

	@ImportExportValue
	public String statusLineManager;

	/**
	 * フォルダツリーのノードが選択されたときに呼び出されるメソッドです。<br />
	 * 
	 * @param selectedFolder
	 *            選択されたノードに対応する {@link File} オブジェクト
	 */
	@EventListener(id = "folderTree")
	public void onFolderSelected(final File selectedFolder) {
		this.selectedFolder = selectedFolder;
		if (selectedFolder != null) {
			listFiles(selectedFolder);
		}
	}

	/**
	 * ファイルビューでファイルをダブルクリックしたときに呼び出されるメソッドです。<br />
	 */
	@EventListener(id = "fileDetailTable", type = EventListenerType.MOUSE_DOUBLE_CLICK)
	public void onDoubleClick() {
		if (selectedFile.size() == 1) {
			File file = selectedFile.get(0);

			if (file.isDirectory()) {
				// フォルダがダブルクリックされた時はフォルダを開く
				listFiles(file);
			} else {
				// ファイルの場合はファイルを開く
				openFile();
			}
		}
	}

	/**
	 * ファイルビューでファイルが選択されたときに呼び出されるメソッドです。<br />
	 */
	@EventListener(id = "fileDetailTable")
	public void onSelected() {
		// ステータスラインに選択しているファイルの数を表示
		if (selectedFile.size() > 0) {
			statusLineManager = MessageUtil.getMessage("SELECTED", selectedFile
					.size());
		}
	}

	private void refresh() {
		if (selectedFolder != null) {
			listFiles(selectedFolder);
		}
	}

	private void listFiles(final File parent) {
		fileList.clear();
		if (!parent.getPath().equals(MY_COMPUTER_PATH)) {
			File[] children = parent.listFiles();
			if (children != null) {
				for (File file : children) {
					fileList.add(file);
				}
			}
		}
	}

	/**
	 * 選択されているファイルを開きます。<br />
	 */
	@EventListener(id = "fileOpen")
	public void openFile() {
		File file = selectedFile.get(0);
		if (file.isFile()) {
			Program program = Program.findProgram(PathUtil.getExt(file
					.getName()));
			if (program != null) {
				Program.launch(file.getAbsolutePath());
			}
		}
	}

	/**
	 * 選択されているファイルの名前を変更します。<br />
	 */
	@EventListener(id = "fileRename")
	public void renameFile() {
		File oldFile = selectedFile.get(0);
		String newName = UrumaMessageDialog.openInput("RENAME", oldFile
				.getName());
		if (newName != null) {
			File newFile = new File(oldFile.getParent() + File.separator
					+ newName);

			oldFile.renameTo(newFile);

			refresh();
		}
	}

	/**
	 * 選択されているファイルを削除します。<br />
	 */
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
