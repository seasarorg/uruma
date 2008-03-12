package org.seasar.uruma.example.rss.logic;

import org.seasar.uruma.example.rss.dto.FeedDto;

public interface FeedLogic {

	public FeedDto fetch(String url);
	
}
