package com.bappedajabar.infobapedda.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bappedajabar.infobapedda.R;


public class KegiatanFragment extends Fragment {

    private RecyclerView rvKamar;
    public KegiatanFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_kegiatan, container, false);
        // Inflate the layout for this fragment

//        rvKamar = view.findViewById(R.id.rv_kamar);
        return view;
    }
}
