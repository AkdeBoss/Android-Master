package com.android.tenera.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.R;
import com.android.tenera.databinding.CartItemBinding;
import com.android.tenera.databinding.FragmentHomeBinding;


public class HomeFragment extends BaseFragment implements View.OnClickListener {


    private CartItemBinding mBinding;

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
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.cart_item, container, false);
        mBinding.setHandler(this);
        return mBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        NoInternetFragment dialogFragment=new NoInternetFragment();
        dialogFragment.show(getActivity().getSupportFragmentManager(),null);
    }
}
