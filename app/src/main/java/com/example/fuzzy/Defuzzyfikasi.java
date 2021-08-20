package com.example.fuzzy;

import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.model.ArrayLIstAcces;
import com.example.model.ArrayListModel;
import com.example.model.DefuzzyfikasiModel;
import com.example.model.FungsiImplikasiModel;
import com.example.model.GetKeyDefuzzyfikasi;
import com.example.model.GetKeyKeanggotaan;
import com.example.model.ShortDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Defuzzyfikasi {
    DatabaseReference mDatabaseMain,mDatabase,mDatabaseSedang,mDatabaseTinggi;

    List<GetKeyDefuzzyfikasi> keyGejala = new ArrayList<>();
    List<String> keyGejalaSt = new ArrayList<>();
    Map<String, List<String>> objekMap = new HashMap<>();

    List<String > getKeyKeanggotaanRendah = new ArrayList<>();
    List<String > getKeyKeanggotaanSedang = new ArrayList<>();
    List<String > getKeyKeanggotaanTinggi = new ArrayList<>();

    ArrayListModel arrayListModel = new ArrayListModel();
    List<DefuzzyfikasiModel> defuzzyfikasiModels = new ArrayList<>();
    List<DefuzzyfikasiModel> defuzzyfikasiModelsSedang = new ArrayList<>();
    List<DefuzzyfikasiModel> defuzzyfikasiModelsTingddi = new ArrayList<>();

    List<ShortDataModel> shortValueSedang = new ArrayList<>();
    List<ShortDataModel> shortValueRendah = new ArrayList<>();

    Map<String, ArrayList<Double>> multiMap = new HashMap<String, ArrayList<Double>>();

    public void defuzzyFikasi(){

       mDatabase = FirebaseDatabase.getInstance().getReference().child("Fungsiimplikasi");
       mDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                   DefuzzyfikasiModel defuzzyfikasiModel = dataSnapshot.getValue(DefuzzyfikasiModel.class);
//                   defuzzyfikasiModels.add(defuzzyfikasiModel);
                   keyGejalaSt.add(dataSnapshot.getKey());
               }
               onQueriesDone(keyGejalaSt);
//               for (int i = 0; i < keyGejalaSt.size(); i++) {
//                   System.out.println("Lihat hasil dari implikasi " + keyGejalaSt.get(i));
//                   mDatabaseMain  = FirebaseDatabase.getInstance().getReference().child("Fungsiimplikasi").child(keyGejalaSt.get(i));
//                   try {
//                       TimeUnit.SECONDS.sleep(2);
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }
//                   mDatabaseMain.addValueEventListener(new ValueEventListener() {
//                       @Override
//                       public void onDataChange(@NonNull DataSnapshot snapshot) {
//                           for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                               DefuzzyfikasiModel defuzzyfikasiModel = dataSnapshot.getValue(DefuzzyfikasiModel.class);
//                               defuzzyfikasiModels.add(defuzzyfikasiModel);
//                           }
//                           for (int j = 0; j < defuzzyfikasiModels.size(); j++) {
//                               System.out.println("Bissmillah yaAllah " + defuzzyfikasiModels.get(j).getKeanggotaan());
//                           }
//                           defuzzyfikasiModels.clear();
//                       }
//
//                       @Override
//                       public void onCancelled(@NonNull DatabaseError error) {
//
//                       }
//                   });
//               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }

    private void onQueriesDone(List<String>keyGejalaSt){
        for (int j = 0; j < keyGejalaSt.size(); j++) {
            Log.d("keyGejalaSt", keyGejalaSt.get(j));
            mDatabaseMain = FirebaseDatabase.getInstance().getReference().child("Fungsiimplikasi").child(keyGejalaSt.get(j));
            int finalJ = j;
            mDatabaseMain.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        DefuzzyfikasiModel defuzzyfikasiModel = dataSnapshot.getValue(DefuzzyfikasiModel.class);
                        Log.d("Lihat hasil akhir ", defuzzyfikasiModel.getKeanggotaan());
//                        System.out.println("Hasil akhir gejala " + dataSnapshot.getKey() + "Penyakit : "+ keyGejalaSt.get(finalJ) + " Hasil " + defuzzyfikasiModel.getKeanggotaan());

                        if (defuzzyfikasiModel.getKeanggotaan().equalsIgnoreCase("RENDAH")){
//                            Log.d("Rendah","");
                            System.out.println("Hasil akhir gejala " + dataSnapshot.getKey() + "Penyakit : "+ keyGejalaSt.get(finalJ) +
                                    " Hasil " + defuzzyfikasiModel.getKeanggotaan() + "--Rendah--");
                            ShortDataModel shortDataModel = new ShortDataModel(Double.parseDouble(defuzzyfikasiModel.getMinFunc()),Double.parseDouble(defuzzyfikasiModel.getZiValue()),keyGejalaSt.get(finalJ));
                            shortValueRendah.add(shortDataModel);
                            Log.e("hasil array", String.valueOf(shortValueRendah.size()));

                            if (keyGejalaSt.get(finalJ).equalsIgnoreCase(defuzzyfikasiModel.key)){

                                if (multiMap.containsKey(keyGejalaSt.get(finalJ))){
                                    ArrayList<Double> list;
                                    list = multiMap.get(keyGejalaSt.get(finalJ));
                                    list.add(Double.valueOf(defuzzyfikasiModel.getMinFunc()));
                                    multiMap.put(keyGejalaSt.get(finalJ),list);
                                }else {
                                   ArrayList<Double> list = new ArrayList<Double>();
                                   list.add(Double.parseDouble(defuzzyfikasiModel.getMinFunc()));
                                   multiMap.put(keyGejalaSt.get(finalJ),list);
                                   list=null;
                                }
                            }
                        }
                        if (defuzzyfikasiModel.getKeanggotaan().equalsIgnoreCase("SEDANG")){
//                            Log.d("Sedang","");
                            System.out.println("Hasil akhir gejala " + dataSnapshot.getKey() + "Penyakit : "+ keyGejalaSt.get(finalJ) +
                                    " Hasil " + defuzzyfikasiModel.getKeanggotaan() + "--Sedang--");
                            ShortDataModel shortDataModel = new ShortDataModel(Double.parseDouble(defuzzyfikasiModel.getMinFunc()),Double.parseDouble(defuzzyfikasiModel.getZiValue()),keyGejalaSt.get(finalJ));
                            shortValueSedang.add(shortDataModel);
                            Log.e("hasil array", String.valueOf(shortValueSedang.size()));

                            if (keyGejalaSt.get(finalJ).equalsIgnoreCase(defuzzyfikasiModel.key)){

                                if (multiMap.containsKey(keyGejalaSt.get(finalJ))){
                                    ArrayList<Double> list;
                                    list = multiMap.get(keyGejalaSt.get(finalJ));
                                    list.add(Double.valueOf(defuzzyfikasiModel.getMinFunc()));
                                    multiMap.put(keyGejalaSt.get(finalJ),list);
                                }else {
                                    ArrayList<Double> list = new ArrayList<Double>();
                                    list.add(Double.parseDouble(defuzzyfikasiModel.getMinFunc()));
                                    multiMap.put(keyGejalaSt.get(finalJ),list);
                                    list=null;
                                }
                            }

                        if (defuzzyfikasiModel.getKeanggotaan().equalsIgnoreCase("TINGGI")){
                            System.out.println("Hasil akhir gejala " + dataSnapshot.getKey() + "Penyakit : "+ keyGejalaSt.get(finalJ) +
                                    " Hasil " + defuzzyfikasiModel.getKeanggotaan() + "--Tinggi--");
                            }
                        }
                        Log.e("hasil array akhir", String.valueOf(shortValueSedang.size()));
                    }

//                    list.clear();
                    shortDataSedang();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

   private void shortDataSedang(){
//        Map<String, ArrayList<String >> multiMap = new HashMap<String, ArrayList<String>>();
//        List<String> list = new ArrayList<String>();
//       for (int j = 0; j < keyGejalaSt.size(); j++) {
//           for (int i = 0; i < shortValueSedang.size(); i++) {
//               Log.e("hasil array akhir", String.valueOf(shortValueSedang.get(i).getNilaiZi()));
//               System.out.println("hasil Zi " + String.valueOf(shortValueSedang.get(i).getNilaiZi()) + "Code gejala "
//                       + shortValueSedang.get(i).getCodeGejala() + "Code gejala snapshot " + keyGejalaSt.get(j));
//
//               if (shortValueSedang.get(i).getCodeGejala().equalsIgnoreCase(keyGejalaSt.get(j))){
//                    Log.d("this ", "Same gejala");
//                    list.add(String.valueOf(shortValueSedang.get(i).getNilaiZi()));
//                    multiMap.put(keyGejalaSt.get(j), (ArrayList<String>) list);
//               }
//           }
//
//       }
       for (Map.Entry<String, ArrayList<Double>> entry : multiMap.entrySet()) {

           String key = entry.getKey();

           List<Double> values = entry.getValue();

           double maxValue = (double) Collections.max(values);
           System.out.println("Value of " + key + " is " + values.toString());
           System.out.println("this max value " + maxValue + "with keyy " + key);

       }

   }

}
