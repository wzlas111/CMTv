package com.cmtv.tv.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

public class BaseViewPager extends ViewGroup {

	public BaseViewPager(Context context) {
		super(context);
	}

	public BaseViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {

	}

}
