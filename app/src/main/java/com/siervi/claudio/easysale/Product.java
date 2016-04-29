package com.siervi.claudio.easysale;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

// table product
public class Product extends RealmObject {

    @PrimaryKey
    private int id;

    private String name;
    private double price;
    private boolean ativo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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


    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
