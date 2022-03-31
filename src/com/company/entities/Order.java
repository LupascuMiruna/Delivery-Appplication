package com.company.entities;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Order implements Comparable<Order> {
    private UUID id;
    private Restaurant restaurant;
    private User user;
    private Courier courier = null;
    private Adress adress = null;
    private ArrayList<Product> products = new ArrayList<Product>();
    private Integer timePrepare = 0;
    private Double finalPrice = 0.0;
    public Scanner scanner = ConsoleReader.getScanner();

    public Order(Restaurant restaurant, User user, ArrayList<Product> products, Integer timePrepare, Double finalPrice) {
        this.restaurant = restaurant;
        this.user = user;
        this.products = products;
        this.timePrepare = timePrepare;
        this.finalPrice = finalPrice;
        this.id = UUID.randomUUID();
    }

    public Order(Restaurant restaurant, User user, ArrayList<Product> products) {
        this.restaurant = restaurant;
        this.user = user;
        this.products = products;
        this.assignCourier();
        this.assignAddress();
        this.id = UUID.randomUUID();
    }

    static Order createNewOrder(Restaurant currentRestaurant, User currentUser, ArrayList<Product> pickedProducts) {
        Order currentOrder = new Order(currentRestaurant, currentUser, pickedProducts);
        currentUser.addOrder(currentOrder);
        return currentOrder;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void deleteProduct(int index) {
        products.remove(index);
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Adress getAdress() {
        return adress;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public Integer getTimePrepare() {
        return timePrepare;
    }

    public void setTimePrepare(Integer timePrepare) {
        this.timePrepare = timePrepare;
    }

    public Double getFinalPrice() {
        finalPrice = 0.0;
        for (int i = 0; i < products.size(); ++i)
            finalPrice += products.get(i).getPrice();
        return finalPrice;
    }

    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Courier getCourier() {
        return courier;
    }

    public void setCourier(Courier courier) {
        this.courier = courier;
    }

    public void showProductsOrdered() {
        System.out.println("Your yummy products:");
        for (int i = 0; i < products.size(); ++i)
            System.out.println(i + ")" + products.get(i));
    }

    private void assignCourier() {
        this.courier = this.restaurant.getFreeCourier();
    }

    private void assignAddress() {
        do {
            System.out.println("1)Home");
            System.out.println("2)Another one");
            int option = scanner.nextInt();

            if (option == 1) {
                this.adress = this.user.getAdress();
                return;
            }
            if (option == 2) {
                Adress currentAddress = Adress.createNewAddress(scanner);
                this.adress = currentAddress;
                return;
            }
            System.out.println("Invalid option! :(");

        } while (true);
    }

    @Override
    public String toString() {
        return restaurant.getName() + " " + products.toString();
    }

    @Override
    public int compareTo(Order order) {
        return this.id.compareTo(order.id);
    }
}
