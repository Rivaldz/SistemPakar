package com.example.model;


public class Gejala {
    String BobotGejala,KodeGejala,NamaGejala;

    public Gejala( String kodeGejala, String namaGejala) {
//        BobotGejala = bobotGejala;
        KodeGejala = kodeGejala;
        NamaGejala = namaGejala;
    }

    public Gejala() {
    }

//    public String getBobotGejala() {
//        return BobotGejala;
//    }

//    public void setBobotGejala(String bobotGejala) {
//        BobotGejala = bobotGejala;
//    }

    public String getKodeGejala() {
        return KodeGejala;
    }

    public void setKodeGejala(String kodeGejala) {
        KodeGejala = kodeGejala;
    }

    public String getNamaGejala() {
        return NamaGejala;
    }

    public void setNamaGejala(String namaGejala) {
        NamaGejala = namaGejala;
    }
}
