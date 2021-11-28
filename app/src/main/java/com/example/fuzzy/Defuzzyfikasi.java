package com.example.fuzzy;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.model.AgreHashModel;
import com.example.model.DefuzzyfikasiModel;
import com.example.model.GetAgregation;
import com.example.model.HashDataShort;
import com.example.model.HasilAkhir;
import com.example.model.InputFuzzyDB;
import com.example.model.KeyGetAgregation;
import com.example.model.ShortDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Defuzzyfikasi {

    String namaPenyakit, userId,idSession,realNamaPenyakit;

    public Defuzzyfikasi() {
    }

    public Defuzzyfikasi(String namaPenyakit, String userId, String realNamaPenyakit) {
        this.namaPenyakit = namaPenyakit;
        this.userId = userId;
        this.realNamaPenyakit = realNamaPenyakit;

    }

    public Defuzzyfikasi(String idSess){
        this.idSession = idSess;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    DatabaseReference
            mDatabaseMain,mDatabaseGetDefuzzy,
            mDatabase,mDatabaseSedang,
            mDatabaseTinggi,mDatabaseRendah,
            mDatabaseAgre, getmDatabaseLastValue, getLomDB, insertResult;

    List<String > getKeyKeanggotaanRendah = new ArrayList<>();
    List<String > getKeyKeanggotaanSedang = new ArrayList<>();
    List<String > getKeyKeanggotaanTinggi = new ArrayList<>();
    List<ShortDataModel> shortValueSedang = new ArrayList<>();
    List<ShortDataModel> shortValueRendah = new ArrayList<>();
    List<String> keyGetAgregationList = new ArrayList<>();
    List<String> keyGejalaSt = new ArrayList<>();

    public Map<String, ArrayList<HashDataShort>> multiMapRendah = new HashMap<String, ArrayList<HashDataShort>>();
    public Map<String, ArrayList<HashDataShort>> multiMapSedang = new HashMap<String, ArrayList<HashDataShort>>();
    public Map<String, ArrayList<HashDataShort>> multiMapTinggi = new HashMap<String, ArrayList<HashDataShort>>();
    Map<String, ArrayList<AgreHashModel>> lastStep = new HashMap<String, ArrayList<AgreHashModel>>();


    public void defuzzyFikasi(){
        new LomDefuzzy(namaPenyakit,userId,namaPenyakit);

       mDatabase = FirebaseDatabase.getInstance().getReference().child("Fungsiimplikasi").child(userId).child(namaPenyakit);
       mDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                   keyGejalaSt.add(dataSnapshot.getKey());
               }
               onQueriesDone(keyGejalaSt);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

    }

    private void onQueriesDone(List<String>keyGejalaSt){
        for (int j = 0; j < keyGejalaSt.size(); j++) {
            Log.d("keyGejalaSt", keyGejalaSt.get(j));
            mDatabaseMain = FirebaseDatabase.getInstance().getReference().child("Fungsiimplikasi").child(userId).child(namaPenyakit).child(keyGejalaSt.get(j));
            int finalJ = j;
            mDatabaseMain.addValueEventListener(new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
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

                                if (multiMapRendah.containsKey(keyGejalaSt.get(finalJ))){
                                    ArrayList<HashDataShort> listRendah;
                                    listRendah = multiMapRendah.get(keyGejalaSt.get(finalJ));
                                    HashDataShort hashDataShort = new HashDataShort(Double.parseDouble(defuzzyfikasiModel.getMinFunc())
                                            ,Double.parseDouble(defuzzyfikasiModel.getZiValue()));

                                    listRendah.add(hashDataShort);
                                    multiMapRendah.put(keyGejalaSt.get(finalJ),listRendah);
                                }else {
                                   ArrayList<HashDataShort> listRendah = new ArrayList<HashDataShort>();
                                    HashDataShort hashDataShort = new HashDataShort(Double.parseDouble(defuzzyfikasiModel.getMinFunc())
                                            ,Double.parseDouble(defuzzyfikasiModel.getZiValue()));
                                   listRendah.add(hashDataShort);
                                   multiMapRendah.put(keyGejalaSt.get(finalJ),listRendah);
                                   listRendah=null;
                                }
                            }
                        }
                        if (defuzzyfikasiModel.getKeanggotaan().equalsIgnoreCase("SEDANG")) {
                            System.out.println("Hasil akhir gejala " + dataSnapshot.getKey() + "Penyakit : " + keyGejalaSt.get(finalJ) +
                                    " Hasil " + defuzzyfikasiModel.getKeanggotaan() + "--Sedang--");
                            ShortDataModel shortDataModel = new ShortDataModel(Double.parseDouble(defuzzyfikasiModel.getMinFunc()), Double.parseDouble(defuzzyfikasiModel.getZiValue()), keyGejalaSt.get(finalJ));
                            shortValueSedang.add(shortDataModel);
                            Log.e("hasil array", String.valueOf(shortValueSedang.size()));

                            if (keyGejalaSt.get(finalJ).equalsIgnoreCase(defuzzyfikasiModel.key)) {

                                if (multiMapSedang.containsKey(keyGejalaSt.get(finalJ))) {
                                    ArrayList<HashDataShort> listSedang;
                                    listSedang = multiMapSedang.get(keyGejalaSt.get(finalJ));
                                    HashDataShort hashDataShort = new HashDataShort(Double.parseDouble(defuzzyfikasiModel.getMinFunc())
                                            , Double.parseDouble(defuzzyfikasiModel.getZiValue()));
                                    listSedang.add(hashDataShort);
                                    multiMapSedang.put(keyGejalaSt.get(finalJ), listSedang);
                                } else {
                                    ArrayList<HashDataShort> listSedang = new ArrayList<>();
                                    HashDataShort hashDataShort = new HashDataShort(Double.parseDouble(defuzzyfikasiModel.getMinFunc())
                                            , Double.parseDouble(defuzzyfikasiModel.getZiValue()));
                                    listSedang.add(hashDataShort);
                                    multiMapSedang.put(keyGejalaSt.get(finalJ), listSedang);
                                    listSedang = null;
                                }
                            }
                        }

                        if (defuzzyfikasiModel.getKeanggotaan().equalsIgnoreCase("TINGGI")){
                            System.out.println("Hasil akhir gejala " + dataSnapshot.getKey() + "Penyakit : "+ keyGejalaSt.get(finalJ) +
                                    " Hasil " + defuzzyfikasiModel.getKeanggotaan() + "--Tinggi--");

                            if (keyGejalaSt.get(finalJ).equalsIgnoreCase(defuzzyfikasiModel.key)){

                                if (multiMapTinggi.containsKey(keyGejalaSt.get(finalJ))){
                                    ArrayList<HashDataShort> listTinggi;
                                    listTinggi = multiMapTinggi.get(keyGejalaSt.get(finalJ));
                                    HashDataShort hashDataShort = new HashDataShort(Double.parseDouble(defuzzyfikasiModel.getMinFunc())
                                            ,Double.parseDouble(defuzzyfikasiModel.getZiValue()));
                                    listTinggi.add(hashDataShort);
                                    multiMapTinggi.put(keyGejalaSt.get(finalJ),listTinggi);
                                }else {
                                    ArrayList<HashDataShort> listTinggi = new ArrayList<>();
                                    HashDataShort hashDataShort = new HashDataShort(Double.parseDouble(defuzzyfikasiModel.getMinFunc())
                                            ,Double.parseDouble(defuzzyfikasiModel.getZiValue()));
                                    listTinggi.add(hashDataShort);
                                    multiMapTinggi.put(keyGejalaSt.get(finalJ),listTinggi);
                                    listTinggi=null;
                                }
                              }
                            }

                        System.out.println("panjang hash " + multiMapSedang.size());
                    }

                    shortDataSedang();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });

        }
    }

   @RequiresApi(api = Build.VERSION_CODES.N)
   private void shortDataSedang(){
        mDatabaseRendah = FirebaseDatabase.getInstance().getReference();
        mDatabaseSedang = FirebaseDatabase.getInstance().getReference();
        mDatabaseTinggi = FirebaseDatabase.getInstance().getReference();

            for (Map.Entry<String, ArrayList<HashDataShort>> entry : multiMapSedang.entrySet()) {

                List<HashDataShort> values = entry.getValue();

                String key = entry.getKey();

                double max = values.get(0).getMinFunction();
                double maxZi = values.get(0).getZiValue();

                int siz = values.size();

                for (int i = 0; i < siz; i++) {
                    if (max < values.get(i).getMinFunction()) {
                        max = values.get(i).getMinFunction();
                        maxZi = values.get(i).getZiValue();

                    }

                }
                InputFuzzyDB inputFuzzyDB = new InputFuzzyDB("SEDANG", String.valueOf(max), String.valueOf(maxZi), key);
                mDatabaseSedang.child("Defuzzyfikasi").child(userId).child(namaPenyakit).child(String.valueOf(key)).child("SEDANG").setValue(inputFuzzyDB);
                System.out.println("Nilai Max Sedang adalah " + max + "Nilai Zi " + maxZi + "Key " + key);
            }

            for (Map.Entry<String, ArrayList<HashDataShort>> entry : multiMapRendah.entrySet()) {
                List<HashDataShort> values = entry.getValue();

                double min = values.get(0).getMinFunction();
                double max = values.get(0).getMinFunction();
                double maxZi = values.get(0).getZiValue();

                int siz = values.size();

                String key = entry.getKey();

                for (int i = 0; i < siz; i++) {
                    if (max < values.get(i).getMinFunction()) {
                        max = values.get(i).getMinFunction();
                        maxZi = values.get(i).getZiValue();

                    }

                }

                System.out.println("Nilai Max Rendah adalah " + max + "Nilai Zi " + maxZi + "key " + key);
                InputFuzzyDB inputFuzzyDB = new InputFuzzyDB("RENDAH", String.valueOf(max), String.valueOf(maxZi), key);
                mDatabaseRendah.child("Defuzzyfikasi").child(userId).child(namaPenyakit).child(String.valueOf(key)).child("RENDAH").setValue(inputFuzzyDB);
                System.out.println("Nilai Max Sedang adalah " + max + "Nilai Zi " + maxZi);
            }

            for (Map.Entry<String, ArrayList<HashDataShort>> entry : multiMapTinggi.entrySet()) {
                List<HashDataShort> values = entry.getValue();

                double min = values.get(0).getMinFunction();
                double max = values.get(0).getMinFunction();
                double maxZi = values.get(0).getZiValue();

                int siz = values.size();

                String key = entry.getKey();

                for (int i = 0; i < siz; i++) {
                    if (max < values.get(i).getMinFunction()) {
                        max = values.get(i).getMinFunction();
                        maxZi = values.get(i).getZiValue();

                    }

                }

                System.out.println("Nilai Max Rendah adalah " + max + "Nilai Zi " + maxZi + "key " + key);
                InputFuzzyDB inputFuzzyDB = new InputFuzzyDB("TINGGI", String.valueOf(max), String.valueOf(maxZi), key);
                mDatabaseTinggi.child("Defuzzyfikasi").child(userId).child(namaPenyakit).child(String.valueOf(key)).child("TINGGI").setValue(inputFuzzyDB);
                System.out.println("Nilai Max Sedang adalah " + max + "Nilai Zi " + maxZi);
            }
            multiMapRendah.clear();
            multiMapSedang.clear();
            multiMapTinggi.clear();

   }

   public void onAgregasi(){
       mDatabaseGetDefuzzy = FirebaseDatabase.getInstance().getReference().child("Defuzzyfikasi").child(userId).child(namaPenyakit);
       mDatabaseSedang = FirebaseDatabase.getInstance().getReference();
       mDatabaseTinggi = FirebaseDatabase.getInstance().getReference();

       mDatabaseGetDefuzzy.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    KeyGetAgregation keyGetAgregation = dataSnapshot.getValue(KeyGetAgregation.class);
                    keyGetAgregation.setKeyAgre(dataSnapshot.getKey());
                    keyGetAgregationList.add(keyGetAgregation.getKeyAgre());
                }
                saveListAgre(keyGetAgregationList);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });

   }

   private void saveListAgre(List<String>keyList){

       for (int j = 0; j < keyList.size(); j++) {
           mDatabaseAgre = FirebaseDatabase.getInstance().getReference().child("Defuzzyfikasi").child(userId).child(namaPenyakit).child(keyList.get(j));
           System.out.println("Ndeleng hasil keyne " + keyList.get(j));
           int finalJ = j;
           mDatabaseAgre.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GetAgregation getAgregation = dataSnapshot.getValue(GetAgregation.class);


                    System.out.println("--- Index " + keyList.get(finalJ) + "Keanggotaan " + getAgregation.getKeanggotaan()
                    + " Max Value " + getAgregation.maxValue + "key data " + getAgregation.getKey__());

                    if (keyList.get(finalJ).equalsIgnoreCase(getAgregation.getKey__())){
                        System.out.println("Same Data " + getAgregation.maxValue);
                        if (lastStep.containsKey(getAgregation.getKey__())){
                            ArrayList<AgreHashModel> agreHashModels;
                            agreHashModels = lastStep.get(getAgregation.getKey__());
                            AgreHashModel agreHashModel = new AgreHashModel(getAgregation.getKeanggotaan(),getAgregation.getMaxValue(),getAgregation.getZiValue());
                            agreHashModels.add(agreHashModel);
                            lastStep.put(getAgregation.getKey__(),agreHashModels);
                        }else {
                            ArrayList<AgreHashModel> agreHashModels = new ArrayList<AgreHashModel>();
                            AgreHashModel agreHashModel = new AgreHashModel(getAgregation.getKeanggotaan(),getAgregation.getMaxValue(),getAgregation.getZiValue());
                            agreHashModels.add(agreHashModel);
                            lastStep.put(getAgregation.getKey__(),agreHashModels);
                            agreHashModels = null;
                        }
                    }

                   shortAgregations();
                }

               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

       }
   }

    public void shortAgregations(){
        double hasilAkhir = 0;
        int sizeOuter = 0;
        double tempHasil = 0;

       getmDatabaseLastValue = FirebaseDatabase.getInstance().getReference();
       for (Map.Entry<String, ArrayList<AgreHashModel>> entry : lastStep.entrySet()) {

           List<AgreHashModel> values = entry.getValue();

           String key = entry.getKey();

           double max = Double.parseDouble(values.get(0).getMaxValue());
           double maxZi = Double.parseDouble(values.get(0).getZiValue());
           String keanggotaan = values.get(0).getKeanggotaan();

           int siz = values.size();

           for (int i = 0; i < siz; i++) {
               if (maxZi < Double.parseDouble(values.get(i).getZiValue())){
                   maxZi =  Double.parseDouble(values.get(i).getZiValue());
                   keanggotaan = values.get(i).getKeanggotaan();
                   max = Double.parseDouble(values.get(i).maxValue);
               }

           }

           System.out.println("gejala == " + key + "Dengan hasil " + maxZi + " Nilai keanggotaan " + keanggotaan + " Nilai max " + max);
           GetAgregation getAgregationLom = new GetAgregation(keanggotaan,String.valueOf(max),String.valueOf(maxZi),String.valueOf(key));
           getmDatabaseLastValue.child("DefuzzyLOM").child(userId).child(namaPenyakit).child(key).setValue(getAgregationLom);

           hasilAkhir = tempHasil + max ;
           tempHasil = hasilAkhir;
           sizeOuter++;
       }
        lastStep.clear();

   }


   public void getLOMFuzzy(String idUser, String kodePenyakit, String namaPenyakit,String nilaiCF){
       String userID = idUser;
       String penyakit = kodePenyakit;
       String namaPenyakitSt = namaPenyakit;
       String nilaiCertainty = nilaiCF;
       getLomDB = FirebaseDatabase.getInstance().getReference().child("DefuzzyLOM").child(userID).child(penyakit);
        getLomDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    GetAgregation getLom = dataSnapshot.getValue(GetAgregation.class);
                    String anggota = getLom.getKeanggotaan();
                    String key = getLom.getKey__();
                    if (anggota.equalsIgnoreCase("RENDAH")){

                        getKeyKeanggotaanRendah.add(anggota);
                    }else if(anggota.equalsIgnoreCase("SEDANG")){

                        getKeyKeanggotaanSedang.add(anggota);
                    }else {

                        getKeyKeanggotaanTinggi.add(anggota);
                    }

                }
                sumKeanggotaan(getKeyKeanggotaanRendah,getKeyKeanggotaanSedang,getKeyKeanggotaanTinggi,userID,penyakit,namaPenyakitSt,nilaiCertainty);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
   }

   public void sumKeanggotaan(List<String > a,List<String > b,List<String > c, String IDuser, String penya, String realPenyakiti,String valueCF){
        String userId = IDuser;
        String namaPenyakit = penya;
        String certaintyFactor = valueCF;
        String realNamaPenyakit1 = realPenyakiti;
        insertResult = FirebaseDatabase.getInstance().getReference();
        int sizeRendah = a.size();
        int sizeSedang = b.size();
        int sizeTinggi = c.size();

       Log.e("Hasil Rendah int", String.valueOf(sizeRendah) );
       Log.e("Hasil Tinggi int", String.valueOf(sizeSedang) );
       Log.e("Hasil Sedang int", String.valueOf(sizeTinggi) );
       if (sizeRendah != 0 || sizeSedang != 0 || sizeTinggi != 0 ) {

           if (sizeRendah >= sizeSedang) {
               if (sizeRendah >= sizeTinggi) {
                   Log.e("Hasil tertinggi Rendah", String.valueOf(sizeRendah));
                   HasilAkhir lastModel = new HasilAkhir(realNamaPenyakit1, certaintyFactor, "RINGAN");
                   insertResult.child("HasilAkhir").child(userId).child(namaPenyakit).setValue(lastModel);

                   getKeyKeanggotaanRendah.clear();
                   getKeyKeanggotaanSedang.clear();
                   getKeyKeanggotaanTinggi.clear();
               }
           } else if (sizeSedang >= sizeTinggi) {
               Log.e("Hasil tertinggi Sedang", String.valueOf(sizeSedang));
               HasilAkhir lastModel = new HasilAkhir(realNamaPenyakit1, certaintyFactor, "SEDANG");
               insertResult.child("HasilAkhir").child(userId).child(namaPenyakit).setValue(lastModel);

               getKeyKeanggotaanRendah.clear();
               getKeyKeanggotaanSedang.clear();
               getKeyKeanggotaanTinggi.clear();
           } else if (sizeTinggi >= sizeRendah) {
               if (sizeTinggi >= sizeSedang) {
                   Log.e("Hasil tertinggi Tinggi", String.valueOf(sizeTinggi));
                   HasilAkhir lastModel = new HasilAkhir(realNamaPenyakit1, certaintyFactor, "TINGGI");
                   insertResult.child("HasilAkhir").child(userId).child(namaPenyakit).setValue(lastModel);

                   getKeyKeanggotaanRendah.clear();
                   getKeyKeanggotaanSedang.clear();
                   getKeyKeanggotaanTinggi.clear();
               }

           }
       }

       a.clear();
       b.clear();
       c.clear();
       deleteHash();
    }

    public void deleteHash(){
        multiMapRendah.clear();
        multiMapSedang.clear();
        multiMapTinggi.clear();
        getKeyKeanggotaanRendah.clear();
        getKeyKeanggotaanTinggi.clear();
        getKeyKeanggotaanSedang.clear();
        shortValueRendah.clear();
        shortValueSedang.clear();
        keyGetAgregationList.clear();
        keyGejalaSt.clear();
        multiMapRendah.clear();
        multiMapSedang.clear();
        multiMapTinggi.clear();
        lastStep.clear();
    }

}
