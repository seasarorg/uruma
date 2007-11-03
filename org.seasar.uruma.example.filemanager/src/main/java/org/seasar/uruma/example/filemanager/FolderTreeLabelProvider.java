package org.seasar.uruma.example.filemanager;

import java.io.File;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.seasar.eclipse.common.util.ImageManager;

public class FolderTreeLabelProvider extends LabelProvider {

	@Override
	public Image getImage(Object element) {
		Image folderImage = ImageManager.loadImage("folder");
		return folderImage;
	}

	@Override
	public String getText(Object element) {
		File folder = (File) element;
		return folder.getName();
	}
}
