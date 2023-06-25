package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.ClipData;

import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class ViewProfileStrangers extends AppCompatActivity {
    private ClipboardManager myClipboard;
    private ClipData myClip;
    ImageView imgstrangers, copyy, btnbackk;
    TextView txtuserprof2, txteamil2,txtnohp2, txtgender2, isiAlamat2;

    String imgChat22,userChat22,emaill,address2,jk,hp;

    private DatabaseReference imgchatRef2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile_strangers);

        imgchatRef2 = FirebaseDatabase.getInstance().getReference().child("Users");

        txtuserprof2 = (TextView) findViewById(R.id.txtuserprof2);
        txteamil2 = (TextView) findViewById(R.id.txteamil2);
        txtnohp2 = (TextView) findViewById(R.id.txtnohp2);
        txtgender2 = (TextView) findViewById(R.id.txtgender2);
        isiAlamat2 = (TextView) findViewById(R.id.isiAlamat2);
        imgstrangers = (ImageView) findViewById(R.id.imgstrangers);
        copyy = (ImageView) findViewById(R.id.copyy);
        btnbackk = (ImageView) findViewById(R.id.btnbackk);
        myClipboard = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);


        emaill = getIntent().getExtras().get("emailku").toString();
        userChat22 = getIntent().getExtras().get("userr").toString();
        imgChat22 = getIntent().getExtras().get("imgChat2").toString();
        hp = getIntent().getExtras().get("nohp").toString();
        jk = getIntent().getExtras().get("gender").toString();
        address2 = getIntent().getExtras().get("alamat").toString();




        txtuserprof2.setText(userChat22);
        txteamil2.setText(emaill);
        txtgender2.setText(jk);
        isiAlamat2.setText(address2);
        txtnohp2.setText(hp);
        Context context = this;
        Glide.with(context)
                .load(imgChat22)
                .into(imgstrangers);


        btnbackk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        copyy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = txtnohp2.getText().toString();
                myClip = ClipData.newPlainText("number", text);
                myClipboard.setPrimaryClip(myClip);
                Toast.makeText(getApplicationContext(), "Text Copied", Toast.LENGTH_SHORT).show();
            }
        });

//        RetrieveArtikelInfo();
    }

//    private void RetrieveArtikelInfo() {
//        imgchatRef2.child(imgChat22).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                String username = ""+snapshot.child("username").getValue();
//                String nohp = ""+snapshot.child("nohp").getValue();
//                String timestamp = ""+snapshot.child("timestamp").getValue();
//                String userType = ""+snapshot.child("userType").getValue();
//                String profileimage = ""+snapshot.child("profileimage").getValue();
//                String uid = ""+snapshot.child("uid").getValue();
//
//                txtuserprof2.setText(nohp);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {

//            }
//        });
//    }
}