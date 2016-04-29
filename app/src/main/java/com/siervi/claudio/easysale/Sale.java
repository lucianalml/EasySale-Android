package com.siervi.claudio.easysale;

import java.util.Date;

import io.realm.RealmObject;

/**
 * Created by Claudio on 15/04/2016.
 */

// table sale
public class Sale extends RealmObject {

    private int id;

    private Product product;
    private int quantity;
    private double valor_total;

    private Date date;

    public double getValor_total() {
        return valor_total;
    }

    public void setValor_total(double valor_total) {
        this.valor_total = valor_total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
