package com.company.services;

import com.company.entities.Adress;
import com.company.entities.ConsoleReader;
import com.company.entities.County;
import com.company.entities.User;
import com.company.interfaces.IUserManager;

import java.util.ArrayList;
import java.util.Scanner;

public class UserService implements IUserManager {
    public static ArrayList<User> users = new ArrayList<User>();
    public Scanner scanner = ConsoleReader.getScanner();

    public UserService() {
        Adress a1 = new Adress(County.IASI, "Iasi", "Unirii", 20);

        User u1 = new User("Miruna", a1, "123", "miruna@gmail.com");
        User u2 = new User("Miru", a1, "000", "miruna@yahoo.com");

        u1.setAdmin(true);

        users.add(u1);
        users.add(u2);
    }

    static User searchUser(String email, String password) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User login() {
        User currentUser = null;
        while (currentUser == null) {
            System.out.println("Email:");
            String email = scanner.next().trim();
            System.out.println("Password:");
            String password = scanner.next().trim();

            currentUser = this.searchUser(email, password);
            if (currentUser == null) {
                System.out.println("\nEmail or password not correct. Try again!");
            }
        }

        return currentUser;
    }

    @Override
    public User register() {
        User currentUser = null;

        System.out.println("Email:");
        String email = scanner.next().trim();

        System.out.println("Name:");
        String name = scanner.next().trim();

        System.out.println("Address:");
        Adress currentAddress = Adress.createNewAddress(scanner);

        System.out.println("Password:");
        String password = scanner.next().trim();

        currentUser = new User(name, currentAddress, password, email);
        users.add(currentUser);

        System.out.println("SUCCESSFULLY REGISTERED!");

        for (User person : users) {
            System.out.println(person);
        }
        return currentUser;
    }

}
