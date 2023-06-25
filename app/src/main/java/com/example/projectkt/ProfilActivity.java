package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.projectkt.databinding.ActivityProfilAdminBinding;
import com.example.projectkt.databinding.ActivityProfilBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfilActivity extends AppCompatActivity {

    private static final String TAG = "PROFILE_TAG";
    //view binding
    private ActivityProfilBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
//        checkUser();

        loadUserInfo();
    }

    private void loadUserInfo() {
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
                        String timestamp = ""+snapshot.child("timestamp").getValue();
                        String userType = ""+snapshot.child("userType").getValue();
                        String profileimage = ""+snapshot.child("profileimage").getValue();
                        String uid = ""+snapshot.child("uid").getValue();

                        //format data to dd/MM/yyyy


                        //set data to ui
                        binding.txtuserprof.setText(username);




                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
        }
//
//
//    private void checkUser() {
//        //get current user
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//        if(firebaseUser==null){
//            //not logged in, go to main screen
//            startActivity(new Intent(this,LoginActivity.class));
//            finish();
//        }
//        else {
//            //logged in, get user info
//            String email = firebaseUser.getEmail();
//
//            //set in textview of toolbar
//            binding.txteamil.setText(email);
//        }
//    }


}