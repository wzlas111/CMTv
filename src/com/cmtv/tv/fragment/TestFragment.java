package com.cmtv.tv.fragment;

import com.cmtv.tv.R;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TestFragment extends BaseFragment {

	private static TestFragment mTestFragment;

	public static TestFragment getInstance() {
		if (mTestFragment == null) {
			mTestFragment = new TestFragment();
		}
		return mTestFragment;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_test_layout, container, false);
		return view;
	}
}
