package com.siervi.claudio.easysale;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

// table product
public class Product extends RealmObject {

    @PrimaryKey
    private int prod_id;

    private String name;

    private double price;

    private boolean ativo;

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
