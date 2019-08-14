package com.bappedajabar.infobapeddapp.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUrl {
        public static String BASE_URL ="http://10.13.2.136/InfoBappedaWeb/api/";

//    public static String BASE_URL ="http://192.168.137.1/webservice/SmartKosanWebService/api/";

    private static Retrofit retrofit;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
}
