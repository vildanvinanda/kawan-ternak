package com.example.projectkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DaftarHewanSapi extends AppCompatActivity {

    ImageView imgheader, icontgl, backbtnedu;
    EditText kolomsearch;
    RecyclerView recdaftaredu;
    private ArrayList<ProjectModelEdu> projectModelEdus;
    private ProjectArtikelAdapter projectArtikelAdapter;
    DatabaseReference database;
    DatabaseReference database2;
    RelativeLayout empty2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_hewan_sapi);

        imgheader = findViewById(R.id.imgheader);
        icontgl = findViewById(R.id.icontgl);
        backbtnedu = findViewById(R.id.backbtnedu);
        kolomsearch = findViewById(R.id.kolomsearch);
        empty2 = findViewById(R.id.empty2);

        //Recyclerview
        recdaftaredu = findViewById(R.id.recdaftaredu);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recdaftaredu.setLayoutManager(layoutManager);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        projectModelEdus = new ArrayList<>();

        database = FirebaseDatabase.getInstance().getReference().child("Edukasi");

        loadData("");

        backbtnedu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        icontgl.setOnClickListener(new View.OnClickListener() {
            private int mDate,mMonth,mYear;
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        DaftarHewanSapi.this, R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = getLayoutInflater().inflate(
                        R.layout.filter_tanggal, (RelativeLayout) v.findViewById(R.id.bottomSheetCountainerTanggal)
                );

                EditText eddmulaidari = bottomSheetView.findViewById(R.id.eddmulaidari);
                EditText eddsampadengan2 = bottomSheetView.findViewById(R.id.eddsampadengan2);
                Button btncarifiltertanggal = bottomSheetView.findViewById(R.id.btncarifiltertanggal);
//                EditText eddsampadengan = bottomSheetView.findViewById(R.id.eddsampadengan);

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

                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarHewanSapi.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int date) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, date);
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
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

                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarHewanSapi.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int date) {
//                                eddsampadengan2.setText(date+"-"+month+"-"+year);
//                                Date date_maximal = calendar.getTime();
//
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, date);
                                SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
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
                            data1("","");
                        }

                    }
                    private void data1(String eddmulai, String eddsampai) {
                        eddmulai = eddmulaidari.getText().toString();
                        eddsampai = eddsampadengan2.getText().toString();

                        Query queryd = FirebaseDatabase.getInstance().getReference().child("Edukasi").orderByChild("tanggalupload").startAt(eddmulai).endAt(eddsampai+"\uf8ff");


                        queryd.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NotNull DataSnapshot snapshot) {

                                empty2.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

                            }

                            @Override
                            public void onCancelled(@NotNull DatabaseError error) {

                            }
                        });
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

                                        if(types.equals("sapi")){
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

                                                    Intent daftarEdu = new Intent(DaftarHewanSapi.this, DetailEdukasiActivity.class);
                                                    daftarEdu.putExtra("clickEduId", clickEduId);
                                                    startActivity(daftarEdu);
                                                }
                                            });
                                        }
                                    }
                                    @NotNull
                                    @Override
                                    public EdukasiHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemedu, parent, false);
                                        EdukasiHolder holder = new EdukasiHolder(view);
                                        return holder;
                                    }
                                };

                        recdaftaredu.setAdapter(adapter);
                        adapter.startListening();
                    }
                });

                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();

            }

        });

        kolomsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });

    }

    private void filter(String text) {
        Query queryd = FirebaseDatabase.getInstance().getReference().child("Edukasi").orderByChild("judul").startAt(text).endAt(text+"\uf8ff");
        queryd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                empty2.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
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

                        if(types.equals("sapi")){
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

                                    Intent daftarEdu = new Intent(DaftarHewanSapi.this, DetailEdukasiActivity.class);
                                    daftarEdu.putExtra("clickEduId", clickEduId);
                                    startActivity(daftarEdu);

                                    //                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
                                    //                                intent.putExtra("aid", model.getAid());
                                    //                                startActivity(intent);

                                }
                            });
                        }


                    }

                    @NotNull
                    @Override
                    public EdukasiHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemedu, parent, false);
                        EdukasiHolder holder = new EdukasiHolder(view);
                        return holder;
                    }
                };

        recdaftaredu.setAdapter(adapter);
        adapter.startListening();
    }

    private void loadData(String data) {
//        Query query = database.orderByChild("eddTitle").startAt(data).endAt(data+"\uf8ff");
        Query queryd = FirebaseDatabase.getInstance().getReference().child("Edukasi").orderByChild("type").equalTo("sapi");


        queryd.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                empty2.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
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

                                Intent daftarEdu = new Intent(DaftarHewanSapi.this, DetailEdukasiActivity.class);
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

        recdaftaredu.setAdapter(adapter);
        adapter.startListening();


    }
}