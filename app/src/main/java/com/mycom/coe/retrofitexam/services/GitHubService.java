package com.mycom.coe.retrofitexam.services;

import com.mycom.coe.retrofitexam.model.GitHubUsers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by coe on 7/31/2017.
 */

public interface GitHubService {
    @GET("users/{user}")
    Call<GitHubUsers> loadUser(@Path("user") String user);
}
