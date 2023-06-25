package com.example.projectkt;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NotifHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    CardView item_notifikasi;
    public ImageView imgnotif;
    public TextView txtjudulnotif, isinotif, txttglnotif;

    public ItemNotifClickListener listener;

    public NotifHolder(@NonNull View itemView){
        super(itemView);
        item_notifikasi = (CardView) itemView.findViewById(R.id.item_notifikasi);
        imgnotif = (ImageView) itemView.findViewById(R.id.imgnotif);
        txtjudulnotif = (TextView) itemView.findViewById(R.id.txtjudulnotif);
        isinotif = (TextView) itemView.findViewById(R.id.isinotif);
        txttglnotif = (TextView) itemView.findViewById(R.id.txttglnotif);
    }

    public void  setItemNotifClickListener(ItemNotifClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}
