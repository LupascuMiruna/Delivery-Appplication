package com.company.entities;

public class Pizza extends Product {
    boolean vegetarian;

    public Pizza(String name, Double price, boolean vegetarian) {
        super(name, price);
        this.vegetarian = vegetarian;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    @Override
    public Double getPrice() {
        if (vegetarian == true) {
            return this.price * 3 / 4;
        }
        return price;
    }

    @Override
    public String toString() {
        return   "Pizza{ name = " + name +
                " vegetarian=" + vegetarian + " price " + getPrice() +
                '}';
    }
}
