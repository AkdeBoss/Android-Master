package com.android.baseproject.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.android.baseproject.R;
import com.android.baseproject.databinding.ActivityMainBinding;
import com.android.baseproject.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
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
}
