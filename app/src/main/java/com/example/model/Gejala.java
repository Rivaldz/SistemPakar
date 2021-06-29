package com.example.model;

public class Gejala {
    private String age, name, nis;


    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

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

    public Gejala(String age, String name, String nis) {
        this.age = age;
        this.name = name;
        this.nis = nis;
    }

    @Override
    public String toString() {
        return "Gejala{" +
                "age='" + age + '\'' +
                ", name='" + name + '\'' +
                ", nis='" + nis + '\'' +
                '}';
    }
}
