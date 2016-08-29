package com.android.tenera.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.Utils.Utils;
import com.android.tenera.activity.MainActivity;
import com.android.tenera.adapter.CustomPagerAdapter;
import com.android.tenera.application.MainApplication;
import com.android.tenera.model.MessageEvent;
import com.shopify.buy.model.Collection;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by raghavendra on 11/07/16.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    private PagerAdapter itemAdapter;
    private ArrayList<String> tabsList = new ArrayList<>();
    private ArrayList<String> collectionIdList = new ArrayList<>();
    private ViewPager mPagerContainer;
    private TabLayout mTabContainer;
    private TextView cartIcon;
    private ImageView cartArrow;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mPagerContainer = (ViewPager) view.findViewById(R.id.page_container);
        mTabContainer = (TabLayout) view.findViewById(R.id.tab_container);
        cartIcon = (TextView) view.findViewById(R.id.cart_item_count);
        cartArrow=(ImageView)view.findViewById(R.id.left_arrow);
        cartArrow.setOnClickListener(this);
        MainActivity.getInstance().showLoader();
        fetchCollections();


        return view;
    }

    private void fetchCollections() {
        MainApplication.getBuyClient().getCollections(new Callback<List<Collection>>() {
            @Override
            public void success(List<Collection> collections, Response response) {
                try {
                    tabsList.clear();
                    collectionIdList.clear();
                    Log.e("", collections.toString());
                    for (int i = 0; i < collections.size(); i++) {
                        Collection collection = collections.get(i);
                        tabsList.add(collection.getTitle());
                        collectionIdList.add(""+collection.getCollectionId());
                    }
                    Utils.setCollecionTitles(tabsList);
                    Utils.setCollectionIds(collectionIdList);
                    itemAdapter = new CustomPagerAdapter(getChildFragmentManager(), MainActivity.getInstance(), tabsList);
                    mPagerContainer.setAdapter(itemAdapter);
                    mTabContainer.setupWithViewPager(mPagerContainer);
                    mPagerContainer.setOffscreenPageLimit(Utils.getCollecionTitles().size());
                    MainActivity.getInstance().hideLoader();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("", MainApplication.getBuyInstance().getErrorBody(error));
                MainActivity.getInstance().hideLoader();

            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_arrow:
                ((MainActivity) getActivity()).replaceFragmentWithBackStack(new CartFragment());
                break;
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchCollections();
        cartIcon.setText("" + MainApplication.getCart().getSize());
    }

    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        cartIcon.setText("" + MainApplication.getCart().getSize());
    }
}
