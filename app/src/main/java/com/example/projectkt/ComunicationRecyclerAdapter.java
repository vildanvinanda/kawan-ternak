//package com.example.projectkt;
//
//import android.content.Context;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import org.jetbrains.annotations.NotNull;
//import org.w3c.dom.Text;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class ComunicationRecyclerAdapter extends RecyclerView.Adapter<ComunicationRecyclerAdapter.ComunicationViewHolder> {
//
//    Context context;
//    List<dataComunication> dataComunicationList;
//    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
//    List <String> listData;
//
//    public ComunicationRecyclerAdapter(Context context, List<dataComunication> dataComunicationList, List<String> listData) {
//        this.context = context;
//        this.dataComunicationList = dataComunicationList;
//        this.listData = listData;
//    }
//
//    @NotNull
//    @Override
//    public ComunicationViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
//        return new ComunicationViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NotNull ComunicationViewHolder holder, int position) {
//        String itemData = listData.get(position);
//        dataComunication itemNormal = dataComunicationList.get(position);
//
//        Date date = new Date();
//        Calendar calendar = Calendar.getInstance();
//        Locale locale = new Locale ("in","ID");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MMMM/yyyy",locale);
//        long kemarin = date.getTime() - ( 1000 * 60 * 60 * 24 );
//
//        if (itemNormal.getTanggal().equals(itemData)){
//            holder.linearTanggal.setVisibility(View.GONE);
//
//        }
//        if (itemNormal.getTanggal().equals(simpleDateFormat.format(kemarin))){
//            holder.textTanggal.setText("Kemarin");
//        }else if (itemNormal.getTanggal().equals(simpleDateFormat.format(date.getTime()))){
//            holder.textTanggal.setText("Sekarang");
//        } else {
//            holder.textTanggal.setText(itemNormal.getTanggal());
//        }
//       holder.binView(dataComunicationList.get(position));
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ComunicationViewHolder extends RecyclerView.ViewHolder{
//        TextView dari,pesan,waktu, textTanggal;
//        CircleImageView profiluserchat;
//        LinearLayout linear, linear2, linearTanggal;
//        CardView cardView, cardTanggal;
//
//        public ComunicationViewHolder(@NotNull View itemView) {
//            super(itemView);
//            dari = itemView.findViewById(R.id.dari);
//            pesan = itemView.findViewById(R.id.pesan);
//            waktu = itemView.findViewById(R.id.waktu);
//            profiluserchat = itemView.findViewById(R.id.profiluserchat);
//            linear = itemView.findViewById(R.id.linear2);
//            linear2 = itemView.findViewById(R.id.linear2);
//            cardView = itemView.findViewById(R.id.cardView);
//            linearTanggal = itemView.findViewById(R.id.linearTanggal);
//            cardTanggal = itemView.findViewById(R.id.cardTanggal);
//            textTanggal = itemView.findViewById(R.id.textTanggal);
//        }
//
//        public void binView(dataComunication dataComunication) {
//            Locale locale = new Locale("in","ID");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa",locale);
//
//            dari.setText(dataComunication.getDari());
//            pesan.setText(dataComunication.getPesan());
//            waktu.setText(simpleDateFormat.format(dataComunication.getWaktu()));
//            textTanggal.setText(dataComunication.getTanggal());
//            database.child("Users").child(dataComunication.getKey()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NotNull DataSnapshot snapshot) {
//                    String srcImage = snapshot.child("profileimage").getValue(String.class);
//                    Glide.with(context).load(srcImage).placeholder(R.drawable.ic_baseline_account_circle_24).into(profiluserchat);
//                }
//
//                @Override
//                public void onCancelled(@NotNull DatabaseError error) {
//
//                }
//            });
//
//            if(dataComunication.getKey().equals(preferences.getKeyData(context))){
//                dari.setVisibility(View.GONE);
//                profiluserchat.setVisibility(View.GONE);
//                linear.setGravity(Gravity.CENTER|Gravity.END);
//                linear2.setGravity(Gravity.CENTER|Gravity.END);
//                pesan.setTextColor(context.getColor(android.R.color.white));
//                cardView.setBackground(context.getResources().getDrawable(R.drawable.chat_to));
//            }
//        }
//
//    }
//}

package com.example.projectkt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.Mass;
import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Observer;

import de.hdodenhof.circleimageview.CircleImageView;

public class ComunicationRecyclerAdapter extends RecyclerView.Adapter<ComunicationRecyclerAdapter.ViewHolder> {

    Context context;
    private ArrayList<Massage> list;
    Massage massage;
    Locale id = new Locale("in", "ID");
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyy", id);
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    FirebaseUser user = firebaseAuth.getCurrentUser();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    ArrayList<String> selectList = new ArrayList<>();
    boolean isEnable = false;
    boolean isSelect = false;


    List <String> listData;

    public ComunicationRecyclerAdapter(Context context, ArrayList<Massage> list, List<String> listData) {

        this.context = context;
        this.list = list;
        this.listData = listData;
    }

    //    public ComunicationRecyclerAdapter(Context context, ArrayList<Massage> list) {
//        this.context = context;
//        this.list = list;
//    }

    @NotNull
    @Override
    public ComunicationRecyclerAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        String itemData = listData.get(position);
        Massage itemNormal = list.get(position);

        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        Locale locale = new Locale ("in","ID");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy",locale);
        long kemarin = date.getTime() - ( 1000 * 60 * 60 * 24 );

        if (itemNormal.getWaktu().equals(itemData)){
            holder.linearTanggal.setVisibility(View.GONE);
        }
        if (itemNormal.getWaktu().equals(simpleDateFormat.format(kemarin))){
            holder.textTanggal.setText("Kemarin");
        }else if (itemNormal.getWaktu().equals(simpleDateFormat.format(date.getTime()))){
            holder.textTanggal.setText("Sekarang");
        } else {
            holder.textTanggal.setText(itemNormal.getWaktu());
        }

        holder.imgmassage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent formdiskusi = new Intent(context, DetailFotoChat.class);
                formdiskusi.putExtra("imgChat", itemNormal.getImage());
                formdiskusi.putExtra("userChat", itemNormal.getUsername());
                formdiskusi.putExtra("jamChat", itemNormal.getJam());
                formdiskusi.putExtra("ttlChat", itemNormal.getWaktu());
                context.startActivity(formdiskusi);

            }
        });


//

        holder.profiluserchat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent profile = new Intent(context, ViewProfileStrangers.class);
                profile.putExtra("imgChat2", itemNormal.getProfiluserchat());
                profile.putExtra("userr", itemNormal.getUsername());
//                profile.putExtra("emailchat", itemNormal.getDari());
                profile.putExtra("emailku", itemNormal.getDari());
                profile.putExtra("nohp", itemNormal.getNohp());
                profile.putExtra("gender", itemNormal.getGender());
                profile.putExtra("alamat", itemNormal.getAddresss());
                context.startActivity(profile);
            }
        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if(!isEnable){
//                    ActionMode.Callback callback = new ActionMode.Callback() {
//                        @Override
//                        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
//                            //initialize menu inflater
//                            MenuInflater menuInflater = mode.getMenuInflater();
//                            //Inflate menu
//                            menuInflater.inflate(R.menu.menu,menu);
//                            return true;
//                        }
//
//                        @Override
//                        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
//                            //When action mode is prepare
//                            //set isEnable true
//                            isEnable = true;
//                            //Create
//                            ClickItem(holder);
//
//                            //set observer on get text method
//                            return false;
//                        }
//
//                        @Override
//                        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
//                            return false;
//                        }
//
//                        @Override
//                        public void onDestroyActionMode(ActionMode mode) {
//
//                        }
//                    };
//                }
//                return false;
//            }
//        });

        holder.viewBind(list.get(position));
    }

    private void ClickItem(ViewHolder holder) {
        String s = listData.get(holder.getAdapterPosition());
        if (holder.iv_check_box.getVisibility() == View.GONE){
            //When item not selected
            //visible check box image
            holder.iv_check_box.setVisibility(View.VISIBLE);
            //Set background color
            holder.itemView.setBackgroundColor(Color.LTGRAY);
            //Add value in selected array list
            selectList.add(s);
        } else {
            //When item selected
            //Hide check box image
            holder.iv_check_box.setVisibility(View.GONE);
            //set background color
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
            //Remove value from selected array list
            selectList.remove(s);

        }
        //set text on view model
        massage.setPesan(String.valueOf(selectList.size()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView dari,pesan,waktu, textTanggal,usernamechat;
        CircleImageView profiluserchat;
        LinearLayout linear, linear2, linearTanggal;
        CardView cardView, cardTanggal;
        RelativeLayout inibgpesan;
        ImageView imgmassage,iv_check_box;


        public ViewHolder(@NotNull View itemView) {
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
            inibgpesan = (RelativeLayout) itemView.findViewById(R.id.inibgpesan);
            imgmassage = (ImageView) itemView.findViewById(R.id.imgmassage);
            iv_check_box = (ImageView) itemView.findViewById(R.id.iv_check_box);
            usernamechat = (TextView) itemView.findViewById(R.id.usernamechat);

        }
        public void viewBind(Massage massage) {

            String userman = massage.getDari();
            String psan = massage.getPesan();
            String muid = massage.getMuid();


            dari.setText(massage.getDari());
            usernamechat.setText(massage.getUsername());
            pesan.setText(massage.getPesan());
            waktu.setText(massage.getJam());


//            database.child("Messages").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NotNull DataSnapshot snapshot) {
////                    String artikelImage = snapshot.child("profileimage").getValue().toString();
//                    String srcImage = snapshot.child("profileimage").getValue(String.class);
////                    String profileimage = ""+snapshot.child("profileimage").getValue();
////                    Picasso.get().load(srcImage).placeholder(R.drawable.usepng).into(profiluserchat);
//
//                }
//
//                @Override
//                public void onCancelled(@NotNull DatabaseError error) {
//
//                }
//            });

            database.child("Messages").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NotNull DataSnapshot snapshot) {
//                    String profileimage = ""+snapshot.child("profiluserchat").getValue();

//                        String srcImage = snapshot.child("profiluserchat").getValue(String.class);
                        Glide.with(profiluserchat.getContext())
                                .load(massage.getProfiluserchat())
                                .centerCrop()
                                .placeholder(R.drawable.usepng)
                                .into(profiluserchat);
//                    Glide.with(holder.image1.getContext())
//                            .load(model.getUploadgambarartikel())
//                            .centerCrop()
//                            .into(holder.image1);

                }

                @Override
                public void onCancelled(@NotNull DatabaseError error) {

                }
            });

//            if(massage.getType().equals("image")){
//                    pesan.setVisibility(View.GONE);
//                    imgmassage.setVisibility(View.VISIBLE);
//                    Glide.with(context).load(massage.getPesan()).placeholder(R.drawable.sapi).into(imgmassage);
//                }else if (massage.getType().equals("text")){
//                    pesan.setVisibility(View.VISIBLE);
//                    imgmassage.setVisibility(View.GONE);
//                }

            if (userman.equals(user.getEmail())){
                dari.setVisibility(View.GONE);
                usernamechat.setVisibility(View.GONE);
                profiluserchat.setVisibility(View.GONE);
                linear.setGravity(Gravity.CENTER|Gravity.END);
                linear2.setGravity(Gravity.CENTER|Gravity.END);
                pesan.setTextColor(context.getColor(android.R.color.white));
                inibgpesan.setBackground(context.getResources().getDrawable(R.drawable.chat_to));

                //ini tabel hapus
                itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        showDeleteDataDialog(muid);

                        return false;
                    }

                    private void showDeleteDataDialog(String muid) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setTitle("Hapus");
                        builder.setMessage("Anda yakin untuk menghapus pesan ini?");
                        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Query query = database.child("Messages").orderByChild("muid").equalTo(muid);
                                query.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NotNull DataSnapshot snapshot) {
                                        for (DataSnapshot ds : snapshot.getChildren()){
                                            ds.getRef().removeValue();
                                        }
                                        Toast.makeText(context, "Pesan Telah Dihapus", Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCancelled(@NotNull DatabaseError error) {

                                    }
                                });
                            }
                        });
                        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int i) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();

                    }
                });

                if(massage.getType().equals("image")){
                    pesan.setVisibility(View.GONE);
                    imgmassage.setVisibility(View.VISIBLE);
                    Glide.with(context).load(massage.getImage()).into(imgmassage);
                }else if (massage.getType().equals("text")){
                    pesan.setVisibility(View.VISIBLE);
                    imgmassage.setVisibility(View.GONE);
                }
//                inibgpesan.setBackgroundColor(context.getResources().getColor(R.color.kuning));
            }else {
                dari.setVisibility(View.GONE);
                if(massage.getType().equals("image")){
                    pesan.setVisibility(View.GONE);
                    imgmassage.setVisibility(View.VISIBLE);
                    Glide.with(context).load(massage.getImage()).into(imgmassage);
                }else if (massage.getType().equals("text")){
                    pesan.setVisibility(View.VISIBLE);
                    imgmassage.setVisibility(View.GONE);
                }
            }

//            cardView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    if(userman.equals(user.getEmail())){
//                        if(massage.getType().equals("image")){
//                            long limit = massage.getWaktu()-(100*60*5);
//                        }
//                    } else if(massage.getType().equals("text")){
//
//                    } else {
//
//                    }
//                    return false;
//                }
//            });


        }



//        public void binView(dataComunication dataComunication) {
//            Locale locale = new Locale("in","ID");
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm aa",locale);
//
//            dari.setText(dataComunication.getDari());
//            pesan.setText(dataComunication.getPesan());
//            waktu.setText(simpleDateFormat.format(dataComunication.getWaktu()));
//            textTanggal.setText(dataComunication.getTanggal());
//            database.child("Users").child(dataComunication.getKey()).addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NotNull DataSnapshot snapshot) {
//                    String srcImage = snapshot.child("profileimage").getValue(String.class);
//                    Glide.with(context).load(srcImage).placeholder(R.drawable.ic_baseline_account_circle_24).into(profiluserchat);
//                }
//
//                @Override
//                public void onCancelled(@NotNull DatabaseError error) {
//
//                }
//            });
//
//            if(dataComunication.getKey().equals(preferences.getKeyData(context))){
//                dari.setVisibility(View.GONE);
//                profiluserchat.setVisibility(View.GONE);
//                linear.setGravity(Gravity.CENTER|Gravity.END);
//                linear2.setGravity(Gravity.CENTER|Gravity.END);
//                pesan.setTextColor(context.getColor(android.R.color.white));
//                cardView.setBackground(context.getResources().getDrawable(R.drawable.chat_to));
//            }
//        }

    }

}

