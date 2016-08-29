package com.android.tenera.application;

import android.app.Application;
import android.widget.Toast;

import com.android.tenera.R;
import com.google.android.gms.wallet.MaskedWallet;
import com.shopify.buy.BuildConfig;
import com.shopify.buy.dataprovider.BuyClient;
import com.shopify.buy.dataprovider.BuyClientBuilder;
import com.shopify.buy.dataprovider.BuyClientError;
import com.shopify.buy.dataprovider.Callback;
import com.shopify.buy.model.Address;
import com.shopify.buy.model.Cart;
import com.shopify.buy.model.Checkout;
import com.shopify.buy.model.Collection;
import com.shopify.buy.model.CreditCard;
import com.shopify.buy.model.PaymentToken;
import com.shopify.buy.model.Product;
import com.shopify.buy.model.ShippingRate;
import com.shopify.buy.model.Shop;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by raghavendra on 08/07/16.
 */
public class MainApplication extends Application {
    private static Cart cart;
    // Constants
    public static final String BUY_CLIENT_SHOP = "meatro.myshopify.com";
    public static final String BUY_CLIENT_API_KEY = "c3875e253fe6f666d09f9f037802bd0a";
    public static final String BUY_CLIENT_APP_ID = "8";

    public static Cart getCart() {
        if (cart == null) {
            cart = new Cart();
        }
        return cart;
    }

    private PaymentToken paymentToken;

    private static MainApplication instance;

    public static BuyClient getBuyClient() {
        return instance.buyClient;
    }

    private BuyClient buyClient;
    private Checkout checkout;
    private Shop shop;


    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        initializeBuyClient();
    }

    private void initializeBuyClient() {
        String BUY_CLIENT_APP_NAME = getPackageName();

        final HttpLoggingInterceptor logging = new HttpLoggingInterceptor().setLevel(BuildConfig.OKHTTP_LOG_LEVEL);

        /**
         * Create the BuyClient
         */

        buyClient = new BuyClientBuilder()
                .shopDomain(BUY_CLIENT_SHOP)
                .apiKey(BUY_CLIENT_API_KEY)
                .appId(BUY_CLIENT_APP_ID)
                .applicationName(BUY_CLIENT_APP_NAME)
                .interceptors(logging)
                .networkRequestRetryPolicy(3, TimeUnit.MILLISECONDS.toMillis(200), 1.5f)
                .build();

        buyClient.getShop(new Callback<Shop>() {
            @Override
            public void success(Shop shop) {
                MainApplication.this.shop = shop;
            }

            @Override
            public void failure(BuyClientError error) {
                Toast.makeText(MainApplication.this, "error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getCollections(final Callback<List<Collection>> callback) {
        buyClient.getCollections(1, callback);
    }


    public void getAllProducts(final int page, final List<Product> allProducts, final Callback<List<Product>> callback) {

        buyClient.getProducts(page, new Callback<List<Product>>() {
            @Override
            public void success(List<Product> products) {
                if (products.size() > 0) {
                    allProducts.addAll(products);
                    getAllProducts(page + 1, allProducts, callback);
                } else {
                    callback.success(allProducts);
                }
            }

            @Override
            public void failure(BuyClientError error) {
                callback.failure(error);
            }
        });
    }

    public void getProducts(Long collectionId, Callback<List<Product>> callback) {
        // For this sample app, we'll just fetch the first page of products in the collection
        buyClient.getProducts(1, collectionId, null, null, callback);
    }

    /**
     * Create a new checkout with the selected product. For convenience in the sample app we will hardcode the user's shipping address.
     * The shipping rates fetched in ShippingRateListActivity will be for this address.
     * <p/>
     * For the Android Pay Checkout, we will replace this with the address and email returned in the {@link MaskedWallet}
     *
     * @param product
     * @param callback
     */
    public void createCheckout(final Product product, final Address address, final Callback<Checkout> callback) {
        Cart cart = new Cart();
        cart.addVariant(product.getVariants().get(0));

        checkout = new Checkout(cart);
//        checkout.setWebReturnToLabel(getString(R.string.web_return_to_label));
//        checkout.setWebReturnToUrl(getString(R.string.web_return_to_url));

        // if we have logged in customer use customer email instead of hardcoded one
//        if (customer != null) {
//            checkout.setEmail(customer.getEmail());
//        } else {
//            checkout.setEmail("something@somehost.com");
//        }

//        // the same for shipping address if we have logged in customer use customer default shipping address instead of hardcoded one
//        if (customer != null && customer.getDefaultAddress() != null) {
//            checkout.setShippingAddress(customer.getDefaultAddress());
//            checkout.setBillingAddress(customer.getDefaultAddress());
//        } else {
//            final Address address = new Address();
//            address.setFirstName("Dinosaur");
//            address.setLastName("Banana");
//            address.setAddress1("421 8th Ave");
//            address.setCity("New York");
//            address.setProvinceCode("NY");
//            address.setZip("10001");
//            address.setCountryCode("US");
        checkout.setShippingAddress(address);
        checkout.setBillingAddress(address);
//        }

//        checkout.setWebReturnToUrl(getString(R.string.web_return_to_url));
//        checkout.setWebReturnToLabel(getString(R.string.web_return_to_label));

        buyClient.createCheckout(checkout, wrapCheckoutCallback(callback));
    }

    /**
     * Update a checkout.
     */
    public void updateCheckout(final Checkout checkout, final Callback<Checkout> callback) {
        buyClient.updateCheckout(checkout, wrapCheckoutCallback(callback));
    }

    public Checkout getCheckout() {
        return checkout;
    }

    public Shop getShop() {
        return shop;
    }

    public void getShippingRates(final Callback<List<ShippingRate>> callback) {
        buyClient.getShippingRates(checkout.getToken(), callback);
    }

    public void setShippingRate(ShippingRate shippingRate, final Callback<Checkout> callback) {
        checkout.setShippingRate(shippingRate);
        buyClient.updateCheckout(checkout, wrapCheckoutCallback(callback));
    }

    public void setDiscountCode(final String code, final Callback<Checkout> callback) {
        checkout.setDiscountCode(code);
        buyClient.updateCheckout(checkout, wrapCheckoutCallback(callback));
    }


    public void storeCreditCard(final CreditCard card, final Callback<PaymentToken> callback) {
        buyClient.storeCreditCard(card, checkout, new Callback<PaymentToken>() {
            @Override
            public void success(PaymentToken body) {
                MainApplication.this.paymentToken = body;
                callback.success(body);
            }

            @Override
            public void failure(BuyClientError error) {
                callback.failure(error);
            }
        });
    }

    public void completeCheckout(final Callback<Checkout> callback) {
        buyClient.completeCheckout(paymentToken, checkout.getToken(), wrapCheckoutCallback(callback));
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
            public void success(Checkout checkout) {
                MainApplication.this.checkout = checkout;
                callback.success(checkout);
            }

            @Override
            public void failure(BuyClientError error) {
                callback.failure(error);
            }
        };
    }
}
