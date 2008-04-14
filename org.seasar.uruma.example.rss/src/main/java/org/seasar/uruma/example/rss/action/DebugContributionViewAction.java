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

import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.ImportExportValue;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.rss.constants.WidgetConstants;
import org.seasar.uruma.rcp.configuration.ContributionBuilder;

/**
 * コントリビュートXMLを表示する。<br />
 * 
 * @author y.sugigami
 */
@Form(DebugContributionViewAction.class)
public class DebugContributionViewAction {

	/**
	 * コントリビュートのテキスト。<br />
	 */
	@ImportExportValue(id = WidgetConstants.DEBUG_CONTRIBUTION_TEXT)
	public String text;
	
	/**
	 * 初期化処理。<br />
	 */
	@InitializeMethod
	public void initialize() {
		text = ContributionBuilder.getContent();
	}
}
