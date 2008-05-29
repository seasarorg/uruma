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

import org.eclipse.jface.dialogs.MessageDialog;
import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.rss.constants.WidgetConstants;
import org.seasar.uruma.example.rss.dto.FeedDto;
import org.seasar.uruma.example.rss.dto.FeedEntryDto;
import org.seasar.uruma.example.rss.dto.NodeDto;
import org.seasar.uruma.example.rss.logic.FeedLogic;
import org.seasar.uruma.ui.dialogs.UrumaMessageDialog;

/**
 * フィードのツリー表示です。<br />
 * 
 * @author y.sugigami
 */
@Form(FeedTreeViewAction.class)
public class FeedTreeViewAction {
	
	/**
	 * フィードのロジックです。<br />
	 */
	public FeedLogic feedLogic;
	
	/**
	 * ツリーウィジェットに表示するフィードのデータです。<br />
	 */
	@ExportValue(id = WidgetConstants.FEED_TREE)
	public NodeDto<FeedDto> root;

	/**
	 * フィードのURLです。<br />
	 */
	private String[] feedUrls = new String[] {
			"http://www.amazon.co.jp/rss/bestsellers/electronics/3371341/ref=pd_ts_rss_link",
			"http://b.hatena.ne.jp/hotentry?mode=rss", 
			"http://www.pheedo.jp/f/slashdot_japan"
			};
	
	
	/**
	 * 初期化処理です。<br />
	 */
	@InitializeMethod 
	public void initialize() {
		FeedDto dummy = new FeedDto();
		root = new NodeDto<FeedDto>();
		root.setTarget(dummy);
		
		for (String feedUrl : feedUrls) {
			FeedDto feedDto = feedLogic.fetch(feedUrl);
			NodeDto<FeedDto> child = new NodeDto<FeedDto>();
			child.setTarget(feedDto);
			root.addChild(child);
		}
	}
}
