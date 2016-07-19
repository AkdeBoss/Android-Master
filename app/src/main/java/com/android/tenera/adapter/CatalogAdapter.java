package com.android.tenera.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tenera.R;
import com.shopify.buy.model.Product;

import java.util.List;

/**
 * Created by raghavendra on 16/07/16.
 */
public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    private final LayoutInflater mLayoutInflator;
    private List<Product> mCatalogList;

    public CatalogAdapter(Context context, List<Product> catalogList) {
        mLayoutInflator = LayoutInflater.from(context);
        mCatalogList = catalogList;
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflator
                .inflate(R.layout.catalog_item, parent, false);
        return new CatalogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder holder, int position) {
        Product model = mCatalogList.get(position);
        holder.itemName.setText(model.getTitle());
    }


    @Override
    public int getItemCount() {
        return mCatalogList.size();
    }


    public class CatalogViewHolder extends RecyclerView.ViewHolder {

        private final ImageView itemImage;
        private final TextView itemName;
        private final TextView itemGrossWeight;
        private final TextView itemPrize;
        private final TextView itemAdd;
        private final TextView itemQuantity;
        private final TextView itemQuantityMinus;
        private final TextView itemQuantityPlus;

        public CatalogViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.catalog_item_image);
            itemName = (TextView) view.findViewById(R.id.catalog_item_name);
            itemGrossWeight = (TextView) view.findViewById(R.id.catalog_item_gross_weight);
            itemPrize = (TextView) view.findViewById(R.id.catalog_item_prize);
            itemAdd = (TextView) view.findViewById(R.id.catalog_item_add);
            itemQuantity = (TextView) view.findViewById(R.id.catalog_item_quantity);
            itemQuantityMinus = (TextView) view.findViewById(R.id.catalog_item_quantity_minus);
            itemQuantityPlus = (TextView) view.findViewById(R.id.catalog_item_quantity_plus);
        }

    }
}
