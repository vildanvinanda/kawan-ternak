package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class UpdateDataHewan extends AppCompatActivity {

    TextView update_ttl2,update_ttl;
    ImageView update_btnttl, update_imghewan, update_btnttl2;
    EditText update_nomor,update_namahewan, update_namapemilik,update_hargabeli, update_jk, update_status, update_kategori, update_kj,update_belidari,update_pristiwa, update_umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_hewan);

        
    }
}