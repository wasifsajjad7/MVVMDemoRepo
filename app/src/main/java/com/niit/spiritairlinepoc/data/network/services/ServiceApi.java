package com.niit.spiritairlinepoc.data.network.services;


import com.niit.spiritairlinepoc.data.network.model.LoginResponse;
import com.niit.spiritairlinepoc.data.network.model.UserDetailsResponse;
import com.niit.spiritairlinepoc.data.network.ApiConstants;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ServiceApi {

    @GET(ApiConstants.USER_DETAILS)
    Call<UserDetailsResponse> getUserDetails();


    @POST(ApiConstants.LOGIN)
    Call<LoginResponse>  login(@Body RequestBody body);


}
