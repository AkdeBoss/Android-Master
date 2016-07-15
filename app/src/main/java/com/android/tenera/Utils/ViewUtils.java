package com.android.tenera.Utils;

import android.databinding.BindingAdapter;
import android.graphics.Typeface;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tenera.R;

/**
 * Created by prajwalrai on 11/07/16.
 */
public class ViewUtils {

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(Typeface.createFromAsset(textView.getContext().getAssets(), "fonts/" + fontName));
    }

    @BindingAdapter({"bind:selector"})
    public static void setSelector(ImageView view,String value) {
        if(view.isSelected()){
            view.setColorFilter(R.color.color_ffbf00);
        }else {
            view.setColorFilter(null);
        }
    }

    @BindingAdapter({"bind:selector"})
    public static void setSelector(TextView view,String value) {
        if(view.isSelected()){
            view.setTextColor(view.getContext().getResources().getColor(R.color.color_ffbf00));
        }else {
            view.setTextColor(view.getContext().getResources().getColor(R.color.color_8a2d2d2d));
        }
    }
}
