//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.bumptech.glide.Glide;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.squareup.picasso.Picasso;
//
//import org.jetbrains.annotations.NotNull;
//
//public class DetailArtikelActivity extends AppCompatActivity {
//
//    private ImageView ImageHolderAc;
//    private TextView isiArtikelAc,tglterbitArtikel,judulArtikel;
//    private String artikelID = "";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_artikel);
//
//        isiArtikelAc = (TextView) findViewById(R.id.isiArtikel);
//        ImageHolderAc = (ImageView) findViewById(R.id.ImageHolder);
//        tglterbitArtikel = (TextView) findViewById(R.id.tglterbitArtikel);
//        judulArtikel = (TextView) findViewById(R.id.judulArtikel);
//
//        artikelID = getIntent().getStringExtra("aid");
//        getArtikelDetail(artikelID);
//
//
//    }
//
//    private void getArtikelDetail(String artikelID)
//    {
//        DatabaseReference artikelRef = FirebaseDatabase.getInstance().getReference().child("Artikel");
//
//        artikelRef.child(artikelID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
////                if (snapshot.exists()) {
////                    ProjectModel projectModel = snapshot.getValue(ProjectModel.class);
////                    judulArtikel.setText(projectModel.getEddTitle());
////                }
////                if (snapshot.exists())
////                {
////                    ProjectModel projectModel = snapshot.getValue(ProjectModel.class);
////                    judulArtikelAc.setText(projectModel.getEddTitle());
////                    isiArtikelAc.setText(projectModel.getEddIsiArtikel());
////                    tglterbitArtikel.setText(projectModel.getEddttl());
////                }
//                if (snapshot.exists())
//                {
//
//                    ProjectModel model = snapshot.getValue(ProjectModel.class);
//                    judulArtikel.setText(model.getEddTitle());
//                    isiArtikelAc.setText(model.getEddIsiArtikel());
//                    tglterbitArtikel.setText(model.getEddttl());
//                    Glide.with(ImageHolderAc.getContext())
//                        .load(model.getUploadgambarartikel())
//                        .centerCrop()
//                        .into(ImageHolderAc);
//                }
//
////                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
////                    ProjectModel projectModel = snapshot.getValue(ProjectModel.class);
////
////                }
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }
//}


//BARU2

package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

public class DetailArtikelActivity extends AppCompatActivity {

    private ImageView ImageHolderAc, backbtnkeuangan;
    private TextView isiArtikelAc,tglterbitArtikel,judulArtikel;
    private String artikelID;

    private DatabaseReference ArtikelRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_artikel);

        ArtikelRef = FirebaseDatabase.getInstance().getReference().child("Artikel");

        isiArtikelAc = (TextView) findViewById(R.id.isiArtikelAc);
        ImageHolderAc = (ImageView) findViewById(R.id.ImageHolderAc);
        backbtnkeuangan = (ImageView) findViewById(R.id.backbtnkeuangan);
        tglterbitArtikel = (TextView) findViewById(R.id.tglterbitArtikel);
        judulArtikel = (TextView) findViewById(R.id.judulArtikelAc);


        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        artikelID = getIntent().getExtras().get("clickArtikelId").toString();

//        Toast.makeText(this, "Artikel ID: " + artikelID,Toast.LENGTH_SHORT).show();


        RetrieveArtikelInfo();
    }

    private void RetrieveArtikelInfo()
    {
        ArtikelRef.child(artikelID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if ((snapshot.exists()) && (snapshot.hasChild("uploadgambarartikel")))
                {

                    String artikelImage = snapshot.child("uploadgambarartikel").getValue().toString();
                    String artikelTitle = snapshot.child("eddTitle").getValue().toString();
                    String artikelTtl = snapshot.child("eddttl").getValue().toString();
                    String artikelIsi = snapshot.child("eddIsiArtikel").getValue().toString().replace("_b","\n");


                    Picasso.get().load(artikelImage).placeholder(R.drawable.kambingmakan).placeholder(R.drawable.load1).into(ImageHolderAc);
                    judulArtikel.setText(artikelTitle);
                    tglterbitArtikel.setText(artikelTtl);
                    isiArtikelAc.setText(artikelIsi);
                }
                else
                    {
                        String artikelTitle = snapshot.child("eddTitle").getValue().toString();
                        String artikelTtl = snapshot.child("eddttl").getValue().toString();
                        String artikelIsi = snapshot.child("eddIsiArtikel").getValue().toString();

                        judulArtikel.setText(artikelTitle);
                        tglterbitArtikel.setText(artikelTtl);
                        isiArtikelAc.setText(artikelIsi);
                    }
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }


}