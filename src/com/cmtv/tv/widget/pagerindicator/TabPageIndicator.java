/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cmtv.tv.widget.pagerindicator;

import com.cmtv.tv.R;
import com.cmtv.tv.activity.HomePageActivity;
import com.cmtv.tv.widget.BaseViewPager;
import com.cmtv.tv.widget.BaseViewPager.OnPageChangeListener;

import android.content.Context;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * This widget implements the dynamic action bar tab behavior that can change
 * across different configurations or circumstances.
 */
public class TabPageIndicator extends HorizontalScrollView implements PageIndicator {
    /** Title text used when no title is provided by the adapter. */
    private static final CharSequence EMPTY_TITLE = "";
    
    private Context mContext;
    
    private Shader mNormalShader;
    private Shader mFocusedShader;
    private int mTextFocusColor;

    /**
     * Interface for a callback when the selected tab has been reselected.
     */
    public interface OnTabReselectedListener {
        /**
         * Callback when the selected tab has been reselected.
         *
         * @param position Position of the current center item.
         */
        void onTabReselected(int position);
    }

    private Runnable mTabSelector;

    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            TabView tabView = (TabView)view;
            final int oldSelected = mBaseViewPager.getCurrentItem();
            final int newSelected = tabView.getIndex();
            mBaseViewPager.setCurrentItem(newSelected);
            if (oldSelected == newSelected && mTabReselectedListener != null) {
                mTabReselectedListener.onTabReselected(newSelected);
            }
        }
    };

    private final IcsLinearLayout mTabLayout;

    private BaseViewPager mBaseViewPager;
    private BaseViewPager.OnPageChangeListener mListener;

    private int mMaxTabWidth;
    private int mSelectedTabIndex;

    private OnTabReselectedListener mTabReselectedListener;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);

        //custom
        mContext = context;
        this.mTextFocusColor = context.getResources().getColor(R.color.tab_view_indicator_focus_color);
        this.mFocusedShader = new LinearGradient(0.0f, 0.0f, 0.0f, 50.0f, mTextFocusColor, mTextFocusColor, Shader.TileMode.CLAMP);
        this.mNormalShader = new LinearGradient(0.0f, 0.0f, 0.0f, 40.0f, Color.WHITE, Color.GRAY, Shader.TileMode.CLAMP);
        
        mTabLayout = new IcsLinearLayout(context, R.attr.vpiTabPageIndicatorStyle);
        addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener) {
        mTabReselectedListener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int)(MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentItem(mSelectedTabIndex);
        }
    }

    private void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                smoothScrollTo(scrollPos, 0);
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }

    private void addTab(int index, CharSequence text, int iconResId) {
        final TabView tabView = new TabView(getContext());
        tabView.mIndex = index;
        tabView.setFocusable(true);
        tabView.setOnClickListener(mTabClickListener);
        tabView.getTabText().setTextSize(0, this.mContext.getResources().getDimension(R.dimen.universal_large_text_size));
        tabView.getTabText().setText(text);
        
        //custom
        tabView.getPaint().setShader(mNormalShader);

        if (iconResId != 0) {
        	tabView.getTabText().setCompoundDrawablesWithIntrinsicBounds(iconResId, 0, 0, 0);
        }

        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, MATCH_PARENT, 1));
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(arg0);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
        if (mListener != null) {
            mListener.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override
    public void onPageSelected(int position) {
    	//custom
        System.out.println("mSelectedTabIndex : "+mSelectedTabIndex+", position : "+position);
        int width = this.mTabLayout.getChildAt(0).getWidth();
        int start = width * mSelectedTabIndex;
        int end = position * width;
        ((HomePageActivity)mContext).startCursorAnimation(start, end);
    	
        setCurrentItem(position);
        if (mListener != null) {
            mListener.onPageSelected(position);
        }
        
        //custom
        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
        	final TabView child = (TabView)mTabLayout.getChildAt(i);
        	if (i == position) {
        		child.getPaint().setShader(mFocusedShader);
			} else {
				child.getPaint().setShader(mNormalShader);
			}
		}
    }

    @Override
    public void setBaseViewPager(BaseViewPager view) {
        if (mBaseViewPager == view) {
            return;
        }
        if (mBaseViewPager != null) {
            mBaseViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("BaseViewPager does not have adapter instance.");
        }
        mBaseViewPager = view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mBaseViewPager.getAdapter();
        IconPagerAdapter iconAdapter = null;
        if (adapter instanceof IconPagerAdapter) {
            iconAdapter = (IconPagerAdapter)adapter;
        }
        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            if (title == null) {
                title = EMPTY_TITLE;
            }
            int iconResId = 0;
            if (iconAdapter != null) {
                iconResId = iconAdapter.getIconResId(i);
            }
            addTab(i, title, iconResId);
        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    @Override
    public void setBaseViewPager(BaseViewPager view, int initialPosition) {
        setBaseViewPager(view);
        setCurrentItem(initialPosition);
    }

    @Override
    public void setCurrentItem(int item) {
        if (mBaseViewPager == null) {
            throw new IllegalStateException("BaseViewPager has not been bound.");
        }
        mSelectedTabIndex = item;
        mBaseViewPager.setCurrentItem(item);

        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(item);
            }
        }
    }

    @Override
    public void setOnPageChangeListener(OnPageChangeListener listener) {
        mListener = listener;
    }

    private class TabView extends FrameLayout {
        private int mIndex;
        private TextView tabText;
        private ImageView nofityImg;

        public TabView(Context context) {
            super(context, null, R.attr.vpiTabPageIndicatorStyle);
            init();
        }

        private void init() {
        	this.tabText = new TextView(getContext());
        	FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        	layoutParams.leftMargin = 22;
        	layoutParams.rightMargin = 22;
        	layoutParams.gravity = Gravity.CENTER;
        	this.tabText.setLayoutParams(layoutParams);
        	addView(tabText);
        	
        	this.nofityImg = new ImageView(getContext());
        	FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
        	layoutParams2.gravity = Gravity.RIGHT;
        	this.nofityImg.setLayoutParams(layoutParams2);
        	this.nofityImg.setImageResource(R.drawable.notify_dot);
        	addView(nofityImg);
        }
        
        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

            // Re-measure if we went beyond our maximum size.
            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY),
                        heightMeasureSpec);
            }
        }

        public int getIndex() {
            return mIndex;
        }
        
        public Paint getPaint() {
        	if (tabText != null) {
				return tabText.getPaint();
			}
        	return new Paint();
        }
        
        public TextView getTabText() {
        	return tabText;
        }
        
        public void setNotify(boolean isShow) {
        	if (isShow) {
				nofityImg.setVisibility(View.VISIBLE);
			} else {
				nofityImg.setVisibility(View.INVISIBLE);
			}
        }
    }
    
    /**
     * custom
     */
    public void requestFirstTabSelected() {
      View localView = this.mTabLayout.getChildAt(0);
      if ((localView != null) && ((localView instanceof TabView)))
      {
        ((TabView)localView).getPaint().setShader(this.mFocusedShader);
        localView.requestFocus();
      }
    }
}
