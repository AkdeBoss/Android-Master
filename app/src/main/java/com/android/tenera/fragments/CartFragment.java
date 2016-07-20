package com.android.tenera.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.Utils.DividerItemDecoration;
import com.android.tenera.Utils.Utils;
import com.android.tenera.activity.MainActivity;
import com.android.tenera.adapter.CartAdapter;
import com.android.tenera.application.MainApplication;
import com.shopify.buy.model.Cart;
import com.shopify.buy.model.CartLineItem;
import com.shopify.buy.model.Checkout;
import com.shopify.buy.model.LineItem;
import com.shopify.buy.model.Product;
import com.shopify.buy.model.ProductVariant;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by raghavendra on 11/07/16.
 */
public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private TextView totalCost;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.catalog_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), null));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        totalCost = (TextView) view.findViewById(R.id.total_cost);

        List<CartLineItem> products = MainApplication.getCart().getLineItems();

        Checkout checkout = new Checkout(MainApplication.getCart());

        totalCost.setText(checkout.getPaymentDue());
        recyclerView.setAdapter(new CartAdapter(MainActivity.getInstance(), products, CartFragment.this));


        return view;
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
}