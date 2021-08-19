package com.example.model;

public class DefuzzyfikasiModel {
    public String fuzzyPakar;
    public String fuzzyUser;
    public String minFunc;
    public String ruleName;
    public String ziValue;
    public String key;
    public String keanggotaan;

    public DefuzzyfikasiModel(String fuzzyPakar, String fuzzyUser, String minFunc, String ruleName, String ziValue, String key, String keanggotaan) {
        this.fuzzyPakar = fuzzyPakar;
        this.fuzzyUser = fuzzyUser;
        this.minFunc = minFunc;
        this.ruleName = ruleName;
        this.ziValue = ziValue;
        this.key = key;
        this.keanggotaan = keanggotaan;
    }

    public DefuzzyfikasiModel() {
    }

    public String getFuzzyPakar() {
        return fuzzyPakar;
    }

    public void setFuzzyPakar(String fuzzyPakar) {
        this.fuzzyPakar = fuzzyPakar;
    }

    public String getFuzzyUser() {
        return fuzzyUser;
    }

    public void setFuzzyUser(String fuzzyUser) {
        this.fuzzyUser = fuzzyUser;
    }

    public String getMinFunc() {
        return minFunc;
    }

    public void setMinFunc(String minFunc) {
        this.minFunc = minFunc;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getZiValue() {
        return ziValue;
    }

    public void setZiValue(String ziValue) {
        this.ziValue = ziValue;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKeanggotaan() {
        return keanggotaan;
    }

    public void setKeanggotaan(String keanggotaan) {
        this.keanggotaan = keanggotaan;
    }
}
