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

import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.ExportSelection;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.example.filemanager.Constants;

/**
 * フォルダビューのためのアクションクラスです。<br />
 * 
 * @author y-komori
 */
@Form(FolderViewAction.class)
public class FolderViewAction implements Constants {
	@ExportValue(id = "folderTree")
	public File root = new File(ROOT_PATH);

	@ExportSelection(id = "folderTree")
	public File selection = root;

	/**
	 * ファイルビューでファイルが選択されたときに呼び出されるメソッドです。<br />
	 * 
	 * @param selectedFile
	 *            選択されている {@link File} オブジェクト
	 */
	@EventListener(id = "fileDetailTable", type = EventListenerType.MOUSE_DOUBLE_CLICK)
	public void fileSelected(final File selectedFile) {
		if (selectedFile.isDirectory()) {
			// フォルダが選択されたらツリー側も対応するフォルダを選択状態にする
			this.selection = selectedFile;
		}
	}
}
