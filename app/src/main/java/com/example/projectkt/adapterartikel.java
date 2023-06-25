package com.example.projectkt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class adapterartikel extends FirebaseRecyclerAdapter <ProjectModel, adapterartikel.myartikel>{

    Context context;


    public adapterartikel(@NotNull FirebaseRecyclerOptions<ProjectModel> options) {
        super(options);
    }


    //BARU SAMPE SINI MENIT 34:48
    @Override
    protected void onBindViewHolder(@NonNull myartikel holder, int position, @NotNull ProjectModel projectModel) {
        holder.txtjudul9solo.setText(projectModel.getEddTitle());
        holder.txttgl6solo.setText(projectModel.getEddttl());
        Glide.with(holder.image1.getContext())
                .load(projectModel.getUploadgambarartikel())
                .centerCrop()
                .into(holder.image1);

        holder.image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Intent intent = new Intent(context, DetailArtikelFragment.class);

//                AppCompatActivity activity = (AppCompatActivity) view.getContext();
//                activity.getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.wrapper, new DetailArtikelFragment(projectModel.getEddTitle(),projectModel.getEddttl(),projectModel.getEddIsiArtikel(),projectModel.getUploadgambarartikel()))
//                .addToBackStack(null).commit();

//                Intent intent = new Intent(context, DetailArtikelActivity.class);
//                intent.putExtra("ImageHolderAc", projectModel.getUploadgambarartikel());
//                intent.putExtra("judulArtikelAc", projectModel.getEddTitle());
//                intent.putExtra("tglterbitArtikel", projectModel.getEddttl());
//                intent.putExtra("isiArtikelAc", projectModel.getEddIsiArtikel());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(intent);

            }
        });


    }

    @NotNull
    @Override
    public myartikel onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
        return new myartikel(view);
    }

    public class myartikel extends RecyclerView.ViewHolder
    {

        ImageView image1;
        TextView txtjudul9solo,txttgl6solo,isiArtikel;

        public myartikel(@NonNull @NotNull View itemView) {
            super(itemView);

            image1 = itemView.findViewById(R.id.image1);
            txtjudul9solo = itemView.findViewById(R.id.txtjudul9solo);
            txttgl6solo = itemView.findViewById(R.id.txttgl6solo);
            isiArtikel = itemView.findViewById(R.id.isiArtikel);
        }
    }
}
