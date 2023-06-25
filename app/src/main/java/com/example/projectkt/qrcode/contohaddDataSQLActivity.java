//package com.example.projectkt;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.Manifest;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.util.Base64;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//
//
//import java.io.ByteArrayOutputStream;
//import java.io.FileNotFoundException;
//import java.io.InputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//public class contohaddDataSQLActivity extends AppCompatActivity {
//
//    ImageView imageup;
//    public static final String url = "http://192.168.100.14/bacadata/insert.php";
//    EditText innama, innomor;
//    private Button btnaddsiswa, uploadimg;
//    TextView txtoutput;
//    Bitmap imageupbit;
//    String encodedImage;
//
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contohadd_data_sqlactivity);
//
//
//        innomor = (EditText) findViewById(R.id.innomor);
//        innama = (EditText) findViewById(R.id.innama);
////        inid = (EditText) findViewById(R.id.inid);
//        txtoutput = (TextView) findViewById(R.id.txtoutput);
//
//        btnaddsiswa = (Button) findViewById(R.id.btnaddsiswa);
//        uploadimg = (Button) findViewById(R.id.uploadimg);
//
//        imageup = (ImageView) findViewById(R.id.imageup);
//
//        btnaddsiswa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputdata();
//            }
//        });
//
//        uploadimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dexter.withActivity(contohaddDataSQLActivity.this)
//                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                        .withListener(new PermissionListener() {
//                            @Override
//                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//
//                                Intent intent = new Intent(Intent.ACTION_PICK);
//                                intent.setType("image/*");
//                                startActivityForResult(Intent.createChooser(intent, "Select Image"),1);
//
//                            }
//
//                            @Override
//                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//                            }
//
//                            @Override
//                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                                permissionToken.continuePermissionRequest();
//                            }
//                        }).check();
//            }
//        });
//
//    }
//
////    private void inputdata(String nama, String nomor) {
////        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
////            @Override
////            protected String doInBackground(String... params) {
////
////                String NameHolder = nama ;
////                String EmailHolder = nomor ;
////
////                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
////
////                nameValuePairs.add(new BasicNameValuePair("name", NameHolder));
////                nameValuePairs.add(new BasicNameValuePair("email", EmailHolder));
////
////                try {
////                    HttpClient httpClient = new DefaultHttpClient();
////
////                    HttpPost httpPost = new HttpPost(ServerURL);
////
////                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
////
////                    HttpResponse httpResponse = httpClient.execute(httpPost);
////
////                    HttpEntity httpEntity = httpResponse.getEntity();
////
////
////                } catch (ClientProtocolException e) {
////
////                } catch (IOException e) {
////
////                }
////                return "Data Inserted Successfully";
////            }
////
////            @Override
////            protected void onPostExecute(String result) {
////
////                super.onPostExecute(result);
////
////                Toast.makeText(MainActivity.this, "Data Submit Successfully", Toast.LENGTH_LONG).show();
////
////            }
////        }
////
////        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
////
////        sendPostReqAsyncTask.execute(name, email);
////    }
////    }
//
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//
//        if(requestCode == 1 && resultCode == RESULT_OK && data!=null)
//        {
//            Uri filePath =  data.getData();
//            try {
//                InputStream inputStream = getContentResolver().openInputStream(filePath);
//                imageupbit = BitmapFactory.decodeStream(inputStream);
//                imageup.setImageBitmap(imageupbit);
//
//                imageStore(imageupbit);
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    private void imageStore(Bitmap imageupbit) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        imageupbit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//
//        byte[] imageBytes = stream.toByteArray();
//
//        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
//    }
//
//    private void inputdata() {
//
//        String nama = innama.getText().toString();
//        String nomor = innomor.getText().toString();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
////                        Toast.makeText(contohaddDataSQLActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
//                        txtoutput.setText(response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                txtoutput.setText("error");
////                Toast.makeText(contohaddDataSQLActivity.this, "gagal", Toast.LENGTH_SHORT).show();
//            }
//        })
//        {
//            @Override
//            protected Map<String,String> getParams() throws AuthFailureError {
////                Map<String, String> params = new HashMap<>();
//                Map<String,String> params = new HashMap<String,String>();
//
//                params.put("image", encodedImage);
//                params.put("nama", nama);
//                params.put("nomor", nomor);
//
//
//                return params;
//            }
//        };
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//    }
////    private void inputdata(final String nama, final String nomor) {
////        String nomor = innomor.getText().toString();
////        String nama = innama.getText().toString();
////
////        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
////                new Response.Listener<String>() {
////                    @Override
////                    public void onResponse(String response) {
////                        Toast.makeText(contohaddDataSQLActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
////                    }
////                }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
//////                textView.setText("That didn't work!");
////                Toast.makeText(contohaddDataSQLActivity.this, "gagal", Toast.LENGTH_SHORT).show();
////            }
////        })
////        {
////            @Override
////            protected HashMap<String,String> getParams() throws AuthFailureError {
//////                Map<String, String> params = new HashMap<>();
////                HashMap<String,String> params = new HashMap<>();
////
////                params.put("nomor", nomor);
////                params.put("nama", nama);
////
////
////                return params;
////            }
////        };
////        RequestQueue queue = Volley.newRequestQueue(this);
////        queue.add(stringRequest);
////    }
//}


//SANGAT BARU

//ini yg baru

//package com.example.projectkt;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//
//import android.Manifest;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.ProgressBar;
//import android.widget.TextView;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.DefaultRetryPolicy;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.RetryPolicy;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.karumi.dexter.Dexter;
//import com.karumi.dexter.PermissionToken;
//import com.karumi.dexter.listener.PermissionDeniedResponse;
//import com.karumi.dexter.listener.PermissionGrantedResponse;
//import com.karumi.dexter.listener.PermissionRequest;
//import com.karumi.dexter.listener.single.PermissionListener;
//
//
//import java.io.ByteArrayOutputStream;
//import java.util.HashMap;
//import java.util.Map;
//
//public class contohaddDataSQLActivity extends AppCompatActivity {
//
//    static final int REQUEST_IMAGE_CAPTURE = 1;
//    static final int REQUEST_TAKE_PHOTO = 1;
//    private static final int IMAGE_PICK_CODE = 1000;
//    private static final int PERMISSION_CODE = 1001;
//    private static final String TAG = "contoh Data";
//    String currentPhotoPath, Document_img1;
//    ImageView imageup;
//
//    public static final String url = "http://192.168.100.14/bacadata/insert.php";
//    EditText innama, innomor;
//    private Button btnaddsiswa, uploadimg;
//    TextView txtoutput;
//    Bitmap imageupbit;
//    String encodedImage;
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE)
//        {
//            Uri selectedImage = data.getData();
//            String[] filePath = { MediaStore.Images.Media.DATA };
//            Cursor c = getContentResolver().query(selectedImage, filePath, null, null,null);
//            c.moveToFirst();
//            int columnIndex = c.getColumnIndex(filePath[0]);
//            String picturePath = c.getString(columnIndex);
//            c.close();
//            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
//            thumbnail = getResizeBitmap(thumbnail, 400);
//            imageup.setImageBitmap(thumbnail);
//            BitMapToString(thumbnail);
//
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_contohadd_data_sqlactivity);
//
//
//        innomor = (EditText) findViewById(R.id.innomor);
//        innama = (EditText) findViewById(R.id.innama);
////        inid = (EditText) findViewById(R.id.inid);
//        txtoutput = (TextView) findViewById(R.id.txtoutput);
//
//        btnaddsiswa = (Button) findViewById(R.id.btnaddsiswa);
//        uploadimg = (Button) findViewById(R.id.uploadimg);
//
//        imageup = (ImageView) findViewById(R.id.imageup);
//
//        btnaddsiswa.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                inputdata();
//            }
//        });
//
//        uploadimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Dexter.withActivity(contohaddDataSQLActivity.this)
//                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
//                        .withListener(new PermissionListener() {
//                            @Override
//                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//
//                                Intent intent = new Intent(Intent.ACTION_PICK);
//                                intent.setType("image/*");
//                                startActivityForResult(intent,IMAGE_PICK_CODE);
//
//                            }
//
//                            @Override
//                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//                            }
//
//                            @Override
//                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                                permissionToken.continuePermissionRequest();
//                            }
//                        }).check();
//            }
//        });
//
//    }
//
//
//
//    private void imageStore(Bitmap imageupbit) {
//        ByteArrayOutputStream stream = new ByteArrayOutputStream();
//        imageupbit.compress(Bitmap.CompressFormat.JPEG, 100, stream);
//
//        byte[] imageBytes = stream.toByteArray();
//
//        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
//    }
//
//    private void inputdata() {
//
//        final ProgressDialog loading = new ProgressDialog(contohaddDataSQLActivity.this);
//        String nama = innama.getText().toString();
//        String nomor = innomor.getText().toString();
//        if(nama!=null)
//        {
//            loading.setMessage("Please Wait...");
//            loading.show();
//            loading.setCanceledOnTouchOutside(false);
//            RetryPolicy retryPolicy = new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
////                        Toast.makeText(contohaddDataSQLActivity.this, "berhasil", Toast.LENGTH_SHORT).show();
//                    loading.dismiss();
////                    txtoutput.setText(response);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
////                    txtoutput.setText("error");
//                    loading.dismiss();
////                Toast.makeText(contohaddDataSQLActivity.this, "gagal", Toast.LENGTH_SHORT).show();
//                }
//            })
//            {
//                @Override
//                protected Map<String,String> getParams() throws AuthFailureError {
////                Map<String, String> params = new HashMap<>();
//                    Map<String,String> params = new HashMap<String,String>();
//
//                    params.put("foto", Document_img1);
//                    params.put("nama", nama);
//                    params.put("nomor", nomor);
//
//
//                    return params;
//                }
//            };
//            RequestQueue queue = Volley.newRequestQueue(this);
//            stringRequest.setRetryPolicy(retryPolicy);
//            queue.add(stringRequest);
//        }
//
//
//    }
//
//    //ini adalah perintah untuk me resize image supaya imagenya itu rata kanann kiri
//    private Bitmap getResizeBitmap(Bitmap image, int maxSize) {
//        int width = image.getWidth();
//        int height = image.getHeight();
//
//        float bitmapRatio = (float) width / (float) height;
//        if (bitmapRatio > 1)
//        {
//            width = maxSize;
//            height = (int) (width/bitmapRatio);
//        } else {
//            height = maxSize;
//            width = (int) (height * bitmapRatio);
//        }
//        return Bitmap.createScaledBitmap(image, width, height,true);
//    }
//
//    private String BitMapToString(Bitmap userImage1) {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        userImage1.compress(Bitmap.CompressFormat.JPEG, 50,baos);
//        byte[] b = baos.toByteArray();
//        Document_img1 = Base64.encodeToString(b, Base64.DEFAULT);
//        Integer panjang = Document_img1.length();
//        Log.d(TAG, "BitMapToString : " +panjang.toString());
//        return Document_img1;
//    }
//
//}

//ini lebih baru

package com.example.projectkt.qrcode;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projectkt.HomeActivity;
import com.example.projectkt.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;


import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class contohaddDataSQLActivity extends AppCompatActivity {


    ImageView imageup;

    public static final String url = "https://projectkawanternak.000webhostapp.com/insert.php";
    EditText innama, innomor;
    private Button btnaddsiswa, uploadimg;
    TextView txtoutput;
    Bitmap imageupbit;
    String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contohadd_data_sqlactivity);


        innomor = (EditText) findViewById(R.id.innomor);
        innama = (EditText) findViewById(R.id.innama);
//        inid = (EditText) findViewById(R.id.inid);
        txtoutput = (TextView) findViewById(R.id.txtoutput);

        btnaddsiswa = (Button) findViewById(R.id.btnaddsiswa);
        uploadimg = (Button) findViewById(R.id.uploadimg);

        imageup = (ImageView) findViewById(R.id.imageup);

        btnaddsiswa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputdata();
            }
        });

        imageup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withActivity(contohaddDataSQLActivity.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent, "Browse Image"),1);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK)
        {
            Uri filepath = data.getData();
            try {

                InputStream inputStream = getContentResolver().openInputStream(filepath);
                imageupbit = BitmapFactory.decodeStream(inputStream);
                imageup.setImageBitmap(imageupbit);
                encodeBitmapImage(imageupbit);

            }catch (Exception ex)
            {

            }
        }
    }

    private void encodeBitmapImage(Bitmap imageupbit) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imageupbit.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        byte[] imageBytes = stream.toByteArray();

        encodedImage = android.util.Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    private void inputdata() {

//        final ProgressDialog loading = new ProgressDialog(contohaddDataSQLActivity.this);
        String nama = innama.getText().toString();
        String nomor = innomor.getText().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                        Toast.makeText(contohaddDataSQLActivity.this, response.toString(), Toast.LENGTH_LONG).show();
//                    loading.dismiss();
//                    txtoutput.setText(response);
                    innama.setText("");
                    innomor.setText("");
                    imageup.setImageResource(0);
                    Intent intent = new Intent (contohaddDataSQLActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
//                    txtoutput.setText("error");
                    innama.setText("");
                    innomor.setText("");
                    imageup.setImageResource(0);
//                    loading.dismiss();
                Toast.makeText(contohaddDataSQLActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                }
            })
            {
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
                    Map<String,String> params = new HashMap<String,String>();
                    params.put("foto", encodedImage);
                    params.put("nama", nama);
                    params.put("nomor", nomor);
                    return params;
                }
            };
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(stringRequest);
        }



}