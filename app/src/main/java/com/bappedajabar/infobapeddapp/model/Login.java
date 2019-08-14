package com.bappedajabar.infobapeddapp.model;

import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("message")
    private String response;

    //get data tanpa index array
    @SerializedName("data")
    User DataUser;

    public String getResponse() {
        return response;
    }

    public User getDataUser() {
        return DataUser;
    }
}
