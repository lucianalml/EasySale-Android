package com.siervi.claudio.easysale;

import io.realm.RealmObject;

// table product
public class Product extends RealmObject {

    private String name;

    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
