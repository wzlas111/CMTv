package com.cmtv.tv.fragment;

import com.cmtv.tv.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FoxPlayFragment extends BaseFragment {
	
	private static FoxPlayFragment mFoxPlayFragment;

	public static FoxPlayFragment getInstance() {
		if (mFoxPlayFragment == null) {
			mFoxPlayFragment = new FoxPlayFragment();
		}
		return mFoxPlayFragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_foxplayer_layout, container, false);
		return view;
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
}
