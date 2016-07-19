package com.android.tenera.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.R;
import com.android.tenera.Utils.Utils;
import com.android.tenera.activity.MainActivity;
import com.android.tenera.adapter.CatalogAdapter;
import com.shopify.buy.model.Product;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by raghavendra on 11/07/16.
 */
public class CatalogFragment extends Fragment {

    private int index;
    private RecyclerView mCatalogList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_catalog, container, false);

        mCatalogList=(RecyclerView)view.findViewById(R.id.catalog_list);
        mCatalogList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        fetchProductsByCollectionId(Utils.getCollectionIds().get(getIndex()));
        return view;
    }

    private void fetchProductsByCollectionId(String id) {
        MainActivity.getBuyInstance().getProducts(1, id, new Callback<List<Product>>() {

            @Override
            public void success(List<Product> products, Response response) {
                // Add code to save Products and update display here
                Utils.getMenuItems().add(products);
                mCatalogList.setAdapter(new CatalogAdapter(MainActivity.getInstance(), products));

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", MainActivity.getBuyInstance().getErrorBody(error));
                // Handle errors here
            }

        });

    }



    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


}
