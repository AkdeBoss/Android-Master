package com.android.tenera.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.fragments.HomeFragment;
import com.shopify.buy.dataprovider.BuyClient;
import com.shopify.buy.dataprovider.BuyClientFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Constants
    public static final String BUY_CLIENT_SHOP = "meatro.myshopify.com";
    public static final String BUY_CLIENT_API_KEY = "c3875e253fe6f666d09f9f037802bd0a";
    public static final String BUY_CLIENT_CHANNEL = "74433223";
    public static final String BUY_CLIENT_APP_NAME = "com.android.tenera";
    private static BuyClient buyInstance;
    private Toolbar mToolBar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ImageView mImageHome;
    private TextView mTextHome;
    private ImageView mImageExploreCategories;
    private TextView mTextExploreCategories;
    private ImageView mImageCart;
    private TextView mTextCart;
    private ImageView mImagePromotionalOffer;
    private TextView mTextPromotionalOffer;
    private ImageView mImageInviteFriend;
    private TextView mTextInviteFriend;
    private ImageView mImageAbout;
    private TextView mTextAbout;
    private ImageView mImageSupport;
    private TextView mTextSupport;
    private ImageView mNavHome;
    private ImageView mNavExploreCategories;
    private ImageView mNavCart;
    private ImageView mNavPromotionalOffer;
    private ImageView mNavInviteFriend;
    private ImageView mNavAbout;
    private ImageView mNavSupport;


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

    private TextView mPreviousSelectedDrawerText;
    private ImageView mPreviousSelectedDrawerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolBar = (Toolbar) findViewById(R.id.custom_toolbar);
        setSupportActionBar(mToolBar);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);


        mImageHome = (ImageView) navigationView.findViewById(R.id.image_home);
        mTextHome = (TextView) navigationView.findViewById(R.id.text_home);
        mImageExploreCategories = (ImageView) navigationView.findViewById(R.id.image_explore_categories);
        mTextExploreCategories = (TextView) navigationView.findViewById(R.id.text_explore_categories);
        mImageCart = (ImageView) navigationView.findViewById(R.id.image_cart);
        mTextCart = (TextView) navigationView.findViewById(R.id.text_cart);
        mImagePromotionalOffer = (ImageView) navigationView.findViewById(R.id.image_promotional_offers);
        mTextPromotionalOffer = (TextView) navigationView.findViewById(R.id.text_promotional_offer);
        mImageInviteFriend = (ImageView) navigationView.findViewById(R.id.image_inivite_friend);
        mTextInviteFriend = (TextView) navigationView.findViewById(R.id.text_invite_friend);
        mImageAbout = (ImageView) navigationView.findViewById(R.id.image_about);
        mTextAbout = (TextView) navigationView.findViewById(R.id.text_about);
        mImageSupport = (ImageView) navigationView.findViewById(R.id.image_support);
        mTextSupport = (TextView) navigationView.findViewById(R.id.text_support);
        mNavHome = (ImageView) navigationView.findViewById(R.id.nav_home);
        mNavExploreCategories = (ImageView) navigationView.findViewById(R.id.nav_explore_categories);
        mNavCart = (ImageView) navigationView.findViewById(R.id.nav_cart);
        mNavPromotionalOffer = (ImageView) navigationView.findViewById(R.id.nav_promotional_offer);
        mNavInviteFriend = (ImageView) navigationView.findViewById(R.id.nav_invite_friend);
        mNavAbout = (ImageView) navigationView.findViewById(R.id.nav_about);
        mNavSupport = (ImageView) navigationView.findViewById(R.id.nav_support);

        mNavHome.setOnClickListener(this);
        mNavExploreCategories.setOnClickListener(this);
        mNavCart.setOnClickListener(this);
        mNavPromotionalOffer.setOnClickListener(this);
        mNavInviteFriend.setOnClickListener(this);
        mNavAbout.setOnClickListener(this);
        mNavSupport.setOnClickListener(this);

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
        getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment()).commit();
    }

    private void addFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();
    }

    private void addFragmentWithBackStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).addToBackStack(fragment.getTag()).commit();
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    private void replaceFragmentWithBackStack(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(fragment.getTag()).commit();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_home:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setSelected(false);
                }
                mImageHome.setSelected(true);
                mTextHome.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mImageHome;
                mPreviousSelectedDrawerText = mTextHome;
                break;
            case R.id.nav_explore_categories:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setSelected(false);
                }
                mImageExploreCategories.setSelected(true);
                mTextExploreCategories.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mImageExploreCategories;
                mPreviousSelectedDrawerText = mTextExploreCategories;
                break;
            case R.id.nav_cart:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setSelected(false);
                }
                mImageCart.setSelected(true);
                mTextCart.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mImageCart;
                mPreviousSelectedDrawerText = mTextCart;
                break;
            case R.id.nav_promotional_offer:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setSelected(false);
                }
                mImagePromotionalOffer.setSelected(true);
                mTextPromotionalOffer.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mImagePromotionalOffer;
                mPreviousSelectedDrawerText = mTextPromotionalOffer;
                break;
            case R.id.nav_invite_friend:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setSelected(false);
                }
                mImageInviteFriend.setSelected(true);
                mTextInviteFriend.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mImageInviteFriend;
                mPreviousSelectedDrawerText = mTextInviteFriend;
                break;
            case R.id.nav_about:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setSelected(false);
                }
                mImageAbout.setSelected(true);
                mTextAbout.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mImageAbout;
                mPreviousSelectedDrawerText = mTextAbout;
                break;
            case R.id.nav_support:
                loadHomeFragment();
                if (isPreviousPresent()) {
                    mPreviousSelectedDrawerText.setTextColor(getResources().getColor(R.color.color_8a2d2d2d));
                    mPreviousSelectedDrawerImage.setSelected(false);
                }
                mImageSupport.setSelected(true);
                mTextSupport.setTextColor(getResources().getColor(R.color.color_ffbf00));
                mPreviousSelectedDrawerImage = mImageSupport;
                mPreviousSelectedDrawerText = mTextSupport;
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
