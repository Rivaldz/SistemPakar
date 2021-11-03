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
}
