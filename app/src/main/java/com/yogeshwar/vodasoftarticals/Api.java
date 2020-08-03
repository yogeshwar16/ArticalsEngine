package com.yogeshwar.vodasoftarticals;


import com.yogeshwar.vodasoftarticals.articalsPack.Article;

import com.yogeshwar.vodasoftarticals.usersPack.UserModel;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("blogs")
    Call<List<Article>> getArticals(
            @Query("page") int page,
            @Query("limit") int size
    );

    @GET("users")
    Call<List<UserModel>> getUsers(
            @Query("page") int page,
            @Query("limit") int size
    );
}
