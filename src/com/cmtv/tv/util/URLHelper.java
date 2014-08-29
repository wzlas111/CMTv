package com.cmtv.tv.util;

public class URLHelper {
	
	/**
	 * 
	 * http://ott.hd.sohu.com/v2/index/column.json?&api_key=7ad23396564b27116418d3c03a77db45&poid=poid&plat=platform&sver=sver&partner=partner_no&ts=1231231231
	 * http://ott.hd.sohu.com/v2/index/recommend.json?&api_key=7ad23396564b27116418d3c03a77db45&poid=poid&plat=platform&sver=sver&partner=partner_no&ts=1231231231
	 */
	
	public static String server_domain = "http://ott.hd.sohu.com";
	public static String API_KEY = "7ad23396564b27116418d3c03a77db45";
	public static String COMMON_PARAMS = "api_key=7ad23396564b27116418d3c03a77db45&poid=poid&plat=platform&sver=sver&partner=partner_no";

	public static String getHomePageColumnsUrl() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(server_domain)
			  .append("/v2/index/column.json?")
			  .append("&")
			  .append(getSystemParams());
		return buffer.toString();
	}
	
	private static String getSystemParams() {
		StringBuffer buffer = new StringBuffer(COMMON_PARAMS);
		buffer.append("&ts=");
		buffer.append(System.currentTimeMillis());
		return buffer.toString();
	}
}
