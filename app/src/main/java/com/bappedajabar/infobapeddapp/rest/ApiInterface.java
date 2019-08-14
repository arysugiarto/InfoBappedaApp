package com.bappedajabar.infobapeddapp.rest;

import com.bappedajabar.infobapeddapp.model.GetKegiatan;
import com.bappedajabar.infobapeddapp.model.Login;
import com.bappedajabar.infobapeddapp.model.Registrasi;
import com.bappedajabar.infobapeddapp.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("Kegiatan/datakegiatan")
    Call<GetKegiatan> getKegiatan();


    @FormUrlEncoded
    @POST("User/signup")
    Call<Registrasi> registerRequest(
            @Field("nip") String nip,
            @Field("nama") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("no_hp") String no_hp);

    @POST("User/signup")
    Call<User> profile(
//            @Query("id_user") String id_user
    );

//
    @FormUrlEncoded
    @POST("User/login")
    Call<Login> userLogin(
            @Field("nip") String nip,
            @Field("password") String password
    );




}
