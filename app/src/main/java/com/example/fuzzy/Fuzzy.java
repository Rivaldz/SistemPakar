package com.example.fuzzy;//package com.example.model;

import android.util.Log;
import android.view.Display;

import com.example.model.ModelFuzzyDB;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Fuzzy {
    private DatabaseReference mDatabase;

    public void penyakitH007_User(double a, double b, double c){
        mDatabase = FirebaseDatabase.getInstance().getReference();


        Log.e("lihat hasil fuzzy a ", String.valueOf(a));
        Log.e("lihat hasil fuzzy b ", String.valueOf(b));
        Log.e("lihat hasil fuzzy c ", String.valueOf(c));

        double arrayVar [] = {a,b,c};
        for (int j = 0; j < arrayVar.length; j++) {
            System.out.println("lihat hasil array " + arrayVar[j]);


            //himpunan Ringan
            double hasilRingan = 0;
            if (arrayVar[j] >= 0.6) {
                hasilRingan = 0;
                Log.e("fuzzyfi Uringan if 1", String.valueOf(hasilRingan));
            }

            if (0.4 < arrayVar[j] && arrayVar[j] < 0.6) {
                hasilRingan = (0.6 - arrayVar[j]) / (0.6 - 0.4);
                Log.e("fuzzyfi Uringan if 2 ", String.valueOf(hasilRingan));
            }

            if (arrayVar[j] <= 0.4) {
                hasilRingan = 1;
                Log.e("fuzzyfi Uringan if 3 ", String.valueOf(hasilRingan));
            }

            //himpunan Sedang
            double hasilSedang = 0;

            if ( arrayVar[j] <= 0.4 || arrayVar[j] >= 0.7) {
                hasilSedang = 0;
                Log.e("fuzzyfi Usedang if 1 ", String.valueOf(hasilSedang));

            }

            if (0.4 < arrayVar[j] && arrayVar[j] < 0.6) {
                hasilSedang = (arrayVar[j] - 0.4) / (0.6 - 0.4);
                Log.e("fuzzyfi Usedang if 2 ", String.valueOf(hasilSedang));
            }

            if (0.6 < arrayVar[j] && arrayVar[j] < 0.8) {
                hasilSedang = (0.8 - arrayVar[j]) / (0.8 - 0.6);
                Log.e("fuzzyfi Usedang if 3 ", String.valueOf(hasilSedang));
            }
//        if (a == 0.4);
//            hasilSedang = 0;

            //Parah
            double hasilParah = 0;
            if (arrayVar[j] <= 0.6) {
                hasilParah = 0;
                Log.e("fuzzyfi Uparah if 1", String.valueOf(hasilParah));

            }

            if (0.6 <= arrayVar[j] && arrayVar[j] <= 0.8) {
                hasilParah = (arrayVar[j] - 0.6) / (0.8 - 0.6);
                Log.e("fuzzyfi Uparah if 2", String.valueOf(hasilParah));
            }

            if (0.8 <= arrayVar[j] && arrayVar[j] <= 1) {
                hasilParah = 1;
                Log.e("fuzzyfi Uparah if 3", String.valueOf(hasilParah));

            }
            ModelFuzzyDB modelFuzzyDB = new ModelFuzzyDB(String.valueOf(hasilRingan),String.valueOf(hasilSedang),String.valueOf(hasilParah));
            mDatabase.child("Fuzzyfikasi").child("user").child(String.valueOf(j)).setValue(modelFuzzyDB);

        }

    }
    public void penyakitH007_Pakar(double a, double b, double c){
        mDatabase = FirebaseDatabase.getInstance().getReference();


        Log.e("lihat hasil fuzzy a ", String.valueOf(a));
        Log.e("lihat hasil fuzzy b ", String.valueOf(b));
        Log.e("lihat hasil fuzzy c ", String.valueOf(c));

        double arrayVar [] = {a,b,c};
        for (int j = 0; j < arrayVar.length; j++) {
            System.out.println("lihat hasil array " + arrayVar[j]);


            //himpunan Ringan
            double hasilRingan = 0;
            if (arrayVar[j] >= 0.6) {
                hasilRingan = 0;
                Log.e("fuzzyfi Uringan if 1", String.valueOf(hasilRingan));
            }

            if (0.4 < arrayVar[j] && arrayVar[j] < 0.6) {
                hasilRingan = (0.6 - arrayVar[j]) / (0.6 - 0.4);
                Log.e("fuzzyfi Uringan if 2 ", String.valueOf(hasilRingan));
            }

            if (arrayVar[j] <= 0.4) {
                hasilRingan = 1;
                Log.e("fuzzyfi Uringan if 3 ", String.valueOf(hasilRingan));
            }

            //himpunan Sedang
            double hasilSedang = 0;

            if ( arrayVar[j] <= 0.4 || arrayVar[j] >= 0.7) {
                hasilSedang = 0;
                Log.e("fuzzyfi Usedang if 1 ", String.valueOf(hasilSedang));

            }

            if (0.4 < arrayVar[j] && arrayVar[j] < 0.6) {
                hasilSedang = (arrayVar[j] - 0.4) / (0.6 - 0.4);
                Log.e("fuzzyfi Usedang if 2 ", String.valueOf(hasilSedang));
            }

            if (0.6 < arrayVar[j] && arrayVar[j] < 0.8) {
                hasilSedang = (0.8 - arrayVar[j]) / (0.8 - 0.6);
                Log.e("fuzzyfi Usedang if 3 ", String.valueOf(hasilSedang));
            }
//        if (a == 0.4);
//            hasilSedang = 0;

            //Parah
            double hasilParah = 0;
            if (arrayVar[j] <= 0.6) {
                hasilParah = 0;
                Log.e("fuzzyfi Uparah if 1", String.valueOf(hasilParah));

            }

            if (0.6 <= arrayVar[j] && arrayVar[j] <= 0.8) {
                hasilParah = (arrayVar[j] - 0.6) / (0.8 - 0.6);
                Log.e("fuzzyfi Uparah if 2", String.valueOf(hasilParah));
            }

            if (0.8 <= arrayVar[j] && arrayVar[j] <= 1) {
                hasilParah = 1;
                Log.e("fuzzyfi Uparah if 3", String.valueOf(hasilParah));

            }
            ModelFuzzyDB modelFuzzyDB = new ModelFuzzyDB(String.valueOf(hasilRingan),String.valueOf(hasilSedang),String.valueOf(hasilParah));
            mDatabase.child("Fuzzyfikasi").child("pakar").child(String.valueOf(j)).setValue(modelFuzzyDB);

        }
    }

    // new function

}
