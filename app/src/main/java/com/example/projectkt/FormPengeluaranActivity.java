package com.example.projectkt;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.fragment.app.Fragment;
        import androidx.fragment.app.FragmentManager;

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

public class FormPengeluaranActivity extends AppCompatActivity{

//    private FirebaseAuth firebaseAuth;

    EditText eddhargaformpengeluaran,eddformqty2,eddketformpengeluaran;
    TextView eddtotalformpengeluaran;
    Spinner spinner_satuan2;

    ImageView backbtnkeuangan;

    Button btnsimpanpengeluaran;

    KeuanganModel keuanganModel;

    Context context;


    String item;
    String[] satuan = {"Pilih", "Liter", "Kg", "Ekor", "Gram", "Batang"};

    String intotal, inqty, inpengeluaran,spin, saveCurrentDate,saveCurrentTime, PengeluaranRendomKey, inharga;

    DatabaseReference databaseReference;

    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    ProgressDialog dialog;

    ArrayList<String> spinList;
    ArrayAdapter<String> adapter;
    Double v1,v2,hasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pengeluaran);
        context = this;

        eddtotalformpengeluaran =  findViewById(R.id.eddtotalformpengeluaran);
        eddketformpengeluaran = findViewById(R.id.eddketformpengeluaran);
        eddformqty2 =  findViewById(R.id.eddformqty2);
        eddhargaformpengeluaran = findViewById(R.id.eddhargaformpengeluaran);
        btnsimpanpengeluaran = findViewById(R.id.btnsimpanpengeluaran);
        backbtnkeuangan = findViewById(R.id.backbtnkeuangan);

        spinner_satuan2 = findViewById(R.id.spinner_satuan2);
//        spinner_satuan.setOnItemSelectedListener(this);

        spinList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_layout,satuan);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        spinner_satuan2.setAdapter(adapter);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!eddformqty2.getText().toString().equals("") && !eddhargaformpengeluaran.getText().toString().equals("")){
                    int num1 = Integer.parseInt(eddformqty2.getText().toString());
                    int num2 = Integer.parseInt(eddhargaformpengeluaran.getText().toString());
                    eddtotalformpengeluaran.setText(String.valueOf(num1 * num2));
                    Double a1 = Double.parseDouble(eddtotalformpengeluaran.getText().toString());
                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("RP.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursIndonesia.setDecimalFormatSymbols(formatRp);
                    eddtotalformpengeluaran.setText(String.valueOf(kursIndonesia.format(a1)));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        Double a1 = Double.parseDouble(eddtotalformpengeluaran.getText().toString());
        DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
        DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
        formatRp.setCurrencySymbol("RP.");
        formatRp.setMonetaryDecimalSeparator(',');
        formatRp.setGroupingSeparator('.');
        kursIndonesia.setDecimalFormatSymbols(formatRp);
        eddtotalformpengeluaran.setText(String.valueOf(kursIndonesia.format(a1)));


        eddformqty2.addTextChangedListener(textWatcher);
        eddhargaformpengeluaran.addTextChangedListener(textWatcher);

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Tolong tunggu.....");
        dialog.setCancelable(false);
        dialog.setTitle("Data sedang di upload");
        dialog.setCanceledOnTouchOutside(false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan");

        keuanganModel = new KeuanganModel();

        btnsimpanpengeluaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatedata();
//                StorePemasukan();
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
        intotal = eddtotalformpengeluaran.getText().toString();
        inqty = eddformqty2.getText().toString();
        inharga = eddhargaformpengeluaran.getText().toString();
        inpengeluaran = eddketformpengeluaran.getText().toString();
        spin = spinner_satuan2.getSelectedItem().toString();


        if (TextUtils.isEmpty(inqty) && TextUtils.isEmpty(inharga) && spin == "Pilih" && TextUtils.isEmpty(inpengeluaran)) {
            Toast.makeText(context, "Data Tidak Boleh Kosong !", Toast.LENGTH_SHORT).show();
            eddformqty2.setError("Tolong dilengkapi !");
            eddformqty2.requestFocus();
            eddhargaformpengeluaran.setError("Tolong dilengkapi !");
            eddhargaformpengeluaran.requestFocus();
            eddketformpengeluaran.setError("Tolong dilengkapi !");
            eddketformpengeluaran.requestFocus();
            spinner_satuan2.requestFocus();
        }else if (TextUtils.isEmpty(intotal))
        {
//            eddtotalformpemasukan.setError("Tolong Dilengkapi");
//            eddtotalformpemasukan.requestFocus();
        } else if (TextUtils.isEmpty(inqty))
        {
            eddformqty2.setError("Tolong masukan jumlah !");
            eddformqty2.requestFocus();
        }else if (TextUtils.isEmpty(inharga))
        {
            eddhargaformpengeluaran.setError("Tolong masukan harga !");
            eddhargaformpengeluaran.requestFocus();
        }else if (TextUtils.isEmpty(inpengeluaran)) {
            eddketformpengeluaran.setError("Tolong masukan keterangan !");
            eddketformpengeluaran.requestFocus();
        }
        else if (spin == "Pilih") {
            Toast.makeText(context, "Tolong pilih satuan !", Toast.LENGTH_SHORT).show();
            spinner_satuan2.requestFocus();
        }
        else
        {
            StorePemasukan(item);
        }
    }

    private void StorePemasukan(String item) {

        dialog.show();

//        int jmlqty = Integer.parseInt(inqty);
//        int jmlharga = Integer.parseInt(inharga);
//        int jml = jmlqty*jmlharga;

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        PengeluaranRendomKey = saveCurrentDate + saveCurrentTime;

//        SaveArtikelFotoDatabase();


        databaseReference.push().setValue(new KeuanganModel(PengeluaranRendomKey, inpengeluaran, inqty,spin, inharga,intotal, saveCurrentDate, "Pengeluaran"))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NotNull Task<Void> task) {
                        if (task.isSuccessful())
                        {

                            eddformqty2.setText("");
                            eddhargaformpengeluaran.setText("");
                            eddtotalformpengeluaran.setText("0");
                            eddketformpengeluaran.setText("");

                                                        dialog.dismiss();
                            Toast.makeText(FormPengeluaranActivity.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                                                        dialog.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(FormPengeluaranActivity.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
        spinner_satuan2.setAdapter(adapter);

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