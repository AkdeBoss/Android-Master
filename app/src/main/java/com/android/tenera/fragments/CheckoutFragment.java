package com.android.tenera.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.tenera.R;

/**
 * Created by raghavendra on 11/07/16.
 */
public class CheckoutFragment extends BaseFragment implements View.OnClickListener {


    private EditText mobile;
    private EditText name;
    private EditText email;
    private ImageView leftArrow;

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
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);
        name = (EditText) view.findViewById(R.id.name);
        email = (EditText) view.findViewById(R.id.email_address);
        mobile = (EditText) view.findViewById(R.id.mobile_number);
        leftArrow=(ImageView)view.findViewById(R.id.left_arrow);
        leftArrow.setOnClickListener(this);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.left_arrow:
                break;
        }
    }
}
