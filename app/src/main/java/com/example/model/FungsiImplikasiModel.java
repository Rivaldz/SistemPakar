package com.example.model;

public class FungsiImplikasiModel {
    String himpunanParah, himpunanSedang, himpunanRingan, key;

    public FungsiImplikasiModel(String himpunanParah, String himpunanSedang, String himpunanRingan, String key) {
        this.himpunanParah = himpunanParah;
        this.himpunanSedang = himpunanSedang;
        this.himpunanRingan = himpunanRingan;
        this.key = key;
    }

    public FungsiImplikasiModel() {
    }

    public String getHimpunanParah() {
        return himpunanParah;
    }

    public void setHimpunanParah(String himpunanParah) {
        this.himpunanParah = himpunanParah;
    }

    public String getHimpunanSedang() {
        return himpunanSedang;
    }

    public void setHimpunanSedang(String himpunanSedang) {
        this.himpunanSedang = himpunanSedang;
    }

    public String getHimpunanRingan() {
        return himpunanRingan;
    }

    public void setHimpunanRingan(String himpunanRingan) {
        this.himpunanRingan = himpunanRingan;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
