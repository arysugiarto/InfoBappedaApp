package com.bappedajabar.infobapedda.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bappedajabar.infobapedda.R;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


public class HomeFragment extends Fragment {

    CarouselView carouselView;
    int[] sampleImages = {R.drawable.img1, R.drawable.img2, R.drawable.img3};
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = view.findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        carouselView.setImageListener(imageListener);


        return view;
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
//            imageView.setImageResource(sampleImages[position]);
            imageView.setImageResource(sampleImages[position]);

        }
    };
}
