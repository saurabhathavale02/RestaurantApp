package com.example.saurabh.restaurantdairy.MenuDisplay.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saura on 7/11/2017.
 */

public class PagerAdapter extends FragmentPagerAdapter {

    private final String TAG =PagerAdapter.class.getName();

    private final List<Fragment> mFragmentList = new ArrayList();

    private final List<String> mFragmentTitleNames = new ArrayList();

    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title)
    {
        Log.i(TAG,"in addfragment");
        mFragmentList.add(fragment);
        mFragmentTitleNames.add(title);
    }

    @Override
    public Fragment getItem(int position)
    {
        Log.i(TAG,"in getposition="+position);
        return mFragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        Log.i(TAG," size="+mFragmentList.size());
        return mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        Log.i(TAG," getPageTitle="+mFragmentTitleNames.get(position));
        return mFragmentTitleNames.get(position);
    }
}
