package com.example.icecreamapp;

import java.io.Serializable;

public class OrderItem implements Serializable {
    String date;
    String flavor;
    String size;
    String cost;

    public OrderItem(String date, String flavor, String size, String cost) {
        this.date = date;
        this.flavor = flavor;
        this.size = size;
        this.cost = cost;
    }

    public String getInfo() {
        return date + flavor + size + cost;

    }

    public void setInfo(String date, String flavor, String size, String cost) {
        this.date = date;
        this.flavor = flavor;
        this.size = size;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return date + flavor + size + cost;
    }
}
