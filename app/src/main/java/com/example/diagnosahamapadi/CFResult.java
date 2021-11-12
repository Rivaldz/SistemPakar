package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;

import com.example.diagnosahamapadi.adapter.CFAdapter;
import com.example.fuzzy.Defuzzyfikasi;
import com.example.fuzzy.LomDefuzzy;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CFResult extends AppCompatActivity {
    Context context;
    RecyclerView recyclerView;
    RecyclerView.Adapter recyclerViewAdapter;
    RecyclerView.LayoutManager recylerViewLayoutManager;
    List<String> subjects;
    List<String> penyakit;
    DatabaseReference mDatabaseLom, getLomDb;
    Button button;
    private String userId = null;

    Defuzzyfikasi defuzzyfikasi = new Defuzzyfikasi();

//    String idPenyakit = null, userID = null;
    LomDefuzzy lomDefuzzy = new LomDefuzzy();
    private String kodePenyakit = lomDefuzzy.kodePenyakit;
//    private String userID = lomDefuzzy;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cfresult);

        button = findViewById(R.id.buttonHitungFuzzy);

        SharedPreferences sharedPreferences = getSharedPreferences("user_details", MODE_PRIVATE);
        userId = sharedPreferences.getString("username",null);


        mDatabaseLom = FirebaseDatabase.getInstance().getReference().child("LastResult").child("testUser");
        mDatabaseLom.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subjects = new ArrayList<String>();
                penyakit = new ArrayList<String>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String valueSub = dataSnapshot.child("nilaiAkhir").getValue().toString();
                    String penyakitSt = dataSnapshot.child("namaPenyakit").getValue().toString();
//                    subjects.add(dataSnapshot.getKey());
                    subjects.add(valueSub);
                    penyakit.add(penyakitSt);
                }
                saveRecycler(subjects,penyakit);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLomDb = FirebaseDatabase.getInstance().getReference().child("LastResult").child(userId);
                getLomDb.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String nilaiAkhir = snapshot.child("nilaiAkhir").getValue().toString();
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            defuzzyfikasi.getLOMFuzzy(userId,dataSnapshot.getKey());
                            defuzzyfikasi.sumKeanggotaan(userId,dataSnapshot.getKey(),nilaiAkhir);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }

        });




    }
    private void saveRecycler(List<String> hasil,List<String> result){
        context = getApplicationContext();
        recyclerView = findViewById(R.id.recyclerview);
        recylerViewLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(recylerViewLayoutManager);
        recyclerViewAdapter = new CFAdapter(hasil,result, context);
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}