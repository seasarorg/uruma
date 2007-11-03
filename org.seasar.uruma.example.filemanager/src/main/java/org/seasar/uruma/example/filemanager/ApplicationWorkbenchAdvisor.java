package org.seasar.uruma.example.filemanager;

import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.seasar.eclipse.rcp.ui.S2RcpWorkbenchAdvisor;

public class ApplicationWorkbenchAdvisor extends S2RcpWorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "org.seasar.rcp.example.fileman.perspective";

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}
}
