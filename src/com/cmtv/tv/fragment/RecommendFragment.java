package com.cmtv.tv.fragment;

import com.cmtv.tv.R;
import com.cmtv.tv.widget.recommend.SmoothHorizontalScrollView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class RecommendFragment extends BaseFragment implements SmoothHorizontalScrollView.OnScrollPageListener{
	
	private Activity mContext;
	private SmoothHorizontalScrollView mScrollView;
	private RelativeLayout mContainer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = getActivity();
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (mScrollView == null) {
			mScrollView = new SmoothHorizontalScrollView(getActivity());
			mScrollView.setHorizontalScrollBarEnabled(false);
			mScrollView.setOnScrollPageListener(this);
			mContainer = (RelativeLayout)inflater.inflate(R.layout.fragment_recommend_layout, container, false);
//			findTopViews();
//		    findBottomViews();
			mScrollView.addView(container);
		}
		return mScrollView;
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	
	@Override
	public void onScrollPage(boolean isCircleScroll) {
		
	}
	
	private static RecommendFragment mRecommendFragment;

	public static RecommendFragment getInstance() {
		if (mRecommendFragment == null) {
			mRecommendFragment = new RecommendFragment();
		}
		return mRecommendFragment;
	}

}
