package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailNotificationActivity extends AppCompatActivity {

    ImageView backbtnnotif, logonotif2, imgupload, gambarIMGH, backbtndetailfotoH;
    TextView txttglnotif2, txtjudul2, isinotif2, btnsimpanimg;
    RelativeLayout tamplateambarnotif, tamplategambarIMGH;

    private DatabaseReference NotifikasiRef;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    public static ProgressDialog dialog;

    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    private String NotifikasiID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();


        NotifikasiRef = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Notifikasi");

        backbtnnotif = (ImageView) findViewById(R.id.backbtnnotif);

        logonotif2 = (ImageView) findViewById(R.id.logonotif2);
        imgupload = (ImageView) findViewById(R.id.imgupload);
        backbtndetailfotoH = (ImageView) findViewById(R.id.backbtndetailfotoH);
        gambarIMGH = (ImageView) findViewById(R.id.gambarIMGH);
        txttglnotif2 = (TextView) findViewById(R.id.txttglnotif2);
        btnsimpanimg = (TextView) findViewById(R.id.btnsimpanimg);
        txtjudul2 = (TextView) findViewById(R.id.txtjudul2);
        isinotif2 = (TextView) findViewById(R.id.isinotif2);
        tamplateambarnotif = (RelativeLayout) findViewById(R.id.tamplateambarnotif);
        tamplategambarIMGH = (RelativeLayout) findViewById(R.id.tamplategambarIMGH);

        NotifikasiID = getIntent().getExtras().get("clickNotifId").toString();

        backbtnnotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        RetrieveNotifikasiInfro();

    }

    private void RetrieveNotifikasiInfro() {

        dialog = new ProgressDialog(this);
        dialog.setMessage("Loading");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);

        NotifikasiRef.child(NotifikasiID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                if ((snapshot.exists()) && (snapshot.hasChild("idn")))
                {
                    String id = snapshot.child("idn").getValue().toString();
                    String judul = snapshot.child("judulnotif").getValue().toString();
                    String isi = snapshot.child("isintif").getValue().toString();
                    String logo = snapshot.child("logonotif").getValue().toString();
                    String logos = snapshot.child("logonotif").getValue().toString();
                    String tgl = snapshot.child("tglnotif").getValue().toString();
                    String img = snapshot.child("imgnotifikasi").getValue().toString();

                    txtjudul2.setText(judul);
                    isinotif2.setText(isi);
//                    Picasso.get().load(logo).placeholder(R.drawable.ic_logo).into(logonotif2);
                    Glide.with(logonotif2.getContext()).load(logo).placeholder(R.drawable.ic_logo).into(logonotif2);
                    txttglnotif2.setText(tgl);

                    if (img.isEmpty())
                    {
                        tamplateambarnotif.setVisibility(View.GONE);
                    }else{
                        tamplateambarnotif.setVisibility(View.VISIBLE);
                        Glide.with(imgupload.getContext()).load(img).optionalFitCenter().placeholder(R.drawable.ic_logo).into(imgupload);
//                        Picasso.get().load(img).placeholder(R.drawable.ic_logo).into(imgupload);

                    }


                    imgupload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            tamplategambarIMGH.setVisibility(View.VISIBLE);
                            Glide.with(gambarIMGH.getContext())
                                    .load(img)
                                    .optionalFitCenter()
                                    .placeholder(R.drawable.ic_logo)
                                    .into(gambarIMGH);
                            backbtndetailfotoH.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    tamplategambarIMGH.setVisibility(View.GONE);
                                }
                            });
                            btnsimpanimg.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
//                                    String myFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
//                                  dowloadfile(DetailNotificationActivity.this,isi, ".jpg", myFilePath,img);
                                    ActivityCompat.requestPermissions(DetailNotificationActivity.this, new String[] {
                                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

                                    //ini baru
//                                    FirebaseStorage storage = FirebaseStorage.getInstance();
//                                    StorageReference storageRef = storage.getReferenceFromUrl("https://firebasestorage.googleapis.com/v0/b/imagestore-b2432.appspot.com/o/Nature.jpg?alt=media&token=07d95162-45f8-424e-9658-8f9022485930");
//
//                                    File rootPath = new File(Environment.getExternalStorageDirectory(), "downloads");
//                                    if(!rootPath.exists()) {
//                                        rootPath.mkdirs();
//                                    }
//                                    final File localFile = new File(rootPath,"anggur.png");
//                                    storageRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                                        @Override
//                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                                            Log.e("firebase ",";local tem file created  created " +localFile.toString());
//                                            //  updateDb(timestamp,localFile.toString(),position);
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NonNull Exception exception) {
//                                            Log.e("firebase ",";local tem file not created  created " +exception.toString());
//                                        }
//                                    });

                                    FirebaseStorage firSotage = FirebaseStorage.getInstance();
                                    StorageReference sRef = firSotage.getReferenceFromUrl(img);
                                    File creatRootDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString());
                                    if (!creatRootDir.exists()){
                                        creatRootDir.mkdirs();
                                    }
                                    File downloadFile = new File(creatRootDir, sRef.getName()+".png");
                                    sRef.getFile(downloadFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            dialog.dismiss();
                                            Toast.makeText(DetailNotificationActivity.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
                                            //untuk memindai file  sehingga akan muncul di galeri
                                            MediaScannerConnection.scanFile(
                                                    getApplicationContext(),
                                                    new String[]{downloadFile.getAbsolutePath()},
                                                    null,
                                                    null
                                            );
//                                            Uri imageUri = FileProvider.getUriForFile(DetailNotificationActivity.this, getPackageName() + ".fileprovider", downloadFile);
//                                            intent.setDataAndType(imageUri, "image/*");
//                                            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                                            startActivity(intent);

                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull @NotNull Exception e) {
                                            dialog.dismiss();
                                            Toast.makeText(DetailNotificationActivity.this, "Gagal Disimpan Karena:"+e.getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    }).addOnProgressListener(new OnProgressListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onProgress(@NonNull @NotNull FileDownloadTask.TaskSnapshot snapshot) {
                                            double progress = (100 * snapshot.getBytesTransferred()/ snapshot.getTotalByteCount());
                                            dialog.setMessage("Downloading"+((int) progress)+"%..");
                                            dialog.show();
                                        }
                                    });

                                }
                            });
                        }

                        private void dowloadfile(Context context, String imgname, String fileextension, String destination, String url) {
                            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                            Uri uri = Uri.parse(url);
                            DownloadManager.Request request = new DownloadManager.Request(uri);
                        }
                    });



                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
    }

    private void downloadimg() {



    }


}



//INI BARU

//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//import androidx.core.content.ContextCompat;
//
//import android.Manifest;
//import android.app.Dialog;
//import android.app.DownloadManager;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Build;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.Settings;
//import android.util.Log;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FileDownloadTask;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.OnProgressListener;
//import com.google.firebase.storage.StorageReference;
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
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.DecimalFormat;
//import java.text.DecimalFormatSymbols;
//
//public class DetailNotificationActivity extends AppCompatActivity {
//
//    ImageView backbtnnotif, logonotif2, imgupload, gambarIMGH, backbtndetailfotoH;
//    TextView txttglnotif2, txtjudul2, isinotif2, btnsimpanimg;
//    RelativeLayout tamplateambarnotif, tamplategambarIMGH;
//
//    private DatabaseReference NotifikasiRef;
//    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
//
//    public static ProgressDialog dialog;
//
//    FirebaseStorage firebaseStorage;
//    StorageReference storageReference;
//
//    private String NotifikasiID;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_detail_notification);
//
//        firebaseStorage = FirebaseStorage.getInstance();
//        storageReference = firebaseStorage.getReference();
//
//
//        NotifikasiRef = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Notifikasi");
//
//        backbtnnotif = (ImageView) findViewById(R.id.backbtnnotif);
//
//        logonotif2 = (ImageView) findViewById(R.id.logonotif2);
//        imgupload = (ImageView) findViewById(R.id.imgupload);
//        backbtndetailfotoH = (ImageView) findViewById(R.id.backbtndetailfotoH);
//        gambarIMGH = (ImageView) findViewById(R.id.gambarIMGH);
//        txttglnotif2 = (TextView) findViewById(R.id.txttglnotif2);
//        btnsimpanimg = (TextView) findViewById(R.id.btnsimpanimg);
//        txtjudul2 = (TextView) findViewById(R.id.txtjudul2);
//        isinotif2 = (TextView) findViewById(R.id.isinotif2);
//        tamplateambarnotif = (RelativeLayout) findViewById(R.id.tamplateambarnotif);
//        tamplategambarIMGH = (RelativeLayout) findViewById(R.id.tamplategambarIMGH);
//
//        NotifikasiID = getIntent().getExtras().get("clickNotifId").toString();
//
//        backbtnnotif.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//        RetrieveNotifikasiInfro();
//
//    }
//
//    private void RetrieveNotifikasiInfro() {
//
//        dialog = new ProgressDialog(this);
//        dialog.setMessage("Loading");
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//
//        NotifikasiRef.child(NotifikasiID).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                if ((snapshot.exists()) && (snapshot.hasChild("idn")))
//                {
//                    String id = snapshot.child("idn").getValue().toString();
//                    String judul = snapshot.child("judulnotif").getValue().toString();
//                    String isi = snapshot.child("isintif").getValue().toString();
//                    String logo = snapshot.child("logonotif").getValue().toString();
//                    String logos = snapshot.child("logonotif").getValue().toString();
//                    String tgl = snapshot.child("tglnotif").getValue().toString();
//                    String img = snapshot.child("imgnotifikasi").getValue().toString();
//
//                    txtjudul2.setText(judul);
//                    isinotif2.setText(isi);
////                    Picasso.get().load(logo).placeholder(R.drawable.ic_logo).into(logonotif2);
//                    Glide.with(logonotif2.getContext()).load(logo).placeholder(R.drawable.ic_logo).into(logonotif2);
//                    txttglnotif2.setText(tgl);
//
//                    if (img.isEmpty())
//                    {
//                        tamplateambarnotif.setVisibility(View.GONE);
//                    }else{
//                        tamplateambarnotif.setVisibility(View.VISIBLE);
//                        Glide.with(imgupload.getContext()).load(img).optionalFitCenter().placeholder(R.drawable.ic_logo).into(imgupload);
////                        Picasso.get().load(img).placeholder(R.drawable.ic_logo).into(imgupload);
//
//                    }
//
//
//                    imgupload.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            tamplategambarIMGH.setVisibility(View.VISIBLE);
//                            Glide.with(gambarIMGH.getContext())
//                                    .load(img)
//                                    .optionalFitCenter()
//                                    .placeholder(R.drawable.ic_logo)
//                                    .into(gambarIMGH);
//                            backbtndetailfotoH.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    tamplategambarIMGH.setVisibility(View.GONE);
//                                }
//                            });
//                            btnsimpanimg.setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
////
//                                    ActivityCompat.requestPermissions(DetailNotificationActivity.this, new String[] {
//                                            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
//
//                                    FirebaseStorage firSotage = FirebaseStorage.getInstance();
//                                    StorageReference sRef = storageReference.child("grcodes").child(img);
////                                    File localFile;
////                                    try {
////                                        localFile = File.createTempFile("gambar", "jpg");
////                                    } catch (IOException e) {
////                                        // Terjadi kesalahan saat membuat file lokal
////                                        return;
////                                    }
////                                    File downloadFile = new File(creatRootDir, sRef.getName()+".jpg");
//                                    sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            dialog.dismiss();
//                                            Toast.makeText(DetailNotificationActivity.this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show();
//                                        }
//                                    }).addOnFailureListener(new OnFailureListener() {
//                                        @Override
//                                        public void onFailure(@NotNull Exception e) {
//                                            dialog.dismiss();
//                                            Toast.makeText(DetailNotificationActivity.this, "Gagal Disimpan", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//
//                                }
//                            });
//                        }
//                    });
//
//
//
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
//    private void downloadimg() {
//
//
//
//    }
//
//
//}