package com.cmtv.tv.widget;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.cmtv.tv.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TopLayout extends RelativeLayout {

	private ImageView mBackIcon = (ImageView)findViewById(R.id.back_icon);
	private ImageView mWifiIcon = (ImageView)findViewById(R.id.wifi_icon);
	private ImageView mBottomImage = (ImageView)findViewById(R.id.top_layout_bottom_image);
	private TextView mTitleView = (TextView)findViewById(R.id.text_title);
	private TextView mSubTitleView = (TextView)findViewById(R.id.text_sub_title);
	private TextView mTimeView = (TextView)findViewById(R.id.text_time);
	private TextView mCategoryTextView = (TextView)findViewById(R.id.text_category);
	
	public TopLayout(Context context) {
		super(context, null);
	}
	
	public TopLayout(Context context, AttributeSet attrs) {
		super(context, attrs, 0);
	}

	public TopLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setTopLayoutItemsVisible(boolean isBack,boolean isWifi,boolean isBottom,boolean isTime,
			boolean isTitle,String pTitle,
			boolean isSubTitle,String pSubTitle,
			boolean isCategory,String pCatory) {
		if (isBack) {
			mBackIcon.setVisibility(View.VISIBLE);
		} else {
			mBackIcon.setVisibility(View.GONE);
		}
		if (isWifi) {
			mWifiIcon.setVisibility(View.VISIBLE);
		} else {
			mWifiIcon.setVisibility(View.GONE);
		}
		if (isBottom) {
			mBottomImage.setVisibility(View.VISIBLE);
		} else {
			mBottomImage.setVisibility(View.GONE);
		}
		if (isTime) {
			mTimeView.setVisibility(View.VISIBLE);
			String now = new SimpleDateFormat("HH:mm").format(new Date());
			mTimeView.setText(now);
		} else {
			mTimeView.setVisibility(View.GONE);
		}
		if (isTitle) {
			mTitleView.setVisibility(View.VISIBLE);
			if (pTitle != null) {
				mTitleView.setText(pTitle);
			}
		} else {
			mTitleView.setVisibility(View.GONE);
		}
		if (isSubTitle) {
			mSubTitleView.setVisibility(View.VISIBLE);
			if (pSubTitle != null) {
				mSubTitleView.setText(pSubTitle);
			}
		} else {
			mSubTitleView.setVisibility(View.GONE);
		}
		if (isCategory) {
			mCategoryTextView.setVisibility(View.VISIBLE);
			if (pCatory != null) {
				mCategoryTextView.setText(pCatory);
			}
		} else {
			mCategoryTextView.setVisibility(View.GONE);
		}
	}
	
}
