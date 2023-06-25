//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Locale;
//
//public class ForumDiskusiActivity extends AppCompatActivity {
//
//    RecyclerView recviewchat;
//    EditText addpesann;
//    RelativeLayout sendbtn;
//    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
//    ArrayList<dataComunication> dataComunicationList;
//    ArrayList<String> listData = new ArrayList<>();
//    ComunicationRecyclerAdapter comunicationRecyclerAdapter;
//    Context context;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forum_diskusi);
//
//        context = this;
//        addpesann = findViewById(R.id.addpesann);
//        sendbtn = findViewById(R.id.sendbtn);
//
//        sendbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputMessage();
//
//            }
//        });
//        sendDataMassageShow();
//    }
//
//    private void inputMessage() {
//        String send = addpesann.getText().toString();
//        if(!send.isEmpty()){
//            addpesann.setError("Masukan pesan");
//            addpesann.requestFocus();
//        }else {
//            String id = preferences.getKeyData(context);
//            String name = preferences.getNamaData(context);
//            Locale locale = new Locale ("in","ID");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy",locale);
//            databaseReference.child("pesan")
//                    .push()
//                    .setValue(new dataComunication(
//                            id,
//                            name,
//                            send,
//                            simpleDateFormat.format(System.currentTimeMillis()),
//                            System.currentTimeMillis(),
//                            "text"))
//            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                @Override
//                public void onSuccess(Void aVoid) {
//                    Toast.makeText(context, "Pesan Berhasil Dikirim", Toast.LENGTH_SHORT).show();
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NotNull Exception e) {
//                    Toast.makeText(context, "Pesan Gagal Dikirim", Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
//
//    private void sendDataMassageShow() {
//        databaseReference.child("pesan").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                dataComunicationList.clear();
//                listData.clear();
//                listData.add("");
//                for (DataSnapshot item : snapshot.getChildren()){
//                    dataComunication comunication = item.getValue(dataComunication.class);
//                    listData.add(comunication != null ? comunication.getTanggal() : null);
//                    dataComunicationList.add(comunication);
//                }
//                comunicationRecyclerAdapter = new ComunicationRecyclerAdapter(context,dataComunicationList, listData);
//                recviewchat.setAdapter(comunicationRecyclerAdapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
//    }
//}


//INI BERFUNNGSI
//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import androidx.appcompat.widget.SearchView;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.appcompat.widget.Toolbar;
//
//import com.bumptech.glide.Glide;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//import org.checkerframework.checker.units.qual.Mass;
//import org.jetbrains.annotations.NotNull;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Locale;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class ForumDiskusiActivity extends AppCompatActivity {
//
//    Toolbar toolbar;
//
//    RecyclerView recviewchat;
//    ComunicationRecyclerAdapter comunicationRecyclerAdapter,search;
//
//    EditText addpesann, kolomsearchchat;
//    RelativeLayout sendbtn;
//
//    //database
//    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
//    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users");
//    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Messages");
//    DatabaseReference dbartikel = FirebaseDatabase.getInstance().getReference().child("Artikel");
//    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//    FirebaseUser user = firebaseAuth.getCurrentUser();
//
//    ArrayList<Massage> list;
//    ArrayList<String> listData = new ArrayList<>();
//
////    ArrayList<String> listData = new ArrayList<>();
//
//    Context context;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_forum_diskusi);
//
//        context = this;
//        addpesann = findViewById(R.id.addpesann);
//        sendbtn = findViewById(R.id.sendbtn);
//        recviewchat = findViewById(R.id.recviewchat);
//        list = new ArrayList<>();
//        kolomsearchchat = findViewById(R.id.kolomsearchchat);
//
//        toolbar= findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");
//
//        String uid = user.getUid();
//
//        sendbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputMessage();
//
//            }
//        });
////        sendDataMassageShow();
//
//        comunicationRecyclerAdapter = new ComunicationRecyclerAdapter(this, list, listData);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
////        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recviewchat.setLayoutManager(layoutManager);
//        recviewchat.setAdapter(comunicationRecyclerAdapter);
//
//        receiveMassages();
//
//        //searchengine
//        searchchat();
//    }
//
//    private void searchchat() {
//        kolomsearchchat.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString()!=null)
//                {
//                    loadchat(s.toString());
//                }
//                else
//                {
//                    loadchat("");
//                }
//            }
//
//            private void loadchat(String data) {
//                Query query = database.orderByChild("pesan").startAt(data).endAt(data+"\uf8ff");
//                FirebaseRecyclerOptions<Massage> options =
//                        new FirebaseRecyclerOptions.Builder<Massage>()
//                                .setQuery(query, Massage.class)
//                                .build();
//                search = new ComunicationRecyclerAdapter(options);
//                search.startListening();
//                recviewchat.setAdapter(search);
//
//            }
//        });
//    }
//
//    @Override
//    protected void onStart(){
//        super.onStart();
//
////        loadData("");
//
//    }
////loadData
////    private void loadData(String data) {
////        Query query = database.orderByChild("pesan").startAt(data).endAt(data+"\uf8ff");
//////        Query query2 = databaseReference.orderByChild("eddttl").startAt(data).endAt(data+"\uf8ff");
////
////
////        FirebaseRecyclerOptions<Massage> options =
////                new FirebaseRecyclerOptions.Builder<Massage>()
////                        .setQuery(query, Massage.class)
////                        .build();
////
////        FirebaseRecyclerAdapter<Massage, ChatHolder> adapter =
////                new FirebaseRecyclerAdapter<Massage, ChatHolder>(options) {
////
////
////                    @Override
////                    protected void onBindViewHolder(@NotNull ChatHolder holder, int position, @NotNull Massage model) {
////                        holder.dari.setText(model.getDari());
////                        holder.pesan.setText(model.getPesan());
//////
////                    }
////
////
////                    @NotNull
////                    @Override
////                    public ChatHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
////                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
////                        ChatHolder holder = new ChatHolder(view);
////                        return holder;
////                    }
////                };
////
////        recviewchat.setAdapter(adapter);
////        adapter.startListening();
////
////    }
////    public static class ChatHolder extends RecyclerView.ViewHolder
////    {
////        TextView dari,pesan,waktu, textTanggal;
////        CircleImageView profiluserchat;
////        LinearLayout linear, linear2, linearTanggal;
////        CardView cardView, cardTanggal;
////
////        public ChatHolder (@NotNull View itemView) {
////            super(itemView);
////            dari = itemView.findViewById(R.id.dari);
////            pesan = itemView.findViewById(R.id.pesan);
////            waktu = itemView.findViewById(R.id.waktu);
////            profiluserchat = itemView.findViewById(R.id.profiluserchat);
////            linear = itemView.findViewById(R.id.linear2);
////            linear2 = itemView.findViewById(R.id.linear2);
////            cardView = itemView.findViewById(R.id.cardView);
////            linearTanggal = itemView.findViewById(R.id.linearTanggal);
////            cardTanggal = itemView.findViewById(R.id.cardTanggal);
////            textTanggal = itemView.findViewById(R.id.textTanggal);
////
////        }
////
////    }
//
//    private void inputMessage() {
//        String send = addpesann.getText().toString();
//        String email = user.getEmail();
//
//
//        if(TextUtils.isEmpty(send)){
//            addpesann.setError("Masukan pesan");
//            addpesann.requestFocus();
//        }else {
////            String id = preferences.getKeyData(context);
////            String name = preferences.getNamaData(context);
////            Locale locale = new Locale ("in","ID");
////            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy",locale);
//            String msg = addpesann.getText().toString();
////            String waktu = "10:00 AM";
//
//
//            Locale locale = new Locale ("in","ID");
//            String timeStamp = new SimpleDateFormat("dd/MMMM/yyyy",locale).format(Calendar.getInstance().getTime());
//
//            db.child(firebaseAuth.getUid())
//                    .addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot snapshot) {
//                            //get all info of user here from snapshot
//                            String username = ""+snapshot.child("username").getValue();
//                            String nohp = ""+snapshot.child("nohp").getValue();
//                            String timestamp = ""+snapshot.child("timestamp").getValue();
//                            String userType = ""+snapshot.child("userType").getValue();
//                            String profileimage = ""+snapshot.child("profileimage").getValue();
//                            String uid = ""+snapshot.child("uid").getValue();
//
//
//                            databaseReference.child("Messages").push().setValue(new Massage(email,username, msg, timeStamp, profileimage)).addOnCompleteListener(new OnCompleteListener<Void>() {
//                                @Override
//                                public void onComplete(@NotNull Task<Void> task) {
//                                    addpesann.setText("");
//
//                                }
//                            });
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError error) {
//
//                        }
//
//                    });
//
////                    .push()
////                    .setValue(new dataComunication(
////                            id,
////                            name,
////                            send,
////                            simpleDateFormat.format(System.currentTimeMillis()),
////                            System.currentTimeMillis(),
////                            "text"))
////                    .addOnSuccessListener(new OnSuccessListener<Void>() {
////                        @Override
////                        public void onSuccess(Void aVoid) {
////                            Toast.makeText(context, "Pesan Berhasil Dikirim", Toast.LENGTH_SHORT).show();
////                        }
////                    }).addOnFailureListener(new OnFailureListener() {
////                @Override
////                public void onFailure(@NotNull Exception e) {
////                    Toast.makeText(context, "Pesan Gagal Dikirim", Toast.LENGTH_SHORT).show();
////                }
////            });
//        }
//    }
//    private void receiveMassages() {
//        databaseReference.child("Messages").addValueEventListener(new ValueEventListener() {
//            @Override
//             public void onDataChange(@NotNull DataSnapshot snapshot) {
//                list.clear();
//                listData.clear();
//                listData.add("");
//
//                for (DataSnapshot snap : snapshot.getChildren()) {
//                    Massage massage = snap.getValue(Massage.class);
//                    list.add(massage);
//                    listData.add(massage != null ? massage.getWaktu() : null);
//                    comunicationRecyclerAdapter.notifyDataSetChanged();
//                }
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menusearch,menu);
//        MenuItem item = menu.findItem(R.id.searchID);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                mysearch(newText);
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//
//    }
//
//    private void mysearch(String str) {
//        FirebaseRecyclerOptions<Massage> options =
//                new FirebaseRecyclerOptions.Builder<Massage>()
//                        .setQuery(database.orderByChild("pesan").startAt(str).endAt(str+"\uf8ff"), Massage.class)
//                        .build();
//        search = new ComunicationRecyclerAdapter(options);
////        search.startListening();
//        recviewchat.setAdapter(search);
//    }
//
//    //    private void sendDataMassageShow() {
////        databaseReference.child("pesan").addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(@NotNull DataSnapshot snapshot) {
////                dataComunicationList.clear();
////                listData.clear();
////                listData.add("");
////                for (DataSnapshot item : snapshot.getChildren()){
////                    dataComunication comunication = item.getValue(dataComunication.class);
////                    listData.add(comunication != null ? comunication.getTanggal() : null);
////                    dataComunicationList.add(comunication);
////                }
////                comunicationRecyclerAdapter = new ComunicationRecyclerAdapter(context,dataComunicationList, listData);
////                recviewchat.setAdapter(comunicationRecyclerAdapter);
////
////            }
////
////            @Override
////            public void onCancelled(@NotNull DatabaseError error) {
////
////            }
////        });
////    }
//}

//UJICOBA
package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.checkerframework.checker.units.qual.Mass;
import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class ForumDiskusiActivity extends AppCompatActivity {

//    Toolbar toolbar;

    RecyclerView recviewchat;
    ComunicationRecyclerAdapter comunicationRecyclerAdapter,search;
    ImageView sendPicturechat, btnscrolldown,sendCamera,backbtndiskusi;

    EditText addpesann;
    RelativeLayout sendbtn;

    //database
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users");
    DatabaseReference database = FirebaseDatabase.getInstance().getReference().child("Messages");
    DatabaseReference dbartikel = FirebaseDatabase.getInstance().getReference().child("Artikel");
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();

    ArrayList<Massage> list;
    ArrayList<String> listData = new ArrayList<>();

    private String isisearch;

//    ArrayList<String> listData = new ArrayList<>();

    Context context;

    //<<<< ini untuk Gambar

    Uri capturedImageURL;
    private String file=null;

    private Uri mImageUri = null;

    private static final  int GALLERY_REQUEST =1;
    private static final int CAMERA_REQUEST_CODE=1;
    Uri uricam;

    int PERMISSION_DATA = 20;
    int ACCESS_DATA = 40;
    ProgressDialog dialog;

    Bitmap mImageUri1;
    Bitmap bitmap;
    //sampe sini >>>>


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum_diskusi);

        context = this;
        addpesann = findViewById(R.id.addpesann);
        sendbtn = findViewById(R.id.sendbtn);
        recviewchat = findViewById(R.id.recviewchat);
        list = new ArrayList<>();
//        kolomsearchchat = findViewById(R.id.kolomsearchchat);
        btnscrolldown = findViewById(R.id.btnscrolldown);
        sendCamera = findViewById(R.id.sendCamera);
        backbtndiskusi = findViewById(R.id.backbtndiskusi);

        //<<< INI UNTUK SEND GAMBAR
        dialog = new ProgressDialog(this);
        sendPicturechat = findViewById(R.id.sendPicturechat);
        sendPicturechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showImagePickerDialog();
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity)context, new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA
                    }, PERMISSION_DATA);
                }else {
                    capturedImageURL = Uri.fromFile(createImageFile());

                    Intent captureIntent = new Intent();
                    captureIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//                    captureIntent.putExtra(MediaStore.EXTRA_OUTPUT,capturedImageURL);

                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/jpeg");

                    Intent choserIntent = Intent.createChooser(intent, "Select Picture");
//                    choserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{captureIntent});
//                    choserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Parcelable[]{captureIntent});

                    startActivityForResult(choserIntent,ACCESS_DATA);


//                    startActivityForResult(Intent.createChooser(choserIntent, "Pilih gambar"), ACCESS_DATA);
                }
            }
        });
        // SAMPE SINI >>>>>

        //INI TOLLBAR SEARCH
//        toolbar= findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("");

        String uid = user.getUid();

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputMessage();

            }
        });
//        sendDataMassageShow();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recviewchat.setLayoutManager(layoutManager);
        comunicationRecyclerAdapter = new ComunicationRecyclerAdapter(this, list, listData);
        recviewchat.setAdapter(comunicationRecyclerAdapter);

        //ini fungsi untuk show/hide image with scroll
        recviewchat.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NotNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0){
                    btnscrolldown.setVisibility(View.GONE);
                }else {
                    btnscrolldown.setVisibility(View.VISIBLE);
                }
            }
        });

        //ini vutton otomatis kebawah
        btnscrolldown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recviewchat.smoothScrollToPosition(recviewchat.getAdapter().getItemCount());
            }
        });

        //searchengine
//        searchchat();

        sendCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
                //startActivityForResult(intent,CAMERA_REQUEST_CODE);

            }
        });

        backbtndiskusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ForumDiskusiActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_DATA && requestCode == Activity.RESULT_OK) {
            ActivityCompat.requestPermissions((Activity)context, new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, PERMISSION_DATA);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK){
            mImageUri = data.getData();
//            mSelectImage.setImageURI(mImageUri);
//            CropImage.activity(mImageUri)
//                    .setGuidelines(CropImageView.Guidelines.ON)
//                    .setAspectRatio(1,1)
//                    .start(this);
            mImageUri1 = (Bitmap) data.getExtras().get("data");
//            imgvontoh.setImageBitmap(mImageUri1);
            kirimImg(mImageUri1);
        }

        if(requestCode == ACCESS_DATA && resultCode == Activity.RESULT_OK){
            if(data != null){
                capturedImageURL = data.getData();
            }

            try {
                Uri uri = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),capturedImageURL);
                sendImageMassage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        }

    private void kirimImg(Bitmap mImageUri1) {
        //ini namanya
        final String nameImages = "IMG_"+ String.valueOf(System.currentTimeMillis())+".jpg";
        final String send = addpesann.getText().toString();

        StorageReference storage = FirebaseStorage.getInstance().getReference().child("Media").child("image").child(nameImages);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        mImageUri1.compress(Bitmap.CompressFormat.PNG, 50,byteArrayOutputStream);
        byte[] bytesData = byteArrayOutputStream.toByteArray();

        dialog.setCancelable(false);
        String email = user.getEmail();

        storage.putBytes(bytesData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Locale locale = new Locale ("in","ID");
                        String timeStamp = new SimpleDateFormat("dd MMMM yyyy",locale).format(Calendar.getInstance().getTime());
                        String timeStamp2 = new SimpleDateFormat("HH:mm",locale).format(Calendar.getInstance().getTime());
                        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
                        Calendar calendar = Calendar.getInstance();
                        String saveCurrentDate = currentDate.format(calendar.getTime());

                        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                        String saveCurrentTime = currentTime.format(calendar.getTime());

                        String PengeluaranRendomKey = saveCurrentDate + saveCurrentTime;
                        db.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NotNull DataSnapshot snapshot) {
                                String username = ""+snapshot.child("username").getValue();
                                String nohp = ""+snapshot.child("nohp").getValue();
                                String timestamp = ""+snapshot.child("timestamp").getValue();
                                String gender = ""+snapshot.child("gender").getValue();
                                String alamat = ""+snapshot.child("alamat").getValue();
                                String userType = ""+snapshot.child("userType").getValue();
                                String profileimage = ""+snapshot.child("profileimage").getValue();
                                String uid = ""+snapshot.child("uid").getValue();
                                database.push().setValue(new Massage(PengeluaranRendomKey, email, username, "", timeStamp, profileimage,timeStamp2,uri.toString(), "image", nohp, gender,alamat))
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                dialog.dismiss();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NotNull Exception e) {
                                        dialog.dismiss();
                                        Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NotNull DatabaseError error) {

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NotNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NotNull Exception e) {
                dialog.dismiss();
                Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NotNull UploadTask.TaskSnapshot snapshot) {
                float progress = 100.0f * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                dialog.setMessage(String.format("Upload %.2f",progress)+"%");
                dialog.show();

            }
        });
    }

    private void sendImageMassage(Bitmap bitmap) {

        SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
        Calendar calendar = Calendar.getInstance();
        String saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        String saveCurrentTime = currentTime.format(calendar.getTime());

        String PengeluaranRendomKey = saveCurrentDate + saveCurrentTime;
        final String nameImage = "IMG_"+ String.valueOf(System.currentTimeMillis())+".jpg";
        final String send = addpesann.getText().toString();

        StorageReference storage = FirebaseStorage.getInstance().getReference().child("Media").child("image").child(nameImage);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50,byteArrayOutputStream);
        byte[] bytesData = byteArrayOutputStream.toByteArray();

        dialog.setCancelable(false);
        String email = user.getEmail();

        storage.putBytes(bytesData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Locale locale = new Locale ("in","ID");
                        String timeStamp = new SimpleDateFormat("dd MMMM yyyy",locale).format(Calendar.getInstance().getTime());
                        String timeStamp2 = new SimpleDateFormat("HH:mm",locale).format(Calendar.getInstance().getTime());

                        db.child(firebaseAuth.getUid()).addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NotNull DataSnapshot snapshot) {
                                String username = ""+snapshot.child("username").getValue();
                                String nohp = ""+snapshot.child("nohp").getValue();
                                String timestamp = ""+snapshot.child("timestamp").getValue();
                                String gender = ""+snapshot.child("gender").getValue();
                                String alamat = ""+snapshot.child("alamat").getValue();
                                String userType = ""+snapshot.child("userType").getValue();
                                String profileimage = ""+snapshot.child("profileimage").getValue();
                                String uid = ""+snapshot.child("uid").getValue();
                                database.push().setValue(new Massage(PengeluaranRendomKey, email,username, "", timeStamp, profileimage,timeStamp2,uri.toString(), "image", nohp, gender,alamat))
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                dialog.dismiss();
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NotNull Exception e) {
                                        dialog.dismiss();
                                        Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }

                            @Override
                            public void onCancelled(@NotNull DatabaseError error) {

                            }
                        });
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NotNull Exception e) {
                        dialog.dismiss();
                        Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NotNull Exception e) {
                dialog.dismiss();
                Toast.makeText(context,e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NotNull UploadTask.TaskSnapshot snapshot) {
                float progress = 100.0f * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
                dialog.setMessage(String.format("Upload %.2f",progress)+"%");
                dialog.show();

            }
        });

    }

    //ini fungsi search

//    private void searchchat() {
//        kolomsearchchat.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                if(s.toString() != null){
////                    loadchat(s.toString());
////                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString()!=null)
//                {
//                    loadchat(s.toString());
//                }
//                else
//                {
////                    receiveMassages();
//                    loadchat("");
//                }
//
//            }
//
//        });
//    }
//
//    private void loadchat(String data) {
//        Query query = database.orderByChild("pesan").startAt(data).endAt(data+"\uf8ff");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                list.clear();
//                listData.clear();
//                listData.add("");
//                isisearch = kolomsearchchat.getText().toString();
//
//                for (DataSnapshot snap : snapshot.getChildren()) {
//                    Massage massage = snap.getValue(Massage.class);
//
//                    if (kolomsearchchat.getText().toString().equals(massage.getPesan())){
//
//                    }
//
//                }
//
//
//
//                }
//
//            private void dicoba() {
//                databaseReference.child("Messages").addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NotNull DataSnapshot snapshot) {
//                        list.clear();
//                        listData.clear();
//                        listData.add("");
//
//                        for (DataSnapshot snap : snapshot.getChildren()) {
//                            Massage massage = snap.getValue(Massage.class);
//                            list.add(massage);
//                            listData.add(massage != null ? massage.getWaktu() : null);
//                            comunicationRecyclerAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NotNull DatabaseError error) {
//
//                    }
//                });
//
//            }
//
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
//    }

    @Override
    protected void onStart(){
        super.onStart();

        receiveMassages();
//        loadData("");

    }
//loadData
//    private void loadData(String data) {
//        Query query = database.orderByChild("pesan").startAt(data).endAt(data+"\uf8ff");
////        Query query2 = databaseReference.orderByChild("eddttl").startAt(data).endAt(data+"\uf8ff");
//
//
//        FirebaseRecyclerOptions<Massage> options =
//                new FirebaseRecyclerOptions.Builder<Massage>()
//                        .setQuery(query, Massage.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<Massage, ChatHolder> adapter =
//                new FirebaseRecyclerAdapter<Massage, ChatHolder>(options) {
//
//
//                    @Override
//                    protected void onBindViewHolder(@NotNull ChatHolder holder, int position, @NotNull Massage model) {
//                        holder.dari.setText(model.getDari());
//                        holder.pesan.setText(model.getPesan());
////
//                    }
//
//
//                    @NotNull
//                    @Override
//                    public ChatHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
//                        ChatHolder holder = new ChatHolder(view);
//                        return holder;
//                    }
//                };
//
//        recviewchat.setAdapter(adapter);
//        adapter.startListening();
//
//    }
//    public static class ChatHolder extends RecyclerView.ViewHolder
//    {
//        TextView dari,pesan,waktu, textTanggal;
//        CircleImageView profiluserchat;
//        LinearLayout linear, linear2, linearTanggal;
//        CardView cardView, cardTanggal;
//
//        public ChatHolder (@NotNull View itemView) {
//            super(itemView);
//            dari = itemView.findViewById(R.id.dari);
//            pesan = itemView.findViewById(R.id.pesan);
//            waktu = itemView.findViewById(R.id.waktu);
//            profiluserchat = itemView.findViewById(R.id.profiluserchat);
//            linear = itemView.findViewById(R.id.linear2);
//            linear2 = itemView.findViewById(R.id.linear2);
//            cardView = itemView.findViewById(R.id.cardView);
//            linearTanggal = itemView.findViewById(R.id.linearTanggal);
//            cardTanggal = itemView.findViewById(R.id.cardTanggal);
//            textTanggal = itemView.findViewById(R.id.textTanggal);
//
//        }
//
//    }

    private void inputMessage() {
        String send = addpesann.getText().toString();
        String email = user.getEmail();


        if(TextUtils.isEmpty(send)){
            addpesann.setError("Masukan pesan");
            addpesann.requestFocus();
        }else {
//            String id = preferences.getKeyData(context);
//            String name = preferences.getNamaData(context);
//            Locale locale = new Locale ("in","ID");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy",locale);
            String msg = addpesann.getText().toString();
//            String waktu = "10:00 AM";


            Locale locale = new Locale ("in","ID");
            String timeStamp = new SimpleDateFormat("dd MMMM yyyy",locale).format(Calendar.getInstance().getTime());
            String timeStamp2 = new SimpleDateFormat("HH:mm",locale).format(Calendar.getInstance().getTime());

            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat currentDate = new SimpleDateFormat("dd-MMM-yyyy");
            String saveCurrentDate = currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            String saveCurrentTime = currentTime.format(calendar.getTime());

            String PengeluaranRendomKey = saveCurrentDate + saveCurrentTime;

            db.child(firebaseAuth.getUid())
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            //get all info of user here from snapshot
                            String username = ""+snapshot.child("username").getValue();
                            String nohp = ""+snapshot.child("nohp").getValue();
                            String timestamp = ""+snapshot.child("timestamp").getValue();
                            String gender = ""+snapshot.child("gender").getValue();
                            String alamat = ""+snapshot.child("alamat").getValue();
                            String userType = ""+snapshot.child("userType").getValue();
                            String profileimage = ""+snapshot.child("profileimage").getValue();
                            String uid = ""+snapshot.child("uid").getValue();


                            databaseReference.child("Messages").push()
                                    .setValue(new Massage(PengeluaranRendomKey, email, username, msg, timeStamp, profileimage,timeStamp2,"", "text", nohp, gender,alamat))
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NotNull Task<Void> task) {
                                    addpesann.setText("");

                                }
                            });
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {

                        }

                    });

//                    .push()
//                    .setValue(new dataComunication(
//                            id,
//                            name,
//                            send,
//                            simpleDateFormat.format(System.currentTimeMillis()),
//                            System.currentTimeMillis(),
//                            "text"))
//                    .addOnSuccessListener(new OnSuccessListener<Void>() {
//                        @Override
//                        public void onSuccess(Void aVoid) {
//                            Toast.makeText(context, "Pesan Berhasil Dikirim", Toast.LENGTH_SHORT).show();
//                        }
//                    }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NotNull Exception e) {
//                    Toast.makeText(context, "Pesan Gagal Dikirim", Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }

    public File createImageFile(){
        File imageStorageDir = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"MyApp");
//
        if(!imageStorageDir.exists()){
            imageStorageDir.mkdirs();
        }
        return new File(imageStorageDir+File.separator+"IMG_"+String.valueOf(System.currentTimeMillis())+".jpg");

//        File folder = new File("")

//        return new File(imageStorageDir.getPath() + File.separator+"IMG_"+String.valueOf(System.currentTimeMillis())+".jpg");
    }

    private void receiveMassages() {
        databaseReference.child("Messages").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                list.clear();
                listData.clear();
                listData.add("");

                for (DataSnapshot snap : snapshot.getChildren()) {
                    Massage massage = snap.getValue(Massage.class);
                    list.add(massage);
                    listData.add(massage != null ? massage.getWaktu() : null);
                    comunicationRecyclerAdapter.notifyDataSetChanged();
                }
                recviewchat.smoothScrollToPosition(recviewchat.getAdapter().getItemCount());
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menusearch,menu);
//        MenuItem item = menu.findItem(R.id.searchID);
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String newText) {
//                if (!TextUtils.isEmpty(newText.trim())){
//                    cariChat(newText);
//                } else {
//
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if (!TextUtils.isEmpty(newText.trim())){
//                    cariChat(newText);
//                } else {
//
//                }
//                return false;
//            }
//        });
//        return super.onCreateOptionsMenu(menu);
//
//    }

//    private void cariChat(String newText) {
//        databaseReference.child("Messages").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                list.clear();
//                listData.clear();
//                listData.add("");
//
//
//                for (DataSnapshot snap : snapshot.getChildren()) {
//                    Massage massage = snap.getValue(Massage.class);
//                    list.add(massage);
//                    listData.add(massage != null ? massage.getWaktu() : null);
//                    comunicationRecyclerAdapter.notifyDataSetChanged();
//                }
//
//                comunicationRecyclerAdapter = new ComunicationRecyclerAdapter(context, list, listData);
//                recviewchat.setAdapter(comunicationRecyclerAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
//    }

//    private void mysearch(String str) {
//        FirebaseRecyclerOptions<Massage> options =
//                new FirebaseRecyclerOptions.Builder<Massage>()
//                        .setQuery(database.orderByChild("pesan").startAt(str).endAt(str+"\uf8ff"), Massage.class)
//                        .build();
//        search = new ComunicationRecyclerAdapter(options);
////        search.startListening();
//        recviewchat.setAdapter(search);
//    }

    //    private void sendDataMassageShow() {
//        databaseReference.child("pesan").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                dataComunicationList.clear();
//                listData.clear();
//                listData.add("");
//                for (DataSnapshot item : snapshot.getChildren()){
//                    dataComunication comunication = item.getValue(dataComunication.class);
//                    listData.add(comunication != null ? comunication.getTanggal() : null);
//                    dataComunicationList.add(comunication);
//                }
//                comunicationRecyclerAdapter = new ComunicationRecyclerAdapter(context,dataComunicationList, listData);
//                recviewchat.setAdapter(comunicationRecyclerAdapter);
//
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
//    }
}

