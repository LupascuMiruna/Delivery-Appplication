package com.company.entities;

import java.util.*;

public class Restaurant {
    private String name;
    private Adress adress;
    private Integer numberRaitings = 0;
    private double score = 0.0;
    private ArrayList<Product> products = new ArrayList<Product>();

    static Integer numberRestaurants = 0;
    static Restaurant bestRatedRestaurant = null;
    public Scanner scanner = ConsoleReader.getScanner();
    static ArrayList<Courier> couriers = new ArrayList<Courier>();

    static {
        couriers.add(new Courier("Alexandru", "bicycle"));
        couriers.add(new Courier("Costel", "scooter"));
    }

    public Restaurant(String name, Adress adress) {
        this.name = name;
        this.adress = adress;
        numberRestaurants += 1;
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

    public Integer getNumberRaitings() {
        return numberRaitings;
    }

    public void setNumberRaitings(Integer numberRaitings) {
        this.numberRaitings = numberRaitings;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    static Integer getNumberRestaurants() {
        return numberRestaurants;
    }

    public void receiveRaiting(double raiting) {
        this.numberRaitings += 1;
        this.score += raiting;
        if (bestRatedRestaurant == null || bestRatedRestaurant.getRate() < this.getRate())
            bestRatedRestaurant = this;
    }

    public double getRate() {
        if (this.numberRaitings == 0)
            return 0.0;
        return this.score / this.numberRaitings;
    }

    public void addProducts(ArrayList<Product> productsToAdd) {
        this.products.addAll(productsToAdd);
    }

    public void showProducts() {
        for (int i = 0; i < products.size(); ++i)
            System.out.println(i + ")" + products.get(i));
    }

    public Integer getNumberDishes() {
        return products.size();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addRating() {
        System.out.println("Rate it with a DOUBLE number from 1,0 to 10,0:");
        boolean ok = false;
        do {
            double raiting = scanner.nextDouble();
            if (raiting < 1.0 || raiting > 10.0)
                System.out.println("Wrong format! Please input a DOUBLE number from 1,0 to 10,0: ");
            else {
                ok = true;
                this.receiveRaiting(raiting);
                System.out.println("Thank you for your feedback! <3");
            }
        } while (!ok);
    }

    private void cartAction(ArrayList<Product> pickedProducts) {
        boolean ok = true;
        int option;

        do {
            Integer numberProducts = pickedProducts.size();
            System.out.println("Want to delete any product?");
            System.out.println("Your yummy products:");

            for (int i = 0; i < numberProducts; ++i) {
                System.out.println(i + ")" + pickedProducts.get(i));
            }

            System.out.println(numberProducts + ") No, it's ok.");
            option = scanner.nextInt();

            if (option > numberProducts || option < 0) {
                System.out.println("Invalid option! :(");
                continue;
            }

            if (option == numberProducts) {
                ok = false;
                continue;
            }
            pickedProducts.remove(option);
        } while (ok);

    }

    private ArrayList<Product> pickProducts() {
        ArrayList<Product> pickedProducts = new ArrayList<Product>();
        Integer cartOption = this.products.size();
        Integer closeOption = cartOption + 1;
        Integer option;

        do {
            System.out.println("Choose your dish :p");
            this.showProducts();
            System.out.println(cartOption + ")Show cart");
            System.out.println(closeOption + ")Close");

            option = scanner.nextInt();

            if (option > closeOption || option < 0) {
                System.out.println("Invalid option! :(");
                continue;
            }

            if (option < cartOption) {
                pickedProducts.add(this.products.get(option));
                continue;
            }

            if (option == cartOption) {
                this.cartAction(pickedProducts);
            }
        } while (option != closeOption);

        return pickedProducts;
    }

    public Order placeOrder(User currentUser) {
        ArrayList<Product> pickedProducts = this.pickProducts();
        Order currentOrder = Order.createNewOrder(this, currentUser, pickedProducts);

        System.out.println("Congratulations, your order has been successfully placed!");
        System.out.println("Do you want to see its details?");
        System.out.println("1) Yes");
        System.out.println("2) No");
        int option = scanner.nextInt();
        while(option > 2) {
            System.out.println("Invalid option!");
            System.out.println("Do you want to see its details?");
            System.out.println("1) Yes");
            System.out.println("2) No");
            option = scanner.nextInt();
        }
        if (option == 1) {
            System.out.println("Final price: " + currentOrder.getFinalPrice());
            System.out.println("The adress will be " + currentOrder.getAdress());
        }

        return currentOrder;
    }

    public Courier getFreeCourier() {
        Random rand = new Random();
        int indexCourier = rand.nextInt(couriers.size());
        Courier currentCourier = couriers.get(indexCourier);
        System.out.println("Your courier will be " + currentCourier.toString());
        return currentCourier;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", adress=" + adress +
                ", numberRaitings=" + numberRaitings +
                ", score=" + score +
                '}';
    }
//    @Override
//    public int compareTo(Restaurant restaurant) {
//        return this.name.compareTo(restaurant.getName())
//    }
}
