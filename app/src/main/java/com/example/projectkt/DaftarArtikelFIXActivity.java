package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DaftarArtikelFIXActivity extends AppCompatActivity {

    RecyclerView recyclerviewartikel2;
    ArrayList<ArtikelModel> artikelModels;
    ProjectArtikelAdapter projectArtikelAdapter;
    ImageView icontglartikel2;
    EditText kolomsearchartikel2;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_artikel_fixactivity);

        icontglartikel2 = findViewById(R.id.icontglartikel2);
        kolomsearchartikel2 = findViewById(R.id.kolomsearchartikel2);

        recyclerviewartikel2 = findViewById(R.id.recyclerviewartikel2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerviewartikel2.setLayoutManager(layoutManager);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("ArtikelFix");

        artikelModels = new ArrayList<>();

        icontglartikel2.setOnClickListener(new View.OnClickListener() {
            private int mDate,mMonth,mYear;
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        DaftarArtikelFIXActivity.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = getLayoutInflater().inflate(
                        R.layout.filter_tanggal, (RelativeLayout) v.findViewById(R.id.bottomSheetCountainerTanggal)
                );

                EditText eddmulaidari = bottomSheetView.findViewById(R.id.eddmulaidari);
                EditText eddsampadengan2 = bottomSheetView.findViewById(R.id.eddsampadengan2);
                Button btncarifiltertanggal = bottomSheetView.findViewById(R.id.btncarifiltertanggal);

                bottomSheetView.findViewById(R.id.btnclosefilter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        bottomSheetDialog.dismiss();
                    }
                });
                bottomSheetView.findViewById(R.id.ictglartikelfilter).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v)
                    {
                        final Calendar calendar = Calendar.getInstance();
                        mDate = calendar.get(Calendar.DATE);
                        mMonth = calendar.get(Calendar.MONTH);
                        mYear = calendar.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelFIXActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int date) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, date);
                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                                String dateString = format.format(calendar.getTime());
                                eddmulaidari.setText(dateString);


                                String cek = eddmulaidari.getText().toString();

                                String edsama = eddsampadengan2.getText().toString();
                                if (TextUtils.isEmpty(cek))
                                {
                                    eddmulaidari.setError("Tolong Dilengkapi");
                                    eddmulaidari.requestFocus();
                                } else if (TextUtils.isEmpty(edsama))
                                {
                                    eddsampadengan2.setError("Tolong Dilengkapi");
                                    eddmulaidari.requestFocus();
                                }
                                else {
                                    btncarifiltertanggal.setEnabled(true);
                                }
                            }

                        },mYear, mMonth, mDate);
                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                        datePickerDialog.show();
                    }

                });
                bottomSheetView.findViewById(R.id.ictglartikelfilter22).setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v)
                    {
                        final Calendar calendar = Calendar.getInstance();
                        mDate = calendar.get(Calendar.DATE);
                        mMonth = calendar.get(Calendar.MONTH);
                        mYear = calendar.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelFIXActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int date) {
//                                eddsampadengan2.setText(date+"-"+month+"-"+year);
//                                Date date_maximal = calendar.getTime();
//
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, date);
                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                                String dateString = format.format(calendar.getTime());
                                eddsampadengan2.setText(dateString);

                                String cek = eddmulaidari.getText().toString();

                                String edsama = eddsampadengan2.getText().toString();
                                if (TextUtils.isEmpty(cek))
                                {
                                    eddmulaidari.setError("Tolong Dilengkapi");
                                    eddmulaidari.requestFocus();
                                } else if (TextUtils.isEmpty(edsama))
                                {
                                    eddsampadengan2.setError("Tolong Dilengkapi");
                                    eddmulaidari.requestFocus();
                                }
                                else {
                                    btncarifiltertanggal.setEnabled(true);
                                }


                            }
                        },mYear, mMonth, mDate);
                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
                        datePickerDialog.show();
                    }

                });
                bottomSheetView.findViewById(R.id.btncarifiltertanggal).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(DaftarArtikelActivity.this, "btn Cari sudah di click", Toast.LENGTH_SHORT).show();

                        String InputTanggal = eddmulaidari.getText().toString();
                        String InputTanggal2 = eddsampadengan2.getText().toString();
                        if (TextUtils.isEmpty(InputTanggal))
                        {
                            eddmulaidari.setError("Tolong Dilengkapi");
                            eddmulaidari.requestFocus();
//                            btncarifiltertanggal.setEnabled(false);
                        } else if (TextUtils.isEmpty(InputTanggal2))
                        {
                            eddsampadengan2.setError("Tolong Dilengkapi");
                            eddsampadengan2.requestFocus();
//                            btncarifiltertanggal.setEnabled(false);
                        } else {
//                            btncarifiltertanggal.setEnabled(true);
                            loadData4();
                        }

                    }
                    private void loadData4() {
                        String eddmulai = eddmulaidari.getText().toString();
                        String eddsampai = eddsampadengan2.getText().toString();

                        Query queryd = FirebaseDatabase.getInstance().getReference().child("Edukasi").orderByChild("tanggalupload").startAt(eddmulai).endAt(eddsampai+"\uf8ff");



                        FirebaseRecyclerOptions<ProjectModelEdu> options =
                                new FirebaseRecyclerOptions.Builder<ProjectModelEdu>()
                                        .setQuery(queryd, ProjectModelEdu.class)
                                        .build();

                        FirebaseRecyclerAdapter<ProjectModelEdu, EdukasiHolder> adapter =
                                new FirebaseRecyclerAdapter<ProjectModelEdu, EdukasiHolder>(options) {


                                    @Override
                                    protected void onBindViewHolder(@NotNull EdukasiHolder holder, int position, @NotNull ProjectModelEdu model) {
                                        String bgcard1 = model.getJudul();
                                        String types = model.getType();

                                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));


                                        holder.juduledu.setText(model.getJudul());
                                        holder.subedu.setText(model.getIsi());
                                        holder.tgledu.setText(model.getTanggalupload());
                                        Glide.with(holder.imageedu.getContext())
                                                .load(model.getGambar())
                                                .centerCrop()
                                                .into(holder.imageedu);
                                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                String clickEduId = getRef(position).getKey();

                                                Intent daftarEdu = new Intent(DaftarArtikelFIXActivity.this, DetailEdukasiActivity.class);
                                                daftarEdu.putExtra("clickEduId", clickEduId);
                                                startActivity(daftarEdu);

//                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
//                                intent.putExtra("aid", model.getAid());
//                                startActivity(intent);

                                            }
                                        });

                                    }

                                    @NotNull
                                    @Override
                                    public EdukasiHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemedu, parent, false);
                                        EdukasiHolder holder = new EdukasiHolder(view);
                                        return holder;
                                    }
                                };

                        recyclerviewartikel2.setAdapter(adapter);
                        adapter.startListening();
                    }

                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        loadData("");
    }
    private void loadData(String data) {
//        Query query = database.orderByChild("eddTitle").startAt(data).endAt(data+"\uf8ff");
        Query queryd = databaseReference.orderByChild("judulartikel").startAt(data).endAt(data+"\uf8ff");

        FirebaseRecyclerOptions<ArtikelModel> options =
                new FirebaseRecyclerOptions.Builder<ArtikelModel>()
                        .setQuery(queryd, ArtikelModel.class)
                        .build();

        FirebaseRecyclerAdapter<ArtikelModel, ArtikelHolderFix> adapter =
                new FirebaseRecyclerAdapter<ArtikelModel, ArtikelHolderFix>(options) {


                    @Override
                    protected void onBindViewHolder(@NotNull ArtikelHolderFix holder, int position, @NotNull ArtikelModel model) {


//                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
                        holder.judulartikelitem.setText(model.getJudulartikel());
                        holder.subartikelitem.setText(model.getIsiartikel());
                        holder.tglartikelitem.setText(model.getTglartikel());
                        Glide.with(holder.imageartikel.getContext())
                                .load(model.getGambarartikel())
                                .centerCrop().placeholder(R.drawable.load1)
                                .into(holder.imageartikel);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickArtikelId = getRef(position).getKey();

                                Intent daftarArtikel = new Intent(DaftarArtikelFIXActivity.this, DetailArtikelActivity.class);
                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
                                startActivity(daftarArtikel);
                            }
                        });
                    }

                    @NotNull
                    @Override
                    public ArtikelHolderFix onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artikel, parent, false);
                        ArtikelHolderFix holder = new ArtikelHolderFix(view);
                        return holder;
                    }
                };

        recyclerviewartikel2.setAdapter(adapter);
        adapter.startListening();


    }
}