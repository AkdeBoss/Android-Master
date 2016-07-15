package com.android.tenera.network;

import com.android.tenera.model.PojoModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by prajwalrai on 07/07/16.
 */
public interface ApiInterface {
    @GET("movie/top_rated")
    Call<PojoModel> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<PojoModel> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}

