package com.example.alicja.aplikacjadietetyczna;

import com.example.alicja.aplikacjadietetyczna.Model.Results;
import com.example.alicja.aplikacjadietetyczna.Remote.IGoogleAPIService;
import com.example.alicja.aplikacjadietetyczna.Remote.RetrofitClient;

/**
 * Created by Alicja on 2018-03-07.
 */

public class Common {
    public static  Results currentResult;
    private static final String GOOGLE_API_URL="https://maps.googleapis.com/";
    public static IGoogleAPIService getGoogleAPIService()
    {
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }
}
