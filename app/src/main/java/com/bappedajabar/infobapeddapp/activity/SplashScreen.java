package com.bappedajabar.infobapeddapp.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.Toast;

import com.bappedajabar.infobapeddapp.R;
import com.bappedajabar.infobapeddapp.rest.SessionManager;

public class SplashScreen extends AppCompatActivity {
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isConnected(SplashScreen.this)) buildDialog(SplashScreen.this).show();
        else {
//            Toast.makeText(SplashScreen.this,"Welcome", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_splash_screen);
            sessionManager = new SessionManager(getApplicationContext());
            sessionManager.getIdUSer();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    sessionManager.checkLogin();
                    finish();
                }
            }, 3000);
        }
    }

    public boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mNetInfo = cm.getActiveNetworkInfo();

        if (mNetInfo != null && mNetInfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("Tidak Ada Koneksi Internet");
        builder.setMessage("Anda perlu cek kembali jaringan internet anda . Tekan ok untuk keluar");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }
    }
