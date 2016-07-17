package com.android.tenera.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.R;
import com.android.tenera.Utils.Utils;
import com.android.tenera.activity.MainActivity;
import com.android.tenera.adapter.CustomPagerAdapter;
import com.android.tenera.databinding.FragmentHomeBinding;
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

    private FragmentHomeBinding mBinding;
    private PagerAdapter itemAdapter;
    private ArrayList<String> tabsList = new ArrayList<>();
    private ArrayList<String> collectionIdList = new ArrayList<>();

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
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mBinding.setHandler(this);

        fetchCollections();


        return mBinding.getRoot();
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

                mBinding.pageContainer.setAdapter(itemAdapter);
                mBinding.tabContainer.setupWithViewPager(mBinding.pageContainer);
                mBinding.pageContainer.setOffscreenPageLimit(Utils.getCollecionTitles().size());
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
