package com.example.model;

public class ShortDataModel {

    Double nilaiMin, nilaiZi;
    String codeGejala;

    public ShortDataModel(Double nilaiMin, Double nilaiZi, String codeGejala) {
        this.nilaiMin = nilaiMin;
        this.nilaiZi = nilaiZi;
        this.codeGejala = codeGejala;
    }

    public ShortDataModel() {
    }

    public String getCodeGejala() {
        return codeGejala;
    }

    public void setCodeGejala(String codeGejala) {
        this.codeGejala = codeGejala;
    }

    public Double getNilaiMin() {
        return nilaiMin;
    }

    public void setNilaiMin(Double nilaiMin) {
        this.nilaiMin = nilaiMin;
    }

    public Double getNilaiZi() {
        return nilaiZi;
    }

    public void setNilaiZi(Double nilaiZi) {
        this.nilaiZi = nilaiZi;
    }
}
