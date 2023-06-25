//INI SUDAH BERHASIL
package com.example.projectkt.qrcode;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.example.projectkt.DaftarHewanActivity;
import com.example.projectkt.DetailHewanActivity;
import com.example.projectkt.HomeActivity;
import com.example.projectkt.R;
//import com.example.projectkt.databinding.ActivityDetailHewanBinding;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.nio.channels.NetworkChannel;
import java.util.ArrayList;
import java.util.List;

public class AdapterRecItemContoh extends RecyclerView.Adapter<AdapterRecItemContoh.ViewHolder> {

//
//    List<DataModel> dataModels;
//    LayoutInflater inflater;
//    Context context;
//    ImageLoader imageLoader;
//
//    public AdapterRecItemContoh(List<DataModel> getDataModel,Context context) {
//        super();
//        this.dataModels = getDataModel;
//        this.context = context;
//    }
//
//    @NotNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itecontohsql,parent,false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
//        DataModel dataModelOBJ = dataModels.get(position);
//        imageLoader = ImageAdapter.getInstance(context).getImageLoader();
//        imageLoader.get(dataModelOBJ.getImaghewan(),
//        ImageLoader.getImageListener(
//                ViewHolder.fotoh,
//                R.mipmap.ic_launcher,
//                android.R.drawable.ic_dialog_alert
//        )
//        );
//
//        ViewHolder.fotoh.setImageUrl(dataModelOBJ.getImaghewan(),imageLoader);
//        ViewHolder.innamah.setText(dataModelOBJ.getNamahewan());
//        ViewHolder.injk.setText(dataModelOBJ.getJk());
//        ViewHolder.instatus.setText(dataModelOBJ.getStatushewan());
//        ViewHolder.inkesehatan.setText(dataModelOBJ.getNamapemilik());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataModels.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        public static TextView innamah, injk, instatus, inkesehatan;
//        public static NetworkImageView fotoh;
//
//        public ViewHolder(@NotNull View itemView) {
//            super(itemView);
//
//            fotoh =  (NetworkImageView) itemView.findViewById(R.id.fotoh);
//            innamah = (TextView) itemView.findViewById(R.id.innamah);
//            injk = (TextView) itemView.findViewById(R.id.injk);
//            instatus = (TextView) itemView.findViewById(R.id.instatus);
//            inkesehatan = (TextView) itemView.findViewById(R.id.inkesehatan);
//        }
//    }
    //N+BEDAAAA
//
//    public HolderData extends RecyclerView.ViewHolder {
//        TextView innamah, injk, instatus, inkesehatan;
//        ImageView fotoh;
//
//        public HolderData(@NotNull View itemView) {
//            super(itemView);
//
//            fotoh = itemView.findViewById(R.id.fotoh);
//            innamah = itemView.findViewById(R.id.innamah);
//            injk = itemView.findViewById(R.id.injk);
//            instatus = itemView.findViewById(R.id.instatus);
//            inkesehatan = itemView.findViewById(R.id.inkesehatan);
//        }
//    }
//
//    @NotNull
//    @Override
//    public AdapterRecItemContoh.HolderData onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//
//        View view = inflater.inflate(R.layout.itecontohsql, parent, false);
//
//        return new HolderData(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NotNull AdapterRecItemContoh.HolderData holder, int position) {
//        holder.innamah.setText(listData.get(position).getNamahewan());
//        holder.injk.setText(listData.get(position).getJk());
//
//        holder.inkesehatan.setText(listData.get(position).getNamapemilik());
//        Glide.with(context).load(listData.get(position).getImaghewan()).into(holder.fotoh);
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size();
//    }

    //BARUUUUU

    LayoutInflater inflater;

    List<DataModel> dataModels;
    Context context;
//    LayoutInflater inflater;

    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener (OnItemClickListener listener){
        mListener = listener;
    }
    public AdapterRecItemContoh(Context context, List<DataModel> dataModels) {
        this.context = context;
        this.dataModels = dataModels;
    }
//    public void clear() {
//        dataModels.clear();
//        a
//    }
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.itecontohsql,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
//        viewHolder.itemcontoh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, HomeActivity.class);
//                intent.putExtra("namahewan",dataModels.get(viewHolder.getAdapterPosition()).getNamahewan());
//                context.startActivity(intent);
//            }
//        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        DataModel itemData = dataModels.get(position);

        String datasakit = itemData.getStatuskesehatan();

        holder.innamah.setText(dataModels.get(position).getNamahewan());
        holder.injk.setText(dataModels.get(position).getJk());
        holder.innokandang.setText(itemData.getNokandang());
        holder.intglhewan.setText(dataModels.get(position).getTglupload());

        if(datasakit.equals("Sakit")){
            holder.inkesehatan.setText(dataModels.get(position).getStatuskesehatan());
            holder.bgkesehatan.setBackgroundColor(context.getResources().getColor(R.color.merahpias));
            holder.inkesehatan.setTextColor(context.getResources().getColor(R.color.merahmuda));
        } else {
            holder.inkesehatan.setText(dataModels.get(position).getStatuskesehatan());
            holder.bgkesehatan.setBackgroundColor(context.getResources().getColor(R.color.ijomudapias));
            holder.inkesehatan.setTextColor(context.getResources().getColor(R.color.ijomuda));
        }
//        Picasso.get().load(dataModels.get(position).getImaghewan()).into(holder.fotoh);
        Glide.with(holder.fotoh.getContext())
                .load(dataModels.get(position).getImaghewan())
                .centerCrop()
                .placeholder(R.drawable.usepng)
                .into(holder.fotoh);
    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public void clear() {
        int size = dataModels.size();
        dataModels.clear();
        notifyItemRangeRemoved(0, size);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        public TextView innamah, injk, inkesehatan,intglhewan, innokandang;
        public ImageView fotoh;
        public CardView itemcontoh;
        public RelativeLayout bgkesehatan;

        public ViewHolder(@NotNull View itemView) {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener != null){
                       int position = getAdapterPosition();
                       if(position != RecyclerView.NO_POSITION){
                           mListener.onItemClick(position);
                       }
                    }
                }
            });



        }
    }
}

//INI BARU

//beda
//package com.example.projectkt.qrcode;
//
//import android.content.Context;
//import android.content.Intent;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.android.volley.toolbox.ImageLoader;
//import com.android.volley.toolbox.NetworkImageView;
//import com.bumptech.glide.Glide;
//import com.example.projectkt.DaftarHewanActivity;
//import com.example.projectkt.DetailHewanActivity;
//import com.example.projectkt.HomeActivity;
//import com.example.projectkt.R;
//import com.example.projectkt.databinding.ActivityDetailHewanBinding;
//import com.squareup.picasso.Picasso;
//
//import org.jetbrains.annotations.NotNull;
//import org.w3c.dom.Text;
//
//import java.nio.channels.NetworkChannel;
//import java.util.ArrayList;
//import java.util.List;
//
//public class AdapterRecItemContoh extends RecyclerView.Adapter<AdapterRecItemContoh.ViewHolder> {
//
//    //
//    List<DataModel> dataModels;
//    LayoutInflater inflater;
//    Context context;
//    ImageLoader imageLoader;
//    //
//    private OnItemClickListener mListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener = listener;
//    }
//
//    public AdapterRecItemContoh(List<DataModel> getDataModel, Context context) {
//        super();
//        this.dataModels = getDataModel;
//        this.context = context;
//    }
//
//    @NotNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itecontohsql, parent, false);
//        ViewHolder viewHolder = new ViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
//        DataModel dataModelOBJ = dataModels.get(position);
//        imageLoader = ImageAdapter.getInstance(context).getImageLoader();
//        imageLoader.get(dataModelOBJ.getImaghewan(),
//                ImageLoader.getImageListener(
//                        ViewHolder.fotoh,
//                        R.mipmap.ic_launcher,
//                        android.R.drawable.ic_dialog_alert
//                )
//        );
//
//        ViewHolder.fotoh.setImageUrl(dataModelOBJ.getImaghewan(), imageLoader);
//        ViewHolder.innamah.setText(dataModelOBJ.getNamahewan());
//        ViewHolder.injk.setText(dataModelOBJ.getJk());
//        ViewHolder.instatus.setText(dataModelOBJ.getStatushewan());
//        ViewHolder.inkesehatan.setText(dataModelOBJ.getNamapemilik());
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataModels.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//
//        public static TextView innamah, injk, instatus, inkesehatan;
//        public static NetworkImageView fotoh;
//
//        public ViewHolder(@NotNull View itemView) {
//            super(itemView);
//
//            fotoh = (NetworkImageView) itemView.findViewById(R.id.fotoh);
//            innamah = (TextView) itemView.findViewById(R.id.innamah);
//            injk = (TextView) itemView.findViewById(R.id.injk);
//            instatus = (TextView) itemView.findViewById(R.id.instatus);
//            inkesehatan = (TextView) itemView.findViewById(R.id.inkesehatan);
//        }
//    }
//
//
//    public HolderData extends RecyclerView.ViewHolder
//
//    {
//        TextView innamah, injk, instatus, inkesehatan;
//        ImageView fotoh;
//
//        public HolderData(@NotNull View itemView) {
//        super(itemView);
//
//        fotoh = itemView.findViewById(R.id.fotoh);
//        innamah = itemView.findViewById(R.id.innamah);
//        injk = itemView.findViewById(R.id.injk);
//        instatus = itemView.findViewById(R.id.instatus);
//        inkesehatan = itemView.findViewById(R.id.inkesehatan);
//
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mListener != null) {
//                    int position = getAdapterPosition();
//                    if (position != RecyclerView.NO_POSITION) {
//                        mListener.onItemClick(position);
//                    }
//                }
//            }
//        });
//    }
//    }
//
//    @NotNull
//    @Override
//    public AdapterRecItemContoh.HolderData onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//
//        View view = inflater.inflate(R.layout.itecontohsql, parent, false);
//
//        return new HolderData(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NotNull AdapterRecItemContoh.HolderData holder, int position) {
//        holder.innamah.setText(listData.get(position).getNamahewan());
//        holder.injk.setText(listData.get(position).getJk());
//
//        holder.inkesehatan.setText(listData.get(position).getNamapemilik());
//        Glide.with(context).load(listData.get(position).getImaghewan()).into(holder.fotoh);
//    }
//
//    @Override
//    public int getItemCount() {
//        return listData.size();
//    }
//}

    //BARUUUUU

//    LayoutInflater inflater;
//    private ArrayList<DataModel> dataModels;
//    private Context context;
//    private OnItemClickListener mListener;
//
//    public interface OnItemClickListener {
//        void onItemClick(int position);
//    }
//
//    public void setOnItemClickListener (OnItemClickListener listener){
//        mListener = listener;
//    }
//
//    public AdapterRecItemContoh(Context context, ArrayList<DataModel> dataModels) {
//        this.context = context;
//        this.dataModels = dataModels;
//    }
//
//    @NotNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
////        LayoutInflater inflater = LayoutInflater.from(context);
////        View view = inflater.inflate(R.layout.itecontohsql,parent,false);
////        ViewHolder viewHolder = new ViewHolder(view);
//
//        View view = LayoutInflater.from(context).inflate(R.layout.itecontohsql,parent,false);
//
////        viewHolder.itemcontoh.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Intent intent = new Intent(context, HomeActivity.class);
////                intent.putExtra("namahewan",dataModels.get(viewHolder.getAdapterPosition()).getNamahewan());
////                context.startActivity(intent);
////            }
////        });
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
//        DataModel currentItem = dataModels.get(position);
//
//        String namahewan = currentItem.getNamahewan();
//        String jk = currentItem.getJk();
//        String statushewan = currentItem.getStatushewan();
//        String imaghewan = currentItem.getImaghewan();
//
//
//        holder.innamah.setText(namahewan);
//        holder.injk.setText(jk);
//        holder.instatus.setText(statushewan);
//        Picasso.get().load(imaghewan).into(holder.fotoh);
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataModels.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//
//
//        public TextView innamah, injk, instatus, inkesehatan;
//        public ImageView fotoh;
//        public CardView itemcontoh;
//
//        public ViewHolder(@NotNull View itemView) {
//            super(itemView);
//
//            itemcontoh = (CardView) itemView.findViewById(R.id.itemcontoh);
//            fotoh = (ImageView) itemView.findViewById(R.id.fotoh);
//            innamah = (TextView) itemView.findViewById(R.id.innamah);
//            injk = (TextView) itemView.findViewById(R.id.injk);
//            instatus = (TextView) itemView.findViewById(R.id.instatus);
//            inkesehatan = (TextView) itemView.findViewById(R.id.inkesehatan);
//
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(mListener != null){
//                       int position = getAdapterPosition();
//                       if(position != RecyclerView.NO_POSITION){
//                           mListener.onItemClick(position);
//                       }
//                    }
//                }
//            });
//        }
//    }
//}