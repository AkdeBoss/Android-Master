package com.android.tenera.fragments;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.R;
import com.android.tenera.Utils.Utils;
import com.android.tenera.activity.MainActivity;
import com.android.tenera.adapter.CatalogAdapter;
import com.android.tenera.databinding.FragmentCatalogBinding;

/**
 * Created by raghavendra on 11/07/16.
 */
public class CatalogFragment extends Fragment {

    private FragmentCatalogBinding mBinding;

    /**
     * Static factory method that takes an int parameter,
     * initializes the fragment's arguments, and returns the
     * new fragment to the client.
     */
    public static CatalogFragment newInstance(int index) {
        CatalogFragment f = new CatalogFragment();
        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_catalog, container, true);
        mBinding.catalogList.setLayoutManager(new GridLayoutManager(getContext(), 2));

        Bundle args = getArguments();
        int index = args.getInt("index", 0);
        mBinding.catalogList.setAdapter(new CatalogAdapter(MainActivity.getInstance(), Utils.getMenuItems().get(index)));


        return mBinding.getRoot();
    }
}
