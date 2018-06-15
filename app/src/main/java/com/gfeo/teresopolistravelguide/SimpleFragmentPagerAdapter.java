package com.gfeo.teresopolistravelguide;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A simple custom {@link FragmentPagerAdapter}.
 *
 * @author gabrielfeo
 */

class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

	/** A {@link Context} for getting the tab title Strings. */
	private final Context mContext;

	SimpleFragmentPagerAdapter(Context context, FragmentManager fragmentManager) {
		super(fragmentManager);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		Fragment fragment = null;
		switch (position) {
			case 0:
				fragment = new TourismFragment();
				break;
			case 1:
				fragment = new FoodFragment();
				break;
			case 2:
				fragment = new BeerFragment();
				break;
		}
		return fragment;
	}

	/**
	 * Gets the current tab title from the respective String resource, using the
	 * {@code Context} ({@link SimpleFragmentPagerAdapter#mContext}) provided by the constructor.
	 */
	@Override
	public CharSequence getPageTitle(int position) {
		String pageTitle = null;
		switch (position) {
			case 0:
				pageTitle = mContext.getString(R.string.main_tab_tourism);
				break;
			case 1:
				pageTitle = mContext.getString(R.string.main_tab_food);
				break;
			case 2:
				pageTitle = mContext.getString(R.string.main_tab_beer);
				break;
		}
		return pageTitle;
	}

	@Override
	public int getCount() {
		return 3;
	}

}
