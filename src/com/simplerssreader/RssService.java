package com.simplerssreader;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.List;

import org.xmlpull.v1.XmlPullParserException;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;

public class RssService extends IntentService {

	protected static final String ITEMS = "items";
	public static final String RECEIVER = "receiver";
	public static final int SUCCESS = 1;
	public static final int ERROR = 0;

	public RssService() {
		super("RssService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Log.d(Constants.TAG, "Service started");
		List<RssItem> rssItems = null;
		try {
			PcWorldRssParser parser = new PcWorldRssParser();
			rssItems = parser.parse(getInputStream("http://www.pcworld.com/index.rss"));
		} catch (XmlPullParserException e) {
			e.printStackTrace();
			Log.w(e.getMessage(), e);
		} catch (IOException e) {
			Log.w(e.getMessage(), e);
		}

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Bundle bundle = new Bundle();
		bundle.putSerializable(ITEMS, (Serializable) rssItems);
		ResultReceiver receiver = intent.getParcelableExtra(RECEIVER);
		receiver.send(0, bundle);
	}

	public InputStream getInputStream(String link) {
		try {
			URL url = new URL(link);
			return url.openConnection().getInputStream();
		} catch (IOException e) {
			Log.w(Constants.TAG, "Exception while retrieving the input stream", e);
			return null;
		}
	}
}
