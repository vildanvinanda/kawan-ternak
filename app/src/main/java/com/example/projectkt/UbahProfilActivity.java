package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class UbahProfilActivity extends AppCompatActivity {
    private static final String TAG = "PROFILE_TAG";
    RelativeLayout updateusername, updatenomor, updategender, updatealamat;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private String gender = "";
    ImageView backbtnsetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profil);

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Tolong Tunggu");
        progressDialog.setCanceledOnTouchOutside(false);

        firebaseAuth = FirebaseAuth.getInstance();
        updateusername = findViewById(R.id.updateusername);
        updatenomor = findViewById(R.id.updatenomor);
        updategender = findViewById(R.id.updategender);
        updatealamat = findViewById(R.id.updatealamat);
        backbtnsetting = findViewById(R.id.backbtnsetting);

        backbtnsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        updateusername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        UbahProfilActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.modal_ubahuser, (RelativeLayout) findViewById(R.id.bottomubahusername)
                        );
                EditText edduser = (EditText) bottomSheetView.findViewById(R.id.edduser);
//                ImageView btnclosefilter = bottomSheetView.findViewById(R.id.btnclosefilter);
                bottomSheetView.findViewById(R.id.btnclosefilter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.btnubahusername).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String username = edduser.getText().toString();

                        if (TextUtils.isEmpty(username)) {
                            edduser.setError("Username tidak boleh kosong !");
                            edduser.requestFocus();
                        } else {
                            updateUsername(username);
                        }


                    }

                    private void updateUsername(String username) {
                        String uid = firebaseAuth.getUid();

                        HashMap User = new HashMap();
                        User.put("uid",uid);
                        User.put("username", username);

                        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        reference.child(uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    bottomSheetDialog.dismiss();
                                    Toast.makeText(UbahProfilActivity.this, "Username telah diubah", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(UbahProfilActivity.this, "Gagal diubah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        updatenomor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        UbahProfilActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.modal_ubahnomor, (RelativeLayout) findViewById(R.id.bottomubahnohp)
                        );

//                ImageView btnclosefilter = bottomSheetView.findViewById(R.id.btnclosefilter);
                EditText eddnomor = (EditText) bottomSheetView.findViewById(R.id.eddnomerhp);

                bottomSheetView.findViewById(R.id.btnclosefilter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetView.findViewById(R.id.btnubahnohp).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String nomorhp = eddnomor.getText().toString();

                        if(TextUtils.isEmpty(nomorhp)){
                            eddnomor.setError("Nomor tidak boleh kosong !");
                            eddnomor.requestFocus();
                        } else if (nomorhp.length() < 12){
                            eddnomor.setError("Nomor kurang !");
                            eddnomor.requestFocus();
                        }else {
                            updateUsername(nomorhp);
                        }

                    }

                    private void updateUsername(String nomorhp) {
                        String uid = firebaseAuth.getUid();

                        HashMap User = new HashMap();
                        User.put("uid",uid);
                        User.put("nohp", nomorhp);

                        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        reference.child(uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    bottomSheetDialog.dismiss();
                                    Toast.makeText(UbahProfilActivity.this, "Nomor telah diubah", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(UbahProfilActivity.this, "Nomor gagal diubah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });
        updategender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        UbahProfilActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.modal_ubahgender, (RelativeLayout) findViewById(R.id.bottomupdateJK)
                        );

                RadioGroup radiongrup = (RadioGroup) bottomSheetView.findViewById(R.id.radiongrup);
                radiongrup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        RadioButton radio_laki = (RadioButton) bottomSheetView.findViewById(R.id.radio_laki);
                        RadioButton radio_cewe = (RadioButton) bottomSheetView.findViewById(R.id.radio_cewe);

                        String g1 = radio_laki.getText().toString();
                        String g2 = radio_cewe.getText().toString();

                        Button btnupdateJK = (Button) bottomSheetView.findViewById(R.id.btnupdatejk);
                        btnupdateJK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(radio_laki.isChecked()){
                                    updateJKL(g1);
                                } else if (radio_cewe.isChecked()){
                                    updateJKP(g2);
                                } else {
                                    Toast.makeText(UbahProfilActivity.this, "Anda belum memilih jenis kelamin", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    private void updateJKP(String g2) {
                        String uid = firebaseAuth.getUid();

                        HashMap User = new HashMap();
                        User.put("uid",uid);
                        User.put("gender", g2);

                        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        reference.child(uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    bottomSheetDialog.dismiss();
                                    Toast.makeText(UbahProfilActivity.this, "Jenis kelamin telah diubah", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(UbahProfilActivity.this, "Gagal diubah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }

                    private void updateJKL(String g1) {
                        String uid = firebaseAuth.getUid();

                        HashMap User = new HashMap();
                        User.put("uid",uid);
                        User.put("gender", g1);

                        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        reference.child(uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    bottomSheetDialog.dismiss();
                                    Toast.makeText(UbahProfilActivity.this, "Jenis kelamin telah diubah", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(UbahProfilActivity.this, "Gagal diubah", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });


//                ImageView btnclosefilter = bottomSheetView.findViewById(R.id.btnclosefilter);
                bottomSheetView.findViewById(R.id.btnclosefilter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });

                bottomSheetView.findViewById(R.id.btnupdatejk).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Toast.makeText(UbahProfilActivity.this, "Anda belum memilih jenis kelamin", Toast.LENGTH_SHORT).show();
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        updatealamat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        UbahProfilActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = LayoutInflater.from(getApplicationContext())
                        .inflate(
                                R.layout.modal_alamat, (RelativeLayout) findViewById(R.id.modalalamat)
                        );
                EditText eddalamat = (EditText) bottomSheetView.findViewById(R.id.eddalamat);
//                ImageView btnclosefilter = bottomSheetView.findViewById(R.id.btnclosefilter);
                bottomSheetView.findViewById(R.id.btnclosefilter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.btnupdatealamat).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String alamat = eddalamat.getText().toString();

                        if(TextUtils.isEmpty(alamat)){
                            eddalamat.setError("Alamat tidak boleh kosong !");
                            eddalamat.requestFocus();
                        }else {
                            updateAlamatt(alamat);
                            Toast.makeText(UbahProfilActivity.this, "Alamat telah disimpan", Toast.LENGTH_SHORT).show();
                        }

                    }

                    private void updateAlamatt(String alamat) {
                        String uid = firebaseAuth.getUid();

                        HashMap User = new HashMap();
                        User.put("uid",uid);
                        User.put("alamat", alamat);

                        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());
                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
                        reference.child(uid).updateChildren(User).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NotNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    bottomSheetDialog.dismiss();
                                    Toast.makeText(UbahProfilActivity.this, "Nomor telah diubah", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(UbahProfilActivity.this, "Nomor gagal diubah", Toast.LENGTH_SHORT).show();
                                    
                                }
                            }
                        });
                    }
                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

    }


}