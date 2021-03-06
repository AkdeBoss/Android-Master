package com.android.tenera.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.Utils.DividerItemDecoration;
import com.android.tenera.Utils.Utils;
import com.android.tenera.activity.MainActivity;
import com.android.tenera.adapter.CartAdapter;
import com.android.tenera.application.MainApplication;
import com.shopify.buy.dataprovider.BuyClient;
import com.shopify.buy.dataprovider.BuyClientError;
import com.shopify.buy.model.Cart;
import com.shopify.buy.model.Checkout;
import com.shopify.buy.model.LineItem;

import org.greenrobot.eventbus.EventBus;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by raghavendra on 11/07/16.
 */
public class CartFragment extends Fragment implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView totalCost;
    private ImageView checkoutArrow;
    private RelativeLayout checkoutLayout;
    private TextView cartAmount;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        checkoutArrow = (ImageView) view.findViewById(R.id.left_arrow);
        recyclerView = (RecyclerView) view.findViewById(R.id.catalog_list);
        checkoutLayout = (RelativeLayout) view.findViewById(R.id.bottom_layout);
        checkoutLayout.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getActivity(), null));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        cartAmount = (TextView) view.findViewById(R.id.cart_ammuont);
        totalCost = (TextView) view.findViewById(R.id.total_cost);
        MainActivity.getInstance().showLoader();
        createCheckout();


        return view;
    }

    private void createCheckout() {
        MainActivity.getInstance().getMainApplication().updateCheckout(new Callback<Checkout>() {
            @Override
            public void success(Checkout checkout, Response response) {
                MainActivity.getInstance().hideLoader();
                onCheckoutCreated(checkout);
            }

            @Override
            public void failure(RetrofitError error) {


            }}

            );
        }

    private void onCheckoutCreated(Checkout checkout) {
        recyclerView.setAdapter(new CartAdapter(MainActivity.getInstance(), checkout.getLineItems(), CartFragment.this));
        totalCost.setText(Utils.addRuppeSymbol(checkout.getPaymentDue()));
        checkoutArrow.setOnClickListener(this);
        if (MainApplication.getCart().getSize() < 1) {
            checkoutLayout.setVisibility(View.GONE);
        } else {
            checkoutLayout.setVisibility(View.VISIBLE);
            cartAmount.setText(Utils.addRuppeSymbol(checkout.getPaymentDue()));
        }
        totalCost.setText(Utils.addRuppeSymbol(checkout.getPaymentDue()));
    }


    public void addCartItem(LineItem item) {
        Cart cart = MainApplication.getCart();
        cart.addVariant(cart.getProductVariant(item));
        createCheckout();
    }

    public void removeCartItem(LineItem item) {
        Cart cart = MainApplication.getCart();
        cart.decrementVariant(cart.getProductVariant(item));
        createCheckout();
    }

    /*@Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }*/

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_arrow:
                ((MainActivity) getActivity()).replaceFragmentWithBackStack(new CheckoutFragment());
                break;
        }
    }
}