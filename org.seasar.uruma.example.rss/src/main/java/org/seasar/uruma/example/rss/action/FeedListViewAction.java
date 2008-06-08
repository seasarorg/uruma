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

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.rss.constants.WidgetConstants;
import org.seasar.uruma.example.rss.dto.FeedDto;
import org.seasar.uruma.example.rss.dto.FeedEntryDto;
import org.seasar.uruma.example.rss.dto.NodeDto;
import org.seasar.uruma.example.rss.logic.FeedLogic;

/**
 * フィードエントリのリスト表示です。<br />
 * 
 * @author y.sugigami
 */
public class FeedListViewAction {

    /**
     * フィードのロジックです。<br />
     */
    public FeedLogic feedLogic;

    /**
     * フィードエントリのリストです。<br />
     */
    @ExportValue(id = WidgetConstants.FEED_LIST_TABLE)
    public List<FeedEntryDto> list = new ArrayList<FeedEntryDto>();

    /**
     * フィードが選択されたときに呼び出されるメソッドです。<br />
     * 
     * @param selected
     *            選択されている {@link NodeDto} オブジェクト
     */
    @EventListener(id = WidgetConstants.FEED_TREE, type = EventListenerType.SELECTION)
    public void feedSelectedSingle(final Object obj) {
        list.clear();
        FeedDto feedDto = ((NodeDto<FeedDto>) obj).getTarget();
        for (FeedEntryDto feedEntryDto : feedDto.getFeedEntryDtoList()) {
            list.add(feedEntryDto);
        }
    }

    /**
     * 初期化処理です。<br />
     */
    @InitializeMethod
    public void initialize() {
        // Do Nothing
    }

}
