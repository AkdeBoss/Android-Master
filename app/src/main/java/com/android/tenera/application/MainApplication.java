package com.android.tenera.application;

import android.app.Application;

import com.shopify.buy.dataprovider.BuyClient;
import com.shopify.buy.dataprovider.BuyClientFactory;
import com.shopify.buy.model.Cart;
import com.shopify.buy.model.Checkout;
import com.shopify.buy.model.Product;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by raghavendra on 08/07/16.
 */
public class MainApplication extends Application {
    private static BuyClient buyInstance;
    private static Cart cart;
    public static Checkout checkout;
    // Constants
    public static final String BUY_CLIENT_SHOP = "meatro.myshopify.com";
    public static final String BUY_CLIENT_API_KEY = "c3875e253fe6f666d09f9f037802bd0a";
    public static final String BUY_CLIENT_CHANNEL = "74433223";
    public static final String BUY_CLIENT_APP_NAME = "com.android.tenera";

    @Override
    public void onCreate() {
        super.onCreate();
        buyInstance = newInstance();
    }

    public static BuyClient getBuyInstance() {
        if (buyInstance == null) {
            buyInstance = newInstance();
        }
        return buyInstance;
    }


    // Easily access an instance of your Buy Client
    public static BuyClient newInstance() {
        if (buyInstance == null) {
            buyInstance = BuyClientFactory.getBuyClient(BUY_CLIENT_SHOP,
                    BUY_CLIENT_API_KEY,
                    BUY_CLIENT_CHANNEL,
                    BUY_CLIENT_APP_NAME);
        }
        return buyInstance;
    }

    public static Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    public void createCheckout(final Callback<Checkout> callback) {
        checkout = new Checkout(getCart());
        getBuyInstance().createCheckout(checkout, wrapCheckoutCallback(callback));
    }

    public static Checkout getCheckout() {
        return checkout;
    }

    /**
     * Wraps the callbacks that are provided by the activities so that the checkout ivar is always up to date.
     *
     * @param callback
     * @return
     */
    private Callback<Checkout> wrapCheckoutCallback(final Callback<Checkout> callback) {
        return new Callback<Checkout>() {
            @Override
            public void success(Checkout checkout, Response response) {
                MainApplication.this.checkout = checkout;
                callback.success(checkout, response);
            }

            @Override
            public void failure(RetrofitError error) {
                callback.failure(error);
            }
        };
    }


}
