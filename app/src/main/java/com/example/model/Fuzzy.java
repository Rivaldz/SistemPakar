package com.example.model;

public class Fuzzy {
    public void diseaseBla(double x){

        System.out.println("ini adalah hasil nilai " );

        System.out.println(x);

//        System.out.println(b);
//        System.out.println(c);

        //Ringan
        double hasilRingan = 0;
        if (x >= 0.6) {
            hasilRingan = 0;
        }

        if (0.4 <= x && x <= 0.6){
            hasilRingan = (0.6 - x) / (0.6 - 0.4);
        }

        if (x <= 0.4) {
            hasilRingan = 1;
        }

        //Sedang
        double hasilSedang = 0;

        if (x <= 0.4 || x >= 0.7) {
            hasilSedang = 0;
        }

        if (0.4 <= x && x <= 0.6){
           hasilSedang = (x - 0.4)/(0.6 - 0.4);
        }

        if (0.6 <= x && x <= 0.8){
           hasilSedang = (0.8 - x) / (0.8 - 0.6);
        }
//        if (a == 0.4);
//            hasilSedang = 0;

        //Parah
        double hasilParah = 0;
        if (x <= 0.6 )
        {
            hasilParah = 0;
            System.out.println("ini parah 0");
        }

        if (0.6 <= x && x <= 0.8)
        {
            hasilParah = (x - 0.6) / (0.8 - 0.6);
            System.out.println("ini parah ke 2");
        }

        if (0.8 <= x && x <= 1)
        {
            hasilParah = 1;
            System.out.println("ini parah ke 3");

        }

//        String stringHasilRingan = String.valueOf(hasilRingan);
//        String stringHasilSedang = String.valueOf(hasilSedang);
//        String stringHasilParah = String.valueOf(hasilParah);
        System.out.println(hasilRingan);
        System.out.println(hasilSedang);
        System.out.println(hasilParah);
    }

}
