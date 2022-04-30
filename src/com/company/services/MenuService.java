package com.company.services;

import com.company.entities.*;
import com.company.interfaces.IMenuService;

import java.util.Scanner;

public class MenuService implements IMenuService {
    private static MenuService instance;
    private User currentUser = null;
    private Order currentOrder = null;
    private Restaurant currentRestaurant = null;
    private UserService userMgr = new UserService();
    private RestaurantService restaurantMgr = new RestaurantService();
    public Scanner scanner = ConsoleReader.getScanner();
    private AuditService auditService;
    private WriteService writeService = WriteService.getInstance();

    private MenuService() {
        this.auditService = AuditService.getInstance();
    }

    public void start() {
        this.logUser();
        if (currentUser == null) {
            return;
        }
        if (currentUser.isAdmin() == true) {
            this.adminAction();
        } else {
            this.userAction();
        }
    }

    private void adminAction() {
        int option;
        do {
            System.out.println("LOADING....To be continued");
            option = 4;
//                    System.out.println("ADMIN menu:");
//                    System.out.println("1) Show restaurants");
//                    System.out.println("2) Add restaurant");
//                    System.out.println("3) Choose a restaurant to add a dish");
//                    System.out.println("4) Logout");
//                    option = scanner.nextInt();
//                    ///toDo

        } while (option != 4);
    }

    private void userAction() {
        boolean success = true;
        currentRestaurant = restaurantMgr.chooseRestaurant();
        while (success) {
            if (getCurrentUser() == null) {
                this.start(); //to relog
                return;
            }

            System.out.println("How can we help you? ");
            System.out.println("1) Give feedback.");
            System.out.println("2) Order");
            System.out.println("3) See my orders");
            System.out.println("4) Write in file details about this restauant");
            System.out.println("5) Write in file details about my profile");
            System.out.println("6) Write in file details about special product");
            System.out.println("7) Logout");

            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    auditService.writeTime("add_raiting");
                    currentRestaurant.addRating();
                    break;
                case 2:
                    auditService.writeTime("order");
                    currentOrder = currentRestaurant.placeOrder(currentUser);
                    break;
                case 3:
                    auditService.writeTime("see_my_orders");
                    currentUser.seeOrders();
                    break;
                case 4:
                    auditService.writeTime("write_details_restaurant");
                    writeService.writeDetails(currentRestaurant, currentRestaurant.getClass());
                    break;
                case 5:
                    auditService.writeTime("write_details_profile");
                    writeService.writeDetails(currentUser, currentUser.getClass());
                    break;
                case 6:
                    auditService.writeTime("write_just_special_product");
                    Product specialProduct = currentRestaurant.getSpecialProduct();
                    writeService.writeDetails(specialProduct, specialProduct.getClass());
                    break;

                case 7:
                    auditService.writeTime("logout");
                    this.logOut();
                    break;

                default:
                    success = false;
                    System.out.println("Invalid option! :(");
            }
        }
    }

    private void logOut() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return this.currentUser;
    }

    public void logUser() {
        if (getCurrentUser() == null) {
            int option;
            while (getCurrentUser() == null) {
                System.out.println("Choose option:");
                System.out.println("1) Login");
                System.out.println("2) Register");
                System.out.println("3) Close");
                System.out.println("Your option:");
                option = scanner.nextInt();
                if (option == 1) {
                    auditService.writeTime("login");
                    currentUser = userMgr.login();
                } else if (option == 2) {
                    auditService.writeTime("register");
                    currentUser = userMgr.register();
                } else if (option == 3) {
                    return;//toDo
                } else {
                    System.out.println("Invalid option :(");
                }
            }
        } else {
            System.out.println("You are already logged in!");
        }
    }

    public static MenuService getInstance() {
        if (instance == null) {
            instance = new MenuService();
        }
        return instance;
    }
}
