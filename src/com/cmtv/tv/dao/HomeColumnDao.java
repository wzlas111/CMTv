package com.cmtv.tv.dao;

import com.cmtv.tv.http.HttpMethod;
import com.cmtv.tv.http.HttpUtility;

public class HomeColumnDao {

	private String mUrl;
	
	public HomeColumnDao(String url) {
		this.mUrl = url;
	}
	
	public String getJSON() throws Exception{
		return HttpUtility.getInstance().executeNormalTask(HttpMethod.Get, mUrl, null);
	}
}
