package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.PointF;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DetailFotoChat extends AppCompatActivity {

    private String imgchatID,userChat,jamChat,ttlChat;
    private TextView tglkirimimg,usenameChat,jamimgchat;
    private ImageView backbtnkeuangan;
    private ImageView gambarchat;

    private DatabaseReference imgchatRef;



    float[] lastEvent = null;
    float d = 0f;
    float newRot = 0f;
    private boolean isZoomAndRotate;
    private boolean isOutSide;
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    private PointF start = new PointF();
    private PointF mid = new PointF();
    float oldDist = 1f;
    private float xCoOrdinate, yCoOrdinate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_foto_chat);

        imgchatRef = FirebaseDatabase.getInstance().getReference().child("Messages");

        tglkirimimg = (TextView) findViewById(R.id.tglkirimimg);
        usenameChat = (TextView) findViewById(R.id.usenameChat);
        jamimgchat = (TextView) findViewById(R.id.jamimgchat);
        backbtnkeuangan = (ImageView) findViewById(R.id.backbtnkeuangan);
        gambarchat = (ImageView) findViewById(R.id.gambarchat);


        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        imgchatID = getIntent().getExtras().get("imgChat").toString();
        userChat = getIntent().getExtras().get("userChat").toString();
        jamChat = getIntent().getExtras().get("jamChat").toString();
        ttlChat = getIntent().getExtras().get("ttlChat").toString();

//        RetrieveImgChatInfo();

        Picasso.get().load(imgchatID).fit().into(gambarchat);

//        gambarchat.setBimgchatID);
        usenameChat.setText(userChat);
        jamimgchat.setText(jamChat);
        tglkirimimg.setText(ttlChat);

        gambarchat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ImageView view = (ImageView) v;
                view.bringToFront();
                viewTransformation(view, event);
                return true;
            }
        });

    }

    private void viewTransformation(ImageView view, MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                xCoOrdinate = view.getX() - event.getRawX();
                yCoOrdinate = view.getY() - event.getRawY();

                start.set(event.getX(), event.getY());
                isOutSide = false;
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    midPoint(mid, event);
                    mode = ZOOM;
                }

                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
                isZoomAndRotate = false;
                if (mode == DRAG) {
                    float x = event.getX();
                    float y = event.getY();
                }
            case MotionEvent.ACTION_OUTSIDE:
                isOutSide = true;
                mode = NONE;
                lastEvent = null;
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!isOutSide) {
                    if (mode == DRAG) {
                        isZoomAndRotate = false;
                        view.animate().x(event.getRawX() + xCoOrdinate).y(event.getRawY() + yCoOrdinate).setDuration(0).start();
                    }
                    if (mode == ZOOM && event.getPointerCount() == 2) {
                        float newDist1 = spacing(event);
                        if (newDist1 > 10f) {
                            float scale = newDist1 / oldDist * view.getScaleX();
                            view.setScaleX(scale);
                            view.setScaleY(scale);
                        }
                        if (lastEvent != null) {
                            newRot = rotation(event);
                            view.setRotation((float) (view.getRotation() + (newRot - d)));
                        }
                    }
                }
                break;
        }
    }

    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (int) Math.sqrt(x * x + y * y);
    }

    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    private void RetrieveImgChatInfo() {
        imgchatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
//                if ((snapshot.exists()) && (snapshot.hasChild("image")))
//                {
//                    String chatImage = snapshot.child("image").getValue().toString();
//                    String chatUsername = snapshot.child("username").getValue().toString();
//                    String chatTtl = snapshot.child("waktu").getValue().toString();
//                    String chatJam = snapshot.child("jam").getValue().toString();
//
//                    Picasso.get().load(chatImage).placeholder(R.drawable.kambingmakan).into(gambarchat);
//                    usenameChat.setText(chatUsername);
//                    tglkirimimg.setText(chatTtl);
//                    jamimgchat.setText(chatJam);
//                }
//                else
//                {
//                    String chatImage = snapshot.child("image").getValue().toString();
//                    String chatUsername = snapshot.child("username").getValue().toString();
//                    String chatTtl = snapshot.child("waktu").getValue().toString();
//                    String chatJam = snapshot.child("jam").getValue().toString();
//
//                    Picasso.get().load(chatImage).placeholder(R.drawable.kambingmakan).into(gambarchat);
//                    usenameChat.setText(chatUsername);
//                    tglkirimimg.setText(chatTtl);
//                    jamimgchat.setText(chatJam);
//                }



            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
}