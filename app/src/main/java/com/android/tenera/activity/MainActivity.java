package com.android.tenera.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.databinding.ActivityMainBinding;
import com.android.tenera.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ActivityMainBinding mBinding;
    private TextView mPreviousSelectedDrawerText;
    private ImageView mPreviousSelectedDrawerImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mBinding.setHandler(this);
        setSupportActionBar(mBinding.customToolbar);
        if (savedInstanceState == null) {
            loadHomeFragment();
        }
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
                mBinding.imageHome.setColorFilter(getColor(R.color.color_ffbf00));
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
                mBinding.imageExploreCategories.setColorFilter(getColor(R.color.color_ffbf00));
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
                mBinding.imageCart.setColorFilter(getColor(R.color.color_ffbf00));
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
                mBinding.imagePromotionalOffers.setColorFilter(getColor(R.color.color_ffbf00));
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
                mBinding.imageIniviteFriend.setColorFilter(getColor(R.color.color_ffbf00));
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
                mBinding.imageAbout.setColorFilter(getColor(R.color.color_ffbf00));
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
                mBinding.imageSupport.setColorFilter(getColor(R.color.color_ffbf00));
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
