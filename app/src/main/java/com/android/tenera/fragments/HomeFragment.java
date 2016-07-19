package com.android.tenera.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.R;
import com.android.tenera.Utils.Utils;
import com.android.tenera.activity.MainActivity;
import com.android.tenera.adapter.CustomPagerAdapter;
import com.shopify.buy.model.Collection;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by raghavendra on 11/07/16.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private PagerAdapter itemAdapter;
    private ArrayList<String> tabsList = new ArrayList<>();
    private ArrayList<String> collectionIdList = new ArrayList<>();
    private ViewPager mPagerContainer;
    private TabLayout mTabContainer;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mPagerContainer = (ViewPager) view.findViewById(R.id.page_container);
        mTabContainer = (TabLayout) view.findViewById(R.id.tab_container);
        fetchCollections();


        return view;
    }

    private void fetchCollections() {
        MainActivity.getBuyInstance().getCollections(new Callback<List<Collection>>() {
            @Override
            public void success(List<Collection> collections, Response response) {
                Log.e("", collections.toString());
                for (int i = 0; i < collections.size(); i++) {
                    Collection collection = collections.get(i);
                    tabsList.add(collection.getTitle());
                    collectionIdList.add(collection.getCollectionId());
                }
                Utils.setCollecionTitles(tabsList);
                Utils.setCollectionIds(collectionIdList);

                itemAdapter = new CustomPagerAdapter(getChildFragmentManager(), MainActivity.getInstance(), tabsList);

                mPagerContainer.setAdapter(itemAdapter);
                mTabContainer.setupWithViewPager(mPagerContainer);
                mPagerContainer.setOffscreenPageLimit(Utils.getCollecionTitles().size());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", MainActivity.getBuyInstance().getErrorBody(error));

            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {

    }
}
