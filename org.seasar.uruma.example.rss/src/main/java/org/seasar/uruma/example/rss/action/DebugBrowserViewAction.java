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

import org.eclipse.swt.browser.Browser;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.rss.constants.WidgetConstants;
import org.seasar.uruma.example.rss.util.S2ContainerListtoHtmlUtil;


/**
 * S2ContainerのComponentsを表示 <br />
 * 
 * @author y.sugigami
 */
@Form(DebugBrowserViewAction.class)
public class DebugBrowserViewAction  {
	
	/**
	 * Browserのhtml <br />
	 */
	@ExportValue(id = WidgetConstants.DEBUG_BROWSER)
	public String html;

	/**
	 * Browser Widget <br />
	 */
	public Browser debugBrowser;  

	/**
	 * 初期化処理 <br />
	 */
	@InitializeMethod
	public void initialize() {
		html = S2ContainerListtoHtmlUtil.list("");
	}
}
