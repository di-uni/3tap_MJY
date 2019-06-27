package com.example.mytab0626;

public class DataList {

    String name;
    String number;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public DataList() {
    }

    public DataList(String name, String number) {
        this.name = name;
        this.number = number;
    }
}
