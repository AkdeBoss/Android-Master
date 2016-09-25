package com.android.tenera.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.Utils.Utils;
import com.android.tenera.fragments.CatalogFragment;
import com.android.tenera.model.MessageEvent;
import com.android.tenera.model.ProductDTO;
import com.android.tenera.network.PicassoHelper;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by raghavendra on 16/07/16.
 */
public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder> {

    private final LayoutInflater mLayoutInflator;
    private ArrayList<ProductDTO> mCatalogList;
    private Context mContext;
    private CatalogFragment catalogFragment;

    public CatalogAdapter(Context context, ArrayList<ProductDTO> catalogList, CatalogFragment fragment) {
        mLayoutInflator = LayoutInflater.from(context);
        mCatalogList = catalogList;
        mContext = context;
        catalogFragment = fragment;
    }

    @Override
    public CatalogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflator
                .inflate(R.layout.catalog_item, parent, false);
        return new CatalogViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder holder, final int position) {
        final ProductDTO model = mCatalogList.get(position);
        holder.itemName.setText(model.getTitle());
        PicassoHelper.getPicassoInstance(mContext).load(model.getImage()).
                placeholder(R.drawable.logoplaceholder).
                error(R.drawable.logoplaceholder).
                into((holder.itemImage));
        holder.itemGrossWeight.setText("" + model.getWeight());
        holder.itemPrice.setText(Utils.addRuppeSymbol(String.valueOf(model.getPrice())));
        holder.itemQuantity.setText("" + model.getQuantity());
        if (model.getQuantity() == 0) {
            holder.itemAdd.setVisibility(View.VISIBLE);
            holder.itemQuantityLayout.setVisibility(View.GONE);
        } else {
            holder.itemAdd.setVisibility(View.GONE);
            holder.itemQuantityLayout.setVisibility(View.VISIBLE);
        }
        holder.itemAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogFragment.addCartItem(model.getVariant());
                model.setQuantity(model.getQuantity() + 1);
                notifyItemChanged(position);
                EventBus.getDefault().post(new MessageEvent(true));


            }
        });

        holder.itemQuantityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogFragment.addCartItem(model.getVariant());
                model.setQuantity(model.getQuantity() + 1);
                notifyItemChanged(position);
                EventBus.getDefault().post(new MessageEvent(true));

            }
        });
        holder.itemQuantityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                catalogFragment.removeCartItem(model.getVariant());
                model.setQuantity(model.getQuantity() - 1);
                notifyItemChanged(position);
                EventBus.getDefault().post(new MessageEvent(false));

            }
        });
    }


    @Override
    public int getItemCount() {
        return mCatalogList.size();
    }


    public class CatalogViewHolder extends RecyclerView.ViewHolder {

        private final ImageView itemImage;
        private final TextView itemName;
        private final TextView itemGrossWeight;
        private final TextView itemPrice;
        private final TextView itemQuantity;
        private final TextView itemAdd;
        private final LinearLayout itemQuantityLayout;
        private final TextView itemQuantityMinus;
        private final TextView itemQuantityPlus;

        public CatalogViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.catalog_item_image);
            itemName = (TextView) view.findViewById(R.id.catalog_item_name);
            itemGrossWeight = (TextView) view.findViewById(R.id.catalog_item_gross_weight);
            itemPrice = (TextView) view.findViewById(R.id.catalog_item_prize);
            itemAdd = (TextView) view.findViewById(R.id.catalog_item_add);
            itemQuantity = (TextView) view.findViewById(R.id.catalog_item_quantity);
            itemQuantityLayout = (LinearLayout) view.findViewById(R.id.quantity_layout);
            itemQuantityMinus = (TextView) view.findViewById(R.id.catalog_item_quantity_minus);
            itemQuantityPlus = (TextView) view.findViewById(R.id.catalog_item_quantity_plus);
        }

    }
}
