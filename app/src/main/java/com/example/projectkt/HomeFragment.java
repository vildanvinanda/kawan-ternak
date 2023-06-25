//package com.example.projectkt;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.denzcoskun.imageslider.ImageSlider;
//import com.denzcoskun.imageslider.constants.ScaleTypes;
//import com.denzcoskun.imageslider.interfaces.ItemClickListener;
//import com.denzcoskun.imageslider.models.SlideModel;
//import com.example.projectkt.databinding.FragmentEdukasiBinding;
//import com.example.projectkt.databinding.FragmentHomeBinding;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.material.navigation.NavigationBarView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//
//public class HomeFragment extends Fragment {
//
//    FragmentHomeBinding binding;
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//
//    }
//
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//    private EdukasiFragment edukasiFragment = new EdukasiFragment();
//    adapterartikel adapterart;
//    ArrayList<ProjectModel> recycleList;
//
//    FirebaseDatabase firebaseDatabase;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
////        binding = FragmentHomeBinding.inflate(inflater, container, false);
//
//
//
//        // Inflate the layout for this fragment
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        LinearLayout btnhewan =(LinearLayout) view.findViewById(R.id.btnhewan);
//
//        ImageSlider imageSlider = (ImageSlider) view.findViewById(R.id.image_slider);
//
//        //btn lihat edukasi
////        LinearLayout txtlihat = (LinearLayout) view.findViewById(R.id.txtlihat);
////        txtlihat.setOnClickListener(this);
//
//
//        //ini artikel
//        RecyclerView recview = (RecyclerView) view.findViewById(R.id.recview);
//
//        //ini yg sebelumnya aktif
//        recview.setLayoutManager(new LinearLayoutManager(getContext()));
//        FirebaseRecyclerOptions<ProjectModel> options =
//                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Artikel"), ProjectModel.class)
//                        .build();
//        adapterart = new adapterartikel(options);
//        recview.setAdapter(adapterart);
//
//
//
//        //baru
//        recycleList = new ArrayList<>();
//        firebaseDatabase = FirebaseDatabase.getInstance();
//        //seharusnyamah getApplicationContext
////        ProjectArtikelAdapter recyclerAdapter = new ProjectArtikelAdapter(recycleList, getContext());
////        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
////        recview.setLayoutManager(new LinearLayoutManager(getContext()));
////        recview.addItemDecoration(new DividerItemDecoration(recview.getContext(),DividerItemDecoration.VERTICAL));
////        recview.setNestedScrollingEnabled(false);
////        recview.setAdapter(recyclerAdapter);
//
////        firebaseDatabase.getReference().child("ArtikelKT")
////                .addListenerForSingleValueEvent(new ValueEventListener() {
////                    @Override
////                    public void onDataChange(@NotNull DataSnapshot snapshot) {
////                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
////                        {
////                            ProjectModel projectModel = dataSnapshot.getValue(ProjectModel.class);
////                            recycleList.add(projectModel);
////                        }
////
////                        recyclerAdapter.notifyDataSetChanged();
////
////                    }
////
////                    @Override
////                    public void onCancelled(@NotNull DatabaseError error) {
////
////                    }
////                });
//
//        ArrayList<SlideModel> arrayList = new ArrayList<>();
////        arrayList.add(new SlideModel(R.drawable.ayamjupiter, ScaleTypes.CENTER_CROP));
////        arrayList.add(new SlideModel(R.drawable.iklan, ScaleTypes.CENTER_CROP));
////        arrayList.add(new SlideModel(R.drawable.sapi, ScaleTypes.FIT));
//
//        FirebaseDatabase.getInstance().getReference().child("Iklan")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NotNull DataSnapshot snapshot)
//                    {
//                        for (DataSnapshot data:snapshot.getChildren())
//                        {
//                            arrayList.add(new SlideModel(data.child("url").getValue().toString(), data.child("title").getValue().toString(), ScaleTypes.FIT));
//                            imageSlider.setImageList(arrayList, ScaleTypes.FIT);
//
//                            imageSlider.setItemClickListener(new ItemClickListener() {
//                                @Override
//                                public void onItemSelected(int i) {
//                                    Toast.makeText(getContext(),arrayList.get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NotNull DatabaseError error) {
//
//                    }
//                });
//
//
//
//        btnhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), UploadArtikelAdmin.class));
//            }
//        });
//
//
//        return view;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        adapterart.startListening();
//    }
//    @Override
//    public void onStop() {
//        super.onStop();
//        adapterart.stopListening();
//    }
////
////    @Override
////    public void onClick(View view) {
//////        startActivity(new Intent(getActivity(), EdukasiFragment.class));
////        //do what you want to do when button is clicked
////
////        //ini untuk perpindahan dari fragment ke fragment lainnya
//////        Fragment fragment = null;
//////        switch (view.getId())
//////        {
//////            case R.id.txtlihat:
//////                fragment = new EdukasiFragment();
//////                replaceFragment(fragment);
//////                break;
//////        }
////
////
////    }
//
////ini perpindahan fragment ke fragmen lainnya
////    public void replaceFragment(Fragment homeFragment) {
////        FragmentTransaction transaction = getFragmentManager().beginTransaction();
////        transaction.replace(R.id.fragmentContainer, homeFragment);
////        transaction.addToBackStack(null);
////        transaction.commit();
////
////    }
//
//
//
//}



//BARUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUUU
package com.example.projectkt;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.projectkt.databinding.FragmentHomeBinding;
import com.example.projectkt.qrcode.contohaddDataSQLActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.net.NetworkInterface;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    FragmentHomeBinding binding;

    private static final String TAG = "PROFILE_TAG";
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    TextView txtuser;
    ImageView imgppHome;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {

    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

//    adapterartikel adapterart;
    ArrayList<ProjectModel> projectModels;

    ProjectArtikelAdapter projectArtikelAdapter;

    LinearLayout btnforumdiskusi, btnnotif;

    RelativeLayout txtlihat2;

    FirebaseDatabase firebaseDatabase;
    RecyclerView recview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        binding = FragmentHomeBinding.inflate(inflater, container, false);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        LinearLayout btnhewan =(LinearLayout) view.findViewById(R.id.btnhewan);
        imgppHome =(ImageView) view.findViewById(R.id.imgppHome);
        txtuser =(TextView) view.findViewById(R.id.txtuser);

        ImageSlider imageSlider = (ImageSlider) view.findViewById(R.id.image_slider);

        txtlihat2 = (RelativeLayout) view.findViewById(R.id.txtlihat2);
        btnnotif = (LinearLayout) view.findViewById(R.id.btnnotif);
        btnforumdiskusi = (LinearLayout) view.findViewById(R.id.btnforumdiskusi);
        btnforumdiskusi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ForumDiskusiActivity.class);
                startActivity(intent);
            }
        });

        btnnotif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NotificatonActivity.class);
                startActivity(intent);
            }
        });

        //ini artikel
        recview = (RecyclerView) view.findViewById(R.id.recview);

        //ini yg sebelumnya aktif
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recview.setLayoutManager(new LinearLayoutManager(getContext()));
         recview.setLayoutManager(layoutManager);

         //ini fungsi supaya recyclerview terbaru ada di paling atas
         layoutManager.setStackFromEnd(true);
         layoutManager.setReverseLayout(true);

        //baru
        projectModels = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        //seharusnyamah getApplicationContextProjectArtikelAdapter
//        projectArtikelAdapter = new ProjectArtikelAdapter(projectModels, getActivity());
//        LinearLayoutManager linearLayoutManager =  new LinearLayoutManager(this);
//        recview.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));
//        recview.addItemDecoration(new DividerItemDecoration(recview.getContext(),DividerItemDecoration.VERTICAL));
//        recview.setNestedScrollingEnabled(false);
//        recview.setAdapter(projectArtikelAdapter);

//
//        firebaseDatabase.getReference().child("ArtikelKT")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NotNull DataSnapshot snapshot) {
//                        for (DataSnapshot dataSnapshot : snapshot.getChildren())
//                        {
//                            ProjectModel projectModel = snapshot.getValue(ProjectModel.class);
//                            projectModels.add(projectModel);
//
//                        }
//
//                        projectArtikelAdapter.notifyDataSetChanged();
//
//
//                    }
//
//                    @Override
//                    public void onCancelled(@NotNull DatabaseError error) {
////                        Toast.makeText(getActivity(), "Error"+ta, Toast.LENGTH_SHORT).show();
//                    }
//                });

        ArrayList<SlideModel> arrayList = new ArrayList<>();
//        arrayList.add(new SlideModel(R.drawable.ayamjupiter, ScaleTypes.CENTER_CROP));
//        arrayList.add(new SlideModel(R.drawable.iklan, ScaleTypes.CENTER_CROP));
//        arrayList.add(new SlideModel(R.drawable.sapi, ScaleTypes.FIT));
        FirebaseDatabase.getInstance().getReference().child("Iklan")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NotNull DataSnapshot snapshot)
                    {
                        for (DataSnapshot data:snapshot.getChildren())
                        {
                            arrayList.add(new SlideModel(data.child("url").getValue().toString(), data.child("title").getValue().toString(), ScaleTypes.FIT));
                            imageSlider.setImageList(arrayList, ScaleTypes.FIT);

                            imageSlider.setItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemSelected(int i) {
                                    Toast.makeText(getContext(),arrayList.get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NotNull DatabaseError error) {

                    }
                });

        btnhewan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarHewanActivity.class);
                startActivity(intent);
            }
        });

        txtlihat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DaftarArtikelActivity.class);
                startActivity(intent);
            }
        });

        loaduser();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        adapterart.startListening();


        loaduser();


        FirebaseRecyclerOptions<ProjectModel> options =
                new FirebaseRecyclerOptions.Builder<ProjectModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Artikel").limitToLast(3), ProjectModel.class)
                        .build();

//         adapterart = new adapterartikel(options);
//         recview.setAdapter(adapterart);

        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {
                    @Override
                    protected void onBindViewHolder(@NotNull ArtikelHolder holder, int position, @NotNull ProjectModel model) {
                        holder.txtjudul9solo.setText(model.getEddTitle());
                        holder.txttgl6solo.setText(model.getEddttl());
                        Glide.with(holder.image1.getContext())
                                .load(model.getUploadgambarartikel())
                                .centerCrop()
                                .into(holder.image1);

                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                String clickArtikelId = getRef(position).getKey();

                                Intent daftarArtikel = new Intent(getActivity(), DetailArtikelActivity.class);
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
        recview.setAdapter(adapter);
        adapter.startListening();
    }

    private void loaduser() {
        Log.d(TAG, "loadUserInfo: loading user info user"+firebaseAuth.getUid());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(firebaseAuth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        //get all info of user here from snapshot
                        String email = ""+snapshot.child("email").getValue();
                        String username = ""+snapshot.child("username").getValue();
                        String nohp = ""+snapshot.child("nohp").getValue();
                        String gender = ""+snapshot.child("gender").getValue();
                        String alamat = ""+snapshot.child("alamat").getValue();
                        String timestamp = ""+snapshot.child("timestamp").getValue();
                        String userType = ""+snapshot.child("userType").getValue();
//                        String profileimage = ""+snapshot.child("profileimage").getValue();
                        String uid = ""+snapshot.child("uid").getValue();
//                        Log.d("TAG", profileimage + " / " + uid);

                        String profileimage = snapshot.child("profileimage").getValue(String.class);

                        //format data to dd/MM/yyyy


                        //set data to ui
                        txtuser.setText(username);
                        if (getActivity() == null) {
                            return;
                        }
                        Glide.with(getActivity())
                                .load(profileimage)
                                .centerCrop()
                                .into(imgppHome);
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {

                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        loaduser();
    }
    @Override
    public void onStop() {
        super.onStop();
//        adapterart.stopListening();
    }


    @Override
    public void onClick(View view) {
//        startActivity(new Intent(getActivity(), EdukasiFragment.class));
        //do what you want to do when button is clicked

        //ini untuk perpindahan dari fragment ke fragment lainnya
//        Fragment fragment = null;
//        switch (view.getId())
//        {
//            case R.id.txtlihat:
//                fragment = new EdukasiFragment();
//                replaceFragment(fragment);
//                break;
//        }


    }

//ini perpindahan fragment ke fragmen lainnya
//    public void replaceFragment(Fragment homeFragment) {
//        FragmentTransaction transaction = getFragmentManager().beginTransaction();
//        transaction.replace(R.id.fragmentContainer, homeFragment);
//        transaction.addToBackStack(null);
//        transaction.commit();
//
//    }



}


//BARU YANG KE 2
//package com.example.projectkt;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.fragment.app.FragmentTransaction;
//import androidx.lifecycle.viewmodel.CreationExtras;
//import androidx.recyclerview.widget.DividerItemDecoration;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
//
//import android.view.LayoutInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//import android.widget.LinearLayout;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.denzcoskun.imageslider.ImageSlider;
//import com.denzcoskun.imageslider.constants.ScaleTypes;
//import com.denzcoskun.imageslider.interfaces.ItemClickListener;
//import com.denzcoskun.imageslider.models.SlideModel;
//import com.example.projectkt.databinding.FragmentEdukasiBinding;
//import com.example.projectkt.databinding.FragmentHomeBinding;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
//import com.firebase.ui.database.FirebaseRecyclerOptions;
//import com.google.android.material.navigation.NavigationBarView;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//
//import org.jetbrains.annotations.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
//
//
//
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    private String mParam1;
//    private String mParam2;
//
//    public HomeFragment() {
//
//    }
//
//    public static HomeFragment newInstance(String param1, String param2) {
//        HomeFragment fragment = new HomeFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//
//
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
//    }
//
//
//    private List<ProjectModel> projectModels;
//    private ProjectArtikelAdapter adapter;
//    String Judul,Isi,Tanggal;
//    FirebaseDatabase firebaseDatabase;
//    RecyclerView recview;
//    DatabaseReference dataa, databaseReference;
//    SwipeRefreshLayout swipeRefreshLayout;
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//
//        View view = inflater.inflate(R.layout.fragment_home, container, false);
//
//        LinearLayout btnhewan =(LinearLayout) view.findViewById(R.id.btnhewan);
//        ImageSlider imageSlider = (ImageSlider) view.findViewById(R.id.image_slider);
//
//        //ini artikel
//        recview = view.findViewById(R.id.recview);
//        recview.setHasFixedSize(true);
//        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.move);
//        recview.startAnimation(animation);
//        recview.setLayoutManager(new LinearLayoutManager(getContext()));
//        projectModels = new ArrayList<>();
//
//        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipelayout);
//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorSchemeResources(R.color.kuning, R.color.purple_500);
//
//        swipeRefreshLayout.post(new Runnable() {
//            @Override
//            public void run() {
//                swipeRefreshLayout.setRefreshing(true);
//            }
//        });
//
//
//
////ini iklan
//        ArrayList<SlideModel> arrayList = new ArrayList<>();
//        FirebaseDatabase.getInstance().getReference().child("Iklan")
//                .addListenerForSingleValueEvent(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NotNull DataSnapshot snapshot)
//                    {
//                        for (DataSnapshot data:snapshot.getChildren())
//                        {
//                            arrayList.add(new SlideModel(data.child("url").getValue().toString(), data.child("title").getValue().toString(), ScaleTypes.FIT));
//                            imageSlider.setImageList(arrayList, ScaleTypes.FIT);
//
//                            imageSlider.setItemClickListener(new ItemClickListener() {
//                                @Override
//                                public void onItemSelected(int i) {
//                                    Toast.makeText(getContext(),arrayList.get(i).getTitle().toString(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NotNull DatabaseError error) {
//
//                    }
//                });
//
//
//
//        btnhewan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), UploadArtikelAdmin.class);
//                startActivity(intent);
//            }
//        });
//
//
//        return view;
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
////        adapterart.startListening();
//
//        FirebaseRecyclerOptions<ProjectModel> options =
//                new FirebaseRecyclerOptions.Builder<ProjectModel>()
//                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Artikel"), ProjectModel.class)
//                        .build();
////         adapterart = new adapterartikel(options);
////         recview.setAdapter(adapterart);
//
//        FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder> adapter =
//                new FirebaseRecyclerAdapter<ProjectModel, ArtikelHolder>(options) {
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
//                                Intent intent = new Intent(getActivity(), DetailArtikelActivity.class);
//                                intent.putExtra("aid", model.getAid());
//                                startActivity(intent);
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
//        recview.setAdapter(adapter);
//        adapter.startListening();
//    }
//
//
//    @Override
//    public void onStop() {
//        super.onStop();
////        adapterart.stopListening();
//    }
//
//
//
//    @Override
//    public void onRefresh() {
//        userArtikel();
//    }
//
//    private void userArtikel() {
//        swipeRefreshLayout.setRefreshing(true);
//        databaseReference = FirebaseDatabase.getInstance().getReference().child("Artikel");
//    }
//
//
//    @NotNull
//    @Override
//    public CreationExtras getDefaultViewModelCreationExtras() {
//        return null;
//    }
//
//
//}