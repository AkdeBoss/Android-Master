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
import com.android.tenera.application.MainApplication;
import com.android.tenera.model.ProductDTO;
import com.shopify.buy.model.Cart;
import com.shopify.buy.model.CartLineItem;
import com.shopify.buy.model.Product;
import com.shopify.buy.model.ProductVariant;

import java.util.ArrayList;
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
    private ArrayList<ProductDTO> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);

        mCatalogList = (RecyclerView) view.findViewById(R.id.catalog_list);
        mCatalogList.setLayoutManager(new GridLayoutManager(getContext(), 2));
        MainActivity.getInstance().showLoader();
        fetchProductsByCollectionId(Utils.getCollectionIds().get(getIndex()));
        return view;
    }

    private void fetchProductsByCollectionId(String id) {
        MainApplication.getBuyInstance().getProducts(1, id, new Callback<List<Product>>() {

            @Override
            public void success(List<Product> products, Response response) {
                // Add code to save Products and update display here
                list = new ArrayList<>();

                for (Product model : products) {
                    ProductDTO item = new ProductDTO(model);
                    list.add(item);
                }
                Utils.getMenuItems().add(list);

                setList();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", MainApplication.getBuyInstance().getErrorBody(error));
                MainActivity.getInstance().hideLoader();

                // Handle errors here
            }

        });

    }

    private void setList() {
        List<CartLineItem> products = MainApplication.getCart().getLineItems();
        if (products != null && products.size() != 0 && list != null && list.size() != 0) {
            for (int i = 0; i < products.size(); i++) {
                for (ProductDTO productDTO : list) {
                    if (products.get(i).getVariant().getProductId() == productDTO.getVariant().getProductId()) {
                        productDTO.setQuantity((int) products.get(i).getQuantity());
                    }
                }
            }
        }
        mCatalogList.setAdapter(new CatalogAdapter(MainActivity.getInstance(), list, CatalogFragment.this));
        MainActivity.getInstance().hideLoader();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    public void addCartItem(ProductVariant variant, int quantity) {
        Cart cart = MainApplication.getCart();
        if (quantity == 1) {
            cart.addVariant(variant);
        } else {
            cart.setVariantQuantity(variant, quantity);
        }
    }

    public void removeCartItem(ProductVariant variant, int quantity) {
        Cart cart = MainApplication.getCart();
        if (quantity < 1) {
            cart.decrementVariant(variant);
        } else {
            cart.setVariantQuantity(variant, quantity);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }


}