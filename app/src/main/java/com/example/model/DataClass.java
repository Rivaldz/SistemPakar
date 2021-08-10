package com.example.model;

public class DataClass {

    String BobotGejala,KodeGejala,NamaGejala;

    public DataClass(String bobotGejala, String kodeGejala, String namaGejala) {
        BobotGejala = bobotGejala;
        KodeGejala = kodeGejala;
        NamaGejala = namaGejala;
    }

    public DataClass() {
    }

    public String getBobotGejala() {
        return BobotGejala;
    }

    public void setBobotGejala(String bobotGejala) {
        BobotGejala = bobotGejala;
    }

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

    @Override
    public String toString() {
        return "DataClass{" +
                "BobotGejala='" + BobotGejala + '\'' +
                ", KodeGejala='" + KodeGejala + '\'' +
                ", NamaGejala='" + NamaGejala + '\'' +
                '}';
    }
}
