package com.example.alicja.aplikacjadietetyczna.Remote;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Alicja on 2018-03-07.
 */

public class RetrofitClient {
    private static Retrofit retrofit=null;
    public static Retrofit getClient(String baseUrl){
        if(retrofit==null) {
            retrofit= new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }
        return retrofit;
        }
    }

