package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.diagnosahamapadi.adapter.CFAdapter;
import com.example.diagnosahamapadi.adapter.FMAdapter;
import com.example.model.HasilAkhir;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FMResult extends AppCompatActivity {
    private Context context;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recylerViewLayoutManager;

    private DatabaseReference mDatabase;
    private String userId ;
    private List<HasilAkhir> hasilAkhirs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fmresult);

        SharedPreferences sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        userId = sharedPreferences.getString("username",null);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("HasilAkhir").child(userId);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                hasilAkhirs = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    HasilAkhir hasilAkhir = dataSnapshot.getValue(HasilAkhir.class);
                    hasilAkhirs.add(hasilAkhir);
                }
                saveRecycler(hasilAkhirs);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    private void saveRecycler(List<HasilAkhir> lastResult){
        for (int i = 0; i < lastResult.size(); i++) {
            String nama = lastResult.get(i).namaPenyakit;
            String nama1 = lastResult.get(i).hasiFM;
            String nama2 = lastResult.get(i).hasilCF;

            System.out.println("check error" + nama + " " + nama1 + " " + nama2);
        }
        context = getApplicationContext();
        recyclerView = findViewById(R.id.recyclerviewFM);
        recylerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        recyclerViewAdapter = new FMAdapter(lastResult, context);
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}