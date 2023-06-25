package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class SettingActivityMenu extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth mAuth;
    private static final String TAG = "PROFILE_TAG";
    private ProgressDialog progressDialog;
    ImageView rowright,rowright2,rowright3,rowright4,rowright6,backbtnsetting;
    TextView ketvapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_menu);

        final String no_admin = "+6282127602881";
        final String text = "Hello";

        rowright = findViewById(R.id.rowright);
        rowright2 = findViewById(R.id.rowright2);
        rowright3 = findViewById(R.id.rowright3);
        rowright4 = findViewById(R.id.rowright4);
        rowright6 = findViewById(R.id.rowright6);
        backbtnsetting = findViewById(R.id.backbtnsetting);
        ketvapp = findViewById(R.id.ketvapp);

        firebaseAuth = FirebaseAuth.getInstance();

        //ini tombol ubah password
        rowright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        SettingActivityMenu.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.modal_ubahpass, (RelativeLayout) findViewById(R.id.bottomubahpassword)
                        );

                EditText eddpassebelum = (EditText) bottomSheetView.findViewById(R.id.eddpassebelum);
                EditText eddpassesudah = (EditText) bottomSheetView.findViewById(R.id.eddpassesudah);

                bottomSheetView.findViewById(R.id.btnclosemodal).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetView.findViewById(R.id.btnubahpass).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String oldpassword = eddpassebelum.getText().toString().trim();
                        String newpassword = eddpassesudah.getText().toString().trim();

                        if (TextUtils.isEmpty(oldpassword) && TextUtils.isEmpty(newpassword)){
                            eddpassebelum.requestFocus();
                            eddpassebelum.setError("Tidak boleh kosong !");
                            eddpassesudah.requestFocus();
                            eddpassesudah.setError("Tidak boleh kosong !");
                        }else if(TextUtils.isEmpty(oldpassword)){
                            eddpassebelum.requestFocus();
                            eddpassebelum.setError("Tolong isi password lama anda !");
                        }else if (newpassword.length()<6){
                            eddpassesudah.requestFocus();
                            eddpassesudah.setError("Minimal 6 hurup/angka");
                        } else {
                            updatePassword(oldpassword, newpassword);
                        }
                    }

                    private void updatePassword(String oldpassword, String newpassword) {
                        //get current user
                        final FirebaseUser user = firebaseAuth.getCurrentUser();

                        //before changing password re-autenticate the user
                        AuthCredential authCredential = EmailAuthProvider.getCredential(user.getEmail(), oldpassword);
                        user.reauthenticate(authCredential).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                user.updatePassword(newpassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        bottomSheetDialog.dismiss();
                                        Toast.makeText(SettingActivityMenu.this, "Password Updated..", Toast.LENGTH_SHORT).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NotNull Exception e) {
                                        Toast.makeText(SettingActivityMenu.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NotNull Exception e) {
                                Toast.makeText(SettingActivityMenu.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });


                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        //ini tombol ubah profil
        rowright2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingActivityMenu.this, UbahProfilActivity.class);
                startActivity(intent);
            }
        });

        //ini tombol kembali
        backbtnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //ini tombol hubungi kami Whatssapp
        rowright3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingActivityMenu.this, HubungiKamiActivity.class);
                startActivity(intent);
//                boolean installed = isAppInstalled("com.whatsapp");
//
//                if(installed){
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+no_admin+"&text="+text));
//                    startActivity(intent);
//                }
//                Toast.makeText(SettingActivityMenu.this, "berhasil", Toast.LENGTH_SHORT).show();
            }
        });
        rowright4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SettingActivityMenu.this, FaqActivity.class);
                startActivity(intent);

            }
        });

        //ini versi app
        versiapp();

        //signup
        rowright6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                signOutUser();
            }
        });


    }

    private void signOutUser() {
        Intent intent = new Intent(SettingActivityMenu.this, LoginActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void versiapp() {
        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("VersiApp");
        reference
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //get all info of user here from snapshot
                        String versi = ""+snapshot.child("versi").getValue();

                        //set data to ui
                        ketvapp.setText(versi);

                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
    }

    private boolean isAppInstalled(String s) {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;
        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            is_installed = true;
        } catch (PackageManager.NameNotFoundException e){
            is_installed = false;
            e.printStackTrace();
        }
        return is_installed;
    }
}