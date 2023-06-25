package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailEdukasiActivity extends AppCompatActivity {

    Bitmap bitmap;
    String encodedImages;

    private ImageView imgkambing,backbtnkeuangan;
    private TextView judul,tglterbit,isiedu;
    private String eduiID;

    private DatabaseReference EduRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_edukasi);

        EduRef = FirebaseDatabase.getInstance().getReference().child("Edukasi");

        isiedu = (TextView) findViewById(R.id.isiedu);
        imgkambing = (ImageView) findViewById(R.id.imgkambing);
        backbtnkeuangan = (ImageView) findViewById(R.id.backbtnkeuangan);
        tglterbit = (TextView) findViewById(R.id.tglterbit);
        judul = (TextView) findViewById(R.id.judul);

        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        eduiID = getIntent().getExtras().get("clickEduId").toString();

//        Toast.makeText(this, "Artikel ID: " + artikelID,Toast.LENGTH_SHORT).show();


        RetrieveEduInfo();
    }

    private void RetrieveEduInfo() {
        EduRef.child(eduiID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {


                String EduImage = snapshot.child("gambar").getValue().toString();
                String EduTitle = snapshot.child("judul").getValue().toString();
                String EduTtl = snapshot.child("tanggalupload").getValue().toString();
                String EduIsi = snapshot.child("isi").getValue().toString().replace("_b", "\n");


                Picasso.get().load(EduImage).placeholder(R.drawable.kambingmakan).into(imgkambing);
                judul.setText(EduTitle);
                tglterbit.setText(EduTtl);
                isiedu.setText(EduIsi);
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}