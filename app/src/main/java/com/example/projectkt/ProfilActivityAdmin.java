package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.projectkt.databinding.ActivityLoginBinding;
import com.example.projectkt.databinding.ActivityProfilAdminBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilActivityAdmin extends AppCompatActivity {

    //view binding
    private ActivityProfilAdminBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfilAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //init firebase auth
        firebaseAuth = FirebaseAuth.getInstance();
        checkUser();


    }

    private void checkUser() {
        //get current user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser==null){
            //not logged in, go to main screen
            startActivity(new Intent(this,LoginActivity.class));
            finish();
        }
        else {
            //logged in, get user info
            String email = firebaseUser.getEmail();
            String username = firebaseUser.getDisplayName();
            //set in textview of toolbar
            binding.txteamil.setText(email);
            binding.txtuserprof.setText(username);
        }
    }
}