//package com.example.projectkt;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.Manifest;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.PointF;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Base64;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.bumptech.glide.Glide;
//import com.example.projectkt.qrcode.DataModel;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//import com.squareup.picasso.Picasso;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_BD;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_HASIL;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_HB;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_ID;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_IMG;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_JK;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_KATEGORI;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_KJ;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_NAME;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_NOKANDANG;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_NPEMILIK;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_PERISTIWA;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_SK;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_STATUS;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_TB;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_TEMUAN;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_TREATMENT;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_TTL;
//import static com.example.projectkt.DaftarHewanActivity.EXTRA_UMUR;
//
//
//public class DetailHewanActivity extends AppCompatActivity {
//
//    Bitmap bitmap;
//    String encodedImages;
//    float[] lastEvent = null;
//    float d = 0f;
//    float newRot = 0f;
//    private boolean isZoomAndRotate;
//    private boolean isOutSide;
//    private static final int NONE = 0;
//    private static final int DRAG = 1;
//    private static final int ZOOM = 2;
//    private int mode = NONE;
//    private PointF start = new PointF();
//    private PointF mid = new PointF();
//    float oldDist = 1f;
//    private float xCoOrdinate, yCoOrdinate;
//
//
//    List<DataModel>dataModels;
//    TextView idDH, nameHewan, btnubahimg, btnsimpanimg;
//    EditText inputnamahewan, inputnamapemilik, inputnokandang, inputjk, inputStatus, inputKategori, inputKJenis, inputTTL, inputTB, inputEUmur, inputHB, inputBD, inputPristiwa;
//    EditText inputSK, inputTemuan, inputTreatment, inputHasil;
//    RelativeLayout kontener1, kontener2, countainerIdentitasH, countainerKesehatan, tamplategambarIMGH;
//    ImageView rowrightDH, rowrightDH2;
//    ImageView imgdetailhewan, gambarIMGH, backbtndetailfotoH, backbtnkeuangan;
//
//
//    private String namahewanq;
//    private String nokandangq, namapemiliq, inputjkq, inputstatusq, inputkategoriq, inputjenisq, inputttlq, inputtbq, inputumurq, inputhbq, inputbdq, inputpristiwaq, inputSKq, inputTemuanq, inputTreatmentq, inputHasilq;
//
//    public static final String urlii = "https://projectkawanternak.000webhostapp.com/update.php";
//    public static final String url_IMG = "https://projectkawanternak.000webhostapp.com/updateimg.php";
//    public static final String url_del = "https://projectkawanternak.000webhostapp.com/delete.php";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_hewan);
//
//        idDH = (TextView) findViewById(R.id.idDH);
//        inputnokandang = (EditText) findViewById(R.id.inputnokandang);
//        inputKategori = (EditText) findViewById(R.id.inputKategori);
//        inputnamapemilik = (EditText) findViewById(R.id.inputnamapemilik);
//        inputjk = (EditText) findViewById(R.id.inputjk);
//        inputStatus = (EditText) findViewById(R.id.inputStatus);
//        inputKJenis = (EditText) findViewById(R.id.inputKJenis);
//        inputTTL = (EditText) findViewById(R.id.inputTTL);
//        inputTB = (EditText) findViewById(R.id.inputTB);
//        inputEUmur = (EditText) findViewById(R.id.inputEUmur);
//        inputHB = (EditText) findViewById(R.id.inputHB);
//        inputBD = (EditText) findViewById(R.id.inputBD);
//        inputPristiwa = (EditText) findViewById(R.id.inputPristiwa);
//
//        inputSK = (EditText) findViewById(R.id.inputSK);
//        inputTemuan = (EditText) findViewById(R.id.inputTemuan);
//        inputTreatment = (EditText) findViewById(R.id.inputTreatment);
//        inputHasil = (EditText) findViewById(R.id.inputHasil);
//
//        kontener1 = (RelativeLayout) findViewById(R.id.kontener1);
//        kontener2 = (RelativeLayout) findViewById(R.id.kontener2);
//        countainerIdentitasH = (RelativeLayout) findViewById(R.id.countainerIdentitasH);
//        countainerKesehatan = (RelativeLayout) findViewById(R.id.countainerKesehatan);
//
//        nameHewan = (TextView) findViewById(R.id.nameHewan);
//        btnubahimg = (TextView) findViewById(R.id.btnubahimg);
//        btnsimpanimg = (TextView) findViewById(R.id.btnsimpanimg);
//        tamplategambarIMGH = (RelativeLayout) findViewById(R.id.tamplategambarIMGH);
//        backbtndetailfotoH = (ImageView) findViewById(R.id.backbtndetailfotoH);
//        gambarIMGH = (ImageView) findViewById(R.id.gambarIMGH);
//
//        rowrightDH = (ImageView) findViewById(R.id.rowrightDH);
//        rowrightDH2 = (ImageView) findViewById(R.id.rowrightDH2);
//        backbtnkeuangan = (ImageView) findViewById(R.id.backbtnkeuangan);
//
//        dataModels = new ArrayList<>();
//
//        Intent intent = getIntent();
//        String namahewan = intent.getStringExtra(EXTRA_NAME);
//        String namapemilik = intent.getStringExtra(EXTRA_NPEMILIK);
//        String imaghewan = intent.getStringExtra(EXTRA_IMG);
//        String statushewan = intent.getStringExtra(EXTRA_STATUS);
//        String jk = intent.getStringExtra(EXTRA_JK);
//        String id = intent.getStringExtra(EXTRA_ID);
//        String noK = intent.getStringExtra(EXTRA_NOKANDANG);
//        String hb = intent.getStringExtra(EXTRA_HB);
//        String ttl = intent.getStringExtra(EXTRA_TTL);
//        String kat = intent.getStringExtra(EXTRA_KATEGORI);
//        String kj = intent.getStringExtra(EXTRA_KJ);
//        String tb = intent.getStringExtra(EXTRA_TB);
//        String umur = intent.getStringExtra(EXTRA_UMUR);
//        String bd = intent.getStringExtra(EXTRA_BD);
//        String peristiwa = intent.getStringExtra(EXTRA_PERISTIWA);
//        String sk = intent.getStringExtra(EXTRA_SK);
//        String temuan = intent.getStringExtra(EXTRA_TEMUAN);
//        String treatment = intent.getStringExtra(EXTRA_TREATMENT);
//        String hasil = intent.getStringExtra(EXTRA_HASIL);
//
//        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        namahewanq = getIntent().getExtras().get("namahewan").toString();
////        nokandangq = getIntent().getExtras().get("nokandang").toString();
////        namapemiliq = getIntent().getExtras().get("namapemilik").toString();
////        inputhbq = getIntent().getExtras().get("hargabeli").toString();
////        inputjkq = getIntent().getExtras().get("jk").toString();
////        inputstatusq = getIntent().getExtras().get("statushewan").toString();
////        inputkategoriq = getIntent().getExtras().get("kategori").toString();
////        inputjenisq = getIntent().getExtras().get("kj").toString();
////        inputttlq = getIntent().getExtras().get("tanggallahir").toString();
////        inputtbq = getIntent().getExtras().get("tanggalbela").toString();
////        inputumurq = getIntent().getExtras().get("umur").toString();
////        inputhbq = getIntent().getExtras().get("hargabeli").toString();
////        inputbdq = getIntent().getExtras().get("belidari").toString();
////        inputpristiwaq = getIntent().getExtras().get("peristiwa").toString();
////        inputSKq = getIntent().getExtras().get("statuskesehatan").toString();
////        inputTemuanq = getIntent().getExtras().get("temuan").toString();
////        inputTreatmentq = getIntent().getExtras().get("treatment").toString();
////        inputHasilq = getIntent().getExtras().get("hasil").toString();
//        imgdetailhewan = (ImageView) findViewById(R.id.imgdetailhewan);
//        inputnamahewan = (EditText) findViewById(R.id.inputnamahewan);
//
//        TextView btndeletedetailhewan = findViewById(R.id.btndeletedetailhewan);
//
//        Button btnubahdetailhewan = findViewById(R.id.btnubahdetailhewan);
//
////        Picasso.get().load(imaghewan).fit().centerInside().into(imgdetailhewan);
//        Glide.with(imgdetailhewan.getContext())
//                .load(imaghewan)
//                .centerCrop()
//                .placeholder(R.drawable.usepng)
//                .into(imgdetailhewan);
//
//        imgdetailhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                tamplategambarIMGH.setVisibility(View.VISIBLE);
//                Glide.with(gambarIMGH.getContext())
//                        .load(imaghewan)
//                        .optionalFitCenter()
//                        .placeholder(R.drawable.usepng)
//                        .into(gambarIMGH);
//                backbtndetailfotoH.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        tamplategambarIMGH.setVisibility(View.GONE);
//                    }
//                });
//                btnubahimg.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        String id = idDH.getText().toString();
//
//                        Dexter.withActivity(DetailHewanActivity.this)
//                                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                                .withListener(new PermissionListener() {
//                                    @Override
//                                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//
//                                        Intent intent = new Intent(Intent.ACTION_PICK);
//                                        intent.setType("image/*");
//                                        startActivityForResult(Intent.createChooser(intent, "Browse Image"),1);
//
//                                    }
//
//                                    @Override
//                                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//                                    }
//
//                                    @Override
//                                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                                        permissionToken.continuePermissionRequest();
//                                    }
//                                }).check();
//                    }
//                });
//                btnsimpanimg.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_IMG, new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                Toast.makeText(DetailHewanActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
//                                btnsimpanimg.setVisibility(View.GONE);
//                                btnubahimg.setVisibility(View.VISIBLE);
//                            }
//                        }, new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(DetailHewanActivity.this, "gagal", Toast.LENGTH_LONG).show();
//                            }
//                        }) {
//                            @Override
//                            protected Map<String, String> getParams() throws AuthFailureError {
//                                Map<String, String> params = new HashMap<String, String>();
//                                params.put("id", id);
//                                params.put("imaghewan", encodedImages);
//                                return params;
//                            }
//                        };
//                        RequestQueue queue = Volley.newRequestQueue(DetailHewanActivity.this);
//                        queue.add(stringRequest);
//                    }
//                });
//
//            }
//        });
//
//        inputnamahewan.setText(namahewanq);
//        inputnokandang.setText(noK);
//        inputnamapemilik.setText(namapemilik);
//        inputjk.setText(jk);
//        inputStatus.setText(statushewan);
//        inputKategori.setText(kat);
//        inputKJenis.setText(kj);
//        inputTTL.setText(ttl);
//        inputTB.setText(tb);
//        inputEUmur.setText(umur);
//        inputHB.setText(hb);
//        inputBD.setText(bd);
//        inputPristiwa.setText(peristiwa);
//        inputSK.setText(sk);
//        inputTemuan.setText(temuan);
//        inputTreatment.setText(treatment);
//        inputHasil.setText(hasil);
//        idDH.setText(id);
//        btnubahdetailhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                updatedata();
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(DetailHewanActivity.this);
//                builder.setTitle("Ubah Data");
//                builder.setMessage("Anda yakin untuk memperbarui data ini?");
//                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        updatedata();
//                    }
//                });
//                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
//        });
//        btndeletedetailhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                hapusData(id);
//
//                AlertDialog.Builder builder = new AlertDialog.Builder(DetailHewanActivity.this);
//                builder.setTitle("Hapus Data");
//                builder.setMessage("Anda yakin untuk menghapus data ini?");
//                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        hapusData(id);
//                    }
//                });
//                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int i) {
//                        dialog.dismiss();
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//
//            }
//        });
//        kontener1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(countainerIdentitasH.getVisibility() == View.GONE){
//                    countainerIdentitasH.setVisibility(View.VISIBLE);
//                    rowrightDH.animate().rotation(rowrightDH.getRotation()+90).start();
//                } else {
//                    countainerIdentitasH.setVisibility(View.GONE);
//                    rowrightDH.animate().rotation(rowrightDH.getRotation()-90).start();
//                }
//            }
//        });
//        kontener2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(countainerKesehatan.getVisibility() == View.GONE){
//                    countainerKesehatan.setVisibility(View.VISIBLE);
//                    rowrightDH2.animate().rotation(rowrightDH2.getRotation()+90).start();
//                } else {
//                    countainerKesehatan.setVisibility(View.GONE);
//                    rowrightDH2.animate().rotation(rowrightDH2.getRotation()-90).start();
//                }
//            }
//        });
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1&&resultCode==RESULT_OK){
//            Uri ImageUris = data.getData();
//            try {
//
//                btnubahimg.setVisibility(View.GONE);
//                btnsimpanimg.setVisibility(View.VISIBLE);
//
//                InputStream inputStream = getContentResolver().openInputStream(ImageUris);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                gambarIMGH.setImageBitmap(bitmap);
//                encodeBitmapImage(bitmap);
//
//            }catch (Exception ex)
//            {
//
//            }
//        }
//
//    }
//
//    private void encodeBitmapImage(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
//
//        byte[] imageBytes = stream.toByteArray();
//
//        encodedImages = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//
//    }
//
//    private void updatedata() {
//        String namahewan = inputnamahewan.getText().toString();
//        String nokandang = inputnokandang.getText().toString();
//        String namapemilik = inputnamapemilik.getText().toString();
//        String jk = inputjk.getText().toString();
//        String statushewan = inputStatus.getText().toString();
//        String kategori = inputKategori.getText().toString();
//        String kj = inputKJenis.getText().toString();
//        String tanggallahir = inputTTL.getText().toString();
//        String tanggalbeli = inputTB.getText().toString();
//        String umur = inputEUmur.getText().toString();
//        String hargabeli = inputHB.getText().toString();
//        String belidari = inputBD.getText().toString();
//        String peristiwa = inputPristiwa.getText().toString();
//        String statuskesehatan = inputSK.getText().toString();
//        String temuan = inputTemuan.getText().toString();
//        String treatment = inputTreatment.getText().toString();
//        String hasil = inputHasil.getText().toString();
////        String imaghewan = imgdetailhewan.getDrawable().toString();
//        String id = idDH.getText().toString();
//        if(namahewan.isEmpty())
//        {
//            Toast.makeText(DetailHewanActivity.this, "Tolong lengkapi", Toast.LENGTH_SHORT).show();
//        } else {
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, urlii, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Toast.makeText(DetailHewanActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
////                            Intent intent = new Intent(DetailHewanActivity.this, DaftarHewanActivity.class);
////                            startActivity(intent);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                textView.setText("That didn't work!");
//                    Toast.makeText(DetailHewanActivity.this, "gagal", Toast.LENGTH_LONG).show();
//                }
//            }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<String, String>();
//                    params.put("id", id);
//                    params.put("nokandang", nokandang);
//                    params.put("namahewan", namahewan);
//                    params.put("namapemilik", namapemilik);
//                    params.put("jk", jk);
//                    params.put("statushewan", statushewan);
//                    params.put("kategori", kategori);
//                    params.put("kj", kj);
//                    params.put("tanggallahir", tanggallahir);
//                    params.put("tanggalbeli", tanggalbeli);
//                    params.put("umur", umur);
//                    params.put("hargabeli", hargabeli);
//                    params.put("belidari", belidari);
//                    params.put("peristiwa", peristiwa);
//                    params.put("setatuskesehatan", statuskesehatan);
//                    params.put("temuan", temuan);
//                    params.put("treatment", treatment);
//                    params.put("hasil", hasil);
////                    params.put("imaghewan", encodedImages);
////                    params.put("imaghewan", imaghewan);
//                    return params;
//                }
//            };
//            RequestQueue queue = Volley.newRequestQueue(DetailHewanActivity.this);
//            queue.add(stringRequest);
//        }
//    }
//
//    private void hapusData(String id) {
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url_del, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(DetailHewanActivity.this, "Data Berhasil Dihapus", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(DetailHewanActivity.this, DaftarHewanActivity.class);
//                startActivity(intent);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                textView.setText("That didn't work!");
//                Toast.makeText(DetailHewanActivity.this, "gagal", Toast.LENGTH_LONG).show();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("id", id);
////                            params.put("namapemilik", namapemilik);
////                            params.put("jk", jk);
////                            params.put("statushewan", statushewan);
////                            params.put("kategori", kategori);
////                            params.put("kj", kj);
////                            params.put("tanggallahir", tanggallahir);
////                            params.put("tanggalbeli", tanggalbeli);
////                            params.put("umur", umur);
////                            params.put("hargabeli", hargabeli);
////                            params.put("belidari", belidari);
////                            params.put("peristiwa", peristiwa);
////                            params.put("nomor", nomor);
////                            params.put("imaghewan", encodedImages);
//                return params;
//            }
//        };
//        RequestQueue queue = Volley.newRequestQueue(DetailHewanActivity.this);
//        queue.add(stringRequest);
//
//    }
////    private void hapusdata() {
////
////        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
////            @Override
////            public void onResponse(String response) {
////                Toast.makeText(DetailHewanActivity.this, "Berhasil", Toast.LENGTH_LONG).show();
////
////                Intent intent = new Intent (DetailHewanActivity.this, DaftarHewanActivity.class);
////                startActivity(intent);
////            }
////        }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
//////                textView.setText("That didn't work!");
////                Toast.makeText(DetailHewanActivity.this, "gagal", Toast.LENGTH_LONG).show();
////            }
////        })
////        {
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                Map<String,String> params = new HashMap<String,String>();
////
////                params.put("id", id);
////
////                return params;
////            }
////        };
////        RequestQueue queue = Volley.newRequestQueue(this);
////        queue.add(stringRequest);
////    }
//}


//INI FIREBASE


//INI BERJALAN DENGAN BAIK MENGGUNAKAN MYSQL
//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.Manifest;
//import android.content.DialogInterface;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.PointF;
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Base64;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.bumptech.glide.Glide;
//import com.example.projectkt.qrcode.DataModel;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//import com.squareup.picasso.Picasso;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.io.ByteArrayOutputStream;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//
//public class DetailHewanActivity extends AppCompatActivity {
//
//    Bitmap bitmap;
//    String encodedImages;
//    float[] lastEvent = null;
//    float d = 0f;
//    float newRot = 0f;
//    private boolean isZoomAndRotate;
//    private boolean isOutSide;
//    private static final int NONE = 0;
//    private static final int DRAG = 1;
//    private static final int ZOOM = 2;
//    private int mode = NONE;
//    private PointF start = new PointF();
//    private PointF mid = new PointF();
//    float oldDist = 1f;
//    private float xCoOrdinate, yCoOrdinate;
//
//    private DatabaseReference DaftarHewanRef;
//    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
//    private String HewanId;
//
//    ArrayList<FormModelHewan>formModelHewans;
//    TextView idDH, nameHewan, btnubahimg, btnsimpanimg;
//    EditText inputnamahewan, inputnamapemilik, inputnokandang, inputjk, inputStatus, inputKategori, inputKJenis, inputTTL, inputTB, inputEUmur, inputHB, inputBD, inputPristiwa;
//    EditText inputSK, inputTemuan, inputTreatment, inputHasil;
//    RelativeLayout kontener1, kontener2, countainerIdentitasH, countainerKesehatan, tamplategambarIMGH;
//    ImageView rowrightDH, rowrightDH2;
//    ImageView imgdetailhewan, gambarIMGH, backbtndetailfotoH, backbtnkeuangan;
//    Button btnubahdetailhewan;
//    TextView btndeletedetailhewan;
//
//    private String namahewanq;
//    private String nokandangq, namapemiliq, inputjkq, inputstatusq, inputkategoriq, inputjenisq, inputttlq, inputtbq, inputumurq, inputhbq, inputbdq, inputpristiwaq, inputSKq, inputTemuanq, inputTreatmentq, inputHasilq;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_hewan);
//
//        idDH = (TextView) findViewById(R.id.idDH);
//        inputnokandang = (EditText) findViewById(R.id.inputnokandang);
//        inputKategori = (EditText) findViewById(R.id.inputKategori);
//        inputnamapemilik = (EditText) findViewById(R.id.inputnamapemilik);
//        inputjk = (EditText) findViewById(R.id.inputjk);
//        inputStatus = (EditText) findViewById(R.id.inputStatus);
//        inputKJenis = (EditText) findViewById(R.id.inputKJenis);
//        inputTTL = (EditText) findViewById(R.id.inputTTL);
//        inputTB = (EditText) findViewById(R.id.inputTB);
//        inputEUmur = (EditText) findViewById(R.id.inputEUmur);
//        inputHB = (EditText) findViewById(R.id.inputHB);
//        inputBD = (EditText) findViewById(R.id.inputBD);
//        inputPristiwa = (EditText) findViewById(R.id.inputPristiwa);
//
//        inputSK = (EditText) findViewById(R.id.inputSK);
//        inputTemuan = (EditText) findViewById(R.id.inputTemuan);
//        inputTreatment = (EditText) findViewById(R.id.inputTreatment);
//        inputHasil = (EditText) findViewById(R.id.inputHasil);
//
//        kontener1 = (RelativeLayout) findViewById(R.id.kontener1);
//        kontener2 = (RelativeLayout) findViewById(R.id.kontener2);
//        countainerIdentitasH = (RelativeLayout) findViewById(R.id.countainerIdentitasH);
//        countainerKesehatan = (RelativeLayout) findViewById(R.id.countainerKesehatan);
//
//        nameHewan = (TextView) findViewById(R.id.nameHewan);
//        btnubahimg = (TextView) findViewById(R.id.btnubahimg);
//        btnsimpanimg = (TextView) findViewById(R.id.btnsimpanimg);
//        tamplategambarIMGH = (RelativeLayout) findViewById(R.id.tamplategambarIMGH);
//        backbtndetailfotoH = (ImageView) findViewById(R.id.backbtndetailfotoH);
//        gambarIMGH = (ImageView) findViewById(R.id.gambarIMGH);
//
//        rowrightDH = (ImageView) findViewById(R.id.rowrightDH);
//        rowrightDH2 = (ImageView) findViewById(R.id.rowrightDH2);
//        backbtnkeuangan = (ImageView) findViewById(R.id.backbtnkeuangan);
//
//        imgdetailhewan = (ImageView) findViewById(R.id.imgdetailhewan);
//        inputnamahewan = (EditText) findViewById(R.id.inputnamahewan);
//
//        btndeletedetailhewan = findViewById(R.id.btndeletedetailhewan);
//        btnubahdetailhewan = findViewById(R.id.btnubahdetailhewan);
//
//        formModelHewans = new ArrayList<>();
//
//        DaftarHewanRef = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("HewanTernak");
//        HewanId = getIntent().getExtras().get("clickHewanId").toString();
//        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        RetrieveDataHewanInfo();
//    }
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode==1&&resultCode==RESULT_OK){
//            Uri ImageUris = data.getData();
//            try {
//
//                btnubahimg.setVisibility(View.GONE);
//                btnsimpanimg.setVisibility(View.VISIBLE);
//
//                InputStream inputStream = getContentResolver().openInputStream(ImageUris);
//                bitmap = BitmapFactory.decodeStream(inputStream);
//                gambarIMGH.setImageBitmap(bitmap);
////                encodeBitmapImage(bitmap);
//
//            }catch (Exception ex)
//            {
//
//            }
//        }
//
//    }
//
//    private void encodeBitmapImage(Bitmap bitmap) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
//
//        byte[] imageBytes = stream.toByteArray();
//
//        encodedImages = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//
//    }
//    private void RetrieveDataHewanInfo() {
//        DaftarHewanRef.child(HewanId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                if ((snapshot.exists()) && (snapshot.hasChild("nokandang"))) {
//
//                    String noqr = snapshot.child("qrcode").getValue().toString();
//                    String noK = snapshot.child("nokandang").getValue().toString();
//                    String namahewanq = snapshot.child("namahewan").getValue().toString();
//                    String namapemilik = snapshot.child("namapemilik").getValue().toString();
//                    String jk = snapshot.child("jk").getValue().toString();
//                    String statushewan = snapshot.child("status").getValue().toString();
//                    String kat = snapshot.child("kategori").getValue().toString();
//                    String kj = snapshot.child("klasifikasijenis").getValue().toString();
//                    String ttl = snapshot.child("tgllahir").getValue().toString();
//                    String tb = snapshot.child("tglbeli").getValue().toString();
//                    String umur = snapshot.child("umur").getValue().toString();
//                    String hb = snapshot.child("hargabeli").getValue().toString();
//                    String bd = snapshot.child("belidari").getValue().toString();
//                    String peristiwa = snapshot.child("peristiwa").getValue().toString();
//                    String sk = snapshot.child("statuskesehatan").getValue().toString();
//                    String temuan = snapshot.child("temuan").getValue().toString();
//                    String treatment = snapshot.child("treatment").getValue().toString();
//                    String hasil = snapshot.child("hasil").getValue().toString();
//                    String imaghewan = snapshot.child("imghewan").getValue().toString();
//
//
//                    Glide.with(imgdetailhewan.getContext())
//                            .load(imaghewan)
//                            .centerCrop()
//                            .placeholder(R.drawable.usepng)
//                            .into(imgdetailhewan);
//
//                    imgdetailhewan.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//
//                            tamplategambarIMGH.setVisibility(View.VISIBLE);
//                            Glide.with(gambarIMGH.getContext())
//                                    .load(imaghewan)
//                                    .optionalFitCenter()
//                                    .placeholder(R.drawable.usepng)
//                                    .into(gambarIMGH);
//
//                            backbtndetailfotoH.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    tamplategambarIMGH.setVisibility(View.GONE);
//                                }
//                            });
//                            btnubahimg.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    String id = idDH.getText().toString();
//
//                                    Dexter.withActivity(DetailHewanActivity.this)
//                                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                                            .withListener(new PermissionListener() {
//                                                @Override
//                                                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//
//                                                    Intent intent = new Intent(Intent.ACTION_PICK);
//                                                    intent.setType("image/*");
//                                                    startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);
//
//                                                }
//
//                                                @Override
//                                                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//                                                }
//
//                                                @Override
//                                                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                                                    permissionToken.continuePermissionRequest();
//                                                }
//                                            }).check();
//                                }
//                            });
//                            btnsimpanimg.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//
//                                    String uid = firebaseAuth.getUid();
//                                    String cekId = idDH.getText().toString();
//                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//
//                                    byte[] imageBytes = stream.toByteArray();
//                                    final String nameImages = "IMG_" + String.valueOf(System.currentTimeMillis()) + "." + ".jpg";
//
////                                    encodedImages = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
//
//                                    StorageReference storage = storageReference.child("Media").child("ImageHewan").child(nameImages);
//                                    storage.putBytes(imageBytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                        @Override
//                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                            storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                                @Override
//                                                public void onSuccess(Uri uri) {
//                                                    String urri = uri.toString();
//
//                                                    HashMap params = new HashMap();
//                                                    params.put("qrcode", noqr);
//                                                    params.put("imghewan", urri);
//
//
//                                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
//                                                    reference.child(uid).child("HewanTernak").child(cekId).updateChildren(params).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                                        @Override
//                                                        public void onComplete(@NotNull Task<Void> task) {
//                                                            if (task.isSuccessful()) {
//                                                                Toast.makeText(DetailHewanActivity.this, "Foto telah diubah", Toast.LENGTH_SHORT).show();
//                                                            } else {
//                                                                Toast.makeText(DetailHewanActivity.this, "Foto gagal diubah", Toast.LENGTH_SHORT).show();
//                                                            }
//                                                        }
//                                                    });
//                                                }
//                                            });
//                                        }
//                                    });
//
//                                }
//                            });
//
//                            inputnamahewan.setText(namahewanq);
//                            inputnokandang.setText(noK);
//                            inputnamapemilik.setText(namapemilik);
//                            inputjk.setText(jk);
//                            inputStatus.setText(statushewan);
//                            inputKategori.setText(kat);
//                            inputKJenis.setText(kj);
//                            inputTTL.setText(ttl);
//                            inputTB.setText(tb);
//                            inputEUmur.setText(umur);
//                            inputHB.setText(hb);
//                            inputBD.setText(bd);
//                            inputPristiwa.setText(peristiwa);
//                            inputSK.setText(sk);
//                            inputTemuan.setText(temuan);
//                            inputTreatment.setText(treatment);
//                            inputHasil.setText(hasil);
//                            idDH.setText(noqr);
//
//                            btnubahdetailhewan.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
////                updatedata();
//
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailHewanActivity.this);
//                                    builder.setTitle("Ubah Data");
//                                    builder.setMessage("Anda yakin untuk memperbarui data ini?");
//                                    builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//                                            String cekId = idDH.getText().toString();
//                                            String nokandang = inputnokandang.getText().toString();
//                                            String namahewan = inputnamahewan.getText().toString();
//                                            String kj = inputKJenis.getText().toString();
//                                            String jk = inputjk.getText().toString();
//                                            if (TextUtils.isEmpty(nokandang) && TextUtils.isEmpty(namahewan) && TextUtils.isEmpty(kj) &&
//                                                    TextUtils.isEmpty(jk)) {
//                                                Toast.makeText(DetailHewanActivity.this, "Maaf data tidak boleh kosong !", Toast.LENGTH_SHORT).show();
//                                                inputnokandang.setError("Tolong Dilengkapi");
//                                                inputnokandang.requestFocus();
//                                                inputnamahewan.setError("Tolong Dilengkapi");
//                                                inputnamahewan.requestFocus();
//                                                inputKJenis.setError("Tolong Dilengkapi");
//                                                inputKJenis.requestFocus();
//                                                inputjk.setError("Tolong Dilengkapi");
//                                                inputjk.requestFocus();
//                                            } else {
//                                                updatedata(cekId);
//                                            }
//                                        }
//                                    });
//                                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int i) {
//                                            dialog.dismiss();
//                                        }
//                                    });
//                                    AlertDialog alertDialog = builder.create();
//                                    alertDialog.show();
//                                }
//                            });
//                            btndeletedetailhewan.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
////                hapusData(id);
//
//                                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailHewanActivity.this);
//                                    builder.setTitle("Hapus Data");
//                                    builder.setMessage("Anda yakin untuk menghapus data ini?");
//                                    builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int which) {
//
//                                            String cekid = idDH.getText().toString();
//                                            hapusData(cekid);
//                                        }
//                                    });
//                                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(DialogInterface dialog, int i) {
//                                            dialog.dismiss();
//                                        }
//                                    });
//                                    AlertDialog alertDialog = builder.create();
//                                    alertDialog.show();
//
//                                }
//                            });
//                            kontener1.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if (countainerIdentitasH.getVisibility() == View.GONE) {
//                                        countainerIdentitasH.setVisibility(View.VISIBLE);
//                                        rowrightDH.animate().rotation(rowrightDH.getRotation() + 90).start();
//                                    } else {
//                                        countainerIdentitasH.setVisibility(View.GONE);
//                                        rowrightDH.animate().rotation(rowrightDH.getRotation() - 90).start();
//                                    }
//                                }
//                            });
//                            kontener2.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    if (countainerKesehatan.getVisibility() == View.GONE) {
//                                        countainerKesehatan.setVisibility(View.VISIBLE);
//                                        rowrightDH2.animate().rotation(rowrightDH2.getRotation() + 90).start();
//                                    } else {
//                                        countainerKesehatan.setVisibility(View.GONE);
//                                        rowrightDH2.animate().rotation(rowrightDH2.getRotation() - 90).start();
//                                    }
//                                }
//                            });
//                        }
//                    });
//                }
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
//    }
//    private void updatedata(String cekId) {
//        String namahewan = inputnamahewan.getText().toString();
//        String nokandang = inputnokandang.getText().toString();
//        String namapemilik = inputnamapemilik.getText().toString();
//        String jk = inputjk.getText().toString();
//        String statushewan = inputStatus.getText().toString();
//        String kategori = inputKategori.getText().toString();
//        String kj = inputKJenis.getText().toString();
//        String tanggallahir = inputTTL.getText().toString();
//        String tanggalbeli = inputTB.getText().toString();
//        String umur = inputEUmur.getText().toString();
//        String hargabeli = inputHB.getText().toString();
//        String belidari = inputBD.getText().toString();
//        String peristiwa = inputPristiwa.getText().toString();
//        String statuskesehatan = inputSK.getText().toString();
//        String temuan = inputTemuan.getText().toString();
//        String treatment = inputTreatment.getText().toString();
//        String hasil = inputHasil.getText().toString();
////        String imaghewan = imgdetailhewan.getDrawable().toString();
//        String id = idDH.getText().toString();
//
//            HashMap params = new HashMap();
//            params.put("id", id);
//            params.put("nokandang", nokandang);
//            params.put("namahewan", namahewan);
//            params.put("namapemilik", namapemilik);
//            params.put("jk", jk);
//            params.put("statushewan", statushewan);
//            params.put("kategori", kategori);
//            params.put("kj", kj);
//            params.put("tanggallahir", tanggallahir);
//            params.put("tanggalbeli", tanggalbeli);
//            params.put("umur", umur);
//            params.put("hargabeli", hargabeli);
//            params.put("belidari", belidari);
//            params.put("peristiwa", peristiwa);
//            params.put("setatuskesehatan", statuskesehatan);
//            params.put("temuan", temuan);
//            params.put("treatment", treatment);
//            params.put("hasil", hasil);
////                    params.put("imaghewan", encodedImages);
////                    params.put("imaghewan", imaghewan);
//
//            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
//            reference.child(firebaseAuth.getUid()).child("HewanTernak").child(cekId)
//                    .updateChildren(params).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NotNull Task<Void> task) {
//                    if(task.isSuccessful()){
//                        Toast.makeText(DetailHewanActivity.this, "Data telah diubah", Toast.LENGTH_SHORT).show();
//                    }else {
//                        Toast.makeText(DetailHewanActivity.this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//
//
//    }
//
//    private void hapusData(String cekid) {
//        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("HewanTernak")
//                .child(cekid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NotNull Task<Void> task) {
//                Toast.makeText(DetailHewanActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
//                finish();
//            }
//        });
//    }
//
//}




package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.example.projectkt.qrcode.DataModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DetailHewanActivity extends AppCompatActivity {

    Bitmap bitmap;
    String encodedImages;
    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;
    private boolean isZoomAndRotate;
    private boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    float oldDist = 1f;
    private float xCoOrdinate, yCoOrdinate;

    private DatabaseReference DaftarHewanRef;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private String HewanId;

    ArrayList<FormModelHewan> formModelHewans;
    TextView idDH, nameHewan, btnubahimg, btnsimpanimg;
    EditText inputnamahewan, inputnamapemilik, inputnokandang, inputjk, inputStatus, inputKategori, inputKJenis, inputTTL, inputTB, inputEUmur, inputHB, inputBD, inputPristiwa;
    EditText inputSK, inputTemuan, inputTreatment, inputHasil;
    RelativeLayout kontener1, kontener2, countainerIdentitasH, countainerKesehatan, tamplategambarIMGH;
    ImageView rowrightDH, rowrightDH2;
    ImageView imgdetailhewan, gambarIMGH, backbtndetailfotoH, backbtnkeuangan;
    Button btnubahdetailhewan;
    TextView btndeletedetailhewan;

    private String namahewanq;
    private String nokandangq, namapemiliq, inputjkq, inputstatusq, inputkategoriq, inputjenisq, inputttlq, inputtbq, inputumurq, inputhbq, inputbdq, inputpristiwaq, inputSKq, inputTemuanq, inputTreatmentq, inputHasilq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_hewan);

        idDH = (TextView) findViewById(R.id.idDH);
        inputnokandang = (EditText) findViewById(R.id.inputnokandang);
        inputKategori = (EditText) findViewById(R.id.inputKategori);
        inputnamapemilik = (EditText) findViewById(R.id.inputnamapemilik);
        inputjk = (EditText) findViewById(R.id.inputjk);
        inputStatus = (EditText) findViewById(R.id.inputStatus);
        inputKJenis = (EditText) findViewById(R.id.inputKJenis);
        inputTTL = (EditText) findViewById(R.id.inputTTL);
        inputTB = (EditText) findViewById(R.id.inputTB);
        inputEUmur = (EditText) findViewById(R.id.inputEUmur);
        inputHB = (EditText) findViewById(R.id.inputHB);
        inputBD = (EditText) findViewById(R.id.inputBD);
        inputPristiwa = (EditText) findViewById(R.id.inputPristiwa);

        inputSK = (EditText) findViewById(R.id.inputSK);
        inputTemuan = (EditText) findViewById(R.id.inputTemuan);
        inputTreatment = (EditText) findViewById(R.id.inputTreatment);
        inputHasil = (EditText) findViewById(R.id.inputHasil);

        kontener1 = (RelativeLayout) findViewById(R.id.kontener1);
        kontener2 = (RelativeLayout) findViewById(R.id.kontener2);
        countainerIdentitasH = (RelativeLayout) findViewById(R.id.countainerIdentitasH);
        countainerKesehatan = (RelativeLayout) findViewById(R.id.countainerKesehatan);

        nameHewan = (TextView) findViewById(R.id.nameHewan);
        btnubahimg = (TextView) findViewById(R.id.btnubahimg);
        btnsimpanimg = (TextView) findViewById(R.id.btnsimpanimg);
        tamplategambarIMGH = (RelativeLayout) findViewById(R.id.tamplategambarIMGH);
        backbtndetailfotoH = (ImageView) findViewById(R.id.backbtndetailfotoH);
        gambarIMGH = (ImageView) findViewById(R.id.gambarIMGH);

        rowrightDH = (ImageView) findViewById(R.id.rowrightDH);
        rowrightDH2 = (ImageView) findViewById(R.id.rowrightDH2);
        backbtnkeuangan = (ImageView) findViewById(R.id.backbtnkeuangan);

        imgdetailhewan = (ImageView) findViewById(R.id.imgdetailhewan);
        inputnamahewan = (EditText) findViewById(R.id.inputnamahewan);

        btndeletedetailhewan = findViewById(R.id.btndeletedetailhewan);
        btnubahdetailhewan = findViewById(R.id.btnubahdetailhewan);

        formModelHewans = new ArrayList<>();

        DaftarHewanRef = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("HewanTernak");
        HewanId = getIntent().getExtras().get("clickHewanId").toString();
        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        RetrieveDataHewanInfo();
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==RESULT_OK){
            Uri ImageUris = data.getData();
            try {

                btnubahimg.setVisibility(View.GONE);
                btnsimpanimg.setVisibility(View.VISIBLE);

                InputStream inputStream = getContentResolver().openInputStream(ImageUris);
                bitmap = BitmapFactory.decodeStream(inputStream);
                gambarIMGH.setImageBitmap(bitmap);
//                encodeBitmapImage(bitmap);

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
    private void RetrieveDataHewanInfo() {

        DaftarHewanRef.child(HewanId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                if ((snapshot.exists()) && (snapshot.hasChild("nokandang"))) {

                    String noqr = snapshot.child("qrcode").getValue().toString();
                    String noK = snapshot.child("nokandang").getValue().toString();
                    String namahewanq = snapshot.child("namahewan").getValue().toString();
                    String namapemilik = snapshot.child("namapemilik").getValue().toString();
                    String jk = snapshot.child("jk").getValue().toString();
                    String statushewan = snapshot.child("status").getValue().toString();
                    String kat = snapshot.child("kategori").getValue().toString();
                    String kj = snapshot.child("klasifikasijenis").getValue().toString();
                    String ttl = snapshot.child("tgllahir").getValue().toString();
                    String tb = snapshot.child("tglbeli").getValue().toString();
                    String umur = snapshot.child("umur").getValue().toString();
                    String hb = snapshot.child("hargabeli").getValue().toString();
                    String bd = snapshot.child("belidari").getValue().toString();
                    String peristiwa = snapshot.child("peristiwa").getValue().toString();
                    String sk = snapshot.child("statuskesehatan").getValue().toString();
                    String temuan = snapshot.child("temuan").getValue().toString();
                    String treatment = snapshot.child("treatment").getValue().toString();
                    String hasil = snapshot.child("hasil").getValue().toString();
                    String imaghewan = snapshot.child("imghewan").getValue().toString();

                    inputnamahewan.setText(namahewanq);
                    inputnokandang.setText(noK);
                    inputnamapemilik.setText(namapemilik);
                    inputjk.setText(jk);
                    inputStatus.setText(statushewan);
                    inputKategori.setText(kat);
                    inputKJenis.setText(kj);
                    inputTTL.setText(ttl);
                    inputTB.setText(tb);
                    inputEUmur.setText(umur);
                    inputHB.setText(hb);
                    inputBD.setText(bd);
                    inputPristiwa.setText(peristiwa);
                    inputSK.setText(sk);
                    inputTemuan.setText(temuan);
                    inputTreatment.setText(treatment);
                    inputHasil.setText(hasil);
                    idDH.setText(noqr);
                    Glide.with(imgdetailhewan.getContext())
                            .load(imaghewan)
                            .centerCrop()
                            .placeholder(R.drawable.usepng)
                            .into(imgdetailhewan);

                    imgdetailhewan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            tamplategambarIMGH.setVisibility(View.VISIBLE);
                            Glide.with(gambarIMGH.getContext())
                                    .load(imaghewan)
                                    .optionalFitCenter()
                                    .placeholder(R.drawable.usepng)
                                    .into(gambarIMGH);

                            backbtndetailfotoH.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tamplategambarIMGH.setVisibility(View.GONE);
                                }
                            });
                            btnubahimg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    String id = idDH.getText().toString();

                                    Dexter.withActivity(DetailHewanActivity.this)
                                            .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                                            .withListener(new PermissionListener() {
                                                @Override
                                                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                                    Intent intent = new Intent(Intent.ACTION_PICK);
                                                    intent.setType("image/*");
                                                    startActivityForResult(Intent.createChooser(intent, "Browse Image"), 1);

                                                }

                                                @Override
                                                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                                                }

                                                @Override
                                                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                                    permissionToken.continuePermissionRequest();
                                                }
                                            }).check();
                                }
                            });
                            btnsimpanimg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    String uid = firebaseAuth.getUid();
                                    String cekId = idDH.getText().toString();
                                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

                                    byte[] imageBytes = stream.toByteArray();
                                    final String nameImages = "IMG_" + String.valueOf(System.currentTimeMillis()) + "." + ".jpg";

//                                    encodedImages = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);

                                    StorageReference storage = storageReference.child("Media").child("ImageHewan").child(nameImages);
                                    storage.putBytes(imageBytes).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                            storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                @Override
                                                public void onSuccess(Uri uri) {
                                                    String urri = uri.toString();

                                                    HashMap params = new HashMap();
                                                    params.put("qrcode", noqr);
                                                    params.put("imghewan", urri);


                                                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                                                    reference.child(uid).child("HewanTernak").child(cekId).updateChildren(params).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NotNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                Toast.makeText(DetailHewanActivity.this, "Foto telah diubah", Toast.LENGTH_SHORT).show();
                                                            } else {
                                                                Toast.makeText(DetailHewanActivity.this, "Foto gagal diubah", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    });

                                }
                            });




                            kontener1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (countainerIdentitasH.getVisibility() == View.GONE) {
                                        countainerIdentitasH.setVisibility(View.VISIBLE);
                                        rowrightDH.animate().rotation(rowrightDH.getRotation() + 90).start();
                                    } else {
                                        countainerIdentitasH.setVisibility(View.GONE);
                                        rowrightDH.animate().rotation(rowrightDH.getRotation() - 90).start();
                                    }
                                }
                            });
                            kontener2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (countainerKesehatan.getVisibility() == View.GONE) {
                                        countainerKesehatan.setVisibility(View.VISIBLE);
                                        rowrightDH2.animate().rotation(rowrightDH2.getRotation() + 90).start();
                                    } else {
                                        countainerKesehatan.setVisibility(View.GONE);
                                        rowrightDH2.animate().rotation(rowrightDH2.getRotation() - 90).start();
                                    }
                                }
                            });
                        }
                    });
                    btnubahdetailhewan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                updatedata();

                            AlertDialog.Builder builder = new AlertDialog.Builder(DetailHewanActivity.this);
                            builder.setTitle("Ubah Data");
                            builder.setMessage("Anda yakin untuk memperbarui data ini?");
                            builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    String cekId = idDH.getText().toString();
                                    String nokandang = inputnokandang.getText().toString();
                                    String namahewan = inputnamahewan.getText().toString();
                                    String kj = inputKJenis.getText().toString();
                                    String jk = inputjk.getText().toString();
                                    if (TextUtils.isEmpty(nokandang) && TextUtils.isEmpty(namahewan) && TextUtils.isEmpty(kj) &&
                                            TextUtils.isEmpty(jk)) {
                                        Toast.makeText(DetailHewanActivity.this, "Maaf data tidak boleh kosong !", Toast.LENGTH_SHORT).show();
                                        inputnokandang.setError("Tolong Dilengkapi");
                                        inputnokandang.requestFocus();
                                        inputnamahewan.setError("Tolong Dilengkapi");
                                        inputnamahewan.requestFocus();
                                        inputKJenis.setError("Tolong Dilengkapi");
                                        inputKJenis.requestFocus();
                                        inputjk.setError("Tolong Dilengkapi");
                                        inputjk.requestFocus();
                                    } else {
                                        updatedata(cekId);
                                    }
                                }
                            });
                            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    });
                    btndeletedetailhewan.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
//                hapusData(id);

                            AlertDialog.Builder builder = new AlertDialog.Builder(DetailHewanActivity.this);
                            builder.setTitle("Hapus Data");
                            builder.setMessage("Anda yakin untuk menghapus data ini?");
                            builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    String cekid = idDH.getText().toString();
                                    hapusData(cekid);
                                }
                            });
                            builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int i) {
                                    dialog.dismiss();
                                }
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
    }
    private void updatedata(String cekId) {
        String namahewan = inputnamahewan.getText().toString();
        String nokandang = inputnokandang.getText().toString();
        String namapemilik = inputnamapemilik.getText().toString();
        String jk = inputjk.getText().toString();
        String statushewan = inputStatus.getText().toString();
        String kategori = inputKategori.getText().toString();
        String kj = inputKJenis.getText().toString();
        String tanggallahir = inputTTL.getText().toString();
        String tanggalbeli = inputTB.getText().toString();
        String umur = inputEUmur.getText().toString();
        String hargabeli = inputHB.getText().toString();
        String belidari = inputBD.getText().toString();
        String peristiwa = inputPristiwa.getText().toString();
        String statuskesehatan = inputSK.getText().toString();
        String temuan = inputTemuan.getText().toString();
        String treatment = inputTreatment.getText().toString();
        String hasil = inputHasil.getText().toString();
//        String imaghewan = imgdetailhewan.getDrawable().toString();
        String id = idDH.getText().toString();

        HashMap params = new HashMap();
        params.put("id", id);
        params.put("nokandang", nokandang);
        params.put("namahewan", namahewan);
        params.put("namapemilik", namapemilik);
        params.put("jk", jk);
        params.put("statushewan", statushewan);
        params.put("kategori", kategori);
        params.put("kj", kj);
        params.put("tanggallahir", tanggallahir);
        params.put("tanggalbeli", tanggalbeli);
        params.put("umur", umur);
        params.put("hargabeli", hargabeli);
        params.put("belidari", belidari);
        params.put("peristiwa", peristiwa);
        params.put("setatuskesehatan", statuskesehatan);
        params.put("temuan", temuan);
        params.put("treatment", treatment);
        params.put("hasil", hasil);
//                    params.put("imaghewan", encodedImages);
//                    params.put("imaghewan", imaghewan);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(firebaseAuth.getUid()).child("HewanTernak").child(cekId)
                .updateChildren(params).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NotNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(DetailHewanActivity.this, "Data telah diubah", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(DetailHewanActivity.this, "Data gagal diubah", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void hapusData(String cekid) {
        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("HewanTernak")
                .child(cekid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NotNull Task<Void> task) {
                Toast.makeText(DetailHewanActivity.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

}