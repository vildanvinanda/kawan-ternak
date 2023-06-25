//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ProgressBar;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.Continuation;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.HashMap;
//
//public class FormPemasukanActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
//
//    private FirebaseAuth firebaseAuth;
//
//    EditText eddketformpemasukan,eddformqty,eddhargaformpemasukan;
//    TextView eddtotalformpemasukan;
//    Spinner spinner_satuan;
//
//    Button btnsimpanpemasukan;
//
//    KeuanganModel keuanganModel;
//
//    Context context;
//
//    String item;
//    String[] satuan = {"Pilih Satuan", "Liter", "Kg", "Ekor", "Gram"};
//
//    String intotal, inqty, inpemasukan,spin, saveCurrentDate,saveCurrentTime, PemasukanRendomKey, inharga;
//
//    DatabaseReference databaseReference;
//
//    FirebaseUser firebaseUser;
//
//    ProgressDialog dialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form_pemasukan);
//        context = this;
//
//        eddtotalformpemasukan = findViewById(R.id.eddtotalformpemasukan);
//        eddketformpemasukan = findViewById(R.id.eddketformpemasukan);
//        eddformqty = findViewById(R.id.eddformqty);
//        eddhargaformpemasukan = findViewById(R.id.eddhargaformpemasukan);
//        btnsimpanpemasukan = findViewById(R.id.btnsimpanpemasukan);
//
//        spinner_satuan = findViewById(R.id.spinner_satuan);
//        spinner_satuan.setOnItemSelectedListener(this);
//
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Tolong tunggu.....");
//        dialog.setCancelable(false);
//        dialog.setTitle("Data sedang di upload");
//        dialog.setCanceledOnTouchOutside(false);
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        databaseReference = database.getReference().child("Users").child("Keuangan");
//
//        keuanganModel = new KeuanganModel();
//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,satuan);
//        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//
//        spinner_satuan.setAdapter(arrayAdapter);
//
//        btnsimpanpemasukan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validatedata();
//            }
//        });
//
//    }
//
//    private void validatedata() {
////        intotal = eddtotalformpemasukan.getText().toString();
//        inqty = eddformqty.getText().toString();
//        inharga = eddhargaformpemasukan.getText().toString();
//        inpemasukan = eddketformpemasukan.getText().toString();
////        spin = spinner_satuan.getSelectedItem().toString();
//
//        if (TextUtils.isEmpty(intotal))
//        {
////            eddtotalformpemasukan.setError("Tolong Dilengkapi");
////            eddtotalformpemasukan.requestFocus();
//        } else if (TextUtils.isEmpty(inqty))
//        {
//            eddformqty.setError("Tolong Dilengkapi");
//            eddformqty.requestFocus();
//        }else if (TextUtils.isEmpty(inharga))
//        {
//            eddhargaformpemasukan.setError("Tolong Dilengkapi");
//            eddhargaformpemasukan.requestFocus();
//        }else if (TextUtils.isEmpty(inpemasukan)) {
//            eddketformpemasukan.setError("Tolong Dilengkapi");
//            eddketformpemasukan.requestFocus();
//        }
////        else if (spin == "Pilih Satuan") {
////            eddketformpemasukan.setError("Tolong Dilengkapi");
////            eddketformpemasukan.requestFocus();
////        }
//        else
//        {
//            StorePemasukan(item);
//        }
//    }
//
//    private void StorePemasukan(String item) {
//
//
//        if (item == "Pilih Satuan"){
//            Toast.makeText(context, "Tolong masukan satuan", Toast.LENGTH_SHORT).show();
//        } else {
//            keuanganModel.setSatuanp(item);
//
//            dialog.show();
//
//            Calendar calendar = Calendar.getInstance();
//
//            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
//            saveCurrentDate = currentDate.format(calendar.getTime());
//
//            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
//            saveCurrentTime = currentTime.format(calendar.getTime());
//
//            PemasukanRendomKey = saveCurrentDate + saveCurrentTime;
//
//            SaveArtikelFotoDatabase();
//        }
//    }
//
//    private void SaveArtikelFotoDatabase() {
//
//        HashMap<String,Object> pemasukanMap = new HashMap<>();
//        pemasukanMap.put("mid", PemasukanRendomKey);
//        pemasukanMap.put("pemasukan", inpemasukan);
//        pemasukanMap.put("qtyp", inqty);
//        pemasukanMap.put("totalp", "");
//        pemasukanMap.put("hargap", inharga);
//        pemasukanMap.put("satuanp", "");
//
//        databaseReference.child(PemasukanRendomKey).updateChildren(pemasukanMap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NotNull Task<Void> task) {
//                        if (task.isSuccessful())
//                        {
//
//                            Intent intent = new Intent(FormPemasukanActivity.this, KeuanganFragment.class);
//                            startActivity(intent);
//
//                            dialog.dismiss();
//                            Toast.makeText(FormPemasukanActivity.this, "Artikel Telah Dibuat", Toast.LENGTH_SHORT).show();
//
//                        }
//                        else
//                        {
//                            dialog.dismiss();
//                            String message = task.getException().toString();
//                            Toast.makeText(FormPemasukanActivity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
//}

package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class FormPemasukanActivity extends AppCompatActivity{

//    private FirebaseAuth firebaseAuth;

    EditText eddketformpemasukan,eddformqty,eddhargaformpemasukan;
    TextView eddtotalformpemasukan;
    Spinner spinner_satuan;

    ImageView backbtnkeuangan;

    Button btnsimpanpemasukan;

    KeuanganModel keuanganModel;

    Context context;
    private KeuanganFragment keuanganFragment = new KeuanganFragment();

    String item;
    String[] satuan = {"Pilih", "Liter", "Kg", "Ekor", "Gram", "Batang"};

    String intotal, inqty, inpemasukan,spin, saveCurrentDate,saveCurrentTime, PemasukanRendomKey, inharga;

    DatabaseReference databaseReference;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    ProgressDialog dialog;

    ArrayList<String>spinnerList;
    ArrayAdapter<String>adapter;
    Double v1,v2,hasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pemasukan);
        context = this;

        eddtotalformpemasukan =  findViewById(R.id.eddtotalformpemasukan);
        eddketformpemasukan = findViewById(R.id.eddketformpemasukan);
        eddformqty =  findViewById(R.id.eddformqty);
        eddhargaformpemasukan = findViewById(R.id.eddhargaformpemasukan);
        btnsimpanpemasukan = findViewById(R.id.btnsimpanpemasukan);
        backbtnkeuangan = findViewById(R.id.backbtnkeuangan);

        spinner_satuan = findViewById(R.id.spinner_satuan);
//        spinner_satuan.setOnItemSelectedListener(this);

        spinnerList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_layout,satuan);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);


        spinner_satuan.setAdapter(adapter);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!eddformqty.getText().toString().equals("") && !eddhargaformpemasukan.getText().toString().equals("")){
                    int num1 = Integer.parseInt(eddformqty.getText().toString());
                    int num2 = Integer.parseInt(eddhargaformpemasukan.getText().toString());
                    eddtotalformpemasukan.setText(String.valueOf(num1 * num2));
                    Double a1 = Double.parseDouble(eddtotalformpemasukan.getText().toString());
                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("RP.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursIndonesia.setDecimalFormatSymbols(formatRp);
                    eddtotalformpemasukan.setText(String.valueOf(kursIndonesia.format(a1)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        eddformqty.addTextChangedListener(textWatcher);
        eddhargaformpemasukan.addTextChangedListener(textWatcher);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Tolong tunggu.....");
        dialog.setCancelable(false);
        dialog.setTitle("Data sedang di upload");
        dialog.setCanceledOnTouchOutside(false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan");

        keuanganModel = new KeuanganModel();

        btnsimpanpemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatedata();

            }
        });
        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    private void validatedata() {
        String intotal2 = eddtotalformpemasukan.getText().toString();
        String inqty2 = eddformqty.getText().toString();
        String inharga2 = eddhargaformpemasukan.getText().toString();
        String inpemasukan2 = eddketformpemasukan.getText().toString();
        String spin2 = spinner_satuan.getSelectedItem().toString();

        if (TextUtils.isEmpty(inqty2) && TextUtils.isEmpty(inharga2) && spin2 == "Pilih" && TextUtils.isEmpty(inpemasukan2)) {
            Toast.makeText(context, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
            eddformqty.setError("Tolong dilengkapi !");
            eddformqty.requestFocus();
            eddhargaformpemasukan.setError("Tolong dilengkapi !");
            eddhargaformpemasukan.requestFocus();
            eddketformpemasukan.setError("Tolong dilengkapi !");
            eddketformpemasukan.requestFocus();
            spinner_satuan.requestFocus();
        }else if (TextUtils.isEmpty(intotal2))
        {
//            eddtotalformpemasukan.setError("Tolong Dilengkapi");
//            eddtotalformpemasukan.requestFocus();
        } else if (TextUtils.isEmpty(inqty2))
        {
            eddformqty.setError("Tolong masukan jumlah !");
            eddformqty.requestFocus();
        }else if (TextUtils.isEmpty(inharga2))
        {
            eddhargaformpemasukan.setError("Tolong masukan harga !");
            eddhargaformpemasukan.requestFocus();
        }else if (TextUtils.isEmpty(inpemasukan2)) {
            eddketformpemasukan.setError("Tolong masukan keterangan !");
            eddketformpemasukan.requestFocus();
        }
        else if (spin2 == "Pilih") {
            Toast.makeText(context, "Tolong pilih satuan !", Toast.LENGTH_SHORT).show();
            spinner_satuan.requestFocus();
        }
        else
        {
            StorePemasukan(item);
        }
    }

    private void StorePemasukan(String item) {
        intotal = eddtotalformpemasukan.getText().toString();
        inqty = eddformqty.getText().toString();
        inharga = eddhargaformpemasukan.getText().toString();
        inpemasukan = eddketformpemasukan.getText().toString();
        spin = spinner_satuan.getSelectedItem().toString();
        dialog.show();

//        int jmlqty = Integer.parseInt(inqty);
//        int jmlharga = Integer.parseInt(inharga);
//        int jml = jmlqty*jmlharga;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        PemasukanRendomKey = saveCurrentDate + saveCurrentTime;

//        SaveArtikelFotoDatabase();


            databaseReference.push().setValue(new KeuanganModel(PemasukanRendomKey, inpemasukan, inqty,spin, inharga,intotal, saveCurrentDate, "Pemasukan"))
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NotNull Task<Void> task) {
                            if (task.isSuccessful())
                            {

                                eddformqty.setText("");
                                eddhargaformpemasukan.setText("");
                                eddtotalformpemasukan.setText("0");
                                eddketformpemasukan.setText("");

                                dialog.dismiss();
                                Toast.makeText(FormPemasukanActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {

                                dialog.dismiss();
                                String message = task.getException().toString();
                                Toast.makeText(FormPemasukanActivity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                            }
                            adapter.notifyDataSetChanged();
                        }
                    });
            spinner_satuan.setAdapter(adapter);

    }

//    private void SaveArtikelFotoDatabase() {
//
////        HashMap<String,Object> pemasukanMap = new HashMap<>();
////        pemasukanMap.put("mid", PemasukanRendomKey);
////        pemasukanMap.put("pemasukan", inpemasukan);
////        pemasukanMap.put("qtyp", "");
////        pemasukanMap.put("totalp", "");
////        pemasukanMap.put("hargap", inharga);
////        pemasukanMap.put("satuanp", "");
//
//        databaseReference.push().setValue(new KeuanganModel(PemasukanRendomKey, inpemasukan, "","", inharga,""))
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NotNull Task<Void> task) {
//                        if (task.isSuccessful())
//                        {
//
////                            Intent intent = new Intent(FormPemasukanActivity.this, keuanganFragment);
////                            startActivity(intent);
//
//                            dialog.dismiss();
//                            Toast.makeText(FormPemasukanActivity.this, "Artikel Telah Dibuat", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//                            dialog.dismiss();
//                            String message = task.getException().toString();
//                            Toast.makeText(FormPemasukanActivity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }

//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}