package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NotificatonActivity extends AppCompatActivity {

    Context context;

    ImageView btnback;

    RecyclerView recviewnotif;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    ArrayList<NotifikasiModel> notifikasiModels;
    FirebaseDatabase firebaseDatabase;
    RelativeLayout emptynotif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaton);

        recviewnotif = findViewById(R.id.recviewnotif);
        emptynotif = findViewById(R.id.emptynotif);

        btnback = findViewById(R.id.backbtnnotif);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //RecyclerVIew
        LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        recviewnotif.setLayoutManager(layoutManager);

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        notifikasiModels = new ArrayList<>();

    }

    @Override
    public void onStart(){
        super.onStart();
        notif("");
    }

    private void notif(String data) {
        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Notifikasi").orderByChild("idn").startAt(data).endAt(data+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                emptynotif.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<NotifikasiModel> options =
                new FirebaseRecyclerOptions.Builder<NotifikasiModel>()
                        .setQuery(query, NotifikasiModel.class)
                        .build();

//         adapterart = new adapterartikel(options);
//         recview.setAdapter(adapterart);

        FirebaseRecyclerAdapter<NotifikasiModel, NotifHolder> adapter =
                new FirebaseRecyclerAdapter<NotifikasiModel, NotifHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NotNull NotifHolder holder, int position, @NotNull NotifikasiModel model) {
                        holder.txtjudulnotif.setText(model.getJudulnotif());
                        holder.txttglnotif.setText(model.getTglnotif());
                        holder.isinotif.setText(model.getIsintif());
                        Glide.with(holder.imgnotif.getContext())
                                .load(model.getLogonotif())
                                .centerCrop()
                                .into(holder.imgnotif);
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickclickNotifIdId = getRef(position).getKey();
                                Intent detailNotif = new Intent(NotificatonActivity.this, DetailNotificationActivity.class);
                                detailNotif.putExtra("clickNotifId", clickclickNotifIdId);
                                startActivity(detailNotif);
                            }
                        });

                        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                            @Override
                            public boolean onLongClick(View v) {

                                showDeletDataDialog("");

                                return false;
                            }

                            private void showDeletDataDialog(String s) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(NotificatonActivity.this);
                                builder.setTitle("Hapus");
                                builder.setMessage("Anda yakin untuk menghapus pesan ini?");
                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Notifikasi").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NotNull Task<Void> task) {
                                                Toast.makeText(NotificatonActivity.this, "Notifikasi Telah Dihapus", Toast.LENGTH_SHORT).show();
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

                    }

                    @NotNull
                    @Override
                    public NotifHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notif, parent, false);
                        NotifHolder holder = new NotifHolder(view);
                        return holder;
                    }
                };
        recviewnotif.setAdapter(adapter);
        adapter.startListening();
    }

}