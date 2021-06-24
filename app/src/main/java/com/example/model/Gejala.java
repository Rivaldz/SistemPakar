package com.example.model;

public class Gejala {
    private String name, nis;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNis() {
        return nis;
    }

    public void setNis(String nis) {
        this.nis = nis;
    }

    public Gejala() {
    }

    public Gejala(String name, String nis) {
        this.name = name;
        this.nis = nis;
    }

    @Override
    public String toString() {
        return "Gejala{" +
                "name='" + name + '\'' +
                ", nis='" + nis + '\'' +
                '}';
    }
}
