package org.seasar.uruma.example.filemanager;

import java.io.File;

import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;

@Form(FolderViewAction.class)
public class FolderViewAction {
	@ExportValue(id = "folderTree")
	public File root = new File("c:/");
}
