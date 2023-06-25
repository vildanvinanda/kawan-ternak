package com.example.projectkt;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class EdukasiHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
    RelativeLayout bgcard;
    public ImageView imageedu;
    public TextView juduledu,tgledu,subedu;
    public CardView itemsedu;

    public ItemEdukasiClickListener listener;

    public EdukasiHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        bgcard = (RelativeLayout) itemView.findViewById(R.id.bgcard);
        imageedu = (ImageView) itemView.findViewById(R.id.imageedu);
        juduledu = (TextView) itemView.findViewById(R.id.juduledu);
        tgledu = (TextView) itemView.findViewById(R.id.tgledu);
        subedu = (TextView) itemView.findViewById(R.id.subedu);
        itemsedu = (CardView) itemView.findViewById(R.id.itemsedu);

    }

    public  void setItemEdukasiClickListener(ItemEdukasiClickListener listener)
    {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        listener.onClick(v, getAdapterPosition(),false);
    }
}

