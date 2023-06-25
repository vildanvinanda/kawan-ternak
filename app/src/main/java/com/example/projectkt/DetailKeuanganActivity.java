package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class DetailKeuanganActivity extends AppCompatActivity {

    TextView titleDetailkeu2,inket,injumlah,inharga,intotal,intgl;
    ImageView backbtnkeuangan;

    private DatabaseReference KeuanganRef;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    String title,inkeu,injum,inhar,intg;
    private String KeuanganlID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_keuangan);


        KeuanganRef = FirebaseDatabase.getInstance().getReference().child("Users").child(firebaseAuth.getUid()).child("Keuangan");

        titleDetailkeu2 = (TextView) findViewById(R.id.titleDetailkeu2);
        inket = (TextView) findViewById(R.id.inket);
        injumlah = (TextView) findViewById(R.id.injumlah);
        inharga = (TextView) findViewById(R.id.inharga);
        intotal = (TextView) findViewById(R.id.intotal);
        intgl = (TextView) findViewById(R.id.intgl);
        backbtnkeuangan = (ImageView) findViewById(R.id.backbtnkeuangan);

        backbtnkeuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        KeuanganlID = getIntent().getExtras().get("clickKeuanganId").toString();

        RetrieveKeuanganInfo();

    }

    private void RetrieveKeuanganInfo() {
        KeuanganRef.child(KeuanganlID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NotNull DataSnapshot snapshot) {
                if ((snapshot.exists()) && (snapshot.hasChild("type")))
                {
                    String keuanganType = snapshot.child("type").getValue().toString();
                    String harga = snapshot.child("hargap").getValue().toString();
                    String ket = snapshot.child("pemasukan").getValue().toString();
                    String qty = snapshot.child("qtyp").getValue().toString();
                    String satuan = snapshot.child("satuanp").getValue().toString();
                    String tgl = snapshot.child("tglpemasukan").getValue().toString();
                    String total = snapshot.child("totalp").getValue().toString();

                    titleDetailkeu2.setText("Detail "+keuanganType);
                    inket.setText(ket);
                    injumlah.setText(qty+" "+satuan);
                    intgl.setText(tgl);
                    inharga.setText(harga);
                    Double a1 = Double.parseDouble(inharga.getText().toString());
                    DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                    DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                    formatRp.setCurrencySymbol("RP.");
                    formatRp.setMonetaryDecimalSeparator(',');
                    formatRp.setGroupingSeparator('.');
                    kursIndonesia.setDecimalFormatSymbols(formatRp);
                    inharga.setText(String.valueOf(kursIndonesia.format(a1)));
                    intotal.setText(total);
                }
            }

            @Override
            public void onCancelled(@NotNull DatabaseError error) {

            }
        });
    }
}