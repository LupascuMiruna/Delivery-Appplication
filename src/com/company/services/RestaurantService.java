package com.company.services;

import com.company.DatabaseTables.AddressDatabeses;
import com.company.DatabaseTables.RestaurantDatabases;
import com.company.entities.*;

import java.util.*;

public class RestaurantService {
    List<Restaurant> restaurants = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    ReadService readService;

    public RestaurantService() {
        Adress a1 = new Adress(County.IASI, "Iasi", "Unirii", 20);
        this.readService = ReadService.getInstance();

        /***
         * PART 2 --> read from files
         * this.restaurants = readService.readRestaurants();
         */
        //this.restaurants = readService.readRestaurants();

        /***
         * PART 3
         */
        AddressDatabeses addressDatabeses = AddressDatabeses.getInstance();
        this.restaurants = RestaurantDatabases.getInstance().getAllRestaurants(addressDatabeses);

        
//        ArrayList<Product> productsToAdd = new ArrayList<>();
//        productsToAdd.add(new Drink("Coca-cola", 12.0, false));
//        productsToAdd.add(new Drink("Special", 32.0, true));
//        productsToAdd.add(new Drink("Pina Colada", 30.0, true));
//        productsToAdd.add(new Sweet("Tiramisu", 20.0, true));
//        productsToAdd.add(new Pizza("Capricioasa", 35.0, false));
//        restaurants.get(0).addProducts(productsToAdd);
    }

    public Restaurant chooseRestaurant() {
        Restaurant currentRestaurant = null;
        Integer option;
        Boolean success = false;
        while (success == false) {
            System.out.println("Choose your restaurant:");
            this.SortRestaurants();

            for (Integer i = 0; i < restaurants.size(); ++i)
                System.out.println(i + ") " + restaurants.get(i).toString());
            option = scanner.nextInt();
            if (option >= restaurants.size())
                System.out.println("Invalid option :(");
            else {
                currentRestaurant = restaurants.get(option);
                success = true;
            }
        }
        return currentRestaurant;
    }

    public void SortRestaurants() {
        RestaurantNameComparator restaurantNameComparator = new RestaurantNameComparator();
        Collections.sort(restaurants, restaurantNameComparator);
    }


}
