package com.android.tenera.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tenera.R;
import com.android.tenera.Utils.Utils;
import com.android.tenera.fragments.CartFragment;
import com.shopify.buy.model.LineItem;

import java.util.List;

/**
 * Created by raghavendra on 16/07/16.
 */
public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private final LayoutInflater mLayoutInflator;
    private List<LineItem> cartLineItems;
    private Context mContext;
    private CartFragment cartFragment;

    public CartAdapter(Context context, List<LineItem> catalogList, CartFragment fragment) {
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
        final LineItem model = cartLineItems.get(position);

        holder.itemName.setText(model.getTitle());
//        PicassoHelper.getPicassoInstance(mContext).load("").
//                placeholder(R.drawable.logoplaceholder).
//                error(R.drawable.logoplaceholder).
//                into((holder.itemImage));
        holder.itemGrossWeight.setText("" + model.getGrams());
        holder.itemPrice.setText(Utils.addRuppeSymbol(model.getLinePrice()));
        holder.itemPriceDesc.setText("" + model.getQuantity() + " X " + model.getPrice());

        holder.itemQuantity.setText("" + model.getQuantity());
        holder.itemQuantityPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFragment.addCartItem(model);
            }
        });
        holder.itemQuantityMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFragment.removeCartItem(model);
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
        private final Button itemQuantityMinus;
        private final Button itemQuantityPlus;

        public CartViewHolder(View view) {
            super(view);
            itemImage = (ImageView) view.findViewById(R.id.cart_item_image);
            itemName = (TextView) view.findViewById(R.id.cart_item_title);
            itemGrossWeight = (TextView) view.findViewById(R.id.cart_item_desc);
            itemPrice = (TextView) view.findViewById(R.id.cart_item_cost);
            itemPriceDesc = (TextView) view.findViewById(R.id.cart_item_cost_desc);
            itemQuantity = (TextView) view.findViewById(R.id.overlay_quantity);
            itemQuantityMinus = (Button) view.findViewById(R.id.btn_minus);
            itemQuantityPlus = (Button) view.findViewById(R.id.btn_plus);
        }

    }
}
