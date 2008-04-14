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
package org.seasar.uruma.example.rss.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * フィードのDtoです。 <br />
 * 
 * @author y.sugigami
 */
public class FeedDto {
	
	private String url;
	
	private Date publishedDate;
	
	private String feedType;
	
	private String author;
	
	private String copyright;
	
	private String description;
	
	private String encoding;
	
	private String link;
	
	private String title;
	
	private List<FeedEntryDto> feedEntryDtoList = new ArrayList<FeedEntryDto>();

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getFeedType() {
		return feedType;
	}

	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<FeedEntryDto> getFeedEntryDtoList() {
		return feedEntryDtoList;
	}

	public void setFeedEntryDtoList(List<FeedEntryDto> feedEntryDtoList) {
		this.feedEntryDtoList = feedEntryDtoList;
	}
	
}
