package com.cmtv.tv.activity;

import com.cmtv.tv.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class WelcomeActivity extends BaseActivity {

	private View mWelcomeLayout;
	private View mWelcomeErrorinfo;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		initView();
	}
	
	private void initView() {
		mWelcomeLayout = findViewById(R.id.welcome_layout);
		mWelcomeErrorinfo = findViewById(R.id.welcome_load_error_info);
		startUpdate();
	}
	
	private void startUpdate() { //先不做更新模块，直接跳转
		mHandler.postDelayed(new Runnable() {
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this, HomePageActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}
	
	private Handler mHandler = new Handler();
}
