//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Patterns;
//import android.view.View;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.projectkt.databinding.ActivityLoginBinding;
//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//
//public class LoginActivity extends AppCompatActivity{
//
//    //firebase auth
//    private FirebaseAuth firebaseAuth;
//
//    //Progress dialog
//    private ProgressDialog progressDialog;
//
//    private ActivityLoginBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityLoginBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        //setup firebase auth
//        firebaseAuth = FirebaseAuth.getInstance();
//
//        //setup progress dialog
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setTitle("Tolong Tunggu");
//        progressDialog.setCanceledOnTouchOutside(false);
//
//        //handller click, go to register
//        binding.adddaf.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//            }
//        });
//
//        binding.btnlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                validteData();
//            }
//        });
//
//    }
//
//    private String email="", password="";
//    private void validteData() {
//        //before login, lets do some data validation
//
//        //get data
//        email = binding.addemail2.getText().toString().trim();
//        password = binding.addpass.getText().toString().trim();
//
//        //validate data
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            binding.addemail2.setError("Tolong di Isi..!!");
//            binding.addemail2.requestFocus();
//        } else if (TextUtils.isEmpty(password)){
//            binding.addpass.setError("Tolong di Isi..!!");
//            binding.addpass.requestFocus();
//        } else {
//            //data is validation, begin login
//            loginUser();
//        }
//    }
//
//    private void loginUser() {
//        //show progress
//        progressDialog.setMessage("Sedang Masuk Kedalam Aplikasi...");
//        progressDialog.show();
//
//        //login user
//        firebaseAuth.signInWithEmailAndPassword(email, password)
//                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                    @Override
//                    public void onSuccess(AuthResult authResult) {
//                        //login success, check if user is user or admin
//                        checkUser();
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(Exception e) {
//                        //login failed
//                        progressDialog.dismiss();
//                        Toast.makeText(LoginActivity.this, "gagal masuk", Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    private void checkUser() {
//        //check if user or admin from realtime database
//        //get curren user
//        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
//
//        //check in db
//        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
//        ref.child(firebaseUser.getUid())
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(DataSnapshot snapshot) {
//                        progressDialog.dismiss();
//                        //get user type
//                        String userType = ""+snapshot.child("userType").getValue();
//
//                        //check user type
//                        if (userType.equals("user")){
//                            //this is simple user, open user profile
//                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//                            finish();
//                        }
//                        else if (userType.equals("admin")){
//                            //this is admin, open admin profile
//                            startActivity(new Intent(LoginActivity.this, ProfilActivityAdmin.class));
//                            finish();
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(DatabaseError error) {
//
//                    }
//                });
//
//    }
//}

package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectkt.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class LoginActivity extends AppCompatActivity{

    //firebase auth
    private FirebaseAuth firebaseAuth;

    //Progress dialog
    private ProgressDialog progressDialog;

    boolean addpassVisible;
    TextView adddaf, btnlogin;
    EditText addemail2,addpass;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //setup firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        context = this;

        addpass = findViewById(R.id.addpass);
        addemail2 = findViewById(R.id.addemail2);
        adddaf = findViewById(R.id.adddaf);
        btnlogin = findViewById(R.id.btnlogin);

        //setup progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Tolong Tunggu");
        progressDialog.setCanceledOnTouchOutside(false);

        //handller click, go to register
        adddaf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validteData();
            }
        });

        addpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
        addpass.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                final int Right=2;
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX()>=addpass.getRight()-addpass.getCompoundDrawables()[Right].getBounds().width())
                    {
                        int selection=addpass.getSelectionEnd();
                        if(addpassVisible)
                        {
                            //set drawable image here
                            addpass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lockkey,0, R.drawable.ic_no_see, 0);
                            //for hide password
                            addpass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            addpassVisible = false;
                        } else {
                            //set drawable image here
                            addpass.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.lockkey,0, R.drawable.ic_see, 0);
                            //for show password
                            addpass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                            addpassVisible = true;
                        }
                        addpass.setSelection(selection);
                        return  true;
                    }
                }

                return false;
            }
        });


        //connection
        if(!connected(this)){
            showInternetDialog();
        }


    }

    private void showInternetDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setCancelable(false);
        View view = LayoutInflater.from(this).inflate(R.layout.modal_lost_connection, findViewById(R.id.no_internet_layout));
        view.findViewById(R.id.btn_try).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!connected(LoginActivity.this)){
                    showInternetDialog();
                } else {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
            }
        });
        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private boolean connected(LoginActivity loginActivity) {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

//        boolean connected = (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
//                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED);

        NetworkInfo wifiConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileConn = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        return(wifiConn != null && wifiConn.isConnected()) || (mobileConn != null && mobileConn.isConnected());
    }

    private String email="", password="";
    private void validteData() {
        //before login, lets do some data validation

        //get data
        email = addemail2.getText().toString().trim();
        password = addpass.getText().toString().trim();

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches() && TextUtils.isEmpty(password)){
            Toast.makeText(context, "Tolong Dilengkapi.!", Toast.LENGTH_SHORT).show();
            addemail2.setError("Tolong isi !");
            addpass.setError("Tolong isi !");
            addemail2.requestFocus();
            addpass.requestFocus();
        } else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            addemail2.setError("Tolong isi email anda !");
            addemail2.requestFocus();
        }else if (TextUtils.isEmpty(password)){
            addpass.setError("Tolong isi password anda !");
            addpass.requestFocus();
        } else {
            //data is validation, begin login
            loginUser();
        }
    }

    private void loginUser() {
        //show progress
        progressDialog.setMessage("Sedang Masuk Kedalam Aplikasi...");
        progressDialog.show();

        //login user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //login success, check if user is user or admin
                        checkUser();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        //login failed
                        progressDialog.dismiss();
                        Toast.makeText(LoginActivity.this, "gagal masuk", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void checkUser() {
        //check if user or admin from realtime database
        //get curren user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        //check in db
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Users");
        ref.child(firebaseUser.getUid())
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        progressDialog.dismiss();
                        //get user type
                        String userType = ""+snapshot.child("userType").getValue();

                        //check user type
                        if (userType.equals("user")){
                            //this is simple user, open user profile
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                            finish();
                        }
                        else if (userType.equals("admin")){
                            //this is admin, open admin profile
                            startActivity(new Intent(LoginActivity.this, ProfilActivityAdmin.class));
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });

    }

//    @Override
//    protected  void onStart(){
//        super.onStart();
//        if (firebaseAuth != null){
//            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
//            finish();
//        }
//    }
}