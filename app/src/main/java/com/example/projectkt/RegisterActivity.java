package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectkt.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity{


    String saveCurrentDate, saveCurrentTime, tglnotifikasi;

    //view binding
    private ActivityRegisterBinding binding;

    //firebase auth
    private FirebaseAuth firebaseAuth;
    //Progress dialog
    private ProgressDialog progressDialog;

    boolean addpassVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //setup firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        //setup progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Tolong Tunggu");
        progressDialog.setCanceledOnTouchOutside(false);

        //inisialiasi variabel
//        addemail = findViewById(R.id.addemail);
//        addhp =findViewById(R.id.addhp);
//        addpas2 =findViewById(R.id.addpas2);
//        adduser2 =findViewById(R.id.adduser2);
//        verifpass =findViewById(R.id.verifpass);

        //handle click, go back to login
        binding.addmasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        //handle click, begin register
        binding.btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });


        binding.addpas2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        binding.addpas2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX()>=binding.addpas2.getRight()-binding.addpas2.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection=binding.addpas2.getSelectionEnd();
                        if(addpassVisible)
                        {
                            //set drawable image here
                            binding.addpas2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lockkey,0, R.drawable.ic_no_see, 0);
                            //for hide password
                            binding.addpas2.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            addpassVisible = false;
                        } else {
                            //set drawable image here
                            binding.addpas2.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lockkey,0, R.drawable.ic_see, 0);
                            //for show password
                            binding.addpas2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            addpassVisible = true;
                        }
                        binding.addpas2.setSelection(selection);
                        return  true;
                    }
                }

                return false;
            }
        });

        binding.verifpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        binding.verifpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX()>=binding.verifpass.getRight()-binding.verifpass.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection=binding.verifpass.getSelectionEnd();
                        if(addpassVisible)
                        {
                            //set drawable image here
                            binding.verifpass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lockkey,0, R.drawable.ic_no_see, 0);
                            //for hide password
                            binding.verifpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            addpassVisible = false;
                        } else {
                            //set drawable image here
                            binding.verifpass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lockkey,0, R.drawable.ic_see, 0);
                            //for show password
                            binding.verifpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            addpassVisible = true;
                        }
                        binding.verifpass.setSelection(selection);
                        return  true;
                    }
                }

                return false;
            }
        });

    }

    private String username="",email="",nohp="",password="",profileimage="";
    private void validateData() {
        //before creating account, lets do some data validate

        //get data
        username = binding.adduser2.getText().toString().trim();
        email = binding.addemail.getText().toString().trim();
        nohp = binding.addhp.getText().toString().trim();
        password = binding.addpas2.getText().toString().trim();
        String verifpassword = binding.verifpass.getText().toString().trim();

        //validate data
        if (TextUtils.isEmpty(username) && !Patterns.EMAIL_ADDRESS.matcher(email).matches() && TextUtils.isEmpty(nohp) && TextUtils.isEmpty(password)){
            Toast.makeText(this, "Tolong Dilengkapi.!", Toast.LENGTH_SHORT).show();
            binding.adduser2.setError("Tolong isi.!!");
            binding.adduser2.requestFocus();
            binding.addemail.setError("Tolong isi.!!");
            binding.addemail.requestFocus();
            binding.addhp.setError("Tolong isi.!!");
            binding.addhp.requestFocus();
            binding.addpas2.setError("Tolong isi.!!");
            binding.addpas2.requestFocus();
        }else if (TextUtils.isEmpty(username)){
            binding.adduser2.setError("Tolong isi username anda !");
            binding.adduser2.requestFocus();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.addemail.setError("Tolong isi email anda !");
            binding.addemail.requestFocus();
        } else if (TextUtils.isEmpty(nohp)){
            binding.addhp.setError("Tolong isi no hp anda !");
            binding.addhp.requestFocus();
        } else if (nohp.length() > 13){
            binding.addhp.setError("No.Hp max 13 angka !");
            binding.addhp.requestFocus();
        }  else if (nohp.length() < 12){
            binding.addhp.setError("No.Hp anda kurang !");
            binding.addhp.requestFocus();
        } else if (TextUtils.isEmpty(password)){
            binding.addpas2.setError("Tolong isi password anda !");
            binding.addpas2.requestFocus();
        }else if (password.length() < 6 ){
            binding.addpas2.setError("Inputan kurang !");
            binding.addpas2.requestFocus();
        } else if (TextUtils.isEmpty(verifpassword)){
            binding.verifpass.setError("Tolong isi verifpassword anda !");
            binding.verifpass.requestFocus();
        }else if (!password.equals(verifpassword)){
            Toast.makeText(this, "Maaf password tidak sesuai !",Toast.LENGTH_SHORT).show();
            binding.verifpass.setError("Tolong di Isi.!!");
            binding.verifpass.requestFocus();
            binding.verifpass.getText().clear();
            binding.addpas2.getText().clear();
        }else {
            createUserAccount();
        }


    }

    private void createUserAccount() {
        //show progress dialog
        progressDialog.setMessage("Pembuatan Akun....");
        progressDialog.show();

        //create user firebase auth
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //account creating success, now add in firebase realtime database
                        updateUserInfo();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //account creating failed
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void updateUserInfo() {
        progressDialog.setMessage("Saving User Info...");

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MMM/yyyy");
        saveCurrentDate = currentDate.format(calendar.getTime());


        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calendar.getTime());

        tglnotifikasi = saveCurrentDate + saveCurrentTime;

        //timestrap
        long timestamp = System.currentTimeMillis();

        //get current user uid, since user is registered so we can get now
        String uid = firebaseAuth.getUid();

        //setup data to add in db
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("uid",uid);
        hashMap.put("email",email);
        hashMap.put("username",username);
        hashMap.put("nohp",nohp);
        hashMap.put("profileimage",""); //add empty, will do leter
        hashMap.put("userType", "user"); // possible value are user, admin: will make admin manually in firebase realtime database by changing this value
        hashMap.put("timestamp", timestamp);
        hashMap.put("gender", "-");
        hashMap.put("alamat", "-");

        HashMap<String, Object> hashMap2 = new HashMap<>();
        hashMap2.put("idn",tglnotifikasi);
        hashMap2.put("judulnotif","Admin Kawan Ternak");
        hashMap2.put("logonotif","");
        hashMap2.put("isintif","Selamat saat ini anda telah bergabung dengan aplikasi Kawan Ternak");
        hashMap2.put("tglnotif", saveCurrentDate);
        hashMap2.put("imgnotifikasi", "");

        //set data db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
//        databaseReference = database.getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan");
        ref.child(uid)
                .setValue(hashMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                //data added to db
                progressDialog.dismiss();
                Toast.makeText(RegisterActivity.this, "Akun Sedang dibuat", Toast.LENGTH_SHORT).show();
                //since user akun is created so stat dasboard of user
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
                finish();
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //data failed adding to db
                        progressDialog.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        DatabaseReference notif = FirebaseDatabase.getInstance().getReference("Users").child(uid);
        notif.child("Notifikasi").child("01:").setValue(hashMap2).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                progressDialog.dismiss();

            }
        });
    }


}


