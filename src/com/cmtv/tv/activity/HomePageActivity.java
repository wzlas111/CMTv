package com.cmtv.tv.activity;

import java.util.ArrayList;
import java.util.List;

import com.cmtv.tv.R;
import com.cmtv.tv.bean.APIResponseBean;
import com.cmtv.tv.bean.HomeColumnBean;
import com.cmtv.tv.fragment.ClassificationFragment;
import com.cmtv.tv.fragment.FoxPlayFragment;
import com.cmtv.tv.fragment.HomePageUserFragment;
import com.cmtv.tv.fragment.RecommendFragment;
import com.cmtv.tv.fragment.TestFragment;
import com.cmtv.tv.widget.BaseViewPager;
import com.cmtv.tv.widget.TopLayout;
import com.cmtv.tv.widget.pagerindicator.TabPageIndicator;
import com.google.gson.Gson;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class HomePageActivity extends BaseActivity {

	public static final String[] CONTENT = { "推荐", "分类", "直播", "我的" };
	
	private TabPageIndicator mtTabPageIndicator;
	private HomePageFragmentAdapter mAdapter;
	private BaseViewPager mViewPager;
	private ImageView mCursor;
	private ColumnsLoadAsyncTask mColumnsLoadAsyncTask;
	
	private List<HomeColumnBean.HomeColumn> mColumns = new ArrayList<HomeColumnBean.HomeColumn>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivty_home_page);
		initView();
		initFragment();
//		mColumnsLoadAsyncTask = new ColumnsLoadAsyncTask();
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
	
	private void initView() {
		mCursor = (ImageView)findViewById(R.id.tab_page_bg);
		mtTabPageIndicator = new TabPageIndicator(this);
		mtTabPageIndicator.setHorizontalScrollBarEnabled(false);
		this.mTopLayout = getTopLayout();
		if (mTopLayout != null) {
			mTopLayout.setTopLayoutItemsVisible(false, true, false, true, false, null, false, null, false, null);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			mTopLayout.addView(mtTabPageIndicator, layoutParams);
		}
	}
	
	private void initFragment() {
		mAdapter = new HomePageFragmentAdapter(getSupportFragmentManager());
		mViewPager = (BaseViewPager)findViewById(R.id.pager);
		mViewPager.setAdapter(mAdapter);
		mtTabPageIndicator.setBaseViewPager(mViewPager);
		mtTabPageIndicator.requestFirstTabSelected();
	}
	
	public void startCursorAnimation(int start,int end) {
		final FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams)mCursor.getLayoutParams();
		final int leftMargin = layoutParams.leftMargin;
		
		final int pos = end - start;
		ValueAnimator animator = ValueAnimator.ofFloat(new float[] {0.0f, 1.0f});
		animator.setDuration(50l);
		animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				float currtentVal = ((Float)animation.getAnimatedValue()).floatValue();
				layoutParams.leftMargin = leftMargin + pos * (int)currtentVal;
				mCursor.setLayoutParams(layoutParams);
			}
		});
		animator.addListener(new ValueAnimator.AnimatorListener() {
			public void onAnimationStart(Animator animation) {}
			public void onAnimationRepeat(Animator animation) {}
			public void onAnimationCancel(Animator animation) {}
			
			@Override
			public void onAnimationEnd(Animator animation) {
				layoutParams.leftMargin = leftMargin + pos;
				mCursor.setLayoutParams(layoutParams);
			}
		});
		animator.start();
	}
	
	/**
	 * Adapter data
	 * @author wangzl
	 *
	 */
	public class ColumnsLoadAsyncTask extends AsyncTask<String, Integer, String> {

		public ColumnsLoadAsyncTask() {}
		
		@Override
		protected String doInBackground(String... params) {
			return null;
		}
		
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			APIResponseBean<HomeColumnBean> apiBean = new Gson().fromJson(result, APIResponseBean.class);
			mColumns.addAll(apiBean.getData().getColumns());
		}
	}
	
	/**
	 * PageIndicator adapter
	 * @author wangzl
	 *
	 */
	public class HomePageFragmentAdapter extends FragmentPagerAdapter {

		public HomePageFragmentAdapter(FragmentManager fm) {
			super(fm);
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return HomePageActivity.CONTENT[position];
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return RecommendFragment.getInstance();
			case 1:
				return ClassificationFragment.getInstance();
			case 2:
				return FoxPlayFragment.getInstance();
			case 3:
				return HomePageUserFragment.getInstance();
			}
			return TestFragment.getInstance();
		}

		@Override
		public int getCount() {
			return HomePageActivity.CONTENT.length;
		}
		
	}
}
