package com.android.tenera.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.R;
import com.android.tenera.databinding.FragmentCatalogBinding;

/**
 * Created by prajwalrai on 16/07/16.
 */
public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_catalog, container, true);
        mBinding.catalogList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        return mBinding.getRoot();
    }
}
