package com.android.tenera.network;

import com.android.tenera.fragments.BaseFragment;
import com.android.tenera.model.PojoModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by prajwalrai on 15/07/16.
 */
public class NetworkManager {

    private static NetworkManager mInstance;

    private NetworkManager() {

    }

    public static synchronized NetworkManager getInstance() {
        if (mInstance == null) {
            mInstance = new NetworkManager();
        }
        return mInstance;
    }

    public void makeSomeApiCall(final BaseFragment.NetworkCallListener listener) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<PojoModel> call = apiService.getMovieDetails(999, "saasa");
        call.enqueue(new Callback<PojoModel>() {
            @Override
            public void onResponse(Call<PojoModel> call, Response<PojoModel> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<PojoModel> call, Throwable t) {
                listener.onFailure(t);
            }
        });


    }
}
