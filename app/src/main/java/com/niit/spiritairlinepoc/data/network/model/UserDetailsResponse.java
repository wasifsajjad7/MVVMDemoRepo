package com.niit.spiritairlinepoc.data.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class UserDetailsResponse {

    @Expose
    @SerializedName("data")
    private List<UserDetails> data;

    public List<UserDetails> getUserDetails(){
        return data;
    }
}
