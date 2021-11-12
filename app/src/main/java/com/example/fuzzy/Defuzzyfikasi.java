package com.example.fuzzy;

import android.content.SharedPreferences;
import android.os.Build;
import android.provider.ContactsContract;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.diagnosahamapadi.MainActivity;
import com.example.model.AgreHashModel;
import com.example.model.ArrayListModel;
import com.example.model.DefuzzyfikasiModel;
import com.example.model.GetAgregation;
import com.example.model.GetKeyDefuzzyfikasi;
import com.example.model.HashDataShort;
import com.example.model.InputFuzzyDB;
import com.example.model.KeyGetAgregation;
import com.example.model.LastModel;
import com.example.model.ShortDataModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Defuzzyfikasi {

    String namaPenyakit, userId,idSession,realNamaPenyakit;

    MainActivity mainActivity = new MainActivity();

    SharedPreferences sharedPreferencesSession;

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

    private int rendah ;
    private int sedang;
    private int tinggi ;

    List<String > getKeyKeanggotaanRendah = new ArrayList<>();
    List<String > getKeyKeanggotaanSedang = new ArrayList<>();
    List<String > getKeyKeanggotaanTinggi = new ArrayList<>();
    List<DefuzzyfikasiModel> defuzzyfikasiModels = new ArrayList<>();
    List<DefuzzyfikasiModel> defuzzyfikasiModelsSedang = new ArrayList<>();
    List<DefuzzyfikasiModel> defuzzyfikasiModelsTingddi = new ArrayList<>();
    List<ShortDataModel> shortValueSedang = new ArrayList<>();
    List<ShortDataModel> shortValueRendah = new ArrayList<>();
    List<String> keyGetAgregationList = new ArrayList<>();
    List<GetKeyDefuzzyfikasi> keyGejala = new ArrayList<>();
    List<String> keyGejalaSt = new ArrayList<>();

    ArrayListModel arrayListModel = new ArrayListModel();

    public Map<String, ArrayList<HashDataShort>> multiMapRendah = new HashMap<String, ArrayList<HashDataShort>>();
    public Map<String, ArrayList<HashDataShort>> multiMapSedang = new HashMap<String, ArrayList<HashDataShort>>();
    public Map<String, ArrayList<HashDataShort>> multiMapTinggi = new HashMap<String, ArrayList<HashDataShort>>();
    Map<String, List<String>> objekMap = new HashMap<>();
    Map<String, ArrayList<AgreHashModel>> lastStep = new HashMap<String, ArrayList<AgreHashModel>>();


    public void defuzzyFikasi(){
        new LomDefuzzy(namaPenyakit,userId,namaPenyakit);

       mDatabase = FirebaseDatabase.getInstance().getReference().child("Fungsiimplikasi").child(userId).child(namaPenyakit);
       mDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                   DefuzzyfikasiModel defuzzyfikasiModel = dataSnapshot.getValue(DefuzzyfikasiModel.class);
//                   defuzzyfikasiModels.add(defuzzyfikasiModel);
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
//                            Log.d("Sedang","");
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

//                        Log.e("hasil array akhir", String.valueOf(shortValueSedang.size()));
                        System.out.println("panjang hash " + multiMapSedang.size());
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

   @RequiresApi(api = Build.VERSION_CODES.N)
   private void shortDataSedang(){
        mDatabaseRendah = FirebaseDatabase.getInstance().getReference();
        mDatabaseSedang = FirebaseDatabase.getInstance().getReference();
        mDatabaseTinggi = FirebaseDatabase.getInstance().getReference();

//        if (databaseChecker()){
//            DatabaseReference deleteDb = FirebaseDatabase.getInstance().getReference().child("Defuzzyfikasi").child(userId);
//            deleteDb.removeValue();
//        }else {
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

//           System.out.println("ini size " + values.size());

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

//           System.out.println("ini size " + values.size());

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
//        }
//       deleteHash();

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

//                    if (keyList.get(finalJ).equalsIgnoreCase(getAgregation.getKey__())){
//
//                        if (lastStep.containsKey(keyList.get(finalJ))){
//                            ArrayList<AgreHashModel> listNew;
//                            listNew = lastStep.get(keyList.get(finalJ));
//                            AgreHashModel hashFinal = new AgreHashModel(getAgregation.getKeanggotaan(),getAgregation.getMaxValue()
//                                    ,getAgregation.getZiValue());
//                            listNew.add(hashFinal);
//                            lastStep.put(keyList.get(finalJ),listNew);
//
//                        }else {
//                            ArrayList<AgreHashModel> listNew = new ArrayList<AgreHashModel>();
//                            AgreHashModel hashFinal = new AgreHashModel(getAgregation.getKeanggotaan(),getAgregation.getMaxValue(),
//                                    getAgregation.getZiValue());
//                            listNew.add(hashFinal);
//                            lastStep.put(keyList.get(finalJ),listNew);
//                            listNew=null;
//                        }
//                    }
//                    getAgregations.add(getAgregation);

                   shortAgregations();
                }
//                   System.out.println("Lihat hasil nilai dari agregasi " + getAgregations.get(0).getMaxValue() + "lihat key hasil agre " + keyList.get(finalJ) );
//                shortAgregations(getAgregations);

//                   shortAgregations();
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

       }
   }

    public void shortAgregations(){
//       sharedPreferencesSession = getSharedPreferences("session",MODE_PRIVATE);
//       sharedPreferencesSession.contains("varSession");
        double hasilAkhir = 0;
        int sizeOuter = 0;
        double tempHasil = 0;

//        String resultKeanggotaan = "";
//       System.out.println("short agre");
       getmDatabaseLastValue = FirebaseDatabase.getInstance().getReference();
       for (Map.Entry<String, ArrayList<AgreHashModel>> entry : lastStep.entrySet()) {

           List<AgreHashModel> values = entry.getValue();

           String key = entry.getKey();

           double max = Double.parseDouble(values.get(0).getMaxValue());
           double maxZi = Double.parseDouble(values.get(0).getZiValue());
           String keanggotaan = values.get(0).getKeanggotaan();

           int siz = values.size();
//           sizeOuter = siz;

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
       double result = hasilAkhir / sizeOuter;
       System.out.println("Ini adalah hasil akhir yang saya inginkan 000 " + result  + " hasil akhir " + hasilAkhir + " siw outer " + sizeOuter );
//       String sessionString = mainActivity.sessionString;
//       LastModel lastModel = new LastModel(realNamaPenyakit,String.valueOf(result),"1");
//       getmDatabaseLastValue.child("LastResult").child(userId).child("FM").setValue(lastModel);

//       getLOMFuzzy();
//       sumKeanggotaan();
       deleteHash();

   }


   public void getLOMFuzzy(String idUser, String kodePenyakit){
//       System.out.println("Ini user ID" + username);
//       System.out.println("Penyakit" + kodePenykit);
//       final String[] lihatHasil = new String[1];
       String userID = idUser;
       String penyakit = kodePenyakit;
       getLomDB = FirebaseDatabase.getInstance().getReference().child("DefuzzyLOM").child(userID).child(penyakit);
        getLomDB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String anggota = dataSnapshot.child("keanggotaan").getValue().toString();
                    String key = dataSnapshot.child("key__").getValue().toString();
                    if (anggota.equalsIgnoreCase("RENDAH")){
                        Log.e("Hasil RENDAH", anggota);
                        Log.e("Hasil key", key);
                        rendah++;
//                        getKeyKeanggotaanRendah.add(anggota);
                    }else if(anggota.equalsIgnoreCase("SEDANG")){
                        Log.e("Hasil SEDANG", anggota);
                        Log.e("Hasil key", key);
                        sedang++;
//                        getKeyKeanggotaanSedang.add(anggota);
                    }else {
                        Log.e("Hasil TINGGI", anggota);
                        Log.e("Hasil key", key);
                        tinggi++;
//                        getKeyKeanggotaanTinggi.add(anggota);
                    }
//                    Log.e("Hasil anggota", anggota);
//                    Log.e("Hasil key", key);
//                    lihatHasil[0] = snapshot.getKey();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    /*    Query getLomQuery = getLomDB.orderByChild("keanggotaan").equalTo("RENDAH");
        getLomQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String keyTest = dataSnapshot.getKey();
                    Log.e("Key Test ", keyTest);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

     */


//       LastModel lastModelSes = new LastModel(realNamaPenyakit,"1");
//        getmDatabaseLastValue.child("LastResult").child(userId).child("SES").child("keyy").setValue(lastModelSes);

//       sumKeanggotaan();
   }

   public void sumKeanggotaan(String idUser, String kodePenyakit, String nilaiCF){
        String userId = idUser;
        String namaPenyakit = kodePenyakit;
        String certaintyFactor = nilaiCF;
        insertResult = FirebaseDatabase.getInstance().getReference();
        int sizeRendah = getKeyKeanggotaanRendah.size();
        int sizeSedang = getKeyKeanggotaanSedang.size();
        int sizeTinggi = getKeyKeanggotaanTinggi.size();

       Log.e("Hasil Rendah int", String.valueOf(rendah) );
       Log.e("Hasil Tinggi int", String.valueOf(tinggi) );
       Log.e("Hasil Sedang int", String.valueOf(sedang) );
       if (rendah > sedang){
            if (rendah > tinggi){
                Log.e("Hasil tertinggi Rendah", String.valueOf(sizeRendah));
                LastModel lastModel = new LastModel(realNamaPenyakit, "RENDAH","1");
                insertResult.child("LastResult")
                        .child(userId)
                        .child(namaPenyakit)
                        .setValue(lastModel);
            }
        }else if(sedang > tinggi){
            Log.e("Hasil tertinggi Sedang", String.valueOf(sizeSedang));
            LastModel lastModel = new LastModel(realNamaPenyakit, "SEDANG","1");
            insertResult.child("LastResult")
                    .child(userId)
                    .child(namaPenyakit)
                    .setValue(lastModel);
        }else if(tinggi > rendah){
            if (tinggi > sedang){
                Log.e("Hasil tertinggi Tinggi", String.valueOf(sizeTinggi));
                LastModel lastModel = new LastModel(realNamaPenyakit, "TINGGI","1");
                insertResult.child("LastResult")
                        .child(userId)
                        .child(namaPenyakit)
                        .setValue(lastModel);
            }

        }

   }

    private void deleteHash(){
        multiMapRendah.clear();
        multiMapSedang.clear();
        multiMapTinggi.clear();
    }

//   public boolean databaseChecker(){
//       final boolean[] valueData = {false};
//        DatabaseReference dbChecker = FirebaseDatabase.getInstance().getReference().child("Defuzzyfikasi").child(userId);
//        dbChecker.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists());
//                    valueData[0] = true;
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//       return valueData[0];
//   }
}
