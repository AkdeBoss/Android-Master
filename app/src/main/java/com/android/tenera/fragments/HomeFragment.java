package com.android.tenera.fragments;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.R;
import com.android.tenera.activity.MainActivity;
import com.android.tenera.databinding.CartItemBinding;
import com.android.tenera.databinding.FragmentHomeBinding;
import com.shopify.buy.model.Collection;
import com.shopify.buy.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class HomeFragment extends BaseFragment implements View.OnClickListener {



    private ArrayList<List<Product>> items = new ArrayList<>();
    private FragmentHomeBinding mBinding;


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
                    fetchProductsByCollectionId(collection.getCollectionId());

                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", MainActivity.getBuyInstance().getErrorBody(error));

            }
        });
    }

    private void fetchProductsByCollectionId(String id) {
        MainActivity.getBuyInstance().getProducts(1, id, new Callback<List<Product>>() {

            @Override
            public void success(List<Product> products, Response response) {
                // Add code to save Products and update display here
                items.add(products);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", MainActivity.getBuyInstance().getErrorBody(error));
                // Handle errors here
            }

        });

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
