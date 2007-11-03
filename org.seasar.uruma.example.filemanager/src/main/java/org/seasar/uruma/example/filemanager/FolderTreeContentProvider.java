package org.seasar.uruma.example.filemanager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

public class FolderTreeContentProvider implements ITreeContentProvider {

	public Object[] getChildren(Object parentElement) {
		File parentFolder = (File) parentElement;
		File[] children = parentFolder.listFiles();
		List<File> temp = new ArrayList<File>();

		for (File child : children) {
			if (child.isDirectory()) {
				temp.add(child);
			}
		}

		return temp.toArray(new File[temp.size()]);
	}

	public Object getParent(Object element) {
		File file = (File) element;
		return file.getParentFile();
	}

	public boolean hasChildren(Object element) {
		File file = (File) element;
		return file.isDirectory();
	}

	public Object[] getElements(Object inputElement) {
		return getChildren(inputElement);
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
