package com.example.projectkt;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
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
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class FormHewanActivity extends AppCompatActivity {


    public static final String url = "https://projectkawanternak.000webhostapp.com/upload.php";
    private int mDate,mMonth,mYear;
//    private static final int GalleryPick = 1;
    Button btnsimpanpemasukan;

    Bitmap bitmap;
    String encodedImages;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    ImageView imghewan, btncamera, btnttl, btnttl2;
    TextView  eddttl2, eddttl,tgluploadtxt;
    EditText eddnamahewan, eddketformpemasukan, eddnamapemilik;
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



    RadioGroup radiongrupK, radiongrupH;
    RadioButton radioButtonK, radioButtonH;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form_hewan);

        imghewan = (ImageView) findViewById(R.id.imghewan);
        btncamera= findViewById(R.id.btncamera);
        btnttl = findViewById(R.id.btnttl);
        btnttl2 = findViewById(R.id.btnttl2);

        eddketformpemasukan = (EditText) findViewById(R.id.eddketformpemasukan);
        eddttl2 = findViewById(R.id.eddttl2);
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

        radiongrupK = (RadioGroup) findViewById(R.id.radiongrupK);
        radiongrupH = (RadioGroup) findViewById(R.id.radiongrupH);

        btnsimpanpemasukan = (Button) findViewById(R.id.btnsimpanpemasukan);

        //ini adalah perintah memilih gambar
        imghewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                UploadImage();
                Dexter.withActivity(FormHewanActivity.this)
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(FormHewanActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(FormHewanActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
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

        btnsimpanpemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomor = eddketformpemasukan.getText().toString();
                String nokandang2 =  eddketformnohewan.getText().toString();
                String namahewan2 = eddnamahewan.getText().toString();
                String statushewan2 = eddstatus.getSelectedItem().toString();
                String kategori2 = eddkategori.getSelectedItem().toString();
                String kj2 = eddjenis.getText().toString();
                String umur2 = eddumur.getText().toString();
                String peristiwa2 = eddpristiwa.getText().toString();

                if (TextUtils.isEmpty(nokandang2) && TextUtils.isEmpty(nomor) && TextUtils.isEmpty(namahewan2) && TextUtils.isEmpty(kj2) && TextUtils.isEmpty(umur2) && TextUtils.isEmpty(peristiwa2)) {
                    Toast.makeText(FormHewanActivity.this, "Maaf data tidak boleh kosong !", Toast.LENGTH_SHORT).show();
                    eddketformnohewan.setError("Tolong Dilengkapi");
                    eddketformnohewan.requestFocus();
                    eddketformpemasukan.setError("Tolong Dilengkapi");
                    eddketformpemasukan.requestFocus();
                    eddnamahewan.setError("Tolong Dilengkapi");
                    eddnamahewan.requestFocus();
                    eddjenis.setError("Tolong Dilengkapi");
                    eddjenis.requestFocus();
                    eddumur.setError("Tolong Dilengkapi");
                    eddumur.requestFocus();
                    eddpristiwa.setError("Tolong Dilengkapi");
                    eddpristiwa.requestFocus();
                }else if(TextUtils.isEmpty(nokandang2)){
                    eddketformnohewan.setError("Tolong Dilengkapi");
                    eddketformnohewan.requestFocus();
                } else if (TextUtils.isEmpty(nomor)) {
                    eddketformpemasukan.setError("Tolong Dilengkapi");
                    eddketformpemasukan.requestFocus();
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
                } else {
                    inputdata();
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

//    private void validasidata() {
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
//        String nomor = eddketformpemasukan.getText().toString();
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
//        if(TextUtils.isEmpty(nokandang)){
//            eddketformnohewan.setError("Tolong Dilengkapi");
//            eddketformnohewan.requestFocus();
//        } else if (statushewan == "Pilih Status") {
//            eddstatus.requestFocus();
//        } else if (kategori == "Pilih") {
//            eddkategori.requestFocus();
//        } else if (TextUtils.isEmpty(umur)) {
//            eddumur.setError("Tolong Dilengkapi");
//            eddumur.requestFocus();
//        } else if (TextUtils.isEmpty(nomor)) {
//            eddketformpemasukan.setError("Tolong Dilengkapi");
//            eddketformpemasukan.requestFocus();
//        } else {
//
//        }
//    }

    private String formatrupiah(Double number) {
        Locale localeID = new Locale("IND", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(localeID);
        String formatrupiah = numberFormat.format(number);
        String[] split  = formatrupiah.split(",");
        int length = split[0].length();
        return split[0].substring(0,2)+". "+split[0].substring(2,length);
    }
//    private void UploadImage() {
////        Intent galleryIntent = new Intent();
////        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
////        galleryIntent.setType("image/*");
////        startActivityForResult(galleryIntent, GalleryPick);
//
//
//    }
    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==1&&resultCode==RESULT_OK){
                Uri ImageUris = data.getData();
                try {

                    InputStream inputStream = getContentResolver().openInputStream(ImageUris);
                    bitmap = BitmapFactory.decodeStream(inputStream);
                    imghewan.setImageBitmap(bitmap);

                    encodeBitmapImage(bitmap);

                }catch (Exception ex)
                {

                }
            }

    }

    private void encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImages = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);


    }

    private void inputdata() {

        dialog.show();
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
        int idTerpilihH = radiongrupH.getCheckedRadioButtonId();
        radioButtonH = (RadioButton) findViewById(idTerpilihH);
        String jkh = radioButtonH.getText().toString();

        //radiobuttonK
        int idTerpilihK = radiongrupK.getCheckedRadioButtonId();
        radioButtonK = (RadioButton) findViewById(idTerpilihK);
        String skh = radioButtonK.getText().toString();

        String temuan = eddtemuan.getText().toString();
        String treatment = eddtreatment.getText().toString();
        String hasil = eddhasil.getText().toString();
        String nomor = eddketformpemasukan.getText().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        dialog.dismiss();
                        Toast.makeText(FormHewanActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
                        eddketformnohewan.setText("");
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
                        eddketformpemasukan.setText("");
                        imghewan.setImageResource(0);
                        Intent intent = new Intent (FormHewanActivity.this, DaftarHewanActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                textView.setText("That didn't work!");
                dialog.dismiss();
                Toast.makeText(FormHewanActivity.this, "gagal", Toast.LENGTH_LONG).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<String,String>();
                params.put("uid", user);
                params.put("nokandang", nokandang);
                params.put("namahewan", namahewan);
                params.put("namapemilik", namapemilik);
                params.put("jk", jkh);
                params.put("statushewan", statushewan);
                params.put("kategori", kategori);
                params.put("kj", kj);
                params.put("tanggallahir", tanggallahir);
                params.put("tanggalbeli", tanggalbeli);
                params.put("umur", umur);
                params.put("hargabeli", hargabeli);
                params.put("belidari", belidari);
                params.put("peristiwa", peristiwa);
                params.put("statuskesehatan", skh);
                params.put("temuan", temuan);
                params.put("treatment", treatment);
                params.put("hasil", hasil);
                params.put("nomor", nomor);
                params.put("imaghewan", encodedImages);
                params.put("tglupload", txtupload);
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}