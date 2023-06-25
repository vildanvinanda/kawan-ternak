package com.example.projectkt;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ArtikelHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    RelativeLayout bgcard;
    public ImageView image1;
    public TextView txtjudul9solo,txttgl6solo,txtsub9solo;
    public CardView singlecardview;

    public ItemArtikelClickListener listener;


    public ArtikelHolder(View itemView) {
        super(itemView);

        bgcard = (RelativeLayout) itemView.findViewById(R.id.bgcard);
        image1 = (ImageView) itemView.findViewById(R.id.image1);
        txtjudul9solo = (TextView) itemView.findViewById(R.id.txtjudul9solo);
        txttgl6solo = (TextView) itemView.findViewById(R.id.txttgl6solo);
        txtsub9solo = (TextView) itemView.findViewById(R.id.txtsub9solo);
        singlecardview = (CardView) itemView.findViewById(R.id.singlecardview);

    }

    public  void setItemArtikelClickListener(ItemArtikelClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}
