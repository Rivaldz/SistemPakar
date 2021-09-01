package com.example.model;

public class AgreHashModel {
    public String keanggotaan, maxValue, ziValue;

    public AgreHashModel(String keanggotaan, String maxValue, String ziValue) {
        this.keanggotaan = keanggotaan;
        this.maxValue = maxValue;
        this.ziValue = ziValue;
    }

    public AgreHashModel() {
    }

    public String getKeanggotaan() {
        return keanggotaan;
    }

    public void setKeanggotaan(String keanggotaan) {
        this.keanggotaan = keanggotaan;
    }

    public String getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(String maxValue) {
        this.maxValue = maxValue;
    }

    public String getZiValue() {
        return ziValue;
    }

    public void setZiValue(String ziValue) {
        this.ziValue = ziValue;
    }
}
