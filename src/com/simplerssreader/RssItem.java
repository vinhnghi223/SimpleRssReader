package com.simplerssreader;

/**
 * A representation of an rss item from the list.
 * 
 * @author Veaceslav Grec
 * 
 */
public class RssItem {

	private String title;
	private String link;

	public RssItem(String title, String link) {
		this.title = title;
		this.link = link;
	}

	public RssItem() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
