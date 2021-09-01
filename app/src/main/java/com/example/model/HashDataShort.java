package com.example.model;

public class HashDataShort {
    Double minFunction, ziValue;

    public HashDataShort(Double minFunction, Double ziValue) {
        this.minFunction = minFunction;
        this.ziValue = ziValue;
    }

    public HashDataShort() {
    }

    public Double getMinFunction() {
        return minFunction;
    }

    public void setMinFunction(Double minFunction) {
        this.minFunction = minFunction;
    }

    public Double getZiValue() {
        return ziValue;
    }

    public void setZiValue(Double ziValue) {
        this.ziValue = ziValue;
    }
}
