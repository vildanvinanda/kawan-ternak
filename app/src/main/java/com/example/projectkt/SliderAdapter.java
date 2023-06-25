package com.example.projectkt;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    //Arrays
    public int[] image_onboarding = {
            R.drawable.megang_hp,
            R.drawable.berbincang,
            R.drawable.ngajar,
            R.drawable.embefoto,
    };

    public String[] jdl = {
            "Manage Peternakan",
            "Forum Diskusi",
            "Artikel dan Edukasi",
            "Scan QR Code"
    };

    public String[] subjdl = {
            "Aplikasi ini akan membantu kamu untuk mengelola peternakan dengan baik",
            "Pada aplikasi ini kamu bisa menemukan solusi terkait peternakan",
            "Aplikasi ini memudahkan kamu untuk mencari ilmu serta motivasi mengenai peternakan",
            "Kamu dapat melihat detail identitas hewan ternak mu hanya menggunakan scan QRCode saja loh"
    };


    @Override
    public int getCount() {
        return jdl.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == (RelativeLayout) object;
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, int position){
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_onboarding, container, false);

        ImageView slideimg = (ImageView) view.findViewById(R.id.image_onboarding);
        TextView slide_jdl = (TextView) view.findViewById(R.id.jdl);
        TextView slide_subjdl = (TextView) view.findViewById(R.id.subjdl);

        slideimg.setImageResource(image_onboarding[position]);
        slide_jdl.setText(jdl[position]);
        slide_subjdl.setText(subjdl[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int positon, Object object){
        container.removeView((RelativeLayout)object);
    }
}
