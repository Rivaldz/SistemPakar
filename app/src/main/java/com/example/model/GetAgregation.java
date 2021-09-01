package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class GetAgregation {
    public String keanggotaan, maxValue, ziValue, key__;

    public GetAgregation(String keanggotaan, String maxValue, String ziValue, String key__) {
        this.keanggotaan = keanggotaan;
        this.maxValue = maxValue;
        this.ziValue = ziValue;
        this.key__ = key__;
    }

    public String getKey__() {
        return key__;
    }

    public void setKey__(String key__) {
        this.key__ = key__;
    }

    public GetAgregation() {
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
