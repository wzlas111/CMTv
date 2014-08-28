package com.cmtv.tv.util.log;

import android.util.Log;

public class LogManager {

	private static final String TAG = "com.cmtv.tv";
	public static final boolean DEBUG = true;
	public static final boolean CAN_SHOW = true;
	
	public static boolean canShow() {
		return canShow();
	}
	
	public static void d(String msg) {
		if (canShow() && msg != null) {
			Log.d(TAG, msg);
		}
	}
	
	public static void d(String tag, String msg) {
		if (canShow() && msg != null) {
			Log.d(tag, msg);
		}
	}
	
	public static void i(String msg) {
		if (canShow() && msg != null) {
			Log.i(TAG, msg);
		}
	}
	
	public static void i(String tag, String msg) {
		if (canShow() && msg != null) {
			Log.i(tag, msg);
		}
	}
	
	public static void w(String msg) {
		if (canShow() && msg != null) {
			Log.w(TAG, msg);
		}
	}
	
	public static void w(String tag, String msg) {
		if (canShow() && msg != null) {
			Log.w(tag, msg);
		}
	}
	
	public static void e(String msg) {
		if (canShow() && msg != null) {
			Log.e(TAG, msg);
		}
	}
	
	public static void e(String tag, String msg) {
		if (canShow() && msg != null) {
			Log.e(tag, msg);
		}
	}
}
