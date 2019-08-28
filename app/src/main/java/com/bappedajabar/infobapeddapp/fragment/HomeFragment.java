package com.bappedajabar.infobapeddapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bappedajabar.infobapeddapp.R;
import com.bappedajabar.infobapeddapp.activity.DetailActivity;
import com.bappedajabar.infobapeddapp.activity.EditActivity;
import com.bappedajabar.infobapeddapp.adapter.KegiatanAdapter;
import com.bappedajabar.infobapeddapp.adapter.KegiatanAdapterHome;
import com.bappedajabar.infobapeddapp.model.GetKegiatan;
import com.bappedajabar.infobapeddapp.model.Kegiatan;
import com.bappedajabar.infobapeddapp.model.UserRespon;
import com.bappedajabar.infobapeddapp.rest.Api;
import com.bappedajabar.infobapeddapp.rest.ApiInterface;
import com.bappedajabar.infobapeddapp.rest.ItemClickSupport;
import com.bappedajabar.infobapeddapp.rest.SessionManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class HomeFragment extends Fragment {

    private RecyclerView rvKegiatan;
    String token;
    SessionManager sessionManager;


    private KegiatanAdapter adapter;
    private List<Kegiatan> kegiatanList;

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.image3, R.drawable.image2, R.drawable.image1};

    public HomeFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);

        token = FirebaseInstanceId.getInstance().getToken();
        Log.e("TOKEN ",token);


        kegiatanList = new ArrayList<>();
        adapter = new KegiatanAdapter(this, kegiatanList);

        sessionManager = new SessionManager(getContext());

        rvKegiatan = view.findViewById(R.id.rv_terbaru);
        rvKegiatan.setHasFixedSize(true);
        rvKegiatan.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        getData();
        updateToken(token);


        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
            imageView.setImageResource(sampleImages[position]);

        }
    };

    private void updateToken(String token){

        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<UserRespon> call = apiInterface.token(sessionManager.getIdUSer(),token);
        call.enqueue(new Callback<UserRespon>() {
            @Override
            public void onResponse(Call<UserRespon> call, Response<UserRespon> response) {
                String message = response.body().getMessage();
                Log.e("UPDATE TOKEN",message);
            }

            @Override
            public void onFailure(Call<UserRespon> call, Throwable t) {
                Log.e("UPDATE TOKEN",t.getMessage());
            }
        });
    }

    private void getData(){
        final KegiatanAdapter kegiatanAdapter = new KegiatanAdapter(this.getActivity());
        ApiInterface apiInterface = Api.getUrl().create(ApiInterface.class);
        Call<GetKegiatan> call = apiInterface.getKegiatanLimit();

        call.enqueue(new Callback<GetKegiatan>() {
            @Override
            public void onResponse(Call<GetKegiatan> call, Response<GetKegiatan> response) {
                List<Kegiatan> lisKegiatan = response.body().getResult();
                kegiatanAdapter.setKegiatanList(lisKegiatan);
                rvKegiatan.setAdapter(kegiatanAdapter);
                kegiatanAdapter.setKegiatanList(lisKegiatan);
                reloadView(kegiatanAdapter,lisKegiatan);
            }

            @Override
            public void onFailure(Call<GetKegiatan> call, Throwable t) {

            }
        });
    }
    private void clickItemDetail(Kegiatan kegiatan) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("id_kegiatan", kegiatan.getIdKegiatan());
        intent.putExtra("nama", kegiatan.getNamaKegiatan());
        intent.putExtra("waktu", kegiatan.getWaktuMulai());
        intent.putExtra("waktus", kegiatan.getWaktuSelesai());
        intent.putExtra("tanggal", kegiatan.getTanggal());
        intent.putExtra("tempat", kegiatan.getTempat());
        intent.putExtra("asalsurat", kegiatan.getAsalsurat());

        startActivity(intent);
        getActivity().overridePendingTransition(0, 0);
    }


    public void reloadView(RecyclerView.Adapter adapter, final List<Kegiatan> list) {
        rvKegiatan.setAdapter(adapter);
        ItemClickSupport.addTo(rvKegiatan).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, final int position, View v) {
                Kegiatan listKegiatan = list.get(position);
                String idKegiatan = ((Kegiatan) listKegiatan).getIdKegiatan();
                clickItemDetail(list.get(position));
            }
        });

    }
}
