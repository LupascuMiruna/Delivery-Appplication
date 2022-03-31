package com.company;

import com.company.services.MenuService;

public class Main {

    public static void main(String[] args) {
        MenuService menu = MenuService.getInstance();
        menu.start();
    }
}
