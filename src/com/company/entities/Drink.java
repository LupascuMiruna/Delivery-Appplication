package com.company.entities;

public class Drink extends Product {
    boolean withAlchool;

    public Drink(String name, Double price, boolean withAlchool) {
        super(name, price);
        this.withAlchool = withAlchool;
    }

    public boolean isWithAlchool() {
        return withAlchool;
    }

    public void setWithAlchool(boolean withAlchool) {
        this.withAlchool = withAlchool;
    }

    @Override
    public Double getPrice() {
        if (withAlchool == true) {
            return this.price * 3 / 2;
        }
        return price;
    }

    @Override
    public String toString() {
        return   "Drink{ name =" + name +
                "withAlchool=" + withAlchool + " price " + getPrice() +
                '}';
    }
}
