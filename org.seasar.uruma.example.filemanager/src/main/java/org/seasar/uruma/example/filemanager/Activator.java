package org.seasar.uruma.example.filemanager;

import org.eclipse.jface.resource.ImageDescriptor;
import org.osgi.framework.BundleContext;
import org.seasar.eclipse.rcp.ui.S2RcpActivator;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends S2RcpActivator {

	// The plug-in ID
	public static final String PLUGIN_ID = "org.seasar.uruma.example.fileman";

	// The shared instance
	private static Activator plugin;

	/**
	 * The constructor
	 */
	public Activator() {
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given plug-in
	 * relative path
	 * 
	 * @param path
	 *            the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(final String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}

	@Override
	protected void s2RcpStart(final BundleContext arg0) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	protected void s2RcpStop(final BundleContext arg0) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	}
}
