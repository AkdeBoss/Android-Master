package com.android.tenera.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.fragments.CartFragment;
import com.android.tenera.model.ProductDTO;
import com.android.tenera.network.PicassoHelper;
import com.shopify.buy.model.CartLineItem;
import com.shopify.buy.model.Product;

import java.util.List;

/**
 * Created by raghavendra on 16/07/16.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final LayoutInflater mLayoutInflator;
    private List<CartLineItem> cartLineItems;
    private Context mContext;
    private CartFragment cartFragment;

    public CartAdapter(Context context, List<CartLineItem> catalogList, CartFragment fragment) {
        mLayoutInflator = LayoutInflater.from(context);
        cartLineItems = catalogList;
        mContext = context;
        cartFragment = fragment;
    }

    @Override
    public CartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mLayoutInflator
                .inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CartViewHolder holder, int position) {
        final CartLineItem model = cartLineItems.get(position);

        holder.itemName.setText(model.getVariant().getProductTitle());
//        PicassoHelper.getPicassoInstance(mContext).load("").
//                placeholder(R.drawable.logoplaceholder).
//                error(R.drawable.logoplaceholder).
//                into((holder.itemImage));
        holder.itemGrossWeight.setText("" + model.getVariant().getGrams());
        holder.itemPrice.setText(model.getLinePrice());
        holder.itemQuantity.setText("" + model.getQuantity());
        holder.itemQuantityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFragment.addCartItem(model.getVariant(), Integer.parseInt("" + (model.getQuantity() + 1)));
                notifyDataSetChanged();

            }
        });
        holder.itemQuantityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFragment.removeCartItem(model.getVariant(), Integer.parseInt("" + (model.getQuantity() - 1)));
                notifyDataSetChanged();

            }
        });
    }


    @Override
    public int getItemCount() {
        return cartLineItems.size();
    }


    public class CartViewHolder extends RecyclerView.ViewHolder {

        private final ImageView itemImage;
        private final TextView itemName;
        private final TextView itemGrossWeight;
        private final TextView itemPrice;
        private final TextView itemPriceDesc;
        private final TextView itemQuantity;
        private final TextView itemQuantityMinus;
        private final TextView itemQuantityPlus;

        public CartViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.cart_item_image);
            itemName = (TextView) view.findViewById(R.id.cart_item_title);
            itemGrossWeight = (TextView) view.findViewById(R.id.cart_item_desc);
            itemPrice = (TextView) view.findViewById(R.id.cart_item_cost);
            itemPriceDesc = (TextView) view.findViewById(R.id.cart_item_cost_desc);
            itemQuantity = (TextView) view.findViewById(R.id.overlay_quantity);
            itemQuantityMinus = (TextView) view.findViewById(R.id.minus);
            itemQuantityPlus = (TextView) view.findViewById(R.id.plus);
        }

    }
}
