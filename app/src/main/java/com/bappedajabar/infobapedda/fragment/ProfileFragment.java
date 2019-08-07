package com.bappedajabar.infobapedda.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bappedajabar.infobapedda.R;
import com.bappedajabar.infobapedda.activity.EditActivity;

public class ProfileFragment extends Fragment {

    private Button btnEdit;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        btnEdit = view.findViewById(R.id.btnEdit);
//        rvKamar = view.findViewById(R.id.rv_kamar);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(ProfileFragment.this.getActivity(), EditActivity.class);
                startActivity(edit);
            }
        });
        return view;
    }
}
