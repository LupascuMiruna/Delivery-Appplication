package com.company.entities;

public class Sweet extends Product {
    boolean dietetic;

    public Sweet(String name, Double price, boolean dietetic) {
        super(name, price);
        this.dietetic = dietetic;
    }

    public boolean isDietetic() {
        return dietetic;
    }

    public void setDietetic(boolean dietetic) {
        this.dietetic = dietetic;
    }

    @Override
    public Double getPrice() {
        if (dietetic == true) {
            return this.price * 2;
        }
        return price;
    }

    @Override
    public String toString() {
        return  "Sweet{ name = " + name +
                " dietetic =" + dietetic + " price " + getPrice() +
        '}';
    }
}
