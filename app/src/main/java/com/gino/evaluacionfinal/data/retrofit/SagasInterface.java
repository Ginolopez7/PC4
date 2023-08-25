package com.gino.evaluacionfinal.data.retrofit;

import com.gino.evaluacionfinal.data.response.ShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SagasInterface {
    @GET("11be6634-f8af-41e4-9213-3241d66bd814")
    Call<ShowResponse> getShows();
}
