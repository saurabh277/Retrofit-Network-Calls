package com.example.retrofitnetworkcalls;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://reqres.in/api/";
    @GET("users?")
    Call<UserResponse> getUsers();
}
