package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HubungiKamiActivity extends AppCompatActivity {
    private static final String TAG = "PROFILE_TAG";
    private FirebaseAuth firebaseAuth;
    Button btnkirimpesanadmin;
    EditText inputpesan;
    TextView emaildari;
    ImageView backbtnhubungi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hubungi_kami);

        btnkirimpesanadmin = findViewById(R.id.btnkirimpesanadmin);
        inputpesan = findViewById(R.id.inputpesan);
        emaildari = findViewById(R.id.inputuser);
        backbtnhubungi = findViewById(R.id.backbtnhubungi);

        backbtnhubungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        firebaseAuth = FirebaseAuth.getInstance();
        inputemailotomatis();

        btnkirimpesanadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dari = emaildari.getText().toString();
                String pesan = inputpesan.getText().toString();

                if (TextUtils.isEmpty(pesan)){
                    inputpesan.setError("Pesan tidak boleh kosong");
                    inputpesan.requestFocus();
                } else {
                    String semuapesan = "Email :"+dari+"\n"+pesan;
                    Intent kirimWa = new Intent(Intent.ACTION_SEND);
                    kirimWa.setType("text/plaint");
                    kirimWa.putExtra(Intent.EXTRA_TEXT, semuapesan);
                    kirimWa.putExtra("jid", "6282127602881"+"@s.whatsapp.net");
                    kirimWa.setPackage("com.whatsapp");

                    startActivity(kirimWa);
                }
            }
        });
    }

    private void inputemailotomatis() {
        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //get all info of user here from snapshot
                        String email = ""+snapshot.child("email").getValue();
                        String username = ""+snapshot.child("username").getValue();
                        String nohp = ""+snapshot.child("nohp").getValue();
                        String gender = ""+snapshot.child("gender").getValue();
                        String timestamp = ""+snapshot.child("timestamp").getValue();
                        String userType = ""+snapshot.child("userType").getValue();
//                        String profileimage = ""+snapshot.child("profileimage").getValue();
                        String uid = ""+snapshot.child("uid").getValue();
//                        Log.d("TAG", profileimage + " / " + uid);

                        String profileimage = snapshot.child("profileimage").getValue(String.class);

                        //format data to dd/MM/yyyy


                        //set data to ui
                        emaildari.setText(email);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
    }
}