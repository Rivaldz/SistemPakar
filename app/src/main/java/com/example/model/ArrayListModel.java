package com.example.model;

import java.util.List;

public class ArrayListModel {
    private List<String> list;
    private String key;

    public ArrayListModel() {
    }

    public List<String> getList(){
        return list;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
