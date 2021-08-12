package com.example.fuzzy;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.model.FungsiImplikasiModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FungsiImplikasi {
    DatabaseReference mDatabase;

    public  void penyakitH007(){
        Log.e("this on ","penyakit007");
        List<FungsiImplikasiModel> fungsiImplikasiModels = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Fuzzyfikasi").child("pakar");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FungsiImplikasiModel fungsiImplikasiModel = dataSnapshot.getValue(FungsiImplikasiModel.class);
//                    Log.i("show result model",fungsiImplikasiModel.getHimpunanRingan());
                    fungsiImplikasiModels.add(fungsiImplikasiModel);
                }

                double lihathasil = 0;
                for (int j = 0; j < fungsiImplikasiModels.size(); j++) {
//                    Log.e("result fungsiimplikasi",fungsiImplikasiModels.get(j).getHimpunanSedang());
                    lihathasil = lihathasil + Double.parseDouble(fungsiImplikasiModels.get(j).getHimpunanRingan());

                }

                System.out.println("Result from counter : " + String.valueOf(lihathasil));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}
