package com.leandroasouza.restapidemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by leandrosouza on 24/06/2016.
 */
public interface BookAPI {

    @Headers({
            "content-type: application/json",
            "cache-control: no-cache"
    })
    @GET("getBooks")
    Call<List<Book>> getBooks();
}
