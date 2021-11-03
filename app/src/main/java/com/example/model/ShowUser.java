package com.example.model;

public class ShowUser {

    public String countStatus, namaPenyakit, nilaiAkhir;

    public ShowUser(String countStatus, String namaPenyakit, String nilaiAkhir) {
        this.countStatus = countStatus;
        this.namaPenyakit = namaPenyakit;
        this.nilaiAkhir = nilaiAkhir;
    }

    public ShowUser() {
    }

    public String getCountStatus() {
        return countStatus;
    }

    public void setCountStatus(String countStatus) {
        this.countStatus = countStatus;
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
}
