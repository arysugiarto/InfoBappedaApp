package com.bappedajabar.infobapeddapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bappedajabar.infobapeddapp.R;
import com.bappedajabar.infobapeddapp.model.Login;
import com.bappedajabar.infobapeddapp.rest.Api;
import com.bappedajabar.infobapeddapp.rest.ApiInterface;
import com.bappedajabar.infobapeddapp.rest.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Button btnLogin;
    private TextView regis;
    EditText editTextNip,editTextPassword;
    SessionManager sessionManager;
    ProgressDialog progressDialog;
    String refreshFlag = "0";

    private TextView mTextMessage;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnLogin = findViewById(R.id.btnLogin);
        regis = findViewById(R.id.regis);




        editTextNip = findViewById(R.id.usernameLogin);
        editTextPassword = findViewById(R.id.passwordLogin);
        progressDialog = new ProgressDialog(this);

        sessionManager = new SessionManager(getApplicationContext());

        editTextPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    login();
                    return true;
                }
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextNip.getText().toString().length()==0){
                    editTextNip.setError("NIP tidak boleh kosong!");
                }else if(editTextPassword.getText().toString().length()==0){
                    editTextPassword.setError("Password tidak boleh kosong!");
                }else {
                    login();
                }
            }
        });

        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regis = new Intent(LoginActivity.this,RegsitrasiActivity.class);
                startActivity(regis);
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else { Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show(); }

        mBackPressed = System.currentTimeMillis();
    }

    private void login(){
        progressDialog.setMessage("Loading..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        refreshFlag = "1";
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);

//        Call<Login>
        retrofit2.Call<Login> call = apiInterface.userLogin(editTextNip.getText().toString(), editTextPassword.getText().toString());

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {

                String nama = response.body().getResponse();

                String id_user = String.valueOf(response.body().getDataUser().getIdUser());
                String username = response.body().getDataUser().getNama();
                String nip = response.body().getDataUser().getNip();
                String email = response.body().getDataUser().getEmail();
                String nohp = response.body().getDataUser().getNohp();

                Log.e("Respone",nama);

                if (nama.equals("sukses")){
                    sessionManager.createID(id_user);
                    sessionManager.createSession(nama);
                    sessionManager.createNip(nip);
                    sessionManager.createNohp(nohp);
                    sessionManager.createEmail(email);
                    sessionManager.createUsername(username);
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    finish();
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Gagal Login", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<Login> call, Throwable t) {

            }
        });

    }
}
