package com.bappedajabar.infobapeddapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bappedajabar.infobapeddapp.R;
import com.bappedajabar.infobapeddapp.activity.EditActivity;
import com.bappedajabar.infobapeddapp.rest.SessionManager;

import java.util.HashMap;

public class ProfileFragment extends Fragment {

    private Button btnEdit;
    TextView txt_nip,txt_nama,txt_email,txt_hp;
    ImageView imgkeluar;

    SessionManager sessionManager;
    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_profile, container, false);
        // Inflate the layout for this fragment
        btnEdit = view.findViewById(R.id.btnEdit);

        txt_nip = view.findViewById(R.id.nipProfile);
        txt_nama = view.findViewById(R.id.namaProfile);
        txt_email = view.findViewById(R.id.emailProfile);
        txt_hp = view.findViewById(R.id.noProfile);
        imgkeluar = view.findViewById(R.id.ig_logout);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent edit = new Intent(ProfileFragment.this.getActivity(), EditActivity.class);
                startActivity(edit);
            }
        });

        imgkeluar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.logout();
            }
        });



        sessionManager = new SessionManager(this.getActivity());
        HashMap<String, String> user = sessionManager.getUserDetils();
        String username = user.get(SessionManager.USERNAME);
        String nip = user.get(SessionManager.NIP);
        String email = user.get(SessionManager.EMAIL);
        String no_hp = user.get(SessionManager.NO_HP);
        txt_nama.setText(username);
        txt_nip.setText(nip);
        txt_email.setText(email);
        txt_hp.setText(no_hp);

        return view;
    }


}
