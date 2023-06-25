package com.example.projectkt.qrcode;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blikoon.qrcodescanner.QrCodeActivity;
import com.example.projectkt.R;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ScannQRCodeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScannQRCodeFragment extends Fragment {


    private static final int REQUEST_CODE_QR_SCAN = 101;

    RecyclerView reccontoh2;
    LinearLayoutManager linearLayoutManager;
    AdapterRecItemContoh adapterRecItemContoh;
    List<DataModel> listData;
    DataModel dataModel;
    public static final String EXTRA_NAMA = "namahewan";

    TextView txthallo2;
    Button btnscanner2;
    ImageView gambar2;

    String namahewan,nomor, imaghewan;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ScannQRCodeFragment() {
        // Required empty public constructor
    }

    public static ScannQRCodeFragment newInstance(String param1, String param2) {
        ScannQRCodeFragment fragment = new ScannQRCodeFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_scann_q_r_code, container, false);

        //recyclerview
        reccontoh2 = (RecyclerView) view.findViewById(R.id.reccontoh2);

        namahewan = "";
        imaghewan = "";
        nomor = "";

        txthallo2 = (TextView) view.findViewById(R.id.txthallo2);
        gambar2 = (ImageView) view.findViewById(R.id.gambar2);
        btnscanner2 = (Button) view.findViewById(R.id.btnscanner2);

        btnscanner2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getContext())
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent i = new Intent(getActivity(), QrCodeActivity.class);
                                startActivityForResult( i,REQUEST_CODE_QR_SCAN);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
                                permissionDeniedResponse.getRequestedPermission();
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                            }
                        }).check();

            }
        });


        return view;
    }
}