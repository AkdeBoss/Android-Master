package com.android.tenera.fragments;

import android.support.v4.app.Fragment;


public class BaseFragment extends Fragment {
    public interface NetworkCallListener {
        void onSuccess(Object response);

        void onFailure(Throwable error);
    }
}
