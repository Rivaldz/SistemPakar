package com.example.diagnosahamapadi;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.diagnosahamapadi.adapter.RecyclerViewAdapter;
import com.example.model.DataClass;
import com.example.model.Gejala;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class MyListData extends AppCompatActivity {
    private RecyclerView recyclerView;

    List<DataClass> gejalaData = new ArrayList<>();
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_list_data);

        databaseReference = FirebaseDatabase.getInstance().getReference("Database").child("A");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DataClass gejalaArray  = snapshot.getValue(DataClass.class);
                Log.i("SEE RESULT FROM :", String.valueOf(gejalaArray.getBobotGejala()));
                gejalaData.add(gejalaArray);

                Log.i("SEE RESULT FROM Delcon:", gejalaData.get(1).getBobotGejala());

//                RecyclerViewAdapter gejalaAdapter= new RecyclerViewAdapter(gejalaData);
//                recyclerView.setAdapter(gejalaAdapter);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}