//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.sql.DriverManager;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Locale;
//
//public class DaftarArtikelActivity extends AppCompatActivity {
//
//
//    Locale id = new Locale("in", "ID");
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyy", id);
//
//    RecyclerView recyclerviewartikel;
//    private ArrayList<ProjectModel>projectModellist;
//    private ProjectArtikelAdapter projectArtikelAdapter;
//
//    private ImageView icontglartikel;
//
//    private DatabaseReference databaseReference;
//    private StorageReference storageReference;
//
//    private Context context;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_daftar_artikel);
//
//        icontglartikel = findViewById(R.id.icontglartikel);
//
//        recyclerviewartikel = findViewById(R.id.recyclerviewartikel);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerviewartikel.setLayoutManager(layoutManager);
//
//        layoutManager.setStackFromEnd(true);
//        layoutManager.setReverseLayout(true);
//
//        projectModellist = new ArrayList<>();
//
//
//        icontglartikel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                  DaftarArtikelActivity.this, R.style.BottomSheetDialogTheme
//                );
//                View bottomSheetView = LayoutInflater.from(getApplicationContext())
//                        .inflate(
//                                R.layout.filter_tanggal, (RelativeLayout) findViewById(R.id.bottomSheetCountainerTanggal)
//                        );
//                bottomSheetView.findViewById(R.id.btncarifiltertanggal).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(DaftarArtikelActivity.this, "btn Cari sudah di click", Toast.LENGTH_SHORT).show();
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
//            }
//        });
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//
//        FirebaseRecyclerOptions<ProjectModel> options =
//                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Artikel"), ProjectModel.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
//                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {
//
//
//                    @Override
//                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
//                        holder.txtjudul9solo.setText(model.getEddTitle());
//                        holder.txttgl6solo.setText(model.getEddttl());
//                        Glide.with(holder.image1.getContext())
//                                .load(model.getUploadgambarartikel())
//                                .centerCrop()
//                                .into(holder.image1);
//
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                String clickArtikelId = getRef(position).getKey();
//
//                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
//                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
//                                startActivity(daftarArtikel);
//
////                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
////                                intent.putExtra("aid", model.getAid());
////                                startActivity(intent);
//
//                            }
//                        });
//                    }
//
//                    @NotNull
//                    @Override
//                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
//                        ArtikelHolder holder = new ArtikelHolder(view);
//                        return holder;
//                    }
//                };
//        recyclerviewartikel.setAdapter(adapter);
//        adapter.startListening();
//
//    }
//
//    public static class ArtikelHolder extends RecyclerView.ViewHolder
//    {
//        ImageView image1;
//        TextView txtjudul9solo,txttgl6solo,isiArtikel;
//
//        public ArtikelHolder(@NotNull View itemView) {
//            super(itemView);
//
//            image1 = itemView.findViewById(R.id.image1);
//            txtjudul9solo = itemView.findViewById(R.id.txtjudul9solo);
//            txttgl6solo = itemView.findViewById(R.id.txttgl6solo);
//            isiArtikel = itemView.findViewById(R.id.isiArtikel);
//
//        }
//    }
//}

//Baru

//ini sudah berhasil yakkk
//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.DatePickerDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.sql.DriverManager;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//
//public class DaftarArtikelActivity extends AppCompatActivity {
//
//    Locale id = new Locale("in", "ID");
//    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy", id);
//
//    RecyclerView recyclerviewartikel;
//    private ArrayList<ProjectModel>projectModellist;
//    private ProjectArtikelAdapter projectArtikelAdapter;
//
//    private ImageView icontglartikel;
//    private EditText kolomsearchartikel, kolomsearchartikel2;
//
//    Button btncarifiltertanggal;
//
//    private DatabaseReference databaseReference;
//    private StorageReference storageReference;
//
//    private Context context;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_daftar_artikel);
//
//        icontglartikel = findViewById(R.id.icontglartikel);
//
//
//        kolomsearchartikel = findViewById(R.id.kolomsearchartikel);
////        kolomsearchartikel2 = findViewById(R.id.kolomsearchartikel2);
//
//
//        btncarifiltertanggal = findViewById(R.id.btncarifiltertanggal);
//
//        recyclerviewartikel = findViewById(R.id.recyclerviewartikel);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerviewartikel.setLayoutManager(layoutManager);
//
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Artikel");
//        layoutManager.setStackFromEnd(true);
//        layoutManager.setReverseLayout(true);
//
//        projectModellist = new ArrayList<>();
//
//        icontglartikel.setOnClickListener(new View.OnClickListener() {
//            private int mDate,mMonth,mYear;
//            @Override
//            public void onClick(View v) {
//
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                        DaftarArtikelActivity.this, R.style.BottomSheetDialogTheme
//                );
//                View bottomSheetView = LayoutInflater.from(getApplicationContext())
//                        .inflate(
//                        R.layout.filter_tanggal, (RelativeLayout) v.findViewById(R.id.bottomSheetCountainerTanggal)
//                );
//
//                EditText eddmulaidari = bottomSheetView.findViewById(R.id.eddmulaidari);
//                EditText eddsampadengan2 = bottomSheetView.findViewById(R.id.eddsampadengan2);
//                Button btncarifiltertanggal = bottomSheetView.findViewById(R.id.btncarifiltertanggal);
//
//
////                EditText eddsampadengan = bottomSheetView.findViewById(R.id.eddsampadengan);
//
//                bottomSheetView.findViewById(R.id.btnclosefilter).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
////                eddmulaidari.addTextChangedListener(new TextWatcher() {
////                    @Override
////                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////
////                    }
////
////                    @Override
////                    public void onTextChanged(CharSequence s, int start, int before, int count) {
////
////                    }
////
////                    @Override
////                    public void afterTextChanged(Editable s) {
////                        if (s.toString()!=null)
////                        {
////                            loadData4(s.toString());
////                        }
////                        else
////                        {
////                            loadData4("");
////                        }
////                    }
////
////                });
//
////                eddsampadengan.addTextChangedListener(new TextWatcher() {
////                    @Override
////                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////
////                    }
////
////                    @Override
////                    public void onTextChanged(CharSequence s, int start, int before, int count) {
////
////                    }
////
////                    @Override
////                    public void afterTextChanged(Editable s) {
////                        if (s.toString()!=null)
////                        {
////                            loadData3(s.toString());
////                        }
////                        else
////                        {
////                            loadData3("");
////                        }
////                    }
////
////                    private void loadData3(String data3) {
////
////                            Query query3 = databaseReference.orderByChild("eddttl").startAt(data3).endAt(data3+"\uf8ff");
////
////                            FirebaseRecyclerOptions<ProjectModel> options3 =
////                                    new FirebaseRecyclerOptions.Builder<ProjectModel>()
////                                            .setQuery(query3, ProjectModel.class)
////                                            .build();
////
////                            FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter3 =
////                                    new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options3) {
////
////
////                                        @Override
////                                        protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
////                                            holder.txtjudul9solo.setText(model.getEddTitle());
////                                            holder.txttgl6solo.setText(model.getEddttl());
////                                            Glide.with(holder.image1.getContext())
////                                                    .load(model.getUploadgambarartikel())
////                                                    .centerCrop()
////                                                    .into(holder.image1);
////
////                                            holder.itemView.setOnClickListener(new View.OnClickListener() {
////                                                @Override
////                                                public void onClick(View v)
////                                                {
////                                                    String clickArtikelId = getRef(position).getKey();
////
////                                                    Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
////                                                    daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
////                                                    startActivity(daftarArtikel);
////
////                                                }
////                                            });
////                                        }
////
////                                        @NotNull
////                                        @Override
////                                        public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
////                                            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
////                                            ArtikelHolder holder = new ArtikelHolder(view);
////                                            return holder;
////                                        }
////                                    };
////
////                            recyclerviewartikel.setAdapter(adapter3);
////                            adapter3.startListening();
////
////                        }
////
////                });
//                bottomSheetView.findViewById(R.id.ictglartikelfilter).setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v)
//                    {
//                        final Calendar calendar = Calendar.getInstance();
//                        mDate = calendar.get(Calendar.DATE);
//                        mMonth = calendar.get(Calendar.MONTH);
//                        mYear = calendar.get(Calendar.YEAR);
//
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int date) {
//                                Calendar calendar = Calendar.getInstance();
//                                calendar.set(year, month, date);
//                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
//                                String dateString = format.format(calendar.getTime());
//                                eddmulaidari.setText(dateString);
//
//
//                                String cek = eddmulaidari.getText().toString();
//
//                                String edsama = eddsampadengan2.getText().toString();
//                                if (TextUtils.isEmpty(cek))
//                                {
//                                    eddmulaidari.setError("Tolong Dilengkapi");
//                                    eddmulaidari.requestFocus();
//                                } else if (TextUtils.isEmpty(edsama))
//                                {
//                                    eddsampadengan2.setError("Tolong Dilengkapi");
//                                    eddmulaidari.requestFocus();
//                                }
//                                else {
//                                    btncarifiltertanggal.setEnabled(true);
//                                }
//                            }
//
//                        },mYear, mMonth, mDate);
//                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
//                        datePickerDialog.show();
//                    }
//
//                });
//
//                bottomSheetView.findViewById(R.id.ictglartikelfilter22).setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v)
//                    {
//                        final Calendar calendar = Calendar.getInstance();
//                        mDate = calendar.get(Calendar.DATE);
//                        mMonth = calendar.get(Calendar.MONTH);
//                        mYear = calendar.get(Calendar.YEAR);
//
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int date) {
////                                eddsampadengan2.setText(date+"-"+month+"-"+year);
////                                Date date_maximal = calendar.getTime();
////
//                                Calendar calendar = Calendar.getInstance();
//                                calendar.set(year, month, date);
//                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
//                                String dateString = format.format(calendar.getTime());
//                                eddsampadengan2.setText(dateString);
//
//                                String cek = eddmulaidari.getText().toString();
//
//                                String edsama = eddsampadengan2.getText().toString();
//                                if (TextUtils.isEmpty(cek))
//                                {
//                                    eddmulaidari.setError("Tolong Dilengkapi");
//                                    eddmulaidari.requestFocus();
//                                } else if (TextUtils.isEmpty(edsama))
//                                {
//                                    eddsampadengan2.setError("Tolong Dilengkapi");
//                                    eddmulaidari.requestFocus();
//                                }
//                                else {
//                                    btncarifiltertanggal.setEnabled(true);
//                                }
//
//
//                            }
//                        },mYear, mMonth, mDate);
//                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
//                        datePickerDialog.show();
//                    }
//
//                });
//
////                bottomSheetView.findViewById(R.id.ictglartikelfilter2).setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v)
////                    {
////                        final Calendar calendar = Calendar.getInstance();
////                        mDate = calendar.get(Calendar.DATE);
////                        mMonth = calendar.get(Calendar.MONTH);
////                        mYear = calendar.get(Calendar.YEAR);
////
////                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
////                            @Override
////                            public void onDateSet(DatePicker view, int year, int month, int date) {
////                                eddsampadengan.setText(date+"-"+month+"-"+year);
////                                date_maximal = calendar.getTime();
////
////                            }
////                        },mYear, mMonth, mDate);
////                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
////                        datePickerDialog.show();
////                    }
////
////
////                });
//                bottomSheetView.findViewById(R.id.btncarifiltertanggal).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Toast.makeText(DaftarArtikelActivity.this, "btn Cari sudah di click", Toast.LENGTH_SHORT).show();
//
//                        String InputTanggal = eddmulaidari.getText().toString();
//                        String InputTanggal2 = eddsampadengan2.getText().toString();
//                        if (TextUtils.isEmpty(InputTanggal))
//                        {
//                            eddmulaidari.setError("Tolong Dilengkapi");
//                            eddmulaidari.requestFocus();
////                            btncarifiltertanggal.setEnabled(false);
//                        } else if (TextUtils.isEmpty(InputTanggal2))
//                        {
//                            eddsampadengan2.setError("Tolong Dilengkapi");
//                            eddsampadengan2.requestFocus();
////                            btncarifiltertanggal.setEnabled(false);
//                        } else {
////                            btncarifiltertanggal.setEnabled(true);
//                            loadData4();
//                        }
//
//                    }
//                    private void loadData4() {
//                        String eddmulai = eddmulaidari.getText().toString();
//                        String eddsampai = eddsampadengan2.getText().toString();
//                        Query query = FirebaseDatabase.getInstance().getReference().child("Artikel").orderByChild("eddttl").startAt(eddmulai).endAt(eddsampai+"\uf8ff");
//
//                        FirebaseRecyclerOptions<ProjectModel> options =
//                                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//                                        .setQuery(query, ProjectModel.class)
//                                        .build();
//
//                        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
//                                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {
//
//
//                                    @Override
//                                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
//                                        String bgcard1 = model.getEddTitle();
//
////                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
//                                        holder.txtjudul9solo.setText(model.getEddTitle());
//                                        holder.txttgl6solo.setText(model.getEddttl());
//                                        Glide.with(holder.image1.getContext())
//                                                .load(model.getUploadgambarartikel())
//                                                .centerCrop().placeholder(R.drawable.load1)
//                                                .into(holder.image1);
//
//                                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v)
//                                            {
//                                                String clickArtikelId = getRef(position).getKey();
//
//                                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
//                                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
//                                                startActivity(daftarArtikel);
//                                            }
//                                        });
//                                    }
//
//                                    @NotNull
//                                    @Override
//                                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//                                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
//                                        ArtikelHolder holder = new ArtikelHolder(view);
//                                        return holder;
//                                    }
//                                };
//
//                        recyclerviewartikel.setAdapter(adapter);
//                        adapter.startListening();
//                    }
//
//                });
//
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
//            }
//        });
//
////        loadData2("");
//        kolomsearchartikel.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString()!=null)
//                {
//                    loadData(s.toString());
//                }
//                else
//                {
//                    loadData("");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
////        kolomsearchartikel2.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence s, int start, int before, int count) {
////
////            }
////
////            @Override
////            public void afterTextChanged(Editable s) {
////                if (s.toString()!=null)
////                {
////                    loadData(s.toString());
////                }
////                else
////                {
////                    loadData("");
////                }
////            }
////        });
//    }
//    @Override
//    public void onStart(){
//        super.onStart();
//        loadData("");
//    }
//
//    private void loadData(String data) {
//        Query query = databaseReference.orderByChild("eddTitle").startAt(data).endAt(data+"\uf8ff");
////        Query query2 = databaseReference.orderByChild("eddttl").startAt(data).endAt(data+"\uf8ff");
//
//
//        FirebaseRecyclerOptions<ProjectModel> options =
//                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//                        .setQuery(query, ProjectModel.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
//                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {
//
//
//                    @Override
//                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
//                        String bgcard1 = model.getEddTitle();
//
////                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
//                        holder.txtjudul9solo.setText(model.getEddTitle());
//                        holder.txtsub9solo.setText(model.getEddIsiArtikel());
//                        holder.txttgl6solo.setText(model.getEddttl());
//                        Glide.with(holder.image1.getContext())
//                                .load(model.getUploadgambarartikel())
//                                .centerCrop().placeholder(R.drawable.load1)
//                                .into(holder.image1);
//
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                String clickArtikelId = getRef(position).getKey();
//
//                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
//                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
//                                startActivity(daftarArtikel);
//
////                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
////                                intent.putExtra("aid", model.getAid());
////                                startActivity(intent);
//
//                            }
//                        });
//                    }
//
//
//                    @NotNull
//                    @Override
//                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
//                        ArtikelHolder holder = new ArtikelHolder(view);
//                        return holder;
//                    }
//                };
//
////        FirebaseRecyclerOptions<ProjectModel> options2 =
////                new FirebaseRecyclerOptions.Builder<ProjectModel>()
////                        .setQuery(query2, ProjectModel.class)
////                        .build();
////
////        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter2 =
////                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options2) {
////
////
////                    @Override
////                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
////                        holder.txtjudul9solo.setText(model.getEddTitle());
////                        holder.txttgl6solo.setText(model.getEddttl());
////                        Glide.with(holder.image1.getContext())
////                                .load(model.getUploadgambarartikel())
////                                .centerCrop()
////                                .into(holder.image1);
////
////                        holder.itemView.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v)
////                            {
////                                String clickArtikelId = getRef(position).getKey();
////
////                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
////                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
////                                startActivity(daftarArtikel);
////
//////                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
//////                                intent.putExtra("aid", model.getAid());
//////                                startActivity(intent);
////
////                            }
////                        });
////                    }
////
////                    @NotNull
////                    @Override
////                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
////                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
////                        ArtikelHolder holder = new ArtikelHolder(view);
////                        return holder;
////                    }
////                };
//
//        recyclerviewartikel.setAdapter(adapter);
//        adapter.startListening();
//
//
//    }
//
//
////    private void loadData2(String data2) {
////        Query query2 = databaseReference.orderByChild("eddttl").startAt(data2).endAt(data2+"\uf8ff");
////
////
////        FirebaseRecyclerOptions<ProjectModel> options2 =
////                new FirebaseRecyclerOptions.Builder<ProjectModel>()
////                        .setQuery(query2, ProjectModel.class)
////                        .build();
////
////        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter2 =
////                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options2) {
////
////                    @Override
////                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
////                        holder.txtjudul9solo.setText(model.getEddTitle());
////                        holder.txttgl6solo.setText(model.getEddttl());
////                        Glide.with(holder.image1.getContext())
////                                .load(model.getUploadgambarartikel())
////                                .centerCrop()
////                                .into(holder.image1);
////
////                        holder.itemView.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v)
////                            {
////                                String clickArtikelId = getRef(position).getKey();
////
////                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
////                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
////                                startActivity(daftarArtikel);
////
//////                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
//////                                intent.putExtra("aid", model.getAid());
//////                                startActivity(intent);
////
////                            }
////                        });
////                    }
////
////                    @NotNull
////                    @Override
////                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
////                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
////                        ArtikelHolder holder = new ArtikelHolder(view);
////                        return holder;
////                    }
////                };
////
//////        FirebaseRecyclerOptions<ProjectModel> options2 =
//////                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//////                        .setQuery(query2, ProjectModel.class)
//////                        .build();
//////
//////        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter2 =
//////                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options2) {
//////
//////
//////                    @Override
//////                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
//////                        holder.txtjudul9solo.setText(model.getEddTitle());
//////                        holder.txttgl6solo.setText(model.getEddttl());
//////                        Glide.with(holder.image1.getContext())
//////                                .load(model.getUploadgambarartikel())
//////                                .centerCrop()
//////                                .into(holder.image1);
//////
//////                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//////                            @Override
//////                            public void onClick(View v)
//////                            {
//////                                String clickArtikelId = getRef(position).getKey();
//////
//////                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
//////                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
//////                                startActivity(daftarArtikel);
//////
////////                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
////////                                intent.putExtra("aid", model.getAid());
////////                                startActivity(intent);
//////
//////                            }
//////                        });
//////                    }
//////
//////                    @NotNull
//////                    @Override
//////                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//////                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
//////                        ArtikelHolder holder = new ArtikelHolder(view);
//////                        return holder;
//////                    }
//////                };
////
////        recyclerviewartikel.setAdapter(adapter2);
////        adapter2.startListening();
////
////
////    }
//
////    public static class ArtikelHolder extends RecyclerView.ViewHolder
////    {
////        ImageView image1;
////        TextView txtjudul9solo,txttgl6solo,isiArtikel,txtsub9solo;
////        RelativeLayout bgcard;
////
////        public ArtikelHolder(@NotNull View itemView) {
////            super(itemView);
////
////            bgcard = itemView.findViewById(R.id.bgcard);
////            image1 = itemView.findViewById(R.id.image1);
////            txtjudul9solo = itemView.findViewById(R.id.txtjudul9solo);
////            txttgl6solo = itemView.findViewById(R.id.txttgl6solo);
////            isiArtikel = itemView.findViewById(R.id.isiArtikel);
////            txtsub9solo = itemView.findViewById(R.id.txtsub9solo);
////        }
////
////    }
//
//
//}


////INI BERHASIL
//package com.example.projectkt;
//
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.DatePickerDialog;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextUtils;
//import android.text.TextWatcher;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.material.bottomsheet.BottomSheetDialog;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.firestore.QueryDocumentSnapshot;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.sql.DriverManager;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.Locale;
//
//public class DaftarArtikelActivity extends AppCompatActivity {
//
//    RecyclerView recyclerviewartikel;
//    private ArrayList<ProjectModel>projectModellist;
//    private ProjectArtikelAdapter projectArtikelAdapter;
//    RelativeLayout empty3;
//
//    private ImageView icontglartikel, backbtnkeuangan;
//    private EditText kolomsearchartikel;
//
//    private DatabaseReference databaseReference;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_daftar_artikel);
//
//        icontglartikel = findViewById(R.id.icontglartikel);
//        backbtnkeuangan = findViewById(R.id.backbtnkeuangan);
//        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//
//        kolomsearchartikel = findViewById(R.id.kolomsearchartikel);
//        empty3 = findViewById(R.id.empty3);
//
//        recyclerviewartikel = findViewById(R.id.recyclerviewartikel);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        recyclerviewartikel.setLayoutManager(layoutManager);
//        layoutManager.setStackFromEnd(true);
//        layoutManager.setReverseLayout(true);
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Artikel");
//
//        projectModellist = new ArrayList<>();
//
//        icontglartikel.setOnClickListener(new View.OnClickListener() {
//            private int mDate,mMonth,mYear;
//            @Override
//            public void onClick(View v) {
//
//                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
//                        DaftarArtikelActivity.this, R.style.BottomSheetDialogTheme
//                );
//                View bottomSheetView = getLayoutInflater().inflate(
//                        R.layout.filter_tanggal, (RelativeLayout) v.findViewById(R.id.bottomSheetCountainerTanggal)
//                );
//
//                EditText eddmulaidari = bottomSheetView.findViewById(R.id.eddmulaidari);
//                EditText eddsampadengan2 = bottomSheetView.findViewById(R.id.eddsampadengan2);
//                Button btncarifiltertanggal = bottomSheetView.findViewById(R.id.btncarifiltertanggal);
//
//                bottomSheetView.findViewById(R.id.btnclosefilter).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        bottomSheetDialog.dismiss();
//                    }
//                });
//                bottomSheetView.findViewById(R.id.ictglartikelfilter).setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v)
//                    {
//                        final Calendar calendar = Calendar.getInstance();
//                        mDate = calendar.get(Calendar.DATE);
//                        mMonth = calendar.get(Calendar.MONTH);
//                        mYear = calendar.get(Calendar.YEAR);
//
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int date) {
//                                Calendar calendar = Calendar.getInstance();
//                                calendar.set(year, month, date);
//                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
//                                String dateString = format.format(calendar.getTime());
//                                eddmulaidari.setText(dateString);
//
//
//                                String cek = eddmulaidari.getText().toString();
//
//                                String edsama = eddsampadengan2.getText().toString();
//                                if (TextUtils.isEmpty(cek))
//                                {
//                                    eddmulaidari.setError("Tolong Dilengkapi");
//                                    eddmulaidari.requestFocus();
//                                } else if (TextUtils.isEmpty(edsama))
//                                {
//                                    eddsampadengan2.setError("Tolong Dilengkapi");
//                                    eddmulaidari.requestFocus();
//                                }
//                                else {
//                                    btncarifiltertanggal.setEnabled(true);
//                                }
//                            }
//
//                        },mYear, mMonth, mDate);
//                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
//                        datePickerDialog.show();
//                    }
//
//                });
//                bottomSheetView.findViewById(R.id.ictglartikelfilter22).setOnClickListener(new View.OnClickListener() {
//
//                    @Override
//                    public void onClick(View v)
//                    {
//                        final Calendar calendar = Calendar.getInstance();
//                        mDate = calendar.get(Calendar.DATE);
//                        mMonth = calendar.get(Calendar.MONTH);
//                        mYear = calendar.get(Calendar.YEAR);
//
//                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int month, int date) {
////                                eddsampadengan2.setText(date+"-"+month+"-"+year);
////                                Date date_maximal = calendar.getTime();
////
//                                Calendar calendar = Calendar.getInstance();
//                                calendar.set(year, month, date);
//                                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
//                                String dateString = format.format(calendar.getTime());
//                                eddsampadengan2.setText(dateString);
//
//                                String cek = eddmulaidari.getText().toString();
//
//                                String edsama = eddsampadengan2.getText().toString();
//                                if (TextUtils.isEmpty(cek))
//                                {
//                                    eddmulaidari.setError("Tolong Dilengkapi");
//                                    eddmulaidari.requestFocus();
//                                } else if (TextUtils.isEmpty(edsama))
//                                {
//                                    eddsampadengan2.setError("Tolong Dilengkapi");
//                                    eddmulaidari.requestFocus();
//                                }
//                                else {
//                                    btncarifiltertanggal.setEnabled(true);
//                                }
//
//
//                            }
//                        },mYear, mMonth, mDate);
//                        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()-1000);
//                        datePickerDialog.show();
//                    }
//
//                });
//                bottomSheetView.findViewById(R.id.btncarifiltertanggal).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Toast.makeText(DaftarArtikelActivity.this, "btn Cari sudah di click", Toast.LENGTH_SHORT).show();
//
//                        String InputTanggal = eddmulaidari.getText().toString();
//                        String InputTanggal2 = eddsampadengan2.getText().toString();
//                        if (TextUtils.isEmpty(InputTanggal))
//                        {
//                            eddmulaidari.setError("Tolong Dilengkapi");
//                            eddmulaidari.requestFocus();
////                            btncarifiltertanggal.setEnabled(false);
//                        } else if (TextUtils.isEmpty(InputTanggal2))
//                        {
//                            eddsampadengan2.setError("Tolong Dilengkapi");
//                            eddsampadengan2.requestFocus();
////                            btncarifiltertanggal.setEnabled(false);
//                        } else {
////                            btncarifiltertanggal.setEnabled(true);
//                            loadData4();
//                        }
//
//                    }
//                    private void loadData4() {
//                        String eddmulai = eddmulaidari.getText().toString();
//                        String eddsampai = eddsampadengan2.getText().toString();
//                        Query query = FirebaseDatabase.getInstance().getReference().child("Artikel").orderByChild("eddttl").startAt(eddmulai).endAt(eddsampai+"\uf8ff");
//                        query.addValueEventListener(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NotNull DataSnapshot snapshot) {
//
//                                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);
//
//                            }
//
//                            @Override
//                            public void onCancelled(@NotNull DatabaseError error) {
//
//                            }
//                        });
//                        FirebaseRecyclerOptions<ProjectModel> options =
//                                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//                                        .setQuery(query, ProjectModel.class)
//                                        .build();
//
//                        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
//                                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {
//
//
//                                    @Override
//                                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
//                                        String bgcard1 = model.getEddTitle();
//
////                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
//                                        holder.txtjudul9solo.setText(model.getEddTitle());
//                                        holder.txttgl6solo.setText(model.getEddttl());
//                                        Glide.with(holder.image1.getContext())
//                                                .load(model.getUploadgambarartikel())
//                                                .centerCrop().placeholder(R.drawable.load1)
//                                                .into(holder.image1);
//
//                                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                                            @Override
//                                            public void onClick(View v)
//                                            {
//                                                String clickArtikelId = getRef(position).getKey();
//
//                                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
//                                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
//                                                startActivity(daftarArtikel);
//                                            }
//                                        });
//                                    }
//
//                                    @NotNull
//                                    @Override
//                                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//                                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
//                                        ArtikelHolder holder = new ArtikelHolder(view);
//                                        return holder;
//                                    }
//                                };
//
//                        recyclerviewartikel.setAdapter(adapter);
//                        adapter.startListening();
//                    }
//
//                });
//                bottomSheetDialog.setContentView(bottomSheetView);
//                bottomSheetDialog.show();
//            }
//        });
//
//        kolomsearchartikel.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                filter(s.toString());
//            }
//        });
////        kolomsearchartikel.addTextChangedListener(new TextWatcher() {
////            @Override
////            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
////
////            }
////
////            @Override
////            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                if (s.toString()!=null)
////                {
////                    loadData(s.toString());
////                }
////                else
////                {
////                    loadData("");
////                }
////            }
////
////            @Override
////            public void afterTextChanged(Editable s) {
////
////            }
////        });
//
//    }
//
//    private void filter(String text) {
//        Query query = databaseReference.orderByChild("eddTitle").startAt(text).endAt(text+"\uf8ff");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//
//                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);
//
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
//        FirebaseRecyclerOptions<ProjectModel> options =
//                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//                        .setQuery(query, ProjectModel.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
//                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {
//
//
//                    @Override
//                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
//                        String bgcard1 = model.getEddTitle();
//
////                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
//                        holder.txtjudul9solo.setText(model.getEddTitle());
//                        holder.txtsub9solo.setText(model.getEddIsiArtikel());
//                        holder.txttgl6solo.setText(model.getEddttl());
//                        Glide.with(holder.image1.getContext())
//                                .load(model.getUploadgambarartikel())
//                                .centerCrop().placeholder(R.drawable.load1)
//                                .into(holder.image1);
//
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                String clickArtikelId = getRef(position).getKey();
//
//                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
//                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
//                                startActivity(daftarArtikel);
//
////                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
////                                intent.putExtra("aid", model.getAid());
////                                startActivity(intent);
//
//                            }
//                        });
//                    }
//
//
//                    @NotNull
//                    @Override
//                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
//                        ArtikelHolder holder = new ArtikelHolder(view);
//                        return holder;
//                    }
//                };
//
//        recyclerviewartikel.setAdapter(adapter);
//        adapter.startListening();
//    }
//
//    @Override
//    public void onStart(){
//        super.onStart();
//        loadData("");
//    }
//
//    private void loadData(String data) {
//        Query query = databaseReference.orderByChild("eddTitle").startAt(data).endAt(data+"\uf8ff");
//        query.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//
//                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);
//
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
//        FirebaseRecyclerOptions<ProjectModel> options =
//                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//                        .setQuery(query, ProjectModel.class)
//                        .build();
//
//        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
//                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {
//
//
//                    @Override
//                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
//                        String bgcard1 = model.getEddTitle();
//
////                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
//                        holder.txtjudul9solo.setText(model.getEddTitle());
//                        holder.txtsub9solo.setText(model.getEddIsiArtikel());
//                        holder.txttgl6solo.setText(model.getEddttl());
//                        Glide.with(holder.image1.getContext())
//                                .load(model.getUploadgambarartikel())
//                                .centerCrop().placeholder(R.drawable.load1)
//                                .into(holder.image1);
//
//                        holder.itemView.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v)
//                            {
//                                String clickArtikelId = getRef(position).getKey();
//
//                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
//                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
//                                startActivity(daftarArtikel);
//
////                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
////                                intent.putExtra("aid", model.getAid());
////                                startActivity(intent);
//
//                            }
//                        });
//                    }
//
//
//                    @NotNull
//                    @Override
//                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
//                        ArtikelHolder holder = new ArtikelHolder(view);
//                        return holder;
//                    }
//                };
//
//        recyclerviewartikel.setAdapter(adapter);
//        adapter.startListening();
//
//
//    }
//
//}


package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DaftarArtikelActivity extends AppCompatActivity {

    RecyclerView recyclerviewartikel;
    private ArrayList<ProjectModel>projectModellist;
    private ProjectArtikelAdapter projectArtikelAdapter;
    RelativeLayout empty3;

    private ImageView icontglartikel, backbtnkeuangan;
    private EditText kolomsearchartikel;

    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_artikel);

        icontglartikel = findViewById(R.id.icontglartikel);
        backbtnkeuangan = findViewById(R.id.backbtnkeuangan);
        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        kolomsearchartikel = findViewById(R.id.kolomsearchartikel);
        empty3 = findViewById(R.id.empty3);

        recyclerviewartikel = findViewById(R.id.recyclerviewartikel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerviewartikel.setLayoutManager(layoutManager);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Artikel");

        projectModellist = new ArrayList<>();

        icontglartikel.setOnClickListener(new View.OnClickListener() {
            private int mDate,mMonth,mYear;
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        DaftarArtikelActivity.this, R.style.BottomSheetDialogTheme
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
                //awal
                bottomSheetView.findViewById(R.id.ictglartikelfilter).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        final Calendar calendar = Calendar.getInstance();
                        mDate = calendar.get(Calendar.DATE);
                        mMonth = calendar.get(Calendar.MONTH);
                        mYear = calendar.get(Calendar.YEAR);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int date) {
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, date);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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

                        DatePickerDialog datePickerDialog = new DatePickerDialog(DaftarArtikelActivity.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int date) {
//                                eddsampadengan2.setText(date+"-"+month+"-"+year);
//                                Date date_maximal = calendar.getTime();
//
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(year, month, date);
                                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
                        Query query = FirebaseDatabase.getInstance().getReference().child("Artikel").orderByChild("eddttl").startAt(eddmulai).endAt(eddsampai+"\uf8ff");
                        query.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NotNull DataSnapshot snapshot) {

                                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

                            }

                            @Override
                            public void onCancelled(@NotNull DatabaseError error) {

                            }
                        });
                        FirebaseRecyclerOptions<ProjectModel> options =
                                new FirebaseRecyclerOptions.Builder<ProjectModel>()
                                        .setQuery(query, ProjectModel.class)
                                        .build();

                        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
                                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {


                                    @Override
                                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
                                        String bgcard1 = model.getEddTitle();

//                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
                                        holder.txtjudul9solo.setText(model.getEddTitle());
                                        holder.txttgl6solo.setText(model.getEddttl());
                                        Glide.with(holder.image1.getContext())
                                                .load(model.getUploadgambarartikel())
                                                .centerCrop().placeholder(R.drawable.load1)
                                                .into(holder.image1);

                                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                String clickArtikelId = getRef(position).getKey();

                                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
                                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
                                                startActivity(daftarArtikel);
                                            }
                                        });
                                    }

                                    @NotNull
                                    @Override
                                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
                                        ArtikelHolder holder = new ArtikelHolder(view);
                                        return holder;
                                    }
                                };

                        recyclerviewartikel.setAdapter(adapter);
                        adapter.startListening();
                    }

                });
                bottomSheetDialog.setContentView(bottomSheetView);
                bottomSheetDialog.show();
            }
        });

        kolomsearchartikel.addTextChangedListener(new TextWatcher() {
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
//        kolomsearchartikel.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if (s.toString()!=null)
//                {
//                    loadData(s.toString());
//                }
//                else
//                {
//                    loadData("");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

    }

    private void filter(String text) {
        Query query = databaseReference.orderByChild("eddTitle").startAt(text).endAt(text+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<ProjectModel> options =
                new FirebaseRecyclerOptions.Builder<ProjectModel>()
                        .setQuery(query, ProjectModel.class)
                        .build();

        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {


                    @Override
                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
                        String bgcard1 = model.getEddTitle();

//                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
                        holder.txtjudul9solo.setText(model.getEddTitle());
                        holder.txtsub9solo.setText(model.getEddIsiArtikel());
                        holder.txttgl6solo.setText(model.getEddttl());
                        Glide.with(holder.image1.getContext())
                                .load(model.getUploadgambarartikel())
                                .centerCrop().placeholder(R.drawable.load1)
                                .into(holder.image1);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickArtikelId = getRef(position).getKey();

                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
                                startActivity(daftarArtikel);

//                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
//                                intent.putExtra("aid", model.getAid());
//                                startActivity(intent);

                            }
                        });
                    }


                    @NotNull
                    @Override
                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
                        ArtikelHolder holder = new ArtikelHolder(view);
                        return holder;
                    }
                };

        recyclerviewartikel.setAdapter(adapter);
        adapter.startListening();
    }

    @Override
    public void onStart(){
        super.onStart();
        loadData("");
    }

    private void loadData(String data) {
        Query query = databaseReference.orderByChild("eddTitle").startAt(data).endAt(data+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                empty3.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<ProjectModel> options =
                new FirebaseRecyclerOptions.Builder<ProjectModel>()
                        .setQuery(query, ProjectModel.class)
                        .build();

        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {


                    @Override
                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
                        String bgcard1 = model.getEddTitle();

//                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));
                        holder.txtjudul9solo.setText(model.getEddTitle());
                        holder.txtsub9solo.setText(model.getEddIsiArtikel());
                        holder.txttgl6solo.setText(model.getEddttl());
                        Glide.with(holder.image1.getContext())
                                .load(model.getUploadgambarartikel())
                                .centerCrop().placeholder(R.drawable.load1)
                                .into(holder.image1);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickArtikelId = getRef(position).getKey();

                                Intent daftarArtikel = new Intent(DaftarArtikelActivity.this, DetailArtikelActivity.class);
                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
                                startActivity(daftarArtikel);

//                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
//                                intent.putExtra("aid", model.getAid());
//                                startActivity(intent);

                            }
                        });
                    }


                    @NotNull
                    @Override
                    public ArtikelHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerowdesign, parent, false);
                        ArtikelHolder holder = new ArtikelHolder(view);
                        return holder;
                    }
                };

        recyclerviewartikel.setAdapter(adapter);
        adapter.startListening();


    }

}