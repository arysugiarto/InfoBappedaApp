package com.bappedajabar.infobapedda.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bappedajabar.infobapedda.R;
import com.bappedajabar.infobapedda.activity.DetailActivity;


public class KegiatanFragment extends Fragment {

    TextView readMore;
    private RecyclerView rvKamar;
    public KegiatanFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_kegiatan, container, false);
        // Inflate the layout for this fragment

//        rvKamar = view.findViewById(R.id.rv_kamar);
        readMore = view.findViewById(R.id.tv_readmore);


        readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(KegiatanFragment.this.getActivity(), DetailActivity.class);
                startActivity(detail);
            }
        });
        return view;
    }
}
