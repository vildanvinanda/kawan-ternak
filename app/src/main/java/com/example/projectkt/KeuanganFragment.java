package com.example.projectkt;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.view.ScrollingView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projectkt.qrcode.AdapterRecItemContoh;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class KeuanganFragment extends Fragment {

    FloatingActionButton btntambahkeu,btnpengeluarn,btnpemasukan;
    TextView title_pengeluaran,title_pemasukan;
    Animation fabOpen,fabClose, rotateForward, rotateBackward;
    RelativeLayout bgtrans;

    EditText kolomsearch;

    TextView txtall, txtmasuk,txtkeluar;

    ImageView icontgl;


    //Btnfilter
    RelativeLayout btnsemua,btnfilter1,btnfilter2,empty1;

    //recyclerview
    RecyclerView reckeuangan;
    FirebaseDatabase firebaseDatabase;
    ArrayList<KeuanganModel> keuanganModels;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    String value;

    private DatabaseReference databaseReference;

    boolean isOpen = false;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public KeuanganFragment() {
        // Required empty public constructor
    }


    public static KeuanganFragment newInstance(String param1, String param2) {
        KeuanganFragment fragment = new KeuanganFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_keuangan, container, false);

        btntambahkeu = (FloatingActionButton) view.findViewById(R.id.btntambahkeu);
        btnpemasukan = (FloatingActionButton) view.findViewById(R.id.btnpemasukan);
        btnpengeluarn = (FloatingActionButton) view.findViewById(R.id.btnpengeluarn);
        title_pengeluaran = (TextView) view.findViewById(R.id.title_pengeluaran);
        title_pemasukan = (TextView) view.findViewById(R.id.title_pemasukan);
        kolomsearch = (EditText) view.findViewById(R.id.kolomsearch);
        icontgl = (ImageView) view.findViewById(R.id.icontgl);
        empty1 = (RelativeLayout) view.findViewById(R.id.empty1);

        txtall = (TextView) view.findViewById(R.id.txtall);
        txtmasuk = (TextView) view.findViewById(R.id.txtmasuk);
        txtkeluar = (TextView) view.findViewById(R.id.txtkeluar);

        bgtrans = (RelativeLayout) view.findViewById(R.id.bgtrans);

        fabClose = (Animation) AnimationUtils.loadAnimation(getContext(),R.anim.fab_close);
        fabOpen = (Animation) AnimationUtils.loadAnimation(getContext(),R.anim.fab_open);

        rotateBackward = (Animation) AnimationUtils.loadAnimation(getContext(),R.anim.rotate_backwawrd);
        rotateForward = (Animation) AnimationUtils.loadAnimation(getContext(),R.anim.rotate_forward);

        btnsemua = (RelativeLayout) view.findViewById(R.id.btnsemua);
        btnfilter1 = (RelativeLayout) view.findViewById(R.id.btnfilter1);
        btnfilter2 = (RelativeLayout) view.findViewById(R.id.btnfilter2);

        btnsemua.setBackground(getResources().getDrawable(R.drawable.button_rounded));
        txtall.setTextColor(getResources().getColor(R.color.white));

        icontgl.setOnClickListener(new View.OnClickListener() {
            private int mDate,mMonth,mYear;
            @Override
            public void onClick(View v) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(
                        getContext(), R.style.BottomSheetDialogTheme
                );
                View bottomSheetView = getLayoutInflater().inflate(
                        R.layout.filter_tanggal, (RelativeLayout) view.findViewById(R.id.bottomSheetCountainerTanggal)
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

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
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

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
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
                            data1("");
                        }

                    }
                    private void data1(String data) {
                        String eddmulai = eddmulaidari.getText().toString();
                        String eddsampai = eddsampadengan2.getText().toString();
                        btnsemua.setBackground(getResources().getDrawable(R.drawable.button_rounded));
                        btnfilter1.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                        btnfilter2.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));

                        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").orderByChild("tglpemasukan").startAt(eddmulai).endAt(eddsampai+"\uf8ff");


                        FirebaseRecyclerOptions<KeuanganModel> options =
                                new FirebaseRecyclerOptions.Builder<KeuanganModel>()
                                        .setQuery(query, KeuanganModel.class)
                                        .build();

                        FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder> adapter =
                                new FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder>(options) {

                                    @Override
                                    protected void onBindViewHolder(@NotNull KeuanganHolder holder, int position, @NotNull KeuanganModel model) {
                                        String bg = model.getType();
                                        String del = model.getMid();

                                        holder.titleKeuangan.setText(model.getType());
                                        holder.txtKet.setText(model.getPemasukan());
                                        holder.tglkirim.setText(model.getTglpemasukan());
                                        holder.txttotalh.setText(model.getTotalp());
                                        if (bg.equals("Pemasukan")){
                                            holder.bgcard.setBackgroundColor(getResources().getColor(R.color.ijomudapias));
                                            holder.titleKeuangan.setTextColor(getResources().getColor(R.color.ijomuda));
                                            holder.txttotalh.setTextColor(getResources().getColor(R.color.ijomuda));
                                            holder.tresmerah.setVisibility(View.INVISIBLE);
                                            holder.tresshijau.setVisibility(View.VISIBLE);
                                        }
                                        holder.tresmerah.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                showDeleteDataDialog("");

                                            }
                                            private void showDeleteDataDialog(String dell) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                builder.setTitle("Hapus");
                                                builder.setMessage("Anda yakin untuk menghapus data ini?");
                                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NotNull Task<Void> task) {

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
                                        holder.tresshijau.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                showDeleteDataDialog("");

                                            }
                                            private void showDeleteDataDialog(String dell) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                                builder.setTitle("Hapus");
                                                builder.setMessage("Anda yakin untuk menghapus data ini?");
                                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                                            @Override
                                                            public void onComplete(@NotNull Task<Void> task) {

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

                                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v)
                                            {
                                                String clickKeuanganId = getRef(position).getKey();
                                                Intent detailKeuangan = new Intent(getActivity(), DetailKeuanganActivity.class);
                                                detailKeuangan.putExtra("clickKeuanganId", clickKeuanganId);
                                                startActivity(detailKeuangan);

                                            }
                                        });

                                    }

                                    @NotNull
                                    @Override
                                    public KeuanganHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keuangan, parent, false);
                                        KeuanganHolder holder = new KeuanganHolder(view);
                                        return holder;
                                    }
                                };
                        reckeuangan.setAdapter(adapter);
                        adapter.startListening();
                    }
                });

//                bottomSheetView.findViewById(R.id.btncarifiltertanggal).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Toast.makeText(DaftarArtikelActivity.this, "btn Cari sudah di click", Toast.LENGTH_SHORT).show();
//                        eddmulaidari.addTextChangedListener(new TextWatcher() {
//                            @Override
//                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//                            }
//
//                            @Override
//                            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                                if (s.toString() != null) {
//                                    loadData4(s.toString());
//                                } else {
//                                    loadData4("");
//                                }
//                            }
//
//                            @Override
//                            public void afterTextChanged(Editable s) {
//
//                            }
//                            private void loadData4(String data) {
//                                btnsemua.setBackground(getResources().getDrawable(R.drawable.button_rounded));
//                                btnfilter1.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//                                btnfilter2.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
//
//                                Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").orderByChild("pemasukan").startAt(data).endAt(data+"\uf8ff");
//
//                                FirebaseRecyclerOptions<KeuanganModel> options =
//                                        new FirebaseRecyclerOptions.Builder<KeuanganModel>()
//                                                .setQuery(query, KeuanganModel.class)
//                                                .build();
//
////         adapterart = new adapterartikel(options);
////         recview.setAdapter(adapterart);
//
//                                FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder> adapter =
//                                        new FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder>(options) {
//                                            @Override
//                                            protected void onBindViewHolder(@NotNull KeuanganHolder holder, int position, @NotNull KeuanganModel model) {
//                                                String bg = model.getType();
//                                                holder.titleKeuangan.setText(model.getType());
//                                                holder.txtKet.setText(model.getPemasukan());
//                                                holder.tglkirim.setText(model.getTglpemasukan());
//                                                if (bg.equals("Pemasukan")){
//                                                    holder.bgcard.setBackgroundColor(getResources().getColor(R.color.ijomudapias));
//                                                    holder.titleKeuangan.setTextColor(getResources().getColor(R.color.ijomuda));
//                                                    holder.tresmerah.setVisibility(View.INVISIBLE);
//                                                    holder.tresshijau.setVisibility(View.VISIBLE);
//                                                }
////                        holder.itemView.setOnClickListener(new View.OnClickListener() {
////                            @Override
////                            public void onClick(View v)
////                            {
////                                String clickArtikelId = getRef(position).getKey();
////
////                                Intent daftarArtikel = new Intent(getActivity(), DetailArtikelActivity.class);
////                                daftarArtikel.putExtra("clickArtikelId", clickArtikelId);
////                                startActivity(daftarArtikel);
////                            }
////                        });
//                                            }
//
//                                            @NotNull
//                                            @Override
//                                            public KeuanganHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
//                                                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keuangan, parent, false);
//                                                KeuanganHolder holder = new KeuanganHolder(view);
//                                                return holder;
//                                            }
//                                        };
//                                reckeuangan.setAdapter(adapter);
//                                adapter.startListening();
//                            }
//                        });
//
//                    }
//
//
//                });


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
                if (s.toString()!=null)
                {
                    coba(s.toString());
                }
                else
                {
                    coba("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnsemua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnsemua.setBackground(getResources().getDrawable(R.drawable.button_rounded));
                btnfilter1.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                btnfilter2.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                txtall.setTextColor(getResources().getColor(R.color.white));
                txtkeluar.setTextColor(getResources().getColor(R.color.kuning));
                txtmasuk.setTextColor(getResources().getColor(R.color.kuning));
                coba("");
            }
        });
        btnfilter1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnfilter1.setBackground(getResources().getDrawable(R.drawable.button_rounded));
                txtmasuk.setTextColor(getResources().getColor(R.color.white));
                txtkeluar.setTextColor(getResources().getColor(R.color.kuning));
                txtall.setTextColor(getResources().getColor(R.color.kuning));
                btnfilter2.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                btnsemua.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                coba2();
            }
        });
        btnfilter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtkeluar.setTextColor(getResources().getColor(R.color.white));
                txtall.setTextColor(getResources().getColor(R.color.kuning));
                txtmasuk.setTextColor(getResources().getColor(R.color.kuning));
                btnfilter2.setBackground(getResources().getDrawable(R.drawable.button_rounded));
                btnfilter1.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                btnsemua.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
                coba3();

            }
        });

        btntambahkeu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();

            }
        });

        btnpemasukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                Intent intent = new Intent(getActivity(), FormPemasukanActivity.class);
                startActivity(intent);
            }
        });
        btnpengeluarn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
                Intent intent = new Intent(getActivity(), FormPengeluaranActivity.class);
                startActivity(intent);
            }
        });

        //Recyclerview
        reckeuangan = (RecyclerView) view.findViewById(R.id.reckeuangan);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recview.setLayoutManager(new LinearLayoutManager(getContext()));
        reckeuangan.setLayoutManager(layoutManager);

        //ini fungsi supaya recyclerview terbaru ada di paling atas
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        keuanganModels = new ArrayList<>();
//
        return view;
    }

    public void coba(String data){

        btnsemua.setBackground(getResources().getDrawable(R.drawable.button_rounded));
        btnfilter1.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));
        btnfilter2.setBackground(getResources().getDrawable(R.drawable.button_rounded_daftar));

        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").orderByChild("pemasukan").startAt(data).endAt(data+"\uf8ff");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                empty1.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<KeuanganModel> options =
                new FirebaseRecyclerOptions.Builder<KeuanganModel>()
                        .setQuery(query, KeuanganModel.class)
                        .build();

//         adapterart = new adapterartikel(options);
//         recview.setAdapter(adapterart);

        FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder> adapter =
                new FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NotNull KeuanganHolder holder, int position, @NotNull KeuanganModel model) {
                        String bg = model.getType();
                        holder.titleKeuangan.setText(model.getType());
                        holder.txtKet.setText(model.getPemasukan());
                        holder.tglkirim.setText(model.getTglpemasukan());
                        holder.txttotalh.setText(model.getTotalp());
                        if (bg.equals("Pemasukan")){
                            holder.bgcard.setBackgroundColor(getResources().getColor(R.color.ijomudapias));
                            holder.titleKeuangan.setTextColor(getResources().getColor(R.color.ijomuda));
                            holder.txttotalh.setTextColor(getResources().getColor(R.color.ijomuda));
                            holder.tresmerah.setVisibility(View.INVISIBLE);
                            holder.tresshijau.setVisibility(View.VISIBLE);
                        }
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickKeuanganId = getRef(position).getKey();

                                Intent detailKeuangan = new Intent(getActivity(), DetailKeuanganActivity.class);
                                detailKeuangan.putExtra("clickKeuanganId", clickKeuanganId);
                                startActivity(detailKeuangan);
                            }
                        });
                        holder.tresmerah.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDeleteDataDialog("");

                            }
                            private void showDeleteDataDialog(String dell) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Hapus");
                                builder.setMessage("Anda yakin untuk menghapus data ini?");
                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid())
                                                .child("Keuangan").child(getRef(position).getKey())
                                                .removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NotNull Task<Void> task) {

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
                        holder.tresshijau.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDeleteDataDialog("");

                            }
                            private void showDeleteDataDialog(String dell) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Hapus");
                                builder.setMessage("Anda yakin untuk menghapus data ini?");
                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NotNull Task<Void> task) {

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
                    public KeuanganHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keuangan, parent, false);
                        KeuanganHolder holder = new KeuanganHolder(view);
                        return holder;
                    }
                };
        reckeuangan.setAdapter(adapter);
        adapter.startListening();
    }
    public void coba2(){

        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").orderByChild("type").equalTo("Pemasukan");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                empty1.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<KeuanganModel> options =
                new FirebaseRecyclerOptions.Builder<KeuanganModel>()
                        .setQuery(query, KeuanganModel.class)
                        .build();

//         adapterart = new adapterartikel(options);
//         recview.setAdapter(adapterart);

        FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder> adapter =
                new FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NotNull KeuanganHolder holder, int position, @NotNull KeuanganModel model) {
                        String bg = model.getType();

                        if(bg.equals("Pemasukan"))
                        {
                            holder.titleKeuangan.setText(model.getType());
                            holder.txtKet.setText(model.getPemasukan());
                            holder.tglkirim.setText(model.getTglpemasukan());
                            holder.txttotalh.setText(model.getTotalp());
                            holder.txttotalh.setTextColor(getResources().getColor(R.color.ijomuda));
                            holder.bgcard.setBackgroundColor(getResources().getColor(R.color.ijomudapias));
                            holder.titleKeuangan.setTextColor(getResources().getColor(R.color.ijomuda));
                            holder.tresmerah.setVisibility(View.INVISIBLE);
                            holder.tresshijau.setVisibility(View.VISIBLE);
                            holder.pengeluaran1.bringToFront();
                        }
                        if (bg.equals("Pengeluaran"))
                        {
                            holder.pengeluaran1.setVisibility(View.GONE);
                        }

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickKeuanganId = getRef(position).getKey();

                                Intent detailKeuangan = new Intent(getActivity(), DetailKeuanganActivity.class);
                                detailKeuangan.putExtra("clickKeuanganId", clickKeuanganId);
                                startActivity(detailKeuangan);
                            }
                        });

                        holder.tresmerah.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDeleteDataDialog("");

                            }
                            private void showDeleteDataDialog(String dell) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Hapus");
                                builder.setMessage("Anda yakin untuk menghapus data ini?");
                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NotNull Task<Void> task) {

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
                        holder.tresshijau.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDeleteDataDialog("");

                            }
                            private void showDeleteDataDialog(String dell) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Hapus");
                                builder.setMessage("Anda yakin untuk menghapus data ini?");
                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NotNull Task<Void> task) {

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
                    public KeuanganHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keuangan, parent, false);
                        KeuanganHolder holder = new KeuanganHolder(view);
                        return holder;
                    }
                };
        reckeuangan.setAdapter(adapter);
        adapter.startListening();
    }
    public void coba3(){

        Query query = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").orderByChild("type").equalTo("Pengeluaran");
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NotNull DataSnapshot snapshot) {
//                for (DataSnapshot ds : snapshot.getChildren()){
//
//                    if(ds.getChildrenCount() == 0){
//                        empty1.setVisibility(View.VISIBLE);
//                    } else if (ds.getChildrenCount() >= 1){
//                        empty1.setVisibility(View.GONE);
//                    } else {
//                        empty1.setVisibility(View.VISIBLE);
//                    }
////                    empty1.setVisibility(ds.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);
//                }
//
//            }
//
//            @Override
//            public void onCancelled(@NotNull DatabaseError error) {
//
//            }
//        });
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {

                   empty1.setVisibility(snapshot.getChildrenCount() == 0 ? View.VISIBLE : View.GONE);

            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
        FirebaseRecyclerOptions<KeuanganModel> options =
                new FirebaseRecyclerOptions.Builder<KeuanganModel>()
                        .setQuery(query, KeuanganModel.class)
                        .build();

//         adapterart = new adapterartikel(options);
//         recview.setAdapter(adapterart);

        FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder> adapter =
                new FirebaseRecyclerAdapter<KeuanganModel, KeuanganHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NotNull KeuanganHolder holder, int position, @NotNull KeuanganModel model) {
                        String bg = model.getType();

                        if(bg.equals("Pengeluaran"))
                        {
                            holder.titleKeuangan.setText(model.getType());
                            holder.txtKet.setText(model.getPemasukan());
                            holder.tglkirim.setText(model.getTglpemasukan());
                            holder.txttotalh.setText(model.getTotalp());
                            holder.tresmerah.setVisibility(View.VISIBLE);
                            holder.tresshijau.setVisibility(View.GONE);
                            holder.pengeluaran1.bringToFront();
                        }
                        if (bg.equals("Pemasuka"))
                        {
                            holder.pengeluaran1.setVisibility(View.GONE);
                        }

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickKeuanganId = getRef(position).getKey();

                                Intent detailKeuangan = new Intent(getActivity(), DetailKeuanganActivity.class);
                                detailKeuangan.putExtra("clickKeuanganId", clickKeuanganId);
                                startActivity(detailKeuangan);
                            }
                        });
                        holder.tresmerah.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDeleteDataDialog("");

                            }
                            private void showDeleteDataDialog(String dell) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Hapus");
                                builder.setMessage("Anda yakin untuk menghapus data ini?");
                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NotNull Task<Void> task) {

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
                        holder.tresshijau.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                showDeleteDataDialog("");

                            }
                            private void showDeleteDataDialog(String dell) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                                builder.setTitle("Hapus");
                                builder.setMessage("Anda yakin untuk menghapus data ini?");
                                builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan").child(getRef(position).getKey()).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NotNull Task<Void> task) {

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
                    public KeuanganHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_keuangan, parent, false);
                        KeuanganHolder holder = new KeuanganHolder(view);
                        return holder;
                    }
                };


        reckeuangan.setAdapter(adapter);
        adapter.startListening();


    }

    @Override
    public void onStart(){
        super.onStart();
        coba("");
    }

    private void animateFab(){
        if(isOpen){
            btntambahkeu.startAnimation(rotateBackward);
            btnpengeluarn.startAnimation(fabClose);
            btnpemasukan.startAnimation(fabClose);
            btnpengeluarn.setClickable(false);
            btnpemasukan.setClickable(false);
            title_pengeluaran.setVisibility(View.INVISIBLE);
            title_pemasukan.setVisibility(View.INVISIBLE);
            bgtrans.setVisibility(View.INVISIBLE);
            isOpen=false;
        } else {
            btntambahkeu.startAnimation(rotateForward);
            btnpengeluarn.startAnimation(fabOpen);
            btnpemasukan.startAnimation(fabOpen);
            btnpengeluarn.setClickable(true);
            btnpemasukan.setClickable(true);
            title_pengeluaran.setVisibility(View.VISIBLE);
            title_pemasukan.setVisibility(View.VISIBLE);
            bgtrans.setVisibility(View.VISIBLE);
            isOpen=true;
        }
    }
}