package com.example.projectkt;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class ArtikelHolderFix  extends RecyclerView.ViewHolder implements View.OnClickListener {
    RelativeLayout bgcard2;
    public ImageView imageartikel;
    public TextView judulartikelitem,tglartikelitem,subartikelitem;
    public CardView itemsartikelcard;

    public ItemArtikelClickistenerFIX listener;

    public ArtikelHolderFix(@NotNull View itemView) {
        super(itemView);
        bgcard2 = (RelativeLayout) itemView.findViewById(R.id.bgcard2);
        imageartikel = (ImageView) itemView.findViewById(R.id.imageartikel);
        judulartikelitem = (TextView) itemView.findViewById(R.id.judulartikelitem);
        tglartikelitem = (TextView) itemView.findViewById(R.id.tglartikelitem);
        subartikelitem = (TextView) itemView.findViewById(R.id.subartikelitem);
        itemsartikelcard = (CardView) itemView.findViewById(R.id.itemsartikelcard);
    }

    public  void setItemArtikelClickistenerFIX(ItemArtikelClickistenerFIX listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}