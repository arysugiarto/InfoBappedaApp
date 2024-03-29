package com.bappedajabar.infobapeddapp.rest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.bappedajabar.infobapeddapp.activity.LoginActivity;
import com.bappedajabar.infobapeddapp.activity.MainActivity;
import com.bappedajabar.infobapeddapp.fragment.HomeFragment;
import com.bappedajabar.infobapeddapp.fragment.ProfileFragment;
import com.bappedajabar.infobapeddapp.model.User;

import java.util.HashMap;
import java.util.List;

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    //    Context context;
    int mode = 0;
    ApiInterface mApiInterface;
    List<User> mUserList;

    private static final String PREF_NAME = "SessionPref";
    private static final String IS_LOGIN = "is_login";
    public static final String USERNAME = "username";
    public static final String NAMA = "nama";
    public static final String EMAIL = "email";
    public static final String ID_KEGIATAN = "id_kegiatan";
    public static final String ID_USER = "id_user";
    public static final String NIP = "nip";
    public static final String PASSWORD = "password";
    public static final String NO_HP = "no_hp";


    public static final String LOGIN_TYPE = "login_type";

    public SessionManager(Context _context) {
        this._context = _context;
        pref = _context.getSharedPreferences(PREF_NAME,mode);
        editor = pref.edit();
    }

    public SessionManager(HomeFragment profileFragment) {

    }


    public void checkLogin(){
        if(!this.is_login()){
            Intent i = new Intent(_context,LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }else{
            Intent i = new Intent(_context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }
    }

    private boolean is_login(){
        return pref.getBoolean(IS_LOGIN,false);
    }



    public void logOut() {
        editor = pref.edit();
        editor.putBoolean(IS_LOGIN, false);
        editor.apply();
    }
//    public String getUsername(){
//
//    }

    public HashMap<String, String> getUserDetils(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(USERNAME, pref.getString(USERNAME, null));
//        this.getUser();
        user.put(LOGIN_TYPE,pref.getString(LOGIN_TYPE,null));
        user.put(ID_USER,pref.getString(ID_USER, null));
        user.put(EMAIL,pref.getString(EMAIL, null));
        user.put(PASSWORD,pref.getString(PASSWORD,null));
        user.put(NIP,pref.getString(NIP, null));
        user.put(NO_HP,pref.getString(NO_HP, null));
        return user;

    }


    public void createSession(String nama) {
        editor.putBoolean(IS_LOGIN,true);
        editor.putString(NAMA,nama);

        editor.commit();
    }


    public void createEmail(String email){
        editor.putString(EMAIL, email);
        editor.commit();

    }
    public void createPassword(String password){
        editor.putString(PASSWORD, password);
        editor.commit();

    }

    public void createNip(String nip){
        editor.putString(NIP, nip);
        editor.commit();

    }

    public void createNohp(String no_hp){
        editor.putString(NO_HP, no_hp);
        editor.commit();

    }

    public void createUsername(String username){
        editor.putString(USERNAME, username);
        editor.commit();

    }
    public void createID(String id_user){
        editor.putString(ID_USER, id_user);
        editor.commit();

    }
    public String getIdUSer(){
        return pref.getString(ID_USER,"");
    }
}
