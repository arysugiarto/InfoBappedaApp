package com.bappedajabar.infobapeddapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bappedajabar.infobapeddapp.R;
import com.bappedajabar.infobapeddapp.model.Registrasi;
import com.bappedajabar.infobapeddapp.rest.ApiInterface;
import com.bappedajabar.infobapeddapp.rest.ApiUrl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegsitrasiActivity extends AppCompatActivity {

    private Button buttonSign;
    private EditText editTextNip,editTextNama,editTextEmail,editTextPassword,editTextNoHp;

    ProgressDialog progressDialog;
    private String refreshFlag = "0";

    Context mContext;
    ApiInterface mApiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsitrasi);

        editTextNip = findViewById(R.id.edt_username);
        editTextNama = findViewById(R.id.edt_nama);
        editTextEmail = findViewById(R.id.edt_email);
        editTextPassword = findViewById(R.id.edt_password);
        editTextNoHp = findViewById(R.id.edt_telepon);

        buttonSign = findViewById(R.id.btn_sign);
        progressDialog = new ProgressDialog(this);


        buttonSign.setOnClickListener(view -> {


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
                regis();
//                Toast.makeText(getApplicationContext(), "Login Berhasil!",
//                        Toast.LENGTH_SHORT).show();
            }

    });
    }

    private void regis(){
            progressDialog.setMessage("Loading..");
            progressDialog.setCancelable(false);
            progressDialog.show();
            refreshFlag = "1";

            String nip = editTextNip.getText().toString();
            String name = editTextNama.getText().toString();
            String email = editTextEmail.getText().toString();
            String password = editTextPassword.getText().toString();
            String no_hp = editTextNoHp.getText().toString();



            ApiInterface api = ApiUrl.getClient().create(ApiInterface.class);
            Call<Registrasi> postItem = api.registerRequest(nip,name,email,password,no_hp);
            postItem.enqueue(new Callback<Registrasi>() {
                @Override
                public void onResponse(Call<Registrasi> call, Response<Registrasi> response) {
                    progressDialog.hide();
                    String status = response.body().getResponse();

                    if (status.equals("sukses")) {
                        Toast.makeText(RegsitrasiActivity.this, "Data berhasil disimpan", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegsitrasiActivity.this,LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegsitrasiActivity.this, "Data gagal disimpan", Toast
                                .LENGTH_SHORT).show();
                    }

                }

                @Override
                public void onFailure(Call<Registrasi> call, Throwable t) {

                }

            });
        }

}
