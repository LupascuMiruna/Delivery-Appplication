package com.company.entities;

import com.company.services.AuditService;
import com.company.services.ReadService;

import java.util.*;
import java.util.function.Predicate;

public class Restaurant {
    private String name;
    private Adress adress;
    private Integer numberRatings = 0;
    private double score = 0.0;
    private List<Product> products = new ArrayList<>();

    static Integer numberRestaurants = 0;
    static Restaurant bestRatedRestaurant = null;
    public Scanner scanner = ConsoleReader.getScanner();
    static List<Courier> couriers = new ArrayList<>();

    private AuditService auditService;
    private static ReadService readService = ReadService.getInstance();

    static {
        couriers = readService.readCouriers();
    }

    public Restaurant(String name, Adress adress) {
        this.auditService = AuditService.getInstance();
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

    public Integer getNumberRatings() {
        return numberRatings;
    }

    public void setNumberRatings(Integer numberRatings) {
        this.numberRatings = numberRatings;
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

    public void receiveRating(double rating) {
        this.numberRatings += 1;
        this.score += rating;
        if (bestRatedRestaurant == null || bestRatedRestaurant.getRate() < this.getRate())
            bestRatedRestaurant = this;
    }

    public double getRate() {
        if (this.numberRatings == 0)
            return 0.0;
        return this.score / this.numberRatings;
    }

    public void addProducts(List<Product> productsToAdd) {
        this.products.addAll(productsToAdd);
    }

    public void showProducts() {
        for (int i = 0; i < products.size(); ++i)
            System.out.println(i + ")" + products.get(i));
    }

    public Integer getNumberDishes() {
        return products.size();
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addRating() {
        System.out.println("Rate it with a DOUBLE number from 1,0 to 10,0:");
        boolean ok = false;
        do {
            double rating = scanner.nextDouble();
            if (rating < 1.0 || rating > 10.0)
                System.out.println("Wrong format! Please input a DOUBLE number from 1,0 to 10,0: ");
            else {
                ok = true;
                this.receiveRating(rating);
                System.out.println("Thank you for your feedback! <3");
            }
        } while (!ok);
    }

    private void cartAction(List<Product> pickedProducts) {
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
            auditService.writeTime("remove_product_from_cart");
            pickedProducts.remove(option);
        } while (ok);

    }

    private List<Product> pickProducts() {
        List<Product> pickedProducts = new ArrayList<Product>();
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
                auditService.writeTime("add_product_to_cart");
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
        List<Product> pickedProducts = this.pickProducts();
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
            auditService.writeTime("see_order_details");
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
    public Product getSpecialProduct() {
        Product specialProduct = (this.products.stream().filter(p ->p.getName().equals("Special"))).findFirst().orElse(null);
        return specialProduct;
    }


    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", adress=" + adress +
                ", numberRatings=" + numberRatings +
                ", score=" + score +
                '}';
    }
//    @Override
//    public int compareTo(Restaurant restaurant) {
//        return this.name.compareTo(restaurant.getName())
//    }
}
