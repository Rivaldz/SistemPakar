package com.example.model;

public class ShowUser {
    public String namaPenyakit, nilaiAkhir, countStatus;

    public ShowUser(String namaPenyakit, String nilaiAkhir, String keyMethod) {
        this.namaPenyakit = namaPenyakit;
        this.nilaiAkhir = nilaiAkhir;
        this.countStatus = keyMethod;
    }

    public ShowUser() {
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

    public String getKeyMethod() {
        return countStatus;
    }

    public void setKeyMethod(String keyMethod) {
        this.countStatus = keyMethod;
    }
}
