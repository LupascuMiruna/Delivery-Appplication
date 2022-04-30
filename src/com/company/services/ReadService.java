package com.company.services;

import com.company.entities.*;
import com.company.exceptions.CountyException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static java.lang.Double.parseDouble;


public class ReadService {
    private static ReadService instance;
    public static synchronized ReadService getInstance() {
        if (instance == null) {
            instance = new ReadService();
        }
        return instance;
    }

    public ArrayList<User> readUsers() {

        ArrayList<User> users = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("Files/user.csv"));
            String line = buffer.readLine();
            while(line != null) {
                //"Miruna", IASI, "Iasi", "Unirii", 20, "123", "miruna@gmail.com"
                String[] array = line.split(",");
                County county = null;
                try {
                    county = Arrays.stream(County.values())
                            .filter(c -> c.getValue().equalsIgnoreCase(array[1]))
                            .findFirst()
                            .orElseThrow(() -> new CountyException("Wrong county")); ///it needs a lambda expression that returns an exception instance
                }
                catch (CountyException exception) {
                    System.out.println(exception.getMessage());
                }

                User user = new User(array[0], new Adress(county,array[2],array[3], Integer.parseInt(array[4])), array[5], array[6]);
                users.add(user);
                line = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    
    public ArrayList<Restaurant> readRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("Files/restaurant.csv"));
            String line = buffer.readLine();
            while(line != null) {
                //Naive, IASI, Iasi, Unirii, 20
                String[] array = line.split(",");
                County county = null;
                try {
                    county = Arrays.stream(County.values())
                            .filter(c -> c.getValue().equalsIgnoreCase(array[2]))
                            .findFirst()
                            .orElseThrow(() -> new CountyException("Wrong county")); ///it needs a lambda expression that returns an exception instance
                }
                catch (CountyException exception) {
                    System.out.println(exception.getMessage());
                }
                ArrayList<Product> products = this.readProducts(array[0]);

                Restaurant restaurant = new Restaurant(array[1], new Adress(county,array[3],array[4], Integer.parseInt(array[5])));
                restaurant.addProducts(products);

                restaurants.add(restaurant);
                line = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return restaurants;
    }
    public <T extends Product> Product readOneProduct(String array[], Class<T> classOf) {

        if(classOf.toString().equals("class com.company.entities.Drink")){
            return  new Drink(array[1], parseDouble(array[2]), Boolean.parseBoolean(array[3]));
        }
        if(classOf.toString().equals("class com.company.entities.Sweet")){
            return new Sweet(array[1], parseDouble(array[2]), Boolean.parseBoolean(array[3]));
        }
        return new Pizza(array[1], parseDouble(array[2]), Boolean.parseBoolean(array[3]));
    }

    public ArrayList<Product> readProducts(String number) {
        ArrayList<Product> products = new ArrayList<>();
        try {
            String path = "Files/products_" + number + ".csv";
            BufferedReader buffer = new BufferedReader(new FileReader(path));
            String line = buffer.readLine();
            while(line != null) {
                //Alexandru, bicycle
                String[] array = line.split(",");
                Product product = null;
                if (array[0].equals( "Drink"))
                    product = readOneProduct(array,Drink.class);

                products.add(product);
                line = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public ArrayList<Courier> readCouriers() {
        ArrayList<Courier> couriers = new ArrayList<>();
        try {
            BufferedReader buffer = new BufferedReader(new FileReader("Files/courier.csv"));
            String line = buffer.readLine();
            while(line != null) {
                //Alexandru, bicycle
                String[] array = line.split(",");

                Courier courier = new Courier(array[0], array[1]);
                couriers.add(courier);
                line = buffer.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return couriers;
    }
}