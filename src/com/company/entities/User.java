package com.company.entities;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

public class User {
    private String name;
    private Adress adress;
    private String password;
    private String email;
    private boolean isAdmin;
    private Set<Order> orders = new TreeSet<>();
    //list of orders

    public User(String name, Adress adress, String password, String email) {
        this.name = name;
        this.adress = adress;
        this.password = password;
        this.email = email;

    }

    public void addOrder(Order currentOrder) {
        this.orders.add(currentOrder);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "User: name =" + name + " adress = " + adress + "pass: " + password;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User u = (User) obj;
        if (this.email != u.email || this.password != u.password)
            return false;
        return true;
    }

    public void seeOrders() {
        if (orders.size() == 0) {
            System.out.println("You don't have any orders!");
            return;
        }
        System.out.println("Your orders");
        for (Order order : orders) {
            System.out.println(order);
        }
    }

}
