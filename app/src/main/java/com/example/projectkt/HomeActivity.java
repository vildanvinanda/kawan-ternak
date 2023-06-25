package com.example.projectkt;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.blikoon.qrcodescanner.QrCodeActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.projectkt.databinding.ActivityHomeBinding;
import com.example.projectkt.qrcode.AdapterRecItemContoh;
import com.example.projectkt.qrcode.ContohQrcodeScannerActivity;
import com.example.projectkt.qrcode.DataModel;
import com.example.projectkt.qrcode.ScannQRCodeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.google.android.material.navigation.NavigationBarView.LABEL_VISIBILITY_LABELED;


public class HomeActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener{

    private ActivityHomeBinding binding;
//    private ActivityHomeBinding binding;

    private BottomNavigationView bottomNavigationView;
    private HomeFragment homeFragment = new HomeFragment();
    private KeuanganFragment keuanganFragment = new KeuanganFragment();
    private EdukasiFragment edukasiFragment = new EdukasiFragment();
    private ProfileFragment profileFragment = new ProfileFragment();

    FloatingActionButton qrscan;

    private EdukasiFragment viewModel;

    //inislide
    private ImageSlider imageSlider;


    //scann
    private static final int REQUEST_CODE_QR_SCAN = 101;
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

    String namahewan,nomor, imaghewan, id, namapemilik, noK, kj, hb, ttl, kat, peristiwa, hasil, treatment, temuan,jk, bd, statushewan, tb, sk, umur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        qrscan = findViewById(R.id.qrscan);

        //ini slide

//        imageSlider = findViewById (R.id.image_slider);
//        ArrayList<SlideModel> arrayList = new ArrayList<>();
//
//        arrayList.add(new SlideModel(R.drawable.ayamjupiter, ScaleTypes.FIT));
//        arrayList.add(new SlideModel(R.drawable.iklan, ScaleTypes.FIT));
//        arrayList.add(new SlideModel(R.drawable.sapi, ScaleTypes.FIT));
//
//        imageSlider.setImageList(arrayList);



//        bottomNavigationView.setLabelVisibilityMode(LABEL_VISIBILITY_LABELED);
//        bottomNavigationView.setItemTextColor(null);
        bottomNavigationView.setBackground(null);
        bottomNavigationView.setItemIconTintList(null);
        bottomNavigationView.setOnItemSelectedListener(this);

        namahewan = "";
        imaghewan = "";
        nomor = "";

        qrscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent i = new Intent(HomeActivity.this, QrCodeActivity.class);
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

        if(!connected(this)){
            showInternetDialog();
        }
        
    }

    private void showInternetDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setCancelable(false);
        View view = LayoutInflater.from(this).inflate(R.layout.modal_lost_connection, findViewById(R.id.no_internet_layout));
        view.findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!connected(HomeActivity.this)){
                    showInternetDialog();
                } else {
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                }
            }
        });
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean connected(HomeActivity homeActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

//        boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        
        return(wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.miHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, homeFragment).commit();
                return true;
            case R.id.miKeuangan:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, keuanganFragment).commit();
                return true;
            case R.id.miEdu:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, edukasiFragment).commit();
                return true;
            case R.id.miProfile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, profileFragment).commit();
                return true;
        }
        return false;
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
//            Log.d(LOGTAG,"COULD NOT GET A GOOD RESULT.RESULT");
            if (data == null)
                return;
            //Getting the passed result
            String result = data.getStringExtra("com.blikoon.qrcodescanner.error_decoding_image");
            if (result != null) {
                AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
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
//            ambil_data(hasil_nomor);
            getDatainFirebase(hasil_nomor);
//            AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
//            alertDialog.setTitle("Scan result");
//            alertDialog.setMessage(hasil_nomor);
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//            alertDialog.show();

        }
    }

    private void getDatainFirebase(String hasil_nomor) {
        Intent intent = new Intent(HomeActivity.this, DetailHewanWithScam.class);
        intent.putExtra("hasil_nomor",hasil_nomor);
        startActivity(intent);
    }

    void ambil_data (String hasil_scan)
    {


        String url = "https://projectkawanternak.000webhostapp.com/cariaku.php?cek="+hasil_scan;


        //volley
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

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

                    Intent intent = new Intent(HomeActivity.this, DetailHewanActivity.class);
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
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

}