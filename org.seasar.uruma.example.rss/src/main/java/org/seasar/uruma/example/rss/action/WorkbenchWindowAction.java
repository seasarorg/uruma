/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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
package org.seasar.uruma.example.rss.action;

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.IWorkbenchPreferenceConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.rcp.configuration.ContributionBuilder;

/**
 *  アプリの初期化処理です。 <br />
 * 
 * @author y.sugigami
 */
public class WorkbenchWindowAction {
	
	/* INJECT */
	public IWorkbenchConfigurer workbenchConfigurer;
	
	public IActionBarConfigurer actionBarConfigurer;
	
	public IWorkbenchWindowConfigurer workbenchWindowConfigurer;

	/**
	 * 初期化処理 <br />
	 */
	@InitializeMethod 
	public void initialize() {
		config();
		createViewMenu();
		createPerspectiveMenu();
		
		System.out.println(ContributionBuilder.getContent());
	}
	
	/**
	 * コンフィグ設定。 <br />
	 */
	private void config() {
		// タブスタイルをEclipse 3.0のようにかっこよくする
		PlatformUI.getPreferenceStore().setValue( IWorkbenchPreferenceConstants.SHOW_TRADITIONAL_STYLE_TABS , false );
	}
	
	/**
	 *  ビュー メニューを追加する。 <br />
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
	 * パースペクティブ メニューを追加する. <br />
	 */
	private void createPerspectiveMenu() {
		IWorkbenchWindow workbenchWindow = workbenchWindowConfigurer.getWindow();
		IContributionItem perspectivesMenu = ContributionItemFactory.PERSPECTIVES_SHORTLIST.create(workbenchWindow);
	    MenuManager layoutMenu = new MenuManager("パースペクティブの表示(&P)", "layoutId");
	    actionBarConfigurer.getMenuManager().add(layoutMenu);
	    layoutMenu.add(perspectivesMenu);
	}
	
	
	@EventListener(id = "menu1", type = EventListenerType.SELECTION)
	public void doMenu1(final Object obj) {
		MessageDialog.openInformation(null, "メニュー１", "メニュー１クリック！！");
	}

	@EventListener(id = "menu2", type = EventListenerType.SELECTION)
	public void doMenu2(final Object obj) {
		MessageDialog.openInformation(null, "メニュー２", "メニュー２クリック！！");
	}

	@EventListener(id = "menu3", type = EventListenerType.SELECTION)
	public void doMenu3(final Object obj) {
		MessageDialog.openInformation(null, "メニュー３", "メニュー３クリック！！");
	}
}