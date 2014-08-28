package com.cmtv.tv.activity;

import java.util.ArrayList;
import java.util.List;

import com.cmtv.tv.R;
import com.cmtv.tv.bean.APIResponseBean;
import com.cmtv.tv.bean.HomeColumnBean;
import com.cmtv.tv.widget.pagerindicator.TabPageIndicator;
import com.google.gson.Gson;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class HomePageActivity extends BaseActivity {

	private TabPageIndicator mtTabPageIndicator;
	private ColumnsLoadAsyncTask mColumnsLoadAsyncTask;
	
	private List<HomeColumnBean.HomeColumn> mColumns = new ArrayList<HomeColumnBean.HomeColumn>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.acitivty_home_page);
		initView();
		
		mColumnsLoadAsyncTask = new ColumnsLoadAsyncTask();
		
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
		mtTabPageIndicator = new TabPageIndicator(this);
		mtTabPageIndicator.setHorizontalScrollBarEnabled(false);
		this.mTopLayout = getTopLayout();
		if (mTopLayout != null) {
			mTopLayout.setTopLayoutItemsVisible(false, true, true, true, false, null, false, null, false, null);
			RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
			layoutParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
			mTopLayout.addView(mtTabPageIndicator, layoutParams);
		}
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
		public Fragment getItem(int arg0) {
			return null;
		}

		@Override
		public int getCount() {
			return 0;
		}
		
	}
}
