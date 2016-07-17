package com.android.tenera.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.android.tenera.fragments.CatalogFragment;

import java.util.ArrayList;

/**
 * Created by prajwalrai on 14/07/16.
 */
public class CustomPagerAdapter extends FragmentStatePagerAdapter {

    private final Context mContext;
    private ArrayList<String> mTabTitles;

    public CustomPagerAdapter(FragmentManager fragmentManager, Context context, ArrayList<String> titles) {
        super(fragmentManager);
        mContext = context;
        mTabTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        CatalogFragment fragment = new CatalogFragment();
        fragment.setIndex("" + position);
        return fragment;
    }

    @Override
    public int getCount() {
        return mTabTitles.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles.get(position);
    }
}
