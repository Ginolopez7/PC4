package com.gino.evaluacionfinal.data.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitHelper {

    public static Retrofit instance;
    public static SagasInterface service;


    public static Retrofit getInstance(){
        if (instance == null) {
            // link2 - https://run.mocky.io/v3/11be6634-f8af-41e4-9213-3241d66bd814
            // https://run.mocky.io/v3/dbbc8522-457a-46e7-96d4-2fc5da055df4
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://run.mocky.io/v3/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getLogginBuilde().build())
                    .build();
            instance = retrofit;
        }
        return instance;
    }

    public static OkHttpClient.Builder getLogginBuilde(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.level(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(interceptor);
        return builder;
    }

    public static SagasInterface getService(){
        if (service == null) {
            service = getInstance().create(SagasInterface.class);
        }
        return service;
    }
}
