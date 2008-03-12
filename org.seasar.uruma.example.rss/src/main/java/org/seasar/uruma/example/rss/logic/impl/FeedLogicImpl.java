package org.seasar.uruma.example.rss.logic.impl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import org.seasar.framework.beans.util.Beans;
import org.seasar.uruma.example.rss.dto.FeedDto;
import org.seasar.uruma.example.rss.dto.FeedEntryDto;
import org.seasar.uruma.example.rss.exception.AppRuntimeException;
import org.seasar.uruma.example.rss.logic.FeedLogic;

import com.sun.syndication.feed.synd.SyndContent;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.fetcher.FeedFetcher;
import com.sun.syndication.fetcher.FetcherException;
import com.sun.syndication.fetcher.impl.HttpURLFeedFetcher;
import com.sun.syndication.io.FeedException;


public class FeedLogicImpl implements FeedLogic {

	public FeedDto fetch(String url) {
		SyndFeed feed = fetchSyndFeed(url);
		FeedDto feedDto = Beans.createAndCopy(FeedDto.class, feed).execute();
		for (int i = 0; i < feed.getEntries().size(); i++) {
			SyndEntry entry = (SyndEntry) feed.getEntries().get(i);
			SyndContent syndContent = entry.getDescription();
			
			FeedEntryDto feedEntryDto = new FeedEntryDto();
			feedEntryDto.setDescription(syndContent == null ? "" : syndContent.getValue());
			feedEntryDto.setTitle(entry.getTitle());
			feedEntryDto.setLink(entry.getLink().trim());
			Date date = entry.getUpdatedDate();
			String dateText = "";
			if (date != null) {
				dateText = date.toString();
			}
			feedEntryDto.setUpdatedDate(dateText);
			feedDto.getFeedEntryDtoList().add(feedEntryDto);
		}
		return feedDto;
	}
	
	protected SyndFeed fetchSyndFeed(String url) {
		// FeedFetcherオブジェクトを作成する  
		FeedFetcher fetcher = new HttpURLFeedFetcher();
		SyndFeed feed = null;
	    try {  
            // 取得するフィードのURLオブジェクトを作成する  
            URL feedUrl = new URL(url);  
              
            // フィードを取得する  
            feed = fetcher.retrieveFeed(feedUrl);  
              
        } catch(MalformedURLException e) {  
        	throw new AppRuntimeException("EAPP0001", e);
        } catch(IOException e) {  
        	throw new AppRuntimeException("EAPP0001", e);
        } catch(FetcherException e) {  
        	throw new AppRuntimeException("EAPP0001", e);
        } catch(FeedException e) {  
        	throw new AppRuntimeException("EAPP0001", e);
        }  
        
        return feed;
	}
}
