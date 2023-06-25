package com.example.projectkt;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projectkt.databinding.ActivityProfilBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;


public class ProfileFragment extends Fragment {


    private static final String TAG = "PROFILE_TAG";
    User userss;

    //firebase auth
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    String imgChatID;

    ProfileFragment context;

    TextView txtuserprof, txteamil,txtnohp,txtgender, isiAlamat;
    ImageView imgpp,btnsetting;
    CardView lengkapi, lengkapi2;
    FloatingActionButton gantipp;
    DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Users");

    StorageReference storageReference = FirebaseStorage.getInstance().getReference().child("Profle Pic");
    private Uri imageUri;
    private String myUri = "";
    private StorageTask uploadTask;


    //<<<< ini untuk Gambar
    Context contextt;


    Uri capturedImgURL;
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
    //sampe sini

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        context = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_profile, container, false);
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

//        checkUser();

        txtuserprof = (TextView) view.findViewById(R.id.txtuserprof);
        txteamil = (TextView) view.findViewById(R.id.txteamil);
        txtnohp = (TextView) view.findViewById(R.id.txtnohp);
        txtgender = (TextView) view.findViewById(R.id.txtgender);
        isiAlamat = (TextView) view.findViewById(R.id.isiAlamat);
        imgpp = (ImageView) view.findViewById(R.id.imgpp);
        btnsetting = (ImageView) view.findViewById(R.id.btnsetting);
        lengkapi = (CardView) view.findViewById(R.id.lengkapi);
        lengkapi2 = (CardView) view.findViewById(R.id.lengkapi2);
        gantipp = (FloatingActionButton) view.findViewById(R.id.gantipp);

        loadUserInfo();

        btnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivityMenu.class);
                startActivity(intent);
            }
        });

        gantipp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((FragmentActivity)getContext(), new String[]{
                            Manifest.permission.WRITE_EXTERNAL_STORAGE
                    }, PERMISSION_DATA);
                }else {
                    capturedImgURL = Uri.fromFile(createImageFile());

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

                }




            }

        });

        String uid = firebaseAuth.getUid();

        return view;
    }
    //Ini adalah req galeri terupdate
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if (requestCode == PERMISSION_DATA && requestCode == RESULT_OK) {
//            ActivityCompat.requestPermissions((FragmentActivity)getContext(), new String[]{
//                    Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSION_DATA);
//        }
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ACCESS_DATA && resultCode == RESULT_OK){
            if(data != null){
                capturedImgURL = data.getData();
            }

            try {
                Uri uri = data.getData();
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getApplicationContext().getContentResolver(),capturedImgURL);
                sendImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    private void sendImage(Bitmap bitmap) {
        final String nameImage = "IMG_"+ String.valueOf(System.currentTimeMillis())+".jpg";

        StorageReference storage = FirebaseStorage.getInstance().getReference().child("Media").child("imgprofil").child(nameImage);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 50,byteArrayOutputStream);
        byte[] bytesData = byteArrayOutputStream.toByteArray();

//        dialog.setCancelable(false);


        storage.putBytes(bytesData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                storage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {

                        String urri = uri.toString();
                        String uid = firebaseAuth.getUid();
                        Locale locale = new Locale ("in","ID");
                        String timeStamp = new SimpleDateFormat("dd MMMM yyyy",locale).format(Calendar.getInstance().getTime());
                        String timeStamp2 = new SimpleDateFormat("HH:mm",locale).format(Calendar.getInstance().getTime());

                        HashMap User = new HashMap();
                        User.put("uid", uid);
                        User.put("profileimage",urri);

                        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        reference.child(uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getContext(), "PP telah diubah", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(getContext(), "Gagal diubah", Toast.LENGTH_SHORT).show();
                                }
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
//                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onProgress(@NotNull UploadTask.TaskSnapshot snapshot) {
//                float progress = 100.0f * snapshot.getBytesTransferred() / snapshot.getTotalByteCount();
//                dialog.setMessage(String.format("Upload %.2f",progress)+"%");
//                dialog.show();
//
//            }
//        });
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

    private void loadUserInfo() {
        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //get all info of user here from snapshot
                        String email = ""+snapshot.child("email").getValue();
                        String username = ""+snapshot.child("username").getValue();
                        String nohp = ""+snapshot.child("nohp").getValue();
                        String gender = ""+snapshot.child("gender").getValue();
                        String alamat = ""+snapshot.child("alamat").getValue();
                        String timestamp = ""+snapshot.child("timestamp").getValue();
                        String userType = ""+snapshot.child("userType").getValue();
//                        String profileimage = ""+snapshot.child("profileimage").getValue();
                        String uid = ""+snapshot.child("uid").getValue();
//                        Log.d("TAG", profileimage + " / " + uid);

                        String profileimage = snapshot.child("profileimage").getValue(String.class);

                        //format data to dd/MM/yyyy


                        //set data to ui
                        txtuserprof.setText(username);
                        txteamil.setText(email);
                        txtnohp.setText(nohp);
                        txtgender.setText(gender);
                        isiAlamat.setText(alamat);
                        Glide.with(context)
                                .load(profileimage)
                                .centerCrop()
                                .into(imgpp);


                        String tbllengkapi = txtgender.getText().toString();
                        String tbllengkapi2 = isiAlamat.getText().toString();

                        if (tbllengkapi.equals("-"))
                        {
                            lengkapi.setVisibility(View.VISIBLE);
                        } else {
                            lengkapi.setVisibility(View.GONE);
                        }
                        if (tbllengkapi2.equals("-"))
                        {
                            lengkapi2.setVisibility(View.VISIBLE);
                        } else {
                            lengkapi2.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
    }
}