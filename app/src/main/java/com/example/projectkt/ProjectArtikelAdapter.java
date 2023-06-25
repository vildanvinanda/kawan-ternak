package com.example.projectkt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class ProjectArtikelAdapter extends RecyclerView.Adapter<ProjectArtikelAdapter.ViewHolder> {


    private static final String Tag = "RecyclerView";

    ArrayList<ProjectModel> list;
    Context context;
    Locale id = new Locale("in", "ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyy", id);

    public ProjectArtikelAdapter( Context context, ArrayList<ProjectModel> list) {
        this.list = list;
        this.context = context;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.singlerowdesign,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {

        holder.viewBind(list.get(position));

//        holder.txtjudul9solo.setText(list.get(position).getEddTitle());
//        holder.txttgl6solo.setText(list.get(position).getEddttl());
//        Glide.with(context)
//                .load(list.get(position).getUploadgambarartikel())
//                .centerCrop()
//                .into(holder.image1);


//        ProjectModel model = list.get(position);
//
//        holder.itemIsiArtikel.setText(model.getEddIsiArtikel());
//        holder.itemTanggal.setText(model.getEddttl());
//        holder.itemTitle.setText(model.getEddTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  ViewHolder extends RecyclerView.ViewHolder
    {


        private EditText txttgl6solo,txtjudul9solo,eddIsiArtikel;
        private ImageView image1;




        public ViewHolder(@NotNull View itemView)
        {
            super(itemView);
            txttgl6solo = itemView.findViewById(R.id.txttgl6solo);
            txtjudul9solo = itemView.findViewById(R.id.txtjudul9solo);
            eddIsiArtikel = itemView.findViewById(R.id.eddIsiArtikel);
            image1 = itemView.findViewById(R.id.image1);

        }

        public void viewBind(ProjectModel projectModel) {

            txttgl6solo.setText(projectModel.getEddttl());
            txttgl6solo.setText(projectModel.getEddTitle());

        }
    }

}


//BARUUUUUUUUUU BARUUUU
// public class Main {

// }
//
//package com.example.projectkt;
//
//import android.content.Context;
//import android.hardware.lights.LightState;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.ImageView;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.FragmentActivity;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.google.firebase.database.DatabaseReference;
//import com.squareup.picasso.Picasso;
//
//import org.jetbrains.annotations.NotNull;
//import org.w3c.dom.Text;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ProjectArtikelAdapter extends RecyclerView.Adapter<ProjectArtikelAdapter.ViewHolder> {
//
//    private Context context;
//    private List<ProjectModel>projectModels;
//    DatabaseReference databaseReference;
//
//    public ProjectArtikelAdapter(Context context, List<ProjectModel> projectModels) {
//        this.context = context;
//        this.projectModels = projectModels;
//    }
//
//    @NotNull
//    @Override
//    public ProjectArtikelAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.singlerowdesign,parent, false);
//
//        return new ProjectArtikelAdapter.ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NotNull ProjectArtikelAdapter.ViewHolder holder, int position) {
//        final ProjectModel model = projectModels.get(position);
//
//        Glide.with(context)
//                .load(model.getUploadgambarartikel())
//                .centerCrop()
//                .into(holder.itemuploadgambarartikel);
//        holder.itemTitle.setText(model.getEddTitle());
//        holder.itemTanggal.setText(model.getEddttl());
//        holder.itemIsiArtikel.setText(model.getEddIsiArtikel());
//    }
//
//    @Override
//    public int getItemCount() {
//        return projectModels.size();
//    }
//
//    public class  ViewHolder extends RecyclerView.ViewHolder
//    {
//        EditText itemTanggal,itemTitle,itemIsiArtikel;
//        ImageView itemuploadgambarartikel;
//
//        public ViewHolder(@NotNull View itemView)
//        {
//            super(itemView);
//
//            itemTanggal = itemView.findViewById(R.id.txttgl6solo);
//            itemTitle = itemView.findViewById(R.id.txtjudul9solo);
//            itemIsiArtikel = itemView.findViewById(R.id.eddIsiArtikel);
//            itemuploadgambarartikel = itemView.findViewById(R.id.image1);
//
//        }
//    }
//
////    private List<ProjectModel> projectModels;
////    private Context context;
////
////    DatabaseReference databaseReference;
////
////
////
////    public ProjectArtikelAdapter(List<ProjectModel> projectModels, Context context) {
////        this.projectModels = projectModels;
////        this.context = context;
////    }
////
////
////    @NotNull
////    @Override
////    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
////
////        View view = LayoutInflater.from(context).inflate(R.layout.singlerowdesign,parent, false);
////
////        return new ViewHolder(view);
////    }
////
////    @Override
////    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
////
////        final ProjectModel model = projectModels.get(position);
////
////        // Picasso.get().load(model.getUploadgambarartikel()).placeholder(R.drawable.sapi).into(holder.itemuploadgambarartikel);
////
////        // holder.itemIsiArtikel.setText(model.getEddIsiArtikel());
////        // holder.itemTanggal.setText(model.getEddttl());
////        // holder.itemTitle.setText(model.getEddTitle());
////
////        //Baru
////        holder.itemIsiArtikel.setText(model.getEddIsiArtikel());
////        holder.itemTanggal.setText(model.getEddttl());
////        holder.itemTitle.setText(model.getEddTitle());
////        Glide.with(context).load(model.getUploadgambarartikel()).into(holder.itemuploadgambarartikel);
////
////    }
////
////    @Override
////    public int getItemCount() {
////        return projectModels.size();
////    }
////
////    public class  ViewHolder extends RecyclerView.ViewHolder
////    {
////
////        //ini semua yang ada di item single
////        EditText itemTanggal,itemTitle,itemIsiArtikel;
////        ImageView itemBtnTanggal,itemuploadgambarartikel;
////
////
////
////
////        public ViewHolder(@NotNull View itemView)
////        {
////            super(itemView);
////            itemTanggal = itemView.findViewById(R.id.txttgl6solo);
////            itemBtnTanggal = itemView.findViewById(R.id.btnttl);
////            itemTitle = itemView.findViewById(R.id.txtjudul9solo);
//////            itemIsiArtikel = itemView.findViewById(R.id.eddIsiArtikel);
////            itemuploadgambarartikel = itemView.findViewById(R.id.image1);
////
////        }
////    }
//
//}

