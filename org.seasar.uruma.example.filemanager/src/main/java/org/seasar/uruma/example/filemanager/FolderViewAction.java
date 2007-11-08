package org.seasar.uruma.example.filemanager;

import org.eclipse.jface.viewers.TreeViewer;
import org.seasar.uruma.annotation.Form;

@Form(FolderViewAction.class)
public class FolderViewAction {
	public TreeViewer folderTree;

	public FolderViewAction() {
	}

	// @Override
	// public void createPartControl(final Composite parent) {
	// super.createPartControl(parent);
	//
	// folderTree.setInput(new File("c:/"));
	//
	// getSite().setSelectionProvider(folderTree);
	// }
	//
	// @Override
	// public void setFocus() {
	// folderTree.getControl().setFocus();
	// }
}
