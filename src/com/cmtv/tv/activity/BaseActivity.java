package com.cmtv.tv.activity;

import com.cmtv.tv.R;
import com.cmtv.tv.util.img.ImgUtil;
import com.cmtv.tv.widget.TopLayout;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.ViewGroup;

public class BaseActivity extends FragmentActivity {

	protected ViewGroup mContainer;
	private Bitmap mNewBackgroud;
	private Activity mActivity;
	
	protected TopLayout mTopLayout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.mContainer = (ViewGroup)getWindow().getDecorView().findViewById(android.R.id.content);
		this.mNewBackgroud = ImgUtil.decodeSampledBitmapFromResource(getResources(), R.drawable.new_background, 2);
		this.mContainer.setBackgroundDrawable(new BitmapDrawable(getResources(), mNewBackgroud));
		getWindow().getDecorView().setSystemUiVisibility(2);
		mActivity = this;
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	public Activity getActivity() {
		return mActivity;
	}
	
	public TopLayout getTopLayout() {
		this.mTopLayout = (TopLayout)this.mContainer.findViewById(R.id.top_layout);
		return this.mTopLayout;
	}
	
}
