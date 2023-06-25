package com.example.projectkt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectkt.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        binding =ActivityMainBinding.inflate(getLayoutInflater());
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        binding.btnloginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, OnboargingActivity.class));
                finish();
            }
        });
        binding.btndaftarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String test = binding.txtlewati.getText().toString();

                if(test.equals("Lewati")){
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    savePrefsData();
                    finish();

                }
            }
        });

        if (restorePrefData()){

            Intent mainActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(mainActivity);
            finish();

        }

    }

    private boolean restorePrefData(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;

    }
    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }
}