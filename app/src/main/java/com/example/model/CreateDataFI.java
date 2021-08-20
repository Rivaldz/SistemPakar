package com.example.model;

public class CreateDataFI {
    public String key, ruleName, fuzzyPakar, fuzzyUser, minFunc, ziValue, keanggotaan;

    public CreateDataFI(String key, String ruleName, String fuzzyPakar, String fuzzyUser, String minFunc, String ziValue, String keanggotaan) {
        this.key = key;
        this.ruleName = ruleName;
        this.fuzzyPakar = fuzzyPakar;
        this.fuzzyUser = fuzzyUser;
        this.minFunc = minFunc;
        this.ziValue = ziValue;
        this.keanggotaan = keanggotaan;
    }

    public CreateDataFI() {
    }
}
