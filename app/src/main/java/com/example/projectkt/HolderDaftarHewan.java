package com.example.projectkt;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

public class HolderDaftarHewan extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView innamah, injk, inkesehatan,intglhewan, innokandang;
    public ImageView fotoh;
    public CardView itemcontoh;
    public RelativeLayout bgkesehatan;
    private ItemDaftarHewanClickListener listener;

    public HolderDaftarHewan(@NotNull View itemView) {
        super(itemView);

        itemcontoh = (CardView) itemView.findViewById(R.id.itemcontoh);
        bgkesehatan = (RelativeLayout) itemView.findViewById(R.id.bgkesehatan);
        fotoh = (ImageView) itemView.findViewById(R.id.fotoh);
        innamah = (TextView) itemView.findViewById(R.id.innamah);
        injk = (TextView) itemView.findViewById(R.id.injk);
//            instatus = (TextView) itemView.findViewById(R.id.txtkesehatan);
        inkesehatan = (TextView) itemView.findViewById(R.id.inkesehatan);
        intglhewan = (TextView) itemView.findViewById(R.id.intglhewan);
        innokandang = (TextView) itemView.findViewById(R.id.innokandang);

    }

    public void setItemDaftarHewanClickListener(ItemDaftarHewanClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick (View v){
        listener.onClick(v, getAbsoluteAdapterPosition(),false);
    }
}
