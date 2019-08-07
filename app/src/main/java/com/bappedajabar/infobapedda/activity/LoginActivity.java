package com.bappedajabar.infobapedda.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bappedajabar.infobapedda.R;
import com.bappedajabar.infobapedda.fragment.ProfileFragment;

public class LoginActivity extends AppCompatActivity {
    private Button btnEdit;
    private TextView regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnEdit = findViewById(R.id.btnRegis);
        regis = findViewById(R.id.regis);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(edit);
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
}
