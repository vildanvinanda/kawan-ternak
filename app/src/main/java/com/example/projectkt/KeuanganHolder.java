package com.example.projectkt;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class KeuanganHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    CardView pengeluaran1;
    RelativeLayout bgcard;
    public TextView txtKet, titleKeuangan,tglkirim,txttotalh;
    public ImageView tresmerah,tresshijau;

    public ItemKeuanganClickListener listener;

    public KeuanganHolder(@NotNull View itemView) {
        super(itemView);
        bgcard = (RelativeLayout) itemView.findViewById(R.id.bgcard);
        titleKeuangan = (TextView) itemView.findViewById(R.id.titleKeuangan);
        txtKet = (TextView) itemView.findViewById(R.id.txtKet);
        tglkirim = (TextView) itemView.findViewById(R.id.tglkirim);
        txttotalh = (TextView) itemView.findViewById(R.id.txttotalh);
        tresmerah = (ImageView) itemView.findViewById(R.id.tresmerah);
        tresshijau = (ImageView) itemView.findViewById(R.id.tresshijau);
        pengeluaran1 = (CardView) itemView.findViewById(R.id.pengeluaran1);
    }

    public  void setItemKeuanganClickListener(ItemKeuanganClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}
