package com.bappedajabar.infobapeddapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.bappedajabar.infobapeddapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_judul_detail)
    TextView tvJudulnya;
    @BindView(R.id.pukul_mulai)
    TextView tvPukulmulai;
    @BindView(R.id.pukul_selesai)
    TextView tvPukulSelesai;
    @BindView(R.id.tanggal_detail)
    TextView tvTanggalnya;
    @BindView(R.id.tempat_detail)
    TextView tvTempatnya;
    @BindView(R.id.asalsurat_detail)
    TextView tvAsalSurat;

    private TextView getTvJudulnya, getTvPukulmulai,getTvPukulSelesai,getTvTanggalnya,getTvTempatnya,getTvAsalSurat;
    private String sJudul, sPukulMulai,sPukulSelesai,sTanggal,sTempat,sAsal;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        id = getIntent().getStringExtra("id_kegiatan");
        ButterKnife.bind(this);

        getTvJudulnya = findViewById(R.id.tv_judul_detail);
        getTvPukulmulai = findViewById(R.id.pukul_mulai);
        getTvPukulSelesai = findViewById(R.id.pukul_selesai);
        getTvTanggalnya = findViewById(R.id.tanggal_detail);
        getTvTempatnya = findViewById(R.id.tempat_detail);
        getTvAsalSurat = findViewById(R.id.asalsurat_detail);

        getData();

    }

    private void getData(){

        sJudul = getIntent().getStringExtra("nama");
        sPukulMulai = getIntent().getStringExtra("waktu");
        sPukulSelesai = getIntent().getStringExtra("waktus");
        sTanggal = getIntent().getStringExtra("tanggal");
        sTempat = getIntent().getStringExtra("tempat");
        sAsal = getIntent().getStringExtra("asalsurat");

        tvJudulnya.setText(sJudul);
        tvPukulmulai.setText(sPukulMulai);
        tvPukulSelesai.setText(sPukulSelesai);
        tvTanggalnya.setText(sTanggal);
        tvTempatnya.setText(sTempat);
        tvAsalSurat.setText(sAsal);

    }
}
