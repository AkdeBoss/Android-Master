package com.android.tenera.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.tenera.R;


public class PaymentInfoFragment extends BaseFragment implements View.OnClickListener {


    private EditText houseNumber;
    private EditText address;
    private EditText zipCode;
    private ImageView leftArrow;

    public PaymentInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payment_info, container, false);
        houseNumber = (EditText) view.findViewById(R.id.house_number);
        address = (EditText) view.findViewById(R.id.address);
        zipCode = (EditText) view.findViewById(R.id.zip_code);
        leftArrow = (ImageView) view.findViewById(R.id.left_arrow);
        leftArrow.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow:
                break;
        }
    }
}
