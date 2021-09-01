package com.example.fuzzy;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.model.CreateDataFI;
import com.example.model.FungsiImplikasiModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FungsiImplikasi {
    public String  namaHama, userId;
    DatabaseReference mDatabaseUser,mDatabasePakar,mDatabase;
    ArrayList<FungsiImplikasiModel> fungsiImplikasiModelsPakar = new ArrayList<>();
    ArrayList<FungsiImplikasiModel> fungsiImplikasiModelsUser = new ArrayList<>();

    public FungsiImplikasi(String namaHama, String userId) {
        this.namaHama = namaHama;
        this.userId = userId;
    }

    public  void onPenyakit(){
//        Log.e("this on ","penyakit007");
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabasePakar = FirebaseDatabase.getInstance().getReference().child("Fuzzyfikasi").child(userId).child(namaHama).child("pakar");
        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Fuzzyfikasi").child(userId).child(namaHama).child("user");
        mDatabasePakar.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                fungsiImplikasiModels = new ArrayList<>();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    FungsiImplikasiModel fungsiImplikasiModel = dataSnapshot.getValue(FungsiImplikasiModel.class);
//                    Log.i("show result model",fungsiImplikasiModel.getHimpunanRingan());
                    fungsiImplikasiModel.setKey(dataSnapshot.getKey());
                    fungsiImplikasiModelsPakar.add(fungsiImplikasiModel);
                }
                mDatabaseUser.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            FungsiImplikasiModel fungsiImplikasiModelUser = dataSnapshot.getValue(FungsiImplikasiModel.class);
//                            Log.i("show result model",fungsiImplikasiModelUser.getHimpunanRingan());
                            fungsiImplikasiModelUser.setKey(dataSnapshot.getKey());
                            fungsiImplikasiModelsUser.add(fungsiImplikasiModelUser);
                        }

                        for (int j = 0; j < fungsiImplikasiModelsPakar.size(); j++) {
                            Log.e("Set -> ",fungsiImplikasiModelsPakar.get(j).getKey());
                            Log.e("Set -> ",fungsiImplikasiModelsUser.get(j).getKey());

                            //R1 Ringan && Ringan
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanRingan()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanRingan()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanRingan()),Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanRingan()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R1",fungsiImplikasiModelsPakar.get(j).getHimpunanRingan(),fungsiImplikasiModelsUser.get(j).getHimpunanRingan(),String.valueOf(minFunction),"0.2","RENDAH");
                                mDatabase.child("Fungsiimplikasi").child(userId).child(namaHama).child(fungsiImplikasiModelsPakar.get(j).getKey()).child("R1").setValue(createDataFI);
                                Log.e("Implikasi If result R1",String.valueOf(minFunction));
                            }
//                          R2
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanRingan()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanSedang()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanSedang()),Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanRingan()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R2",fungsiImplikasiModelsPakar.get(j).getHimpunanRingan(),fungsiImplikasiModelsUser.get(j).getHimpunanSedang(),String.valueOf(minFunction),"0.4","RENDAH");
                                mDatabase.child("Fungsiimplikasi").child(userId).child(namaHama).child(fungsiImplikasiModelsPakar.get(j).getKey()).child("R2").setValue(createDataFI);
                                Log.e("Implikasi If result R2",String.valueOf(minFunction));
                            }
                            //R3 Ringan && Parah
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanRingan()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanParah()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanParah()),Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanRingan()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R3",fungsiImplikasiModelsPakar.get(j).getHimpunanRingan(),fungsiImplikasiModelsUser.get(j).getHimpunanParah(),String.valueOf(minFunction),"0.6","SEDANG");
                                mDatabase.child("Fungsiimplikasi").child(userId).child(namaHama).child(fungsiImplikasiModelsPakar.get(j).getKey()).child("R3").setValue(createDataFI);
                                Log.e("Implikasi If result R3",String.valueOf(minFunction));
                            }
                            //R4
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanSedang()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanRingan()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanSedang()),Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanRingan()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R4",fungsiImplikasiModelsPakar.get(j).getHimpunanSedang(),fungsiImplikasiModelsUser.get(j).getHimpunanRingan(),String.valueOf(minFunction),"0.4","RENDAH");
                                mDatabase.child("Fungsiimplikasi").child(userId)
                                        .child(namaHama)
                                        .child(fungsiImplikasiModelsPakar.get(j).getKey())
                                        .child("R4").setValue(createDataFI);
                                Log.e("Implikasi If result R4 ",String.valueOf(minFunction));
                            }
                            //R5
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanSedang()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanSedang()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanSedang()),Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanSedang()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R5",fungsiImplikasiModelsPakar.get(j).getHimpunanSedang(),fungsiImplikasiModelsUser.get(j).getHimpunanSedang(),String.valueOf(minFunction),"0.6","SEDANG");
                                mDatabase.child("Fungsiimplikasi").child(userId).child(namaHama).child(fungsiImplikasiModelsPakar.get(j).getKey()).child("R5").setValue(createDataFI);
                                Log.e("Implikasi If result R5",String.valueOf(minFunction));
                            }
                            //R6
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanSedang()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanParah()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanSedang()),Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanParah()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R6",fungsiImplikasiModelsPakar.get(j).getHimpunanSedang(),fungsiImplikasiModelsUser.get(j).getHimpunanParah(),String.valueOf(minFunction),"0.8","TINGGI");
                                mDatabase.child("Fungsiimplikasi").child(userId).child(namaHama).child(fungsiImplikasiModelsPakar.get(j).getKey()).child("R6").setValue(createDataFI);
                                Log.e("Implikasi If result R6",String.valueOf(minFunction));
                            }
                            //R7
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanParah()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanRingan()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanParah()),Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanRingan()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R7",fungsiImplikasiModelsPakar.get(j).getHimpunanParah(),fungsiImplikasiModelsUser.get(j).getHimpunanRingan(),String.valueOf(minFunction),"0.6","SEDANG");
                                mDatabase.child("Fungsiimplikasi").child(userId).child(namaHama).child(fungsiImplikasiModelsPakar.get(j).getKey()).child("R7").setValue(createDataFI);
                                Log.e("Implikasi If result R7",String.valueOf(minFunction));
                            }
                            //R8
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanParah()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanSedang()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanParah()),Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanSedang()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R8",fungsiImplikasiModelsPakar.get(j).getHimpunanParah(),fungsiImplikasiModelsUser.get(j).getHimpunanSedang(),String.valueOf(minFunction),"0.8","TINGGI");
                                mDatabase.child("Fungsiimplikasi").child(userId).child(namaHama).child(fungsiImplikasiModelsPakar.get(j).getKey()).child("R8").setValue(createDataFI);
                                Log.e("Implikasi If result R8",String.valueOf(minFunction));
                            }
                            //R9
                            if (Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanParah()) != 0 && Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanParah()) != 0 ){
                                double minFunction = Math.min(Double.parseDouble(fungsiImplikasiModelsUser.get(j).getHimpunanParah()),Double.parseDouble(fungsiImplikasiModelsPakar.get(j).getHimpunanParah()));
                                CreateDataFI createDataFI = new CreateDataFI(fungsiImplikasiModelsPakar.get(j).getKey(),"R9",fungsiImplikasiModelsPakar.get(j).getHimpunanParah(),fungsiImplikasiModelsUser.get(j).getHimpunanParah(),String.valueOf(minFunction),"1","TINGGI");
                                mDatabase.child("Fungsiimplikasi").child(userId).child(namaHama).child(fungsiImplikasiModelsPakar.get(j).getKey()).child("R9").setValue(createDataFI);
                                Log.e("Implikasi If result R9",String.valueOf(minFunction));
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });



//                System.out.println("Result from counter : " + String.valueOf(lihathasil));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        for (int j = 0; j < fungsiImplikasiModels.size(); j++) {
//            Log.e("result fungsiimplikasi",fungsiImplikasiModels.get(0).getHimpunanSedang());
//        }

    }
}
