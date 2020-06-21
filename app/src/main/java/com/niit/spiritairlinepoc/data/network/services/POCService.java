package com.niit.spiritairlinepoc.data.network.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class POCService {

    private static final String BASE_URL = "https://reqres.in/api/";

    private ServiceApi mServiceApi;

    private static POCService instance;


    public static POCService getInstance() {
        if (instance == null) {
            instance = new POCService();
        }
        return instance;
    }

    private POCService() {
        Retrofit mRetrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL).build();
        mServiceApi = mRetrofit.create(ServiceApi.class);
    }

    public ServiceApi getApiService() {
        return mServiceApi;
    }

}
