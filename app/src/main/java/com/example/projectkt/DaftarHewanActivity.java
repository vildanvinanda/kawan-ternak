//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.projectkt.qrcode.AdapterRecItemContoh;
//import com.example.projectkt.qrcode.ContohQrcodeScannerActivity;
//import com.example.projectkt.qrcode.DataModel;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.auth.FirebaseAuth;
//
//import org.checkerframework.checker.units.qual.A;
//import org.jetbrains.annotations.NotNull;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DaftarHewanActivity extends AppCompatActivity implements AdapterRecItemContoh.OnItemClickListener {
//
//    FloatingActionButton tambahhewan;
//
//    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//    EditText kolomsearch;
//    ImageView icontgl, backbtndafhewan;
//    RelativeLayout btnsemuahewan, btnjantan, btnbetina;
//
//    TextView txtsemuahewan, txtjantan, txtbetina;
//
//    RecyclerView recDafH;
//    ArrayList<DataModel> dataModels;
//
//    private RequestQueue requestQueue;
//    private AdapterRecItemContoh adapterRecItemContoh, adapterRecItemContoh2, adapterRecItemContoh3, adapterRecItemContoh4, adapterRecItemContoh5;
//
//    public static final String EXTRA_NAME = "namahewan";
//    public static final String EXTRA_IMG = "imaghewan";
//    public static final String EXTRA_JK = "jk";
//    public static final String EXTRA_STATUS = "statushewan";
//    public static final String EXTRA_ID = "id";
//
//    public static final String JSON_URL = "http://192.168.100.14/dbprojectkt/cari.php";
//    public static final String urlsearch = "http://192.168.100.14/dbprojectkt/search.php?cek=";
//    Context context;
//
////
////    List<DataModel> listOfDataModel;
////    RecyclerView recyclerView;
////    String TAG_ID = "id";
////    String TAG_NAMAHEWAN = "namahewan";
////    String TAG_NAMAPEMILI = "namapemilik";
////    String TAG_JK = "jk";
////    String TAG_STATUSHEWAN = "statushewan";
////    String TAG_KATEGORI = "kategori";
////    String TAG_KJ = "kj";
////    String TAG_TANGGALLAHIR = "tanggallahir";
////    String TAG_TANGGALBELI = "tanggalbeli";
////    String TAG_UMUR = "umur";
////    String TAG_HARGABELI = "hargabeli";
////    String TAG_BELIDARI = "belidari";
////    String TAG_PERISTIWA = "peristiwa";
////    String TAG_NOMOR = "nomor";
////    String TAG_IMAGHEWAN = "imaghewan";
////
////    JsonArrayRequest RequestOfJsSonArray;
////    RequestQueue requestQueue;
////
////    View view;
////    int RecyclerItemPosition;
////
////    RecyclerView.LayoutManager layoutManagerOfrecycerview;
////    RecyclerView.Adapter recyclerViewAdapter;
////    ArrayList<String> ImageTitleidArrayListForClick;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_daftar_hewan);
//
//        tambahhewan = findViewById(R.id.tambahhewan);
//        tambahhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DaftarHewanActivity.this, FormHewanActivity.class);
//                startActivity(intent);
//            }
//        });
////
////        ImageTitleidArrayListForClick = new ArrayList<>();
////        listOfDataModel = new ArrayList<>();
////
////        recyclerView = (RecyclerView) findViewById(R.id.recyclerviewContoh);
////        recyclerView.setHasFixedSize(true);
////        layoutManagerOfrecycerview = new LinearLayoutManager(this);
////        recyclerView.setLayoutManager(layoutManagerOfrecycerview);
//
//
//        //ini recyclerview
//        recDafH = findViewById(R.id.recDafH);
//        dataModels = new ArrayList<>();
//
//        txtsemuahewan = findViewById(R.id.txtsemuahewan);
//        txtjantan = findViewById(R.id.txtjantan);
//        txtbetina = findViewById(R.id.txtbetina);
//
//        btnbetina = findViewById(R.id.btnbetina);
//        btnjantan = findViewById(R.id.btnjantan);
//        btnsemuahewan = findViewById(R.id.btnsemuahewan);
//
//        btnsemuahewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded));
//                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                txtsemuahewan.setTextColor(getResources().getColor(R.color.white));
//                txtjantan.setTextColor(getResources().getColor(R.color.kuning));
//                txtbetina.setTextColor(getResources().getColor(R.color.kuning));
//                coba();
//            }
//        });
//        btnjantan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded));
//                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                txtsemuahewan.setTextColor(getResources().getColor(R.color.kuning));
//                txtjantan.setTextColor(getResources().getColor(R.color.white));
//                txtbetina.setTextColor(getResources().getColor(R.color.kuning));
//                coba2();
//            }
//        });
//        btnbetina.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded));
//                txtsemuahewan.setTextColor(getResources().getColor(R.color.kuning));
//                txtjantan.setTextColor(getResources().getColor(R.color.kuning));
//                txtbetina.setTextColor(getResources().getColor(R.color.white));
//                coba3();
//            }
//        });
//
//        kolomsearch = findViewById(R.id.kolomsearch);
//
//
//        icontgl = findViewById(R.id.icontgl);
//
//        kolomsearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString()!=null)
//                {
//                   coba(s.toString());
//                }
//                else
//                {
//                    coba("");
//                }
////                coba(s.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        backbtndafhewan = findViewById(R.id.backbtndafhewan);
//        backbtndafhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
////        coba();
//
//    }
//
//    @Override
//    public void onStart(){
//        super.onStart();
//        coba();
//    }
//    private void coba(String data) {
//        if(data.isEmpty())
//        {
//            RequestQueue queue = Volley.newRequestQueue(DaftarHewanActivity.this);
//            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlsearch+data, null, new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//                            JSONObject dataObject = response.getJSONObject(i);
//                            String user = firebaseAuth.getUid();
//
////                        String uidcek = dataObject.getString("uid").toString();
//                            if(dataObject.getString("uid").equals(user))
//                            {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            } else {
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    adapterRecItemContoh = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                    adapterRecItemContoh.notifyDataSetChanged();
//                    adapterRecItemContoh.clear();
//                    recDafH.setAdapter(adapterRecItemContoh);
//                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//
////                    LinearLayoutManager layoutManager = new LinearLayoutManager(DaftarHewanActivity.this, RecyclerView.VERTICAL,false);
////                    recDafH.setLayoutManager(layoutManager);
////                    adapterRecItemContoh = new AdapterRecItemContoh(DaftarHewanActivity.this, dataModels);
////                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
////                    adapterRecItemContoh.notifyDataSetChanged();
////                    recDafH.setAdapter(adapterRecItemContoh);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//                }
//            });
//            queue.add(jsonArrayRequest);
//
//            RequestQueue queue2 = Volley.newRequestQueue(this);
//            JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//                            JSONObject dataObject = response.getJSONObject(i);
//                            String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                            if(dataObject.getString("uid").equals(user))
//                            {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            } else {
//
//                            }
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    adapterRecItemContoh2 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                    recDafH.setAdapter(adapterRecItemContoh2);
//                    adapterRecItemContoh2.setOnItemClickListener(DaftarHewanActivity.this);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//                }
//            });
//
//            queue2.add(jsonArrayRequest2);
//        }
//        else {
//
//            RequestQueue queue = Volley.newRequestQueue(DaftarHewanActivity.this);
//            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlsearch+data, null, new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//                            JSONObject dataObject = response.getJSONObject(i);
//                            String user = firebaseAuth.getUid();
//
////                        String uidcek = dataObject.getString("uid").toString();
//                            if(dataObject.getString("uid").equals(user))
//                            {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            } else {
//
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    adapterRecItemContoh = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                    adapterRecItemContoh.notifyDataSetChanged();
//                    recDafH.setAdapter(adapterRecItemContoh);
//                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//
////                    LinearLayoutManager layoutManager = new LinearLayoutManager(DaftarHewanActivity.this, RecyclerView.VERTICAL,false);
////                    recDafH.setLayoutManager(layoutManager);
////                    adapterRecItemContoh = new AdapterRecItemContoh(DaftarHewanActivity.this, dataModels);
////                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
////                    adapterRecItemContoh.notifyDataSetChanged();
////                    recDafH.setAdapter(adapterRecItemContoh);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//                }
//            });
//            queue.add(jsonArrayRequest);
//
//            RequestQueue queue2 = Volley.newRequestQueue(this);
//            JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//
//                    for (int i = 0; i < response.length(); i++) {
//                        try {
//                            JSONObject dataObject = response.getJSONObject(i);
//                            String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                            if(dataObject.getString("uid").equals(user))
//                            {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            } else {
//
//                            }
//
//
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                    recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                    adapterRecItemContoh2 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                    adapterRecItemContoh2.clear();
//                    recDafH.setAdapter(adapterRecItemContoh2);
//                    adapterRecItemContoh2.setOnItemClickListener(DaftarHewanActivity.this);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//                }
//            });
//
//            queue2.add(jsonArrayRequest2);
//
//        }
//    }
//
//    private void coba() {
//        RequestQueue queue2 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            DataModel dataModel = new DataModel();
//                            dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                            dataModel.setJk(dataObject.getString("jk").toString());
//                            dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                            dataModel.setId(dataObject.getString("id").toString());
//                            dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                            dataModel.setNokandang(dataObject.getString("nokandang"));
//                            dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                            dataModels.add(dataModel);
//                        } else {
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh2 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                recDafH.setAdapter(adapterRecItemContoh2);
//                adapterRecItemContoh2.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue2.add(jsonArrayRequest2);
//
//        RequestQueue queue3 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            if(dataObject.getString("jk").equals("Jantan")) {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            }
//                        } else {
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh3 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                adapterRecItemContoh3.clear();
//                recDafH.setAdapter(adapterRecItemContoh3);
//                adapterRecItemContoh3.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue3.add(jsonArrayRequest3);
//
//
//        RequestQueue queue4 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest4 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            if(dataObject.getString("jk").equals("Betina")) {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            }
//                        } else {
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh4 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                adapterRecItemContoh4.clear();
//                recDafH.setAdapter(adapterRecItemContoh4);
//                adapterRecItemContoh4.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue4.add(jsonArrayRequest4);
//    }
//    private void coba2() {
//        RequestQueue queue2 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            DataModel dataModel = new DataModel();
//                            dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                            dataModel.setJk(dataObject.getString("jk").toString());
//                            dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                            dataModel.setId(dataObject.getString("id").toString());
//                            dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                            dataModel.setNokandang(dataObject.getString("nokandang"));
//                            dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                            dataModels.add(dataModel);
//                        } else {
//
//                        }
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh2 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                adapterRecItemContoh2.clear();
//                recDafH.setAdapter(adapterRecItemContoh2);
//                adapterRecItemContoh2.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue2.add(jsonArrayRequest2);
//
//
//        RequestQueue queue3 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            if(dataObject.getString("jk").equals("Jantan")) {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            }
//                        } else {
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh3 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                recDafH.setAdapter(adapterRecItemContoh3);
//                adapterRecItemContoh3.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue3.add(jsonArrayRequest3);
//
//
//        RequestQueue queue4 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest4 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            if(dataObject.getString("jk").equals("Betina")) {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            }
//                        } else {
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh4 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                adapterRecItemContoh4.clear();
//                recDafH.setAdapter(adapterRecItemContoh4);
//                adapterRecItemContoh4.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue4.add(jsonArrayRequest4);
//    }
//    private void coba3() {
//        RequestQueue queue2 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest2 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            DataModel dataModel = new DataModel();
//                            dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                            dataModel.setJk(dataObject.getString("jk").toString());
//                            dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                            dataModel.setId(dataObject.getString("id").toString());
//                            dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                            dataModel.setNokandang(dataObject.getString("nokandang"));
//                            dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                            dataModels.add(dataModel);
//                        } else {
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh2 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                adapterRecItemContoh2.clear();
//                recDafH.setAdapter(adapterRecItemContoh2);
//                adapterRecItemContoh2.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue2.add(jsonArrayRequest2);
//
//        RequestQueue queue3 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest3 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            if(dataObject.getString("jk").equals("Betina")) {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            }
//                        } else {
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh3 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                adapterRecItemContoh3.clear();
//                recDafH.setAdapter(adapterRecItemContoh3);
//                adapterRecItemContoh3.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue3.add(jsonArrayRequest3);
//
//
//        RequestQueue queue4 = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest4 = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i < response.length(); i++) {
//                    try {
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String user = firebaseAuth.getUid();
//
//
//
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(user))
//                        {
//                            if(dataObject.getString("jk").equals("Betina")) {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModels.add(dataModel);
//                            }
//                        } else {
//
//                        }
//
//
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                recDafH.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                adapterRecItemContoh4 = new AdapterRecItemContoh(getApplicationContext(), dataModels);
//                recDafH.setAdapter(adapterRecItemContoh4);
//                adapterRecItemContoh4.setOnItemClickListener(DaftarHewanActivity.this);
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
////                Log.d("tag", "onErrorResponse:" + error.getMessage());
//            }
//        });
//
//        queue4.add(jsonArrayRequest4);
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(this, DetailHewanActivity.class);
//        DataModel clickitm = dataModels.get(position);
//
//        intent.putExtra(EXTRA_IMG, clickitm.getImaghewan());
//        intent.putExtra(EXTRA_NAME, clickitm.getNamahewan());
//        intent.putExtra(EXTRA_STATUS, clickitm.getStatushewan());
//        intent.putExtra(EXTRA_JK, clickitm.getJk());
//        intent.putExtra(EXTRA_ID, clickitm.getId());
//
//        startActivity(intent);
//
//    }
//
//    public void JSON_HTTP (){
//        RequestOfJsSonArray = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                ParseJSonResponse(response);
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//        requestQueue = Volley.newRequestQueue(DaftarHewanActivity.this);
//        requestQueue.add(RequestOfJsSonArray);
//
//    }
//        public void ParseJSonResponse (JSONArray array) {
//            for (int i=0; i<array.length(); i++)
//            {
//                DataModel GetDataModel2 = new DataModel();
//                JSONObject json = null;
//                try {
//                    json = array.getJSONObject(i);
//                    ImageTitleidArrayListForClick.add(json.getString(TAG_NAMAHEWAN));
//                    GetDataModel2.setNamahewan(TAG_NAMAHEWAN);
//                    GetDataModel2.setNamapemilik(TAG_NAMAPEMILI);
//                    GetDataModel2.setJk(TAG_JK);
//                    GetDataModel2.setStatushewan(TAG_STATUSHEWAN);
//                    GetDataModel2.setKategori(TAG_KATEGORI);
//                    GetDataModel2.setKj(TAG_KJ);
//                    GetDataModel2.setTanggallahir(TAG_TANGGALLAHIR);
//                    GetDataModel2.setTanggalbeli(TAG_TANGGALBELI);
//                    GetDataModel2.setHargabeli(TAG_HARGABELI);
//                    GetDataModel2.setBelidari(TAG_BELIDARI);
//                    GetDataModel2.setUmur(TAG_UMUR);
//                    GetDataModel2.setPeristiwa(TAG_PERISTIWA);
//                    GetDataModel2.setNomor(TAG_NOMOR);
//                    GetDataModel2.setImaghewan(TAG_IMAGHEWAN);
//                } catch (JSONException e)
//                {
//                    e.printStackTrace();
//                }
//                listOfDataModel.add(GetDataModel2);
//            }
//            recyclerViewAdapter = new AdapterRecItemContoh(listOfDataModel, this);
//            recyclerView.setAdapter(recyclerViewAdapter);
//        }

//}

//INI YANG BARU

//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.projectkt.qrcode.AdapterRecItemContoh;
//import com.example.projectkt.qrcode.ContohQrcodeScannerActivity;
//import com.example.projectkt.qrcode.DataModel;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import org.checkerframework.checker.units.qual.A;
//import org.jetbrains.annotations.NotNull;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.List;
////
//public class DaftarHewanActivity extends AppCompatActivity implements AdapterRecItemContoh.OnItemClickListener {
//
//    public static final String EXTRA_NAME = "namahewan";
//    public static final String EXTRA_IMG = "imaghewan";
//    public static final String EXTRA_JK = "jk";
//    public static final String EXTRA_STATUS = "statushewan";
//
//    FloatingActionButton tambahhewan;
//
//    RecyclerView recDafH;
//    ArrayList<DataModel> dataModels;
//    private RequestQueue requestQueue;
//    private AdapterRecItemContoh adapterRecItemContoh;
//
//
//    Context context;
//
////
////    List<DataModel> listOfDataModel;
////    RecyclerView recyclerView;
////    String TAG_ID = "id";
////    String TAG_NAMAHEWAN = "namahewan";
////    String TAG_NAMAPEMILI = "namapemilik";
////    String TAG_JK = "jk";
////    String TAG_STATUSHEWAN = "statushewan";
////    String TAG_KATEGORI = "kategori";
////    String TAG_KJ = "kj";
////    String TAG_TANGGALLAHIR = "tanggallahir";
////    String TAG_TANGGALBELI = "tanggalbeli";
////    String TAG_UMUR = "umur";
////    String TAG_HARGABELI = "hargabeli";
////    String TAG_BELIDARI = "belidari";
////    String TAG_PERISTIWA = "peristiwa";
////    String TAG_NOMOR = "nomor";
////    String TAG_IMAGHEWAN = "imaghewan";
////
////    JsonArrayRequest RequestOfJsSonArray;
////    RequestQueue requestQueue;
////
////    View view;
////    int RecyclerItemPosition;
////
////    RecyclerView.LayoutManager layoutManagerOfrecycerview;
////    RecyclerView.Adapter recyclerViewAdapter;
////    ArrayList<String> ImageTitleidArrayListForClick;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_daftar_hewan);
//
//        tambahhewan = findViewById(R.id.tambahhewan);
//        tambahhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DaftarHewanActivity.this, ContohQrcodeScannerActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        //ini recyclerview
//        recDafH = findViewById(R.id.recDafH);
//        recDafH.setHasFixedSize(true);
//        recDafH.setLayoutManager(new LinearLayoutManager(this));
//
//        dataModels = new ArrayList<>();
//
//        requestQueue = Volley.newRequestQueue(this);
//
//        extractData();
//
//    }
//
//    private void extractData() {
//        String JSON_URL = "http://192.168.100.14/dbprojectkt/cari.php";
//       JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONObject>() {
//           @Override
//           public void onResponse(JSONObject response) {
//
//               try {
//                   JSONArray jsonArray = response.getJSONArray("data");
//                   for (int i = 0; i < jsonArray.length(); i++) {
//
//                           JSONObject dataObject = jsonArray.getJSONObject(i);
//
//                           String namahewan = dataObject.getString("namahewan");
//                           String jk = dataObject.getString("jk");
//                           String statushewan = dataObject.getString("statushewan");
//                           String imaghewan = dataObject.getString("imaghewan");
//
//                           dataModels.add(new DataModel(imaghewan, namahewan, jk, statushewan));
//                   }
//
//                   adapterRecItemContoh = new AdapterRecItemContoh(DaftarHewanActivity.this, dataModels);
//                   recDafH.setAdapter(adapterRecItemContoh);
//                   adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//
//               } catch (JSONException e) {
//                   e.printStackTrace();
//               }
//
//           }
//       }, new Response.ErrorListener() {
//           @Override
//           public void onErrorResponse(VolleyError error) {
//               error.printStackTrace();
//           }
//       });
//
//       requestQueue.add(request);
//    }
//
//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(this, DetailHewanActivity.class);
//        DataModel clickitm = dataModels.get(position);
//
//        intent.putExtra(EXTRA_IMG, clickitm.getImaghewan());
//        intent.putExtra(EXTRA_NAME, clickitm.getNamahewan());
//        intent.putExtra(EXTRA_STATUS, clickitm.getStatushewan());
//        intent.putExtra(EXTRA_JK, clickitm.getJk());
//
//        startActivity(intent);
//    }
//}

//PALING TERBARU





//INI BERHASIL MENGGUNAKAN PHP Untuk Databasenya

//package com.example.projectkt;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.projectkt.qrcode.AdapterRecItemContoh;
//import com.example.projectkt.qrcode.DataModel;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.auth.FirebaseAuth;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.List;
//
//public class DaftarHewanActivity extends AppCompatActivity implements AdapterRecItemContoh.OnItemClickListener {
//
//    FloatingActionButton tambahhewan;
//
//    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//    EditText kolomsearch;
//    ImageView icontgl, backbtndafhewan;
//    RelativeLayout btnsemuahewan, btnjantan, btnbetina, empty3;
//
//    TextView txtsemuahewan, txtjantan, txtbetina;
//
//    RecyclerView recDafH, recDafH2, recDafH3, recDafH4;
//    List<DataModel> dataModels;
//    List<DataModel> filterList;
//
//    private RequestQueue requestQueue;
//    private AdapterRecItemContoh adapterRecItemContoh, adapterRecItemContoh2, adapterRecItemContoh3, adapterRecItemContoh4, adapterRecItemContoh5;
//
//    public static final String EXTRA_NAME = "namahewan";
//    public static final String EXTRA_HB = "hargabeli";
//    public static final String EXTRA_IMG = "imaghewan";
//    public static final String EXTRA_JK = "jk";
//    public static final String EXTRA_STATUS = "statushewan";
//    public static final String EXTRA_ID = "id";
//    public static final String EXTRA_NOKANDANG = "nokandang";
//    public static final String EXTRA_TTL = "tanggallahir";
//    public static final String EXTRA_NPEMILIK = "namapemilik";
//    public static final String EXTRA_KATEGORI = "kategori";
//    public static final String EXTRA_KJ = "kj";
//    public static final String EXTRA_TB = "tanggalbeli";
//    public static final String EXTRA_UMUR = "umur";
//    public static final String EXTRA_BD = "belidari";
//    public static final String EXTRA_PERISTIWA = "peristiwa";
//    public static final String EXTRA_SK = "statuskesehatan";
//    public static final String EXTRA_TEMUAN = "temuan";
//    public static final String EXTRA_TREATMENT = "treatment";
//    public static final String EXTRA_HASIL = "hasil";
//
//    public static final String JSON_URL = "https://projectkawanternak.000webhostapp.com/cari.php";
//    public static final String JSON_URL2 = "https://projectkawanternak.000webhostapp.com/carisesuaiuid.php?cek=";
//    public static final String urlsearch = "https://projectkawanternak.000webhostapp.com/search.php?cek=";
//    Context context;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_daftar_hewan);
//
//        tambahhewan = findViewById(R.id.tambahhewan);
//        tambahhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DaftarHewanActivity.this, TambahHewan2.class);
//                startActivity(intent);
//            }
//        });
//
//        //ini recyclerview
//        recDafH = findViewById(R.id.recDafH);
//        recDafH2 = findViewById(R.id.recDafH2);
//        recDafH3 = findViewById(R.id.recDafH3);
//        recDafH4 = findViewById(R.id.recDafH4);
//
//        dataModels = new ArrayList<>();
//        filterList = new ArrayList<>();
//
//        txtsemuahewan = findViewById(R.id.txtsemuahewan);
//        txtjantan = findViewById(R.id.txtjantan);
//        txtbetina = findViewById(R.id.txtbetina);
//
//        empty3 = findViewById(R.id.empty3);
//
//        btnbetina = findViewById(R.id.btnbetina);
//        btnjantan = findViewById(R.id.btnjantan);
//        btnsemuahewan = findViewById(R.id.btnsemuahewan);
////
//        btnsemuahewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded));
//                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                txtsemuahewan.setTextColor(getResources().getColor(R.color.white));
//                txtjantan.setTextColor(getResources().getColor(R.color.kuning));
//                txtbetina.setTextColor(getResources().getColor(R.color.kuning));
//                getData();
//                recDafH.setVisibility(View.VISIBLE);
//                recDafH2.setVisibility(View.GONE);
//                recDafH3.setVisibility(View.GONE);
//            }
//        });
//        btnjantan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded));
//                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                txtsemuahewan.setTextColor(getResources().getColor(R.color.kuning));
//                txtjantan.setTextColor(getResources().getColor(R.color.white));
//                txtbetina.setTextColor(getResources().getColor(R.color.kuning));
//                getData2();
//                recDafH.setVisibility(View.GONE);
//                recDafH2.setVisibility(View.VISIBLE);
//                recDafH3.setVisibility(View.GONE);
//            }
//        });
//        btnbetina.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded));
//                txtsemuahewan.setTextColor(getResources().getColor(R.color.kuning));
//                txtjantan.setTextColor(getResources().getColor(R.color.kuning));
//                txtbetina.setTextColor(getResources().getColor(R.color.white));
//                getData3();
//                recDafH.setVisibility(View.GONE);
//                recDafH2.setVisibility(View.GONE);
//                recDafH3.setVisibility(View.VISIBLE);
//
//            }
//        });
//
//        kolomsearch = findViewById(R.id.kolomsearch);
//
//        icontgl = findViewById(R.id.icontgl);
//
//        kolomsearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString()!=null)
//                {
//                   getData4(s.toString());
//                }
//                else
//                {
//                    getData4("");
//                }
////                if (s.toString()!=null)
////                {
////                    recDafH.setVisibility(View.VISIBLE);
////                    recDafH2.setVisibility(View.GONE);
////                    recDafH3.setVisibility(View.GONE);
////                    recDafH4.setVisibility(View.GONE);
////                }
////                else
////                {
////
////                    recDafH.setVisibility(View.GONE);
////                    recDafH2.setVisibility(View.GONE);
////                    recDafH3.setVisibility(View.GONE);
////                    recDafH4.setVisibility(View.VISIBLE);
////                    getData4("");
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
////
////                String text = s.toString();
////                ArrayList<String> dataModels = new ArrayList<>();
////                filterList.clear();
////
//////                if (s.toString()!=null)
//////                {
//////                    recDafH.setAdapter(new AdapterRecItemContoh(getApplicationContext(),dataModels));
//////                    adapterRecItemContoh.notifyDataSetChanged();
//////                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//////                }
//////                else
//////                {
//////                    filter(s.toString());
//////                }
////                filter(s.toString());
//////                coba(s.toString());
//            }
//        });
//        backbtndafhewan = findViewById(R.id.backbtndafhewan);
//        backbtndafhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
////        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
////        layoutManager.setReverseLayout(true);
////        layoutManager.setStackFromEnd(true);
//        recDafH.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
////        recDafH.setLayoutManager(layoutManager);
////        recDafH.setHasFixedSize(true);
//
//        recDafH2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
////        recDafH2.setLayoutManager(layoutManager);
////        recDafH2.setHasFixedSize(true);
//
//
//        recDafH3.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
////        recDafH3.setLayoutManager(layoutManager);
////        recDafH3.setHasFixedSize(true);
//
//        recDafH4.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
////        recDafH4.setLayoutManager(layoutManager);
////        recDafH4.setHasFixedSize(true);
//
////        coba();
//
//        getData();
//    }
//
//    private void getData4(String text) {
//        if(text.isEmpty())
//        {
//            recDafH.setVisibility(View.VISIBLE);
//            recDafH2.setVisibility(View.GONE);
//            recDafH3.setVisibility(View.GONE);
//            recDafH4.setVisibility(View.GONE);
//            getData();
//
//        } else {
//            recDafH.setVisibility(View.GONE);
//            recDafH2.setVisibility(View.GONE);
//            recDafH3.setVisibility(View.GONE);
//            recDafH4.setVisibility(View.VISIBLE);
//            ProgressDialog progressDialog = new ProgressDialog (DaftarHewanActivity.this);
//            progressDialog.setMessage("Loading...");
//            progressDialog.show();
//
//            dataModels.clear();
//            empty3.setVisibility(View.VISIBLE);
//
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlsearch+text, null, new Response.Listener<JSONArray>() {
//                @Override
//                public void onResponse(JSONArray response) {
//
//                    for (int i = 0; i <= response.length();i++){
//                        try {
////                        String user = firebaseAuth.getUid();
////                        JSONObject jsonObject = response.getJSONObject(i);
////                        dataModels.add(new DataModel(
////                                jsonObject.getString("namahewan"),
////                                jsonObject.getString("jk"),
////                                jsonObject.getString("statushewan"),
////                                jsonObject.getString("id"),
////                                jsonObject.getString("imaghewan"),
////                                jsonObject.getString("nokandang"),
////                                jsonObject.getString("statuskesehatan")
////                        ));
//                            JSONObject dataObject = response.getJSONObject(i);
//                            String user = firebaseAuth.getUid();
//
////                        String uidcek = dataObject.getString("uid").toString();
//                            if(dataObject.getString("uid").equals(user))
//                            {
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setNamapemilik(dataObject.getString("namapemilik").toString());
//                                dataModel.setUmur(dataObject.getString("umur").toString());
//                                dataModel.setKj(dataObject.getString("kj").toString());
//                                dataModel.setTanggallahir(dataObject.getString("tanggallahir").toString());
//                                dataModel.setTanggalbeli(dataObject.getString("tanggalbeli").toString());
//                                dataModel.setHargabeli(dataObject.getString("hargabeli").toString());
//                                dataModel.setBelidari(dataObject.getString("belidari"));
//                                dataModel.setPeristiwa(dataObject.getString("peristiwa"));
//                                dataModel.setTemuan(dataObject.getString("temuan"));
//                                dataModel.setTreatment(dataObject.getString("treatment"));
//                                dataModel.setBelidari(dataObject.getString("hasil"));
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                                dataModel.setTglupload(dataObject.getString("tglupload"));
//                                dataModels.add(dataModel);
//                                empty3.setVisibility(View.GONE);
//                            } else {
//                                dataModels.clear();
//                                empty3.setVisibility(View.VISIBLE);
//                            }
//                        }catch (JSONException e){
//                            e.printStackTrace();
//                            progressDialog.dismiss();
//                        }
//                        adapterRecItemContoh = new AdapterRecItemContoh(getApplicationContext(),dataModels);
//                        recDafH4.setAdapter(adapterRecItemContoh);
//                        adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//                        adapterRecItemContoh.notifyDataSetChanged();
//                    }
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//
//                }
//            });
//
//            requestQueue.add(jsonArrayRequest);
//
//        }
//
//    }
//
//    private void getData2() {
//        ProgressDialog progressDialog = new ProgressDialog (DaftarHewanActivity.this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//
//        dataModels.clear();
//        empty3.setVisibility(View.VISIBLE);
//
//        String user = firebaseAuth.getUid().toString();
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL2+user, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i <= response.length();i++){
//                    try {
////                        String user = firebaseAuth.getUid();
////                        JSONObject jsonObject = response.getJSONObject(i);
////                        dataModels.add(new DataModel(
////                                jsonObject.getString("namahewan"),
////                                jsonObject.getString("jk"),
////                                jsonObject.getString("statushewan"),
////                                jsonObject.getString("id"),
////                                jsonObject.getString("imaghewan"),
////                                jsonObject.getString("nokandang"),
////                                jsonObject.getString("statuskesehatan")
////                        ));
//                        JSONObject dataObject = response.getJSONObject(i);
//
//                        if(dataObject.getString("jk").equals("Jantan")){
//                                DataModel dataModel = new DataModel();
//                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                                dataModel.setJk(dataObject.getString("jk").toString());
//                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                                dataModel.setNamapemilik(dataObject.getString("namapemilik").toString());
//                                dataModel.setUmur(dataObject.getString("umur").toString());
//                                dataModel.setKj(dataObject.getString("kj").toString());
//                                dataModel.setTanggallahir(dataObject.getString("tanggallahir").toString());
//                                dataModel.setTanggalbeli(dataObject.getString("tanggalbeli").toString());
//                                dataModel.setHargabeli(dataObject.getString("hargabeli").toString());
//                                dataModel.setBelidari(dataObject.getString("belidari"));
//                                dataModel.setPeristiwa(dataObject.getString("peristiwa"));
//                                dataModel.setTemuan(dataObject.getString("temuan"));
//                                dataModel.setTreatment(dataObject.getString("treatment"));
//                                dataModel.setBelidari(dataObject.getString("hasil"));
//                                dataModel.setId(dataObject.getString("id").toString());
//                                dataModel.setKategori(dataObject.getString("kategori"));
//                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                                dataModel.setNokandang(dataObject.getString("nokandang"));
//                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                            dataModel.setTglupload(dataObject.getString("tglupload"));
//                                dataModels.add(dataModel);
//                            empty3.setVisibility(View.GONE);
//                            } else{
//
//                        }
////                        String uidcek = dataObject.getString("uid").toString();
////                        if(dataObject.getString("uid").equals(user))
////                        {
////                            if(dataObject.getString("jk").equals("Jantan")){
////                                DataModel dataModel = new DataModel();
////                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
////                                dataModel.setJk(dataObject.getString("jk").toString());
////                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
////                                dataModel.setNamapemilik(dataObject.getString("namapemilik").toString());
////                                dataModel.setUmur(dataObject.getString("umur").toString());
////                                dataModel.setKj(dataObject.getString("kj").toString());
////                                dataModel.setTanggallahir(dataObject.getString("tanggallahir").toString());
////                                dataModel.setTanggalbeli(dataObject.getString("tanggalbeli").toString());
////                                dataModel.setHargabeli(dataObject.getString("hargabeli").toString());
////                                dataModel.setBelidari(dataObject.getString("belidari"));
////                                dataModel.setPeristiwa(dataObject.getString("peristiwa"));
////                                dataModel.setTemuan(dataObject.getString("temuan"));
////                                dataModel.setTreatment(dataObject.getString("treatment"));
////                                dataModel.setBelidari(dataObject.getString("hasil"));
////                                dataModel.setId(dataObject.getString("id").toString());
////                                dataModel.setKategori(dataObject.getString("kategori"));
////                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
////                                dataModel.setNokandang(dataObject.getString("nokandang"));
////                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
////                                dataModels.add(dataModel);
////                            }
////
////                        } else {
////                            dataModels.clear();
////                        }
//                    }catch (JSONException e){
//                        e.printStackTrace();
//                        progressDialog.dismiss();
//                    }
//                    adapterRecItemContoh = new AdapterRecItemContoh(getApplicationContext(),dataModels);
//                    recDafH2.setAdapter(adapterRecItemContoh);
//                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//                    adapterRecItemContoh.notifyDataSetChanged();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//    }
//    private void getData3() {
//        ProgressDialog progressDialog = new ProgressDialog (DaftarHewanActivity.this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//        dataModels.clear();
//        empty3.setVisibility(View.VISIBLE);
//
//        String user = firebaseAuth.getUid();
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL2+user, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i <= response.length();i++){
//                    try {
////                        String user = firebaseAuth.getUid();
////                        JSONObject jsonObject = response.getJSONObject(i);
////                        dataModels.add(new DataModel(
////                                jsonObject.getString("namahewan"),
////                                jsonObject.getString("jk"),
////                                jsonObject.getString("statushewan"),
////                                jsonObject.getString("id"),
////                                jsonObject.getString("imaghewan"),
////                                jsonObject.getString("nokandang"),
////                                jsonObject.getString("statuskesehatan")
////                        ));
//                        JSONObject dataObject = response.getJSONObject(i);
//
//                        if(dataObject.getString("jk").equals("Betina")){
//                            DataModel dataModel = new DataModel();
//                            dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                            dataModel.setJk(dataObject.getString("jk").toString());
//                            dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                            dataModel.setNamapemilik(dataObject.getString("namapemilik").toString());
//                            dataModel.setUmur(dataObject.getString("umur").toString());
//                            dataModel.setKj(dataObject.getString("kj").toString());
//                            dataModel.setTanggallahir(dataObject.getString("tanggallahir").toString());
//                            dataModel.setTanggalbeli(dataObject.getString("tanggalbeli").toString());
//                            dataModel.setHargabeli(dataObject.getString("hargabeli").toString());
//                            dataModel.setBelidari(dataObject.getString("belidari"));
//                            dataModel.setPeristiwa(dataObject.getString("peristiwa"));
//                            dataModel.setTemuan(dataObject.getString("temuan"));
//                            dataModel.setTreatment(dataObject.getString("treatment"));
//                            dataModel.setBelidari(dataObject.getString("hasil"));
//                            dataModel.setId(dataObject.getString("id").toString());
//                            dataModel.setKategori(dataObject.getString("kategori"));
//                            dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                            dataModel.setNokandang(dataObject.getString("nokandang"));
//                            dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                            dataModel.setTglupload(dataObject.getString("tglupload"));
//                            dataModels.add(dataModel);
//                            empty3.setVisibility(View.GONE);
//                        } else{
//
//                        }
////                        String uidcek = dataObject.getString("uid").toString();
////                        if(dataObject.getString("uid").equals(user))
////                        {
////                            if(dataObject.getString("jk").equals("Betina")){
////                                DataModel dataModel = new DataModel();
////                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
////                                dataModel.setJk(dataObject.getString("jk").toString());
////                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
////                                dataModel.setNamapemilik(dataObject.getString("namapemilik").toString());
////                                dataModel.setUmur(dataObject.getString("umur").toString());
////                                dataModel.setKj(dataObject.getString("kj").toString());
////                                dataModel.setTanggallahir(dataObject.getString("tanggallahir").toString());
////                                dataModel.setTanggalbeli(dataObject.getString("tanggalbeli").toString());
////                                dataModel.setHargabeli(dataObject.getString("hargabeli").toString());
////                                dataModel.setBelidari(dataObject.getString("belidari"));
////                                dataModel.setPeristiwa(dataObject.getString("peristiwa"));
////                                dataModel.setTemuan(dataObject.getString("temuan"));
////                                dataModel.setTreatment(dataObject.getString("treatment"));
////                                dataModel.setBelidari(dataObject.getString("hasil"));
////                                dataModel.setId(dataObject.getString("id").toString());
////                                dataModel.setKategori(dataObject.getString("kategori"));
////                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
////                                dataModel.setNokandang(dataObject.getString("nokandang"));
////                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
////                                dataModels.add(dataModel);
////                            }
////
////                        } else {
////                            dataModels.clear();
////                        }
//                    }catch (JSONException e){
//                        e.printStackTrace();
//                        progressDialog.dismiss();
//                    }
//                    adapterRecItemContoh = new AdapterRecItemContoh(getApplicationContext(),dataModels);
//                    recDafH3.setAdapter(adapterRecItemContoh);
//                    adapterRecItemContoh.notifyDataSetChanged();
//                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//    }
//    private void getData() {
//        ProgressDialog progressDialog = new ProgressDialog (DaftarHewanActivity.this);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//        dataModels.clear();
//
//
//        String user = firebaseAuth.getUid();
//
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL2+user, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//
//                for (int i = 0; i <= response.length();i++){
//                    try {
////                        String user = firebaseAuth.getUid();
////                        JSONObject jsonObject = response.getJSONObject(i);
////                        dataModels.add(new DataModel(
////                                jsonObject.getString("namahewan"),
////                                jsonObject.getString("jk"),
////                                jsonObject.getString("statushewan"),
////                                jsonObject.getString("id"),
////                                jsonObject.getString("imaghewan"),
////                                jsonObject.getString("nokandang"),
////                                jsonObject.getString("statuskesehatan")
////                        ));
//                        JSONObject dataObject = response.getJSONObject(i);
//                        String useri = firebaseAuth.getUid();
////                        String uidcek = dataObject.getString("uid").toString();
//                        if(dataObject.getString("uid").equals(useri))
//                        {
//                            DataModel dataModel = new DataModel();
//                            dataModel.setNamahewan(dataObject.getString("namahewan").toString());
//                            dataModel.setJk(dataObject.getString("jk").toString());
//                            dataModel.setStatushewan(dataObject.getString("statushewan").toString());
//                            dataModel.setId(dataObject.getString("id").toString());
//                            dataModel.setNamapemilik(dataObject.getString("namapemilik").toString());
//                            dataModel.setUmur(dataObject.getString("umur").toString());
//                            dataModel.setKj(dataObject.getString("kj").toString());
//                            dataModel.setTanggallahir(dataObject.getString("tanggallahir").toString());
//                            dataModel.setTanggalbeli(dataObject.getString("tanggalbeli").toString());
//                            dataModel.setHargabeli(dataObject.getString("hargabeli").toString());
//                            dataModel.setBelidari(dataObject.getString("belidari"));
//                            dataModel.setPeristiwa(dataObject.getString("peristiwa"));
//                            dataModel.setTemuan(dataObject.getString("temuan"));
//                            dataModel.setTreatment(dataObject.getString("treatment"));
//                            dataModel.setHasil(dataObject.getString("hasil"));
//                            dataModel.setKategori(dataObject.getString("kategori"));
//                            dataModel.setNokandang(dataObject.getString("nokandang"));
//                            dataModel.setImaghewan(dataObject.getString("imaghewan"));
//                            dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
//                            dataModel.setTglupload(dataObject.getString("tglupload"));
//                            dataModels.add(dataModel);
//                            empty3.setVisibility(View.GONE);
//                        } else {
//                            dataModels.clear();
//                            empty3.setVisibility(View.VISIBLE);
//                        }
//                    }catch (JSONException e){
//                        e.printStackTrace();
//                        progressDialog.dismiss();
//                    }
//                    adapterRecItemContoh = new AdapterRecItemContoh(getApplicationContext(),dataModels);
//                    recDafH.setAdapter(adapterRecItemContoh);
//                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//                    adapterRecItemContoh.notifyDataSetChanged();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//
//    }
//
//    private void filter(String txt) {
//        if(txt.isEmpty())
//        {
//            filterList.clear();
//            recDafH.setAdapter(new AdapterRecItemContoh(getApplicationContext(),dataModels));
//                    adapterRecItemContoh.notifyDataSetChanged();
//                    adapterRecItemContoh.setOnItemClickListener(DaftarHewanActivity.this);
//        } else {
////            RequestQueue requestQueue = Volley.newRequestQueue(this);
////            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlsearch+txt, null, new Response.Listener<JSONArray>() {
////                @Override
////                public void onResponse(JSONArray response) {
////
////                    for (int i = 0; i <= response.length();i++){
////                        try {
//////                        String user = firebaseAuth.getUid();
//////                        JSONObject jsonObject = response.getJSONObject(i);
//////                        dataModels.add(new DataModel(
//////                                jsonObject.getString("namahewan"),
//////                                jsonObject.getString("jk"),
//////                                jsonObject.getString("statushewan"),
//////                                jsonObject.getString("id"),
//////                                jsonObject.getString("imaghewan"),
//////                                jsonObject.getString("nokandang"),
//////                                jsonObject.getString("statuskesehatan")
//////                        ));
////                            JSONObject dataObject = response.getJSONObject(i);
////                            String user = firebaseAuth.getUid();
////
//////                        String uidcek = dataObject.getString("uid").toString();
////                            if(dataObject.getString("uid").equals(user))
////                            {
////                                DataModel dataModel = new DataModel();
////                                dataModel.setNamahewan(dataObject.getString("namahewan").toString());
////                                dataModel.setJk(dataObject.getString("jk").toString());
////                                dataModel.setStatushewan(dataObject.getString("statushewan").toString());
////                                dataModel.setId(dataObject.getString("id").toString());
////                                dataModel.setImaghewan(dataObject.getString("imaghewan"));
////                                dataModel.setNokandang(dataObject.getString("nokandang"));
////                                dataModel.setStatuskesehatan(dataObject.getString("statuskesehatan"));
////                                dataModels.add(dataModel);
////                            } else {
////
////                            }
////                        }catch (JSONException e){
////                            e.printStackTrace();
////                        }
//
////
////                    }
////
////                }
////            }, new Response.ErrorListener() {
////                @Override
////                public void onErrorResponse(VolleyError error) {
////
////                }
////            });
////
////            requestQueue.add(jsonArrayRequest);
//
//
//            adapterRecItemContoh = new AdapterRecItemContoh(getApplicationContext(),filterList);
//            recDafH.setAdapter(adapterRecItemContoh);
//            adapterRecItemContoh.notifyDataSetChanged();
//        }
//
//    }
//
//    @Override
//    public void onStart(){
//        super.onStart();
//
//    }
//    @Override
//    public void onItemClick(int position) {
//        Intent intent = new Intent(this, DetailHewanActivity.class);
//        DataModel clickitm = dataModels.get(position);
//        intent.putExtra(EXTRA_IMG, clickitm.getImaghewan());
//        intent.putExtra(EXTRA_NAME, clickitm.getNamahewan());
//        intent.putExtra(EXTRA_STATUS, clickitm.getStatushewan());
//        intent.putExtra(EXTRA_JK, clickitm.getJk());
//        intent.putExtra(EXTRA_ID, clickitm.getId());
//        intent.putExtra(EXTRA_NOKANDANG, clickitm.getNokandang());
//        intent.putExtra(EXTRA_TTL, clickitm.getTanggallahir());
//        intent.putExtra(EXTRA_NPEMILIK, clickitm.getNamapemilik());
//        intent.putExtra(EXTRA_HB, clickitm.getHargabeli());
//        intent.putExtra(EXTRA_KATEGORI, clickitm.getKategori());
//        intent.putExtra(EXTRA_KJ, clickitm.getKj());
//        intent.putExtra(EXTRA_TB, clickitm.getTanggalbeli());
//        intent.putExtra(EXTRA_UMUR, clickitm.getUmur());
//        intent.putExtra(EXTRA_BD, clickitm.getBelidari());
//        intent.putExtra(EXTRA_PERISTIWA, clickitm.getPeristiwa());
//        intent.putExtra(EXTRA_SK, clickitm.getStatuskesehatan());
//        intent.putExtra(EXTRA_TEMUAN, clickitm.getTemuan());
//        intent.putExtra(EXTRA_TREATMENT, clickitm.getTreatment());
//        intent.putExtra(EXTRA_HASIL, clickitm.getHasil());
//        startActivity(intent);
//
//    }
//
//}


//INI BARU DENGAN FIREBASE
package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.projectkt.qrcode.AdapterRecItemContoh;
import com.example.projectkt.qrcode.DataModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DaftarHewanActivity extends AppCompatActivity{

    FloatingActionButton tambahhewan;

    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    EditText kolomsearch;
    ImageView icontgl, backbtndafhewan;
    RelativeLayout btnsemuahewan, btnjantan, btnbetina, empty3;

    TextView txtsemuahewan, txtjantan, txtbetina;

    RecyclerView recDafH;
    ArrayList<FormModelHewan> formModelHewans;
    FirebaseDatabase firebaseDatabase;
    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_hewan);

        tambahhewan = findViewById(R.id.tambahhewan);
        tambahhewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DaftarHewanActivity.this, TambahHewan2.class);
                startActivity(intent);
            }
        });

        //ini recyclerview


        formModelHewans = new ArrayList<>();


        txtsemuahewan = findViewById(R.id.txtsemuahewan);
        txtjantan = findViewById(R.id.txtjantan);
        txtbetina = findViewById(R.id.txtbetina);

        empty3 = findViewById(R.id.empty3);

        btnbetina = findViewById(R.id.btnbetina);
        btnjantan = findViewById(R.id.btnjantan);
        btnsemuahewan = findViewById(R.id.btnsemuahewan);


        btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded));
        txtsemuahewan.setTextColor(getResources().getColor(R.color.white));

        btnsemuahewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded));
                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                txtsemuahewan.setTextColor(getResources().getColor(R.color.white));
                txtjantan.setTextColor(getResources().getColor(R.color.kuning));
                txtbetina.setTextColor(getResources().getColor(R.color.kuning));
                getData("");
            }
        });
        btnjantan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded));
                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                txtsemuahewan.setTextColor(getResources().getColor(R.color.kuning));
                txtjantan.setTextColor(getResources().getColor(R.color.white));
                txtbetina.setTextColor(getResources().getColor(R.color.kuning));
                getData2();

            }
        });
        btnbetina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded));
                txtsemuahewan.setTextColor(getResources().getColor(R.color.kuning));
                txtjantan.setTextColor(getResources().getColor(R.color.kuning));
                txtbetina.setTextColor(getResources().getColor(R.color.white));
                getData3();

            }
        });

        kolomsearch = findViewById(R.id.kolomsearch);

        icontgl = findViewById(R.id.icontgl);

        kolomsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString()!=null)
                {
                    getData(s.toString());
                }
                else
                {
                    getData("");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        backbtndafhewan = findViewById(R.id.backbtndafhewan);
        backbtndafhewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Recyclerview
        recDafH = (RecyclerView) findViewById(R.id.recDafH);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recDafH.setLayoutManager(layoutManager);

        //ini fungsi supaya recyclerview terbaru ada di paling atas
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        formModelHewans = new ArrayList<>();
    }

    private void getData(String data) {
        ProgressDialog progressDialog = new ProgressDialog (DaftarHewanActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        btnsemuahewan.setBackground(getResources().getDrawable(R.drawable.button_rounded));
        btnjantan.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
        btnbetina.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));

        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("HewanTernak").orderByChild("nokandang").startAt(data).endAt(data+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                progressDialog.dismiss();
                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<FormModelHewan> options =
                new FirebaseRecyclerOptions.Builder<FormModelHewan>()
                        .setQuery(query, FormModelHewan.class)
                        .build();

        FirebaseRecyclerAdapter<FormModelHewan, HolderDaftarHewan> adapter =
                new FirebaseRecyclerAdapter<FormModelHewan, HolderDaftarHewan>(options) {
                    @Override
                    protected void onBindViewHolder(@NotNull HolderDaftarHewan holder, int position, @NotNull FormModelHewan model) {
                        progressDialog.dismiss();
                        String datasakit = model.getStatuskesehatan();
                        holder.innamah.setText(model.getNamahewan());
                        holder.injk.setText(model.getJk());
                        holder.innokandang.setText(model.getNokandang());
                        holder.intglhewan.setText(model.getTglupload());
                        if (datasakit.equals("Sakit")){
                            holder.inkesehatan.setText(model.getStatuskesehatan());
                            holder.bgkesehatan.setBackgroundColor(getResources().getColor(R.color.merahpias));
                            holder.inkesehatan.setTextColor(getResources().getColor(R.color.merahmuda));
                        } else {
                            holder.inkesehatan.setText(model.getStatuskesehatan());
                            holder.bgkesehatan.setBackgroundColor(getResources().getColor(R.color.ijomudapias));
                            holder.inkesehatan.setTextColor(getResources().getColor(R.color.ijomuda));
                        }

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickHewanId = getRef(position).getKey();
                                Intent detailHewan = new Intent(DaftarHewanActivity.this, DetailHewanActivity.class);
                                detailHewan.putExtra("clickHewanId", clickHewanId);
                                startActivity(detailHewan);
                            }
                        });
                        Glide.with(holder.fotoh.getContext())
                                .load(model.getImghewan())
                                .centerCrop()
                                .placeholder(R.drawable.usepng)
                                .into(holder.fotoh);
                    }

                    @NotNull
                    @Override
                    public HolderDaftarHewan onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itecontohsql, parent, false);
                        HolderDaftarHewan holder = new HolderDaftarHewan(view);
                        return holder;
                    }
                };
        recDafH.setAdapter(adapter);
        adapter.startListening();

    }
    private void getData2() {

        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("HewanTernak").orderByChild("jk").equalTo("Jantan");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<FormModelHewan> options =
                new FirebaseRecyclerOptions.Builder<FormModelHewan>()
                        .setQuery(query, FormModelHewan.class)
                        .build();

        FirebaseRecyclerAdapter<FormModelHewan, HolderDaftarHewan> adapter =
                new FirebaseRecyclerAdapter<FormModelHewan, HolderDaftarHewan>(options) {
                    @Override
                    protected void onBindViewHolder(@NotNull HolderDaftarHewan holder, int position, @NotNull FormModelHewan model) {
                        String cekJK = model.getJk();
                        if (cekJK.equals("Jantan")){

                            holder.innamah.setText(model.getNamahewan());
                            holder.injk.setText(model.getJk());
                            holder.innokandang.setText(model.getNokandang());
                            holder.intglhewan.setText(model.getTglupload());
                            holder.itemcontoh.bringToFront();
                        } else {
                            holder.itemcontoh.setVisibility(View.GONE);
                        }

                        String datasakit = model.getStatuskesehatan();
                        if (datasakit.equals("Sakit")){
                            holder.inkesehatan.setText(model.getStatuskesehatan());
                            holder.bgkesehatan.setBackgroundColor(getResources().getColor(R.color.merahpias));
                            holder.inkesehatan.setTextColor(getResources().getColor(R.color.merahmuda));
                        } else {
                            holder.inkesehatan.setText(model.getStatuskesehatan());
                            holder.bgkesehatan.setBackgroundColor(getResources().getColor(R.color.ijomudapias));
                            holder.inkesehatan.setTextColor(getResources().getColor(R.color.ijomuda));
                        }

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickHewanId = getRef(position).getKey();

                                Intent detailHewan = new Intent(DaftarHewanActivity.this, DetailHewanActivity.class);
                                detailHewan.putExtra("clickHewanId", clickHewanId);
                                startActivity(detailHewan);
                            }
                        });
                        Glide.with(holder.fotoh.getContext())
                                .load(model.getImghewan())
                                .centerCrop()
                                .placeholder(R.drawable.usepng)
                                .into(holder.fotoh);
                    }

                    @NotNull
                    @Override
                    public HolderDaftarHewan onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itecontohsql, parent, false);
                        HolderDaftarHewan holder = new HolderDaftarHewan(view);
                        return holder;
                    }
                };
        recDafH.setAdapter(adapter);
        adapter.startListening();

    }
    private void getData3() {

        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("HewanTernak").orderByChild("jk").equalTo("Betina");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<FormModelHewan> options =
                new FirebaseRecyclerOptions.Builder<FormModelHewan>()
                        .setQuery(query, FormModelHewan.class)
                        .build();

        FirebaseRecyclerAdapter<FormModelHewan, HolderDaftarHewan> adapter =
                new FirebaseRecyclerAdapter<FormModelHewan, HolderDaftarHewan>(options) {
                    @Override
                    protected void onBindViewHolder(@NotNull HolderDaftarHewan holder, int position, @NotNull FormModelHewan model) {
                        String cekJK = model.getJk();
                        if (cekJK.equals("Betina")){

                            holder.innamah.setText(model.getNamahewan());
                            holder.injk.setText(model.getJk());
                            holder.innokandang.setText(model.getNokandang());
                            holder.intglhewan.setText(model.getTglupload());
                            holder.itemcontoh.bringToFront();
                        } else {
                            holder.itemcontoh.setVisibility(View.GONE);
                        }

                        String datasakit = model.getStatuskesehatan();
                        if (datasakit.equals("Sakit")){
                            holder.inkesehatan.setText(model.getStatuskesehatan());
                            holder.bgkesehatan.setBackgroundColor(getResources().getColor(R.color.merahpias));
                            holder.inkesehatan.setTextColor(getResources().getColor(R.color.merahmuda));
                        } else {
                            holder.inkesehatan.setText(model.getStatuskesehatan());
                            holder.bgkesehatan.setBackgroundColor(getResources().getColor(R.color.ijomudapias));
                            holder.inkesehatan.setTextColor(getResources().getColor(R.color.ijomuda));
                        }

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickHewanId = getRef(position).getKey();

                                Intent detailHewan = new Intent(DaftarHewanActivity.this, DetailHewanActivity.class);
                                detailHewan.putExtra("clickHewanId", clickHewanId);
                                startActivity(detailHewan);
                            }
                        });
                        Glide.with(holder.fotoh.getContext())
                                .load(model.getImghewan())
                                .centerCrop()
                                .placeholder(R.drawable.usepng)
                                .into(holder.fotoh);
                    }

                    @NotNull
                    @Override
                    public HolderDaftarHewan onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itecontohsql, parent, false);
                        HolderDaftarHewan holder = new HolderDaftarHewan(view);
                        return holder;
                    }
                };
        recDafH.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    public void onStart(){
        super.onStart();
        getData("");

    }


}