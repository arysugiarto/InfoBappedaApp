package com.bappedajabar.infobapeddapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bappedajabar.infobapeddapp.R;
import com.bappedajabar.infobapeddapp.model.Registrasi;
import com.bappedajabar.infobapeddapp.model.User;
import com.bappedajabar.infobapeddapp.model.UserRespon;
import com.bappedajabar.infobapeddapp.rest.ApiInterface;
import com.bappedajabar.infobapeddapp.rest.ApiUrl;
import com.bappedajabar.infobapeddapp.rest.SessionManager;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    private Button buttonEdit;
    private EditText editTextNip,editTextNama,editTextEmail,editTextPassword,editTextNoHp;


    SharedPreferences preferences;
    ProgressDialog progressDialog;
    private String refreshFlag = "0";

    Context mContext;
    ApiInterface mApiService;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        editTextNip = findViewById(R.id.nipEdt);
        editTextNama = findViewById(R.id.namaEdt);
        editTextEmail = findViewById(R.id.emailEdt);
        editTextPassword = findViewById(R.id.passwordEdt);
        editTextNoHp = findViewById(R.id.teleponEdt);

        buttonEdit = findViewById(R.id.btnEdit);
//        progressDialog = new ProgressDialog(this);

        sessionManager = new SessionManager(EditActivity.this);
        HashMap<String, String> user = sessionManager.getUserDetils();

        buttonEdit.setOnClickListener(view -> {
            if(editTextNip.getText().toString().length()==0){
                editTextNip.setError("NIP tidak boleh kosong!");
            }else if(editTextPassword.getText().toString().length()==0){
                editTextPassword.setError("Password tidak boleh kosong!");
            }else if(editTextNama.getText().toString().length()==0){
                editTextNama.setError("Nama tidak boleh kosong!");
            } else if(editTextEmail.getText().toString().length()==0){
                editTextEmail.setError("Email tidak boleh kosong!");
            } else if(editTextNoHp.getText().toString().length()==0){
                editTextNoHp.setError("No Hp tidak boleh kosong!");
            }
            else {
                update();
//                Toast.makeText(getApplicationContext(), "Data Berhasil di update!",
//                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void update(){
//        progressDialog.setMessage("Loading..");
//        progressDialog.setCancelable(false);
//        progressDialog.show();
//        refreshFlag = "1";

        String nip = editTextNip.getText().toString();
        String nama = editTextNama.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String no_hp = editTextNoHp.getText().toString();


        HashMap<String, String> user = sessionManager.getUserDetils();
        String id = user.get(SessionManager.ID_USER);
        ApiInterface api = ApiUrl.getClient().create(ApiInterface.class);
        Call<UserRespon> postItem = api.update(id,nip,nama,email,password,no_hp);
        postItem.enqueue(new Callback<UserRespon>() {
            @Override
            public void onResponse(Call<UserRespon> call, Response<UserRespon> response) {
//                progressDialog.hide();
                String status = response.body().getMessage();
                Log.e("klnald", response.body().getMessage());
                User user = response.body().getData();
                Log.e("user", user.getNip());
                Log.e("status", status);

                if (status.equals("sukses")) {
                    Toast.makeText(EditActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditActivity.this, "Data gagal disimpan", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserRespon> call, Throwable t) {
                    Log.e("Failure",t.getMessage()
                    );
            }

        });
    }


}
