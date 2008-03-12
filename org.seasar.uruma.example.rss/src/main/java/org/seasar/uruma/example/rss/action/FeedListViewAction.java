package org.seasar.uruma.example.rss.action;

import java.util.ArrayList;
import java.util.List;

import org.seasar.uruma.annotation.EventListener;
import org.seasar.uruma.annotation.EventListenerType;
import org.seasar.uruma.annotation.ExportValue;
import org.seasar.uruma.annotation.Form;
import org.seasar.uruma.annotation.InitializeMethod;
import org.seasar.uruma.example.rss.dto.FeedDto;
import org.seasar.uruma.example.rss.dto.FeedEntryDto;
import org.seasar.uruma.example.rss.dto.Node;
import org.seasar.uruma.example.rss.logic.FeedLogic;

/**
 * @author y.sugigami
 */
@Form(FeedListViewAction.class)
public class FeedListViewAction  {
	
	public FeedLogic feedLogic;
	
	@ExportValue(id = "feedListTable")
	public List<FeedEntryDto> list = new ArrayList<FeedEntryDto>();

	
	@EventListener(id = "feedTree", type = EventListenerType.SELECTION)
	public void feedSelectedSingle(final Object obj) {
		list.clear();
		FeedDto feedDto = ((Node<FeedDto>) obj).getTarget();
		for (FeedEntryDto feedEntryDto : feedDto.getFeedEntryDtoList()) {
			list.add(feedEntryDto);
		}
	}

	@InitializeMethod
	public void initialize() {
		// Do Nothing
	}

}
