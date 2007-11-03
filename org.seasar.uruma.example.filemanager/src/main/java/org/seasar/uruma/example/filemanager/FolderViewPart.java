package org.seasar.uruma.example.filemanager;

import java.io.File;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;
import org.seasar.eclipse.rcp.ui.S2RcpViewPart;

public class FolderViewPart extends S2RcpViewPart {
	private TreeViewer folderTree;

	public FolderViewPart() {
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		folderTree.setInput(new File("c:/"));

		getSite().setSelectionProvider(folderTree);
	}

	@Override
	public void setFocus() {
		folderTree.getControl().setFocus();
	}
}
