package com.example.projectkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FaqActivity extends AppCompatActivity {

    private ArrayList<FaqModel> faqModels;
    private DatabaseReference databaseReference;
    private Context context;
    RecyclerView recfaq;
    ImageView backbtnfaq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);
        recfaq = findViewById(R.id.recfaq);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recfaq.setLayoutManager(layoutManager);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Faq");

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        faqModels = new ArrayList<>();

        backbtnfaq = findViewById(R.id.backbtnfaq);
        backbtnfaq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    @Override
    protected void onStart()
    {
        super.onStart();

        FirebaseRecyclerOptions<FaqModel> options =
                new FirebaseRecyclerOptions.Builder<FaqModel>()
                        .setQuery(databaseReference, FaqModel.class)
                        .build();

        FirebaseRecyclerAdapter<FaqModel, FaqActivity.FaqHolder> adapter =
                new FirebaseRecyclerAdapter<FaqModel, FaqActivity.FaqHolder>(options) {


                    @Override
                    protected void onBindViewHolder(@NotNull FaqActivity.FaqHolder holder, int position, @NotNull FaqModel model) {
//                        String bgcard1 = model.getEddTitle();

//                        holder.bgcard.setBackgroundColor(getResources().getColor(R.color.white));has
                        holder.jawaban.setText(model.getJawaban());
                        holder.pertanyaan.setText(model.getPertanyaan());
//
                        holder.rowrightfaq.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

//                                boolean button1IsVisible = holder.jawabanbg.getVisibility();
                                if(holder.jawabanbg.getVisibility() == View.GONE){
                                    holder.jawabanbg.setVisibility(View.VISIBLE);
                                    holder.rowrightfaq.animate().rotation(holder.rowrightfaq.getRotation()+90).start();
                                }
                                else{
                                    holder.jawabanbg.setVisibility(View.GONE);
                                    holder.rowrightfaq.animate().rotation(holder.rowrightfaq.getRotation()-90).start();
                                }



                            }
                        });

//                        if(holder.rowrightfaq.isContextClickable()){
//                            holder.jawabanbg.setVisibility(View.GONE);
//                        } else {
//                            holder.jawabanbg.setVisibility(View.VISIBLE);
//                        }


                    }


                    @NotNull
                    @Override
                    public FaqActivity.FaqHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
                        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_faq, parent, false);
                        FaqActivity.FaqHolder holder = new FaqActivity.FaqHolder(view);
                        return holder;
                    }
                };
        recfaq.setAdapter(adapter);
        adapter.startListening();
    }

    public static class FaqHolder extends RecyclerView.ViewHolder
    {
        ImageView rowrightfaq;
        TextView pertanyaan,jawaban;
        RelativeLayout jawabanbg;

        public FaqHolder(@NotNull View itemView) {
            super(itemView);

            jawabanbg = itemView.findViewById(R.id.jawabanbg);
            rowrightfaq = itemView.findViewById(R.id.rowrightfaq);
            pertanyaan = itemView.findViewById(R.id.pertanyaan);
            jawaban = itemView.findViewById(R.id.jawaban);

        }

    }
}