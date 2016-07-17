package com.android.tenera.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.Utils.Utils;
import com.android.tenera.databinding.ActivityMainBinding;
import com.android.tenera.fragments.HomeFragment;
import com.shopify.buy.dataprovider.BuyClient;
import com.shopify.buy.dataprovider.BuyClientFactory;
import com.shopify.buy.model.Collection;
import com.shopify.buy.model.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Constants
    public static final String BUY_CLIENT_SHOP = "meatro.myshopify.com";
    public static final String BUY_CLIENT_API_KEY = "c3875e253fe6f666d09f9f037802bd0a";
    public static final String BUY_CLIENT_CHANNEL = "74433223";
    public static final String BUY_CLIENT_APP_NAME = "com.android.tenera";
    private static BuyClient buyInstance;


    public static MainActivity getInstance() {
        return instance;
    }

    public static void setInstance(MainActivity instance) {
        MainActivity.instance = instance;
    }

    private static MainActivity instance;


    public static BuyClient getBuyInstance() {
        return buyInstance;
    }

    public static void setBuyInstance() {
        MainActivity.buyInstance = newInstance();
    }

    private ActivityMainBinding mBinding;
    private TextView mPreviousSelectedDrawerText;
    private ImageView mPreviousSelectedDrawerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setHandler(this);
        setSupportActionBar(mBinding.customToolbar);
        setBuyInstance();
        setInstance(this);
        if (savedInstanceState == null) {
            loadHomeFragment();
        }
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

    private void loadHomeFragment() {
        getSupportFragmentManager().beginTransaction().add(mBinding.container.getId(), new HomeFragment()).commit();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(mBinding.container.getId(), fragment).commit();
    }

    private void addFragmentWithBackStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(mBinding.container.getId(), fragment).addToBackStack(fragment.getTag()).commit();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(mBinding.container.getId(), fragment).commit();
    }

    private void replaceFragmentWithBackStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(mBinding.container.getId(), fragment).addToBackStack(fragment.getTag()).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_home:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setColorFilter(null);
                }
                mBinding.imageHome.setColorFilter(getResources().getColor(R.color.color_ffbf00));
                mBinding.textHome.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mBinding.imageHome;
                mPreviousSelectedDrawerText = mBinding.textHome;
                break;
            case R.id.nav_explore_categories:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setColorFilter(null);
                }
                mBinding.imageExploreCategories.setColorFilter(getResources().getColor(R.color.color_ffbf00));
                mBinding.textExploreCategories.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mBinding.imageExploreCategories;
                mPreviousSelectedDrawerText = mBinding.textExploreCategories;
                break;
            case R.id.nav_cart:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setColorFilter(null);
                }
                mBinding.imageCart.setColorFilter(getResources().getColor(R.color.color_ffbf00));
                mBinding.textCart.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mBinding.imageCart;
                mPreviousSelectedDrawerText = mBinding.textCart;
                break;
            case R.id.nav_promotional_offer:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setColorFilter(null);
                }
                mBinding.imagePromotionalOffers.setColorFilter(getResources().getColor(R.color.color_ffbf00));
                mBinding.textPromotionalOffer.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mBinding.imagePromotionalOffers;
                mPreviousSelectedDrawerText = mBinding.textPromotionalOffer;
                break;
            case R.id.nav_invite_friend:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setColorFilter(null);
                }
                mBinding.imageIniviteFriend.setColorFilter(getResources().getColor(R.color.color_ffbf00));
                mBinding.textInviteFriend.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mBinding.imageIniviteFriend;
                mPreviousSelectedDrawerText = mBinding.textInviteFriend;
                break;
            case R.id.nav_about:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setColorFilter(null);
                }
                mBinding.imageAbout.setColorFilter(getResources().getColor(R.color.color_ffbf00));
                mBinding.textAbout.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mBinding.imageAbout;
                mPreviousSelectedDrawerText = mBinding.textAbout;
                break;
            case R.id.nav_support:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setColorFilter(null);
                }
                mBinding.imageSupport.setColorFilter(getResources().getColor(R.color.color_ffbf00));
                mBinding.textSupport.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mBinding.imageSupport;
                mPreviousSelectedDrawerText = mBinding.textSupport;
                break;
            default:
                break;
        }
    }





    public boolean isPreviousPresent() {
        if (mPreviousSelectedDrawerImage == null && mPreviousSelectedDrawerText == null) {
            return false;
        } else {
            return true;
        }
    }
}
