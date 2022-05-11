package com.company;

import com.company.DatabaseTables.AddressDatabeses;
import com.company.DatabaseTables.CourierDatabases;
import com.company.DatabaseTables.RestaurantDatabases;
import com.company.DatabaseTables.UserDatabases;
import com.company.entities.Restaurant;
import com.company.entities.User;
import com.company.services.MenuService;

import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /***
         * FOR RESET AUTOINCREMENT EXECUTE IN WORKBENCH:
         * ALTER TABLE tablename AUTO_INCREMENT = 1
         * DON'T FORGET TO COMMENT NOT TO HAVE DUPLICATES
         * !!!!put the password for db
         */
        ///ADDRESSES
//        AddressDatabeses addressDatabeses = new AddressDatabeses();
//        addressDatabeses.createTable();
//        addressDatabeses.insertAddress("IASI", "Iasi", "Unirii", 20);

        //System.out.println(addressDatabeses.getAddressById(16));
//        addressDatabeses.insertAddress("BUCURESTI", "Bucuresti", "Tineretului", 10);
//        addressDatabeses.updateAddressStreet("alba", 2);
//        addressDatabeses.deleteAllAddresses();


        ///USERS
//        UserDatabases userDatabases = new UserDatabases();
//        userDatabases.createTable();
//        userDatabases.insertUser("Miruna",1,"000", "miruna@yahoo.com");

////        User user = userDatabases.getUserById(1, addressDatabeses);
////        System.out.println(user);
//        userDatabases.updateUserEmail("miruna@gmail.com", 6);
//        userDatabases.deleteUser(6);


        //COURIERS
//        CourierDatabases courierDatabases = new CourierDatabases();
//        courierDatabases.createTable();
//        courierDatabases.insertCourier("Costel", "scooter", 0.0, 0, 0.0);

//        //System.out.println(courierDatabases.getCourierById(1));
//        courierDatabases.updateCourierTransport("boat",1);
//        courierDatabases.deleteCourier(1);


        //RESTAURANTS
//        RestaurantDatabases restaurantDatabases = new RestaurantDatabases();
//        restaurantDatabases.createTable();
//        restaurantDatabases.insertRestaurant("Apollo", 1);

//        List<Restaurant> restaurants  = restaurantDatabases.getAllRestaurants(addressDatabeses);
//        Iterator iterator = restaurants.iterator();
//
//        while(iterator.hasNext()) {
//            System.out.println(iterator.next());
//        }
//        restaurantDatabases.insertRestaurant("Apollo", 20);
//        //System.out.println(restaurantDatabases.getRestaurantById(1, addressDatabeses));
//        restaurantDatabases.updateRestaurantName("Magic", 1);
//        restaurantDatabases.deleteRestaurant(1);

        MenuService menu = MenuService.getInstance();
        menu.start();
    }
}
