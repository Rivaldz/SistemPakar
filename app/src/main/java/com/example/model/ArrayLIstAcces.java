package com.example.model;

import java.util.ArrayList;

public class ArrayLIstAcces {
    private static ArrayList<String> arrayList;

    public static ArrayList<String> getArrayList() {
        return arrayList;
    }

    public static void setArrayList(ArrayList<String> arrayList) {
        ArrayLIstAcces.arrayList = arrayList;
    }
}
