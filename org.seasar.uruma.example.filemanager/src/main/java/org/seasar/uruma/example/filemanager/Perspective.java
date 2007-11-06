package org.seasar.uruma.example.filemanager;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(final IPageLayout layout) {
		layout.setEditorAreaVisible(false);

		layout.addStandaloneView(
				"org.seasar.uruma.example.filemanager.folderView", true,
				IPageLayout.LEFT, 0.2f, layout.getEditorArea());

		layout.addStandaloneView(
				"org.seasar.uruma.example.filemanager.fileView", true,
				IPageLayout.LEFT, 0.2f, layout.getEditorArea());

		layout.addStandaloneView(
				"org.seasar.uruma.example.filemanager.testView1", true,
				IPageLayout.LEFT, 0.2f, layout.getEditorArea());

		layout.addStandaloneView(
				"org.seasar.uruma.example.filemanager.testView2", true,
				IPageLayout.LEFT, 0.2f, layout.getEditorArea());

		layout.addStandaloneView(
				"org.seasar.uruma.example.filemanager.testView3", true,
				IPageLayout.LEFT, 0.2f, layout.getEditorArea());
	}
}
