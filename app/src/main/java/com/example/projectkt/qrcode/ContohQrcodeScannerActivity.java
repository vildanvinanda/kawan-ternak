package com.example.projectkt.qrcode;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blikoon.qrcodescanner.QrCodeActivity;
import com.bumptech.glide.Glide;
import com.example.projectkt.DetailHewanActivity;
import com.example.projectkt.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class  ContohQrcodeScannerActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_QR_SCAN = 101;

    RecyclerView reccontoh;
    LinearLayoutManager linearLayoutManager;
    AdapterRecItemContoh adapterRecItemContoh;
    List<DataModel> listData;
    DataModel dataModel;
    public static final String EXTRA_NAME = "namahewan";
    public static final String EXTRA_HB = "hargabeli";
    public static final String EXTRA_IMG = "imaghewan";
    public static final String EXTRA_JK = "jk";
    public static final String EXTRA_STATUS = "statushewan";
    public static final String EXTRA_ID = "id";
    public static final String EXTRA_NOKANDANG = "nokandang";
    public static final String EXTRA_TTL = "tanggallahir";
    public static final String EXTRA_NPEMILIK = "namapemilik";
    public static final String EXTRA_KATEGORI = "kategori";
    public static final String EXTRA_KJ = "kj";
    public static final String EXTRA_TB = "tanggalbeli";
    public static final String EXTRA_UMUR = "umur";
    public static final String EXTRA_BD = "belidari";
    public static final String EXTRA_PERISTIWA = "peristiwa";
    public static final String EXTRA_SK = "statuskesehatan";
    public static final String EXTRA_TEMUAN = "temuan";
    public static final String EXTRA_TREATMENT = "treatment";
    public static final String EXTRA_HASIL = "hasil";

    TextView txthallo;
    Button btnscanner;
    ImageView gambar;

    String namahewan,nomor, imaghewan, id, namapemilik, noK, kj, hb, ttl, kat, peristiwa, hasil, treatment, temuan,jk, bd, statushewan, tb, sk, umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contoh_qrcode_scanner);

        //recyclerview
        reccontoh = findViewById(R.id.reccontoh);

//
//        for(int i=0; i<10; i++)
//        {
//            listData.add("Data ke"+i);
//        }

        namahewan = "";
        imaghewan = "";
        nomor = "";

        txthallo = findViewById(R.id.txthallo);
        gambar = findViewById(R.id.gambar);

        btnscanner = findViewById(R.id.btnscanner);
        btnscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent i = new Intent(ContohQrcodeScannerActivity.this, QrCodeActivity.class);
                                startActivityForResult( i,REQUEST_CODE_QR_SCAN);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                permissionDeniedResponse.getRequestedPermission();
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                            }
                        }).check();

            }
        });
    }
//
//    private void getData()
//    {
//
//        String urls = "http://192.168.100.14/dbprojectkt/cari.php";
//
//        //volley
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, urls, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                dataModel = new DataModel();
//                listData = new ArrayList<>();
////                Toast.makeText(ContohQrcodeScannerActivity.this, response, Toast.LENGTH_SHORT).show();
////                txthallo.setText(response);
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    JSONArray jsonArray = jsonObject.getJSONArray("results");
//
//                    for (int i=0 ; i<jsonArray.length();i++)
//                    {
//                        JSONObject data = jsonArray.getJSONObject(i);
//                        dataModel.setNamahewan(data.getString("namahewan"));
//                        dataModel.setJk(data.getString("jk"));
//                        dataModel.setStatushewan(data.getString("statushewan"));
//                        dataModel.setNamapemilik(data.getString("namapemilik"));
//                        dataModel.setImaghewan(data.getString("imaghewan"));
//                        listData.add((dataModel));
//
//                    }
//                    linearLayoutManager = new LinearLayoutManager(ContohQrcodeScannerActivity.this, LinearLayoutManager.VERTICAL,false);
//                    reccontoh.setLayoutManager(linearLayoutManager);
//                    adapterRecItemContoh = new AdapterRecItemContoh(ContohQrcodeScannerActivity, listData);
//                    reccontoh.setAdapter(adapterRecItemContoh);
//                    adapterRecItemContoh.notifyDataSetChanged();
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Toast.makeText(ContohQrcodeScannerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//                txthallo.setText("error");
//            }
//        });
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
//            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.");
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(ContohQrcodeScannerActivity.this).create();
                alertDialog.setTitle("Scan Error");
                alertDialog.setMessage("QR Code could not be scanned");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
            return;

        }
        if (requestCode == REQUEST_CODE_QR_SCAN) {
            if (data == null)
                return;
            //Getting the passed result
            String hasil_nomor = data.getStringExtra("com.blikoon.qrcodescanner.got_qr_scan_relult");
//            Log.d(LOGTAG, "Have scan result in your app activity :" + result);
            ambil_data(hasil_nomor);
//            AlertDialog alertDialog = new AlertDialog.Builder(ContohQrcodeScannerActivity.this).create();
//            alertDialog.setTitle("Scan result");
//            alertDialog.setMessage(result);
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//            alertDialog.show();

        }
    }
    void ambil_data (String hasil_scan)
    {


        String url = "https://projectkawanternak.000webhostapp.com/cariaku.php?cek="+hasil_scan;
//        String url = "http://192.168.100.14/dbprojectkt/cariaku.php?cek="+hasil_scan;


        //volley
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(ContohQrcodeScannerActivity.this, response, Toast.LENGTH_SHORT).show();
//                txthallo.setText(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
//                    String nama = jsonObject.getString("namahewan");

//                    String status = jsonObject.getString("nama");

                        //ini meamasukan nama sama nomor dibawah
                        namahewan = jsonObject.getString("namahewan");
                        namapemilik = jsonObject.getString("namapemilik");
                        id = jsonObject.getString("id");
                        imaghewan = jsonObject.getString("imaghewan");
                        noK = jsonObject.getString("nokandang");
                        jk = jsonObject.getString("jk");
                        statushewan = jsonObject.getString("statushewan");
                        kat = jsonObject.getString("kategori");
                        kj = jsonObject.getString("kj");
                        ttl = jsonObject.getString("tanggallahir");
                        tb = jsonObject.getString("tanggalbeli");
                        umur = jsonObject.getString("umur");
                        hb = jsonObject.getString("hargabeli");
                        bd = jsonObject.getString("belidari");
                        peristiwa = jsonObject.getString("peristiwa");
                        sk = jsonObject.getString("statuskesehatan");
                        temuan = jsonObject.getString("temuan");
                        treatment = jsonObject.getString("treatment");
                        hasil = jsonObject.getString("hasil");

                    Intent intent = new Intent(ContohQrcodeScannerActivity.this, DetailHewanActivity.class);
                    intent.putExtra("namahewan",namahewan);
                    intent.putExtra("namapemilik",namapemilik);
                    intent.putExtra("id",id);
                    intent.putExtra("imaghewan",imaghewan);
                    intent.putExtra("nokandang",noK);
                    intent.putExtra("jk",namahewan);
                    intent.putExtra("statushewan",statushewan);
                    intent.putExtra("umur",umur);
                    intent.putExtra("hargabeli",hb);
                    intent.putExtra("kategori",kat);
                    intent.putExtra("kj",kj);
                    intent.putExtra("tanggallahir",ttl);
                    intent.putExtra("tanggalbeli",tb);
                    intent.putExtra("belidari",bd);
                    intent.putExtra("peristiwa",peristiwa);
                    intent.putExtra("statuskesehatan",sk);
                    intent.putExtra("temuan",temuan);
                    intent.putExtra("treatment",treatment);
                    intent.putExtra("hasil",hasil);
                    intent.putExtra("namahewan",namahewan);
                    startActivity(intent);

                        //ini bisa di pake
//                        txthallo.setText(namahewan);
//                        Glide.with(getApplicationContext()).load(imaghewan).into(gambar);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(ContohQrcodeScannerActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                txthallo.setText("error");
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}