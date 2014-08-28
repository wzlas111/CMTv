package com.cmtv.tv;

import com.cmtv.tv.util.log.LogManager;

import android.app.Application;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class TvApplication extends Application {
	
	private static final String TAG = "TvApplication";

	private static TvApplication instance = null;
	
	public DisplayMetrics mDisplayMetrics;
	public static int mScreenWidth;
	public static int mScreenHeight;
	
	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
		initMetrics();
	}
	
	public static TvApplication getInstance() {
		return instance;
	}
	
	private void initMetrics() {
		this.mDisplayMetrics = new DisplayMetrics();
		((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mDisplayMetrics);
		mScreenWidth = mDisplayMetrics.widthPixels;
		mScreenHeight = mDisplayMetrics.heightPixels;
		LogManager.i(TAG, "DisplayMetrics : "+mDisplayMetrics.toString());
	}
}
