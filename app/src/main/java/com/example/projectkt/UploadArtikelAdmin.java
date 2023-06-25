//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.Manifest;
//import android.app.DatePickerDialog;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Calendar;
//
//public class UploadArtikelAdmin extends AppCompatActivity {
//
//    private EditText eddttl,eddTitle,eddIsiArtikel;
//    private ImageView btnttl,uploadgambarartikel;
//    private int mDate,mMonth,mYear;
//    private Button btnsimpanartikel;
//
//    Uri ImageUri;
//
//    ProgressDialog dialog;
//
//    private FirebaseDatabase database;
//    private FirebaseStorage firbaseStorage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_artikel_admin);
//
//        //menambahkan dialog proses upload
//        dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Tolong tunggu.....");
//        dialog.setCancelable(false);
//        dialog.setTitle("Artikel sedang di upload");
//        dialog.setCanceledOnTouchOutside(false);
//
//        database = FirebaseDatabase.getInstance();
//        firbaseStorage = FirebaseStorage.getInstance();
//
//        eddttl = findViewById(R.id.eddttl);
//        btnttl = findViewById(R.id.btnttl);
//        eddTitle = findViewById(R.id.eddTitle);
//        eddIsiArtikel = findViewById(R.id.eddIsiArtikel);
//        btnsimpanartikel = findViewById(R.id.btnsimpanartikel);
//
//        uploadgambarartikel = findViewById(R.id.uploadgambarartikel);
//
//        //ini adalah perintah date
//        btnttl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                mDate = calendar.get(Calendar.DATE);
//                mMonth = calendar.get(Calendar.MONTH);
//                mYear = calendar.get(Calendar.YEAR);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(UploadArtikelAdmin.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int date) {
//                        eddttl.setText(date+"-"+month+"-"+year);
//                    }
//                },mYear, mMonth, mDate);
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
//                datePickerDialog.show();
//            }
//        });
//
//        //ini adalah perintah memilih gambar
//        uploadgambarartikel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                UploadImage();
//
////                relativeLayout.setVisibility(View.VISIBLE);
////                uploadgambarartikel.setVisibility(View.GONE);
//            }
//
//        });
//
//        //ini adalah button simpan dude
//        btnsimpanartikel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.show();
//
//                final StorageReference reference = firbaseStorage.getReference().child("ArtikelKT")
//                        .child(System.currentTimeMillis()+"");
//
//                reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                ProjectModel model = new ProjectModel();
//                                model.setUploadgambarartikel(uri.toString());
//
//                                model.setEddTitle(eddTitle.getText().toString());
//                                model.setEddIsiArtikel(eddIsiArtikel.getText().toString());
//                                model.setEddttl(eddttl.getText().toString());
//
//                                database.getReference().child("ArtikelKT").push().setValue(model)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                dialog.dismiss();
//                                                Toast.makeText(UploadArtikelAdmin.this, "Artikel Sudah Disimpan", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NotNull Exception e) {
//                                        dialog.dismiss();
//                                        Toast.makeText(UploadArtikelAdmin.this, "Artikel Gafal Disimpan", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//
//                            }
//                        });
//
//                    }
//                });
//
//            }
//        });
//
//    }
//
//    private void UploadImage() {
//        Dexter.withContext(this)
//                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        Intent intent = new Intent();
//                        intent.setType("image/*");
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(intent, 101 );
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                        Toast.makeText(UploadArtikelAdmin.this, "Permission Denied", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                        permissionToken.continuePermissionRequest();
//                    }
//                }).check();
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 101 && resultCode == RESULT_OK){
//            ImageUri = data.getData();
//            uploadgambarartikel.setImageURI(ImageUri);
//        }
//
//    }
//}
//
//
//
////BARUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
//
package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class UploadArtikelAdmin extends AppCompatActivity {

    private EditText InputTTL,InputTitle,InputIsiArtikel;
    private ImageView btnttl,uploadgambarartikel;
    private int mDate,mMonth,mYear;
    private Button btnsimpanartikel;

    private static final int GalleryPick = 1;
    private String eddttl,eddTitle,eddIsiArtikel,saveCurrentDate, saveCurrentTime, ArtikelRendomKey, downloadImageUrl;

    Uri ImageUri;

    ProgressDialog dialog;

    private FirebaseDatabase database;
    private FirebaseStorage firbaseStorage;
    private StorageReference ArtikelImageRef;
    private DatabaseReference ArtikelRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_artikel_admin);

        //menambahkan dialog proses upload
        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Tolong tunggu.....");
        dialog.setCancelable(false);
        dialog.setTitle("Artikel sedang di upload");
        dialog.setCanceledOnTouchOutside(false);

        database = FirebaseDatabase.getInstance();
        firbaseStorage = FirebaseStorage.getInstance();
        ArtikelImageRef = FirebaseStorage.getInstance().getReference().child("Artikel Image");
        ArtikelRef = FirebaseDatabase.getInstance().getReference().child("Artikel");

        InputTTL = findViewById(R.id.eddttl);
        btnttl = findViewById(R.id.btnttl);
        InputTitle = findViewById(R.id.eddTitle);
        InputIsiArtikel = findViewById(R.id.eddIsiArtikel);
        btnsimpanartikel = findViewById(R.id.btnsimpanartikel);

        uploadgambarartikel = findViewById(R.id.uploadgambarartikel);

        //ini adalah perintah date
        btnttl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                mDate = calendar.get(Calendar.DATE);
                mMonth = calendar.get(Calendar.MONTH);
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = new DatePickerDialog(UploadArtikelAdmin.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int date) {
                        InputTTL.setText(date+"-"+month+"-"+year);
                    }
                },mYear, mMonth, mDate);
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                datePickerDialog.show();
            }
        });

        //ini adalah perintah memilih gambar
        uploadgambarartikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UploadImage();

//                relativeLayout.setVisibility(View.VISIBLE);
//                uploadgambarartikel.setVisibility(View.GONE);
            }

        });


        //ini adalah button simpan dude
        btnsimpanartikel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                ValidateArtikelData();

            }
        });

    }

    private void ValidateArtikelData()
    {
        eddTitle = InputTitle.getText().toString();
        eddIsiArtikel = InputIsiArtikel.getText().toString();
        eddttl = InputTTL.getText().toString();

        if (ImageUri == null)
        {
            Toast.makeText(this, "Foto belum ditambahkan..", Toast.LENGTH_SHORT).show();
        } else if (TextUtils.isEmpty(eddTitle))
        {
            InputTitle.setError("Tolong Dilengkapi");
            InputTitle.requestFocus();
        }else if (TextUtils.isEmpty(eddIsiArtikel))
        {
            InputIsiArtikel.setError("Tolong Dilengkapi");
            InputIsiArtikel.requestFocus();
        }else if (TextUtils.isEmpty(eddttl))
        {
            InputTTL.setError("Tolong Dilengkapi");
            InputTTL.requestFocus();
        }else
        {
            StoreArtikelInformation();
        }
    }

    private void StoreArtikelInformation()
    {
        dialog.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        ArtikelRendomKey = saveCurrentDate + saveCurrentTime;

        StorageReference filePath = ArtikelImageRef.child(ImageUri.getLastPathSegment() + ArtikelRendomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String massage = e.toString();
                Toast.makeText(UploadArtikelAdmin.this, "Error: " + massage, Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(UploadArtikelAdmin.this, "Artikel Image Telah di Upload", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();

                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return  filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<Uri> task) {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(UploadArtikelAdmin.this, "Artikel image save to Database Succesfully", Toast.LENGTH_SHORT).show();

                            SaveArtikelFotoDatabase();
                        }

                    }
                });
            }
        });


    }

    private void SaveArtikelFotoDatabase() {
        HashMap<String,Object> artikelMap = new HashMap<>();
        artikelMap.put("aid", ArtikelRendomKey);
        artikelMap.put("eddTitle", eddTitle);
        artikelMap.put("eddttl", eddttl);
        artikelMap.put("eddIsiArtikel", eddIsiArtikel);
        artikelMap.put("uploadgambarartikel", downloadImageUrl);

        ArtikelRef.child(ArtikelRendomKey).updateChildren(artikelMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NotNull Task<Void> task) {
                        if (task.isSuccessful())
                        {

                            Intent intent = new Intent(UploadArtikelAdmin.this, HomeActivity.class);
                            startActivity(intent);

                            dialog.dismiss();
                            Toast.makeText(UploadArtikelAdmin.this, "Artikel Telah Dibuat", Toast.LENGTH_SHORT).show();

                        }
                        else
                        {
                            dialog.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(UploadArtikelAdmin.this, "Error : " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void UploadImage()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
         startActivityForResult(galleryIntent, GalleryPick);
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GalleryPick && resultCode == RESULT_OK && data!=null){
            ImageUri = data.getData();
            uploadgambarartikel.setImageURI(ImageUri);
        }

    }
}


//BARU
//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.Manifest;
//import android.app.DatePickerDialog;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.Calendar;
//
//public class UploadArtikelAdmin extends AppCompatActivity {
//
//    private EditText eddttl,eddTitle,eddIsiArtikel;
//    private ImageView btnttl,uploadgambarartikel;
//    private int mDate,mMonth,mYear;
//    private Button btnsimpanartikel;
//
//    Uri ImageUri;
//
//    ProgressDialog dialog;
//
//    private FirebaseDatabase database;
//    private FirebaseStorage firbaseStorage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_artikel_admin);
//
//        //menambahkan dialog proses upload
//        dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Tolong tunggu.....");
//        dialog.setCancelable(false);
//        dialog.setTitle("Artikel sedang di upload");
//        dialog.setCanceledOnTouchOutside(false);
//
//        database = FirebaseDatabase.getInstance();
//        firbaseStorage = FirebaseStorage.getInstance();
//
//        eddttl = findViewById(R.id.eddttl);
//        btnttl = findViewById(R.id.btnttl);
//        eddTitle = findViewById(R.id.eddTitle);
//        eddIsiArtikel = findViewById(R.id.eddIsiArtikel);
//        btnsimpanartikel = findViewById(R.id.btnsimpanartikel);
//
//        uploadgambarartikel = findViewById(R.id.uploadgambarartikel);
//
//        //ini adalah perintah date
//        btnttl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
//                mDate = calendar.get(Calendar.DATE);
//                mMonth = calendar.get(Calendar.MONTH);
//                mYear = calendar.get(Calendar.YEAR);
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(UploadArtikelAdmin.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int date) {
//                        eddttl.setText(date+"-"+month+"-"+year);
//                    }
//                },mYear, mMonth, mDate);
//                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
//                datePickerDialog.show();
//            }
//        });
//
//        //ini adalah perintah memilih gambar
//        uploadgambarartikel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                UploadImage();
//
////                relativeLayout.setVisibility(View.VISIBLE);
////                uploadgambarartikel.setVisibility(View.GONE);
//            }
//
//        });
//
//        //ini adalah button simpan dude
//        btnsimpanartikel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                dialog.show();
//
//                final StorageReference reference = firbaseStorage.getReference().child("ArtikelKT")
//                        .child(System.currentTimeMillis()+"");
//
//                reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                    @Override
//                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                ProjectModel model = new ProjectModel();
//                                model.setUploadgambarartikel(uri.toString());
//
//                                model.setEddTitle(eddTitle.getText().toString());
//                                model.setEddIsiArtikel(eddIsiArtikel.getText().toString());
//                                model.setEddttl(eddttl.getText().toString());
//
//                                database.getReference().child("ArtikelKT").push().setValue(model)
//                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                            @Override
//                                            public void onSuccess(Void unused) {
//                                                dialog.dismiss();
//                                                Toast.makeText(UploadArtikelAdmin.this, "Artikel Sudah Disimpan", Toast.LENGTH_SHORT).show();
//                                            }
//                                        }).addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NotNull Exception e) {
//                                        dialog.dismiss();
//                                        Toast.makeText(UploadArtikelAdmin.this, "Artikel Gafal Disimpan", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//
//                            }
//                        });
//
//                    }
//                });
//
//            }
//        });
//
//    }
//
//    private void UploadImage() {
//        Dexter.withContext(this)
//                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                .withListener(new PermissionListener() {
//                    @Override
//                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                        Intent intent = new Intent();
//                        intent.setType("image/*");
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(intent, 101 );
//                    }
//
//                    @Override
//                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//                        Toast.makeText(UploadArtikelAdmin.this, "Permission Denied", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                        permissionToken.continuePermissionRequest();
//                    }
//                }).check();
//    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == 101 && resultCode == RESULT_OK){
//            ImageUri = data.getData();
//            uploadgambarartikel.setImageURI(ImageUri);
//        }
//
//    }
//}



//BARUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU

//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.DatePickerDialog;
//import android.app.ProgressDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.View;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//
//import com.google.android.gms.tasks.Continuation;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Locale;

//public class UploadArtikelAdmin extends AppCompatActivity {
//
//    private EditText InputTTL,InputTitle,InputIsiArtikel;
//    private ImageView btnttl,uploadgambarartikel;
//    private int mDate,mMonth,mYear;
//    private Button btnsimpanartikel;
//
//    private static final int GalleryPick = 1;
//    private String eddttl,eddTitle,eddIsiArtikel,saveCurrentDate, saveCurrentTime, ArtikelRendomKey, downloadImageUrl;
//
//    Uri ImageUri;
//
//    ProgressDialog dialog;
//
//    private FirebaseDatabase database;
//    private FirebaseStorage firbaseStorage;
//    private StorageReference ArtikelImageRef;
//    private DatabaseReference ArtikelRef;
//    Locale id = new Locale("in", "ID");
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyy",id);
//
//    Context context;
//    Date InputDate;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_upload_artikel_admin);
//
//        context = this;
//        //menambahkan dialog proses upload
//        dialog = new ProgressDialog(this);
//        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//        dialog.setMessage("Tolong tunggu.....");
//        dialog.setCancelable(false);
//        dialog.setTitle("Artikel sedang di upload");
//        dialog.setCanceledOnTouchOutside(false);
//
//        database = FirebaseDatabase.getInstance();
//        firbaseStorage = FirebaseStorage.getInstance();
//        ArtikelImageRef = FirebaseStorage.getInstance().getReference().child("Artikel ImageKT");
//        ArtikelRef = FirebaseDatabase.getInstance().getReference().child("ArtikelKT");
//
//        InputTTL = findViewById(R.id.eddttl);
//        btnttl = findViewById(R.id.btnttl);
//        InputTitle = findViewById(R.id.eddTitle);
//        InputIsiArtikel = findViewById(R.id.eddIsiArtikel);
//        btnsimpanartikel = findViewById(R.id.btnsimpanartikel);
//
//        uploadgambarartikel = findViewById(R.id.uploadgambarartikel);
//
//        //ini adalah perintah date
//        btnttl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final Calendar calendar = Calendar.getInstance();
////                mDate = calendar.get(Calendar.DATE);
////                mMonth = calendar.get(Calendar.MONTH);
////                mYear = calendar.get(Calendar.YEAR);
////
////                DatePickerDialog datePickerDialog = new DatePickerDialog(UploadArtikelAdmin.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
////                    @Override
////                    public void onDateSet(DatePicker view, int year, int month, int date) {
////                        InputTTL.setText(date+"-"+month+"-"+year);
////                    }
////                },mYear, mMonth, mDate);
////                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
////                datePickerDialog.show();
//
//                DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//                        calendar.set(year, month, dayOfMonth);
//                        InputTTL.setText(simpleDateFormat.format(calendar.getTime()));
//                        InputDate = calendar.getTime();
//                    }
//                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//            }
//        });
//
//        //ini adalah perintah memilih gambar
//        uploadgambarartikel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                UploadImage();
//
////                relativeLayout.setVisibility(View.VISIBLE);
////                uploadgambarartikel.setVisibility(View.GONE);
//            }
//
//        });
//
//
//        //ini adalah button simpan dude
//        btnsimpanartikel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                ValidateArtikelData();
//
//            }
//        });
//
//    }
//
//    private void ValidateArtikelData()
//    {
//        eddTitle = InputTitle.getText().toString();
//        eddIsiArtikel = InputIsiArtikel.getText().toString();
//        eddttl = InputTTL.getText().toString();
//
//        if (ImageUri == null)
//        {
//            Toast.makeText(this, "Foto belum ditambahkan..", Toast.LENGTH_SHORT).show();
//        } else if (TextUtils.isEmpty(eddTitle))
//        {
//            InputTitle.setError("Tolong Dilengkapi");
//            InputTitle.requestFocus();
//        }else if (TextUtils.isEmpty(eddIsiArtikel))
//        {
//            InputIsiArtikel.setError("Tolong Dilengkapi");
//            InputIsiArtikel.requestFocus();
//        }else if (TextUtils.isEmpty(eddttl))
//        {
//            InputTTL.setError("Tolong Dilengkapi");
//            InputTTL.requestFocus();
//        }else
//        {
//            StoreArtikelInformation();
//        }
//    }
//
//    private void StoreArtikelInformation()
//    {
//        dialog.show();
//
////        Calendar calendar = Calendar.getInstance();
//
////        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
////        saveCurrentDate = currentDate.format(calendar.getTime());
////
////        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
////        saveCurrentTime = currentTime.format(calendar.getTime());
//
//        ArtikelRendomKey = saveCurrentDate + saveCurrentTime;
//
//        StorageReference filePath = ArtikelImageRef.child(ImageUri.getLastPathSegment() + ArtikelRendomKey + ".jpg");
//
//        final UploadTask uploadTask = filePath.putFile(ImageUri);
//
//        uploadTask.addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e)
//            {
//                String massage = e.toString();
//                Toast.makeText(UploadArtikelAdmin.this, "Error: " + massage, Toast.LENGTH_SHORT).show();
//
//                dialog.dismiss();
//            }
//        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Toast.makeText(UploadArtikelAdmin.this, "Artikel Image Telah di Upload", Toast.LENGTH_SHORT).show();
//
//                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
//                    @Override
//                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
//                        if (!task.isSuccessful())
//                        {
//                            throw task.getException();
//
//                        }
//
//                        downloadImageUrl = filePath.getDownloadUrl().toString();
//                        return  filePath.getDownloadUrl();
//                    }
//                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
//                    @Override
//                    public void onComplete(@NonNull @NotNull Task<Uri> task) {
//                        if (task.isSuccessful())
//                        {
//                            downloadImageUrl = task.getResult().toString();
//
//                            Toast.makeText(UploadArtikelAdmin.this, "Artikel image save to Database Succesfully", Toast.LENGTH_SHORT).show();
//
//                            SaveArtikelFotoDatabase();
//                        }
//
//                    }
//                });
//            }
//        });
//
//
//    }
//
//    private void SaveArtikelFotoDatabase() {
//        HashMap<String,Object> artikelMap = new HashMap<>();
//        artikelMap.put("aid", ArtikelRendomKey);
//        artikelMap.put("eddTitle", eddTitle);
//        artikelMap.put("eddttl", eddttl);
//        artikelMap.put("eddIsiArtikel", eddIsiArtikel);
//        artikelMap.put("uploadgambarartikel", downloadImageUrl);
//
//        ArtikelRef.child(ArtikelRendomKey).updateChildren(artikelMap)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NotNull Task<Void> task) {
//                        if (task.isSuccessful())
//                        {
//
//                            Intent intent = new Intent(UploadArtikelAdmin.this, HomeActivity.class);
//                            startActivity(intent);
//
//                            dialog.dismiss();
//                            Toast.makeText(UploadArtikelAdmin.this, "Artikel Telah Dibuat", Toast.LENGTH_SHORT).show();
//
//                        }
//                        else
//                        {
//                            dialog.dismiss();
//                            String message = task.getException().toString();
//                            Toast.makeText(UploadArtikelAdmin.this, "Error : " + message, Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
//
//
//    private void UploadImage()
//    {
//        Intent galleryIntent = new Intent();
//        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
//        galleryIntent.setType("image/*");
//        startActivityForResult(galleryIntent, GalleryPick);
//    }
//
//
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == GalleryPick && resultCode == RESULT_OK && data!=null){
//            ImageUri = data.getData();
//            uploadgambarartikel.setImageURI(ImageUri);
//        }
//
//    }
//}