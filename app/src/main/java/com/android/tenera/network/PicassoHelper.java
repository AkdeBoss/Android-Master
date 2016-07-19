package com.android.tenera.network;

/**
 * Created by raghav on 29/02/16.
 */

import android.content.Context;

import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

public class PicassoHelper {

    /**
     * Static Picasso Instance
     */
    private static Picasso picassoInstance = null;

    /**
     * PicassoHelper Constructor
     *
     * @param context application Context
     */
    private PicassoHelper(Context context) {

        Downloader downloader = new OkHttpDownloader(context, Integer.MAX_VALUE);
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(downloader);

        picassoInstance = builder.build();
    }

    /**
     * Get Singleton Picasso Instance
     *
     * @param context application Context
     * @return Picasso instance
     */
    public static Picasso getPicassoInstance(Context context) {

        if (picassoInstance == null) {

            new PicassoHelper(context);
            return picassoInstance;
        }

        return picassoInstance;
    }

}