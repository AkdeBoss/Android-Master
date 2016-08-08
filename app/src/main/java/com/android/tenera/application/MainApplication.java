package com.android.tenera.application;

import android.app.Application;

import com.shopify.buy.dataprovider.BuyClient;
import com.shopify.buy.dataprovider.BuyClientFactory;
import com.shopify.buy.model.Cart;

/**
 * Created by raghavendra on 08/07/16.
 */
public class MainApplication extends Application {
    private static MainApplication instance;
    private static BuyClient buyInstance;
    private static Cart cart;
    // Constants
    public static final String BUY_CLIENT_SHOP = "meatro.myshopify.com";
    public static final String BUY_CLIENT_API_KEY = "c3875e253fe6f666d09f9f037802bd0a";
    public static final String BUY_CLIENT_CHANNEL = "74433223";
    public static final String BUY_CLIENT_APP_NAME = "com.android.tenera";
    public static MainApplication getInstance() {
        return instance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
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
}
