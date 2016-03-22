package com.mcswainsoftware.pcpp;

import android.app.*;
import android.os.*;
import android.view.*;
import android.support.v4.view.*;
import android.content.*;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_details);
		
		ActionBar actionBar = getActionBar();
		//actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		LayoutInflater inflater = LayoutInflater.from(this);
		
		View customActionBar = inflater.inflate(R.layout.actionbar, null);
		
		actionBar.setCustomView(customActionBar);
		actionBar.setDisplayShowCustomEnabled(true);
		
		ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(new CustomPagerAdapter(this));
    }
	
	public enum CustomPagerEnum {

		PRICES(R.string.price_tab, R.layout.view_price),
		SPECS(R.string.spec_tab, R.layout.view_spec),
		BUILDS(R.string.build_tab, R.layout.view_build),
		REVIEWS(R.string.review_tab, R.layout.view_review);
		
		private int mTitleResId;
		private int mLayoutResId;

		CustomPagerEnum(int titleResId, int layoutResId) {
			mTitleResId = titleResId;
			mLayoutResId = layoutResId;
		}

		public int getTitleResId() {
			return mTitleResId;
		}

		public int getLayoutResId() {
			return mLayoutResId;
		}

	}
	
	public class CustomPagerAdapter extends PagerAdapter {

		private Context mContext;

		public CustomPagerAdapter(Context context) {
			mContext = context;
		}

		@Override
		public Object instantiateItem(ViewGroup collection, int position) {
			CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
			LayoutInflater inflater = LayoutInflater.from(mContext);
			ViewGroup layout = (ViewGroup) inflater.inflate(customPagerEnum.getLayoutResId(), collection, false);
			collection.addView(layout);
			return layout;
		}

		@Override
		public void destroyItem(ViewGroup collection, int position, Object view) {
			collection.removeView((View) view);
		}

		@Override
		public int getCount() {
			return CustomPagerEnum.values().length;
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			CustomPagerEnum customPagerEnum = CustomPagerEnum.values()[position];
			return MainActivity.this.getString(customPagerEnum.getTitleResId());
		}

	}
}
