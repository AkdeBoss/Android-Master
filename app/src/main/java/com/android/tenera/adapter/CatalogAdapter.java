package com.android.tenera.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tenera.BR;
import com.android.tenera.R;
import com.android.tenera.model.PojoModel;
import com.shopify.buy.model.Product;

import java.util.List;

/**
 * Created by raghavendra on 16/07/16.
 */
public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final LayoutInflater mLayoutInflator;
    private List<Product> mCatalogList;

    public CatalogAdapter(Context context, List<Product> catalogList){
        mLayoutInflator = LayoutInflater.from(context);
        mCatalogList=catalogList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding viewDataBinding = DataBindingUtil.inflate(mLayoutInflator, R.layout.catalog_item, parent, false);
        return (new CatalogViewHolder(viewDataBinding));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Product model = mCatalogList.get(position);
        ViewDataBinding viewDataBindingParent = ((CatalogViewHolder) holder).getViewDataBinding();
        viewDataBindingParent.setVariable(BR.item, model);
        handleOnClick(viewDataBindingParent,model);
    }

    private void handleOnClick(ViewDataBinding viewDataBindingParent, Product model) {
        viewDataBindingParent.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
            }
        });
    }


    @Override
    public int getItemCount() {
        return mCatalogList.size();
    }


    public class CatalogViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mViewDataBinding;

        public CatalogViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }
}