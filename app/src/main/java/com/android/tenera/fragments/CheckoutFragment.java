package com.android.tenera.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.R;

/**
 * Created by raghavendra on 11/07/16.
 */
public class CheckoutFragment extends BaseFragment {


    public CheckoutFragment() {
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
        return inflater.inflate(R.layout.fragment_checkout, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
