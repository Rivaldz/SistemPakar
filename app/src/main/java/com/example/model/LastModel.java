package com.example.model;

public class LastModel {
    public String namaPenyakit, nilaiAkhir, countStatus, nilaiAkhirFM;

    public LastModel(String namaPenyakit, String nilaiAkhir) {
        this.namaPenyakit = namaPenyakit;
        this.nilaiAkhir = nilaiAkhir;
    }

    public LastModel(String namaPenyakit, String nilaiAkhirFM, String countStatus, String CF) {
        this.namaPenyakit = namaPenyakit;
        this.nilaiAkhirFM = nilaiAkhirFM;
        this.countStatus = countStatus;
        this.nilaiAkhir = CF;
    }

    public LastModel() {
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public String getNilaiAkhir() {
        return nilaiAkhir;
    }

    public void setNilaiAkhir(String nilaiAkhir) {
        this.nilaiAkhir = nilaiAkhir;
    }

    public String getCountStatus() {
        return countStatus;
    }

    public void setCountStatus(String countStatus) {
        this.countStatus = countStatus;
    }

    public String getNilaiAkhirFM() {
        return nilaiAkhirFM;
    }

    public void setNilaiAkhirFM(String nilaiAkhirFM) {
        this.nilaiAkhirFM = nilaiAkhirFM;
    }
}
