package com.bappedajabar.infobapedda.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kegiatan {
    @SerializedName("id_kegiatan")
    @Expose
    private String idKegiatan;

    @SerializedName("nama")
    @Expose
    private String namaKegiatan;

    @SerializedName("tempat")
    @Expose
    private String tempat;

    @SerializedName("asalsurat")
    @Expose
    private String asalsurat;

    @SerializedName("waktu_mulai")
    @Expose
    private String waktuMulai;

    @SerializedName("waktu_selesai")
    @Expose
    private String waktuSelesai;

    @SerializedName("tanggal")
    @Expose
    private String tanggal;


    public Kegiatan(){

    }


    public Kegiatan(String idKegiatan, String namaKegiatan, String tempat, String asalsurat, String waktuMulai, String waktuSelesai, String tanggal) {
        this.idKegiatan = idKegiatan;
        this.namaKegiatan = namaKegiatan;
        this.tempat = tempat;
        this.asalsurat = asalsurat;
        this.waktuMulai = waktuMulai;
        this.waktuSelesai = waktuSelesai;
        this.tanggal = tanggal;

    }

    public String getIdKegiatan() {
        return idKegiatan;
    }

    public void setIdKegiatan(String idKegiatan) {
        this.idKegiatan = idKegiatan;
    }

    public String getNamaKegiatan() {
        return namaKegiatan;
    }

    public void setNamaKegiatan(String namaKegiatan) {
        this.namaKegiatan = namaKegiatan;
    }

    public String getTempat() {
        return tempat;
    }

    public void setTempat(String tempat) {
        this.tempat = tempat;
    }

    public String getAsalsurat() {
        return asalsurat;
    }

    public void setAsalsurat(String asalsurat) {
        this.asalsurat = asalsurat;
    }

    public String getWaktuMulai() {
        return waktuMulai;
    }

    public void setWaktuMulai(String waktuMulai) {
        this.waktuMulai = waktuMulai;
    }

    public String getWaktuSelesai() {
        return waktuSelesai;
    }

    public void setWaktuSelesai(String waktuSelesai) {
        this.waktuSelesai = waktuSelesai;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }


}
