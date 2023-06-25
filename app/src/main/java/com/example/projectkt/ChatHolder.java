package com.example.projectkt;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChatHolder extends RecyclerView.ViewHolder{
    TextView dari,pesan,waktu, textTanggal;
    CircleImageView profiluserchat;
    LinearLayout linear, linear2, linearTanggal;
    CardView cardView, cardTanggal;

    public ChatHolder(View itemView) {
        super(itemView);
        dari = (TextView) itemView.findViewById(R.id.dari);
        pesan = (TextView) itemView.findViewById(R.id.pesan);
        waktu = (TextView) itemView.findViewById(R.id.waktu);
        profiluserchat = (CircleImageView) itemView.findViewById(R.id.profiluserchat);
        linear = (LinearLayout) itemView.findViewById(R.id.linear2);
        linear2 = (LinearLayout) itemView.findViewById(R.id.linear2);
        cardView = (CardView) itemView.findViewById(R.id.cardView);
        linearTanggal = (LinearLayout) itemView.findViewById(R.id.linearTanggal);
        cardTanggal = (CardView) itemView.findViewById(R.id.cardTanggal);
        textTanggal = (TextView) itemView.findViewById(R.id.textTanggal);
    }
}
