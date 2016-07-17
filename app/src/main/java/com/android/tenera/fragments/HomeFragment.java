package com.android.tenera.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
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
import com.android.tenera.databinding.CartItemBinding;
import com.android.tenera.databinding.FragmentHomeBinding;
import com.shopify.buy.model.Collection;
import com.shopify.buy.model.Product;

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
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.fragment_home, container, false);
        mBinding.setHandler(this);
        itemAdapter = new CustomPagerAdapter(MainActivity.getInstance().getSupportFragmentManager(), MainActivity.getInstance(), Utils.getMenuTitles());

        mBinding.pageContainer.setAdapter(itemAdapter);
        mBinding.tabContainer.setupWithViewPager(mBinding.pageContainer);
        return mBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        NoInternetFragment dialogFragment = new NoInternetFragment();
        dialogFragment.show(getActivity().getSupportFragmentManager(), null);
    }
}
