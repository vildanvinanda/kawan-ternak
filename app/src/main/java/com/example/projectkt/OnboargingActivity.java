//package com.example.projectkt;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.viewpager.widget.ViewPager;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.text.Html;
//import android.view.View;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import org.w3c.dom.Text;
//
//public class OnboargingActivity extends AppCompatActivity {
//
//    private ViewPager slideviewpager;
//    private LinearLayout dots;
//
//    private TextView[] mDots;
//
//    private SliderAdapter sliderAdapter;
//
//    TextView lewati, selanjutnya, kembali;
//
//    private int mCurrentPage;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_onboarging);
//
//        slideviewpager = findViewById(R.id.slideviewpager);
//        dots = findViewById(R.id.dots);
//        lewati = findViewById(R.id.lewati);
//        selanjutnya = findViewById(R.id.selanjutnya);
//        kembali = findViewById(R.id.kembali);
//
//        sliderAdapter = new SliderAdapter(this);
//
//        slideviewpager.setAdapter(sliderAdapter);
//
//        addDotsIndicator(0);
//
//        slideviewpager.addOnPageChangeListener(viewListener);
//
//        selanjutnya.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slideviewpager.setCurrentItem(mCurrentPage +1);
//            }
//        });
//
//        lewati.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                slideviewpager.setCurrentItem(mCurrentPage -1);
//            }
//        });
//
//        if (restorePrefData()){
//
//            Intent mainActivity = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(mainActivity);
//            finish();
//
//        }
//    }
//
//    public void addDotsIndicator(int position){
//        mDots = new TextView[4];
//        dots.removeAllViews();
//        for(int i = 0; i < mDots.length; i++ ){
//            mDots [i] = new TextView(this);
//            mDots[i].setText(Html.fromHtml("&#8226;"));
//            mDots[i].setTextSize(35);
//            mDots[i].setTextColor(getResources().getColor(R.color.white));
//
//            dots.addView(mDots[i]);
//        }
//        if (mDots.length > 0){
//            mDots[position].setTextColor(getResources().getColor(R.color.kuning));
//        }
//    }
//
//    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int i) {
//            addDotsIndicator(i);
//
//            mCurrentPage = i;
//            if (i == 0){
////                kembali.setVisibility(View.GONE);
////                lewati.setVisibility(View.VISIBLE);
////                selanjutnya.setVisibility(View.VISIBLE);
//                selanjutnya.setEnabled(true);
//                lewati.setEnabled(true);
//                selanjutnya.setText("Selanjutnya");
//                lewati.setText("Lewati");
//                String cek2 = (String) selanjutnya.getText();
//                if (cek2.equals("Lewati")){
//                    selanjutnya.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(OnboargingActivity.this, LoginActivity.class);
//                            startActivity(intent);
//
//                            savePrefsData();
//                            finish();
//                        }
//                    });
//                }
//            } else if (i == mDots.length -1) {
//                selanjutnya.setEnabled(true);
//                lewati.setEnabled(true);
//
//                selanjutnya.setText("Selesai");
//                lewati.setText("Kembali");
//
//                String cek = (String) selanjutnya.getText();
//                if (cek.equals("Selesai")){
//                    selanjutnya.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(OnboargingActivity.this, LoginActivity.class);
//                            startActivity(intent);
//
//                            savePrefsData();
//                            finish();
//                        }
//                    });
//                }
//
//            } else {
//                selanjutnya.setEnabled(true);
//                lewati.setEnabled(true);
//
//                selanjutnya.setText("Selanjutnya");
//                lewati.setText("Kembali");
//            }
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    };
//
//    private boolean restorePrefData(){
//
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
//        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
//        return isIntroActivityOpenedBefore;
//
//    }
//    private void savePrefsData() {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putBoolean("isIntroOpened", true);
//        editor.commit();
//    }
//}


//baruuu
package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import org.w3c.dom.Text;

public class OnboargingActivity extends AppCompatActivity {

    private ViewPager slideviewpager;
    private DotsIndicator dots;

    private SliderAdapter sliderAdapter;

    TextView lewati, selanjutnya, kembali;

    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarging);

        slideviewpager = findViewById(R.id.slideviewpager);
        dots = findViewById(R.id.dots);
        lewati = findViewById(R.id.lewati);
        selanjutnya = findViewById(R.id.selanjutnya);
        kembali = findViewById(R.id.kembali);

        sliderAdapter = new SliderAdapter(this);

        slideviewpager.addOnPageChangeListener(viewListener);
        slideviewpager.setAdapter(sliderAdapter);
        dots.attachTo(slideviewpager);



        selanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideviewpager.setCurrentItem(mCurrentPage +1);
            }
        });

        lewati.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideviewpager.setCurrentItem(mCurrentPage -1);
            }
        });

        if (restorePrefData()){

            Intent mainActivity = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(mainActivity);
            finish();

        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int i) {
            dots.attachTo(slideviewpager);

            mCurrentPage = i;
            if (i == 0){
                selanjutnya.setEnabled(true);
                lewati.setEnabled(true);
                selanjutnya.setText("Selanjutnya");
                lewati.setText("Lewati");
                String cek2 = (String) selanjutnya.getText();
                if (cek2.equals("Lewati")){
                    selanjutnya.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(OnboargingActivity.this, LoginActivity.class);
                            startActivity(intent);

                            savePrefsData();
                            finish();
                        }
                    });
                }
            } else if (i == 3) {
                selanjutnya.setEnabled(true);
                lewati.setEnabled(true);

                selanjutnya.setText("Selesai");
                lewati.setText("Kembali");

                String cek = (String) selanjutnya.getText();
                if (cek.equals("Selesai")){
                    selanjutnya.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(OnboargingActivity.this, LoginActivity.class);
                            startActivity(intent);

                            savePrefsData();
                            finish();
                        }
                    });
                }

            } else {
                selanjutnya.setEnabled(true);
                lewati.setEnabled(true);

                selanjutnya.setText("Selanjutnya");
                lewati.setText("Kembali");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private boolean restorePrefData(){

        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        Boolean isIntroActivityOpenedBefore = pref.getBoolean("isIntroOpened", false);
        return isIntroActivityOpenedBefore;

    }
    private void savePrefsData() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("myPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("isIntroOpened", true);
        editor.commit();
    }
}