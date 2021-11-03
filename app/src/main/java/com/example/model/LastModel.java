package com.example.model;

public class LastModel {
    public String namaPenyakit, nilaiAkhir, countStatus;

    public LastModel(String namaPenyakit, String nilaiAkhir) {
        this.namaPenyakit = namaPenyakit;
        this.nilaiAkhir = nilaiAkhir;
    }

    public LastModel(String namaPenyakit, String nilaiAkhir, String countStatus) {
        this.namaPenyakit = namaPenyakit;
        this.nilaiAkhir = nilaiAkhir;
        this.countStatus = countStatus;
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
}
