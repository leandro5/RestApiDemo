package com.leandroasouza.restapidemo;

import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by leandrosouza on 24/06/2016.
 */
public interface BookAPI {

    @GET("getBooks")
    Call<List<Book>> getBooks();

}
