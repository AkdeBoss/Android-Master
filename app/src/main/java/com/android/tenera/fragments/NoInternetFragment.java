package com.android.tenera.fragments;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.tenera.R;
import com.android.tenera.databinding.FragmentNoInternetBinding;

/**
 * Created by raghavendra on 11/07/16.
 */
public class NoInternetFragment extends DialogFragment implements View.OnClickListener {


    private com.android.tenera.databinding.FragmentNoInternetBinding mBinding;

    public NoInternetFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
//        setStyle();
        setStyle(STYLE_NO_FRAME, android.R.style.Theme_Holo_Light);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_no_internet, container, false);
        mBinding.setHandler(this);
        return mBinding.getRoot();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), "No internet clicked", Toast.LENGTH_LONG).show();
        startActivity(new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS));
    }
}
