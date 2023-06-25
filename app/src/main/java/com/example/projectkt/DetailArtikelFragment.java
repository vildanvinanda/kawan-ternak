package com.example.projectkt;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class DetailArtikelFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private String eddttl,eddTitle,eddIsiArtikel;
    private String uploadgambarartikel;

    public DetailArtikelFragment() {

    }

    public DetailArtikelFragment(String eddttl, String eddTitle, String eddIsiArtikel, String uploadgambarartikel) {
        this.eddttl = eddttl;
        this.eddTitle = eddTitle;
        this.eddIsiArtikel = eddIsiArtikel;
        this.uploadgambarartikel = uploadgambarartikel;
    }


    public static DetailArtikelFragment newInstance(String param1, String param2) {
        DetailArtikelFragment fragment = new DetailArtikelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_artikel, container, false);

        ImageView imageholder = view.findViewById(R.id.ImageHolder);
        TextView judulholder = view.findViewById(R.id.judulArtikel);
        TextView tglholder = view.findViewById(R.id.tglterbit);
        TextView isiholder = view.findViewById(R.id.isiArtikel);

        judulholder.setText(eddTitle);
        isiholder.setText(eddIsiArtikel);
        tglholder.setText(eddttl);
        Glide.with(imageholder.getContext())
                .load(uploadgambarartikel)
                .centerCrop()
                .into(imageholder);
        return view;
    }

    public void onBackPressed()
    {
//        AppCompatActivity activity = (AppCompatActivity)getContext();
//        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper, new HomeFragment()).addToBackStack(null).commit();
     }

}