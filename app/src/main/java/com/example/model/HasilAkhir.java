package com.example.model;

public class HasilAkhir {
    public String namaPenyakit, hasilCF, hasiFM;

    public HasilAkhir(String namaPenyakit, String hasilCF, String hasiFM) {
        this.namaPenyakit = namaPenyakit;
        this.hasilCF = hasilCF;
        this.hasiFM = hasiFM;
    }

    public String getNamaPenyakit() {
        return namaPenyakit;
    }

    public void setNamaPenyakit(String namaPenyakit) {
        this.namaPenyakit = namaPenyakit;
    }

    public String getHasilCF() {
        return hasilCF;
    }

    public void setHasilCF(String hasilCF) {
        this.hasilCF = hasilCF;
    }

    public String getHasiFM() {
        return hasiFM;
    }

    public void setHasiFM(String hasiFM) {
        this.hasiFM = hasiFM;
    }
}
