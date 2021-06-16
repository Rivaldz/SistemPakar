package com.example.model;

public class Fuzzy {
    public void diseaseBla(double a, double b, double c){

        System.out.println("ini adalah hasil nilai " );

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);

        //Ringan
        double hasilRingan;
        if (a >= 0.4);
             hasilRingan = 0;

        if (0.4 >= a && a >= 0.4){
            hasilRingan = (0.4 - a)  / (0.4 - 0.3);
        }
        if (a <= 0.4);
            hasilRingan = 1;

        //Sedang
        double hasilSedang;
        if (a <= 0.3 || a >= 0.7);
            hasilSedang = 0;

        if (0.3 < a && a < 0.4){
           hasilSedang = (a - 0.3)/0.3;
        }
        if (0.4 < a && a < 0.7){
           hasilSedang = (0.7 - a)/0.3;
        }
        if (a == 0.4);
            hasilSedang = 0;

        //Parah
        if (a < 0.4 );
        if (0.6 < a && a < 1);
        if (a >= 1);
    }

}
