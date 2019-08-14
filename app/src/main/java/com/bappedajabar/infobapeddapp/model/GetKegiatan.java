package com.bappedajabar.infobapeddapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetKegiatan {
    @SerializedName("result")
    @Expose
    private List<Kegiatan> result = null;

    public List<Kegiatan> getResult() {
        return result;
    }

    public void setResult(List<Kegiatan> result) {
        this.result = result;
    }
}
