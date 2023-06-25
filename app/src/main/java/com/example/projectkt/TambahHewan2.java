package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.BitmapFactory;
import java.io.ByteArrayOutputStream;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import net.glxn.qrgen.android.QRCode;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class TambahHewan2 extends AppCompatActivity {
    Button btnsimpanpemasukan;

    Bitmap bitmap;
    Bitmap bitmap2;
    String encodedImages;

    ImageView imghewan, btncamera, btnttl, btnttl2, backbtnkeuangan;
    TextView eddttl2, eddttl,tgluploadtxt, tesskn, tesjkh;
    EditText eddnamahewan, eddnamapemilik;
    EditText eddjenis, eddumur, eddhargabeli, eddbelidari, eddpristiwa;
    EditText eddketformnohewan, eddtemuan, eddtreatment, eddhasil;

    //Spinner
    Spinner eddkategori, eddstatus;

    String item;
    String[] skategori = {"Pilih", "Kambing", "Domba", "Sapi"};

    String saveCurrentDate;

    String item2;
    String[] sstatus = {"Pilih Status", "Belum Kawin", "Sudah Kawin", "Bunting"};

    String spinn,spinn2;

    ArrayList<String> spinnerList, spinnerList2;
    ArrayAdapter<String> adapter, adapter2;

    //Sampe sini

    String jkH = "";
    String sk = "";
    ProgressDialog dialog;
    private int mDate,mMonth,mYear;

    //QRcode
    final int min = 10000;
    final int max = 99999;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    FormModelHewan formModelHewan;
    private MultiFormatWriter multi = new MultiFormatWriter();
    private StorageReference storageReference;
    Calendar calendar = Calendar.getInstance();
    Locale locale = new Locale ("in","ID");
    String tglqr = new SimpleDateFormat("ddMMyyyy",locale).format(Calendar.getInstance().getTime());
    int number_rendom = new Random().nextInt((max - min) +1) + min;
    String rendomqrcode = tglqr +"-"+ String.valueOf(number_rendom);

    Uri ImageUris;


    RadioGroup radiongrupK, radiongrupH;
    RadioButton radioButtonK, radioButtonH;
    RadioButton radio_jantan, radio_betina;
    RadioButton radio_sehat, radio_sakit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_hewan2);
        imghewan = (ImageView) findViewById(R.id.imghewan);
        btncamera= findViewById(R.id.btncamera);
        btnttl = findViewById(R.id.btnttl);
        btnttl2 = findViewById(R.id.btnttl2);

        eddttl2 = findViewById(R.id.eddttl2);
        tesjkh = findViewById(R.id.tesjkh);
        tesskn = findViewById(R.id.tesskn);
        eddttl = findViewById(R.id.eddttl);

        eddnamahewan = (EditText) findViewById(R.id.eddnamahewan);
        eddnamapemilik = (EditText) findViewById(R.id.eddnamapemilik);
        eddjenis = (EditText) findViewById(R.id.eddjenis);
        eddumur = (EditText) findViewById(R.id.eddumur);
        eddhargabeli = (EditText) findViewById(R.id.eddhargabeli);
        eddbelidari = (EditText) findViewById(R.id.eddbelidari);
        eddpristiwa = (EditText) findViewById(R.id.eddpristiwa);
        eddketformnohewan = (EditText) findViewById(R.id.eddketformnohewan);
        eddtemuan = (EditText) findViewById(R.id.eddtemuan);
        eddtreatment = (EditText) findViewById(R.id.eddtreatment);
        eddhasil = (EditText) findViewById(R.id.eddhasil);
        backbtnkeuangan = (ImageView) findViewById(R.id.backbtnkeuangan);

        radiongrupK = (RadioGroup) findViewById(R.id.radiongrupK);
        radiongrupH = (RadioGroup) findViewById(R.id.radiongrupH);
        radio_betina = (RadioButton) findViewById(R.id.radio_betina);
        radio_jantan = (RadioButton) findViewById(R.id.radio_jantan);
        radio_sehat = (RadioButton) findViewById(R.id.radio_sehat);
        radio_sakit = (RadioButton) findViewById(R.id.radio_sakit);

        btnsimpanpemasukan = (Button) findViewById(R.id.btnsimpanpemasukan);

        //ini adalah perintah memilih gambar
        imghewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                UploadImage();
                Dexter.withActivity(TambahHewan2.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Browse Image"),1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
//                relativeLayout.setVisibility(View.VISIBLE);
//                uploadgambarartikel.setVisibility(View.GONE);
            }

        });

        //Spinner
        eddstatus = (Spinner) findViewById(R.id.eddstatus);
        eddkategori = (Spinner) findViewById(R.id.eddkategori);

        spinnerList = new ArrayList<>();
        spinnerList2 = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_layout,sstatus);
        adapter2 = new ArrayAdapter<String>(this, R.layout.spinner_dropdown_layout,skategori);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_layout);

        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        eddstatus.setAdapter(adapter);
        eddkategori.setAdapter(adapter2);

        //Sampe Sini

        eddhargabeli.addTextChangedListener(new TextWatcher() {

            private String setEdittext = eddhargabeli.getText().toString().trim();
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(setEdittext)){
                    eddhargabeli.removeTextChangedListener(this);
                    String replace = s.toString().replaceAll("[Rp. ]","");
                    if(!replace.isEmpty()){
                        setEdittext  = formatrupiah(Double.parseDouble(replace));
                    } else {
                        setEdittext = "";
                    }
                    eddhargabeli.setText(setEdittext);
                    eddhargabeli.setSelection(setEdittext.length());
                    eddhargabeli.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //Sampe sini

        btnttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mDate = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TambahHewan2.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int date) {
                        eddttl.setText(date+"/"+month+"/"+year);
                        eddttl.setTextColor(getResources().getColor(R.color.black));
                    }
                },mYear, mMonth, mDate);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });
        btnttl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mDate = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(TambahHewan2.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int date) {
                        eddttl2.setText(date+"/"+month+"/"+year);
                        eddttl2.setTextColor(getResources().getColor(R.color.black));
                    }
                },mYear, mMonth, mDate);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Tolong tunggu.....");
        dialog.setCancelable(false);
        dialog.setTitle("Data sedang di upload");
        dialog.setCanceledOnTouchOutside(false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Users").child(firebaseAuth.getUid()).child("HewanTernak");
        formModelHewan = new FormModelHewan();

        storageReference = FirebaseStorage.getInstance().getReference();


        btnsimpanpemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nokandang2 =  eddketformnohewan.getText().toString();
                String namahewan2 = eddnamahewan.getText().toString();
                String nameqr = rendomqrcode+namahewan2+":"+firebaseAuth.getUid();
                String statushewan2 = eddstatus.getSelectedItem().toString();
                String kategori2 = eddkategori.getSelectedItem().toString();
                String kj2 = eddjenis.getText().toString();
                String umur2 = eddumur.getText().toString();
                String peristiwa2 = eddpristiwa.getText().toString();
                String status = eddstatus.getSelectedItem().toString();
//                String kate = eddstatus.getSelectedItem().toString();

//                Bitmap qrBitmap = QRCode.from(rendomqrcode).withSize(500, 500).withColor(Color.BLACK, Color.WHITE).bitmap();
////                imgqrcode.setImageBitmap(myBitmap);
//                ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                qrBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//                byte[] data = baos.toByteArray();
//
//
//                StorageReference qrCodeRef = storageReference.child("qrcodes").child(nameqr);
//                UploadTask uploadTask = qrCodeRef.putBytes(data);
//                uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        Toast.makeText(TambahHewan2.this, "QR Code uploaded successfully", Toast.LENGTH_SHORT).show();
//                    }
//                }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(TambahHewan2.this, "Failed to upload QR Code", Toast.LENGTH_SHORT).show();
//                    }
//                });

                if (TextUtils.isEmpty(nokandang2) && TextUtils.isEmpty(namahewan2) && TextUtils.isEmpty(kj2) && TextUtils.isEmpty(umur2) && TextUtils.isEmpty(peristiwa2)) {
                    Toast.makeText(TambahHewan2.this, "Maaf data tidak boleh kosong !", Toast.LENGTH_SHORT).show();
                    eddketformnohewan.setError("Tolong Dilengkapi");
                    eddketformnohewan.requestFocus();
                    eddnamahewan.setError("Tolong Dilengkapi");
                    eddnamahewan.requestFocus();
                    eddjenis.setError("Tolong Dilengkapi");
                    eddjenis.requestFocus();
                    eddumur.setError("Tolong Dilengkapi");
                    eddumur.requestFocus();
                    eddpristiwa.setError("Tolong Dilengkapi");
                    eddpristiwa.requestFocus();
                } else if (imghewan.getDrawable() == null){
                    Toast.makeText(TambahHewan2.this, "Gambar wajib di isi", Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(nokandang2)){
                    eddketformnohewan.setError("Tolong Dilengkapi");
                    eddketformnohewan.requestFocus();
                } else if (TextUtils.isEmpty(namahewan2)) {
                    eddnamahewan.setError("Tolong Dilengkapi");
                    eddnamahewan.requestFocus();
                } else if (TextUtils.isEmpty(kj2)) {
                    eddjenis.setError("Tolong Dilengkapi");
                    eddjenis.requestFocus();
                }  else if (TextUtils.isEmpty(umur2)) {
                    eddumur.setError("Tolong Dilengkapi");
                    eddumur.requestFocus();
                }  else if (TextUtils.isEmpty(peristiwa2)) {
                    eddpristiwa.setError("Tolong Dilengkapi");
                    eddpristiwa.requestFocus();
                } else if (kategori2.equals("Pilih")) {
                    Toast.makeText(TambahHewan2.this, "Kategori tidak boleh kosong!!", Toast.LENGTH_SHORT).show();
                    eddkategori.requestFocus();
                } else if (statushewan2.equals("Pilih Status")){
                    Toast.makeText(TambahHewan2.this, "Status tidak boleh kosong!!", Toast.LENGTH_SHORT).show();
                    eddstatus.requestFocus();
                } else {
                    if ((radio_sehat.isChecked() || radio_sakit.isChecked()) && (radio_jantan.isChecked() || radio_betina.isChecked())){
                        int idTerpilihH = radiongrupH.getCheckedRadioButtonId();
                        radioButtonH = (RadioButton) findViewById(idTerpilihH);
                        String jkh = radioButtonH.getText().toString();

                        int idTerpilihK = radiongrupK.getCheckedRadioButtonId();
                        radioButtonK = (RadioButton) findViewById(idTerpilihK);
                        String skh = radioButtonK.getText().toString();

                        Bitmap qrBitmap = QRCode.from(nameqr).withSize(500, 500).withColor(Color.BLACK, Color.WHITE).bitmap();
//                imgqrcode.setImageBitmap(myBitmap);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        qrBitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                        byte[] data = baos.toByteArray();


                        StorageReference qrCodeRef = storageReference.child("qrcodes").child(nameqr);
                        UploadTask uploadTask = qrCodeRef.putBytes(data);
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                            }
                        });

                        inputdata(nameqr,jkh, skh);

                    }else {
                        Toast.makeText(TambahHewan2.this, "Jenis Kelamin & Status Kesehatan Wajib diisi", Toast.LENGTH_LONG).show();
                    }

                }


            }
        });

        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale ("in","ID");
        String tglupload = new SimpleDateFormat("dd/MM/yyyy",locale).format(Calendar.getInstance().getTime());
        String timeStamp2 = new SimpleDateFormat("HH:mm",locale).format(Calendar.getInstance().getTime());

        tgluploadtxt = (TextView) findViewById(R.id.tgluploadtxt);

        tgluploadtxt.setText(tglupload);
    }

    private String formatrupiah(Double number) {
        Locale localeID = new Locale("IND", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
        String formatrupiah = numberFormat.format(number);
        String[] split  = formatrupiah.split(",");
        int length = split[0].length();
        return split[0].substring(0,2)+". "+split[0].substring(2,length);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            ImageUris = data.getData();
            try {

                InputStream inputStream = getContentResolver().openInputStream(ImageUris);
                bitmap = BitmapFactory.decodeStream(inputStream);
                imghewan.setImageBitmap(bitmap);

//                encodeBitmapImage(bitmap);

            }catch (Exception ex)
            {

            }
        }



    }
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void encodeBitmapImage (Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);

        byte[] imageBytes = stream.toByteArray();
        final String nameImages = "IMG_"+ String.valueOf(System.currentTimeMillis())+".jpg";
//        encodedImages = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users");
        StorageReference storage = FirebaseStorage.getInstance().getReference().child("Media").child("ImageHewan").child(nameImages);
        storage.putBytes(imageBytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        String urri = uri.toString();
                        String uid = firebaseAuth.getUid();
                        String namahewan4 = eddnamahewan.getText().toString();
                        String nameqr = rendomqrcode+namahewan4+":"+firebaseAuth.getUid();



//                        HashMap HewanTernak = new HashMap();
//                        HewanTernak.put("imghewan",urri);
//
//                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
//                        reference.child(uid).child("HewanTernak").child(rendomqrcode+namahewan4+":"+firebaseAuth.getUid()).setValue(HewanTernak).addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NotNull Task<Void> task) {
//
//                            }
//                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NotNull Exception e) {
                        dialog.dismiss();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NotNull Exception e) {
                dialog.dismiss();
            }
        });

    }
    private void inputdata(String nameqr, String jkh, String skh) {
        dialog.show();

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] imageBytes = stream.toByteArray();
        final String nameImages = "IMG_"+ String.valueOf(System.currentTimeMillis())+"."+ ".jpg";
//        encodedImages = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users");
        StorageReference storage = FirebaseStorage.getInstance().getReference().child("Media").child("ImageHewan").child(nameImages);
        storage.putBytes(imageBytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        String urri = uri.toString();
                        String uid = firebaseAuth.getUid();
                        String namahewan4 = eddnamahewan.getText().toString();
                        String nameqr = rendomqrcode+namahewan4+":"+firebaseAuth.getUid()+".png";
                        String nameqr2 = rendomqrcode+namahewan4+":"+firebaseAuth.getUid();


                        String user = firebaseAuth.getUid().toString();
                        String nokandang =  eddketformnohewan.getText().toString();
                        String namahewan = eddnamahewan.getText().toString();
                        String namapemilik = eddnamapemilik.getText().toString();
//        String jk = eddjeniskelamin.getText().toString();
                        String statushewan = eddstatus.getSelectedItem().toString();
                        String kategori = eddkategori.getSelectedItem().toString();
                        String kj = eddjenis.getText().toString();
                        String tanggallahir = eddttl.getText().toString();
                        String tanggalbeli = eddttl2.getText().toString();
                        String umur = eddumur.getText().toString();
                        String hargabeli = eddhargabeli.getText().toString();
                        String belidari = eddbelidari.getText().toString();
                        String peristiwa = eddpristiwa.getText().toString();
                        String txtupload = tgluploadtxt.getText().toString();

                        //radiobuttonH
//                        int idTerpilihH = radiongrupH.getCheckedRadioButtonId();
//                        radioButtonH = (RadioButton) findViewById(idTerpilihH);
//                        String jkh = radioButtonH.getText().toString();


//                        radiobuttonK
//                        int idTerpilihK = radiongrupK.getCheckedRadioButtonId();
//                        radioButtonK = (RadioButton) findViewById(idTerpilihK);
//                        String skh = radioButtonK.getText().toString();

                        String temuan = eddtemuan.getText().toString();
                        String treatment = eddtreatment.getText().toString();
                        String hasil = eddhasil.getText().toString();

                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("qrcode",nameqr);
                        hashMap.put("nokandang",nokandang);
                        hashMap.put("namahewan",namahewan);
                        hashMap.put("namapemilik",namapemilik);
                        hashMap.put("jk",jkh); //add empty, will do leter
                        hashMap.put("status", statushewan); // possible value are user, admin: will make admin manually in firebase realtime database by changing this value
                        hashMap.put("kategori", kategori);
                        hashMap.put("klasifikasijenis", kj);
                        hashMap.put("tgllahir", tanggallahir);
                        hashMap.put("tglbeli", tanggalbeli);
                        hashMap.put("umur", umur);
                        hashMap.put("hargabeli", hargabeli);
                        hashMap.put("belidari", belidari);
                        hashMap.put("peristiwa", peristiwa);
                        hashMap.put("statuskesehatan", skh);
                        hashMap.put("temuan", temuan);
                        hashMap.put("treatment", treatment);
                        hashMap.put("hasil", hasil);
                        hashMap.put("imghewan", urri);
                        hashMap.put("tglupload", txtupload);

                        databaseReference.child(nameqr2).setValue(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                eddtemuan.setText("");
                                eddtreatment.setText("");
                                eddhasil.setText("");
                                eddnamahewan.setText("");
                                eddstatus.getSelectedItem();
                                eddjenis.setText("");
                                eddttl.setText("");
                                eddttl2.setText("");
                                eddumur.setText("");
                                eddhargabeli.setText("");
                                eddbelidari.setText("");
                                eddpristiwa.setText("");
//                            imghewan.setImageResource(0);
                            Intent intent = new Intent (TambahHewan2.this, DaftarHewanActivity.class);
                            startActivity(intent);

                                dialog.dismiss();
                                Toast.makeText(TambahHewan2.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NotNull Exception e) {
                        dialog.dismiss();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NotNull Exception e) {
                dialog.dismiss();
            }
        });

//        databaseReference.push().setValue(new FormModelHewan(rendomqrcode, nokandang, namahewan,namapemilik, jkh,statushewan, kategori, kj, tanggallahir, tanggalbeli, umur, hargabeli, belidari, peristiwa, skh, temuan, treatment, hasil, encodedImages, txtupload))
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NotNull Task<Void> task) {
//                        if (task.isSuccessful())
//                        {
//
//                            eddtemuan.setText("");
//                            eddtreatment.setText("");
//                            eddhasil.setText("");
//                            eddnamahewan.setText("");
//                            eddstatus.getSelectedItem();
//                            eddjenis.setText("");
//                            eddttl.setText("");
//                            eddttl2.setText("");
//                            eddumur.setText("");
//                            eddhargabeli.setText("");
//                            eddbelidari.setText("");
//                            eddpristiwa.setText("");
//                            imghewan.setImageResource(0);
//                            Intent intent = new Intent (TambahHewan2.this, DaftarHewanActivity.class);
//                            startActivity(intent);
//
//                            dialog.dismiss();
//                            Toast.makeText(TambahHewan2.this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
//                        }
//                        else
//                        {
//
//                            dialog.dismiss();
//                            String message = task.getException().toString();
//                            Toast.makeText(TambahHewan2.this, "Error : " + message, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });


    }
//    private void inputdata() {
//
//        dialog.show();
//        String user = firebaseAuth.getUid().toString();
//        String nokandang =  eddketformnohewan.getText().toString();
//        String namahewan = eddnamahewan.getText().toString();
//        String namapemilik = eddnamapemilik.getText().toString();
////        String jk = eddjeniskelamin.getText().toString();
//        String statushewan = eddstatus.getSelectedItem().toString();
//        String kategori = eddkategori.getSelectedItem().toString();
//        String kj = eddjenis.getText().toString();
//        String tanggallahir = eddttl.getText().toString();
//        String tanggalbeli = eddttl2.getText().toString();
//        String umur = eddumur.getText().toString();
//        String hargabeli = eddhargabeli.getText().toString();
//        String belidari = eddbelidari.getText().toString();
//        String peristiwa = eddpristiwa.getText().toString();
//        String txtupload = tgluploadtxt.getText().toString();
//
//        //radiobuttonH
//        int idTerpilihH = radiongrupH.getCheckedRadioButtonId();
//        radioButtonH = (RadioButton) findViewById(idTerpilihH);
//        String jkh = radioButtonH.getText().toString();
//
//        //radiobuttonK
//        int idTerpilihK = radiongrupK.getCheckedRadioButtonId();
//        radioButtonK = (RadioButton) findViewById(idTerpilihK);
//        String skh = radioButtonK.getText().toString();
//
//        String temuan = eddtemuan.getText().toString();
//        String treatment = eddtreatment.getText().toString();
//        String hasil = eddhasil.getText().toString();
//        String nomor = eddketformpemasukan.getText().toString();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                dialog.dismiss();
//                Toast.makeText(TambahHewan2.this, "Berhasil", Toast.LENGTH_LONG).show();
//                eddketformnohewan.setText("");
//                eddtemuan.setText("");
//                eddtreatment.setText("");
//                eddhasil.setText("");
//                eddnamahewan.setText("");
//                eddstatus.getSelectedItem();
//                eddjenis.setText("");
//                eddttl.setText("");
//                eddttl2.setText("");
//                eddumur.setText("");
//                eddhargabeli.setText("");
//                eddbelidari.setText("");
//                eddpristiwa.setText("");
//                eddketformpemasukan.setText("");
//                imghewan.setImageResource(0);
//                Intent intent = new Intent (TambahHewan2.this, DaftarHewanActivity.class);
//                startActivity(intent);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                textView.setText("That didn't work!");
//                dialog.dismiss();
//                Toast.makeText(TambahHewan2.this, "gagal", Toast.LENGTH_LONG).show();
//            }
//        })
//        {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> params = new HashMap<String,String>();
//                params.put("uid", user);
//                params.put("nokandang", nokandang);
//                params.put("namahewan", namahewan);
//                params.put("namapemilik", namapemilik);
//                params.put("jk", jkh);
//                params.put("statushewan", statushewan);
//                params.put("kategori", kategori);
//                params.put("kj", kj);
//                params.put("tanggallahir", tanggallahir);
//                params.put("tanggalbeli", tanggalbeli);
//                params.put("umur", umur);
//                params.put("hargabeli", hargabeli);
//                params.put("belidari", belidari);
//                params.put("peristiwa", peristiwa);
//                params.put("statuskesehatan", skh);
//                params.put("temuan", temuan);
//                params.put("treatment", treatment);
//                params.put("hasil", hasil);
//                params.put("nomor", nomor);
//                params.put("imaghewan", encodedImages);
//                params.put("tglupload", txtupload);
//                return params;
//            }
//        };
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//    }
}