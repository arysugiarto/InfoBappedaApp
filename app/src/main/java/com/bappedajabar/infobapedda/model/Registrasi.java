package com.bappedajabar.infobapedda.model;

import com.google.gson.annotations.SerializedName;

public class Registrasi {
    @SerializedName("message")
    private String status;

    public String getResponse() {
        return status;
    }

    public void setResponse(String response) {
        this.status = response;
    }
}
