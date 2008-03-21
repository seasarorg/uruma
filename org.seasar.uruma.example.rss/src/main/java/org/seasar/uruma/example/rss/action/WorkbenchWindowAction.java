package org.seasar.uruma.example.rss.action;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.seasar.uruma.annotation.InitializeMethod;

public class WorkbenchWindowAction {
	
	/* INJECT */
	public IWorkbenchConfigurer workbenchConfigurer;
	
	public IActionBarConfigurer actionBarConfigurer;
	
	public IWorkbenchWindowConfigurer workbenchWindowConfigurer;

	@InitializeMethod 
	public void initialize() {
		config();
		createViewMenu();
		createPerspectiveMenu();
	}
	
	private void config() {
		// ウィンドウの位置・サイズを保存する
		workbenchConfigurer.setSaveAndRestore(true);
		
		// perspective を切り替える perspective bar を表示
		workbenchWindowConfigurer.setShowPerspectiveBar(true);
		
		// Eclipse標準の「保存」を使う
//		ActionFactory.SAVE.create( window );
		
		// Coolbarとperspectivebarを同じ行に配置する
		PlatformUI.getPreferenceStore().setValue( IWorkbenchPreferenceConstants.DOCK_PERSPECTIVE_BAR , "top" );
		
		// タブスタイルをEclipse 3.0のようにかっこよくする
		PlatformUI.getPreferenceStore().setValue( IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS , false );
	}
	
	/**
	 *  ビュー メニューを追加する。
	 * 
	 */
	private void createViewMenu() {
		IWorkbenchWindow workbenchWindow = workbenchWindowConfigurer.getWindow();
	    MenuManager showViewMenu = new MenuManager("ビューの表示(&V)");
	    showViewMenu.add(ContributionItemFactory.VIEWS_SHORTLIST.create(workbenchWindow));
	    actionBarConfigurer.getMenuManager().add(showViewMenu);
	    // 複数のウィンドウが開かれている場合にのみ表示され、
	    // ウィンドウの切り替えを行うアクション
	    actionBarConfigurer.getMenuManager().add(ContributionItemFactory.OPEN_WINDOWS.create(workbenchWindow));
	}
	
	/**
	 * パースペクティブ メニューを追加する.
	 * 
	 */
	private void createPerspectiveMenu() {
		IWorkbenchWindow workbenchWindow = workbenchWindowConfigurer.getWindow();
		IContributionItem perspectivesMenu = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(workbenchWindow);
	    MenuManager layoutMenu = new MenuManager("パースペクティブの表示(&P)", "layoutId");
	    actionBarConfigurer.getMenuManager().add(layoutMenu);
	    layoutMenu.add(perspectivesMenu);
	}
}