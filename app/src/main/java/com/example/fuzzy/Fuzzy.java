package com.example.fuzzy;//package com.example.model;

import android.util.Log;

public class Fuzzy {
    public void penyakitH001(double a, double b, double c){

        Log.e("lihat hasil fuzzy a ", String.valueOf(a));
        Log.e("lihat hasil fuzzy b ", String.valueOf(b));
        Log.e("lihat hasil fuzzy c ", String.valueOf(c));

        double arrayVar [] = {a,b,c};
        for (int j = 0; j < arrayVar.length; j++) {
            System.out.println("lihat hasil array " + arrayVar[j]);


            //Ringan
            double hasilRingan = 0;
            if (arrayVar[j] >= 0.6) {
                hasilRingan = 0;
                Log.e("fuzzyfi Uringan if 1", String.valueOf(hasilRingan));
            }

            if (0.4 <= arrayVar[j] && arrayVar[j] <= 0.6) {
                hasilRingan = (0.6 - arrayVar[j]) / (0.6 - 0.4);
                Log.e("fuzzyfi Uringan if 2 ", String.valueOf(hasilRingan));
            }

            if (arrayVar[j] <= 0.4) {
                hasilRingan = 1;
                Log.e("fuzzyfi Uringan if 3 ", String.valueOf(hasilRingan));
            }

            //Sedang
            double hasilSedang = 0;

            if ( arrayVar[j]<= 0.4 || arrayVar[j] >= 0.7) {
                hasilSedang = 0;
                Log.e("fuzzyfi Usedang if 1 ", String.valueOf(hasilSedang));

            }

            if (0.4 <= arrayVar[j] && arrayVar[j] <= 0.6) {
                hasilSedang = (arrayVar[j] - 0.4) / (0.6 - 0.4);
                Log.e("fuzzyfi Usedang if 2 ", String.valueOf(hasilSedang));
            }

            if (0.6 <= arrayVar[j] && arrayVar[j] <= 0.8) {
                hasilSedang = (0.8 - arrayVar[j]) / (0.8 - 0.6);
                Log.e("fuzzyfi Usedang if 3 ", String.valueOf(hasilSedang));
            }
//        if (a == 0.4);
//            hasilSedang = 0;

            //Parah
            double hasilParah = 0;
            if (arrayVar[j] <= 0.6) {
                hasilParah = 0;
                System.out.println("ini parah 0");
            }

            if (0.6 <= arrayVar[j] && arrayVar[j] <= 0.8) {
                hasilParah = (arrayVar[j] - 0.6) / (0.8 - 0.6);
                System.out.println("ini parah ke 2");
            }

            if (0.8 <= arrayVar[j] && arrayVar[j] <= 1) {
                hasilParah = 1;
                System.out.println("ini parah ke 3");

            }

        }

        // input firebase

    }

    // new function

}
